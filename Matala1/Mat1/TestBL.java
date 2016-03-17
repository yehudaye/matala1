package C_R;

import java.util.Stack;


public class TestBL {
    private static final String NEWLINE = System.getProperty("line.separator");

    private int Queries;                // number of vertices in this digraph
    private int s;   
    private int t;
    private int numBl;
    private int[] nodesBL;   
    
    /**
     * Initializes an empty edge-weighted digraph with <tt>V</tt> vertices and 0 edges.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if <tt>V</tt> < 0
     */
    /*public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }
*/
    /**
     * Initializes a random edge-weighted digraph with <tt>V</tt> vertices and <em>E</em> edges.
     *
     * @param  V the number of vertices
     * @param  E the number of edges
     * @throws IllegalArgumentException if <tt>V</tt> < 0
     * @throws IllegalArgumentException if <tt>E</tt> < 0
     */
    /*public Graph(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = .01 * StdRandom.uniform(100);
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }*/

    /**  
     * Initializes an edge-weighted digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IndexOutOfBoundsException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public TestBL(In in) {
        //this(in.readInt());
        int q = in.readInt();
        if (q < 0) throw new IllegalArgumentException("Number of queries must be nonnegative");
        for (int i = 0; i < q; i++) {
            int s = in.readInt();
            int t = in.readInt();
            if (s < 0 ) throw new IndexOutOfBoundsException("vertex " + s + "must be nonnegative");
            if (t < 0 ) throw new IndexOutOfBoundsException("vertex " + t + "must be nonnegative");
            int numbl = in.readInt();
            if(numbl<0)  throw new IndexOutOfBoundsException("number of black nodes " + numbl + "must be nonnegative");
            if(numbl==0) break;
            while (numbl != 0) {
            	int a = in.readInt();
            	a=Integer.MAX_VALUE;
            	numbl--;
            }
            	

            
        }
    }

   
    /**
     * Unit tests the <tt>EdgeWeightedDigraph</tt> data type.
     */
    public static void main(String[] args) {
        In in = new In("test1.txt");
        TestBL G = new TestBL(in);
        StdOut.println(G);
        //System.out.println(G.V +" "+ G.E);
    }
	
}