package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private long enrollmentLimit = Long.MAX_VALUE;
    private List<Student> waitList = new LinkedList<Student>();
    public static long NO_ENROLLMENT_LIMIT = Long.MAX_VALUE;

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

    public void setEnrollmentLimit(long number) {
        if (number < students.size()){
            throw new IllegalArgumentException("Error: course size above parameter.");
        }
        if (number > enrollmentLimit || enrollmentLimit == NO_ENROLLMENT_LIMIT){
            this.enrollmentLimit = number;
            while (enrollmentLimit > students.size() && !waitList.isEmpty()) {
                this.replace();
            }
        }

    }

    public void resetEnrollmentLimit(){this.enrollmentLimit = NO_ENROLLMENT_LIMIT;}

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    void enroll(Student student) {
        students.add(student);
    }

    void drop (Student student){
        students.remove(student);
        waitList.remove(student);
    }

    void addWaitList(Student student){
        if (!waitList.contains(student)) {
            waitList.add(student);
        }
    }

    void removeWaitList(Student student){
        waitList.remove(student);
    }

    public List<Student> getWaitList() {return Collections.unmodifiableList(waitList);}

    void replace(){
        waitList.get(0).enrollIn(this);
    }

    public long getEnrollmentLimit() {
        if (enrollmentLimit == Long.MAX_VALUE){
            return NO_ENROLLMENT_LIMIT;
        }
        return enrollmentLimit;
    }

}

