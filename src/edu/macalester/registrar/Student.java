package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Charles Park
 * COMP-225 Registrar Homework #1
 */
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
    public boolean enrollIn(Course course) {
        if(course.getStudents().size() < course.getEnrollmentLimit())
            courses.add(course);
        if(!course.enroll(this)) {
            System.out.println(this.getName()
                    + " will automatically be placed on the wait list for the course "
                    + course.getTitle()
                    + ".");
            return false;
        }
        return true;
    }

    public void drop(Course course) {
        courses.remove(course);
        course.drop(this);
    }
}