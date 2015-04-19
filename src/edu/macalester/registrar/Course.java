package edu.macalester.registrar;

import java.util.*;


public class Course {
    private String catalogNumber, title;
    public static int NO_ENROLLMENT_LIMIT = Integer.MAX_VALUE;
    private int enrollLimit = NO_ENROLLMENT_LIMIT;
    private Set<Student> students = new HashSet<Student>();
    private List<Student> waitList = new LinkedList<Student>();


    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEnrollmentLimit() {
        return enrollLimit;
    }

    public void setEnrollmentLimit(int limit) {
        if (limit >= students.size()) { //covers case where setting lim = 0 but no one enrolled
            this.enrollLimit = limit;
            spacesOpened();
        } else if (limit == 0) {
            /* Special case: class is temporarily closed, but we want to hang on to list of students
             * (perhaps prof had an emergency and they don't know if they can get someone else in?)
             * All students who were in the class go are put into the beginning of the waitlist in no particular order,
             * and then those who were on the waitlist are stuck onto the end in order.
             * Then, if the course size was made larger, the waitlist still follows the who-signed-up-first-gets-in-first
             * policy. Of course, that's screwed if the size of the class is smaller than it was before, as students
             * who were in the class before are picked at random to be in the new class.
             * We know students is non-empty because otherwise it would fall under the first if condition. */
            LinkedList<Student> temp = new LinkedList<Student>();
            for (Student student : students) {
                temp.add(student);
            }
            while (waitList.size() != 0) {
                temp.add(waitList.remove(0));
            }
            waitList = temp;
            students = new HashSet<Student>(); //reset the student list, removing all of them

        } else {
            throw new IllegalArgumentException("Operation failed. There are currently more students in the class than allowed by that limit. Remove some and try again.");
        }

    }

    public void liftEnrollLimit() {
        this.enrollLimit = NO_ENROLLMENT_LIMIT;
        spacesOpened();
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public List<Student> getWaitList() { return Collections.unmodifiableList(waitList); }

    void enroll(Student student) { students.add(student); }

    //only gets called from Student; is package private
    void unenroll(Student student) { students.remove(student);}

    //only gets called from Student; is package private
    void unenrollWaitList(Student student) { waitList.remove(student);}

    void addToWaitList(Student student){
        if(!waitList.contains(student)){
            waitList.add(student);
        } else {
            throw new IllegalArgumentException("Operation failed: The student is already on that course's waitlist.");
        }
    }

    //called whenever spaces are opened in a course so that students on wait list are put into the course
    void spacesOpened() {
        if(waitList.size() > 0) {
            while (true) {
                Student student = waitList.remove(0);
                student.enrollIn(this);
                if (students.size() == enrollLimit || waitList.size() == 0) {
                    break;
                }
            }
        }
    }
}
