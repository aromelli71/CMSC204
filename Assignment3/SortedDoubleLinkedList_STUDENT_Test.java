import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<Double> list;
	
	@BeforeEach
	void setUp() throws Exception {
		list = new SortedDoubleLinkedList<Double>(new DoubleComparator());
	}

	@AfterEach
	void tearDown() throws Exception {
		list = null;
	}

	@Test
	void testAddToEnd() {
		try {
			list.addToEnd(null);
			fail("Should have caused exception");
		} catch(UnsupportedOperationException e) {
			assertTrue(e.getMessage().equals("Invalid operation for sorted list"));
		}
	}

	@Test
	void testAddToFront() {
		try {
			list.addToFront(null);
			fail("Should have caused exception");
		} catch(UnsupportedOperationException e) {
			assertTrue(e.getMessage().equals("Invalid operation for sorted list"));
		}
	}

	@Test
	void testIterator() {
		list.add((Double)2.0);
		list.add((Double)6.0);
		list.add((Double)4.0);
		list.add((Double)8.0);
		java.util.ListIterator<Double> itr = list.iterator();
		itr.next();
		itr.next();
		assertEquals((Double)6.0,itr.next());
	}

	@Test
	void testRemove() {
		list.add((Double)2.0);
		list.add((Double)6.0);
		list.add((Double)4.0);
		list.add((Double)8.0);
		list.remove((Double)6.0, new DoubleComparator());
		assertTrue(list.toArrayList().toString().equals("[2.0, 4.0, 8.0]"));
	}

	@Test
	void testSortedDoubleLinkedList() {
		DoubleComparator c = new DoubleComparator();
		list = new SortedDoubleLinkedList<Double>(c);
		assertEquals(list.comparator,c);
	}

	@Test
	void testAdd() {
		list.add((Double)2.0);
		list.add((Double)6.0);
		list.add((Double)4.0);
		list.add((Double)8.0);
		assertTrue(list.toArrayList().toString().equals("[2.0, 4.0, 6.0, 8.0]"));
	}

	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
}
