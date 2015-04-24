package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    public final static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;
    private Queue<Student> waitlist = new LinkedList<Student>();

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

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(Integer limit) {
        if (limit >= students.size()) {
            this.enrollmentLimit = limit;
        } else {
            throw new IllegalArgumentException("Enrollment limit cannot be set below number of currently enrolled students");
        }
    }

    boolean enroll(Student student) {
        if (this.getStudents().size() < this.getEnrollmentLimit()) {
            students.add(student);
            return true;
        } else {
            this.waitlist(student);
            return false;
        }
    }

    public String drop(Student student) {
        students.remove(student);
        waitlist.remove(student);
        if ( (waitlist.size() > 0) && (getStudents().size() < getEnrollmentLimit()) ) {
            enrollFromWaitlist();
            return "Student successfully dropped the course, and was replaced by the first member of the waitlist";
        } else {
            return "Student successfully dropped the course";
        }
    }

    private void waitlist(Student student) {
        waitlist.add(student);
    }

    public Queue<Student> getWaitList() {return waitlist;}

    private boolean enrollFromWaitlist() {
        Student s = waitlist.remove();
        return this.enroll(s);
    }
}
