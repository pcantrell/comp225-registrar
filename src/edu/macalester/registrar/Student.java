package edu.macalester.registrar;

import org.omg.IOP.ExceptionDetailMessage;

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
            course.enroll(this);
            courses.add(course);
            return true;
        }
        else {
            return false;
        }

    }

    public void drop(Course course){
        if (this.courses.contains(course)){
            this.courses.remove(course);
            course.dropStudent(this);
        }

    }

    @Override
    public String toString(){

        return this.name;

    }
}
