package edu.macalester.registrar;

import java.util.*;

public class Course {

    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;

    private String catalogNumber, title;
    private int enrollmentLimit = NO_ENROLLMENT_LIMIT;

    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitlist = new LinkedList<Student>();

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

    public List<Student> getWaitList() {return Collections.unmodifiableList(waitlist);}

    /**
     * Change the enrollment limit of the class. Cannot lower enrollment limit below the current
     * class size. If the new enrollment limit is greater than the current class size, enroll students
     * from the waitlist until either the limit is reached, or the waitlist is empty.
     * @param limit
     */
    public void setEnrollmentLimit(int limit) {
        if (limit < this.getStudents().size()) {
            throw new IllegalArgumentException("Can't lower enrollment limit below current class size!");
        } else {
            enrollmentLimit = limit;
            while (!this.getWaitList().isEmpty()) {
                if (enrollmentLimit == this.getStudents().size()) {
                    break;
                } else {
                    waitlist.remove(0).enrollIn(this);
                }
            }
        }
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
            if (!waitlist.contains(student)) {
                waitlist.add(student);
                System.out.println(getTitle() + " was full, so " + student.getName() + " was added to the waitlist");
            }
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
        while (!this.getWaitList().isEmpty()) {
            Student waitlistedStudent = waitlist.remove(0);
            waitlistedStudent.enrollIn(this);
        }
    }

    /**
     * Drops the given student out of this course's roster/waitlist,
     * and enrolls the first wait-listed student, if the wait list is not empty.
     * @param student
     */

    void dropStudent(Student student) {
        if (getStudents().contains(student)) {
            students.remove(student);
        } else if (getWaitList().contains(student)) {
            waitlist.remove(student);
        }

        if (waitlist.size() != 0 && getStudents().size() < enrollmentLimit) {
            Student waitlistedStudent = waitlist.remove(0);
            waitlistedStudent.enrollIn(this);
        }
    }

}
