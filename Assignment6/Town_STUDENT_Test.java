import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {

	
	Town t;
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHashCode() {
		t = new Town("A");
		assertEquals(t.hashCode(),new Town("A").hashCode());
	}

	@Test
	void testTownString() {
		t = new Town("A");
		assertTrue(t != null);
	}

	@Test
	void testTownTown() {
		t = new Town(new Town("A"));
		assertEquals(t.getName(),"A");
	}

	@Test
	void testGetName() {
		t = new Town("A");
		assertEquals(t.getName(),"A");
	}

	@Test
	void testCompareTo() {
		t = new Town("A");
		assertFalse(t.compareTo(new Town("B")) == 0);
		assertTrue(t.compareTo(new Town("A")) == 0);
	}

	@Test
	void testToString() {
		t = new Town("A");
		assertEquals("A",t.toString());
	}

	@Test
	void testEqualsObject() {
		t = new Town("A");
		assertFalse(t.equals(new Town("B")));
		assertTrue(t.equals(new Town("A")));
	}

}
