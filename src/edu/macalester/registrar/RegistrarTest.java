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
        c1.setEnrollmentLimit(1);

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");
        c2.setEnrollmentLimit(2);

        System.out.println("------ Enrolling Sally in two courses ------");

        sally.enrollIn(c1);
        sally.enrollIn(c2);
        printSchedule(sally);


        System.out.println("------ Enrolling Fred in two course ------");

        fred.enrollIn(c1); //Fred will be added to the wait list
        fred.enrollIn(c2);
        printSchedule(fred);
        printEnrollment(c1);
        printEnrollment(c2);
        printWaitList(c1); //Fred is on the wait list

        System.out.println("------ Re-enrolling Sally has no effect ------");

        sally.enrollIn(c2);
        printSchedule(sally);
        printEnrollment(c2);
        printWaitList(c2); // wait list is still empty.

        System.out.println("--------- Course EnrollmentLimit ----------");
        printEnrollmentLimit(c1);
        printEnrollmentLimit(c2);

        System.out.println("--------- Course Wait List ------------");
        printWaitList(c1);
        printWaitList(c2);

        System.out.println("---------- Dropping Sally from a course -------------");
        sally.dropCourse(c1);
        printSchedule(sally);
        printSchedule(fred); //fred is added to course.
        printWaitList(c1);  // the wait list is now empty.
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

    private static void printEnrollmentLimit(Course course) {
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle() + ": " + ": enrollment size " + ": " + course.getEnrollmentLimit());
    }

    private static void printWaitList(Course course) {
        System.out.println("The wait list for: "+ " " + course.getTitle() + ":" + course.getWaitList() );
    }
}
