package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = -1;


    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setEnrollmentLimit(int limit){
        if(limit<0){
           enrollmentLimit = -1;
        }
        else if(limit<students.size()){
            System.out.println("The number of students in " +this.getTitle() + " already exceeds " + limit + ".");
            System.out.println("The enrollment limit of " +this.getTitle() + " is still " + this.enrollmentLimit+ "." );
            System.out.println();


        }  else{
            this.enrollmentLimit=limit;
            }
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

    boolean enroll(Student student) {
        if (enrollmentLimit>students.size() || enrollmentLimit== -1){
            students.add(student);
            return true;
        }else{
            return false;
        }
    }
}
