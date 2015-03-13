package edu.macalester.registrar;

/**
 * A simple scenario to exercise the various registrar model objects.
 */
public class RegistrarTest {
    public static void main(String[] args) {

        // Creating 6 students for tests

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

        // Creating 3 courses for tests

        Course c1 = new Course();
        c1.setEnrollmentLimit(2);    //Change enrollment limit here in order to test wait list functionality
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun");

        Course c2 = new Course();
        c2.setEnrollmentLimit(0);    //Testing if the enrollment limit is set to 0
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("Number Six");

        Course c3 = new Course();
       //Testing if enrollment limit is not set
        c3.setCatalogNumber("COMP 123");
        c3.setTitle("Neuro Comp");


        //sally.drop(c1); //testing drop if no one is enrolled/ yet.

        //Enrolling students
        System.out.println();
        System.out.println("Enrolling students:");
        System.out.println();
        sally.enrollIn(c1);
        fred.enrollIn(c1);
        judy.enrollIn(c1);
        ingrid.enrollIn(c1);
        ingrid.enrollIn(c2);
        evan.enrollIn(c2);
        jamey.enrollIn(c2);
        System.out.println();


        //Testing if enrollment limit is set to its default.
        sally.enrollIn(c3);
        fred.enrollIn(c3);
        judy.enrollIn(c3);
        ingrid.enrollIn(c3);
        evan.enrollIn(c3);
        jamey.enrollIn(c3);
        System.out.println();
        System.out.println();


        System.out.println("------ Testing the Wait list ------");
        System.out.println();
        printEnrollment(c1);
        printEnrollment(c3);

        System.out.println("------ Testing drop functionality ------");
        System.out.println();
        sally.drop(c1); //testing regular drop
        printEnrollment(c1);
        fred.drop(c1); //testing second drop
        printEnrollment(c1);
        printSchedule(sally);
        printSchedule(judy);
    }


    private static void printSchedule(Student student) {
        System.out.println();
        System.out.println("Schedule for " + student.getName() + ":");
        System.out.println("---------------");
        System.out.println("Student name: " + student.getName());
        System.out.println("Courses (" + student.getCourses().size() + ")");
        for(Course course : student.getCourses())
            System.out.println("    "
                + course.getCatalogNumber() + ": "
                + course.getTitle());
        System.out.println();
    }

    private static void printEnrollment(Course course) {
        System.out.println();
        System.out.println("Enrollment for " + course.getTitle() + ":");
        System.out.println("---------------");
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
