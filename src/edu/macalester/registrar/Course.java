package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private Integer enrollmentLimit = NO_ENROLLMENT_LIMIT;
    private List<Student> waitList = new ArrayList<>(); //An arraylist so you can add as many students into the wait list, and easily grab student at position 0

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

    public List<Student> getWaitList() { return Collections.unmodifiableList(waitList); }

    public int getEnrollmentLimit() { return enrollmentLimit; }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    boolean enroll(Student student) {
        if (students.size() != enrollmentLimit){
            if (!students.contains(student)){
                students.add(student);
                waitList.remove(student);
            }
            return true;
        }
        else{
            if (!waitList.contains(student)){
                waitList.add(student);
            }
            return false;
        }
    }

    void removeStudent(Student student){
        if (waitList.contains(student)){
            waitList.remove(student);
        }

        else{
            students.remove(student);
            if (!waitList.isEmpty()){
                Student first =waitList.get(0);
                //students.add(first);
                first.enrollIn(this);
                waitList.remove(first);

            }
        }
    }

    int getRemainingSeats(){
        if (students.size() < enrollmentLimit){
            return getEnrollmentLimit() - getStudents().size();
        }
        else{
            return 0;
        }
    }

    public void setEnrollmentLimit(int number){

        if (number < students.size()){ //Cannot lower enrollment limit below class size.
            throw new IllegalArgumentException();
        }

        if (enrollmentLimit < number){ //if the number is greater than the enrollment limit... limit increased...
            int n = number - enrollmentLimit;
            enrollmentLimit = number;
            for (int i=0; i < n; i++){
                if (!waitList.isEmpty()){
                    Student stud = waitList.get(0);
                    stud.enrollIn(this);
                    waitList.remove(stud);
                }
            }
        }

        if (number > students.size()){
            enrollmentLimit = number;
        }





    }
}