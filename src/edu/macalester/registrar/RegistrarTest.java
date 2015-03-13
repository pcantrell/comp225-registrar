package edu.macalester.registrar;

import java.util.ArrayList;

/**
 * A simple scenario to exercise the various registrar model objects.
 */
public class RegistrarTest {
    public static void main(String[] args) {
        // Example students

        Student sally = new Student();
        sally.setName("Sally");

        Student fred = new Student();
        fred.setName("Fred");

        Student chris = new Student();
        chris.setName("Chris");

        Student linda = new Student();
        linda.setName("Linda");

        Student jenny = new Student();
        jenny.setName("Jenny");
        // Example courses

        Course c1 = new Course();
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");

        Course c3 = new Course();
        c3.setCatalogNumber("ART/COMP 400");
        c3.setTitle("Computer Animation");

        System.out.println("------ Enrolling Sally in two courses ------");

        sally.enrollIn(c1);
        sally.enrollIn(c2);

        printSchedule(sally);
        printSchedule(fred);

        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Enrolling Fred in one course ------");

        fred.enrollIn(c2);

        printSchedule(fred);
        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Re-enrolling Sally has no effect ------");

        sally.enrollIn(c1);

        printSchedule(sally);
        printEnrollment(c1);

        System.out.println("------ Part 1: Enrolling a student into a full class ------");

        //For all courses, course.getStudents().size() ≤ course.getEnrollmentLimit().
        //Over-enrolling must signal failure in a way that lets the calling code handle the error gracefully.
        jenny.enrollIn(c2); //Expect to see a message saying Jenny can't enroll because course is full.
        chris.enrollIn(c2);
        linda.enrollIn(c2);

        System.out.println("------ Part 2: Adding a student to a wait list. ------");

        //Add a wait list to courses. If course is full, they automatically go on the wait list.
        //This is not an error condition; however, the enroll() method should let the caller know
        //whether the enrollment was successful or the student was waitlisted.

        printEnrollment(c2); //Show that the course is full
        printWaitList(c2); //and show that we have a wait list for the full course

        printSchedule(jenny); //Show that a student is never both enrolled in and wait listed for the same course.
        printSchedule(chris); //Currently, none of them can enroll yet
        printSchedule(linda); //this is just to show that their schedules are empty while they are on the waitlist for a class

        linda.enrollIn(c3); //this is to show student can enroll in classes with empty seats
        printSchedule(linda);
        printWaitList(c2); //and it does not affect their waitlist for another course

        printWaitList(c1);//If a course is not full..
        printEnrollment(c1); //show that its wait list is empty

        System.out.println("------ Part 3: Drop. ------");

        //Give students the ability to drop courses.
        //If an enrolled student drops, then the first wait-listed student is automatically enrolled.

        fred.dropCourse(c2); //fred finds a new passion in geography and poli sci, with a global health concentration!

        printSchedule(fred); //Show that student's schedule is updated
        printSchedule(jenny); //Show that new student knows they're enrolled now in class
        printEnrollment(c2); //Show that class accepted new student
        printWaitList(c2); //Show that waitlist has been updated

        System.out.println("------ Attempt at extra credit: Customize enrollment ------");

        //For (modest) extra credit: Make courses impose no enrollment limit by default.
        // Make it possible to lift the enrollment limit after one is set.

        c3.setEnrollmentLimit(4);

        chris.enrollIn(c3);
        jenny.enrollIn(c3);
        sally.enrollIn(c3);
        fred.enrollIn(c3);

        chris.dropCourse(c1);

    }

    private static void printSchedule(Student student) {
        System.out.println();
        System.out.println("Student name: " + student.getName());
        System.out.println("Courses (" + student.getCourses().size() + ")");
        for(Course course : student.getCourses()){
            System.out.println("    "
                + course.getCatalogNumber() + ": "
                + course.getTitle());
        }
    }

    private static void printEnrollment(Course course) {
        System.out.println();
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());
        System.out.println("Students enrolled (" + course.getStudents().size() + ")");
        for(Student student : course.getStudents())
            System.out.println("    " + student.getName());
    }

    private static void printWaitList(Course course) {
        System.out.println();
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle()+" WAIT LIST");
        for(Student student : course.getWaitList())
            System.out.println("    " + student.getName());
    }
}