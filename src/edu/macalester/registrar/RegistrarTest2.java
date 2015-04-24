package edu.macalester.registrar;

/**
 * Modified enrollment tests
 * Created by Taylor Rose on 3/13/2015.
 */
public class RegistrarTest2 {
    public static void main(String[] args) {
        // Example students

        Student sally = new Student();
        sally.setName("Sally");

        Student fred = new Student();
        fred.setName("Fred");

        Student dave = new Student();
        dave.setName("Dave");

        // Example courses

        Course c1 = new Course();
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");

        Course c3 = new Course();
        c3.setCatalogNumber("THDA 4");
        c3.setTitle("Computers and Performance");
        c3.setEnrollmentLimit(2);

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

        System.out.println("------ Enrolling all students in third course ------");

        sally.enrollIn(c3);
        fred.enrollIn(c3);
        dave.enrollIn(c3);

        printSchedule(sally);
        printSchedule(fred);
        printSchedule(dave);
        printEnrollment(c3);

        System.out.println("------- Unenroll Sally, remove dave from waitlist -------");

        sally.drop(c3);

        printSchedule(sally);
        printSchedule(dave);
        printEnrollment(c3);
    }

    private static void printSchedule(Student student) {
        System.out.println("Student name: " + student.getName());
        System.out.println("Courses (" + student.getCourses().size() + ")");
        for(Course course : student.getCourses())
            System.out.println("    "
                    + course.getCatalogNumber() + ": "
                    + course.getTitle());
        System.out.println();
        System.out.println("Wait Listed Courses (" + student.getWaitListedCourses().size() + ")" );
        for (Course course : student.getWaitListedCourses())
            System.out.println("     " + course.getCatalogNumber() + ": " + course.getTitle());
        System.out.println();
    }

    private static void printEnrollment(Course course) {
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());
        System.out.println("Students enrolled (" + course.getStudents().size() + ")");
        for(Student student : course.getStudents())
            System.out.println("    " + student.getName());
        System.out.println();
        printWaitList(course);
    }

    private static void printWaitList(Course course) {
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());
        System.out.println("Students waitlisted (" + course.getWaitList().size() + ")");
        for (Student student : course.getWaitList())
            System.out.println("     " + student.getName());
        System.out.println();
    }
}

