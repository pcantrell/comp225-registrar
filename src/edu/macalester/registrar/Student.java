package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Student Name: Qinghao Peng
 */

public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();
    private Set<Course> waitlistCourses = new HashSet<Course>();

    /*
        Getters and setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public Set<Course> getWaitlistCourses() {
        return Collections.unmodifiableSet(waitlistCourses);
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     * If the course is full, the student is added to waitlist.
     */
    public void enrollIn(Course course) {
        if (course.enroll(this)) {
            courses.add(course);
            // make sure the student is both enrolled and waitlisted
            if (waitlistCourses.contains(course)) {
                waitlistCourses.remove(course);
            }
        } else {
            waitlistCourses.add(course);
        }
    }

    /**
     * Allows a student to drop a class.
     * If the student is not enrolled in the course, print the error message.
     * If the student is enrolled, remove the course from the course list,
     * and call the drop method from the Course class.
     * @param course
     */
    public void dropCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            course.drop(this);
        } else {
            System.out.println(name + " is not enrolled in " + course.getCatalogNumber());
        }
    }
}
