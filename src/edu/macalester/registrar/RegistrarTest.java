
//Clare Speer 
//March 13, 2015

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
        
        Student asra = new Student();
        asra.setName("Asra Cool Nizami");
        
        Student maya = new Student();
        maya.setName("Maya");

        // Example courses

        Course c1 = new Course();
        c1.setCatalogNumber("COMP 225");
        c1.setTitle("Software Fun Fun");
        c1.setEnrollmentLimit(25);

        Course c2 = new Course();
        c2.setCatalogNumber("MATH 6");
        c2.setTitle("All About the Number Six");
        c2.setEnrollmentLimit(2);

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
        
        System.out.println("------ Waitlist for a not-full class is empty ------");
        
        printWaitlist(c2);
        
        System.out.println("------ Enrolling Asra in a full course ------");
        
        asra.enrollIn(c2);
        
        printSchedule(asra);
        printEnrollment(c2);
        
        System.out.println("------Class was full, so Asra is added to waitlist------");
        
        printWaitlist(c2);
        
        System.out.println("------Trying to enroll Maya in the full course------");
        
        maya.enrollIn(c2);
        printEnrollment(c2);
        printSchedule(maya);
        printWaitlist(c2);
        
        System.out.println("------Sally drops out of All About the Number Six------");
        
        sally.drop(c2);
        printSchedule(sally);
        
        System.out.println("------Asra automatically enrolled off of the waitlist------");
        
        printEnrollment(c2);
        printSchedule(asra);
        printWaitlist(c2);
        
        System.out.println("------Fred drops out and Maya joins------");
        
        fred.drop(c2);
        printEnrollment(c2);
        printSchedule(maya);
        printSchedule(fred);
        printWaitlist(c2);
        
        System.out.println("------Dropping out of a class you don't take has no effect------");
        
        asra.drop(c1);
        printSchedule(asra);
        
        
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
    
    private static void printWaitlist(Course course){
        System.out.println(course.getCatalogNumber() + ": " + course.getTitle());
        System.out.println("Students on waitlist (" + course.getWaitList().size() + ")");
        for(Student student : course.getWaitList())
            System.out.println("    " + student.getName());
        System.out.println();
    }
      
}
