package edu.macalester.registrar;


import java.util.*;


public class Student {

    // General Student Attributes
    private String studentName;
    private Set<Course> studentCourses = new HashSet<Course>();

    // Getters
    public String getStudentName() {
        return this.studentName;
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(this.studentCourses);
    }

    // Setters
    public void setName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */

    // Enroll Student in Class
    public boolean enrollIn(Course course) {

        if (course.getStudents().size() < course.getEnrollmentLimit()) {
            studentCourses.add(course);
            course.enroll(this);
            return true;

        }

        else {
            course.addToWaitList(this);
            return false;
        }

    }

    // Student Course Actions
    public void removeCourse(Course course){this.studentCourses.remove(course); }

    // Student Drops Course
    public void drop(Course course) { // Remove student from list in opposite direction so student is not put back in waitlist

        // Handles no students waiting to enroll in class
        if(course.getWaitList().size() == 0){
            this.removeCourse(course);
            course.dropStudent(this);
        }

       // Handles students waiting to enroll in course
       else if (course.getWaitList().size() > 0){
            this.removeCourse(course);
            course.dropStudent(this);
            Student student = course.getPriorityStudent();
            course.removeFromWaitList(student);
            student.enrollIn(course);

        }

       else{
            System.err.println("Drop Error");
        }

    }

    @Override
    public String toString(){ return this.studentName; }

}







