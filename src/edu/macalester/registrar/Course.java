package edu.macalester.registrar;

import java.util.*;


public class Course {

    //  VARIABLE SETUP
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    public final static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;
    private List<Student> waitlist = new LinkedList<Student>();


    //  BASIC GETTERS & SETTERS
    //  get, set catalog number
    public String getCatalogNumber() {
        return catalogNumber;
    }
    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    //  get, set title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    //  get students, waitlist, enrollmentLimit
    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }
    public List<Student> getWaitList() {return Collections.unmodifiableList(waitlist);}
    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }


    //  ADVANCED METHODS
    //  enroll a student (adds to waitlist if full)
    boolean enroll(Student student) {
        if (students.contains(student)) {
            return true;
        }

        if (waitlist.contains(student)) {
            return false;
        }

        if (getStudents().size() < getEnrollmentLimit()) {
            students.add(student);
            student.enrollIn(this);
            return true;
        }

        waitlist.add(student);
        return false;
    }

    //  set enrollment limit (promotes from waitlist if new spots open up)
    public void setEnrollmentLimit(Integer limit) {
        if (limit >= students.size()) {
            enrollmentLimit = limit;

            if ( (enrollmentLimit > students.size()) & (waitlist.size() > 0) ) {
                int openSpots = enrollmentLimit - students.size();
                while ( (openSpots > 0) & (waitlist.size() > 0) ) {
                     enrollFromWaitlist();
                    openSpots--;
                }
            }
        } else {
            throw new IllegalArgumentException("Enrollment limit cannot be set below number of currently enrolled students");
        }
    }

    //  remove a student (promotes from waitlist if new spots open up)
    public void drop(Student student) {
        students.remove(student);
        waitlist.remove(student);
        while ( (waitlist.size() > 0) && (getStudents().size() < getEnrollmentLimit()) ) {
            enrollFromWaitlist();
        }
    }

    //  enroll the first waitlisted student
    private boolean enrollFromWaitlist() {
        Student s = waitlist.remove(0);
        return this.enroll(s);
    }
}
