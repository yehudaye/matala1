/*
 * Decompiled with CFR 0_114.
 */
import java.util.Stack;

public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        this.adj = new Bag[V];
        int v = 0;
        while (v < V) {
            this.adj[v] = new Bag();
            ++v;
        }
    }

    public EdgeWeightedGraph(int V, int E) {
        this(V);
        if (E < 0) {
            throw new IllegalArgumentException("Number of edges must be nonnegative");
        }
        int i = 0;
        while (i < E) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = (double)Math.round(100.0 * StdRandom.uniform()) / 100.0;
            Edge e = new Edge(v, w, weight);
            this.addEdge(e);
            ++i;
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("Number of edges must be nonnegative");
        }
        int i = 0;
        while (i < E) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            this.addEdge(e);
            ++i;
        }
    }

    public EdgeWeightedGraph(EdgeWeightedGraph G) {
        this(G.V());
        this.E = G.E();
        int v = 0;
        while (v < G.V()) {
            Stack<Edge> reverse = new Stack<Edge>();
            for (Edge e2 : G.adj[v]) {
                reverse.push(e2);
            }
            for (Edge e2 : reverse) {
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

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        this.validateVertex(v);
        this.validateVertex(w);
        this.adj[v].add(e);
        this.adj[w].add(e);
        ++this.E;
    }

    public Iterable<Edge> adj(int v) {
        this.validateVertex(v);
        return this.adj[v];
    }

    public int degree(int v) {
        this.validateVertex(v);
        return this.adj[v].size();
    }

    public Iterable<Edge> edges() {
        Bag list = new Bag();
        int v = 0;
        while (v < this.V) {
            int selfLoops = 0;
            for (Edge e : this.adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                    continue;
                }
                if (e.other(v) != v) continue;
                if (selfLoops % 2 == 0) {
                    list.add(e);
                }
                ++selfLoops;
            }
            ++v;
        }
        return list;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.valueOf(this.V) + " " + this.E + NEWLINE);
        int v = 0;
        while (v < this.V) {
            s.append(String.valueOf(v) + ": ");
            for (Edge e : this.adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
            ++v;
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        StdOut.println(G);
    }
}

