package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private Queue<Student> waitList = new LinkedList<Student>();
    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;
    private int difference;


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

    //todo make immutable
    public List<Student> getWaitList() { return Collections.unmodifiableList((List<? extends Student>) waitList);}

    public void addToWaitList(Student student){
        if(getWaitList().contains(student) == false){
            waitList.add(student);
        }
    }

    public void setEnrollmentLimit(int limit){
        if(limit > getStudents().size() || enrollmentLimit == NO_ENROLLMENT_LIMIT) {
            if(limit == NO_ENROLLMENT_LIMIT){
                difference = waitList.size();
            }
            else if(limit > enrollmentLimit){
                difference = limit - enrollmentLimit;
            }
            enrollmentLimit = limit;
            for(int i = 0; i<difference; i++){
                enrollFromWaitlist(this);
            }
        }
        else if(limit<getStudents().size()){
            throw new IllegalArgumentException();
            //todo failed to change the limit below the actual enrollment
            //todo throw exception?
        }
    }



    boolean enroll(Student student) {
        if(!getStudents().contains(student)){
            if(getStudents().size() < enrollmentLimit){
                students.add(student);
                return true;
            }
            else {
                if(!waitList.contains(student)) {
                    waitList.add(student);
                    student.waitListedCourses().add(this);
                    //todo throw exception? notify student?
                    System.err.println("Error: Course is full. Student has been added to the wait list.");
                    return false;
                }
            }
        }
        return false;
    }

    public void enrollFromWaitlist(Course course){
        if(getWaitList().size()>0){
            Student firstFromWaitList = waitList.poll();
            firstFromWaitList.enrollIn(course); //enroll first from waitlist in this course
            enroll(firstFromWaitList); //tell the student that they are enrolled in course
            firstFromWaitList.waitListedCourses().remove(firstFromWaitList); //remove the student from waitlisted courses
        }
    }

    void drop(Student student) {
        int currentClassSize = students.size();
        students.remove(student);
        waitList.remove(student);
        int newClassSize = students.size();
        if(newClassSize < currentClassSize){
            student.removeFromWaitList(this);
            enrollFromWaitlist(this);
        }

    }

    public int getEnrollmentLimit() {return enrollmentLimit;}
}
