package tests;

import edu.macalester.registrar.Course;
import edu.macalester.registrar.Student;

/**
 * A simple scenario to exercise the various registrar model objects.
 *
 * This folder is in a separate package so package private works only for Course and Student sharing methods.
 */
public class RegistrarTest {
    public static void main(String[] args) {

        // Example students
        Student sally = new Student();
        sally.setName("Sally");

        Student fred = new Student();
        fred.setName("Fred");

        Student susan = new Student();
        susan.setName("Susan");

        Student peri = new Student();
        peri.setName("Peri");


        // Example courses
        Course c1 = new Course();
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");


        System.out.println("------ Setting enroll limit to 1 for MATH 6------");
        c2.setEnrollLimit(1);

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
        sally.enrollIn(c2);

        printSchedule(sally);
        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Re-enrolling Fred for MATH 6 has no effect ------");

        fred.enrollIn(c2);

        printEnrollment(c2);

        System.out.println("------ Trying to change enroll limit of Math 6 to 3------");
        c2.setEnrollLimit(3);
        System.out.println("Enroll limit for course: " + c2.getEnrollLimit());

        printEnrollment(c2);

        System.out.println("------ Enrolling Susan and Peri in Math 6 ------");

        susan.enrollIn(c2);
        peri.enrollIn(c2);

        printEnrollment(c2);

        System.out.println("------ Math 6 is too easy for Susan, take her out ------");

        susan.unenrollFrom(c2);

        printEnrollment(c2);

        System.out.println("------ A new class is added - it fulfills all the gen-ed requirements! ------");
        System.out.println("------ Everyone wants to take it! Unfortunately, the enroll limit is 2.------");

        //adding a new example course
        Course c3 = new Course();
        c3.setCatalogNumber("OMNI 100");
        c3.setTitle("Multicultural Writing & Internationalism with Queues");
        c3.setEnrollLimit(2);

        susan.enrollIn(c3);
        peri.enrollIn(c3);
        sally.enrollIn(c3);
        fred.enrollIn(c3);

        System.out.println("------ The administration is not keen on this idea.------");
        System.out.println("------ They're putting the course on hold, setting the enroll limit to 0.------");
        c3.setEnrollLimit(0);

        printEnrollment(c3);

        System.out.println("------ But no! They are convinced to let it go through, and the enroll limit is set to 3. ------");
        c3.setEnrollLimit(3);

        printEnrollment(c3);

        System.out.println("------ Students make a petition. All should be able to take OMNI 100. ------");
        System.out.println("            ------ The enrollment limit is lifted. ------");

        c3.liftEnrollLimit();

        printEnrollment(c3);

        System.out.println("------ Try to put the enrollment limit back on: (should fail) ------");
        c3.setEnrollLimit(2);
        printEnrollment(c3);


    }

    private static void printSchedule(Student student) {
        System.out.println("Student name: " + student.getName());

        System.out.println("Courses (" + student.getCourses().size() + ")");
        for(Course course : student.getCourses())
            System.out.println("    "
                + course.getCatalogNumber() + ": "
                + course.getTitle());

        System.out.println("Waitlisted Courses (" + student.getWaitListedCourses().size() + ")");
        for(Course course : student.getWaitListedCourses())
            System.out.println("    "
                    + course.getCatalogNumber() + ": "
                    + course.getTitle());
        System.out.println();

    }

    private static void printEnrollment(Course course) {
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());

        System.out.println("Students enrolled (" + course.getStudents().size() + ")");
        for(Student student : course.getStudents())
            System.out.println("    " + student.getName());

        System.out.println("Students on wait list (" + course.getWaitList().size() + ")");
        for(Student student : course.getWaitList())
            System.out.println("    " + student.getName());

        System.out.println();
    }
}
