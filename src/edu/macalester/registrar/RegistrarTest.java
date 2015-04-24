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

        Student marie = new Student();
        marie.setName("Marie");

        // Example courses

        Course c1 = new Course();
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");
        c1.setEnrollmentLimit(2);

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");
        c2.setEnrollmentLimit(1);

        Course c3 = new Course();
        c3.setCatalogNumber("CHIN 194");
        c3.setTitle("Masterpieces in Chinese Literature");
        c3.setEnrollmentLimit(5);

        System.out.println("------ Enrolling Sally in two courses ------");

        sally.enrollIn(c1);
        sally.enrollIn(c2);
        sally.enrollIn(c3);

        printSchedule(sally);
        printSchedule(fred);

        System.out.println("------ Printing Course Enrollment so far ------");

        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Enrolling Fred in two course, should be waitlisted on Math ------");

        fred.enrollIn(c1);
        fred.enrollIn(c2);
        printSchedule(fred);

        System.out.println("------ Printing enrollment and wait lists of all courses ------");

        printEnrollment(c1);
        printEnrollment(c2);
        printWaitList(c1);
        printWaitList(c2);

        System.out.println("------ Enrolling Marie into both courses, she should be waitlisted for both ------");
        marie.enrollIn(c1);
        marie.enrollIn(c2);
        printSchedule(marie);

        System.out.println("------ Printing enrollment and wait lists of all courses ------");

        printEnrollment(c1);
        printEnrollment(c2);
        printWaitList(c1);
        printWaitList(c2);

        System.out.println("------ Sally drops math and chin course, Fred should be able to get in now ------");

        sally.drop(c2);
        sally.drop(c3);
        printSchedule(sally);
        printSchedule(fred);
        printSchedule(marie);
        printEnrollment(c1);
        printEnrollment(c2);
        printEnrollment(c3);

        System.out.println("------ Sally changes her mind and adds the course again, should be waitlisted because class full ------");

        sally.enrollIn(c2);
        printSchedule(sally);
        printEnrollment(c2);
        printWaitList(c2);

        System.out.println("------ Re-enrolling Sally has no effect ------");

        sally.enrollIn(c1);
        printSchedule(sally);
        printEnrollment(c1);
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

    private static void printWaitList(Course course) {
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());
        System.out.println("Students wait-listed (" + course.getWaitList().size() + ")");
        for(Student student : course.getWaitList())
            System.out.println("    " + student.getName());
        System.out.println();
    }
}
