# UNIVERSITY OF MASSACHUSETTS AMHERST
## MANNING COLLEGE OF INFORMATION AND COMPUTER SCIENCE
### CICS 210 DATA STRUCTURES
**✦ PROGRAMMING ASSIGNMENT 8 – PA08 – HASH TABLES ✦**

## Overview

We covered a simple implementation of hash tables in class. In this assignment, you'll expand that implementation to be more robust, you'll develop another one that we only discussed at a high level, and you'll use it to implement a simplified version of Java's Map.

In particular, you will further develop the chained hash table implementation. Then, you will write a version using open-addressing (with linear probing). Finally, you'll implement a map interface using one of your two implementations of hash tables, along with a key-value class we've provided for you.

We've provided a large-ish set of unit tests to help with automated testing, as there are several edge cases to handle even for simple tables with chaining as a collision resolution mechanism.

## Goals

- Translate written descriptions of behavior into code.
- Demonstrate understanding of hash tables and chaining as a collision-resolution mechanism.
- Test code using unit tests.
- Develop new tests as necessary to verify program behavior.

## Downloading and importing the starter code

As in previous assignments, download and decompress the provided archive file containing the starter code. Then import it into Code in the same way; you should end up with a `hash-tables-student` project in the "Project Explorer".

## What to do

You're a pro by now, but just in case: Read over the existing files and the associated Javadoc. You will be working on the files in `src/`, but they implement methods in interfaces described in `support/` -- your implementation must conform to the specifications laid out in those interfaces. **Do not modify the files in `support/`, as the autograder uses the original versions of them when grading your assignment.**

Some specific notes on `ChainingHashTable`:

  - Use chaining (via an array of `LinkedList`s) to resolve collisions, just like we did in class.
  - Your implementation must not import any other classes -- just `java.util.LinkedList`, `java.util.Iterator`, and `java.util.NoSuchElementException` (possibly needed for the `Iterator`).
  - Pay attention to the difference between `size` and `capacity`.
  - Note that enlarging the underlying array happens at the *start* of an `add()` (that is, *before* the new element is added), only when the load factor is strictly greater than 0.75, *before* checking to see if the element is already in the table. This means the following sequence of states/events is valid:
      - The table has a load factor of something less than or equal to 0.75.
      - An element is added.
      - The table now has a load factor of greater than 0.75.
      - THE ARRAY IS NOT YET ENLARGED! Calls to `get()` etc. can happen here without triggering enlargement.
      - The *next time* `add()` is called, the array is enlarged *before* the next element is added.
  - The array never shrinks.
  - Read the contract for `add()` carefully -- the element should be replaced *even if it already exists in the table*. This detail matters for the implementation of `SimpleHashMap`, as we saw in lecture. The key-value pairs are represented by an object that uses equality *only on keys* -- so different key-value pairs might be "equal" even if they have different values.
  - If you are having trouble, I would strongly suggest two things. First, define a useful `toString()` method. Second, use the debugger.

Some specific notes on `OpenHashTable`:

  - Much of the advice for `ChainingHashTable` applies here too: pay attention to `size` vs. `capacity`, read the `add()` contract carefully (overwrite if the element already exists), and use `toString()` and the debugger liberally.
  - In the starter code, tombstones are represented in a separate `boolean[]` array, with indices that correspond to the hash table `array`. This is one of many possible implementations, and among the easiest to debug. You do not have to do it this way -- you can create a wrapper class for the elements stored in the hash table that has a per-object `isTombstone` flag, for example.
  - The core operation in every method is probing: starting from the home index (computed by `index()`), step forward one cell at a time. A probe chain stops -- meaning the element you are looking for is definitely *not* in the table -- only when you hit a cell that is `null` (and not a tombstone, by definition in this implementation). A tombstoned cell does *not* end the search, because the element you want may have been inserted after the tombstone was created. Remember to wrap around if appropriate!
  - Unlike `ChainingHashTable`, which checks `loadFactor()`, the resize in `OpenHashTable` is triggered by `loadFactorWithTombstones()` -- that is, `(size + tombstones) / capacity`.
  - `add()` is the trickiest method. You need to do two things simultaneously during the probe: (1) remember the *first* tombstone slot you encounter (a candidate insertion point if the element turns out to be new), and (2) keep probing past tombstones to check whether the element is already present. Don't forget to clear the `tombstone` flag when you reuse a tombstone slot.
  - When you resize, you allocate a fresh `array` and `tombstone` of the new capacity and re-insert all *live* elements. Tombstones *do not* get copied over -- they are cleared during rehashing, which is one of the benefits of resizing.
  - When you `remove()`, don't actually delete the element. Just set `tombstone[i] = true` and decrement `size`. The array cell keeps its value so that probe chains for *other* elements remain intact.
  - The iterator must skip over both `null` cells and tombstoned cells; only live (non-null, non-tombstone) entries should be yielded. The structure is very similar to the iterator for `ChainingHashTable`, but in some ways is simpler since there is no inner chain to walk.

Some specific notes on `SimpleHashMap`:

  - You **MUST** use `ChainingHashTable` OR `OpenHashTable` in your implementation. You may import and use `java.util.Set` and `java.util.HashSet` **ONLY** in the `keys()` method. You may not use `java.util.HashSet` (or especially, `java.util.HashMap`) to implement `SimpleHashMap`.
  - Using `ChainingHashTable` should make the methods in `SimpleHashMap` short -- if they are longer than six lines or so (some are just a couple lines), you're doing something the hard way.
  - As mentioned above, use the existing `SimpleMapEntry` class. This class only uses the key value when generating hash values or checking `equals()`, which is exactly the semantics you want for a hash table. We did this in class recently for maps backed by both hash tables and trees.
  - Also as mentioned above, define `toString()` methods and use the debugger if you're having trouble.

## Submitting the assignment

When you have completed the changes to your code, follow the same steps as from Assignment 01 to produce a `.zip` file, and upload it to Gradescope.

Remember, you can resubmit the assignment as many times as you want, until the deadline. If it turns out you missed something and your code doesn't pass 100% of the tests, you can keep working until it does.
