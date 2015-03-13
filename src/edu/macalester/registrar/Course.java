package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollmentLimit = Integer.MAX_VALUE;
    private LinkedList<Student> waitlist = new LinkedList<Student>();

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

    public void setEnrollmentLimit(int limit) {
        enrollmentLimit = limit;
    }

    /**
     * Enroll the given student to this course if the course is not full,
     * else add the student to this course's waitlist.
     * @param student
     * @param enrolledSuccessfully
     */
    void enroll(Student student, boolean enrolledSuccessfully) {
        if (enrolledSuccessfully) {
            students.add(student);
            System.out.println(student.getName() + " enrolled successfully");
        }
        else {
            waitlist.add(student);
            System.out.println(getTitle() + " was full, so " + student.getName() + " was added to the waitlist");
        }
    }

    /**
     * Lift the enrollment limit of this course if one has been set,
     * and automatically enroll all the students in the waitlist.
     */
    public void liftEnrollmentLimit() {
        if (enrollmentLimit > 0 && enrollmentLimit < Integer.MAX_VALUE) {
            enrollmentLimit = Integer.MAX_VALUE;
        }
        if (waitlist.size() != 0) {
            while (waitlist.size() > 0) {
                Student waitlistedStudent = waitlist.poll();
                waitlistedStudent.enrollIn(this);
            }
        }
    }

    /**
     * Drops the given student out of this course's roster,
     * and enrolls the first wait-listed student, if the wait list is not empty.
     * @param student
     */

    public void dropStudent(Student student) {
        students.remove(student);
        if (waitlist.size() != 0) {
            Student waitlistedStudent = waitlist.poll();
            waitlistedStudent.enrollIn(this);
        }
    }
}
