package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollLimit;
    private List<Student> waitList = new LinkedList<Student>();

    public static final int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;


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
        if (newLimit > enrollLimit){
            int removeS = newLimit - enrollLimit;
            enrollLimit = newLimit;
            for (int i = 0; i < removeS; i++) {
                if (waitList.size() > 0) {
                    Student s = waitList.remove(0);
                    s.enrollIn(this);
                }
            }
        } else if (newLimit < this.students.size()){
                throw new IllegalArgumentException("Cannot lower enrollment limit below class size");
        }
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    Boolean enroll(Student student) {
        if (students.contains(student)){
            // if the student is already enrolled
            return true;
        }
        if (waitList.contains(student)){
            // if the student is already on waitlist
            return false;
        }
        if (getStudents().size() >= getEnrollmentLimit()){
            waitList.add(student);
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
        
        // if there are students on the waitlist and the size of the course has decreased
        if (waitList.size() > 0 && newSize < origSize){
            Student s = this.waitList.remove(0);
            s.enrollIn(this);

        }
    }
}
