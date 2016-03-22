package C_R;

/*
 * Decompiled with CFR 0_114.
 */
import java.io.PrintStream;
import java.util.Stack;

public class DijkstraSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(Graph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() >= 0.0) continue;
            throw new IllegalArgumentException("edge " + e + " has negative weight");
        }
        this.distTo = new double[G.V()];
        this.edgeTo = new DirectedEdge[G.V()];
        int v = 0;
        while (v < G.V()) {
            this.distTo[v] = Double.POSITIVE_INFINITY;
            ++v;
        }
        this.distTo[s] = 0.0;
        this.pq = new IndexMinPQ(G.V());
        this.pq.insert(s, this.distTo[s]);
        while (!this.pq.isEmpty()) {
            v = this.pq.delMin();
            for (DirectedEdge e2 : G.adj(v)) {
                this.relax(e2);
            }
        }
        assert (this.check(G, s));
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (this.distTo[w] > this.distTo[v] + e.weight()) {
            this.distTo[w] = this.distTo[v] + e.weight();
            this.edgeTo[w] = e;
            if (this.pq.contains(w)) {
                this.pq.decreaseKey(w, this.distTo[w]);
            } else {
                this.pq.insert(w, this.distTo[w]);
            }
        }
    }

    public double distTo(int v) {
        return this.distTo[v];
    }

    public boolean hasPathTo(int v) {
        if (this.distTo[v] < Double.POSITIVE_INFINITY) {
            return true;
        }
        return false;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!this.hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        DirectedEdge e = this.edgeTo[v];
        while (e != null) {
            path.push(e);
            e = this.edgeTo[e.from()];
        }
        return path;
    }

    private boolean check(Graph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() >= 0.0) continue;
            System.err.println("negative edge weight detected");
            return false;
        }
        if (this.distTo[s] != 0.0 || this.edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        int v = 0;
        while (v < G.V()) {
            if (v != s && this.edgeTo[v] == null && this.distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
            ++v;
        }
        v = 0;
        while (v < G.V()) {
            for (DirectedEdge e2 : G.adj(v)) {
                int w = e2.to();
                if (this.distTo[v] + e2.weight() >= this.distTo[w]) continue;
                System.err.println("edge " + e2 + " not relaxed");
                return false;
            }
            ++v;
        }
        int w = 0;
        while (w < G.V()) {
            if (this.edgeTo[w] != null) {
                DirectedEdge e2;
                e2 = this.edgeTo[w];
                int v2 = e2.from();
                if (w != e2.to()) {
                    return false;
                }
                if (this.distTo[v2] + e2.weight() != this.distTo[w]) {
                    System.err.println("edge " + e2 + " on shortest path not tight");
                    return false;
                }
            }
            ++w;
        }
        return true;
    }

    public static void main(String[] args) {
        String f = "data/mediumEWD.txt";
        if (args != null && args.length > 0) {
            f = args[0];
        }
        In in = new In(f);
        Graph G = new Graph(in);
        int s = Integer.parseInt("1");
        DijkstraSP sp = new DijkstraSP(G, s);
        int t = 0;
        while (t < G.V()) {
            if (t % 100 == 0 && sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            ++t;
        }
        int[] invalid = new int[]{44, 14, 128};
        G.setValidateVertex(invalid, false);
        sp = new DijkstraSP(G, s);
        int t2 = 0;
        while (t2 < G.V()) {
            if (t2 % 100 == 0 && sp.hasPathTo(t2)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t2, sp.distTo(t2));
                for (DirectedEdge e : sp.pathTo(t2)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            ++t2;
        }
    }
}

