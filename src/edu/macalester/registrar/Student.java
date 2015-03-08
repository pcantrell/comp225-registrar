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
    public void enrollIn(Course course) {
        //System.out.println(course.getStudents().size());
        //System.out.println(course.getEnrollmentLimit());
        if(course.getStudents().size() < course.getEnrollmentLimit()) {
            courses.add(course);
            course.enroll(this);
        } else if (!course.getStudents().contains(this)) {
            System.out.println("**************************************************");
            System.out.println("***Student: Attempting to over-enroll a course!***");
            System.out.println("**************************************************");
            System.out.println(this.getName()
                    + " will automatically be placed on the waiting list for the course "
                    + course.getTitle()
                    + ".");
        }

//        courses.add(course);
//        course.enroll(this);

    }
}
