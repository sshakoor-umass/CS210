/*
 * Copyright 2025 Marc Liberatore.
 */

package loops.exercises;

import java.util.List;

public class ForExercises {
    /**
     * Return the sum of the integers from 0 to (n-1).
     * 
     * Use a "classic" for loop (or the closed-form expression 
     * you may have learned in Calc 2).
     * 
     * Assume 0 <= n.
     */
    public static int sumTo(int n) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += i;
        }
        return total;
    }

    /**
     * Return the sum of the integers from m to (n-1).
     * 
     * Assume 0 <= m < n.
     */
    public static int sumFromTo(int m, int n) {
        int total = 0;
        for (int i = m; i < n; i++) {
            total += i;
        }
        return total;
    }

    /**
     * Return the sum of the elements in the array. 
     * 
     * Use a for-each loop.
     */
    public static int sum(int[] a) {
        int total = 0;
        for (int i : a) {
            total += i;
        }
        return total;
    }

    /**
     * Return the sum of the elements in the list. 
     * 
     * Use a for-each loop.
     */
    public static int sum(List<Integer> l) {
		    return 0;
    }


    /**
     * Returns true if and only if (iff) every element in the array is divisible by 2. 
     * Also returns `true` for arrays of length 0.
     * 
     * An int x is even iff `x % 2 == 0`.
     * 
     * Use a for-each loop.
     */
    public static boolean allEven(int[] a) {
        for (int i : a) {
            if (i % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true iff the list l contains the integer i.
     * 
     * Use a for-each loop.
     */
    public static boolean contains(List<Integer> l, int i) {
        for (int j : l) {
            if (j == i) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the sum of every-other element of the array, starting
     * at index 0 (in other words, a[0] + a[2] + a[4] + ...).
     * 
     * Use a classic for loop.
     */
    public static int sumEveryOther(int[] a) {
        int total = 0;
        for (int i = 0; i < a.length; i += 2) {
            total += a[i];
        }
        return total;
    }

    /**
     * Returns true iff each element of the list (starting at index 1) 
     * is exactly double the previous element.
     * 
     * Assume l.size() is at least 2.
     * 
     * Use a classic for loop.
     */
    public static boolean doublingList(List<Integer> l) {
        for (int i = 1; i < l.size(); i++) {
            if (l.get(i) != 2 * l.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}