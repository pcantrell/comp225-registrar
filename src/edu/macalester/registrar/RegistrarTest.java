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

        System.out.println("------ Enrolling Fred in one course ------");

        fred.enrollIn(c2);

        printSchedule(fred);
        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Re-enrolling Sally has no effect ------");

        sally.enrollIn(c1);

        printSchedule(sally);
        printEnrollment(c1);

        System.out.println("----- Dropping Sally from one course -----");

        sally.dropCourse(c1);

        printSchedule(sally);
        printEnrollment(c1);

        System.out.println("----- Re-Dropping Sally from one course -----");

        sally.dropCourse(c1);

        printSchedule(sally);
        printEnrollment(c1);

        System.out.println("----- Testing waitlist -----");
        c1.setCap(1);

        sally.enrollIn(c1);
        fred.enrollIn(c1);
        printEnrollment(c1);

        fred.setName("Fred");

        System.out.println("----- Testing waitlist and enrollment logic");

        sally.dropCourse(c1);
        printEnrollment(c1);
        printSchedule(sally);
        printSchedule(fred);

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
        ///System.out.println("pppop"); FOR TESTING
        for(Student student : course.getStudents())
            System.out.println("    " + student.getName());
        System.out.println();
    }
}
