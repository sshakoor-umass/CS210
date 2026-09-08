## STEP 6: RUN THE STARTER CODE

Running your Java code in VSCode is straightforward, enhancing the development process by allowing you to execute the code you're working on. Since Java is a compiled language, you must first compile the source code into bytecode and then run this bytecode on the Java Virtual Machine. Fortunately, VSCode simplifies this into a single step. The instructions below will guide you on how to efficiently compile and run your programs, equipping you with the skills needed for future assignments:

### TASKS TO COMPLETE

1. **Open the src/main/java/hello/Hello.java** file from the file explorer by double-clicking it. This program contains two static methods: `helloString()` and `main()`. Execution starts with the `main()` method.

2. **Notice the "Run | Debug" label** above the main method, which is added by the Java Extensions and not part of the actual code. You can click "Run" to compile and execute the program, or "Debug" to execute it in the debugger.

3. **Alternatively**, you can execute the program by clicking the "Play" button located on the top right menu bar, as long as your open Java file contains a `main()` method.

You will notice several other files and folders in the Explorer. The folders and files that you need to care about are as follows:

- `README.md`: This file! In every project, this file will contain the directions for the project.
- `src/main`: A directory containing one or more Java "packages" (which are themselves directories). The source code you need to read, understand, and modify to complete the assignment lives here; any files you should not modify are labeled as such (and VS Code will recognize them as read-only, also).
- `src/test`: The "unit tests" which we provide to you to aid in your work. Generally, these are a subset of the tests that run on the Gradescope autograder. Sometimes, the autograder will run additional tests you will not have access to!
- `AGENTS.md`: Guardrails for an AI agent, if you choose to use one, that will help (but not totally prevent) it from depriving you from learning to code.
- `config.ini`: the project configuration file that lists the ".java" files you'll need to submit to Gradescope -- but we provide a tool to help gather them, so you do not need to do so manually yourself on later projects with multiple files to submit! More about that in a moment.

## STEP 7: RUN TESTS AND MODIFY CODE

Now that you know how to run your code, we want to run the included tests to see if the code is correct. You need to click on the "Testing" button (it looks like a Test Beaker) on the left-hand palette. This will bring up the "Test Explorer".

First, click the three dots ("...", which if you hover the mouse pointer over it will read "Views and More Actions...") at the top of the pane, click, and choose "View as List". 

The next step is to run the tests. You can hover over the buttons in the "Testing" pane -- by default, the third one from the left, which looks like a triangle atop another tringle, is the "Run Tests" button. Click it. Or, if you want to run just a single test (and this assignment has only one), you can hover over the test name itself, which causes the "Run Test" button (which again, looks like a triangular "Play" button) to appear for just that test, and click it.

When you run the test, you will notice that the test fails.

There are several ways of looking at the test that fails. You can navigate to the test folder and open the test file. Or,  you can right-click on the line in the bottom right panel (under "Test Result") that starts with "Expected ..." and select "Go to Test". 

When you click on "Go to Test" you can see that the problem is that the test expected the `String` `"Hello, CICS 210 Data Structures World!"`, but our program returned `"Hello, CICS 210 Data Structures"`. Clicking on the red label in the test file shows you the difference as well. The next step is to modify the code to fix the problem.

## STEP 8: MODIFY THE CODE

Now that we know where the problem is, we can fix it by going to the code in **src/main/java/hello/Hello.java** and modifying the `helloString` method to return the correct `String`. Make the change -- add the `" World!"` text to the `String -- and re-run the tests. If you did this step properly, you should see green check marks next to the tests in the Test Explorer. If not, look carefully and see what you might have missed. Feel free to ask course staff for help!

## STEP 9: BUNDLE YOUR WORK

To bundle your project files for submission, we're going to use the underlying build tool (Gradle) that VS Code is already using to build your code behind the scenes. Follow these steps:

### TASKS TO COMPLETE

1. **Prepare Your Project**
   - Ensure your project folder is open in VSCode and contains all necessary files for submission.
   - In this and future programming assignments you should make sure that all your code files have been saved and are ready for submission -- if you don't save before submitting, the most recent changes you've made won't be in the zipfile you submit, which will be confusing!

2. **Create the submission.zip File**
   - With your project open in VSCode, go to the **Terminal** menu, then choose > Run Task > "Make submission zip."

This will create a `submission.zip` file, ready for submission to Gradescope. You should follow these steps each time before you submit to Gradescope. Ensure everything looks correct before proceeding!

Bonus information: If, instead of the above, you want to run the Gradle task via VS Code's Gradle extension panel (the Gradle elephant icon in the Activity Bar) then:

 - Click the Gradle icon in the left Activity Bar.
 - Expand your project, then Tasks > Other.
 - Click the play arrow next to makeSubmissionZipfile.
 - Watch the terminal for Wrote .../submission.zip.

...but the menu item we added above ("Make submission zip") does this for you in one step.

## STEP 10: SUBMIT TO GRADESCOPE

Now that you've created your **submission.zip** file, it's time to submit your work to Gradescope, our trusty autograder. Follow these steps to ensure a successful submission:

### TASKS TO COMPLETE

1. **Access Gradescope**
   - Gradescope will appear at the bottom of this assignment in Canvas. It will display the submission page so you can easily submit your work.
   - If you access Gradescope outside of Canvas, locate the course which should be named something like "CICS 210 Data Structures" from your Gradescope dashboard.
     - Locate the assignment named **PA01 Welcome** and click on it.

2. **Upload Your Zip File**
   - On the assignment page, click the **Submit** button.
   - Drag and drop your **submission.zip** file into the upload area or click to browse your computer for the file.

3. **Confirm Your Submission**
   - Verify that the uploaded file is named **submission.zip**.
   - Double-check that the file size and upload confirmation match your expectations (Gradescope will display a success message after uploading).

4. **Run the Autograder (if applicable)**
   - Since the assignment includes an autograder, Gradescope will automatically evaluate your submission.
   - Check the feedback and ensure everything is correct. If there's an error, review the feedback and fix your project as needed before resubmitting. You may resubmit as many times as allowed within the deadline -- there is no penalty for multiple submissions before the deadline, but only the most recent one will be considered as your "final" submission.

5. **Verify Your Submission**
   - Return to the assignment page and ensure your latest submission appears in the submission history.
   - Gradescope will email you confirmation that it has received your assignment.

💡 **Pro Tip**: Always submit well before the deadline to avoid last-minute issues like slow uploads or unforeseen errors. "I had network problems" or "the Internet was slow" or the like will not avoid the use of a late day.

Once submitted and verified, you're done! Congratulations on completing your first assignment setup, you're now ready to tackle the programming challenges ahead.