/*
 * Copyright 2026 Marc Liberatore.
 */

package trees;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


 // @Timeout(10)
 public class AVLTreeTest {
 

    @BeforeEach
    public void setup() {
        AVLTree<Integer> t = new AVLTree<>();
    }

    @Test
    public void testFloorEmptyTree() throws Exception {
        AVLTree<Integer> t = new AVLTree<>();
        assertNull(t.floor(5));
        
    }

    @Test
    public void testFloorSingleElementExactMatch() throws Exception {
        AVLTree<Integer> t = new AVLTree<>();
        t.add(10);
        assertEquals(Integer.valueOf(10), t.floor(10));
    }

    @Test
    public void testFloorSingleElementBelow() throws Exception {
        AVLTree<Integer> t = new AVLTree<>();
        t.add(10);
        // query is smaller than the only element — no floor exists
        assertNull(t.floor(5));
    }

    @Test
    public void testFloorSingleElementAbove() throws Exception {
        AVLTree<Integer> t = new AVLTree<>();
        t.add(10);
        // query is larger than the only element — floor is that element
        assertEquals(Integer.valueOf(10), t.floor(15));
    }

    @Test
    public void testFloorBelowAllElements() throws Exception {
        AVLTree<Integer> t = new AVLTree<>();
        for (int i : new int[]{10, 20, 30, 40, 50}) {
            t.add(i);
        }
        // query is smaller than every element in the tree
        assertNull(t.floor(5));
    }

    @Test
    public void testFloorAboveAllElements() throws Exception {
        AVLTree<Integer> t = new AVLTree<>();
        for (int i : new int[]{10, 20, 30, 40, 50}) {
            t.add(i);
        }
        // query is larger than every element — floor is the max
        assertEquals(Integer.valueOf(50), t.floor(99));
    }

    @Test
    public void testFloorExactMatchInTree() throws Exception {
        AVLTree<Integer> t = new AVLTree<>();
        for (int i : new int[]{10, 20, 30, 40, 50}) {
            t.add(i);
        }
        assertEquals(Integer.valueOf(30), t.floor(30));
    }

    @Test
    public void testFloorBetweenElements() throws Exception {
        AVLTree<Integer> t = new AVLTree<>();
        for (int i : new int[]{10, 20, 30, 40, 50}) {
            t.add(i);
        }
        // 25 is between 20 and 30; floor is 20
        assertEquals(Integer.valueOf(20), t.floor(25));
        // 19 is between 10 and 20; floor is 10
        assertEquals(Integer.valueOf(10), t.floor(19));
        // 49 is between 40 and 50; floor is 40
        assertEquals(Integer.valueOf(40), t.floor(49));
    }

    @Test
    public void testFloorRequiresRightThenLeft() throws Exception {
        // Build a tree where the floor answer lies in the right subtree of the root,
        // requiring the algorithm to go right and then left.
        //
        //       20
        //      /  \
        //    10    30
        //         /
        //        25
        //
        // floor(27) must go right to 30, record 30 isn't the answer yet (27 < 30),
        // then go left to 25, record 25, then find null — answer is 25.
        AVLTree<Integer> t = new AVLTree<>();
        t.add(20);
        t.add(10);
        t.add(30);
        t.add(25);
        assertEquals(Integer.valueOf(25), t.floor(27));
    }

    @Test
    public void testFloorMinElement() throws Exception {
        AVLTree<Integer> t = new AVLTree<>();
        for (int i : new int[]{10, 20, 30, 40, 50}) {
            t.add(i);
        }
        // floor of the minimum element returns that element
        assertEquals(Integer.valueOf(10), t.floor(10));
    }

    @Test
    public void testFloorMaxElement() throws Exception {
        AVLTree<Integer> t = new AVLTree<>();
        for (int i : new int[]{10, 20, 30, 40, 50}) {
            t.add(i);
        }
        // floor of the maximum element returns that element
        assertEquals(Integer.valueOf(50), t.floor(50));
    }

    @Test
    public void testFloorAll() throws Exception {
        testFloorEmptyTree();
        testFloorSingleElementExactMatch();
        testFloorSingleElementBelow();
        testFloorSingleElementAbove();
        testFloorBelowAllElements();
        testFloorAboveAllElements();
        testFloorExactMatchInTree();
        testFloorBetweenElements();
        testFloorRequiresRightThenLeft();
        testFloorMinElement();
        testFloorMaxElement();
    }

    @Test
    public void testLLSimple() throws Exception {
        Node<Integer> A = new Node<>(5);
        Node<Integer> B = new Node<>(10, A);
        Node<Integer> T1 = new Node<>(0, A);
        A.left = T1;
        A.right = B;
        assertTrue(TreeUtilities.isAVLTree(A));

        AVLTree<Integer> t = new AVLTree<>();
        t.add(10);
        assertTrue(TreeUtilities.isAVLTree(t.root));
        t.add(5);
        assertTrue(TreeUtilities.isAVLTree(t.root));
        t.add(0);
        assertTrue(TreeUtilities.isAVLTree(t.root));

        assertTrue(TreeUtilities.equalSubtrees(A, t.root));
    }

    @Test
    public void testRRSimple() throws Exception {
        Node<Integer> B = new Node<>(10);
        Node<Integer> A = new Node<>(5, B);
        Node<Integer> T3 = new Node<>(15, B);
        B.left = A;
        B.right = T3;
        assertTrue(TreeUtilities.isAVLTree(B));

        AVLTree<Integer> t = new AVLTree<>();
        t.add(5);
        assertTrue(TreeUtilities.isAVLTree(t.root));
        t.add(10);
        assertTrue(TreeUtilities.isAVLTree(t.root));
        t.add(15);
        assertTrue(TreeUtilities.isAVLTree(t.root));

        assertTrue(TreeUtilities.equalSubtrees(B, t.root));
    }

    @Test
    public void testLRSimple() throws Exception {
        Node<Integer> B = new Node<>(15);
        Node<Integer> A = new Node<>(10, B);
        Node<Integer> C = new Node<>(20, B);
        Node<Integer> T3 = new Node<>(18, C);
        B.left = A;
        B.right = C;
        C.left = T3;
        assertTrue(TreeUtilities.isAVLTree(B));

        AVLTree<Integer> t = new AVLTree<>();
        t.add(20);
        assertTrue(TreeUtilities.isAVLTree(t.root));
        t.add(10);
        assertTrue(TreeUtilities.isAVLTree(t.root));
        t.add(15);
        assertTrue(TreeUtilities.isAVLTree(t.root));
        t.add(18);
        assertTrue(TreeUtilities.isAVLTree(t.root));

        assertTrue(TreeUtilities.equalSubtrees(B, t.root));
    }

    @Test
    public void testRLSimple() throws Exception {
        Node<Integer> B = new Node<>(15);
        Node<Integer> A = new Node<>(10, B);
        Node<Integer> C = new Node<>(20, B);
        Node<Integer> T3 = new Node<>(18, C);
        B.left = A;
        B.right = C;
        C.left = T3;
        assertTrue(TreeUtilities.isAVLTree(B));

        AVLTree<Integer> t = new AVLTree<>();
        t.add(10);
        assertTrue(TreeUtilities.isAVLTree(t.root));
        t.add(20);
        assertTrue(TreeUtilities.isAVLTree(t.root));
        t.add(15);
        assertTrue(TreeUtilities.isAVLTree(t.root));
        t.add(18);
        assertTrue(TreeUtilities.isAVLTree(t.root));

        assertTrue(TreeUtilities.equalSubtrees(B, t.root));
    }

    @Test
    public void testAVLWarning() throws Exception {        
        fail("This test is intended to fail. Read the comment and then comment out this test.");
        // We are not providing you with exhaustive tests for your AVL tree implementation,
        // just the simple ones above.
        // The next tests (testAVL100RandomizedAdds, testAVL100RandomizedAddsAndRemoves) 
        // creates 100 random trees and checks them
        // using isAVLTree. On your computer, this might erroneously pass if your isAVLTree()
        // implementation is flawed! On Gradescope, we will use our own implementation of
        // isAVLTree() when running these tests.
        // So, if either passes on your computer but not Gradescope, 
        // you need to figure out which of your rotations is broken, or if you are
        // calling the wrong rotations -- start by writing
        // more robust test cases where the various subtrees (T1, etc.) are nodes with
        // parent pointers, as the simple tests above don't check those cases.
    }

    @Test
    public void testAVL100RandomizedAddsOnly() throws Exception {
        for (int seed = 0; seed < 100; seed++) {
            // the seed controls the rest of the random choices in this method
            Random r = new Random(seed);

            int size = 1 + r.nextInt((int) Math.pow(2, 9));

            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                l.add(i);
            }

            Collections.shuffle(l, r);
            AVLTree<Integer> a = new AVLTree<>();
            int s = 0;
            for (Integer i : l) {
                assertEquals(s, a.size());
                assertFalse(a.contains(i));
                a.add(i);
                s++;
                assertTrue(TreeUtilities.isAVLTree(a.root));
                assertTrue(a.contains(i));
            }
        }
    }

    @Test
    public void testAVL100RandomizedAddsAndRemoves() throws Exception {
        for (int seed = 0; seed < 100; seed++) {
            // the seed controls the rest of the random choices in this method
            Random r = new Random(seed);

            int size = 1 + r.nextInt((int) Math.pow(2, 9));

            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                l.add(i);
            }
            Collections.shuffle(l, r);
            AVLTree<Integer> a = new AVLTree<>();
            int s = 0;
            for (Integer i : l) {
                assertEquals(s, a.size());
                assertFalse(a.contains(i));
                a.add(i);
                s++;
                assertTrue(TreeUtilities.isAVLTree(a.root));
                assertTrue(a.contains(i));
                assertEquals(s, a.size());
            }

            Collections.shuffle(l, r);
            for (Integer i: l) {
                assertEquals(s, a.size());
                assertTrue(a.contains(i));
                a.remove(i);
                s--;
                assertTrue(TreeUtilities.isAVLTree(a.root));
                assertFalse(a.contains(i));
                assertEquals(s, a.size());
            }
        }
    }
 }
