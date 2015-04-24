
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
        return Collections.unmodifiableSet(courses);
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public boolean enrollIn(Course course) {
        boolean inClass;
        if(course.getStudents().size() == course.getEnrollmentLimit()){
            if(course.getStudents().contains(this)){
                inClass = true;
            }
            else {
                System.out.println("Enrollment limit met in " + course.getTitle() + ". Student cannot be enrolled in the class.");
                course.addToWaitlist(this);
                inClass = false;
            }
        }
        else{
            courses.add(course);
            course.enroll(this);
            System.out.println(name + " successfully enrolled in " + course.getTitle());
            inClass = true;
        }
        return inClass;
    }
    
    public void drop(Course course){
        courses.remove(course);
        course.dropOut(this);
        if(course.getWaitList().size() != 0){
            Student first = course.getWaitList().get(0);
            first.enrollIn(course);
            course.removeFromWaitList(first);
        }
        if(course.getWaitList().contains(this)){
            course.removeFromWaitList(this);
        }
    }
}
