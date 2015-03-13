package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit;
    private List<Student> waitList = new ArrayList<>();

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

    public void setEnrollmentLimit(int enrollmentLimit){this.enrollmentLimit = enrollmentLimit;}

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
            waitList.add(student);
            System.out.println("There is no room in " + catalogNumber + ": " + title + " to add " + student.getName() );
            System.out.println(student.getName() + " has been added to the waitlist");
        }
    }

    void drop(Student student){
        students.remove(student);
        if(!waitList.isEmpty()){
            waitList.get(0).enrollIn(this);
            waitList.remove(0);
        }
    }

}
