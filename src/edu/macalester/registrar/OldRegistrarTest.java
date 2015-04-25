//package edu.macalester.registrar;
//
///**
// * A simple scenario to exercise the various registrar model objects.
// */
//public class OldRegistrarTest {
//    public static void main(String[] args) {
//        // Example students
//
//        Student sally = new Student();
//        sally.setName("Sally");
//
//        Student fred = new Student();
//        fred.setName("Fred");
//
//        Student ben = new Student();
//        ben.setName("Ben");
//
//        Student nathan = new Student();
//        nathan.setName("Nathan");
//
//        // Example courses
//
//        Course c1 = new Course();
//        c1.setCatalogNumber("COMP 225");
//        c1.setTitle("Software Fun Fun");
//        c1.setEnrollmentLimit(2);
//
//        Course c2 = new Course();
//        c2.setCatalogNumber("MATH 6");
//        c2.setTitle("All About the Number Six");
//        c2.setEnrollmentLimit(2);
//
//        Course c3 = new Course();
//        c3.setCatalogNumber("COMP 261");
//        c3.setTitle("Theory of Computation");
//        c3.setEnrollmentLimit(2);
//
//        System.out.println("------ Enrolling Sally in two courses ------");
//        sally.enrollIn(c1);
//        sally.enrollIn(c2);
//
//        System.out.println("STUDENT SCHEDULES");
//        printSchedule(sally);
//        printSchedule(fred);
//        System.out.println("COURSE ENROLLMENTS");
//        printEnrollment(c1);
//        printEnrollment(c2);
//
//        System.out.println("------ Enrolling Fred in one course ------");
//        fred.enrollIn(c2);
//
//        System.out.println("STUDENT SCHEDULES");
//        printSchedule(fred);
//        System.out.println("COURSE ENROLLMENTS");
//        printEnrollment(c1);
//        printEnrollment(c2);
//
//        System.out.println("------ Re-enrolling Sally has no effect ------");
//        sally.enrollIn(c1);
//
//        System.out.println("STUDENT SCHEDULES");
//        printSchedule(sally);
//        System.out.println("COURSE ENROLLMENTS");
//        printEnrollment(c1);
//
//        System.out.println("------ # of students never exceeds allowed enrollment maximum------");
//        ben.enrollIn(c2);
//
//        System.out.println("STUDENT SCHEDULES");
//        printSchedule(ben);
//        System.out.println("COURSE ENROLLMENTS");
//        printEnrollment(c2);
//
//        System.out.println("------ A student is never both enrolled in and wait-listed for the same course------");
//        //c1 has 1 student, sally, and max enrollment of 2. I enroll ben once (successfully), then I
//        //try to add enroll hom again, and then try to add fred to the course who is not in there initially
//        //as ben is.
//        ben.enrollIn(c1);
//        ben.enrollIn(c1);
//        fred.enrollIn(c1);
//
//        System.out.println("STUDENT SCHEDULES");
//        printSchedule(ben);
//        printSchedule(fred);
//        System.out.println("COURSE ENROLLMENTS");
//        printEnrollment(c1);
//
//        System.out.println("------ A course never has a waitlist if it's not full------");
//        //create a new course, c3, set max enrollment to 2 and enroll fred in it.
//        fred.enrollIn(c3);
//
//        System.out.println("STUDENT SCHEDULES");
//        printSchedule(fred);
//        System.out.println("COURSE ENROLLMENTS");
//        printEnrollment(c3);
//
//        System.out.println("------ Students can drop courses, in which case the student first on the wait list gets enrolled------");
//        //sally and ben are enrolled in c1, c1 also has fred and a new student, nathan, on the wait list (respectively).
//        // In the second part of this test sally drops, and fred is enrolled.
//        nathan.enrollIn(c1);
//
//        System.out.println("STUDENT SCHEDULES");
//        printSchedule(nathan);
//        System.out.println("COURSE ENROLLMENTS");
//        printEnrollment(c1);
//
//        sally.dropFrom(c1);
//
//        System.out.println("STUDENT SCHEDULES");
//        printSchedule(sally);
//        printSchedule(fred);
//        System.out.println("COURSE ENROLLMENTS");
//        printEnrollment(c1);
//    }
//
//    private static void printSchedule(Student student) {
//        System.out.println("Student name: " + student.getName());
//        System.out.println("Courses (" + student.getCourses().size() + ")");
//        for(Course course : student.getCourses())
//            System.out.println("    "
//                + course.getCatalogNumber() + ": "
//                + course.getTitle());
//        System.out.println("Wait List Courses (" + student.getWaitListCourses().size() + ")");
//        for(Course course : student.getWaitListCourses())
//            System.out.println("    "
//                    + course.getCatalogNumber() + ": "
//                    + course.getTitle());
//    }
//
//    private static void printEnrollment(Course course) {
//        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());
//        System.out.println("Students enrolled " + course.getStudents().size() + " (out of " + course.getEnrollmentLimit() + ")");
//        for(Student student : course.getStudents())
//            System.out.println("    " + student.getName());
//        System.out.println("Wait List Students (" + course.getWaitList().size() + ")");
//        for(Student student : course.getWaitList())
//            System.out.println("    " + student.getName());
//    }
//}
