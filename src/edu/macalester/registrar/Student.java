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
    public boolean enrollIn(Course course) {
        boolean bool = true;
        if (!(courses.contains(course))){

            if (course.enroll(this) == true){
                courses.add(course);
                bool = true;
            }
            else{
                bool = false;
            }
        }
        return bool;
    }

    public void drop(Course course){
            courses.remove(course);
            course.removeStudent(this);
    }

//   public void signWaitList(Student student){
//       if (!(course.getWaitList().contains(this))){
//           course.getWaitList().add(this);
//           course.
//       }
//       else{
//           System.out.println(getName()+" is already on the wait list.");
//           
//   }
}