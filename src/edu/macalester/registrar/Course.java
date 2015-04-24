package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private long enrollLimit = Long.MAX_VALUE;
    private ArrayList<Student> waitList = new ArrayList<Student>();
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
        if (number > enrollLimit || enrollLimit == NO_ENROLLMENT_LIMIT){
            this.enrollLimit = number;
            while (enrollLimit > students.size() && !waitList.isEmpty()) {
                this.replace();
            }
        }
    }

    public void resetEnrollmentLimit(){this.enrollLimit = NO_ENROLLMENT_LIMIT;}

    public long getEnrollmentLimit() {
        if (enrollLimit == Long.MAX_VALUE){
            return NO_ENROLLMENT_LIMIT;
        }
        return enrollLimit;
    }

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

}