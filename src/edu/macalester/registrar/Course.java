package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;



    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitList = new ArrayList<Student>();
    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private int classLimit = NO_ENROLLMENT_LIMIT;



    public void setEnrollmentLimit(int classLimit) {
        int oldSize = this.classLimit;
        int differenceInSize = classLimit - oldSize;
        if (this.students.size() > classLimit) {
            System.err.print("Class already has more participants than class limit allows");
        }
        else {
            this.classLimit = classLimit;
        }
//        if (differenceInSize > 0){
//            for(int x=)
//        }
    }

    public int getEnrollmentLimit(){
        return this.classLimit;
    }

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
        if (this.students.size() < this.classLimit && !this.students.contains(student)) {
            this.students.add(student);
            student.enrollIn(this);
        }
        else if(this.students.contains(student)) {
            System.err.print("Student already enrolled");
        }
        else if (this.students.size()>= this.classLimit){
            this.sendToWaitlist(student);
        }
        else{
            sendToWaitlist(student);
        }
    }

    void sendToWaitlist(Student student) {
        this.waitList.add(student);
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    public void dropStudent(Student student){
       this.students.remove(student);
    }

    @Override
    public String toString(){

        return this.title;

    }


}
