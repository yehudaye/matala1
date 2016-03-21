/*
 * Decompiled with CFR 0_114.
 */
import java.util.Iterator;
import java.util.Stack;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int V) {
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

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("Number of edges must be nonnegative");
        }
        int i = 0;
        while (i < E) {
            int v = in.readInt();
            int w = in.readInt();
            this.addEdge(v, w);
            ++i;
        }
    }

    public Graph(Graph G) {
        this(G.V());
        this.E = G.E();
        int v = 0;
        while (v < G.V()) {
            int w;
            Stack<Integer> reverse = new Stack<Integer>();
            Iterator iterator = G.adj[v].iterator();
            while (iterator.hasNext()) {
                w = (int) iterator.next();
                reverse.push(w);
            }
            iterator = reverse.iterator();
            while (iterator.hasNext()) {
                w = (Integer)iterator.next();
                this.adj[v].add(w);
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

    public void addEdge(int v, int w) {
        this.validateVertex(v);
        this.validateVertex(w);
        ++this.E;
        this.adj[v].add(w);
        this.adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        this.validateVertex(v);
        return this.adj[v];
    }

    public int degree(int v) {
        this.validateVertex(v);
        return this.adj[v].size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.valueOf(this.V) + " vertices, " + this.E + " edges " + NEWLINE);
        int v = 0;
        while (v < this.V) {
            s.append(String.valueOf(v) + ": ");
            Iterator<Integer> iterator = this.adj[v].iterator();
            while (iterator.hasNext()) {
                int w = iterator.next();
                s.append(String.valueOf(w) + " ");
            }
            s.append(NEWLINE);
            ++v;
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);
    }
}

