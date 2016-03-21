/*
 * Decompiled with CFR 0_114.
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class StdAudio {
    public static final int SAMPLE_RATE = 44100;
    private static final int BYTES_PER_SAMPLE = 2;
    private static final int BITS_PER_SAMPLE = 16;
    private static final double MAX_16_BIT = 32767.0;
    private static final int SAMPLE_BUFFER_SIZE = 4096;
    private static SourceDataLine line;
    private static byte[] buffer;
    private static int bufferSize;

    static {
        bufferSize = 0;
        StdAudio.init();
    }

    private StdAudio() {
    }

    private static void init() {
        try {
            AudioFormat format = new AudioFormat(44100.0f, 16, 1, true, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine)AudioSystem.getLine(info);
            line.open(format, 8192);
            buffer = new byte[2730];
        }
        catch (LineUnavailableException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        line.start();
    }

    public static void close() {
        line.drain();
        line.stop();
    }

    public static void play(double sample) {
        if (Double.isNaN(sample)) {
            throw new IllegalArgumentException("sample is NaN");
        }
        if (sample < -1.0) {
            sample = -1.0;
        }
        if (sample > 1.0) {
            sample = 1.0;
        }
        short s = (short)(32767.0 * sample);
        StdAudio.buffer[StdAudio.bufferSize++] = (byte)s;
        StdAudio.buffer[StdAudio.bufferSize++] = (byte)(s >> 8);
        if (bufferSize >= buffer.length) {
            line.write(buffer, 0, buffer.length);
            bufferSize = 0;
        }
    }

    public static void play(double[] samples) {
        if (samples == null) {
            throw new NullPointerException("argument to play() is null");
        }
        int i = 0;
        while (i < samples.length) {
            StdAudio.play(samples[i]);
            ++i;
        }
    }

    public static double[] read(String filename) {
        byte[] data = StdAudio.readByte(filename);
        int N = data.length;
        double[] d = new double[N / 2];
        int i = 0;
        while (i < N / 2) {
            d[i] = (double)((short)(((data[2 * i + 1] & 255) << 8) + (data[2 * i] & 255))) / 32767.0;
            ++i;
        }
        return d;
    }

    public static void play(String filename) {
        URL url = null;
        try {
            File file = new File(filename);
            if (file.canRead()) {
                url = file.toURI().toURL();
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url == null) {
            throw new RuntimeException("audio " + filename + " not found");
        }
        AudioClip clip = Applet.newAudioClip(url);
        clip.play();
    }

    public static void loop(String filename) {
        URL url = null;
        try {
            File file = new File(filename);
            if (file.canRead()) {
                url = file.toURI().toURL();
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url == null) {
            throw new RuntimeException("audio " + filename + " not found");
        }
        AudioClip clip = Applet.newAudioClip(url);
        clip.loop();
    }

    private static byte[] readByte(String filename) {
        byte[] data = null;
        AudioInputStream ais = null;
        try {
            File file = new File(filename);
            if (file.exists()) {
                ais = AudioSystem.getAudioInputStream(file);
                data = new byte[ais.available()];
                ais.read(data);
            } else {
                URL url = StdAudio.class.getResource(filename);
                ais = AudioSystem.getAudioInputStream(url);
                data = new byte[ais.available()];
                ais.read(data);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Could not read " + filename);
        }
        catch (UnsupportedAudioFileException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(String.valueOf(filename) + " in unsupported audio format");
        }
        return data;
    }

    public static void save(String filename, double[] samples) {
        block5 : {
            AudioFormat format = new AudioFormat(44100.0f, 16, 1, true, false);
            byte[] data = new byte[2 * samples.length];
            int i = 0;
            while (i < samples.length) {
                short temp = (short)(samples[i] * 32767.0);
                data[2 * i + 0] = (byte)temp;
                data[2 * i + 1] = (byte)(temp >> 8);
                ++i;
            }
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                AudioInputStream ais = new AudioInputStream(bais, format, samples.length);
                if (filename.endsWith(".wav") || filename.endsWith(".WAV")) {
                    AudioSystem.write(ais, AudioFileFormat.Type.WAVE, new File(filename));
                    break block5;
                }
                if (filename.endsWith(".au") || filename.endsWith(".AU")) {
                    AudioSystem.write(ais, AudioFileFormat.Type.AU, new File(filename));
                    break block5;
                }
                throw new RuntimeException("File format not supported: " + filename);
            }
            catch (IOException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
    }

    private static double[] note(double hz, double duration, double amplitude) {
        int N = (int)(44100.0 * duration);
        double[] a = new double[N + 1];
        int i = 0;
        while (i <= N) {
            a[i] = amplitude * Math.sin(6.283185307179586 * (double)i * hz / 44100.0);
            ++i;
        }
        return a;
    }

    public static void main(String[] args) {
        double freq = 440.0;
        int i = 0;
        while (i <= 44100) {
            StdAudio.play(0.5 * Math.sin(6.283185307179586 * freq * (double)i / 44100.0));
            ++i;
        }
        int[] arrn = new int[8];
        arrn[1] = 2;
        arrn[2] = 4;
        arrn[3] = 5;
        arrn[4] = 7;
        arrn[5] = 9;
        arrn[6] = 11;
        arrn[7] = 12;
        int[] steps = arrn;
        int i2 = 0;
        while (i2 < steps.length) {
            double hz = 440.0 * Math.pow(2.0, (double)steps[i2] / 12.0);
            StdAudio.play(StdAudio.note(hz, 1.0, 0.5));
            ++i2;
        }
        StdAudio.close();
        System.exit(0);
    }
}

