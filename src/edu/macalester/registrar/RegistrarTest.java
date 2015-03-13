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

        Student jay = new Student();
        jay.setName("Jay");

        // Example courses

        Course c1 = new Course();
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");

        Course c3 = new Course();
        c3.setCatalogNumber("COMP 123");
        c3.setTitle("Python Yay");

//        System.out.println("------ Enrolling Sally in two courses ------");
//
//        sally.enrollIn(c1);
//        sally.enrollIn(c2);
//
//        printSchedule(sally);
//        printSchedule(fred);
//
//        printEnrollment(c1);
//        printEnrollment(c2);
//
//        System.out.println("------ Enrolling Fred in one course ------");
//
//        fred.enrollIn(c2);
//
//        printSchedule(fred);
//        printEnrollment(c1);
//        printEnrollment(c2);
//
//        System.out.println("------ Re-enrolling Sally has no effect ------");
//
//        sally.enrollIn(c1);
//
//        printSchedule(sally);
//        printEnrollment(c1);

        System.out.println("------ Testing Enroll Limit and Wait List ------");

        c3.setEnrollLimit(2); // toggle restriction to view behavior
        c3.getEnrollLimit();
        /*
        Expected Behavior, (0) => all students go to waitList
                           (1) => jay enrolls, bob and sally in waitList
                           (2) => jay, bob enroll, sally in waitList
                           (3+) => all students enroll successfully
         */
        System.out.println();
        System.out.println("Course 3 WaitList: " + c3.getWaitList());
        System.out.println("Enroll Jay");
        System.out.println();
        jay.enrollIn(c3);
        System.out.println("Students in C3: " + c3.getStudents().size());
        System.out.println("Course 3 Status:  " + c3.getCourseStatus());
        System.out.println();
        System.out.println("Enroll bob");
        bob.enrollIn(c3);
        System.out.println("Course 3 Status:  " + c3.getCourseStatus());
        System.out.println("Course 3 WaitList: " + c3.getWaitList());
        System.out.println();
        System.out.println("Enroll sally");
        sally.enrollIn(c3);
        System.out.println("Course 3 WaitList: " + c3.getWaitList());
        System.out.println();

        printSchedule(jay);
        printSchedule(bob);
        printSchedule(sally);

        System.out.println("------ Testing Student Drop Course ------"); // test for Enroll

        jay.dropCourse(c3); // jay drops a course
        printSchedule(jay);

        printSchedule(sally); // in state (2) sally will be enrolled in c3
        System.out.println("Course 3 WaitList: " + c3.getWaitList());


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

    private static void printWaitList(Course course){
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());
        System.out.println("Students in wait list (" + course.getWaitList() + ")");
        for (Student student : course.getWaitList())
            System.out.println("    " + course.getStudents());
        System.out.println();
    }
}
