/*
 * Copyright 2026 Marc Liberatore.
 */

package recursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import static recursion.RecursionExercises.*;

// @Timeout(10)
public class RecursionExercisesTest {

    @Test
    public void testSumOfDigitsIterative() {
        assertEquals(0, sumOfDigitsIterative(0));
        assertEquals(1, sumOfDigitsIterative(1));
        assertEquals(3, sumOfDigitsIterative(12));
        assertEquals(6, sumOfDigitsIterative(123));
        assertEquals(10, sumOfDigitsIterative(1234));
        assertEquals(1, sumOfDigitsIterative(-1));
        assertEquals(3, sumOfDigitsIterative(-21));
        assertEquals(6, sumOfDigitsIterative(-321));
        assertEquals(10, sumOfDigitsIterative(-1234));
    }

    @Test
    public void testSumOfDigitsRecursive() {
        assertEquals(0, sumOfDigitsRecursive(0));
        assertEquals(1, sumOfDigitsRecursive(1));
        assertEquals(3, sumOfDigitsRecursive(12));
        assertEquals(6, sumOfDigitsRecursive(123));
        assertEquals(10, sumOfDigitsRecursive(1234));
        assertEquals(1, sumOfDigitsRecursive(-1));
        assertEquals(3, sumOfDigitsRecursive(-21));
        assertEquals(6, sumOfDigitsRecursive(-321));
        assertEquals(10, sumOfDigitsRecursive(-1234));
    }

    @Test
    public void testSize() {
        final Node<Character> a = new Node<>('a');
        final Node<Character> b = new Node<>('b', a);
        final Node<Character> c = new Node<>('c', b);
        final Node<Character> d = new Node<>('d', c);

        assertEquals(0, size(a.next));
        assertEquals(1, size(a));
        assertEquals(2, size(b));
        assertEquals(3, size(c));
        assertEquals(4, size(d));
    }

    @Test
    public void testGetNode() {
        final Node<Character> a = new Node<>('a');
        final Node<Character> b = new Node<>('b', a);
        final Node<Character> c = new Node<>('c', b);
        final Node<Character> d = new Node<>('d', c);

        assertEquals(null, getNode(null, 0));
        assertEquals(null, getNode(a, 1));
        assertEquals(a, getNode(a, 0));

        assertEquals(b, getNode(b, 0));
        assertEquals(a, getNode(b, 1));
        assertEquals(null, getNode(b, 2));

        assertEquals(c, getNode(c, 0));
        assertEquals(b, getNode(c, 1));
        assertEquals(a, getNode(c, 2));
        assertEquals(null, getNode(c, 3));

        assertEquals(d, getNode(d, 0));
        assertEquals(c, getNode(d, 1));
        assertEquals(b, getNode(d, 2));
        assertEquals(a, getNode(d, 3));
        assertEquals(null, getNode(d, 4));
    }

    @Test
    public void testContains() {
        final Node<Character> a = new Node<>('a');
        final Node<Character> b = new Node<>('b', a);
        final Node<Character> c = new Node<>('c', b);
        final Node<Character> d = new Node<>('d', c);

        // empty list contains nothing
        assertFalse(contains(null, 'a'));

        // single-element list
        assertTrue(contains(a, 'a'));
        assertFalse(contains(a, 'b'));

        // element at head
        assertTrue(contains(b, 'b'));
        // element at tail
        assertTrue(contains(b, 'a'));
        // element not in list
        assertFalse(contains(b, 'z'));

        // longer list — element at each position
        assertTrue(contains(d, 'd'));
        assertTrue(contains(d, 'c'));
        assertTrue(contains(d, 'b'));
        assertTrue(contains(d, 'a'));
        assertFalse(contains(d, 'z'));
    }

    @Test
    public void testNoneOdd() {
        // empty list returns true
        assertTrue(noneOdd(null));

        // single-element lists
        assertTrue(noneOdd(new Node<>(2)));
        assertFalse(noneOdd(new Node<>(1)));

        // all-even list
        final Node<Integer> allEvens = new Node<>(4, new Node<>(6, new Node<>(8)));
        assertTrue(noneOdd(allEvens));

        // odd element at head
        final Node<Integer> oddAtHead = new Node<>(1, new Node<>(2, new Node<>(4)));
        assertFalse(noneOdd(oddAtHead));

        // odd element in the middle
        final Node<Integer> oddInMiddle = new Node<>(2, new Node<>(3, new Node<>(4)));
        assertFalse(noneOdd(oddInMiddle));

        // odd element at tail
        final Node<Integer> oddAtTail = new Node<>(2, new Node<>(4, new Node<>(5)));
        assertFalse(noneOdd(oddAtTail));
    }

    @Test
    public void testAllEven() {
        // empty list returns false
        assertFalse(allEven(null));

        // single-element lists
        assertTrue(allEven(new Node<>(2)));
        assertFalse(allEven(new Node<>(1)));

        // all-even list
        final Node<Integer> allEvens = new Node<>(4, new Node<>(6, new Node<>(8)));
        assertTrue(allEven(allEvens));

        // odd element at head
        final Node<Integer> oddAtHead = new Node<>(1, new Node<>(2, new Node<>(4)));
        assertFalse(allEven(oddAtHead));

        // odd element in the middle
        final Node<Integer> oddInMiddle = new Node<>(2, new Node<>(3, new Node<>(4)));
        assertFalse(allEven(oddInMiddle));

        // odd element at tail
        final Node<Integer> oddAtTail = new Node<>(2, new Node<>(4, new Node<>(5)));
        assertFalse(allEven(oddAtTail));
    }
}
