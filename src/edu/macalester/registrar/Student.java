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
        if (course.getRemainingSeats() != 0){
            courses.add(course);
            course.enroll(this);
            System.out.println(getName()+" successfully enrolled in "+course.getTitle()+".");
        }
        else{
            System.out.println(this.getName()+" cannot enroll in "+course.getTitle()+" because it's full.");
            signWaitList(course);
            System.out.println();
        }
    }

    public void dropCourse(Course course){
        if (courses.contains(course)){
            System.out.println(getName()+" is dropping "+course.getTitle()+".");
            course.removeStudent(this);
            courses.remove(course);
        }
        else {
            System.out.println(getName()+" can't drop "+course.getTitle()+" because they're not enrolled.");
        }
    }

   public void signWaitList(Course course){
       ArrayList<Student> waitList = course.getWaitList();
       if (!(waitList.contains(this))){
           waitList.add(this);
       }
       else{
           System.out.println(getName()+" is already on the wait list.");
       }
   }
}