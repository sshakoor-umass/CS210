/*
 * Copyright 2026 Marc Liberatore.
 */

package trees;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


// @Timeout(10)
public class BinarySearchTreeTest {


    @BeforeEach
    public void setup() {
    }

    // -------------------------------------------------------------------------
    // Group 1: add and size
    // -------------------------------------------------------------------------

    @Test
    public void testSizeEmpty() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        assertEquals(0, b.size());
    }

    @Test
    public void testSizeOne() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        assertEquals(1, b.size());
    }

    @Test
    public void testSizeTwoLeft() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        b.add(3);
        assertEquals(2, b.size());
    }

    @Test
    public void testSizeTwoRight() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        b.add(7);
        assertEquals(2, b.size());
    }

    @Test
    public void testSizeMany() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(4); b.add(2); b.add(6); b.add(1); b.add(3); b.add(5); b.add(7);
        assertEquals(7, b.size());
    }

    @Test
    public void testSizeDuplicate() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        b.add(5);
        assertEquals(1, b.size());
    }

    @Test
    public void testAddSizeAll() throws Exception {
        testSizeEmpty();
        testSizeOne();
        testSizeTwoLeft();
        testSizeTwoRight();
        testSizeMany();
        testSizeDuplicate();
    }

    // -------------------------------------------------------------------------
    // Group 2: contains
    // -------------------------------------------------------------------------

    @Test
    public void testContainsEmpty() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        assertFalse(b.contains(5));
    }

    @Test
    public void testContainsOne() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        assertTrue(b.contains(5));
        assertFalse(b.contains(3));
    }

    @Test
    public void testContainsTwoLeft() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        b.add(3);
        assertTrue(b.contains(5));
        assertTrue(b.contains(3));
        assertFalse(b.contains(7));
    }

    @Test
    public void testContainsTwoRight() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        b.add(7);
        assertTrue(b.contains(5));
        assertTrue(b.contains(7));
        assertFalse(b.contains(3));
    }

    @Test
    public void testContainsMany() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(4); b.add(2); b.add(6); b.add(1); b.add(3); b.add(5); b.add(7);
        for (int i = 1; i <= 7; i++) {
            assertTrue(b.contains(i));
        }
        assertFalse(b.contains(8));
        assertFalse(b.contains(0));
    }

    @Test
    public void testContainsAll() throws Exception {
        testContainsEmpty();
        testContainsOne();
        testContainsTwoLeft();
        testContainsTwoRight();
        testContainsMany();
    }

    // -------------------------------------------------------------------------
    // Group 3: getLeastElement
    // -------------------------------------------------------------------------

    @Test
    public void testGetLeastEmpty() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        assertNull(b.getLeastElement());
    }

    @Test
    public void testGetLeastOne() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        assertEquals(Integer.valueOf(5), b.getLeastElement());
    }

    @Test
    public void testGetLeastTwoLeft() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        b.add(3);
        assertEquals(Integer.valueOf(3), b.getLeastElement());
    }

    @Test
    public void testGetLeastTwoRight() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        b.add(7);
        assertEquals(Integer.valueOf(5), b.getLeastElement());
    }

    @Test
    public void testGetLeastMany() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(4); b.add(2); b.add(6); b.add(1); b.add(3); b.add(5); b.add(7);
        assertEquals(Integer.valueOf(1), b.getLeastElement());
    }

    @Test
    public void testGetLeastAfterRemoveLeast() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(4); b.add(2); b.add(6); b.add(1);
        b.remove(1);
        assertEquals(Integer.valueOf(2), b.getLeastElement());
    }

    @Test
    public void testGetLeastAll() throws Exception {
        testGetLeastEmpty();
        testGetLeastOne();
        testGetLeastTwoLeft();
        testGetLeastTwoRight();
        testGetLeastMany();
        testGetLeastAfterRemoveLeast();
    }

    // -------------------------------------------------------------------------
    // Group 4: remove
    // -------------------------------------------------------------------------

    @Test
    public void testRemoveEmpty() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.remove(5); // should not throw
        assertEquals(0, b.size());
    }

    @Test
    public void testRemoveAbsent() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        b.remove(3);
        assertEquals(1, b.size());
        assertTrue(b.contains(5));
    }

    @Test
    public void testRemoveOnlyElement() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5);
        b.remove(5);
        assertEquals(0, b.size());
        assertFalse(b.contains(5));
    }

    @Test
    public void testRemoveLeafLeft() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5); b.add(3);
        b.remove(3);
        assertEquals(1, b.size());
        assertFalse(b.contains(3));
        assertTrue(b.contains(5));
    }

    @Test
    public void testRemoveLeafRight() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5); b.add(7);
        b.remove(7);
        assertEquals(1, b.size());
        assertFalse(b.contains(7));
        assertTrue(b.contains(5));
    }

    @Test
    public void testRemoveRootOneChildLeft() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5); b.add(3);
        b.remove(5);
        assertEquals(1, b.size());
        assertFalse(b.contains(5));
        assertTrue(b.contains(3));
        assertEquals(Integer.valueOf(3), b.root.data);
    }

    @Test
    public void testRemoveRootOneChildRight() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5); b.add(7);
        b.remove(5);
        assertEquals(1, b.size());
        assertFalse(b.contains(5));
        assertTrue(b.contains(7));
        assertEquals(Integer.valueOf(7), b.root.data);
    }

    @Test
    public void testRemoveRootTwoChildren() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(5); b.add(3); b.add(7);
        b.remove(5);
        assertEquals(2, b.size());
        assertFalse(b.contains(5));
        assertTrue(b.contains(3));
        assertTrue(b.contains(7));
        assertTrue(TreeUtilities.isBST(b.root));
    }

    @Test
    public void testRemoveInternalTwoChildren() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(4); b.add(2); b.add(6); b.add(1); b.add(3); b.add(5); b.add(7);
        b.remove(2);
        assertEquals(6, b.size());
        assertFalse(b.contains(2));
        for (int i : Arrays.asList(1, 3, 4, 5, 6, 7)) {
            assertTrue(b.contains(i));
        }
        assertTrue(TreeUtilities.isBST(b.root));
    }

    @Test
    public void testRemoveSequential() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        List<Integer> elems = Arrays.asList(3, 1, 4, 5, 2);
        for (int e : elems) {
            b.add(e);
        }
        List<Integer> toRemove = new ArrayList<>(elems);
        int expectedSize = elems.size();
        for (int e : toRemove) {
            b.remove(e);
            expectedSize--;
            assertEquals(expectedSize, b.size());
            assertFalse(b.contains(e));
            assertTrue(TreeUtilities.isBST(b.root));
        }
        assertEquals(0, b.size());
    }

    @Test
    public void testRemoveGradedAll() throws Exception {
        testRemoveEmpty();
        testRemoveAbsent();
        testRemoveOnlyElement();
        testRemoveLeafLeft();
        testRemoveLeafRight();
        testRemoveRootOneChildLeft();
        testRemoveRootOneChildRight();
        testRemoveRootTwoChildren();
        testRemoveInternalTwoChildren();
        testRemoveSequential();
    }

    // -------------------------------------------------------------------------
    // Group 5: BST structural validity
    // -------------------------------------------------------------------------

    @Test
    public void testBSTAfterAdds() throws Exception {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            l.add(i);
        }
        Collections.shuffle(l, new Random(42));
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        for (int e : l) {
            b.add(e);
        }
        assertEquals(15, b.size());
        assertTrue(TreeUtilities.isBST(b.root));
    }

    @Test
    public void testBSTAfterRemoves() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(4); b.add(2); b.add(6); b.add(1); b.add(3); b.add(5); b.add(7);
        b.remove(4); // remove root (two-child case)
        b.remove(1); // remove leaf
        b.remove(6); // remove internal (one-child case)
        assertEquals(4, b.size());
        assertTrue(TreeUtilities.isBST(b.root));
    }

    @Test
    public void testBSTValidityAll() throws Exception {
        testBSTAfterAdds();
        testBSTAfterRemoves();
    }

}
