package edu.macalester.registrar;

import java.util.*;

public class Course {
    private String catalogNumber, title;
    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;
    private List<Student> waitList = new ArrayList();
    private Set<Student> students = new HashSet<Student>();

    public boolean isFull() { return (students.size() >= getEnrollmentLimit()); }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public int getEnrollmentLimit() { return enrollmentLimit; }

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

    public List<Student> getWaitList() { return Collections.unmodifiableList(waitList); }

    /**
     *   This methods set the new enrollment limit
     *   @param newLimit the new limit has to be equal or greater than the current student size
     */
    public void setEnrollmentLimit(int newLimit) {
        if (newLimit < students.size()) {
            throw new IllegalArgumentException(Integer.toString(newLimit));
        }
        else {
            enrollmentLimit = newLimit;
            enrollWaitList();
        }
    }

    /**
    *   This method lifts the enrollment limit to the max integer value, once the enrollment limit is set
    *   After lifting the limit, admit all wait-listed students
    */
    void liftEnrollmentLimit() {
        if (enrollmentLimit != 0 && enrollmentLimit != Integer.MAX_VALUE) {
            // lift enrollment limit
            setEnrollmentLimit(Integer.MAX_VALUE);
            // now put all current wait-listed student in the class
            enrollWaitList();
        }
    }

    /**
    *   This method takes in two arguments, the student and a boolean value depending on whether course is full
    *   @param student  the student caller is asking to enroll in the course
    *   @param enrollmentPossible   boolean value determining if course is already full
    */
    boolean enroll(Student student, boolean enrollmentPossible) {
        if (enrollmentPossible) {
            if (students.contains(student)) {
                System.out.println("I'm sorry, " + student.getName() + " cannot be enrolled twice!");
            } else if (!students.contains(student)) {
                students.add(student);
            }
            return true;
        } else if (!enrollmentPossible) { // Course already full
            if (students.contains(student)) {
                System.out.println("I'm sorry, " + student.getName() + " cannot be enrolled twice!");
                return true;
            } else if (!students.contains(student)) {
                addToWaitList(student);
                return false;
            }
        }
        return true;
    }

    /**
    *   This method takes in the student as argument, then check if the waitlist already contains her, then add her to
    *   the list and inform the caller of the wait-list status
    *   @param student  the student caller is asking to enroll in the course parsed from enroll()
    */
    void addToWaitList(Student student) {
        if (!waitList.contains(student)) {
            waitList.add(student);
            System.out.println(student.getName() + " is wait-listed for " + this.getCatalogNumber() + ": " + this.getTitle());
        }
    }

    /**
     *   This method takes in the student as argument, then drop her from the class. the first waitlisted student is then
     *   automatically admitted. If she is in the waitlist, the drop function will remove her from the wait list too.
     *   @param student  the student caller is asking to drop the class
     */
    void drop(Student student) {
        if (!students.isEmpty() && (students.contains(student))) {
            students.remove(student);
            if (!waitList.isEmpty()) {
                Student firstInLine = waitList.remove(0);
                firstInLine.enrollIn(this);
            }
        }
        if (!waitList.isEmpty() && (waitList.contains(student))) {
            waitList.remove(student);
        }
    }

    /**
    *   Once the enrollment limit is lifted, all students in the waitlist will be admitted to the class
    */
    void enrollWaitList() {
        while (!waitList.isEmpty() && (!isFull())) {
            Student firstInLine = waitList.remove(0);
            firstInLine.enrollIn(this);
        }
    }
}
