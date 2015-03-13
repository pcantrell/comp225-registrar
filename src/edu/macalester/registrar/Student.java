package edu.macalester.registrar;

import java.util.ArrayList;
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
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */

    public void enrollIn(Course course) {
        if (course.getStudents().size() < course.getEnrollmentLimit()) {
            courses.add(course);
            course.enroll(this);
            System.out.println(this.getName() + " has been added to the course '" + course.getTitle() + ".'");


        } else {
            course.waitListEnroll(this);
            System.out.println("Sorry, we tried adding " + this.getName() + " to the course '" + course.getTitle() + "', but it's full! " + this.getName() + " has been added to the wait list.");
        }
    }

    public void drop(Course course) {
        Set enrolled = course.getStudents();
        if (enrolled.contains(this)) {
            course.drop(this);
            courses.remove(course);
            if (course.getWaitList().size() != 0) {
                Student firstOnWaitList = course.getWaitList().get(0);

                //System.out.println("course is being dropped. This is this.courses: " + this.courses);
                System.out.println();
                System.out.println(this.getName() + " has dropped '" + course.getTitle() + ".' " + firstOnWaitList.getName() + " was first on the wait list, so " + firstOnWaitList.getName() + " has now been enrolled. ");
                System.out.println();
                course.enroll(firstOnWaitList);
                course.getWaitList().remove(firstOnWaitList);
                firstOnWaitList.courses.add(course);
            }
        }
        else {      //if (!enrolled.contains(this))
            System.out.println();
            System.out.println("I think you made a mistake. " + this.getName() + " isn't in '" + course.getTitle() + ".'");
            System.out.println("There are " + (course.getEnrollmentLimit() - course.getStudents().size()) + " spots left in " + course.getTitle() + ".");
            System.out.println();
        }
    }
}
