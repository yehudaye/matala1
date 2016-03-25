package M1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.Test;


public class Graph_Tests {
	//testing the third fun
	@Test
	public void test1() throws Exception {
		 String File_Name_Exepectes = "Solution_test3.txt_mediumEWD.txt_1458849524511_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("mediumEWD.txt", "test3.txt");
	     boolean check = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	      assertTrue(check);     	        
	}
	//testing the third fun
	@Test
	public void test2() throws Exception {
		 String File_Name_Exepectes = "Solution_test1.txt_tinyEWD.txt_1458849692099_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("tinyEWD.txt", "test1.txt");
	     boolean check1 = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	     assertTrue(check1);     	        
	}
	//testing the third fun
	@Test
	public void test3() throws Exception {
		 String File_Name_Exepectes = "Solution_test2.txt_mediumEWG.txt_1458849815806_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("mediumEWG.txt", "test2.txt");
	     boolean check2 = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	     assertTrue(check2);     	        
	}
	//testing the third fun
	@Test
	public void test4() throws Exception {
		 String File_Name_Exepectes = "Solution_test1.txt_tinyEWG.txt_1458850001630_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("tinyEWG.txt", "test1.txt");
	     boolean check4 = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	     assertTrue(check4);     	        
	}
	//testing the third fun
	@Test
	public void test5() throws Exception {
		 String File_Name_Exepectes = "Solution_test4.txt_tinyEWG.txt_1458893879859_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("tinyEWG.txt", "test4.txt");
	     boolean check5 = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	     assertTrue(check5);     	        
	}
	//testing the third fun
	@Test
	public void test6() throws Exception {
		 String File_Name_Exepectes = "Solution_test1.txt_mediumEWG.txt_1458894234945_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("mediumEWG.txt", "test1.txt");
	     boolean check6 = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	     assertTrue(check6);     	        
	}
	//testing the third fun
	@Test
	public void test7() throws Exception {
		 String File_Name_Exepectes = "Solution_test5.txt_mediumEWG.txt_1458894818943_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("mediumEWG.txt", "test5.txt");
	     boolean check7 = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	     assertTrue(check7);     	        
	}
	//testing the third fun
	@Test
	public void test8() throws Exception {
		 String File_Name_Exepectes = "Solution_test6.txt_mediumEWG.txt_1458895215095_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("mediumEWG.txt", "test6.txt");
	     boolean check8 = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	     assertTrue(check8);     	        
	}
	//testing the third fun
	@Test
	public void test9() throws Exception {
		 String File_Name_Exepectes = "Solution_test1.txt_mediumEWD.txt_1458895948884_.txt";
	     String File_Name_Actual =  Main_Ex1.main2("mediumEWD.txt", "test1.txt");
	     boolean check9 = Main_Ex1.FILES_ARE_SAME(File_Name_Actual,File_Name_Exepectes);
	     assertTrue(check9);     	        
	}
	
	
	private static final Double Inf = Double.POSITIVE_INFINITY;
	


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
 	
 	
	@Test //testing the first fun
 	public void testDist3() {
 		In in = new In("mediumEWG.txt");
 	 	Graph G2 = new Graph(in);
 	 	Graph_algo T2 = new Graph_algo(G2);
 		assertEquals(Inf, T2.dist(111, 22),0.01);
 		assertEquals(0.10309, T2.dist(220,247), 0.01);
 		assertEquals(0.05719, T2.dist(0, 15), 0.01);
 		assertEquals(0.05813, T2.dist(210, 212), 0.01);
 		assertEquals(0.10995, T2.dist(149,225), 0.01);
 		assertEquals(Inf, T2.dist(36,71),0.01);
 	}

}
