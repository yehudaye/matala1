/*
 * Decompiled with CFR 0_114.
 */
public class DijkstraAllPairsSP {
    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
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
}

