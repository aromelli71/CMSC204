import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test {

	TownGraphManager tgm;
	
	@BeforeEach
	void setUp() throws Exception {
		tgm = new TownGraphManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		tgm = null;	
	}

	@Test
	void testAddRoad() {
		tgm.addTown("A");
		tgm.addTown("B");
		tgm.addRoad("A","B", 10, "Road");
		assertEquals(tgm.allRoads().size(),1);
	}

	@Test
	void testGetRoad() {
		tgm.addTown("A");
		tgm.addTown("B");
		Road r = new Road(new Town("A"),new Town("B"), 10, "Road");
		tgm.addRoad("A","B", 10, "Road");
		assertEquals(r.getName(),tgm.getRoad("A", "B"));
	}

	@Test
	void testAddTown() {
		tgm.addTown("Germantown");
		assertEquals(tgm.allTowns().size(),1);
	}

	@Test
	void testGetTown() {
		Town t = new Town("Rockville");
		tgm.addTown("Rockville");
		assertEquals(tgm.getTown("Rockville"),t);
	}

	@Test
	void testContainsTown() {
		tgm.addTown("Rockville");
		tgm.addTown("Germantown");
		tgm.addTown("Silver Spring");
		assertTrue(tgm.containsTown("Rockville"));
		assertFalse(tgm.containsTown("Paris"));
	}

	@Test
	void testContainsRoadConnection() {
		tgm.addTown("A");
		tgm.addTown("B");
		tgm.addTown("C");
		tgm.addTown("D");
		tgm.addTown("E");
		tgm.addTown("F");
		tgm.addRoad("A","B", 10, "A-B");
		tgm.addRoad("C","D", 10, "C-D");
		tgm.addRoad("E","F", 10, "E-F");
		assertTrue(tgm.containsRoadConnection("A", "B"));
		assertFalse(tgm.containsRoadConnection("G","H"));
	}

	@Test
	void testAllRoads() {
		tgm.addTown("A");
		tgm.addTown("B");
		tgm.addTown("C");
		tgm.addTown("D");
		tgm.addTown("E");
		tgm.addTown("F");
		tgm.addRoad("A","B", 10, "A-B");
		tgm.addRoad("C","D", 10, "C-D");
		tgm.addRoad("E","F", 10, "E-F");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("A-B");
		expected.add("C-D");
		expected.add("E-F");
		assertEquals(tgm.allRoads(),expected);
	}

	@Test
	void testDeleteRoadConnection() {
		tgm.addTown("A");
		tgm.addTown("B");
		tgm.addRoad("A", "B", 10, "Road");
		tgm.addTown("C");
		tgm.addTown("D");
		tgm.addRoad("C", "D", 10, "Road 2");
		tgm.deleteRoadConnection("A", "B", "Road");
		assertEquals(tgm.allRoads().size(),1);
	}

	@Test
	void testDeleteTown() {
		tgm.addTown("A");
		tgm.addTown("B");
		tgm.deleteTown("A");
		assertEquals(tgm.allTowns().size(),1);
	}

	@Test
	void testAllTowns() {
		tgm.addTown("A");
		tgm.addTown("B");
		tgm.addTown("C");
		tgm.addTown("D");
		tgm.addTown("E");
		tgm.addTown("F");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("A");
		expected.add("B");
		expected.add("C");
		expected.add("D");
		expected.add("E");
		expected.add("F");
		assertEquals(expected,tgm.allTowns());
	}

	@Test
	void testGetPath() {
		tgm.addTown("A");
		tgm.addTown("B");
		tgm.addTown("C");
		tgm.addRoad("A","B", 5, "A-B");
		tgm.addRoad("B","C", 10, "B-C");
		tgm.addRoad("A","C", 20, "A-C");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("A via A-B to B 5 mi");
		expected.add("B via B-C to C 10 mi");
		assertEquals(tgm.getPath("A", "C"),expected);
	}

	@Test
	void testPopulateTownGraph() throws IOException {
		// FUNCTION USES THE "MD TOWNS" PROVIDED FILE (placed in Project folder)
		tgm.populateTownGraph(new java.io.File("MD Towns.txt"));
		ArrayList<String> expected = new ArrayList<>();
		expected.add("Bethesda");
		expected.add("Boyds");
		expected.add("Clarksburg");
		expected.add("Darnestown");
		expected.add("Frederick");
		expected.add("Gaithersburg");
		expected.add("Germantown");
		expected.add("Olney");
		expected.add("Poolesville");
		expected.add("Potomac");
		expected.add("Rockville");
		assertEquals(tgm.allTowns(),expected);
	}

}
