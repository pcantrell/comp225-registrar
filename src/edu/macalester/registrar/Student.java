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

    public boolean enrollIn(Course course) {
        boolean isEnrolled = false; //boolean is student enrolled?
        course.enroll(this); //attempt to enroll in course
        if(course.getStudents().contains(this)){ //if successful enrollment (i.e. not on waitlist)
            courses.add(course); //add course to course list
            isEnrolled = true; //set is enrolled to true
        }//else enrolled is still false
        return isEnrolled; //returns is enrolled
    }

    public void drop(Course course){
        course.drop(this);
        courses.remove(course);
    }
}
