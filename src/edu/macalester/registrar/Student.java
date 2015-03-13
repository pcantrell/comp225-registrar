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
    public void enrollIn(Course course) {
        if(course.getStudents().size() < course.getEnrollmentLimit()) {//if the class is not full
            course.enroll(this);
            courses.add(course);
        }
        else if(!course.getStudents().contains(this)){ //if the class is full and student is not already in the class
            course.addToWaitList(this);
            coursesWaitListed.add(course);
        }
    }

    public void drop(Course course) {
        if(course.getStudents().contains(this)){ // if student is in the course let him/her drop it
            course.drop(this); //remove student from the course
            courses.remove(course); // remove course from student's schedule
            System.out.println(this.getName() + " successfully dropped " + course.getCatalogNumber());
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
