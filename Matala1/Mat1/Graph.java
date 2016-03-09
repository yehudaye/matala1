/******************************************************************************
 *  Compilation:  javac Digraph.java
 *  Execution:    java Digraph filename.txt
 *  Dependencies: Collection.java In.java StdOut.java
 *  Open source:  http://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Digraph.java.html
 *
 *  A graph, implemented using an array of lists.
 *  Parallel edges and self-loops are permitted.
 *  Directed graph
 ******************************************************************************/

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
//import java.util.Collection;
//import In.java.StdOut;
//import In.StdOut.java;
/**
 * The <tt>Digraph</tt> class represents a directed graph of vertices named 0
 * through <em>V</em> - 1. It supports the following two primary operations: add
 * an edge to the digraph, iterate over all of the vertices adjacent from a
 * given vertex. Parallel edges and self-loops are permitted.
 * <p>
 * This implementation uses an adjacency-lists representation, which is a
 * vertex-indexed array of {@link Collection} objects. All operations take constant
 * time (in the worst case) except iterating over the vertices adjacent from a
 * given vertex, which takes time proportional to the number of such vertices.
 * <p>
 * For additional documentation, see <a
 * href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 */

public class Graph{
	private static final String NEWLINE = System.getProperty("line.separator");
	private final int nodes; // number of nodes in this digraph
	private int edges; // number of edges in this digraph
	private Collection<Integer>[] adj; // adj[nodes] = adjacency list for vertex v
	private int[] indegree; // indegree[nodes] = indegree of vertex v ( number of directed edges )
	private double weight;   // weight of a edge

	/**
	 * Initializes an empty graph with <em>V</em> vertices.
	 *
	 * @param V
	 *            the number of vertices
	 * @throws IllegalArgumentException
	 *             if V < 0
	 */
	@SuppressWarnings("unchecked")
	public Graph(int nodes) {
		if (nodes < 0)
			throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		this.nodes = nodes;
		this.edges = 0;
		indegree = new int[nodes];
		weight = 0;
		adj = (Collection<Integer>[]) new Collection[nodes];
		for (int v = 0; v < nodes; v++) {
			adj[v] = new Collection<Integer>();
		}
	}

	/**
	 * Initializes a digraph from the specified input stream. The format is the
	 * number of vertices <em>V</em>, followed by the number of edges <em>E</em>
	 * , followed by <em>E</em> pairs of vertices, with each entry separated by
	 * whitespace.
	 *
	 * @param in
	 *            the input stream
	 * @throws IndexOutOfBoundsException
	 *             if the endpoints of any edge are not in prescribed range
	 * @throws IllegalArgumentException
	 *             if the number of vertices or edges is negative
	 */
	public Graph(In in) {
		try {
			this.nodes = in.readInt();
			if (nodes < 0)
				throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
			indegree = new int[nodes];
			adj = (Collection<Integer>[]) new Collection[nodes];
			for (int v = 0; v < nodes; v++) {
				adj[v] = new Collection<Integer>();
			}
			int edges = in.readInt();
			if (edges < 0)
				throw new IllegalArgumentException(	"Number of edges in a Digraph must be nonnegative");
			for (int i = 0; i < edges; i++) {
				int u = in.readInt();
				int v = in.readInt();
				double w = in.readInt();
				addEdge(u,v,w);
			}
		} catch (NoSuchElementException e) {
			throw new InputMismatchException(
					"Invalid input format in Digraph constructor");
		}
	}

	/**
	 * Initializes a new digraph that is a deep copy of the specified digraph.
	 *
	 * @param G
	 *            the digraph to copy
	 */
	/*public Digraph(Digraph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < V; v++)
			this.indegree[v] = G.indegree(v);
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj[v].add(w);
			}
		}
	}*/

	
	/**
	 * Returns the number of vertices in this digraph.
	 *
	 * @return the number of vertices in this digraph
	 */
	public int nodes() {
		return nodes;
	}

	/**
	 * Returns the number of edges in this digraph.
	 *
	 * @return the number of edges in this digraph
	 */
	public int edges() {
		return edges;
	}

	// throw an IndexOutOfBoundsException unless 0 <= v < V
	private void validateVertex(int v) {
		if (v < 0 || v >= nodes)
			throw new IndexOutOfBoundsException("vertex " + v	+ " is not between 0 and " + (nodes - 1));
	}

	/**
	 * Adds the directed edge v->w to this digraph.
	 *
	 * @param v
	 *            the tail vertex
	 * @param w
	 *            the head vertex
	 * @throws IndexOutOfBoundsException
	 *             unless both 0 <= v < V and 0 <= w < V
	 */
	
	public void addEdge(int u, int v, double w) {
		validateVertex(u);
		validateVertex(v);
		adj[u].add(v);
		indegree[v]++;
		edges++;
		weight = w;
	}

	/**
	 * Returns the vertices adjacent from vertex <tt>v</tt> in this digraph.
	 *
	 * @param v
	 *            the vertex
	 * @return the vertices adjacent from vertex <tt>v</tt> in this digraph, as
	 *         an iterable
	 * @throws IndexOutOfBoundsException
	 *             unless 0 <= v < V
	 */
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/**
	 * Returns the number of directed edges incident from vertex <tt>v</tt>.
	 * This is known as the <em>outdegree</em> of vertex <tt>v</tt>.
	 *
	 * @param v
	 *            the vertex
	 * @return the outdegree of vertex <tt>v</tt>
	 * @throws IndexOutOfBoundsException
	 *             unless 0 <= v < V
	 */
	/*public int outdegree(int v) {
		validateVertex(v);
		return adj[v].size();
	}*/

	/**
	 * Returns the number of directed edges incident to vertex <tt>v</tt>. This
	 * is known as the <em>indegree</em> of vertex <tt>v</tt>.
	 *
	 * @param v
	 *            the vertex
	 * @return the indegree of vertex <tt>v</tt>
	 * @throws IndexOutOfBoundsException
	 *             unless 0 <= v < V
	 */
	public int indegree(int v) {
		validateVertex(v);
		return indegree[v];
	}

	/**
	 * Returns the reverse of the digraph.
	 *
	 * @return the reverse of the digraph
	 */
	/*public Graph reverse() {
		Digraph R = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				R.addEdge(w, v);
			}
		}
		return R;
	}*/

	/**
	 * Returns a string representation of the graph.
	 *
	 * @return the number of vertices <em>V</em>, followed by the number of
	 *         edges <em>E</em>, followed by the <em>V</em> adjacency lists
	 */
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(nodes + " vertices, " + edges + " edges " + NEWLINE);
		for (int u = 0; u < nodes; u++) {
			s.append(String.format("%d: ", u));
			for (int v : adj[u]) {
				s.append(String.format("%d ", v));
				for (int w: edges.weight)
					s.append(String.format("%d: ",w));
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * Unit tests the <tt>Digraph</tt> data type.
	 */
	public static void main(String[] args) {
		In in = new In("tinyEWD.txt");
		Graph G = new Graph(in);
		StdOut.println(G);
	}

}