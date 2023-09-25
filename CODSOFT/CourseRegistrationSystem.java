import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<String> enrolledStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean enrollStudent(String studentId) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(studentId);
            return true;
        } else {
            System.out.println("Course is full. Cannot enroll more students.");
            return false;
        }
    }

    public void dropStudent(String studentId) {
        enrolledStudents.remove(studentId);
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description
                + "\nCapacity: " + capacity + "\nSchedule: " + schedule.toString()
                + "\nEnrolled Students: " + enrolledStudents.toString() + "\n";
    }
}

class Student {
    private String studentId;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\nName: " + name
                + "\nRegistered Courses: " + registeredCourses.toString() + "\n";
    }
}

public class CourseRegistrationSystem {
    private static Map<String, Course> courseDatabase = new HashMap<>();
    private static Map<String, Student> studentDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Course Registration System");
            System.out.println("1. Course Listing");
            System.out.println("2. Student Registration");
            System.out.println("3. Course Removal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayCourseListing();
                    break;
                case 2:
                    performStudentRegistration();
                    break;
                case 3:
                    performCourseRemoval();
                    break;
                case 4:
                    System.out.println("Thank you for using the Course Registration System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayCourseListing() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
        }
    }

    private static void performStudentRegistration() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (!studentDatabase.containsKey(studentId)) {
            System.out.print("Enter Student Name: ");
            String studentName = scanner.nextLine();
            Student student = new Student(studentId, studentName);
            studentDatabase.put(studentId, student);
        }

        System.out.println("Available Courses for Registration:");
        for (Course course : courseDatabase.values()) {
            if (!studentDatabase.get(studentId).getRegisteredCourses().contains(course.getCode())) {
                System.out.println(course.getCode() + " - " + course.getTitle());
            }
        }

        System.out.print("Enter Course Code to Register (or '0' to go back): ");
        String courseCode = scanner.nextLine();
        if (!courseCode.equals("0")) {
            if (courseDatabase.containsKey(courseCode)) {
                Course course = courseDatabase.get(courseCode);
                if (course.enrollStudent(studentId)) {
                    studentDatabase.get(studentId).registerCourse(courseCode);
                    System.out.println("Registration successful!");
                }
            } else {
                System.out.println("Invalid Course Code.");
            }
        }
    }

    private static void performCourseRemoval() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (studentDatabase.containsKey(studentId)) {
            Student student = studentDatabase.get(studentId);
            List<String> registeredCourses = student.getRegisteredCourses();
            if (!registeredCourses.isEmpty()) {
                System.out.println("Courses registered by " + student.getName() + ":");
                for (String courseCode : registeredCourses) {
                    System.out.println(courseCode + " - " + courseDatabase.get(courseCode).getTitle());
                }

                System.out.print("Enter Course Code to Remove (or '0' to go back): ");
                String courseCode = scanner.nextLine();
                if (!courseCode.equals("0")) {
                    if (courseDatabase.containsKey(courseCode)) {
                        Course course = courseDatabase.get(courseCode);
                        if (registeredCourses.contains(courseCode)) {
                            course.dropStudent(studentId);
                            student.dropCourse(courseCode);
                            System.out.println("Course removal successful!");
                        } else {
                            System.out.println("You are not registered for this course.");
                        }
                    } else {
                        System.out.println("Invalid Course Code.");
                    }
                }
            } else {
                System.out.println("No courses registered by " + student.getName() + ".");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }
}
