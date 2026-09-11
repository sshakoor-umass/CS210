# UNIVERSITY OF MASSACHUSETTS AMHERST
## MANNING COLLEGE OF INFORMATION AND COMPUTER SCIENCE
### CICS 210 DATA STRUCTURES
**✦ PROGRAMMING ASSIGNMENT 2 – PA02 – HAMSPAM ✦**


## Overview

Welcome to your second programming assignment in CICS 210! This task is designed to deepen your familiarity with the coding environment you'll be using throughout this course. In this assignment, you will focus on modifying the `HamSpam` class to implement methods that determine the correct output for a sequence of numbers based on specific rules. Your objectives are to download the starter code,  apply your coding skills to solve the problem, and then upload your solution to Gradescope. This exercise will help you get accustomed to managing and navigating projects within our coding environment, sharpening your problem-solving skills and preparing you for more complex programming challenges ahead. By the end of this assignment, you'll be more comfortable with the tools and workflows that are essential for success in upcoming projects.

## Goals

- Import a project into VSC.
- Execute code within VSC.
- Run tests within VSC.
- Write code within VSC.
- Export a project from VSC and submit it to the autograder.

## Some reminders

 1. Start this (and future) assignment(s) early. It is your responsibility to start early enough that you can get help if you have trouble!

 2. This is the second programming assignment, and the first "real" one in the sense that it requires a nonzero amount of algorithmic thinking and familiarity with Java to complete.

 3. Do not expect much, if any, partial credit if you submit a program that does not compile or contains an infinite loop.  Errors of these sorts in your project will cause the autograder to fail, and you will receive a zero for your submission.

 4. We do our best to check the assignments and autograder. But, if you think something is wrong with the autograder or assignment (as opposed to your submission), contact the course staff immediately. Capture a screenshot of the error, and export a copy of your assignment in the state that triggers the error. Providing these to us will help us immensely in narrowing down the problem.

 5. Late submissions will be accepted up until the "late deadline" (but not afterward!), and consume a corresponding number of your late days, even if they get a zero! Read the syllabus for details.


## Downloading and importing the starter code

Download the starter code zip file as you did in the first assignment, expand it if needed, and open the folder in VS Code. Consult the first assignment's directions if you need help, or come to office hours or ask on Piazza if you get stuck.

You should see a ``hamspam-student`` folder in the Explorer window on the left. 

Folder are represented by small arrows pointing down for folders whose contents is shown, and to the right for folders whose contents is hidden. Click on the folder name to toggle whether its contents is shown or not. In particular, click on `src`,  and then `main`, then do the same for `test` and `main`.

You should see three Java source files among the folders: `HamSpam.java`, `HamCommander.java`, and `HamSpamTest.java`. You can open them by clicking on them. Do so, and take a look through each of them. You'll see that they involve a game about "Ham", "Spam", and numbers, and that VSC has detected errors.

## `src` and `test`

In this (and all future) projects, you'll see these directories. We mentioned them in the first project -- let's say a few more words now.

- `src` is where files you might modify will be located, as well as files you should not modify. For files you can edit, you can do *almost* anything you want -- in particular, you can write new code and add new methods. But you *cannot* change the "signature" of methods that we've provided to you -- you must keep their scope, names, parameters, and return types as written. (This is a limitation of the autograder and of Java -- methods with different signatures are different, even if they have the same name!) Some files, like `HamCommander.java` are explicitly marked as not-editable. In this assignment it won't actually break anything to edit this file, but in future assignments changes to these marked files may cause tests to pass locally but fail on the autograder (or worse, for your files to compile locally but not on the autograder). VSC is configured by the project to not allow you to edit these files -- override this setting at your peril!
- `test` contains "unit tests." We'll talk more about Java automated testing in class and lab, but in short, these are short code snippets that check an "expected" (that is, correct) value is returned by your program's code (called the "actual" value). Hopefully you've seen these already in both Python and Java in CICS 160 or the equivalent! VSC and many other tools have the built-in tools to automatically run tests and tell you whether your code is "passing" them or not, and that's also how the autograder works.

## On Ham and Spam

"Ham and Spam" is a children's counting game. Before it is played, the players agree on a *ham number* and a *spam number*. Both are integers greater than one, and they cannot be the same number. The players then take turns saying the *hamspam* value for each successive integer, starting at one. Usually, the hamspam value is just the number. But:

  -  If the number they are supposed to say is divisible by the ham number and not the spam number they say "ham" instead of the number.
  - If the number they are supposed to say is divisible by the spam number and not the ham number they say "spam" instead of the number.
  - If the number they are supposed to say is divisible by both the spam number and the ham number they say "hamspam" instead of the number.

For example, if the ham number is three, and the spam number is four, then the first twelve hamspam values are:  
`1, 2, ham, spam, 5, ham, 7, spam, ham, 10, 11, hamspam`

For this assignment, you are going to modify the `HamSpam` class provided to produce the correct value or values for a game of "Ham and Spam".

## Using VS Code

Let's walk through using VSC to identify and fix errors, run code, and run unit tests.

