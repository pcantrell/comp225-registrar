package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = -1;
    private Queue<Student> waitList = new LinkedList<>();


    public String getCatalogNumber() {
        return catalogNumber;
    }



    public void setEnrollmentLimit(int limit){
        if(limit<0){
           enrollmentLimit = -1;
        } else if(limit<students.size()){
            throw new IllegalArgumentException("You can't reset the enrollment limit to be smaller than the current class size");
        }  else{
            this.enrollmentLimit=limit;
        }
    }

    public void liftEnrollmentLimit(){

        while(!waitList.isEmpty()){
            Student nextStudent= waitList.remove();
            students.add(nextStudent);
            nextStudent.offWaitList(this);
        }

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

    public Queue<Student> getWaitList(){

        return new LinkedList<>(waitList);
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    boolean enroll(Student student) {
        if (enrollmentLimit>students.size() || enrollmentLimit == -1){
            students.add(student);
            return true;
        }else{
            this.waitList.add(student);
            return false;
        }
    }

    void drop(Student student){
        students.remove(student);
        if(!waitList.isEmpty()) {
            Student nextStudent = waitList.remove();
            students.add(nextStudent);
            nextStudent.offWaitList(this);
        }

    }
}
