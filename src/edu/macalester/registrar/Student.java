package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();
    private Set<Course> coursesWaitListed = new HashSet<Course>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public Set<Course> getCoursesWaitListed(){ return Collections.unmodifiableSet(coursesWaitListed);}

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public boolean enrollIn(Course course) {
        if(course.getStudents().contains(this)){
            return true;
        }
        else if(course.getStudents().size() < course.getEnrollmentLimit()) {
            course.enrollIn(this);
            courses.add(course);
            return true;
        }
        else if(course.getWaitList().contains(this)){
            return false;
        }
        else if(!course.getStudents().contains(this)){
            course.addToWaitList(this);
            coursesWaitListed.add(course);
            return false;
        }
        else{
            return false;
        }
    }

    public void drop(Course course) {
        if(course.getStudents().contains(this)){
            course.drop(this);
            courses.remove(course);
            System.out.println(this.getName() + " successfully dropped " + course.getCatalogNumber());
        }
        else if(course.getWaitList().contains(this)){
            course.removesFromWaitList(this);
            coursesWaitListed.remove(course);
        }
        else{
            System.out.println(this.getName() + " is not in "+ course.getCatalogNumber() );
        }
        course.update(course);

    }

    public void removeCourseFromWaitList(Course course){
        coursesWaitListed.remove(course);
    }



}
