package edu.macalester.registrar;
import java.util.*;

public class Course {

    public static final int NO_ENROLLMENT_LIMIT = 2147483647;

    private String catalogNumber, title;

    private Set<Student> students = new HashSet<Student>();

    private List<Student> waitList = new ArrayList<Student>();

    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        if (enrollmentLimit < this.getStudents().size()) {
            throw new IllegalArgumentException("You cannot set enrollment limit to a number lower than your current class size.");
        } else {
            this.enrollmentLimit = enrollmentLimit;
            while (!this.getWaitList().isEmpty() && enrollmentLimit > this.getStudents().size()) {
                getWaitList().get(0).enrollIn(this);
            }
        }
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

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitList);
    }

    boolean enroll(Student student) {
        if (this.getStudents().size() < this.getEnrollmentLimit()) { //if there is room in the course
            students.add(student);
            waitList.remove(student);
            return true;
        } else {
            if (!this.getWaitList().contains(student)) {
                waitList.add(student);
            }
            return false;
        }
    }

    void drop(Student student) {
        if (this.getWaitList().contains(student)) {
            waitList.remove(student);
        } else {
            students.remove(student);
            if (!waitList.isEmpty()) {
                Student firstOnWaitList = waitList.get(0);
                waitList.remove(firstOnWaitList);
                firstOnWaitList.enrollIn(this);
                //System.out.println(student.getName() + " has dropped '" + this.getTitle() + ".' " + firstOnWaitList.getName() + " was first on the wait list, so " + firstOnWaitList.getName() + " has now been enrolled. ");
            }
        }
    }
}