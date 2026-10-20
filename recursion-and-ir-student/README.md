# UNIVERSITY OF MASSACHUSETTS AMHERST
## MANNING COLLEGE OF INFORMATION AND COMPUTER SCIENCE
### CICS 210 DATA STRUCTURES
**✦ PROGRAMMING ASSIGNMENT 7 – PA07 – RECURSION AND INFORMATION RETRIEVAL ✦**


## Overview

A two part assignment this week. First, you'll practice writing some standalone recursive methods. 

Then, you'll combine a topic from the recent past -- the use of comparators -- with the more recent idea of using sets and maps -- to implement parts of a simple information retrieval engine. Maybe you've heard of this little startup called Google? Part of their initial claim to fame was their [web search engine](https://en.wikipedia.org/wiki/Web_search_engine), which let users quickly find web pages of interest based on a list of search terms. A full web search engine relies on a stable of different kinds of software.

In this assignment, you'll be building a simplified part of a [search engine](https://en.wikipedia.org/wiki/Search_engine_(computing)), consisting of a document [indexer](https://en.wikipedia.org/wiki/Inverted_index) and a system for determining [documents' relevance to a specific search term](https://en.wikipedia.org/wiki/Tf-idf). We've provided an outline for the search engine, but it will be your job to fill it in.

We've provided a set of unit tests to help with automated testing. **But unlike previous assignments**, the Gradescope autograder is running slightly different tests that you **do not** have access to. If your code can pass the tests we've provided, it is likely correct, but the Gradescope tests are the final arbiter. Read the note further down if your code is working locally but failing one or two of the Gradescope tests.

## Goals

- Translate written descriptions of behavior into code.
- Practice implementing recursive methods.
- Practice representing state in a class.
- Practice interacting with the `Map`, `Set`, and `List` abstractions.
- Practice writing a nontrivial `Comparator`.
- Test code using unit tests.

## Downloading and importing the starter code

As in previous assignments, download and decompress the provided archive file containing the starter code. Then import it into Code in the same way; you should end up with a `recursion-and-ir-student` project in the "Project Explorer".

As usual, look over the files we've provided. The `support/` directory (which contains files you should *not* modify) contains the `Node` class you've seen in lecture. The `src/` directory contains a single file with four methods for you to implement. Finally, the `test/` directory contains tests of the desired behavior.

## Recursion and What to Do
 
First, look in `src/recursion`.

The methods are all self-contained. Read the Javadoc (carefully!) to understand what we're asking, and refer to the JUnit tests which also serve as examples of the methods' intended behaviors. The autograder will not check that you use recursion (rather than iteration)...but these are the sorts of methods we will ask you to write recursively (on, say, quizzes and exams) so we recommend you use this assignment to practice that skill!

## Information Retrieval and Search Engine Behavior

For the purposes of this assignment, a search engine is a stateful object that "knows about" a set of documents and supports various queries on those documents and their contents.

Documents are identified by a unique ID and consist of a sequence of terms. Terms are the *lowercase* version of words; operations on the search engine and documents are therefore case-insensitive. Documents are added one-by-one to the search engine; the search engine's internal state is updated each time a document is added.

The search engine functions as an index. That is, given a term, the search engine can return the set of documents (that previously were added to it) that contain that term.

The search engine can also find a list of documents (again, from among the set it knows about) relevant to a given term, ordered from most-relevant to least-relevant. It does so using a specific version of the [tf-idf](https://en.wikipedia.org/wiki/Tf-idf) statistic, which sounds intimidating but is not too bad to calculate efficiently --- so long as you have the data structures to support doing so.

## What to do

First, look at the files in `src/index/`, `src/comparators`, and `support/documents/`.

The `SearchEngine` needs to keep track of the documents for two things: to do index lookups of terms, returning a set of documents (in `indexLookup`), and to compute the two components of the tf-idf statistics (in `termFrequency` and `inverseDocumentFrequency`). You can hold this state with whatever data structures you like, but my (strong!) suggestions follow.

I suggest you get `addDocument` and `indexLookup` working first. To support the index, a straightforward mapping of terms to sets of `DocumentID`s will work. (To be clear: a `Map<String, Set<DocumentId>>`). Whatever you decide, declare the structure(s) as instance variables, create the empty structure(s) you'll use in the constructor, fill it/them in `addDocument`, and examine it/them in `indexLookup`. 

How do you read in the document? I suggest a `Scanner`:

```java
Scanner s = new Scanner(); // pass in the document's contents, of course
s.useDelimiter("\\W+"); // set the Scanner to break up words, splitting on all non-word characters as required by the assignment
```
This is similar to the use of `String.split` in assignment 2. Remember `toLowerCase()` the words!

**Again, because this is important:** Read in a document and populate the structure(s) you need (the `Map`s) once, only, in `addDocument` -- do all your indexing and counting **only once**. Then, all other methods query the `Map`s which already have the answers you need to compute their results -- you do not need to (and should not) be re-reading the documents in any other method!

`termFrequency` requires that you compute the number of times a given term appears in a given document. This suggests you should have a data structure that keeps track of the count of terms per document: a `Map<String, Integer>`. But this frequency-counting structure is per-document; you need to keep track of each document's counts of terms. 

So, I suggest a `Map<DocumentId, Map<String, Integer>>`. The outer map goes from `DocumentId`s to the inner string-frequency-counting structure. 

To make this work, you'll have to update `addDocument` to populate and update these structures. Be sure to get clear in your head the different times you'll use `get`, `put`, `containsKey`, and `getOrDefault` before you start writing code!

Once you have the structure described above, you can use it to implement `inverseDocumentFrequency`. Be sure to read the javadoc comment above the method for the exact equation the tests are expecting. Use `Math.log` to compute the logarithm (not `Math.log10` or `Math.log2`).

Use these two methods to compute a given document-term pair's `tfIdf`.

Be careful about `int`eger division -- it truncates! Remember to cast to `double` where appropriate to avoid this behavior.

Finally, implement `relevanceLookup`, which returns a list of all documents containing a given term, sorted from largest tf-idf to smallest. You'll need to implement `TfIdfComparator.compare`. Make sure it returns a value that will result in the list being sorted largest-to-smallest, and remember the tie-breaker requirement. **The Gradescope tests check the sorting and tie-breaker more thoroughly than the tests we provide to you.** If you are failing the Gradescope tests but passing all tests locally, check your comparator's logic!

## Submitting the assignment

When you have completed the changes to your code, follow the same steps as from Assignment 01 to produce a `.zip` file, and upload it to Gradescope.

Remember, you can resubmit the assignment as many times as you want, until the deadline. If it turns out you missed something and your code doesn't pass 100% of the tests, you can keep working until it does.

## Other notes

### Mutators and observers

Again: The only place where your `SearchEngine`'s state (its instance variables: `Map`s and so on) will be *modified* (mutated) should be `addDocument`. The other methods (`indexLookup`, `termFrequency`, and so on) are *observers*: they should return results based upon the things stored in your `SearchEngine`'s state, but they should *not* change its state.

### On Stringly-typed variables

You may have noticed that `DocumentId` is a very thin wrapper around the `String` class. Why bother? Why not just use a `String` for the document's ID?

Well, you could. Doing so is called `String`ly typing (a pun on "strongly" typing) your program. Sometimes we do this when it's perhaps more trouble than it's worth to declare a new type.

But then you lose one of Java's strengths, which is its strong static (that is, compile-time) [type system's](https://en.wikipedia.org/wiki/Type_system) ability to help you find errors. If we had just used `String`, I can guarantee that at least a third or so of the class would have accidentally used a document ID where they meant to use a term (or vice versa) in their code. This mistake would result in hard-to-track-down errors at runtime. But making a different type for document IDs prevents this error. Further, it aids in code readability, as it is now unambiguous what, say a variable in a loop is for when it's a `DocumentId` and not just a `String.`

The major downside is that Java makes declaring what amounts to a type alias very verbose. In many other statically-typed languages this ends up being a single line (compare with [structs](https://doc.rust-lang.org/book/ch05-00-structs.html) and [enums](https://doc.rust-lang.org/book/ch06-00-enums.html) in Rust or user-defined types in [OCaml](http://www2.lib.uchicago.edu/keith/ocaml-class/userdefined.html)). If you want to go into the Java rabbit hole, you can use `record`s to do a simple version of this for types that just hold other data, but I didn't want to add Even More Java syntax to this class.
