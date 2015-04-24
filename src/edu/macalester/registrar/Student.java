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
        if (course.getStudents().size() < course.getEnrollmentLimit()) {
            courses.add(course);
            course.enroll(this);
            return true;
        }
        if (course.getStudents().contains(this)){
            return true;
        }
        if (course.getWaitList().contains(this)){
            return false;
        }
        else {
            course.addToWaitList(this);
            return false;
        }
    }

    public void drop(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
        }
        course.drop(this);
    }
}
