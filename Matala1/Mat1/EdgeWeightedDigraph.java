/*
 * Decompiled with CFR 0_114.
 */
import java.util.Stack;

public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    private int[] indegree;

    public EdgeWeightedDigraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        this.adj = new Bag[V];
        int v = 0;
        while (v < V) {
            this.adj[v] = new Bag();
            ++v;
        }
    }

    public EdgeWeightedDigraph(int V, int E) {
        this(V);
        if (E < 0) {
            throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
        }
        int i = 0;
        while (i < E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * (double)StdRandom.uniform(100);
            DirectedEdge e = new DirectedEdge(v, w, weight);
            this.addEdge(e);
            ++i;
        }
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("Number of edges must be nonnegative");
        }
        int i = 0;
        while (i < E) {
            int v = in.readInt();
            int w = in.readInt();
            if (v < 0 || v >= this.V) {
                throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (this.V - 1));
            }
            if (w < 0 || w >= this.V) {
                throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (this.V - 1));
            }
            double weight = in.readDouble();
            this.addEdge(new DirectedEdge(v, w, weight));
            ++i;
        }
    }

    public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
        this(G.V());
        this.E = G.E();
        int v = 0;
        while (v < G.V()) {
            this.indegree[v] = G.indegree(v);
            ++v;
        }
        v = 0;
        while (v < G.V()) {
            Stack<DirectedEdge> reverse = new Stack<DirectedEdge>();
            for (DirectedEdge e2 : G.adj[v]) {
                reverse.push(e2);
            }
            for (DirectedEdge e2 : reverse) {
                this.adj[v].add(e2);
            }
            ++v;
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= this.V) {
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (this.V - 1));
        }
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        this.validateVertex(v);
        this.validateVertex(w);
        this.adj[v].add(e);
        int[] arrn = this.indegree;
        int n = w;
        arrn[n] = arrn[n] + 1;
        ++this.E;
    }

    public Iterable<DirectedEdge> adj(int v) {
        this.validateVertex(v);
        return this.adj[v];
    }

    public int outdegree(int v) {
        this.validateVertex(v);
        return this.adj[v].size();
    }

    public int indegree(int v) {
        this.validateVertex(v);
        return this.indegree[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        int v = 0;
        while (v < this.V) {
            for (DirectedEdge e : this.adj(v)) {
                list.add(e);
            }
            ++v;
        }
        return list;
    }

    public void validAll() {
        Iterable<DirectedEdge> ee = this.edges();
        for (DirectedEdge de : ee) {
            de.setValid(true);
        }
    }

    public void setValidateVertex(int v, boolean value) {
        Iterable<DirectedEdge> ee = this.adj(v);
        for (DirectedEdge de : ee) {
            de.setValid(value);
        }
    }

    public void setValidateVertex(int[] vv, boolean value) {
        int i = 0;
        while (i < vv.length) {
            this.setValidateVertex(vv[i], value);
            ++i;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.valueOf(this.V) + " " + this.E + NEWLINE);
        int v = 0;
        while (v < this.V) {
            s.append(String.valueOf(v) + ": ");
            for (DirectedEdge e : this.adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
            ++v;
        }
        return s.toString();
    }

    public static void main(String[] args) {
      In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        StdOut.println(G);
    }
}