**Finding and fixing compilation errors.** You can see that VSC has highlighted the `HamSpam.java` in red. The red underlines in the source for `HamSpam.java` indicate there's an error; if you hover the mouse pointer over that line, you'll see that "The blank final field hamNumber may not have been initialized."

In other words, the instance variable `hamNumber` is declared (`private final int hamNumber`) but never set to a value if the class is instantiated. Your lazy instructor didn't even provide you with code that compiles!

Fix the error by modifying the constructor of `HamSpam` to set the instance variable `hamNumber` to the appropriate argument of the constructor. (A reminde, if it's been a minute since you last wrote some Java: when a local variable, such as the argument to the constructor, has the same name as an instance variable, you can disambiguate the instance variable by including the `this.` qualifier. In other words, you can write `this.hamNumber = hamNumber;` in the constructor to set the instance variable to the argument passed to the constructor.) 

Then fix the same error for `spamNumber`.

Each distinct instance of `HamSpam` may have a different pair of ham and spam numbers. For example, an instances with a ham number of four and a spam number of five is created when a program calls `new HamSpam(4, 5)`). These values are tracked in these instance variables. When you open `HamSpamTest.java` you'll see that more than one instance of `HamSpam` is created, each with its own ham and spam numbers.

**Running programs.** Once you've eliminated errors, you can run the program. But you'll note there's no `main` method in `HamSpam.java`. Instead, open `HamCommander.java` and take a look at its `main` method. To run it, right-click on `HamCommander.java` in the left-hand side of the window , then choose "Run" from the context menu. A "Terminal" showing the "Java Process Console" will appear in the bottom of the VSC window. Follow along, pressing Enter after each number you enter, to see the (sometimes incorrect) results of the current implementation of Ham and Spam.

If you want to check your implementation of HamSpam, you can do it this way. But now we'll look at automated tests, which provide a faster way to more reliably repeat the same set of checks each time you run them.

