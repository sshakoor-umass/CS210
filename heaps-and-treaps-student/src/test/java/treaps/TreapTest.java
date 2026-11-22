/*
 * Copyright 2023 Marc Liberatore.
 */

package treaps;

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
public class TreapTest {


   @BeforeEach
   public void setup() {
   }

   @Test
   public void testContainsEmpty() throws Exception {
      Treap<Integer> t = new Treap<>();
      assertFalse(t.contains(42));
   }

   @Test
   public void testContainsReturnsFalse() throws Exception {
      Treap<Integer> t = new Treap<>();
      t.add(1);
      t.add(2);
      t.add(3);
      assertFalse(t.contains(99));
      assertFalse(t.contains(0));
   }

   @Test
   public void testInOrderEmpty() throws Exception {
      List<Integer> result = Treap.inOrder(null);
      assertTrue(result.isEmpty());
   }

   @Test
   public void testInOrderSingle() throws Exception {
      Node<Integer> node = new Node<>(5);
      List<Integer> result = Treap.inOrder(node);
      assertEquals(Collections.singletonList(5), result);
   }

   @Test
   public void testInOrderThreeNodes() throws Exception {
      Node<Integer> root = new Node<>(2);
      Node<Integer> left = new Node<>(1, root);
      Node<Integer> right = new Node<>(3, root);
      root.left = left;
      root.right = right;
      List<Integer> result = Treap.inOrder(root);
      assertEquals(Arrays.asList(1, 2, 3), result);
   }

   @Test
   public void testIsBSTValid() throws Exception {
      Node<Integer> root = new Node<>(2);
      Node<Integer> left = new Node<>(1, root);
      Node<Integer> right = new Node<>(3, root);
      root.left = left;
      root.right = right;
      assertTrue(Treap.isBST(root));
   }

   @Test
   public void testIsBSTInvalid() throws Exception {
      // root=2, left=3, right=1: left child is larger than root — not a BST
      Node<Integer> root = new Node<>(2);
      Node<Integer> left = new Node<>(3, root);
      Node<Integer> right = new Node<>(1, root);
      root.left = left;
      root.right = right;
      assertFalse(Treap.isBST(root));
   }

   @Test
   public void testIsHeapTreapValid() throws Exception {
      Node<Integer> root = new Node<>(10);
      root.priority = 50;
      Node<Integer> left = new Node<>(5, root);
      left.priority = 30;
      Node<Integer> right = new Node<>(15, root);
      right.priority = 20;
      root.left = left;
      root.right = right;
      assertTrue(Treap.isHeap(root));
   }

   @Test
   public void testIsHeapTreapInvalid() throws Exception {
      Node<Integer> root = new Node<>(10);
      root.priority = 5;
      Node<Integer> left = new Node<>(5, root);
      left.priority = 10; // child priority exceeds parent — heap violation
      root.left = left;
      assertFalse(Treap.isHeap(root));
   }

   @Test
   public void testSizeAfterAdds() throws Exception {
      Treap<Integer> t = new Treap<>();
      int[] values = { 10, 3, 7, 1, 5 };
      for (int i = 0; i < values.length; i++) {
         t.add(values[i]);
         assertEquals(i + 1, t.size());
      }
   }

   @Test
   public void testAddDuplicate() throws Exception {
      Treap<Integer> t = new Treap<>();
      t.add(5);
      t.add(5);
      assertEquals(1, t.size());
      assertTrue(t.contains(5));
      assertTrue(Treap.isBST(t.root));
   }

   @Test
   public void testSmallTreapStructure() throws Exception {
      Treap<Integer> t = new Treap<>();
      int[] values = { 5, 3, 7, 1, 4 };
      for (int v : values) {
         t.add(v);
         assertTrue(Treap.isBST(t.root));
         assertTrue(Treap.isHeap(t.root));
      }
      assertEquals(5, t.size());
      for (int v : values) {
         assertTrue(t.contains(v));
      }
   }

   @Test
   public void testTreapAll() throws Exception {
      testContainsEmpty();
      testContainsReturnsFalse();
      testInOrderEmpty();
      testInOrderSingle();
      testInOrderThreeNodes();
      testIsBSTValid();
      testIsBSTInvalid();
      testIsHeapTreapValid();
      testIsHeapTreapInvalid();
      testSizeAfterAdds();
      testAddDuplicate();
      testSmallTreapStructure();
   }

   @Test
   public void testIsHeapWarning() throws Exception {        
      fail("This test is intended to fail. Read the comment and then comment out this test.");
      // We are not providing you with exhaustive tests for your inOrder, isBST, or isHeap code.
      // But testTreapAll and likely also your local version of testTreap1000Randomized
      // won't pass until you do. (The Gradescope test may pass if your treap code is correct
      // -- it doesn't use your version of inOrder, isBST, or isHeap.)
   }

   @Test
   public void testTreap1000Randomized() throws Exception {
      final int size = 100;
      for (int seed = 0; seed < 1000; seed++) {
         // the seed controls the rest of the random choices in this method
         Random r = new Random(seed);

         List<Integer> l = new ArrayList<>(size);
         for (int i = 0; i < size; i++) {
            l.add(i);
         }
         Collections.shuffle(l, r);

         Treap<Integer> t = new Treap<>();
         List<Integer> v = new ArrayList<>();
         int count = 0;
         for (int i : l) {
            count++;
            t.add(i);
            v.add(i);
            assertTrue(Treap.isBST(t.root));
            assertTrue(Treap.isHeap(t.root));
            assertEquals(count, t.size());
            for (int j : v) {
               assertTrue(t.contains(j));
            }
         }
      }
   }

}
