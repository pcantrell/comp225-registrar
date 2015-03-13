package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();
    private Set<Course> waitListCourses = new HashSet<Course>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public Set<Course> getWaitListCourses() {
        return Collections.unmodifiableSet(this.waitListCourses);
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public void enrollIn(Course course) {
        if (!course.isFull()){
            if (!this.courses.contains(course)){
                courses.add(course);
            }
            if (this.waitListCourses.contains(course)){
                waitListCourses.remove(course);
            }
        }
        else if (course.isFull() && !this.courses.contains(course) && !this.waitListCourses.contains(course)) {
            waitListCourses.add(course);
        }
        course.enroll(this);
    }

    public void dropFrom(Course course) {
        if (this.courses.contains(course)){
            courses.remove(course);
        }
        else if (this.waitListCourses.contains(course)) {
            waitListCourses.remove(course);
        }
        course.drop(this);
    }
}
