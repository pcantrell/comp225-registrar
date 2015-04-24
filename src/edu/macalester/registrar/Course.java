package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    public static final int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private int enrollLimit;
    private List<Student> waitList = new LinkedList<Student>();

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    // never allows enrollment limit to be zero
    public int getEnrollmentLimit() {
        if (enrollLimit == 0 ){
            return NO_ENROLLMENT_LIMIT;
        }
        return enrollLimit;}

    public void setEnrollmentLimit(int newLimit) {
        //TODO  modify this to change enrollment / wait lists for e.c.
        if (newLimit > enrollLimit){
            int removeS = newLimit - enrollLimit;
            this.enrollLimit = newLimit;
            for (int i = 0; i < removeS; i++) {
                if (this.waitList.size() > 0) {
                    Student s = this.waitList.remove(0);
                    s.enrollIn(this);
                }
            }
        } else if (newLimit < this.students.size()){
                throw new IllegalArgumentException("Cannot lower enrollment limit below class size");
        }
        // if new enrollment > old : remove students from waitlist, add to course
        // else if new enrollment < old : return false, don't allow to lower limit
        //if equal, do nothing.

    }
    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    Boolean enroll(Student student) {
        if (this.students.contains(student)){
            // if the student is already enrolled
            return true;
        }
        if (this.waitList.contains(student)){
            // if the student is already on waitlist
            return false;
        }
        if (this.getStudents().size() >= this.getEnrollmentLimit()){
            System.out.println(student.getName() + " was not enrolled. This operation would exceed the enrollment limit.");
            waitList.add(student);
            System.out.println(student.getName() + " was added to the waitlist.");
            return false;
        }
        else {
            students.add(student);
            return true;
        }
    }

    void drop(Student student) {
        int origSize = students.size();
        this.students.remove(student);
        this.waitList.remove(student);
        int newSize = students.size();
        //need to check if class size is available to do next step
        // if there are students on the waitlist and the size of the course has decreased
        if (waitList.size() > 0 && newSize < origSize){
            Student s = this.waitList.remove(0);
            s.enrollIn(this);

        }
    }
}
