package C_R;

import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Queue;


//import javax.swing.text.html.HTMLDocument.Iterator;

//import java.security.AllPermission;

public class Graph_algo {

	private DijkstraSP[] all;  
	private ArrayList<Integer> num;
	private Bag<DirectedEdge>[] adj;
	//private Queue<Double> q = new Queue<Double>();
	//private int vertex=Graph.V;

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

	/*public void printallpath()
	    {
	    	for(int i=0;i<all.length;i++){
	    		System.out.println(all[i]);
	    	}
	    }*/

	@SuppressWarnings("rawtypes")
	public void pathBL(ArrayList<Integer> num2) {
		num= new ArrayList<Integer>();
		for (int i = 0; i < num2.size(); i++) {
			num.add(num2.get(i));
			Bag B = adj[num2.get(i)];
			Iterator It = B.iterator();
			for (int j = 0; It.hasNext() == true ; j++) {
				DirectedEdge e = (DirectedEdge)It.next();
				q.enqueue(e.weight());
				e.Setweight(Double.MAX_VALUE);
			}
			
		}
	}
	
	public void update (DirectedEdge e) {
		int v= e.from();
		int w = e.to();
		validateVertex(v);
		validateVertex(w);
		adj[v].remove(index_Num(v,w));
		adj[v].add(e);
	}
	
	private int index_Num(int u, int v) {
		
		
	}
	
    private void validateVertex(int v) {
        if (v < 0 || v >= Graph.V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }

	public static void main(String[] args) {
		In in = new In("mediumEWD.txt");
		Graph G = new Graph(in);
		Graph_algo T = new Graph_algo(G);
		StdOut.println(T.dist(6,3));
		StdOut.println(T.path(2,6));    
		//T.printallpath();
		
		
		
	}
}

