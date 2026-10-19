import java.util.*;

class CoursePortal {

    public static class Course {
        protected String name;
        protected String ID;
        protected HashSet<String> UniqueEnrolledStudents;
        protected ArrayList<String> PrerequisiteCodes;
        protected LinkedList<String> waitlist;
        protected int capacity;

        Course(String name, String ID, int capacity) {
            this.name = name;
            this.ID = ID;
            this.capacity = capacity;
            this.UniqueEnrolledStudents = new HashSet<>();
            this.PrerequisiteCodes = new ArrayList<>();
            this.waitlist = new LinkedList<>();
        }

        boolean isFull() {
            return UniqueEnrolledStudents.size() >= capacity;
        }

        @Override
        public String toString() {
            return ID + " - " + name
                    + " | enrolled " + UniqueEnrolledStudents.size() + "/" + capacity
                    + " | waitlist " + waitlist.size()
                    + " | prereqs " + PrerequisiteCodes;
        }
    }

    public static class CourseRegistration {

        protected HashMap<String, Course> Courses;
        protected HashMap<String, HashSet<String>> CompletedCourses;

        CourseRegistration() {
            this.Courses = new HashMap<>();
            this.CompletedCourses = new HashMap<>();

            Courses.put("CS101", new Course("Introduction to Programming", "CS101", 3));
            Courses.put("CS102", new Course("Data Structures and Algorithms", "CS102", 2));
            Courses.put("CS201", new Course("Database Management Systems", "CS201", 2));
            Courses.put("CS202", new Course("Operating Systems", "CS202", 2));
            Courses.put("CS301", new Course("Computer Networks", "CS301", 2));
            Courses.put("CS302", new Course("Artificial Intelligence", "CS302", 2));

            addPrerequisite("CS102", "CS101");
            addPrerequisite("CS201", "CS102");
            addPrerequisite("CS202", "CS102");
            addPrerequisite("CS301", "CS202");
            addPrerequisite("CS302", "CS102");
        }

        ArrayList<Course> FindCourses(String courseID) {
            ArrayList<Course> result = new ArrayList<>();

            if (courseID == null) {
                return result;
            }

            String q = courseID.trim().toLowerCase();

            for (Course c : Courses.values()) {
                if (c.ID.toLowerCase().contains(q)
                        || c.name.toLowerCase().contains(q)) {
                    result.add(c);
                }
            }

            return result;
        }

        void addPrerequisite(String courseID, String prereqCode) {
            Course c = Courses.get(courseID);

            if (c != null && !c.PrerequisiteCodes.contains(prereqCode)) {
                c.PrerequisiteCodes.add(prereqCode);
            }
        }

        void markCompleted(String studentID, String courseID) {
            CompletedCourses
                    .computeIfAbsent(studentID, k -> new HashSet<>())
                    .add(courseID);
        }

        private boolean prerequisitesMet(String studentID, Course c) {
            HashSet<String> done =
                    CompletedCourses.getOrDefault(studentID, new HashSet<>());

            for (String pre : c.PrerequisiteCodes) {
                if (!done.contains(pre)) {
                    return false;
                }
            }

            return true;
        }

        void registerStudent(String courseID, String studentID) {
            Course c = Courses.get(courseID);

            if (c == null) {
                System.out.println("Course not found: " + courseID);
                return;
            }

            if (c.UniqueEnrolledStudents.contains(studentID)
                    || c.waitlist.contains(studentID)) {
                System.out.println(studentID
                        + " is already enrolled/waitlisted in " + courseID);
                return;
            }

            if (!prerequisitesMet(studentID, c)) {
                System.out.println("Prerequisites not met for " + courseID
                        + ". Need " + c.PrerequisiteCodes);
                return;
            }

            if (!c.isFull()) {
                c.UniqueEnrolledStudents.add(studentID);
                System.out.println(studentID + " enrolled in " + courseID);
                return;
            }

            c.waitlist.addLast(studentID);

            System.out.println(courseID + " full. " + studentID
                    + " waitlisted at position " + c.waitlist.size());
        }

        void dropCourse(String courseID, String studentID) {
            Course c = Courses.get(courseID);

            if (c == null) {
                System.out.println("Course not found: " + courseID);
                return;
            }

            if (c.UniqueEnrolledStudents.remove(studentID)) {
                System.out.println("Dropped " + courseID + " by " + studentID);
                promoteFromWaitlist(c);
                return;
            }

            if (c.waitlist.remove(studentID)) {
                System.out.println("Removed " + studentID
                        + " from waitlist of " + courseID);
                return;
            }

            System.out.println(studentID
                    + " is not registered in " + courseID);
        }

        private void promoteFromWaitlist(Course c) {
            while (!c.isFull() && !c.waitlist.isEmpty()) {
                String next = c.waitlist.pollFirst();
                c.UniqueEnrolledStudents.add(next);

                System.out.println("Waitlist promoted: " + next);
            }
        }

        void printStatus(String courseID) {
            Course c = Courses.get(courseID);

            System.out.println(
                    c == null ? "Course not found: " + courseID : "  " + c
            );
        }
    }

    public static void main(String[] args) {
        CourseRegistration portal = new CourseRegistration();

        System.out.println("=== Search Courses ===");

        for (Course c : portal.FindCourses("cs1")) {
            System.out.println(c);
        }


        portal.registerStudent("CS101", "S1");
        portal.registerStudent("CS101", "S2");
        portal.registerStudent("CS101", "S3");
        portal.registerStudent("CS101", "S4");
        portal.registerStudent("CS101", "S5");

        portal.printStatus("CS101");

        portal.dropCourse("CS101", "S2");
        portal.printStatus("CS101");


        portal.registerStudent("CS102", "S9");

        portal.markCompleted("S9", "CS101");

        portal.registerStudent("CS102", "S9");
        portal.printStatus("CS102");
    }
}