package C_R;
/*
 * Decompiled with CFR 0_114.
 */
public class DirectedEdge {
    public static final double INF = 1.7976931348623156E305;
    private final int v;
    private final int w;
    private final double weight;
    private boolean valid = true;

    public DirectedEdge(int v, int w, double weight) {
        if (v < 0) {
            throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
        }
        if (w < 0) {
            throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
        }
        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException("Weight is NaN");
        }
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return this.v;
    }

    public int to() {
        return this.w;
    }

    public double weight() {
        if (this.isValid()) {
            return this.weight;
        }
        return 1.7976931348623156E305;
    }

    public String toString() {
        return String.valueOf(this.v) + "->" + this.w + " " + String.format("%5.2f", this.weight);
    }

    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5.67);
        StdOut.println(e);
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

