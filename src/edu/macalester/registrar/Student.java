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

    public void dropCourse (Course course){
        if (course.getStudents().contains(this)){
            courses.remove(course);
            course.dropStudent(this);
            course.updateWaitList();
        }
    }
    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public void enrollIn(Course course) {
        if (course.getStudents().size() < course.getEnrollmentLimit()){
            courses.add(course);
            course.enroll(this);
        }
        else if (course.getStudents().size() == course.getEnrollmentLimit() && !(course.getStudents().contains(this))){
            System.out.println("The class is full");
            System.out.println("Adding " + this.getName() + " to the waitlist...");
            course.addToWaitList(this);
        }
        else if (course.getStudents().size() > course.getEnrollmentLimit()){
            System.out.println("If you're here, something has gone terribly wrong");
        }
    }
}
