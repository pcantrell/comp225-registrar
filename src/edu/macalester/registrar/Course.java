package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = Integer.MAX_VALUE;
    private List<Student> waitList = new ArrayList<>();
    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setEnrollmentLimit(int newEnrollmentLimit){
        int oldLimit = enrollmentLimit;
        if(newEnrollmentLimit < students.size()){
           throw new IllegalArgumentException();
        }
        this.enrollmentLimit = newEnrollmentLimit;
        if(oldLimit < newEnrollmentLimit){
            for(int i = 0; i < newEnrollmentLimit-oldLimit; i++)
            {
                if(!waitList.isEmpty()&& students.size() < enrollmentLimit){
                    waitList.get(0).enrollIn(this);
                    waitList.remove(0);
                }
            }
        }
    }

    public int getEnrollmentLimit(){return enrollmentLimit;}

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitList(){
        return Collections.unmodifiableList(waitList);
    }

    void enroll(Student student) {
        if(students.size() < enrollmentLimit){
            students.add(student);
        }
        else if(!students.contains(student)){
            if(!waitList.contains(student)){
                waitList.add(student);}
        }
    }

    void drop(Student student){
        students.remove(student);
        waitList.remove(student);
        if(!waitList.isEmpty()&& students.size() < enrollmentLimit){
            waitList.get(0).enrollIn(this);
            waitList.remove(0);
        }
    }
}
