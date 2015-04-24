package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = Integer.MAX_VALUE;
    private List<Student> waitList = new ArrayList<>();
    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setEnrollmentLimit(int newEnrollmentLimit){
        int oldLimit = enrollmentLimit;
        if(newEnrollmentLimit < students.size()){
           throw new IllegalArgumentException();
        }
        this.enrollmentLimit = newEnrollmentLimit; //sets enrollmentLimit to newEnrollmentLimit
        if(oldLimit < newEnrollmentLimit){
            for(int i = 0; i < newEnrollmentLimit-oldLimit; i++)
            {
                if(!waitList.isEmpty()&& students.size() < enrollmentLimit){ //if the waitlist is not empty
                    waitList.get(0).enrollIn(this); //enroll the first student on the list
                    waitList.remove(0); //remove student from waitlist
                }
            }
        }
    }

    public int getEnrollmentLimit(){return enrollmentLimit;}

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitList(){
        return Collections.unmodifiableList(waitList);
    }

    void enroll(Student student) {
        if(students.size() < enrollmentLimit){
            students.add(student);
        }
        else if(!students.contains(student)){
            if(!waitList.contains(student)){
                waitList.add(student);}
//            System.out.println("There is no room in " + catalogNumber + ": " + title + " to add " + student.getName() );
//            System.out.println(student.getName() + " has been added to the waitlist");
        }
    }

    void drop(Student student){ //drops student from course
        students.remove(student); //remove students from student list
        waitList.remove(student);//remove student from waitlist
        if(!waitList.isEmpty()&& students.size() < enrollmentLimit){ //if the waitlist is not empty
            waitList.get(0).enrollIn(this); //enroll the first student on the list
            waitList.remove(0); //remove student from waitlist
        }
    }



}
