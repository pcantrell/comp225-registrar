package edu.macalester.registrar;

import java.util.*;


public class Course {

    // Course Attributes
    private String catalogNumber, courseTitle;
    private Set<Student> studentCourseList = new HashSet<Student>();
    private List<Student> courseWaitList = new ArrayList<Student>();
    public static final int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private int enrollLim = NO_ENROLLMENT_LIMIT;


    // Getters
    public String getCatalogNumber() {
        return this.catalogNumber;
    }

    public String getCourseTitle() { return this.courseTitle; }

    public Set<Student> getStudents() { return Collections.unmodifiableSet(this.studentCourseList); }

    public List<Student> getWaitList() {return Collections.unmodifiableList(this.courseWaitList); }

    public Student getPriorityStudent() {return this.courseWaitList.get(0); } // return priority student off the course waitlist

    public int getEnrollmentLimit() {return this.enrollLim; }


    // Setters
    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public void setTitle(String courseTitle) {this.courseTitle = courseTitle; }

    public void setEnrollmentLimit(int newLim) {

        if (newLim > this.getEnrollmentLimit()){
            System.err.println("Cannot set enrollment limit below current class size!");
        }
        else{
            this.enrollLim = newLim;
        }

    }


    // Course Actions

    void addToWaitList(Student student) { this.courseWaitList.add(student); }

    public void removeFromWaitList(Student student) {
        this.courseWaitList.remove(student);

        }


    // Enroll Student in Course
    void enroll(Student student) {

        if (this.getStudents().size() < this.getEnrollmentLimit()) {
            studentCourseList.add(student);

        }

        else {
            this.courseWaitList.add(student);
            System.err.println("Maximum Course Limited Reached: " + this.getEnrollmentLimit());

        }

    }

    public void dropStudent(Student student){
        this.studentCourseList.remove(student);
        this.courseWaitList.remove(student);

    }


    @Override
    public String toString(){ return this.courseTitle; }

}
