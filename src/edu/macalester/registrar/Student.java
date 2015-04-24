package edu.macalester.registrar;

import java.util.*;


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
        if (courses.isEmpty()) {
            return Collections.emptySet();
        }else {
            return Collections.unmodifiableSet(courses);
        }
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public boolean enrollIn(Course course) {
        if (course.getStudents().contains(this)){
            return true;
        }
        if (course.getWaitList().contains(this)){
            return false;
        }
        if (course.getStudents().size()>=course.getEnrollmentLimit() && !this.getCourses().contains(course)){
            /* throw new IllegalArgumentException("Class is at capacity!");
             */
            System.out.println("Class is at capacity," + this.getName()  + " will automatically be added to the wait list");

            course.addToWaitList(this);
            return false;
        }else {
            courses.add(course);
            course.enroll(this);
            return true;
        }
    }

    public void drop(Course course) {
        if (course.getWaitList().contains(this)){
            course.removeFromWaitList(this);
        }
        if (course.getStudents().contains(this)){
            courses.remove(course);
            course.dropStudent(this);
        }


    }
}
