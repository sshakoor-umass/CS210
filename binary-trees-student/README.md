# UNIVERSITY OF MASSACHUSETTS AMHERST
## MANNING COLLEGE OF INFORMATION AND COMPUTER SCIENCE
### CICS 210 DATA STRUCTURES
**✦ PROGRAMMING ASSIGNMENT 9 – PA09 – BINARY TREES ✦**

## Overview

Over the last couple of classes, we've been exploring the wonderful world of binary trees, and then Binary Search Trees.

In this assignment, you'll write several methods to check various properties of trees. You'll write the method to construct a perfectly-balanced BST from an arbitrary BST. And, you'll implement a BST, just like we did in class.

We've provided a set of unit tests to help with automated testing. **But unlike previous assignments**, this is not a comprehensive set of tests! And, Gradescope has additional tests it will run that you don't have access to. If your code can pass the tests we've provided, it is likely correct, but the Gradescope tests are the final arbiter in terms of grading.

## Goals

- Translate written descriptions of behavior into code.
- Demonstrate understanding of binary and binary search tree properties.
- Test code using unit tests.
- Develop new tests as necessary to verify program behavior.

## Downloading and importing the starter code

As in previous assignments, download and decompress the provided archive file containing the starter code. Then import it into Code in the same way; you should end up with a `binary-trees-student` project in the "Project Explorer".

## What to do

Start with `TreeUtilities` fill in the methods there. Here are some suggestions and hints:

  - For `inOrder`, I strongly suggest doing this one recursively rather than trying to backtrack the `parent` pointers. Write a recursive helper method and pass around a `List` to accumulate the result. 
  - `height` is a standalone implementation of the instance method we wrote in class. It should be short and recursive.
  - `intoBalanced` follows the recursive algorithm we talked about but didn't program in class. Pull out the values from the old tree into a sorted list and create a new tree. Then, pass that list and tree into a recursive method. That recursive method should insert the middle element of the list into the tree, then recurse on the left and right remaining elements of the list. Make sure to stop when the list gets small enough! Note that the Gradescope test here uses our own implementation of `height`, not yours, so if your `height` is subtly wrong, your `intoBalanced` might pass local tests but fail on Gradescope. Note that this won't fully work until you complete `add()` in `BinarySearchTree`!
  - `isBST` verifies that a tree rooted at a given node is, in fact, a BST. Strictly speaking you don't need to complete this one, but it will be helpful in checking your implementation of BSTs.

Generally, none of the above are super long methods; most are easiest if you use recursion and recursive helper methods, but you shouldn't be writing hundreds of lines of code to complete this file -- if you find yourself doing so, stop and ask for help!

Then, move on to `BinarySearchTree`. This file will largely consist of the code we wrote in class. We want you to finish implementing it -- there are a couple extra methods to implement but they should be self-explanatory.

To aid you in debugging, you can use your `isBST` method from `TreeUtilities`; you can also use the included version of `TreePrinter` to see what's happening after each operation. Some combination of the debugger and printing the tree will help you solve any problems that arise -- but only if you can recognize when things go wrong, either due to NPEs or your `isBST` returning false!

## Submitting the assignment

When you have completed the changes to your code,  follow the same steps as from Assignment 01 to produce a `.zip` file, and upload it to Gradescope.

Remember, you can resubmit the assignment as many times as you want, until the deadline. If it turns out you missed something and your code doesn't pass 100% of the tests, you can keep working until it does.
