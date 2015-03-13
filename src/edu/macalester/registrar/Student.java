package edu.macalester.registrar;

import java.io.IOError;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();
    private Set<Course> coursesOnWaitList = new HashSet<Course>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public Set<Course> getWaitListedCourses() {
        return Collections.unmodifiableSet(coursesOnWaitList);
    }


    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public void enrollIn(Course course) {
        if (course.getStudents().size() < course.getEnrollLimit()) {
            courses.add(course);
            course.enroll(this);
        } else if (!courses.contains(course) && !(course.getWaitList()).contains(this)) { //if student not already on course waitlist
            coursesOnWaitList.add(course);
            course.addToWaitList(this);
            System.out.println(this.getName() + " added to wait list: course has reached maximum enrollment.");
        } else {
            System.out.println("Could not add " + this.getName() + " to '" + course.getTitle() +"'. The student is already registered for that course or is on its waitlist.");
        }

    }

    // Removes student from a course they're already in, fills slot from waitlist if waitlist is non-empty.
    public void unenrollFrom(Course course) {
        if (course.getStudents().contains(this)) {
            courses.remove(course);
            course.unenroll(this);
            course.spacesOpened();
        } else if (course.getWaitList().contains(this)) {
            courses.remove(course);
        } else {
            System.out.println("Could not remove " + this.getName() + " from '" + course.getTitle() +"'. The student is not enrolled in that course or is on its waitlist.");
        }
    }

}
