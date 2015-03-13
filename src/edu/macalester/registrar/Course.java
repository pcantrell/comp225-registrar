package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = 1;
    private Queue<Student> waitList = new LinkedList<Student>();


    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //todo unmodifiable? unable to drop?
    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    //unmodifiable? unable to pull from wait list?
    public Queue<Student> getWaitList() { return waitList;}

    void addToWaitList(Student student){
        waitList.add(student);
    }

    boolean enroll(Student student) {
        if(!getStudents().contains(student)){
            if(getStudents().size() < enrollmentLimit){
                students.add(student);
                return true;
            }
            else {
                waitList.add(student);
                student.waitListedCourses().add(this);
                //todo throw exception? notify student?
                System.err.println("Error: Course is full. Student has been added to the wait list.");
                return false;
            }
        }
        return false;
    }

    void drop(Student student) {
        if(getStudents().size()>0){
            students.remove(student);
            //add first student from waitlist into class automatically
            if(getWaitList().size()>0){
                Student s = waitList.poll();
                s.enrollIn(this);
                enroll(s);
                student.waitListedCourses().remove(student);
            }
        }
        else{
            //todo there are no students in the class
            //todo throw exception?
        }
    }

    public int getEnrollmentLimit() {return enrollmentLimit;}
}