**Running tests.** Have the file "HamSpamTest.java" visible in your editor, and then open the "Command Palette" using the "View" menu. (You can also use a keyboard shortcut to do so; on a Mac, for example, it's Command-Shift-P.) You are looking for an item labeled "Java: Run all tests". To find it quickly, type the word "tests" to narrow the listed choices. Choose the item and run it by clicking on it or pressing "Enter" when it's selected. (Or, use the method we showed you in the previous project -- up to you!)

Several things will now happen. VSC will run all the tests; some will pass, and some will fail. VSC will jump to one of the failing tests and show its results in the editor. A "TEST RESULTS" tab wil be added to the bottom pane, alongside whatever other tabs are there (typically some combination of "TERMINAL", "PORTS", "PROBLEMS", and "OUTPUT".)  If you click "TEST RESULTS" you can navigate a list of the tests and their results.

You can also view tests more generally through the "Testing" pane, which you can find by clicking the "Testing" icon on the left side of the interface -- it looks like a flask, and is usually below the "Extensions" icon, which looks like three connected squares and a fourth in the upper-right slightly removed.

When you do this, the left-hand side will witch to the testing interface. You should see a total of fourteen tests: four tests that pass, and ten tests that fail. If you don't see them, click the small arrow to the left of "hamspam-student" to expand it -- repeat this process with each revealed item until you see the list of tests -- or change to the "List View" using the "..." icon.

Familiarize yourself with the testing interface: You can filter by test name, or you can click the funnel icon to limit the list to only failing tests; you can click the icon to the far right of each test to jump to the test's source code (it looks like a piece of paper with an arrow pointing to it); you can click the name of each test to see the "stack trace" (that is, the currently-executing methods when the test failed).

For example, if you select `testHamAndSpamGetValue`, you should see the following under Failure Trace:

```
org.junit.ComparisonFailure: getValue returns incorrect value expected:[[hamspam]] but was:[[12]]
 at hamspam.HamSpamTest.testHamAndSpamGetValue(HamSpamTest.java:47)```
```

...and much more of the stack track, but it's typically only the top line or two that will be relevant.

JUnit tests work by checking that the expected result of a method call equals the actual result. The failed test indicates that one (or more) methods in the starter code aren’t returning correct values. Examine the test; again, click on the icon to its right, or navigate to it manually in `HamSpamTest.java` around line 46.

Here, you can see that the test case expects the result of `hamspamThreeFour.getValue(12)` to be the string `"hamspam"`, but as the output in the Failure Trace indicates, it produced the string `"12"` instead.

**Diagnosing and fixing problems.** Go to the declaration of `getValue()`; you can do so by right-clicking on it in `HamSpamTest.java` and selecting "Go to Definition", or by double-clicking on `HamSpam.java` in the Package Explorer and scrolling to it.

Your goal is to correct the implementation of the `getValue()` method. The correct solution is not merely to make the function return "hamspam" when `n == 12`. Instead, you should revise the code so that the function will return the correct string for any `n`. 

There is at least one thing to fix. The `getValue()` method currently checks for equality against numbers, not divisibility. You can use the remainder (sometimes people say "mod" or "modulo") operator (`%`) to check for divisibility. It divides one number by another and returns the remainder. For example, if you wanted to print out whether the value of a variable `n` was divisible by three, you could write:

```java
if ((n % 3) == 0) {
  System.out.println(n + " is divisible by 3");
else {
  System.out.println(n + " is not divisible by 3");
}
```

Notably, the value `((n % 3) == 0)` will evaluate to `true` if and only if `n` is divisible by three. You can use this fact in your implementation of `getValue()`. One straightforward correct implementation will involves three `if` statements. Post a question on CampusWire or come chat with us during office hours if you get stuck.

**Finishing up.** Next, you'll need to write `getValues()`, which will allocate and return an array of `String` containing the correct hamspam values for the range. Think carefully about what you need to do: create and return an array of the right size with the right values. Break this down into several parts: How big should the array be? Then, how do you put the right values into the right places of the array? That second part may require you break things down into further steps. Calculate the number of values, initialize an array of the appropriate size, iterate and assign values to the array, and then return that array.

When we autograde your program, we will test both `getValue()` and `getValues()` with other ham and spam numbers to make sure each works properly. Our test cases will obey the constraints described in this assignment (for example, the ham number and spam number will always be greater than one, and never be equal to one another), but are otherwise unconstrained. 

In this assignment, we've given you all the test cases. But in future assignments, there are test cases we are going to use that you won't be able to examine for yourself. You will have to think about the problem and make sure you're not missing any details or corner cases if you want to pass them. (We'll teach you to write tests soon, but there's no magic: copy/paste and edit an existing test to get 90% of the way there -- it's the same as in Python.)

You can also use the `HamCommander` class for interactive testing. But as you'll see, just being able to "Run tests" quickly and repeatedly is much more convenient, especially as programs grow more complex.

## What not to do

(I apologize for the tone of the following, but I need to be absolutely clear about something that is not permissible.)

This is the first assignment where you may be tempted to game the autograder, as many of the `static` methods are standalone "functions," with well-defined behavior that depends only upon their input. Don't do it.

What do I mean by gaming the autograder? Since we are still giving you the tests, you may be tempted to write code that is not general, but that does pass the tests. Put another way, you may be tempted to hard-code the "correct" answers to the tests (that won't work on other inputs!) into your methods via a series of if/then/else statements that work on very specific values (like ham=2, spam=3) but fail for others.

**Do not take this approach.** We expect you to write *general* solutions to the assignments, not just attempt to pass the tests. We give you the tests to help you learn (though note that later in this class, and in life generally, you won't always get the tests handed to you), not to let you pattern-match `if-then-else` statements on the test cases. 

Doing so is gaming the autograder. We will treat it as academic dishonesty and lobby for the maximum possible sanction -- at least an F in this course, if not an academic suspension.

If you are having trouble understanding the assignment or need help, please ask! But don't game the autograder.

## Another things to perhaps not do

You might also be tempted to use Copilot or other AI tools to solve this assignment. To be frank: Copilot (and ChatGPT, Claude, etc.) eat introductory CS assignments, especially ones with well-written comments (like this one), *especially* ones where a solution is probably already on GitHub somewhere (like this one), for breakfast.

Should you use Copilot or the like to solve this assignment? You could. We're not going to formally consider it cheating. But you are responsible for knowing the things you need to know to complete this assignment or things like it (on, say, quizzes or exams), **including Java syntax and commonly-used methods and classes from the Java standard library**. If Copilot helps you get there, great. But if you use it as a magic genie to solve the assignment, do keep in mind it's not going to be there for you on the quizzes or exams, so you'll want to *understand and remember* what it's doing. And if it makes subtle mistakes in logic or semantics -- which it probably won't on simple assignments like this one, but may later -- that'll be on you to figure out. And again, we'll expect you to be able to do so on quizzes and exams yourself. We've configured an `AGENTS.md` file in each project to encourage AI assistants to "do the right thing" and act as teachers, not solvers -- but this is an imperfect guardrail and won't guarantee the AI won't just spoon-feed you the right answer, circumventing the learning process.


## Submitting the assignment

When you have completed the changes to your code, you should create the `submission.zip` file as you did in the previous assignment. (You can also make the `zip` file yourself with another tool, if you prefer, or just upload the files directly).

Next, log into Gradescope, select this assignment, and submit the file for grading. You'll see the contents of the `.zip` file in the upload window after you select it.

Compilation errors will be provided by the autograder, but the exact details of the tests we run on your code might not be provided in Gradescope. For this assignment, the tests you have locally and the tests run on Gradescope are the same, but this won't always be the case. You must read assignments carefully -- if your submission is not passing a test, it is almost certainly because your submission doesn't match the requirements of the assignment.

Remember, you can resubmit the assignment as many times as you want, until the deadline. (Make sure you upload the right version!) If it turns out you missed something and your code doesn't pass 100% of the tests, you can keep working until it does. 

The last submission you make before the due date is considered as your final submission for grading. Ensure that this submission is your best effort and fully complete. Any submissions made after the due date are marked as late and might be subject to penalties as per the course's late submission policy. If you wish to change which submission is considered for grading, you can do so by accessing the "Submission History" on Gradescope.