package york.eecs.test;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import york.eecs.source.UndirectedGraph;
import york.eecs.source.UndirectedGraphAlgorithms;
import york.eecs.source.VertexExistsException;


public class StudentTest {
	UndirectedGraphAlgorithms<String> uga;
	//The following 5 methods are for Graph testing

	@Test public void SizeTest(){
		//Size Testing
		UndirectedGraph<String> student = new UndirectedGraph<>();
		try{
			for (int i=0;i<=10;i++){
				student.addVertex(((Integer)i).toString());
				//System.out.println("Added Node:"+i);
			}
			for (int i=0;i<=9;i++){
				student.addEdge(((Integer)i).toString(),((Integer)(i+1)).toString());
			}
		} catch (VertexExistsException e) {
			e.printStackTrace();
		}
		assertEquals(11,student.getSize());
		assertFalse(student.isEmpty());
	}

	@Test public void GetTest(){
		//Get Testing
		int i;
		UndirectedGraph<String> student = new UndirectedGraph<>();
		try{
			for (i=0;i<=100;i++){
				student.addVertex(((Integer)i).toString());
				//System.out.println("Added Node:"+i);
			}
			for (i=0;i<=99;i++){
				student.addEdge(((Integer)i).toString(),((Integer)(i+1)).toString());
			}
		} catch (VertexExistsException e) {
			e.printStackTrace();
		}
		Set<String> keys = new HashSet<String>();
		for (i=0;i<=100;i++){
			keys.add(((Integer)i).toString());
			//System.out.println("Added Node:"+i);
		}
		ArrayList arrayKeys = new ArrayList(keys);
		assertEquals(arrayKeys,student.getVertices());
		List<String> expected = new ArrayList<>();
		i = (int)(Math.random()*8)+1;
		expected.add(((Integer)(i-1)).toString());
		expected.add(((Integer)(i+1)).toString());
		assertEquals(student.getAdjacent(((Integer)i).toString()),expected);
	}

	@Test
	public void ExceptionTest() {
		//Adding Exception Testing
		UndirectedGraph<String> student = new UndirectedGraph<>();
		try{
			student.addVertex("1");
			student.addVertex("1");
		}
		catch (VertexExistsException e){
			assertEquals("Vertex Exist",e.getMessage());
		}
	}

	@Test public void LargeTest(){
		//Get Testing
		int i;
		UndirectedGraph<String> student = new UndirectedGraph<>();
		try{
			for (i=0;i<=100000;i++){
				student.addVertex(((Integer)i).toString());
				//System.out.println("Added Node:"+i);
			}
			for (i=0;i<=99999;i++){
				student.addEdge(((Integer)i).toString(),((Integer)(i+1)).toString());
			}
		} catch (VertexExistsException e) {
			e.printStackTrace();
		}
		Set<String> keys = new HashSet<String>();
		for (i=0;i<=100000;i++){
			keys.add(((Integer)i).toString());
			//System.out.println("Added Node:"+i);
		}
		ArrayList arrayKeys = new ArrayList(keys);
		assertEquals(arrayKeys,student.getVertices());
		List<String> expected = new ArrayList<>();
		i = (int)(Math.random()*99998)+1;
		expected.add(((Integer)(i-1)).toString());
		expected.add(((Integer)(i+1)).toString());
		assertEquals(student.getAdjacent(((Integer)i).toString()),expected);
	}

	@Test
	public void toStringTest() {
		int i;
		UndirectedGraph<Integer> student = new UndirectedGraph<Integer>();
		Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
		try {
			Set<Integer> temp;
			temp = new HashSet<>();
			temp.add(1);
			graph.put(0,temp);
			temp = new HashSet<>();
			temp.add(99);
			graph.put(100,temp);
			for (i=1;i<100;i++){
				temp = new TreeSet<>();
				temp.add(i-1);
				temp.add(i+1);
				graph.put(i,temp);
			}
			for (i=0;i<=100;i++){
				student.addVertex(((Integer)i));
				//System.out.println("Added Node:"+i);

			}
			for (i=0;i<=99;i++){
				student.addEdge((Integer)i,(Integer)(i+1));
			}
		} catch (VertexExistsException e) {
			e.printStackTrace();
		}
		String expected = "Graph:\n";
		Iterator Iterator = graph.entrySet().iterator();
		while(Iterator.hasNext()){
			Map.Entry temp = (Map.Entry)Iterator.next();
			expected+="Vertex: "+temp.getKey()+" & Adjacent Vertices: "+temp.getValue()+'\n';
		}
		String toSt = student.toString();
		assertEquals(expected,toSt);
	}


