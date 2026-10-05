# UNIVERSITY OF MASSACHUSETTS AMHERST
## MANNING COLLEGE OF INFORMATION AND COMPUTER SCIENCE
### CICS 210 DATA STRUCTURES
**✦ PROGRAMMING ASSIGNMENT 5 – PA05 – LIST IMPLEMENTATION ✦**

## Overview

We've spent some time in class and in the readings seeing how you can use the simple building blocks of *references* and *arrays* to implement higher level abstractions. In particular, we built implementations of the List Abstract Data Type, in two different ways: one using arrays, and one using linked nodes (a so-called "linked list").

In this assignment, you'll continue this process, adding or modifying a few methods in each implementation.

We've provided a set of unit tests to help with automated testing, though you might also want to write a class with a `main` method for interactive testing. As before, we've disable the timeout code so you can use the debugger, but if your code gets stuck during testing, you might want to uncomment the `@Timeout` line at the top of each test file.

## Goals

- Translate Javadoc descriptions of behavior into code.
- Practice writing instance methods.
- Practice implementing generic abstractions.
- Test code using unit tests.

## Downloading and importing the starter code

As in previous assignments, download and decompress the provided archive file containing the starter code. Then import it into Code in the same way; you should end up with a `list-implementation-student` project.

## What to do

As usual, look over the files we've provided. The `support/` directory (which contains files you should *not* modify) contains the `List` interface, which describes what behavior will need to be implemented. The `src/` directory contains the `ArrayList` and `LinkedList` classes, where you will implement the behavior defined in `List`. And, the `test/` directory contains tests of the desired behavior.

We suggest you start with `ArrayList` as most students find it simpler and easier to debug. Once you have that working, move on to `LinkedList`.

**One of the first things you should do is complete the `.equals()` method in each of the implementations.** Almost all of the tests require a functioning `equals()`. We've written most of it for you -- it already handles the `null` and type checks, but you must write the code that verifies that each element contained within the current list and the other list are semantically equal -- if they're not, then return `false`. If your `equals()` method uses another method (for example, `get()`), then you'll also need to complete that method before you can expect `equals()` to work.

As for the rest of the methods, you are welcome and expected to use the code we wrote in class as a reference for this assignment. But you're going to get Maximum Learning™️ if you do it by thinking about it, rather than just copying code out of the lecture notes and/or one of many web sites (or LLMs). You will be expected to be able to implement these methods during, say, quizzes and exams -- this assignment serves as practice.

And, in particular, for this assignment, you may not use or import the `java.util.ArrayList` or `java.util.LinkedList` classes. The point of this assignment is to build them yourself! 

Also note, that **unlike in the previous assignments, there are some portions of this assignment we may manually audit.** In particular, human graders may check for the following:

  - In your `ArrayList` implementation, the array backing the list should grow dynamically -- don't just allocate an enormous list to pass the tests!
  - Similarly, the array's growth should have optimal behavior (in this case "amortized constant time") when adding to the list, growing by some multiplicative (not additive) factor. We have talked about the right approach in lecture.
  - The `LinkedList` should have a `tail` reference that is kept pointing to the last element of the linked list. This will require some thought -- and changes to `add` and `remove`. In particular, make sure you update `tail` appropriately when adding to an empty list, when removing the last element in a multi-element list, and when removing the last element in a single-element list.
  - The `LinkedList` should use this `tail` reference to make the single-argument `add()` method (which appends to the end of the list) run in "constant time" -- in particular, unlike the version we wrote in lecture, it should no longer traverse the list, but instead use the fact there is a `tail` pointer to append directly to the end.

## Submitting the assignment

When you have completed the changes to your code, you should export an archive file containing the entire Java project. To do this, follow the same steps as from Assignment 01 to produce a `.zip` file, and upload it to Gradescope.

Remember, you can resubmit the assignment as many times as you want, until the deadline. If it turns out you missed something and your code doesn't pass 100% of the tests, you can keep working until it does. We will not do any manual grading until after the late deadline for the assignment passes.
