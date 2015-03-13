package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
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

    //todo unmodifiable? unable to drop?
    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public void enrollIn(Course course) {
        if(course.getStudents().contains(this)){
        }
        else{
            if(course.getStudents().size() < course.getEnrollmentLimit()) {
                courses.add(course);
                course.enroll(this);
            }
            else{
                //trying to add that student that tried to enroll to the wait list
                course.addToWaitList(this);
            }
        }
    }

    public void dropOut(Course course){
        if(course.getStudents().contains(this)){
            courses.remove(course);
            course.drop(this);
        }
    }
}
