package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
// import java.lang.Exception;


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

    public void dropCourse(Course course) {
        if (course.getStudents().contains(this)) {
            courses.remove(course);
            course.dropStudent(this);
            course.resolveWaitlist();    
        }
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public void enrollIn(Course course) {
        if (!course.isFull()) {
            courses.add(course);
            course.enroll(this);
        } else if (!course.getStudents().contains(this)) {
            course.addToWaitList(this);
            System.out.println("Student added to wait list for " + course.getCatalogNumber());
        }
        
    }
}
