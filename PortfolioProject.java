// Kyle Petersen
// CSC372


// Imports
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PortfolioProject {

    // Instanciate a LinkedList of Student objects
    public static LinkedList<Student> students = new LinkedList<>();

    public static void main(String[] args) {
        // Start program by getting student data
        getStudents();

        // Sort students by name
        sortStudents();

        // Write student data to file
        printFiles();
    }

    // Prompt user for all student data
    public static void getStudents() {
        Scanner scanner = new Scanner(System.in);

        // Get student name
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        // Get student address
        System.out.print("Enter student address: ");
        String address = scanner.nextLine();

        // Get student GPA
        double GPA = getGPA(scanner);

        // Add student to linked list
        students.add(new Student(name, address, GPA));

        // Prompt user to enter another student
        System.out.print("Do you want to enter another student? (yes/no): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            // Recursively call getStudents until user enters "no"
            getStudents();
        }

        scanner.close();
    }


    // get student GPA prompt
    public static double getGPA(Scanner scanner) {
        System.out.print("Enter student GPA: ");
        double GPA = Double.valueOf(scanner.nextLine());

        // Validate GPA input
        if (GPA < 0 || GPA > 4) {
            System.out.print("Invalid GPA. Please enter a GPA between 0 and 4.");

            // Recursively call getGPA until valid input
            return getGPA(scanner);
        }

        return GPA;
    }

    // Sort students linked list by name
    public static void sortStudents() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getName().compareTo(students.get(j + 1).getName()) > 0) {
                    // Swap students
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    // Write student data to student_data.txt
    public static void printFiles() {
        // Try Catch block to handle IOException
        try (FileWriter writer = new FileWriter("student_data.txt")) {
            // Write student data to file
            for (Student student : students) {
                writer.write("Name: " + student.getName() + "\n");
                writer.write("Address: " + student.getAddress() + "\n");
                writer.write("GPA: " + student.getGPA() + "\n\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            // Exit method early if exception
            return;
        }
        System.out.println("Student data has been written to student_data.txt.");
    }
}