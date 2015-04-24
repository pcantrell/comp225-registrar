package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Student {

    //  VARIABLE SETUP
    private String name;
    private Set<Course> courses = new HashSet<Course>();


    //  BASIC GETTERS & SETTERS
    //  get,set name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //  get courses
    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    
    //  ADVANCED METHODS
    //  enroll in a course
    public boolean enrollIn(Course course) {
        boolean enrolled = course.enroll(this);
        if (enrolled) {
            courses.add(course);
        }
        return enrolled;
    }

    //  drop a course
    public void drop(Course course) {
        courses.remove(course);
        course.drop(this);
    }
}
