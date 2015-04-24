package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();
    private Set<Course> waitListedCourses = new HashSet<Course>();

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
    public Set<Course> getWaitListedCourses() { return Collections.unmodifiableSet(waitListedCourses);}
    public Set<Course> waitListedCourses() {return waitListedCourses;}

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public boolean enrollIn(Course course) {
        if(course.getStudents().contains(this)){
            return true;
        }
        else{
            if(course.getStudents().size() < course.getEnrollmentLimit()) {
                courses.add(course);
                boolean result = course.enroll(this);
                if(result == false){
                    //student is not enrolled - enrollment failed - added to wait list
                    waitListedCourses.add(course);
                }
                if(result == true){
                    waitListedCourses.remove(course);
                }
            }
            else{
                //trying to add that student that tried to enroll to the wait list
                course.addToWaitList(this);
                return false;
            }
        }
        return true;
    }

    public void drop(Course course){
            courses.remove(course);
            course.drop(this);
    }

    public void removeFromWaitList(Course course) {
        waitListedCourses.remove(course);
    }
}
