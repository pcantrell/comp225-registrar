package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    public static final int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitList = new LinkedList<Student>();
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;


    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

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

    public void setEnrollmentLimit(int enrollmentLimit) {
        if (enrollmentLimit == NO_ENROLLMENT_LIMIT){
            this.enrollmentLimit = enrollmentLimit;
            removeAllFromWaitList();

        }
        else if (enrollmentLimit >= this.getStudents().size()){
            this.enrollmentLimit = enrollmentLimit;
            for (int i = 0; i < enrollmentLimit; i++){
                updateWaitList();
            }
        }
        else{
            throw new IllegalArgumentException("Should not be here.");
        }

    }

    void enroll(Student student) {
         students.add(student);
    }

    void addToWaitList(Student student){
        if (!waitList.contains(student))  {
            waitList.add(student);
        }
    }
    void updateWaitList(){
        try{
            if (waitList.size() > 0){
                waitList.remove(0).enrollIn(this);
            }

        } catch (IndexOutOfBoundsException error){
            System.out.println("Uh oh. This student was not on the waitlist or the waitlist is empty.");

        }

    }

    void dropStudent(Student student){
        students.remove(student);
        updateWaitList();
    }

    void removeAllFromWaitList(){
        while(!waitList.isEmpty()){
            updateWaitList();
        }
    }

    void removeFromWaitList(Student student){
        waitList.remove(student);
    }
}