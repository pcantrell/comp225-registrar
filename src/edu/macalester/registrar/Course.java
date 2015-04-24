package edu.macalester.registrar;

import java.util.*;

public class Course {
    public static int NO_ENROLLMENT_LIMIT =  2147483647;
    private String catalogNumber, title;
    private Set<Student> students = new HashSet<Student>();
    private int enrollCap;
    private List<Student> waitlist = new ArrayList<Student>();


        public Course() {
            enrollCap = NO_ENROLLMENT_LIMIT;
        }


        public List<Student> getWaitList() {
            return Collections.unmodifiableList(waitlist);
        }

        public void setWaitList(List<Student> waitlist) {
            this.waitlist = waitlist;
        }



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

        public int getEnrollmentLimit() {return enrollCap;}

        public void setEnrollmentLimit(int newEnrollCap) {
            if(newEnrollCap>=getStudents().size()){
                this.enrollCap = newEnrollCap;
                enrollCapChangeListener();
        }
            else throw new IllegalArgumentException();
        }
        public void enrollCapChangeListener(){
            while((enrollCap>students.size())&&(waitlist.iterator().hasNext())){
                Student temp = waitlist.iterator().next();
                waitlist.remove(temp);
                temp.enrollIn(this);

            }
        }
        public Set<Student> getStudents() {
            return Collections.unmodifiableSet(students);
            //Why not return students
            //Instead of Collections.unmodifiableSet(students)
        }

        public Boolean enroll(Student student) {
            // System.out.println(waitlist);
            //check for name in waitlist or already in course
            //So the add function of Set will add to a set if the element is not already
            if (waitlist.contains(student) || students.contains(student)) {
                System.out.println("Student cannot be enrolled. Is either already enrolled or already on waitlist.");
                return false;
            }

            //check if cap is exceeded. If not exceeded, adds to course. If exceeded, adds to waitlist instead.
            if ((enrollCap > students.size())){
                students.add(student);
                return true;
            } else {
                System.out.println("Enrollment unsuccessful. Class is full. You have been added to the waitlist.");
                waitlist.add(student);
                return false;
            }
        }

        public Boolean drop(Student student) {
            //Ceheck for name in waitlist or already in course. If present, will remove
            //If in waitlist, just remove. If in course, will remove student from course, then put the next student in course
            //Similarly to add function, the remove function of Set will remove only if its there.
            if (waitlist.contains(student)) {
                waitlist.remove(student);
                System.out.println("You have been removed from the course waitlist.");
                return true;
            } else if (students.contains(student)) {
                students.remove(student);

                System.out.println("You have been unregistered from the course");
                //???????????????????
                if (waitlist.iterator().hasNext()) {
                    Student temp = waitlist.iterator().next();
                    waitlist.remove(temp);
                    temp.enrollIn(this);
                }

                return true;

            } else {
                System.out.println("Invalid command! Student cannot drop this class.");
                return false;}

                //Return true or false?

        }

}
