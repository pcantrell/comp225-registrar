package edu.macalester.registrar;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();
    private LinkedList<Course> studentWaitList = new LinkedList<Course>();

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

            if (!course.getCourseStatus() & course.getEnrollLimit() > 0) { // course is not full
                courses.add(course);
                course.enroll(this);

            }

            else {
                studentWaitList.add(course);
                course.enroll(this);

            }

    }

    public void dropCourse(Course course) {
        this.courses.remove(course);
        Student newStud = course.getWaitList().pop(); // getPriority() pop student, manually remove
        System.out.println(newStud);
        System.out.println("dropCourse");
        newStud.enrollIn(course);

    }

}







