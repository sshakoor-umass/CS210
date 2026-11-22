# UNIVERSITY OF MASSACHUSETTS AMHERST
## MANNING COLLEGE OF INFORMATION AND COMPUTER SCIENCE
### CICS 210 DATA STRUCTURES
**✦ PROGRAMMING ASSIGNMENT 11 – PA11 – HEAPS AND TREAPS ✦**

## Overview

In previous assignments, we've looked at various kinds of binary trees -- BSTs and AVL trees, in particular. This time, we're here for heaps -- efficiently implemented as arrays -- and treaps, a kind of hybrid BST/heap that acts as an approximately-balanced BST.

In this assignment, you'll write several methods to manipulate heaps (as implemented as arrays). You'll also complete the implementation of a treap -- I've done most of the busywork for you, but you'll need to handle the mechanics of `add` maintaining the heap property on priorities without breaking the BST property. To do this, you'll need to pull in code from a previous assignment. 

We've provided a set of unit tests to help with automated testing. But this is not a comprehensive set of tests! And, Gradescope has additional tests it will run that you don't have access to. If your code can pass the tests we've provided, it is likely correct, but the Gradescope tests are the final arbiter in terms of grading.

## Goals

- Translate written descriptions of behavior into code.
- Demonstrate understanding of heap and treap behavior.
- Implement several foundational heap and treap algorithms.
- Test code using unit tests.
- Develop new tests as necessary to verify program behavior.

## Downloading and importing the starter code

As in previous assignments, download and decompress the provided archive file containing the starter code. Then import it into Code in the same way; you should end up with a `heaps-and-treaps-student` project in the "Project Explorer".

## What to do

Start with `HeapUtilities` fill in the methods there. Here are some suggestions and hints:

  - Notice that this is a **MAX**-heap, not a min-heap.
  - You don't have to implement `isHeap` -- but you will want to if you want to be able to run the randomized test locally.
  - `siftDown` is *almost* exactly what we did in class, with one change. In particular, if you want to only treat the first `0..n-1` elements of the array as a heap and ignore the elements from `n` onward, you pass the appropriate value of `n`. If you just want to sift over the whole array, then pass in `a.length` for `n`.
  - `heapify` should be a two-liner based upon the linear-time algorithm we discussed in class.
  - `heapSort` begins by `heapify`ing the array. Then it swaps the largest element (at index 0) to the end of the heap -- the (n-1th) element of the array. Then it sifts-down the element at index 0, treating the heap as the first n-1 (not n!) elements of the array. It then repeats: swap the largest element to the end of heap (now at n-2), and sift-down the element at index 0, treating the heap as the first n-2 elements of the array, and so on.  

Then move on to `Treap`:

  - Several methods are repeats from previous assignments -- feel free to copy/paste your old code in.
  - `add(E e, Node<E> node)` is the same BST add code you've seen before. You must modify it to check-and-fix the heap property on the nodes' priorities.
  - I strongly suggest you pull out the check-and-fix code into its own instance method, rather than trying to inline it in `add`.
  - `Node` has been updated with a `priority` value for you already, as has its `toString` method. So has `TreePrinter`. These things might help you when debugging.
  - You do not need to implement `remove` -- capturing the special cases involving the `root` make it cumbersome (even moreso than the AVL rotations), so I decided to skip it. Feel free to write it and some tests if you want, though, it's good practice 😂

## Submitting the assignment

When you have completed the changes to your code, follow the same steps as from Assignment 01 to produce a `.zip` file, and upload it to Gradescope.

Remember, you can resubmit the assignment as many times as you want, until the deadline. If it turns out you missed something and your code doesn't pass 100% of the tests, you can keep working until it does.
