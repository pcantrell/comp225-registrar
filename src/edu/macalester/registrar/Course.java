package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;

    public int NO_ENROLLMENT_LIMIT = 1000;

    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;

    private Set<Student> students = new HashSet<Student>();

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

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public void enroll(Student student) {
        students.add(student);
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        this.enrollmentLimit = enrollmentLimit;
//       if(enrollmentLimit > students.size()){
//            enrollIfRoom(enrollmentLimit - students.size());
//        }
    }
    private void enrollIfRoom (int enrollmentlimitChanged){
        int avalRoom = enrollmentlimitChanged;
        for (int i=0; i < avalRoom ; i++ ){
            this.enroll(this.getWaitList().poll());
        }
    }
    void addToWaitList(Student student) {
        waitList.add(student);
    }

    public Student getFirstPerson() {
        return waitList.poll();
    }

    public Queue<Student> getWaitList() {
        return waitList;
    }

    void drop(Student student) {
        students.remove(student);
    }
}


