package edu.macalester.registrar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */

    public void enrollIn(Course course) {
        if (course.getStudents().size() < course.getEnrollmentLimit()) {
            courses.add(course);
            course.enroll(this);
        } else {
            course.waitListEnroll(this);
            System.out.println("Sorry, '" + course.getTitle() + "' is full! " + this.getName() + " has been added to the wait list.");
            System.out.println();
        }
    }

    public void drop(Course course) {
        Set enrolled = course.getStudents();
        Student firstOnWaitList = course.getWaitList().get(0);
        if (enrolled.contains(this)) {
            course.drop(this);
            System.out.println();
            System.out.println(this.getName() + " has dropped '" + course.getTitle() + ".' " + firstOnWaitList.getName() + " was first on the wait list, so " + firstOnWaitList.getName() + " has now been enrolled. ");
            System.out.println();
            course.enroll(firstOnWaitList);
            course.getWaitList().remove(firstOnWaitList);
        }
        else {
            System.out.println();
            System.out.println("Don't worry! " + this.getName() + " is already out of that course.");
            System.out.println();
        }


    }
}
