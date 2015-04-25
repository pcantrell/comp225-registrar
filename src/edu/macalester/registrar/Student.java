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
        boolean gotEnrolled = course.enroll(this);
        if (gotEnrolled){
            courses.add(course);
        }
        return gotEnrolled;
    }

    public void drop(Course course) {
        if (this.courses.contains(course)){
            courses.remove(course);
        }
        course.drop(this);
    }
}
