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
        if (course.getStudents().contains(this)) { //if the student is already in the course, but tries to re-enroll when it is full, do nothing.
            System.out.println(this.getName() + " is already in the course '" + course.getTitle() + ".'");
            return true;
        }
        if (course.getWaitList().contains(this)) { //if student is already on wait list
            if (course.getStudents().size() < course.getEnrollmentLimit()) { //if there is room left
                System.out.println("==================");
                course.enroll(this);
                courses.add(course);
                //System.out.println(this.getName() + " has been added to the course '" + course.getTitle() + ".'");
                return true;
            } else {
                System.out.println(this.getName() + " has already requested enrollment in the course '" + course.getTitle() + ".' They are already on the wait list");
                return false;
                }
            }
            //change the below code to return course.enroll (and have the enroll method return a boolean so as not to repeat the check
            if (course.getStudents().size() < course.getEnrollmentLimit()) { //if there is room in the class, student gets added
                courses.add(course);
                course.enroll(this);
                return true;
            } else {
                course.enroll(this);
                return false;
            }
        }


    public void drop(Course course) {
        Set enrolled = course.getStudents();
        if (enrolled.contains(this)) { //if the student is in the course
            course.drop(this); //calling the course drop method
            courses.remove(course); //removing the course from the student's courses list
        } else { //if student is not in the course
            course.drop(this);
            System.out.println();
            System.out.println("I think you made a mistake. " + this.getName() + " isn't in '" + course.getTitle() + ".'");
            System.out.println("There are " + (course.getEnrollmentLimit() - course.getStudents().size()) + " spots left in " + course.getTitle() + ".");
            System.out.println();
        }
    }
}
