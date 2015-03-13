package edu.macalester.registrar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitList = new ArrayList<Student>();
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;
    
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
    
    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }
    
    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }
    
    public void setEnrollmentLimit(int enrollmentLimit) {
        if (enrollmentLimit == NO_ENROLLMENT_LIMIT) {
            this.enrollmentLimit = enrollmentLimit;
            while (!waitList.isEmpty()) {
                Student student = waitList.remove(0);
                student.enrollIn(this);
            }
        } else {
            if (enrollmentLimit < this.enrollmentLimit) {
                throw new IllegalArgumentException("The enrollment limit cannot be decreased.");
            }
            
            if (enrollmentLimit < students.size()) {
                throw new IllegalArgumentException("The enrollment limit cannot be set lower than the current number of students.");
            }

            int difference = enrollmentLimit - this.enrollmentLimit;
            this.enrollmentLimit = enrollmentLimit;
            
            if (!waitList.isEmpty()) {
                int admitCount = Math.min(difference, waitList.size());
                for (int i = 0; i < admitCount; i++) {
                    Student student = waitList.remove(0);
                    student.enrollIn(this);
                }
            }
        }
    }

    boolean enroll(Student student) {
        if (enrollmentLimit != NO_ENROLLMENT_LIMIT) {
            if (enrollmentLimit <= students.size()) {
                waitList.add(student);
                return false;
            }
        }

        students.add(student);
        return true;
    }
    
    void drop(Student student) {
        if (students.remove(student)) {
            if (!waitList.isEmpty()) {
                Student fromWaitList = waitList.remove(0);
                fromWaitList.enrollIn(this);
            }
        }
    }
    
    public static final int NO_ENROLLMENT_LIMIT = -1;
}
