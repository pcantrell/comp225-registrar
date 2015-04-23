package edu.macalester.registrar;

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

    //leaving in because I think this is important functionality, even if it's not in the test.
    public Set<Course> getWaitListedCourses() {
        return Collections.unmodifiableSet(coursesOnWaitList);
    }


    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public boolean enrollIn(Course course) {
        if (course.getStudents().size() < course.getEnrollmentLimit()) {
            courses.add(course);
            course.enroll(this);
            return true;
        } else if (course.getWaitList().contains(this)) { //if student already on waitlist, pass back false since not enrolled in course
            return false;
        } else if (!courses.contains(course) && !(course.getWaitList()).contains(this)) { //if student not already on course waitlist, add them, return false since they were not actually added to the course
            coursesOnWaitList.add(course);
            course.addToWaitList(this);
            return false;
        } else { //student is already in course, reenrolling passes back true since they are in the course
            return true;
        }

    }

    // Removes student from a course they're already in, fills slot from waitlist if waitlist is non-empty.
    public void drop(Course course) {
        if (course.getStudents().contains(this)) {
            courses.remove(course);
            course.unenroll(this);
            course.spacesOpened();
        } else if (course.getWaitList().contains(this)) {
            coursesOnWaitList.remove(course);
            course.unenrollWaitList(this);
        } else {
            throw new IllegalArgumentException("Could not remove " + this.getName() + " from '" + course.getTitle() +"'. The student is not enrolled in that course or is on its waitlist.");
        }
    }

}
