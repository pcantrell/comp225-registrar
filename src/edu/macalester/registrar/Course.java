package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitlist = new LinkedList<Student>();
    private int enrollmentLimit;
    public static final int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;

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

    public void setEnrollmentLimit(int newEnrollmentLimit) {

        if(newEnrollmentLimit == this.NO_ENROLLMENT_LIMIT){
            enrollmentLimit = NO_ENROLLMENT_LIMIT;
            while(this.waitlist.size() != 0){
                moveFromWaitlist();
            }
        }

        else if(newEnrollmentLimit > enrollmentLimit) {
            if (waitlist.size() > 0) {
                int discrepancy = newEnrollmentLimit - enrollmentLimit;
                enrollmentLimit = newEnrollmentLimit;
                for (int i = 0; i < discrepancy; i++) {
                    Student luckyStudent = waitlist.remove(0);
                    luckyStudent.enrollIn(this);
                }
            }
            else{
                enrollmentLimit = newEnrollmentLimit;
            }
        }

        else if(newEnrollmentLimit < students.size()) {
            throw new IllegalArgumentException("Cannot lower enrollment limit below current class size.");
        }

    }

    public int getEnrollmentLimit(){

        if(enrollmentLimit == 0){
            return NO_ENROLLMENT_LIMIT;
        }
        else{
            return enrollmentLimit;
        }
    }

    boolean enroll(Student student) {
        if(students.contains(student)){ /*if the student is already enrolled in the course*/ }

        else {
            if (getStudents().size() < getEnrollmentLimit()) { /*if there is still room in the course*/
                students.add(student);
            }

            else{ /*if the course is full*/
                if(getWaitList().contains(student)){ return false;}
                else{addToWaitlist(student); return false;}
            }
        }
        return true;
    }

    void drop(Student student){
        if(this.students.contains(student)){
            this.students.remove(student);
            moveFromWaitlist();
        }
        else{
            this.waitlist.remove(student);
        }
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitlist);
    }

    public void addToWaitlist(Student student){
        this.waitlist.add(student);
    }

    void moveFromWaitlist(){
        if(waitlist.size() > 0){
            waitlist.remove(0).enrollIn(this);
        }
    }

    public String toString(){
        return title;
    }

}
