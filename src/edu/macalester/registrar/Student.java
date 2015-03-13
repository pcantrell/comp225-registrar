
//Clare Speer 
//March 13, 2015

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
        return courses;
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public void enrollIn(Course course) {
        if(course.getStudents().size() == course.getEnrollmentLimit()){
            System.out.println("Enrollment limit met in " + course.getTitle() + ". Student cannot be enrolled in the class.");
            course.addToWaitlist(this);
        }
        else{
            courses.add(course);
            course.enroll(this);
            System.out.println(name + " successfully enrolled in " + course.getTitle());
        }
    }
    
    public void dropOut(Course course){
        courses.remove(course);
        course.drop(this);
        if(course.getWaitlist().size() != 0){
            Student first = course.getWaitlist().get(0);
            first.enrollIn(course);
            course.getWaitlist().remove(first);
        }
    }
}
