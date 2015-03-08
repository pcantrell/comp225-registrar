package edu.macalester.registrar;

import java.util.*;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();
    private Queue<Course> waitListCourses = new LinkedList<Course>();

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
    public void enrollIn(Course course) {
        if (course.getStudents().size()>=course.getEnrollmentLimit() && !this.getCourses().contains(course)){
            /* throw new IllegalArgumentException("Class is at capacity!");
             */
            System.out.println("Class is at capacity," + this.getName()  + " will automatically be added to the wait list");
            waitListCourses.add(course);
            course.addToWaitList(this);
        } else {
            courses.add(course);
            course.enroll(this);
        }
    }

    public void dropCourse(Course course) {
        courses.remove(course);
        course.dropStudent(this);

    }
}
