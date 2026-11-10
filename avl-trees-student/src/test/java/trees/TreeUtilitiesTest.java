/*
 * Copyright 2026 Marc Liberatore.
 */

package trees;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


// @Timeout(10)
public class TreeUtilitiesTest {


    @BeforeEach
    public void setup() {
    }

    @Test
    public void testInOrderEmpty() throws Exception {
        assertEquals(new ArrayList<>(), TreeUtilities.inOrder(null));
    }

    @Test
    public void testInOrderOne() throws Exception {
        Node<Integer> n = new Node<>(1);
        assertEquals(Arrays.asList(1), TreeUtilities.inOrder(n));
    }

    @Test
    public void testInOrderTwoLeft() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.left = m;
        assertEquals(Arrays.asList(2, 1), TreeUtilities.inOrder(n));
    }

    @Test
    public void testInOrderTwoRight() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.right = m;
        assertEquals(Arrays.asList(1, 2), TreeUtilities.inOrder(n));
    }

    @Test
    public void testInOrderAll() throws Exception {
        testInOrderEmpty();
        testInOrderOne();
        testInOrderTwoLeft();
        testInOrderTwoRight();
    }

    @Test
    public void testHeightEmpty() throws Exception {
        assertEquals(-1, TreeUtilities.height(null));
    }

    @Test
    public void testHeightOne() throws Exception {
        Node<Integer> n = new Node<>(1);
        assertEquals(0, TreeUtilities.height(n));
    }

    @Test
    public void testHeightTwoLeft() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.left = m;
        assertEquals(1, TreeUtilities.height(n));
    }

    @Test
    public void testHeightTwoRight() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.right = m;
        assertEquals(1, TreeUtilities.height(n));
    }

    @Test
    public void testHeightAll() throws Exception {
        testHeightEmpty();
        testHeightOne();
        testHeightTwoLeft();
        testHeightTwoRight();
    }

    @Test
    public void testisBSTEmpty() throws Exception {
        assertTrue(TreeUtilities.isBST(null));
    }

    @Test
    public void testisBSTOne() throws Exception {
        assertTrue(TreeUtilities.isBST(new Node<Integer>(0)));
    }

    @Test
    public void testIsBSTTwoLeft() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        m.left = n;
        assertTrue(TreeUtilities.isBST(m));
    }

    @Test
    public void testIsNotBSTTwoLeft() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.left = m;
        assertFalse(TreeUtilities.isBST(n));
    }

    @Test
    public void testIsBSTTwoRight() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.right = m;
        assertTrue(TreeUtilities.isBST(n));
    }

    @Test
    public void testIsNotBSTTwoRight() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        m.right = n;
        assertFalse(TreeUtilities.isBST(m));
    }

    @Test
    public void testIsBSTWarning() throws Exception {        
        fail("This test is intended to fail. Read the comment and then comment out this test.");
        // We are not providing you with exhaustive tests for your isBST() code.
        // Nor do you *have to* test it. But if you run into trouble with your AVL tree
        // implementation, it can be helpful to be able to automatically check the tree
        // after a rotation to see if it's still a binary tree.
    }

    @Test
    public void testisAVLEmpty() throws Exception {
        assertTrue(TreeUtilities.isAVLTree(null));
    }

    @Test
    public void testisAVLTreeOne() throws Exception {
        assertTrue(TreeUtilities.isAVLTree(new Node<Integer>(0)));
    }

    @Test
    public void testIsAVLTreeTwoLeft() throws Exception {
        Node<Integer> m = new Node<>(2);
        Node<Integer> n = new Node<>(1, m);
        m.left = n;
        assertTrue(TreeUtilities.isAVLTree(m));
    }

    @Test
    public void testIsNotAVLTreeThreeLeft() throws Exception {
        Node<Integer> o = new Node<>(3);
        Node<Integer> m = new Node<>(2, o);
        Node<Integer> n = new Node<>(1, m);
        o.left = m;
        m.left = n;
        assertFalse(TreeUtilities.isAVLTree(o));
    }

    @Test
    public void testIsAVLTreeThreeBalanced() throws Exception {
        Node<Integer> m = new Node<>(2);
        Node<Integer> n = new Node<>(1, m);
        Node<Integer> o = new Node<>(3, m);
        m.left = n;
        m.right = o;
        assertTrue(TreeUtilities.isAVLTree(m));
    }

    @Test
    public void testIsNotAVLTreeBrokenParentNull() throws Exception {
        Node<Integer> m = new Node<>(2);
        Node<Integer> n = new Node<>(1); // parent not set — should be m
        m.left = n;
        assertFalse(TreeUtilities.isAVLTree(m));
    }

    @Test
    public void testIsNotAVLTreeWrongParent() throws Exception {
        Node<Integer> m = new Node<>(2);
        Node<Integer> n = new Node<>(1);
        Node<Integer> decoy = new Node<>(99);
        n.parent = decoy; // stale/wrong parent pointer, as a buggy rotation might leave
        m.left = n;
        assertFalse(TreeUtilities.isAVLTree(m));
    }

    @Test
    public void testIsAVLTreeWarning() throws Exception {        
        fail("This test is intended to fail. Read the comment and then comment out this test.");
        // We are not providing you with exhaustive tests for your isAVLTree code.
        // Nor do you *have to* test it. But if you run into trouble with your AVL tree
        // implementation, it can be helpful to be able to automatically check the tree
        // after a rotation to see if it's still an AVL tree.
    }
}
