package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Integer enrollmentLimit;
    private PriorityQueue<Student> waitList = new PriorityQueue<Student>();



    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public void setEnrollmentLimit(Integer enrollmentLimit){
        this.enrollmentLimit = enrollmentLimit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    void enroll(Student student) {
        students.add(student);
        System.out.println(student.getName() + " successful enroll into " + this.getCatalogNumber());
    }

    void addToWaitList(Student student){
        waitList.add(student);
        System.out.println(student.getName() + " successful got into the waitlist for " + this.getCatalogNumber());
    }

    public Integer getEnrollmentLimit() { return enrollmentLimit; }

    public PriorityQueue<Student> getWaitList() { return waitList; }

    void drop(Student student){
        students.remove(student);
    }

    void update(Course course){
        while(waitList.size() != 0 && course.getStudents().size() < course.getEnrollmentLimit() ) { //while the waitlist is not empty and class is not fill keep try to enroll student into
            Student student = course.getWaitList().poll(); //poll/remove the first student form waitlist. reduce waitlist size
            student.enrollIn(course);      //add him/her to class, increase course size
            student.removeCourseFromWaitList(course); //remove the course from student's list of wailisted course
        }

    }

}
