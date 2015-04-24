package edu.macalester.registrar;


import java.util.*;


public class Course {
    public static final int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;
    private List<Student> waitList = new LinkedList<Student>();



    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public void setEnrollmentLimit(int enrollmentLimit){
        if (enrollmentLimit<students.size()){
            throw new IllegalArgumentException("cannot set class enrollmentlimit lower than class size");
        }
        else {
            this.enrollmentLimit = enrollmentLimit;
            update(this);
        }
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

    void enrollIn(Student student) {
        students.add(student);
        System.out.println(student.getName() + " successful enroll into " + this.getCatalogNumber());
    }

    void addToWaitList(Student student){
        waitList.add(waitList.size(),student);
        System.out.println(student.getName() + " successful got into the waitlist for " + this.getCatalogNumber());
    }

    void removesFromWaitList(Student student){
        waitList.remove(student);
    }

    public int getEnrollmentLimit() { return enrollmentLimit; }

    public List<Student> getWaitList() { return Collections.unmodifiableList(waitList);}

    void drop(Student student){
        students.remove(student);
    }

    void update(Course course){
        for (int i=0; i<waitList.size(); i++) {
            while (waitList.size() != 0 && course.getStudents().size() < course.getEnrollmentLimit()) {
                Student student = course.getWaitList().get(i);
                student.removeCourseFromWaitList(course);
                course.removesFromWaitList(student);
                student.enrollIn(course);
            }
        }

    }

}
