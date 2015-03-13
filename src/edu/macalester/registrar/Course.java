package edu.macalester.registrar;


import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Integer enrollmentLimit;
    private Queue<Student> waitList = new LinkedList<Student>();



    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public void setEnrollmentLimit(Integer enrollmentLimit){
        this.enrollmentLimit = enrollmentLimit;
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

    void enroll(Student student) {
        students.add(student);
        System.out.println(student.getName() + " successful enroll into " + this.getCatalogNumber());
    }

    void addToWaitList(Student student){
        waitList.add(student);
        System.out.println(student.getName() + " successful got into the waitlist for " + this.getCatalogNumber());
    }

    public Integer getEnrollmentLimit() { return enrollmentLimit; }

    public Queue<Student> getWaitList() { return new LinkedList<Student>(waitList); }

    void drop(Student student){
        students.remove(student);
    }

    void update(Course course){
        while(waitList.size() != 0 && course.getStudents().size() < course.getEnrollmentLimit() ) {
            Student student = course.getWaitList().poll();
            student.enrollIn(course);
            student.removeCourseFromWaitList(course);
        }

    }

}
