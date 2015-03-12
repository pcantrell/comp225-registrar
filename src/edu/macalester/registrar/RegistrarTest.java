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

        Student judy = new Student();
        judy.setName("Judy");

        Student ingrid = new Student();
        ingrid.setName("Ingrid");

        Student jamey = new Student();
        jamey.setName("Jamey");

        Student evan = new Student();
        evan.setName("Evan");

        // Example courses

        Course c1 = new Course();
        c1.setEnrollmentLimit(2);    //Change enrollment limit here in order to test wait list functionality
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");

/*
        System.out.println("------ Enrolling Sally in two courses ------");

        sally.enrollIn(c1);
        //sally.enrollIn(c2);

        //printSchedule(sally);
        //printSchedule(fred);

        printEnrollment(c1);
        //printEnrollment(c2);

        System.out.println("------ Enrolling Fred in one course ------");

        fred.enrollIn(c2);

        printSchedule(fred);
        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Re-enrolling Sally has no effect ------");

        sally.enrollIn(c1);

        printEnrollment(c1);
        printSchedule(sally);

*/

        System.out.println("------ Testing the Wait list ------");


        sally.enrollIn(c1);
        fred.enrollIn(c1);
        judy.enrollIn(c1);
        ingrid.enrollIn(c1);
        evan.enrollIn(c1);
        jamey.enrollIn(c1);

        System.out.println("------ Testing drop functionality ------");

        sally.drop(c1);
        sally.drop(c1);
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
        System.out.println("Students on wait list: " + course.getWaitList().size() + " ");
        int count = 0;
        for (Student student : course.getWaitList()) {
            count ++;
            System.out.println("    " + count + ". " + student.getName());
        }
        System.out.println();
    }
}
