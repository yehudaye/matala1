package C_R;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tesing {
	In in = new In("tinyEWD.txt");
	Graph G = new Graph(in);
	Graph_algo T = new Graph_algo(G);

	@Test 
	public void testDist() {

		assertEquals(0.35, T.dist(4, 5), 0.01);
		assertEquals(0.37, T.dist(4, 7), 0.02);
		assertEquals(0.97, T.dist(2, 4), 0.01);
		assertEquals(0.6, T.dist(0, 7), 0.01);
		assertEquals(0.67, T.dist(4, 1), 0.01);
		assertEquals(1.13, T.dist(6, 3), 0.01);
	}

	/*@SuppressWarnings("deprecation")
	@Test
	public void testPath() {
		Iterable<DirectedEdge> r = new Iterable<DirectedEdge>( 3->6  0.52, 7->3  0.39, 2->7  0.34);

		 assertEquals(/*3->6  0.52, 7->3  0.39, 2->7  0.34*/T.path(2,6) ,T.path(2,6));
}
*/

/*@Test
	public void testPathBL() {
		assertEquals(3->6  0.52, 7->3  0.39, 2->7  0.34 ,T.pathBL(2,6,[1,4]));
		
	}*/
	
	@Test(timeout=1)
	public void testWithTimeout() {
		assertEquals(0.35, T.dist(4, 5), 0.01);
	}
	
}
	

