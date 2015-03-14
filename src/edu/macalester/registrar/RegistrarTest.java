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

        Student bob = new Student();
        bob.setName("Bob");

        // Example courses

        Course c1 = new Course();
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");

        Course c3 = new Course();
        c3.setCatalogNumber("ART 1");
        c3.setTitle("What is Art?");

        System.out.println("------ Enrolling Sally in two courses ------");

        sally.enrollIn(c1);
        sally.enrollIn(c2);

        printSchedule(sally);
        printSchedule(fred);
        printSchedule(bob);

        printEnrollment(c1);
        printEnrollment(c2);
        printEnrollment(c3);

        System.out.println("------ Enrolling Fred in one course ------");

        fred.enrollIn(c2);

        printSchedule(fred);
        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Re-enrolling Sally has no effect ------");

        sally.enrollIn(c1);

        printSchedule(sally);
        printEnrollment(c1);

        System.out.println("------ Tests setting enrollment limit of 1 and creating a wait list------");
        c1.setEnrollmentLimit(1);
        bob.enrollIn(c1);
        fred.enrollIn(c1);

        printSchedule(bob);
        printWaiting(bob);
        printWaitList(c1);

        System.out.println("------ Tests setting enrollment limit smaller than current class size ------");

        bob.enrollIn(c3);
        fred.enrollIn(c3);
        try{
            c3.setEnrollmentLimit(1);
        }catch(IllegalArgumentException e) {
            System.out.println("Could not set an enrollment limit smaller than the current class size.");

        }

        System.out.println("------ Tests dropping a class ------");

        sally.dropCourse(c1);

        printEnrollment(c1);
        printWaitList(c1);
        printSchedule(sally);
        printSchedule(bob);
        printWaiting(bob);


        System.out.println("------ Tests lifting enrollment limit ------");


        c1.liftEnrollmentLimit();

        printEnrollment(c1);
        printWaitList(c1);
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
        for(Student student : course.getStudents())
            System.out.println("    " + student.getName());
        System.out.println();
    }

    private static void printWaiting(Student student) {
        System.out.println("Student name: " + student.getName());
        System.out.println("On the Wait list for (" + student.getWaiting().size() + ")");
        for(Course course : student.getWaiting())
            System.out.println("    "
                    + course.getCatalogNumber() + ": "
                    + course.getTitle());
        System.out.println();
    }

    private static void printWaitList(Course course) {
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());
        System.out.println("Students on wait list (" + course.getWaitList().size() + ")");
        for(Student student : course.getWaitList())
            System.out.println("    " + student.getName());
        System.out.println();
    }
}
