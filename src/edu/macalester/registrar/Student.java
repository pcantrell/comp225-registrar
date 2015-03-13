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
     * If the given course is not full, add this student to the its roster,
     * else add the student to the course's waitlist.
     * Has no effect if the student is already registered.
     *
     */
    public void enrollIn(Course course) {
        if (course.getStudents().size() == course.getEnrollmentLimit()) {
            course.enroll(this, false);
        } else {
            courses.add(course);
            course.enroll(this, true);
        }
    }

    /**
     * Drop this student out of the given course's roster.
     * Has no effect if the student is not enrolled in the course.
     * Equivalent to course.dropStudent(student).
     * @param course
     */
    public void drop(Course course) {
        courses.remove(course);
        course.dropStudent(this);
    }
}
