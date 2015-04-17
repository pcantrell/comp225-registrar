# Part 2: Unit Testing

This builds on Part 1. I have written a set of unit tests. Use these to identify and fix problems in your implementation of part 1.

Your mission: **make the tests pass.**

Please note that the tests I am giving you are already correct. Your job is to make them pass as is, without modifications.

## Getting the tests

You will first need to pull my new tests into your fork of the project by syncing your fork. To do this, first configure my original resposity as your fork’s upstream repository:

    git remote add upstream https://github.com/pcantrell/comp225-registrar.git

Once you have done that, [follow these instructions](https://help.github.com/articles/syncing-a-fork/).

If you hit snags with this, ask for help. Don’t let yourself get sucked into a git snit.

## Running the tests

In IntelliJ, open the `test` directory (next to `src`). Right click on `RegistrarTest` and choose “Debug RegistrarTest.”

You can use something other than IntelliJ for running JUnit tests if you prefer, but I leave it to you to figure it out.

## Recommended approach

You will see a lot of test failures at first, and it will be overwhelming. Don’t despair! Here’s how I recommend proceeding:

* Fix all the compiler errors first. That will mean renaming some of your methods, making some of them public, and perhaps changing some of their return values.
* Once the compiler errors are fixed and the tests run, focus on one failing test at a time. JUnit runs your tests in an arbitrary order, but I recommend fixing the tests in the order they appear in `RegistrarTest.java`.
* You may find it useful to comment out all the test code between `Enrollment limits` and `Post-test invariant check` (lines 73–220 of the test file), then uncomment one test at a time.
* When you are done, double check that you haven’t modified `RegistrarTest.java` at all.

After all the tests pass:

* **Minimize member visibility,** i.e. make sure that no members of `Student` and `Course` are public that do not need to be public.
* **Clean up your code.** One of the pleasures of having good unit tests is that you can refactor with confidence. Once you have all the tests passing, try reorganizing your code to make it cleaner, clearer, and more elegant.

The previous assignment appears below for reference.

-------------

# Part 1: A Modeling Exercise

This repository contains the Student and Course classes we developed in class. You will add some new behavior to them.

This assignment is an introduction to:

* **Object modeling**: representing concepts as object-oriented code.
* **Programming by contract**: writing code that satisfies logical constraints.
* **Defensive programming**: writing code that actively prevents other code from using it incorrectly.

## Rules of the exercise

* **This is not a group assignment.** Please review the rules about collaboration vs. copying in [the syllabus](https://moodle.macalester.edu/mod/resource/view.php?id=18335).
* You are allowed to use only the following data types for the arguments and return values of public methods of model classes:
  * numeric types (int, long, char, Integer, etc.),
  * String and Date,
  * your own model classes (Student and Course), and
  * Java Collections API interfaces (List, Set, Collection, Map) containing the above types.
* Use strict defensive programming: make it impossible to get any model object into an illegal state using public methods.

## Your starting point

No matter what public methods other code calls or what data it passes to them, the model classes (Student and Course) you’re given here always satisfy the following invariant condition:

> For all students and courses, `student.getCourses().contains(course)` if and only if `course.getStudents().contains(student)`.

Your mission is to add the following behavior:

## Part 1: Enrollment Limit

Give courses an enrollment limit. This adds the following invariant:

> For all courses, `course.getStudents().size()` ≤ `course.getEnrollmentLimit()`.

Any operation that would break this condition (e.g. attempting to over-enroll a course) must fail, and it must signal the failure in a way that lets the calling code handle the error gracefully.

## Part 2: Wait List

Add a wait list to courses. When a student attempts to register for a course that is full, they automatically go on the wait list. This is not an error condition; however, the `enroll()` method should let the caller know whether the enrollment was successful or the student was waitlisted.

This new feature adds the following two invariants:

> A student is never both enrolled in and wait listed for the same course.

…and:

> If a course is not full, then its wait list is empty.

## Part 3: Drop

Give students the ability to drop courses.

If an enrolled student drops, then the first wait-listed student is automatically enrolled. (That’s not realistic, of course, but it makes for a better programming exercise!)

## Bonus: Optional Limits

For (modest) extra credit: Make courses impose no enrollment limit by default. Make it possible to lift the enrollment limit after one is set.

## Before you’re done…

…three things:

* Make sure that _all_ of the invariants listed above still hold, even after all of your changes.
* Make sure that the `RegistrarTest` test scenario exercises your code enough to satisfactorily demonstrate that it works.
* Practice good hygiene. Make sure your code is tidy, legible, and free of waste.
