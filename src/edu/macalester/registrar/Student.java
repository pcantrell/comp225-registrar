package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();
    private Set<Course> waiting = new HashSet<Course>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }
    public Set<Course> getWaiting() {
        return Collections.unmodifiableSet(waiting);
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     * returns a boolean representing whether or not the student was enrolled
     */
    public boolean enrollIn(Course course) {
        boolean enrolled =course.enroll(this);
        if (enrolled){
            courses.add(course);
        }else{
            waiting.add(course);
        }
        return enrolled;
    }
    /**
     * Calls the drop method on a given course and removes the course from the student's schedule
     */
    public void dropCourse(Course course){
        course.drop(this);
        courses.remove(course);

    }
    /**
     * removes a course from the student's wait list and adds it to their schedule
     */
     void offWaitList(Course course){
         waiting.remove(course);
         courses.add(course);

    }
}
