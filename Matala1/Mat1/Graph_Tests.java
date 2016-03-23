package M_1;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.Test;
//GG

public class Graph_Tests {

	@Test
	public void test1() throws Exception {
		 String File_Name_Exepectes = "Solution_test3.txt_mediumEWD.txt_1458730589160_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("mediumEWD.txt", "test3.txt");
	     boolean check = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	      assertTrue(check);     	        
	}
	
	@Test
	public void test2() throws Exception {
		 String File_Name_Exepectes = "Solution_test1.txt_tinyEWD.txt_1458731179674_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("tinyEWD.txt", "test1.txt");
	     boolean check1 = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	     assertTrue(check1);     	        
	}
	
	@Test
	public void test3() throws Exception {
		 String File_Name_Exepectes = "Solution_test2.txt_mediumEWG.txt_1458731534833_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("mediumEWG.txt", "test2.txt");
	     boolean check2 = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	     assertTrue(check2);     	        
	}
	
	
	
	
	


 	@Test //testing the first fun
 	public void testDist1() {
 		In in = new In("tinyEWD.txt");
 	 	Graph G = new Graph(in);
 	 	Graph_algo T = new Graph_algo(G);
 		assertEquals(0.35, T.dist(4, 5), 0.01);
 		assertEquals(0.37, T.dist(4, 7), 0.02);
 		assertEquals(0.97, T.dist(2, 4), 0.01);
 		assertEquals(0.6, T.dist(0, 7), 0.01);
 		assertEquals(0.67, T.dist(4, 1), 0.01);
 		assertEquals(1.13, T.dist(6, 3), 0.01);
 	}
 	
 	@Test //testing the first fun
 	public void testDist2() {
 		In in = new In("mediumEWD.txt");
 	 	Graph G1 = new Graph(in);
 	 	Graph_algo T1 = new Graph_algo(G1);
 		assertEquals(0.54, T1.dist(4, 132), 0.04);
 		assertEquals(0.32, T1.dist(4, 7), 0.04);
 		assertEquals(0.26, T1.dist(51, 11), 0.01);
 		assertEquals(0.81, T1.dist(67, 100), 0.01);
 		assertEquals(0.85, T1.dist(85, 249), 0.01);
 		assertEquals(0.0, T1.dist(99, 99), 0.01);
 	}

}
