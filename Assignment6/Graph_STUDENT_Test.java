import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class Graph_STUDENT_Test {

	Graph g;
	
	@BeforeEach
	void setUp() throws Exception {
		g = new Graph();
	}

	@AfterEach
	void tearDown() throws Exception {
		g = null;
	}

	@Test
	void testGetEdge() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		Road r = new Road(new Town("A"), new Town("B"), 10, "Road");
		g.addEdge(new Town("A"), new Town("B"), 10, "Road");
		assertEquals(r,g.getEdge(new Town("A"), new Town("B")));
	}

	@Test
	void testAddEdge() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		Road r = new Road(new Town("A"), new Town("B"), 10, "Road");
		g.addEdge(new Town("A"), new Town("B"), 10, "Road");
		assertEquals(r,g.getEdge(new Town("A"), new Town("B")));
	}

	@Test
	void testAddVertex() {
		Town t = new Town("Germantown");
		g.addVertex(new Town("Germantown"));
		assertEquals(1,g.vertexSet().size());
	}

	@Test
	void testContainsEdge() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		g.addEdge(new Town("A"), new Town("B"), 10, "Road");
		assertTrue(g.containsEdge(new Town("A"), new Town("B")));
	}

	@Test
	void testContainsVertex() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		assertTrue(g.containsVertex(new Town("A")) && g.containsVertex(new Town("B")));
	}

	@Test
	void testEdgeSet() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		g.addVertex(new Town("C"));
		g.addEdge(new Town("A"), new Town("B"), 10, "Road 1");
		g.addEdge(new Town("B"), new Town("C"), 10, "Road 2");
		Set<Road> expected = new HashSet<Road>();
		expected.add(new Road(new Town("B"), new Town("C"), 10, "Road 2"));
		expected.add(new Road(new Town("A"), new Town("B"), 10, "Road 1"));
		assertEquals(expected.toString(),g.edgeSet().toString());
	}

	@Test
	void testEdgesOf() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		g.addVertex(new Town("C"));
		g.addEdge(new Town("A"), new Town("B"), 10, "Road 1");
		g.addEdge(new Town("B"), new Town("C"), 10, "Road 2");
		Set<Road> expected = new HashSet<Road>();
		expected.add(new Road(new Town("A"), new Town("B"), 10, "Road 1"));
		expected.add(new Road(new Town("B"), new Town("C"), 10, "Road 2"));
		assertEquals(expected.toString(),g.edgesOf(new Town("B")).toString());
	}

	@Test
	void testRemoveEdge() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		g.addVertex(new Town("C"));
		g.addEdge(new Town("A"), new Town("B"), 10, "Road 1");
		g.addEdge(new Town("B"), new Town("C"), 10, "Road 2");
		g.removeEdge(new Town("A"), new Town("B"), 10, "Road 1");
		assertEquals(1,g.edgeSet().size());
	}

	@Test
	void testRemoveVertex() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		g.addVertex(new Town("C"));
		g.removeVertex(new Town("A"));
		assertEquals(2,g.vertexSet().size());
	}

	@Test
	void testVertexSet() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		g.addVertex(new Town("C"));
		Set<Town> expected = new HashSet<Town>();
		expected.add(new Town("A"));
		expected.add(new Town("B"));
		expected.add(new Town("C"));
		assertEquals(expected.toString(),g.vertexSet().toString());
	}

	@Test
	void testShortestPath() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		g.addVertex(new Town("C"));
		g.addEdge(new Town("A"), new Town("B"), 5, "Road 1");
		g.addEdge(new Town("B"), new Town("C"), 10, "Road 2");
		g.addEdge(new Town("A"), new Town("C"), 20, "Road 3");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("A via Road 1 to B 5 mi");
		expected.add("B via Road 2 to C 10 mi");
		assertEquals(expected,g.shortestPath(new Town("A"), new Town("C")));
	}

	@Test
	void testDijkstraShortestPath() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		g.addVertex(new Town("C"));
		g.addEdge(new Town("A"), new Town("B"), 5, "Road 1");
		g.addEdge(new Town("B"), new Town("C"), 10, "Road 2");
		g.addEdge(new Town("A"), new Town("C"), 20, "Road 3");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("A via Road 1 to B 5 mi");
		expected.add("B via Road 2 to C 10 mi");
		assertEquals(expected,g.shortestPath(new Town("A"), new Town("C")));
	}

	@Test
	void testGetAdjacentTowns() {
		g.addVertex(new Town("A"));
		g.addVertex(new Town("B"));
		g.addVertex(new Town("C"));
		g.addEdge(new Town("A"), new Town("B"), 10, "Road 1");
		g.addEdge(new Town("B"), new Town("C"), 10, "Road 2");
		HashMap<String,Road> expected = new HashMap<String,Road>();
		expected.put("C", new Road(new Town("B"), new Town("C"), 10, "Road 2"));
		expected.put("A", new Road(new Town("A"), new Town("B"), 10, "Road 1"));
		assertEquals(expected.toString(),g.getAdjacentTowns(new Town("B")).toString());
	}

}
