package edu.macalester.registrar;

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

        // Example courses

        Course c1 = new Course();
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");

        System.out.println("------ Enrolling Sally in two courses ------");

        sally.enrollIn(c1);
        sally.enrollIn(c2);

        printSchedule(sally);
        printSchedule(fred);

        printEnrollment(c1);
        printEnrollment(c2);
        printWaitlist(c1);
        printWaitlist(c2);

        System.out.println("------ Enrolling Fred in one course but putting him on the wait-list ------");

        fred.enrollIn(c2);

        printSchedule(fred);
        printEnrollment(c2);
        printWaitlist(c2);

        System.out.println("------ Re-enrolling Sally has no effect ------");

        sally.enrollIn(c1);

        printSchedule(sally);
        printEnrollment(c1);
        printWaitlist(c1);

        System.out.println("------ Result of enrollment limit ------");
        printWaitlist(c1);
        printWaitlist(c2);

        System.out.println("------ Drop Sally and put Fred on wait-list ------");
        printEnrollment(c2);
        printWaitlist(c2);
        sally.drop(c2);
        printWaitlist(c2);
        printEnrollment(c2);
    }

    private static void printSchedule(Student student) {
        System.out.println("Student name: " + student.getName());
        System.out.println("Courses (" + student.getCourses().size() + ")");
        for(Course course : student.getCourses())
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
        System.out.println();
    }

    private static void printWaitlist(Course course) {
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());
        System.out.println("Students on wait list(" + course.getWaitlist().size() + ")");
        for(Student student : course.getWaitlist())
            System.out.println("    " + student.getName());
        System.out.println();
    }
}
