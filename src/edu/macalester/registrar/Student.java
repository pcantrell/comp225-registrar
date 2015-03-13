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
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public void enrollIn(Course course) {

        if(Student.this.getCourses().contains(course)){
            System.out.println("You are already registered for this course");
        }
        else{
            if(course.getStudents().size() < course.getEnrollmentLimit()) {
                courses.add(course);
                course.enroll(this);
            }
            else {
                course.addToWaitList(this);
                System.out.println(this.getName() + " " + "You have been added to the waiting list for " + course.getTitle());
            }
        }
    }

    public void dropCourse(Course course) {
        //Student.this.getCourses().remove(course);
        courses.remove(course);
        course.drop(this);
        if(course.getWaitList()!= null){
            course.getFirstPerson().enrollIn(course);
        }
    }
}
