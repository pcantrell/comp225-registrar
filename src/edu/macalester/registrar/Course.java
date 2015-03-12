package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Integer enrollmentLimit;
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

    public Integer getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(Integer limit) {
        this.enrollmentLimit = limit;
    }

    public String enroll(Student student) {
        if (this.getStudents().size() < this.getEnrollmentLimit()) {
            students.add(student);
            return "Student successfully added!";
        } else {
            //add to the wait list...
            this.waitlist(student);
            return "Course is full; student successfully added to the waitlist";
        }
    }

    public String drop(Student student) {
        this.students.remove(student);
        if ( (this.waitlist.size() > 0) && (this.getStudents().size() < this.getEnrollmentLimit()) ) {
            this.enrollFromWaitlist();
            return "Student successfully dropped the course, and was replaced by the first member of the waitlist";
        } else {
            return "Student successfully dropped the course";
        }
    }

    public void waitlist(Student student) {
        waitlist.add(student);
    }

    public String enrollFromWaitlist() {
        if (this.waitlist.size() > 0) {
          Student s = waitlist.remove();
          return this.enroll(s);
        } else {
            return "Waitlist is empty!";
        }
    }
}
