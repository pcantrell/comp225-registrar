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

    public void drop (Course course){
        if (course.getStudents().contains(this)){
            courses.remove(course);
            course.dropStudent(this);
            course.updateWaitList();
        }
        else if (course.getWaitList().contains(this)){
            course.removeFromWaitList(this);
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
        else if (course.getWaitList().contains(this)){
            return false;
        }
        else if (course.getStudents().size() < course.getEnrollmentLimit()){
            courses.add(course);
            course.enroll(this);
            return true;
        }
        else if (course.getStudents().size() == course.getEnrollmentLimit()){
            System.out.println("The class is full");
            System.out.println("Adding " + this.getName() + " to the waitlist...");
            course.addToWaitList(this);
            return false;
        }
        return false;
    }
}
