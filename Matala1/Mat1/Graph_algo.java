package M_1;
//SS

public class Graph_algo {
    private DijkstraSP[] all;

    public Graph_algo(Graph G) {
        this.all = new DijkstraSP[G.V()];
        int v = 0;
        while (v < G.V()) {
            this.all[v] = new DijkstraSP(G, v);
            ++v;
        }
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return this.all[s].pathTo(t);
    }

    public boolean hasPath(int s, int t) {
        if (this.dist(s, t) < Double.POSITIVE_INFINITY) {
            return true;
        }
        return false;
    }

    public double dist(int s, int t) {
        return this.all[s].distTo(t);
    }
    
    
	    public static void main(String[] args) {
      In in = new In("mediumEWD.txt");
      Graph G = new Graph(in);
      Graph_algo T = new Graph_algo(G);
       StdOut.println(T.dist(4,7));
        //StdOut.println(T.path(1,4));      
        //StdOut.println(T.dist(6,3));

}
    
}

