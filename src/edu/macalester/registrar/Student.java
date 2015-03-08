package edu.macalester.registrar;

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
        //System.out.println(course.getStudents().size());
        //System.out.println(course.getEnrollmentLimit());
        if(course.getStudents().size() < course.getEnrollmentLimit()) {
            courses.add(course);
            course.enroll(this);
        } else if (!course.getStudents().contains(this)) {
            throw new IllegalArgumentException("Student: Attempting to over-enroll a course!");
        }

//        courses.add(course);
//        course.enroll(this);

    }
}
