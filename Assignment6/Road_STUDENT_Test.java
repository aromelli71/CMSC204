import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {

	Road r, s, t;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		r = null;
	}

	@Test
	void testRoadTownTownIntString() {
		r = new Road(new Town("A"),new Town("B"),10,"Test");
		assertEquals(r.getSource(),new Town("A"));
		assertEquals(r.getDestination(),new Town("B"));
		assertEquals(r.getWeight(),10);
		assertEquals(r.getName(),"Test");
	}

	@Test
	void testRoadTownTownString() {
		r = new Road(new Town("1"),new Town("2"),"Testing");
		assertEquals(r.getSource(),new Town("1"));
		assertEquals(r.getDestination(),new Town("2"));
		assertEquals(r.getName(),"Testing");
	}

	@Test
	void testContains() {
		r = new Road(new Town("A"),new Town("B"),10,"Test");
		assertTrue(r.contains(new Town("A")));
		assertFalse(r.contains(new Town("C")));
	}

	@Test
	void testToString() {
		r = new Road(new Town("A"),new Town("B"),10,"Test");
		assertEquals(r.toString(),"Test");
	}

	@Test
	void testGetName() {
		r = new Road(new Town("A"),new Town("B"),10,"Test2");
		assertEquals(r.getName(),"Test2");
	}

	@Test
	void testGetDestination() {
		r = new Road(new Town("A"),new Town("B"),10,"Test");
		assertEquals(r.getDestination(),new Town("B"));

	}

	@Test
	void testGetSource() {
		r = new Road(new Town("A"),new Town("B"),10,"Test");
		assertEquals(r.getSource(),new Town("A"));
	}

	@Test
	void testCompareTo() {
		r = new Road(new Town("A"),new Town("B"),10,"Road 1");
		s = new Road(new Town("A"),new Town("B"),10,"Road 2");
		t = new Road(new Town("A"),new Town("B"),10,"Road 1");
		assertTrue(r.compareTo(s) != 0);
		assertEquals(0,r.compareTo(t));
	}

	@Test
	void testGetWeight() {
		r = new Road(new Town("A"),new Town("B"),10,"Test");
		assertEquals(r.getWeight(),10);

	}

	@Test
	void testEqualsObject() {
		r = new Road(new Town("A"),new Town("B"),10,"Road 1");
		s = new Road(new Town("B"),new Town("A"),10,"Road 1");
		t = new Road(new Town("A"),new Town("C"),10,"Road 2");
		assertFalse(r.equals(t));
		assertTrue(r.equals(s));
	}

}
