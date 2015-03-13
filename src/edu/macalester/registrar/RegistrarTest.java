package edu.macalester.registrar;

/**
 * A simple scenario to exercise the various registrar model objects.
 */
public class RegistrarTest {
    public static void main(String[] args) {
        enrollTest();
        enrollmentLimitTest();
        dropTest();
    }

    public static void enrollTest() {
        Course geography = createCourse("GEOG 314", "Geography and Lasers");
        Course math = createCourse("MATH 412", "Calculus with Apples");

        Student sally = createStudent("Sally");
        Student fred = createStudent("Fred");

        assert math.getStudents().size() == 0;
        assert math.getWaitList().size() == 0;
        assert sally.getCourses().size() == 0;

        sally.enrollIn(math);
        sally.enrollIn(geography);
        fred.enrollIn(math);

        // Math should have both Sally and Fred enrolled
        assert math.getStudents().contains(sally);
        assert math.getStudents().contains(fred);

        // Geography should have Sally enrolled
        assert geography.getStudents().contains(sally);

        // Sally should be enrolled in both Geography and Math
        assert sally.getCourses().contains(math);
        assert sally.getCourses().contains(geography);

        // Fred should be enrolled in Math
        assert fred.getCourses().contains(math);

        // Fred should not be enrolled in Geography
        assert !fred.getCourses().contains(geography);

        // Geography should not have Fred enrolled
        assert !geography.getStudents().contains(fred);
    }

    public static void enrollmentLimitTest() {
        Course geography = createCourse("GEOG 314", "Geography and Lasers");
        Course math = createCourse("MATH 412", "Calculus with Apples");

        Student sally = createStudent("Sally");
        Student greg = createStudent("Greg");
        Student fred = createStudent("Fred");
        Student emily = createStudent("Emily");

        // There should be no enrollment limit by default
        assert math.getEnrollmentLimit() == Course.NO_ENROLLMENT_LIMIT;
        assert geography.getEnrollmentLimit() == Course.NO_ENROLLMENT_LIMIT;

        // It should be possible to set an enrollment limit
        math.setEnrollmentLimit(2);
        assert math.getEnrollmentLimit() == 2;

        // Adding students up to the enrollment limit should work
        sally.enrollIn(math);
        greg.enrollIn(math);
        assert sally.getCourses().contains(math);
        assert greg.getCourses().contains(math);
        assert math.getStudents().contains(sally);
        assert math.getStudents().contains(greg);

        // Extra students added should go on the wait list
        boolean fredSucceeded = fred.enrollIn(math);
        boolean emilySucceeded = emily.enrollIn(math);
        assert !fredSucceeded;
        assert !emilySucceeded;
        assert !fred.getCourses().contains(math);
        assert !emily.getCourses().contains(math);
        assert !math.getStudents().contains(fred);
        assert !math.getStudents().contains(emily);
        assert math.getWaitList().get(0) == fred;
        assert math.getWaitList().get(1) == emily;

        // Increasing the enrollment limit should add students from the wait list
        math.setEnrollmentLimit(3);
        assert fred.getCourses().contains(math);
        assert math.getStudents().contains(fred);
        assert !emily.getCourses().contains(math);
        assert !math.getStudents().contains(emily);
        assert math.getWaitList().get(0) == emily;

        // Removing the enrollment limit should admit all students from the wait list
        math.setEnrollmentLimit(Course.NO_ENROLLMENT_LIMIT);
        assert emily.getCourses().contains(math);
        assert math.getStudents().contains(emily);
        assert math.getWaitList().isEmpty();

        // It should not be possible to reduce the enrollment limit once set,
        // unless it has subsequently been removed
        geography.setEnrollmentLimit(2);
        boolean reducedEnrollmentLimit = false;
        try {
            geography.setEnrollmentLimit(1);
            reducedEnrollmentLimit = true;
        } catch (IllegalArgumentException e) {

        }

        assert !reducedEnrollmentLimit;

        // It should not be possible to set the enrollment limit to a
        // value smaller than the number of students in the class
        geography.setEnrollmentLimit(Course.NO_ENROLLMENT_LIMIT);
        fred.enrollIn(geography);
        emily.enrollIn(geography);
        assert geography.getStudents().size() == 2;

        boolean setEnrollmentLimitTooSmall = false;
        try {
            geography.setEnrollmentLimit(1);
            setEnrollmentLimitTooSmall = true;
        } catch (IllegalArgumentException e) {

        }

        assert !setEnrollmentLimitTooSmall;
    }

    public static void dropTest() {
        Course math = createCourse("MATH 412", "Calculus with Apples");

        Student sally = createStudent("Sally");
        Student greg = createStudent("Greg");
        Student fred = createStudent("Fred");

        math.setEnrollmentLimit(2);

        sally.enrollIn(math);
        greg.enrollIn(math);
        fred.enrollIn(math);

        // Sally and Greg should both be enrolled in Math
        assert math.getStudents().contains(sally);
        assert math.getStudents().contains(greg);
        assert sally.getCourses().contains(math);
        assert greg.getCourses().contains(math);

        // Fred should not be enrolled in Math
        assert !math.getStudents().contains(fred);
        assert !fred.getCourses().contains(math);

        greg.drop(math);

        // After Greg drops Math, Sally and Fred should be in Math
        assert math.getStudents().contains(sally);
        assert math.getStudents().contains(fred);
        assert sally.getCourses().contains(math);
        assert fred.getCourses().contains(math);

        // Greg should no longer be in Math
        assert !math.getStudents().contains(greg);
        assert !greg.getCourses().contains(math);
    }

    private static Course createCourse(String catalogNumber, String title) {
        Course course = new Course();
        course.setCatalogNumber(catalogNumber);
        course.setTitle(title);
        return course;
    }

    private static Student createStudent(String name) {
        Student student = new Student();
        student.setName(name);
        return student;
    }
}
