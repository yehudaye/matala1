package C_R;

public class Graph_algo {
	
	    private DijkstraSP[] all;

	    /**
	     * Computes a shortest paths tree from each vertex to to every other vertex in
	     * the edge-weighted digraph <tt>G</tt>.
	     * @param G the edge-weighted digraph
	     * @throws IllegalArgumentException if an edge weight is negative
	     * @throws IllegalArgumentException unless 0 &le; <tt>s</tt> &le; <tt>V</tt> - 1
	     */
	    public Graph_algo(Graph G) {
	        all  = new DijkstraSP[G.V()];
	        for (int v = 0; v < G.V(); v++)
	            all[v] = new DijkstraSP(G, v);
	    }

	    /**
	     * Returns a shortest path from vertex <tt>s</tt> to vertex <tt>t</tt>.
	     * @param s the source vertex
	     * @param t the destination vertex
	     * @return a shortest path from vertex <tt>s</tt> to vertex <tt>t</tt>
	     *    as an iterable of edges, and <tt>null</tt> if no such path
	     */
	    public Iterable<DirectedEdge> path(int s, int t) {
	        return all[s].pathTo(t);
	    }

	    /**
	     * Is there a path from the vertex <tt>s</tt> to vertex <tt>t</tt>?
	     * @param s the source vertex
	     * @param t the destination vertex
	     * @return <tt>true</tt> if there is a path from vertex <tt>s</tt> 
	     *    to vertex <tt>t</tt>, and <tt>false</tt> otherwise
	     */
	    public boolean hasPath(int s, int t) {
	        return dist(s, t) < Double.POSITIVE_INFINITY;
	    }

	    /**
	     * Returns the length of a shortest path from vertex <tt>s</tt> to vertex <tt>t</tt>.
	     * @param s the source vertex
	     * @param t the destination vertex
	     * @return the length of a shortest path from vertex <tt>s</tt> to vertex <tt>t</tt>;
	     *    <tt>Double.POSITIVE_INFINITY</tt> if no such path
	     */
	    
	    public double dist(int s, int t) {
	        return all[s].distTo(t);
	    }
	    
	    /*public static void main(String[] args) {
        In in = new In("tinyEWD.txt");
        Graph G = new Graph(in);
        Graph_algo T = new Graph_algo(G);
        StdOut.println(T.dist(1,4));
        StdOut.println(T.path(1,4));      
    }
    */
}