	//The following 5 methods are for Algorithm testing
	@Test public void PathTest1(){
		uga = new UndirectedGraphAlgorithms<>();
		UndirectedGraph<String> student = new UndirectedGraph<>();
		try {

			student.addVertex("V");
			student.addVertex("W");
			student.addVertex("X");
			student.addVertex("Y");
			student.addVertex("Z");

			student.addEdge("V","W");
			student.addEdge("W","X");
			student.addEdge("X","Y");
			student.addEdge("Z","Y");

		} catch (VertexExistsException e) {
			e.printStackTrace();
		}

		List<String> result = (ArrayList<String>) uga.findBFSpath(student, "V", "Z");
		List<String> expected = new ArrayList<>();
		expected.add("V");
		expected.add("W");
		expected.add("X");
		expected.add("Y");
		expected.add("Z");
		assertEquals(expected, result);
	}

	@Test public void PathTest2(){
		uga = new UndirectedGraphAlgorithms<>();
		UndirectedGraph<String> student = new UndirectedGraph<>();
		try {

			student.addVertex("V");
			student.addVertex("Z");

		} catch (VertexExistsException e) {
			e.printStackTrace();
		}

		List<String> result = (ArrayList<String>) uga.findBFSpath(student, "V", "Z");
		assertEquals(null, result);
	}

	@Test public void PathTest3(){
		uga = new UndirectedGraphAlgorithms<>();
		UndirectedGraph<String> student = new UndirectedGraph<>();
		List<String> result = (ArrayList<String>) uga.findBFSpath(student, "V", "Z");
		assertEquals(null, result);
	}

	//Testing with route picking with all loops
	@Test public void PathTest4(){
		int i;
		uga = new UndirectedGraphAlgorithms<>();
		UndirectedGraph<String> student = new UndirectedGraph<>();
		try{
			for (i=0;i<=5;i++){
				student.addVertex(((Integer)i).toString());
				//System.out.println("Added Node:"+i);
			}
			student.addEdge("0","1");
			student.addEdge("1","2");
			student.addEdge("2","5");
			student.addEdge("0","3");
			student.addEdge("3","4");
			student.addEdge("4","5");
			student.addEdge("1","3");
			student.addEdge("2","4");
			/*
			for (i=0;i<=9;i++){
				student.addEdge(((Integer)i).toString(),((Integer)(i+1)).toString());
				System.out.println("Added Edge:"+i+" to "+(i+1));
			}
			*/
		} catch (VertexExistsException e) {
			e.printStackTrace();
		}

		//System.out.println(student.toString());
		List<String> result = (ArrayList<String>) uga.findBFSpath(student, "0", "5");
		List<String> expected = new ArrayList<>();
		expected.add("0");
		expected.add("1");
		expected.add("2");
		expected.add("5");
		assertEquals(expected, result);
	}

	//Testing for case that has looping and no route to destination
	@Test public void PathTest5(){
		uga = new UndirectedGraphAlgorithms<>();
		UndirectedGraph<String> student = new UndirectedGraph<>();
		try {

			student.addVertex("V");
			student.addVertex("W");
			student.addVertex("X");
			student.addVertex("Y");
			student.addVertex("Z");

			student.addEdge("V","W");
			student.addEdge("W","X");
			student.addEdge("X","Y");
			student.addEdge("Y","W");

		} catch (VertexExistsException e) {
			e.printStackTrace();
		}

		List<String> result = (ArrayList<String>) uga.findBFSpath(student, "V", "Z");
		assertEquals(null, result);
	}
}
