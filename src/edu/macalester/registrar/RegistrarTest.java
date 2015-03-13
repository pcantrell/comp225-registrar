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

        Student will = new Student();
        will.setName("Will");

        Student carly = new Student();
        carly.setName("Carly");

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

        // Testing enrollment limit

        c1.setEnrollmentLimit(2);

        System.out.println("------ Attempting to enroll Fred, Will, and Carly in COMP 225 ------");

        fred.enrollIn(c1);
        will.enrollIn(c1);
        carly.enrollIn(c1);

        printEnrollment(c1);
        printWaitList(c1);
        printSchedule(fred);
        printSchedule(will);
        printSchedule(carly);

        System.out.println("------ Re-enrolling Carly has no effect ------");

        carly.enrollIn(c1);

        printSchedule(carly);
        printEnrollment(c1);
        printWaitList(c1);

        // Testing dropping and wait list

        System.out.println("------ Sally drops COMP 225. Will is enrolled automatically and removed from the wait list ------");

        sally.dropFrom(c1);

        printEnrollment(c1);
        printWaitList(c1);
        printSchedule(sally);
        printSchedule(will);

        System.out.println("------ Carly drops from the wait list for COMP 225 ------");

        carly.dropFrom(c1);

        printSchedule(carly);
        printEnrollment(c1);
        printWaitList(c1);

        System.out.println("------ Carly re-enrolls in COMP 225 ------");

        carly.enrollIn(c1);

        printSchedule(carly);
        printEnrollment(c1);
        printWaitList(c1);

        System.out.println("------ Fred drops COMP 225. Carly is enrolled automatically and the wait list is emptied. ------");

        fred.dropFrom(c1);

        printEnrollment(c1);
        printWaitList(c1);
        printSchedule(fred);
        printSchedule(carly);

        System.out.println("------ Fred re-dropping COMP 225 has no effect ------");

        fred.dropFrom(c1);

        printEnrollment(c1);
        printWaitList(c1);
        printSchedule(fred);
        printSchedule(carly);

        System.out.println("------ Will drops from COMP 225 ------");

        will.dropFrom(c1);

        printEnrollment(c1);
        printWaitList(c1);
        printSchedule(will);
        printSchedule(carly);

        System.out.println("------ Finally, Carly drops from COMP 225 ------");

        printEnrollment(c1);
        printWaitList(c1);
        printSchedule(will);
        printSchedule(carly);
        printSchedule(fred);
        printSchedule(sally);

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
