# UNIVERSITY OF MASSACHUSETTS AMHERST
## MANNING COLLEGE OF INFORMATION AND COMPUTER SCIENCE
### CICS 210 DATA STRUCTURES
**✦ PROGRAMMING ASSIGNMENT 10 – PA10 – AVL TREES ✦**

## Overview

Over the last few classes, we've been exploring the wonderful world of Binary Search Trees and one of their self-balancing variants, the AVL tree.

In this assignment, you'll finish implementing the AVL tree rotations that we started in class, and also do another small exercise (the `floor()` method) related to the BST property.

We've provided a set of unit tests to help with automated testing. **But similar to recent assignments**, this is not a comprehensive set of tests! And, Gradescope has additional tests it will run that you don't have access to. If your code can pass the tests we've provided, it is likely correct, but the Gradescope tests are the final arbiter in terms of grading.

## Goals

- Translate written descriptions of behavior into code.
- Demonstrate understanding of BST and AVL properties.
- Test code using unit tests.
- Develop new tests as necessary to verify program behavior.

## Downloading and importing the starter code

As in previous assignments, download and decompress the provided archive file containing the starter code. Then import it into Code in the same way; you should end up with a `avl-trees-student` project in the "Project Explorer".

## What to do

You can, if you want, just go right to `AVLTree`. This file contains a cleaned-up version of the code we wrote in class. I want you to finish implementing the remaining rotations, insertion check, and the deletion check There's no trick here -- you need to translate the rotation diagrams from the lecture notes into code; the LL rotation is already done for you to use as a reference/example. We wrote the `remove()` method for you so you could see how it works. In it, rather than backtracking upward and tracking the direction of the imbalance, it computes downward once it finds the imbalanced node.

To aid you in debugging, you can use the `isAVLTree` method (and others) from `TreeUtilities`; you can also use the included version of `TreePrinter` to see what's happening after each rotation. We provided some tests for these methods (and some will be carryover from last week) -- but none of these are graded, so you don't have to do them if you don't want to. It's good practice, though.

And, I expect  that some of you will end up making minor reference assignment errors in rotations (forgetting to set a parent, or setting a reference, in the wrong direction, or the like -- no shame, I did it too!). Some combination of the methods from `TreeUtilities`, the debugger, and printing the tree will help you solve these problems -- but only if you can recognize when things go wrong, either due to NPEs or your `isAVLTree` returning false!

## Submitting the assignment

When you have completed the changes to your code, follow the same steps as from Assignment 01 to produce a `.zip` file, and upload it to Gradescope.

Remember, you can resubmit the assignment as many times as you want, until the deadline. If it turns out you missed something and your code doesn't pass 100% of the tests, you can keep working until it does.
