/*
 * Copyright 2026 Marc Liberatore.
 */

package recursion;

public class RecursionExercises {

    /**
     * sumOfDigits (both implementations) should return the sum of the
     * digits of its input, x.
     * 
     * If `x` is 1234, the function should return 1 + 2 + 3 + 4, that is, 10.
     * 
     * If `x` is 345, the function should return 3 + 4 + 5 = 12.
     * 
     * If `x` is 3, the function should return 3.
     * 
     * If `x` is negative, *ignore the minus sign*. For example, -12 and 12
     * should both return 3.
     */

    /**
     * Return the sum of the digits of `x`; your algorithm must be implemented
     * iteratively.
     * 
     * @param x
     * @return
     */
    static int sumOfDigitsIterative(int x) {
        x = Math.abs(x);
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    /**
     * Return the sum of the digits of `x`; your algorithm must be implemented
     * recursively. No explicit loops (`for`, `while`) or use of Streams are
     * allowed!
     * 
     * @param x
     * @return
     */
    static int sumOfDigitsRecursive(int x) {
        x = Math.abs(x);
        if (x == 0) {
            return 0;
        }
        return x % 10 + sumOfDigitsRecursive(x / 10);
    }

    /**
     * Returns the length of the linked list, starting from node.
     * Empty lists have length 0.
     * 
     * (Must traverse the list using recursion.)
     * 
     * @param node - the head of a list
     * @return the size (length) of the linked list, starting from node
     */
    static <E> int size(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.next);
    }

    /**
     * Returns the i-th Node in the linked list which starts at node,
     * or null if the list is empty.
     * 
     * (Must be implemented recursively.)
     * 
     * @param node the head of a list
     * @param i
     * @return the i-th node of the list
     */
    static <E> Node<E> getNode(Node<E> node, int i) {
        if (node == null) {
            return null;
        }
        if (i == 0) {
            return node;
        }
        return getNode(node.next, i - 1);
    }

    /**
     * Returns true iff the list contains the specified value.
     * 
     * (Must be implemented recursively.)
     * 
     * @param node the head of the list
     * @param e    the element being searched for
     * @return true iff the list contains the element
     */
    static <E> boolean contains(Node<E> node, E e) {
        if (node == null) {
            return false;
        }
        if (node.data.equals(e)) {
            return true;
        }
        return contains(node.next, e);
    }

    /**
     * Returns true iff every element of the list is evenly divisible by 2.
     * 
     * An empty list should return `true`.
     * 
     * @param node the head of the list
     * @return true iff every element of the list is even
     */
    static boolean noneOdd(Node<Integer> node) {
        if (node == null) {
            return true;
        }
        if (node.data % 2 != 0) {
            return false;
        }
        return noneOdd(node.next);
    }

    /**
     * Returns true iff the list every element of the list is evenly divisible by 2.
     * 
     * An empty list should return `false`.
     * 
     * @param node the head of the list
     * @return true iff every element of the list is even
     */
    static boolean allEven(Node<Integer> node) {
        if (node == null) {
            return false;
        }
        return noneOdd(node);
    }
}
