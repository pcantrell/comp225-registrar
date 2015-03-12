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

        Student joe = new Student();
        joe.setName("Joe");
        // Example courses

        Course c1 = new Course();
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");
        c1.setCourseLimit(3);

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");
        c2.setCourseLimit(1);

        System.out.println("------ Enrolling Sally in two courses ------");

        sally.enrollIn(c1);
        sally.enrollIn(c2);

        printSchedule(sally);
        printSchedule(fred);

        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Enrolling Fred in one course ------");

        fred.enrollIn(c1);

        printSchedule(fred);
        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Enrolling Joe in two course (one is full) ------");
        
        joe.enrollIn(c1);
        joe.enrollIn(c2);

        printSchedule(joe);
        printEnrollment(c1);
        printEnrollment(c2);

        System.out.println("------ Re-enrolling Sally has no effect ------");

        sally.enrollIn(c1);

        printSchedule(sally);
        printEnrollment(c1);

        System.out.println("------ Drop Sally from course ------");

        sally.dropCourse(c2);

        printSchedule(joe);
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
}
