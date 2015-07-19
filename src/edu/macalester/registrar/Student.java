package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();
    private boolean enrolled = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public boolean enrollIn(Course course) {
        if (!(courses.contains(course))) {
            this.enrolled = false;
            if (course.getStudents().size() < course.getEnrollmentLimit()) {
                courses.add(course);
                this.enrolled = true;
            }
            course.enroll(this);
        }
        return this.enrolled;
    }

    /**
     * Drop this student from the given course's roster.
     * Has no effect if the student is not registered or wait-listed.
     * Equivalent to course.drop(student).
     */
    public void drop(Course course) {
        if (course.getWaitList().size() > 0) {
            Student waitingStudent = course.getWaitList().get(0);
            waitingStudent.courses.add(course);
        }
        courses.remove(course);
        course.drop(this);

    }
}
