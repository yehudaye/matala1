package M1;

//QQ

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class In {
    private static final String CHARSET_NAME = "UTF-8";
    private static final Locale LOCALE = Locale.US;
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");
    private Scanner scanner;

    public In() {
        this.scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");
        this.scanner.useLocale(LOCALE);
    }

    public In(Socket socket) {
        if (socket == null) {
            throw new NullPointerException("argument is null");
        }
        try {
            InputStream is = socket.getInputStream();
            this.scanner = new Scanner(new BufferedInputStream(is), "UTF-8");
            this.scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + socket);
        }
    }

    public In(URL url) {
        if (url == null) {
            throw new NullPointerException("argument is null");
        }
        try {
            URLConnection site = url.openConnection();
            InputStream is = site.getInputStream();
            this.scanner = new Scanner(new BufferedInputStream(is), "UTF-8");
            this.scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + url);
        }
    }

    public In(File file) {
        if (file == null) {
            throw new NullPointerException("argument is null");
        }
        try {
            this.scanner = new Scanner(file, "UTF-8");
            this.scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + file);
        }
    }

    public In(String name) {
        if (name == null) {
            throw new NullPointerException("argument is null");
        }
        try {
            File file = new File(name);
            if (file.exists()) {
                this.scanner = new Scanner(file, "UTF-8");
                this.scanner.useLocale(LOCALE);
                return;
            }
            URL url = this.getClass().getResource(name);
            if (url == null) {
                url = new URL(name);
            }
            URLConnection site = url.openConnection();
            InputStream is = site.getInputStream();
            this.scanner = new Scanner(new BufferedInputStream(is), "UTF-8");
            this.scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + name);
        }
    }

    public In(Scanner scanner) {
        if (scanner == null) {
            throw new NullPointerException("argument is null");
        }
        this.scanner = scanner;
    }

    public boolean exists() {
        if (this.scanner != null) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return !this.scanner.hasNext();
    }

    public boolean hasNextLine() {
        return this.scanner.hasNextLine();
    }

    public boolean hasNextChar() {
        this.scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = this.scanner.hasNext();
        this.scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }

    public String readLine() {
        String line;
        try {
            line = this.scanner.nextLine();
        }
        catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }

    public char readChar() {
        this.scanner.useDelimiter(EMPTY_PATTERN);
        String ch = this.scanner.next();
        assert (ch.length() == 1);
        this.scanner.useDelimiter(WHITESPACE_PATTERN);
        return ch.charAt(0);
    }

    public String readAll() {
        if (!this.scanner.hasNextLine()) {
            return "";
        }
        String result = this.scanner.useDelimiter(EVERYTHING_PATTERN).next();
        this.scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }

    public String readString() {
        return this.scanner.next();
    }

    public int readInt() {
        return this.scanner.nextInt();
    }

    public double readDouble() {
        return this.scanner.nextDouble();
    }

    public float readFloat() {
        return this.scanner.nextFloat();
    }

    public long readLong() {
        return this.scanner.nextLong();
    }

    public short readShort() {
        return this.scanner.nextShort();
    }

    public byte readByte() {
        return this.scanner.nextByte();
    }

    public boolean readBoolean() {
        String s = this.readString();
        if (s.equalsIgnoreCase("true")) {
            return true;
        }
        if (s.equalsIgnoreCase("false")) {
            return false;
        }
        if (s.equals("1")) {
            return true;
        }
        if (s.equals("0")) {
            return false;
        }
        throw new InputMismatchException();
    }

    public String[] readAllStrings() {
        String[] tokens = WHITESPACE_PATTERN.split(this.readAll());
        if (tokens.length == 0 || tokens[0].length() > 0) {
            return tokens;
        }
        String[] decapitokens = new String[tokens.length - 1];
        int i = 0;
        while (i < tokens.length - 1) {
            decapitokens[i] = tokens[i + 1];
            ++i;
        }
        return decapitokens;
    }

    public String[] readAllLines() {
        ArrayList<String> lines = new ArrayList<String>();
        while (this.hasNextLine()) {
            lines.add(this.readLine());
        }
        return lines.toArray(new String[0]);
    }

    public int[] readAllInts() {
        String[] fields = this.readAllStrings();
        int[] vals = new int[fields.length];
        int i = 0;
        while (i < fields.length) {
            vals[i] = Integer.parseInt(fields[i]);
            ++i;
        }
        return vals;
    }

    public double[] readAllDoubles() {
        String[] fields = this.readAllStrings();
        double[] vals = new double[fields.length];
        int i = 0;
        while (i < fields.length) {
            vals[i] = Double.parseDouble(fields[i]);
            ++i;
        }
        return vals;
    }

    public void close() {
        this.scanner.close();
    }

    public static int[] readInts(String filename) {
        return new In(filename).readAllInts();
    }

    public static double[] readDoubles(String filename) {
        return new In(filename).readAllDoubles();
    }

    public static String[] readStrings(String filename) {
        return new In(filename).readAllStrings();
    }

    public static int[] readInts() {
        return new In().readAllInts();
    }

    public static double[] readDoubles() {
        return new In().readAllDoubles();
    }

    public static String[] readStrings() {
        return new In().readAllStrings();
    }

    public static void main(String[] args) {
        String s;
        String s2;
        In in;
        String urlName = "http://introcs.cs.princeton.edu/stdlib/InTest.txt";
        System.out.println("readAll() from URL " + urlName);
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In(urlName);
            System.out.println(in.readAll());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
        System.out.println("readLine() from URL " + urlName);
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In(urlName);
            while (!in.isEmpty()) {
                s = in.readLine();
                System.out.println(s);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
        System.out.println("readString() from URL " + urlName);
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In(urlName);
            while (!in.isEmpty()) {
                s = in.readString();
                System.out.println(s);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
        System.out.println("readLine() from current directory");
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In("./InTest.txt");
            while (!in.isEmpty()) {
                s = in.readLine();
                System.out.println(s);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
        System.out.println("readLine() from relative path");
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In("../stdlib/InTest.txt");
            while (!in.isEmpty()) {
                s = in.readLine();
                System.out.println(s);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
        System.out.println("readChar() from file");
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In("InTest.txt");
            while (!in.isEmpty()) {
                char c = in.readChar();
                System.out.print(c);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
        System.out.println();
        System.out.println("readLine() from absolute OS X / Linux path");
        System.out.println("---------------------------------------------------------------------------");
        in = new In("/n/fs/introcs/www/java/stdlib/InTest.txt");
        try {
            while (!in.isEmpty()) {
                s2 = in.readLine();
                System.out.println(s2);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
        System.out.println("readLine() from absolute Windows path");
        System.out.println("---------------------------------------------------------------------------");
        try {
            in = new In("G:\\www\\introcs\\stdlib\\InTest.txt");
            while (!in.isEmpty()) {
                s2 = in.readLine();
                System.out.println(s2);
            }
            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
    }
}

