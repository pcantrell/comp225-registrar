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

        Student greg = new Student();
        greg.setName("Greg");

        Student maya = new Student();
        maya.setName("Maya");

        Student phil = new Student();
        phil.setName("Phil");

        // Example courses

        Course c1 = new Course();
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");
        c1.setEnrollmentLimit(1);


        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");
        c2.setEnrollmentLimit(2);


        Course c3 = new Course();
        c3.setCatalogNumber("GEO 225");
        c3.setTitle("All About Seattle");
        c3.setEnrollmentLimit(3);


        System.out.println("------ Enrolling Sally in two courses (COMP 225 and MATH 6)------");

        sally.enrollIn(c1);
        sally.enrollIn(c2);

        printSchedule(sally);
        printSchedule(fred);

        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Enrolling Fred in one course (MATH 6) ------");

        fred.enrollIn(c2);

        printSchedule(fred);
        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Re-enrolling Sally in COMP 225 has no effect ------");

        sally.enrollIn(c1);

        printSchedule(sally);
        printEnrollment(c1);

        System.out.println("------ Enrolling Fred in course COMP 225 which is full ------");

        fred.enrollIn(c1);

        printSchedule(fred);
        printEnrollment(c1);

        System.out.println("------ Dropping Sally from course COMP 225 which is full ------");

        sally.dropCourse(c1);

        printSchedule(sally);
        printEnrollment(c1);

        System.out.println("------ Enrolling Sally, Fred, Maya and Greg in GEO 225 with enrollment size 3 ------");

        sally.enrollIn(c3);
        fred.enrollIn(c3);
        maya.enrollIn(c3);
        greg.enrollIn(c3);

        printSchedule(sally);
        printSchedule(fred);
        printSchedule(maya);
        printSchedule(greg);

        printEnrollment(c3);

        System.out.println("------ Enrolling Phil in course GEO 225 which is full ------");

        phil.enrollIn(c3);

        printSchedule(phil);
        printEnrollment(c3);

        System.out.println("------ Dropping Fred from course GEO 225 which is full ------");

        fred.dropCourse(c3);

        printSchedule(fred);
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
    }

    private static void printEnrollment(Course course) {
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());
        System.out.println("Students enrolled (" + course.getStudents().size() + ")");
        for(Student student : course.getStudents())
            System.out.println("    " + student.getName());
        System.out.println();
        System.out.println("Students wait listed (" + course.getWaitList().size() + ")");
        for(Student student : course.getWaitList())
            System.out.println("    " + student.getName());
        System.out.println();
    }
}
