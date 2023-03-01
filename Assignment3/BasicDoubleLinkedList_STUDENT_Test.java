import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicDoubleLinkedList_STUDENT_Test {

	BasicDoubleLinkedList<Double> list;
	
	@BeforeEach
	void setUp() throws Exception {
		list = new BasicDoubleLinkedList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		list = null;
	}

	@Test
	void testBasicDoubleLinkedList() {
		assertEquals(list.head,null);
		assertEquals(list.tail,null);
		assertEquals(list.size,0);
	}

	@Test
	void testGetSize() {
		assertEquals(list.size,list.getSize());
		list.addToFront((Double)2.0);
		assertEquals(list.size,list.getSize());
		list.addToEnd((Double)4.0);
		assertEquals(list.size,list.getSize());
	}

	@Test
	void testAddToEnd() {
		list.addToFront((Double)2.0);
		list.addToEnd((Double)4.0);
		assertEquals(list.retrieveLastElement(),4.0);
	}

	@Test
	void testAddToFront() {
		list.addToFront((Double)2.0);
		list.addToEnd((Double)4.0);
		assertEquals(list.retrieveFirstElement(),2.0);
	}

	@Test
	void testGetFirst() {
		list.addToFront((Double)2.0);
		list.addToEnd((Double)4.0);
		assertEquals(list.getFirst(),2.0);
	}

	@Test
	void testGetLast() {
		list.addToFront((Double)2.0);
		list.addToEnd((Double)4.0);
		assertEquals(list.getLast(),4.0);
	}

	@Test
	void testIterator() {
		list.addToEnd((Double)5.0);
		java.util.ListIterator<Double> itr = list.iterator();
		itr.next();
	}

	@Test
	void testRemove() {
		list.addToEnd((Double)2.0);
		list.addToEnd((Double)4.0);
		list.addToEnd((Double)6.0);
		list.addToEnd((Double)8.0);
		list.remove((Double)6.0, new DoubleComparator());
		assertTrue(list.toArrayList().toString().equals("[2.0, 4.0, 8.0]"));
	}

	@Test
	void testRetrieveFirstElement() {
		list.addToEnd((Double)2.0);
		list.addToEnd((Double)4.0);
		list.addToEnd((Double)6.0);
		list.addToEnd((Double)8.0);
		list.retrieveFirstElement();
		assertTrue(list.toArrayList().toString().equals("[4.0, 6.0, 8.0]"));
	}

	@Test
	void testRetrieveLastElement() {
		list.addToEnd((Double)2.0);
		list.addToEnd((Double)4.0);
		list.addToEnd((Double)6.0);
		list.addToEnd((Double)8.0);
		list.retrieveLastElement();
		assertTrue(list.toArrayList().toString().equals("[2.0, 4.0, 6.0]"));
	}

	@Test
	void testToArrayList() {
		list.addToEnd((Double)2.0);
		list.addToEnd((Double)4.0);
		list.addToEnd((Double)6.0);
		list.addToEnd((Double)8.0);
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
