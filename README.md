# A Modeling Exercise

This repository contains the Student and Course classes we developed in class. You will add some new behavior to them.

The learning goals of this exercise are:

* **Object modeling**: representing concepts as object-oriented code.
* **Programming by contract**: writing code that satisfies logical constraints.
* **Defensive programming**: writing code that actively prevents other code from using it incorrectly.

## Rules of the exercise

* **This is not a group assignment.**
* You are allowed to use only the following types for the arguments and return values of the public methods you add:
  * primitives (int, long, char, etc.),
  * String and Date,
  * Java Collections API interfaces (List, Set, Collection, Map), and
  * your other model classes.
* Use strict defensive programming: make it impossible to get any model object into an illegal state using public methods.
* Exercise any changes you make in `RegistrarTest` (so that the test scenario grows).

## Your starting point

No matter what public methods other code calls or what data it passes to them, the model classes currently always satisfy the following invariant condition:

> For all students and courses, `student.getCourses().contains(course)` if and only if `course.getStudents().contains(student)`.

Your mission is to add the following behavior.

## Part 1: Enrollment Limit

Give courses an enrollment limit. This adds the following invariant:

> For all courses, `course.getStudents().size()` ≤ `course.getEnrollmentLimit()`.

Any operation that would break this condition (e.g. attempting to overenroll a course) must fail, and it must signal the failure in a way that lets the calling code handle the error gracefully.

## Part 2: Wait List

Add a wait list to courses. When a student attempts to register for a course that is full, they automatically go on the wait list. This is not an error condition; however, the `enroll()` method should let the caller know whether the enrollment was successful or the student was waitlisted.

This new feature adds the following two invariants:

> A student is never both enrolled in and wait listed for the same course.

…and:

> If a course is not full, then its wait list is empty.

## Part 3: Drop

Give students the ability to drop courses. If an enrolled student drops, then the first wait-listed student is automatically enrolled. (That’s not realistic, of course, but it makes for a good exercise!)

## Bonus: Optional Limits

For extra credit, make courses impose no enrollment limit by default, but only if an enrollment limit is set. Make it possible to lift the enrollment limit after one is set.

## Before you’re done…

…three things:

* Make sure that _all_ of the invariants listed above still hold, even after all of your changes.
* Make sure that the `RegistrarTest` test scenario exercises your code enough to satisfactorily demonstrate that it works.
* Practice good hygiene. Make sure you code is tidy, legible, and free of waste.
