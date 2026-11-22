
/*
 * Copyright 2023 Marc Liberatore.
 */

package heaps;

import java.util.Arrays;
import java.util.Random;

public class HeapUtilities {

    /**
     * Returns true iff the subtree of a starting at index i is a max-heap.
     * @param a an array representing a mostly-complete tree, possibly a heap
     * @param i an index into that array representing a subtree rooted at i
     * @return true iff the subtree of a starting at index i is a max-heap
     */
    static boolean isHeap(double[] a, int i) {
        if (i >= a.length) {
            return true;
        }

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < a.length && a[left] > a[i]) {
            return false;
        }

        if (right < a.length && a[right] > a[i]) {
            return false;
        }

        return isHeap(a, left) && isHeap(a, right);
    }

    /**
     * Perform the heap siftdown operation on index i of the array a.
     *
     * This method assumes the subtrees of i are already valid max-heaps.
     *
     * This operation is bounded by n (exclusive).
     *
     * @param a the array being sifted
     * @param i the index of the element to sift down
     * @param n the bound on the array
     */
    static void siftDown(double[] a, int i, int n) {
        while (2 * i + 1 < n) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            if (a[left] > a[largest]) {
                largest = left;
            }

            if (right < n && a[right] > a[largest]) {
                largest = right;
            }

            if (largest == i) {
                break;
            }

            double temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            i = largest;
        }
    }

    /**
     * Heapify the array a in-place in linear time as a max-heap.
     * @param a an array of values
     */
    static void heapify(double[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            siftDown(a, i, a.length);
        }
    }

    /**
     * Heapsort the array a in-place, resulting in the elements
     * of a being in ascending order.
     * @param a an array of values
     */
    static void heapSort(double[] a) {
        heapify(a);

        for (int end = a.length - 1; end > 0; end--) {
            double temp = a[0];
            a[0] = a[end];
            a[end] = temp;

            siftDown(a, 0, end);
        }
    }
}
