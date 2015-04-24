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
    public boolean enrollIn(Course course) {

        if (courses.contains(course)) {
            return true;
        }
        else {
            if (course.enroll(this)) {
                courses.add(course);
                if (waitlistCourses.contains(course)) {
                    waitlistCourses.remove(course);
                }
                return true;
            } else {
                if (!waitlistCourses.contains(course)) {
                    waitlistCourses.add(course);
                }
                return false;
            }
        }
    }

    /**
     * Allows a student to drop a class.
     * If the student is not enrolled in the course, print the error message.
     * If the student is enrolled/waitlisted, remove the course from the course list,
     * and call the drop method from the Course class.
     */
    public void drop(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            course.drop(this);
        } else {
            System.out.println(name + " is not enrolled in " + course.getCatalogNumber());
            if (waitlistCourses.contains(course)) {
                waitlistCourses.remove(course);
                course.waitListDrop(this);
            } else {
                System.out.println(name + " is not waitlisted in " + course.getCatalogNumber());
            }
        }
    }
}
