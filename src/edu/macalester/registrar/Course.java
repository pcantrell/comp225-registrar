package edu.macalester.registrar;

import java.util.*;
import java.lang.Integer;


public class Course {
    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitList = new LinkedList<Student>();
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

    public void setEnrollmentLimit(int i) {
        if (i == NO_ENROLLMENT_LIMIT) {
            this.enrollmentLimit = i;
            while (!waitList.isEmpty()) {
                resolveWaitlist();
            }
        } else if (i >= students.size()) {
            this.enrollmentLimit = i;
            for (int n = 0; n <= enrollmentLimit; n++) {
                resolveWaitlist();
            }
        }
        else {
            throw new IllegalArgumentException("Cannot lower class bellow current students");
        }
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void addToWaitList(Student s) {
        if (!waitList.contains(s)) {
            waitList.add(s);
        }
    }
    

    public void resolveWaitlist() {
        if (!this.isFull()) {
            try {
                waitList.remove(0).enrollIn(this);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No students in wait list to add.");
            }
        }
    }

    public boolean isFull() {
        return students.size() == enrollmentLimit;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public void dropStudent(Student s) {
        students.remove(s);
    }

    public void enroll(Student student) {
        students.add(student);
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    public void removeFromWaitList(Student s) {
        waitList.remove(s);
    }

}
