package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();

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
     * If the given course is not full, add this student to its roster,
     * else add the student to the course's waitlist.
     * Has no effect if the student is already registered.
     *
     */
    public boolean enrollIn(Course course) {
        if (course.getStudents().size() == course.getEnrollmentLimit()) {
            if (!course.getStudents().contains(this)) {
                course.enroll(this, false);
                return false;
            } else {
                return true;
            }
        } else {
            courses.add(course);
            course.enroll(this, true);
            return true;
        }
    }

    /**
     * Drop this student out of the given course's roster/waitlist.
     * Has no effect if the student is not enrolled in the course.
     * Equivalent to course.dropStudent(student).
     * @param course
     */
    public void drop(Course course) {
        courses.remove(course);
        course.dropStudent(this);
    }
}
