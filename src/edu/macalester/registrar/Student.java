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
    public String enrollIn(Course course) {
        String status = course.enroll(this);
        if(status.equals("enrolled")) {
            courses.add(course);
        }
        return status;
    }

    public void dropCourse(Course course) {
        courses.remove(course);
        course.dropStudent(this);
    }

    public boolean isEnrolledIn(Course course) {
        return courses.contains(course);
    }
}
