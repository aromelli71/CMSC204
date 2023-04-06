import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class STUDENT_MorseCodeTreeTest {

	MorseCodeTree tree;
	@BeforeEach
	void setUp() throws Exception {
		tree = new MorseCodeTree();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMorseCodeTree() {
		assertFalse(tree == null);
	}

	@Test
	void testAddNode() {
		tree.addNode(tree.treeRoot,"----","!");
		assertEquals(tree.fetch("----"),"!");
	}

	@Test
	void testBuildTree() {
		tree.buildTree();
		tree.fetch("--.-");
		tree.fetch("-..-");
		tree.fetch("-.--");
	}

	@Test
	void testDelete() {
		try {
			tree.delete(".");
			fail("Should have caused error");
		} catch(UnsupportedOperationException e) {}
	}

	@Test
	void testFetch() {
		assertEquals(tree.fetch("--.-"),"q");
		assertEquals(tree.fetch("-..-"),"x");
		assertEquals(tree.fetch("-.--"),"y");
	}

	@Test
	void testFetchNode() {
		assertEquals(tree.fetchNode(tree.treeRoot,"--.-"),"q");
		assertEquals(tree.fetchNode(tree.treeRoot,"-..-"),"x");
		assertEquals(tree.fetchNode(tree.treeRoot,"-.--"),"y");
	}

	@Test
	void testGetRoot() {
		assertEquals(tree.getRoot(),tree.treeRoot);
	}

	@Test
	void testInsert() {
		tree.insert("----","!");
		assertEquals(tree.fetch("----"),"!");
	}

	@Test
	void testLNRoutputTraversal() {
		ArrayList<String> list = new ArrayList<>();
		tree.LNRoutputTraversal(tree.treeRoot, list);
		assertEquals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o]",list.toString());
	}

	@Test
	void testSetRoot() {
		tree.setRoot(new TreeNode<String>("data"));
		assertEquals(tree.getRoot().getData(),"data");
	}

	@Test
	void testToArrayList() {
		ArrayList<String> list = new ArrayList<>();
		list = tree.toArrayList();
		assertEquals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o]",list.toString());
	}

	@Test
	void testUpdate() {
		try {
			tree.update();
			fail("Should have caused error");
		} catch(UnsupportedOperationException e) {}
	}

}
