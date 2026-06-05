package studentmanagement;

import java.util.List;
import java.util.Scanner;

/**
 * StudentUI - Console-based user interface for the Student Management System.
 * Module: Presentation Layer
 * Author: Group Project - SE1201
 */
public class StudentUI {
    private StudentService service;
    private Scanner scanner;

    public StudentUI(StudentService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    /** Display the main menu */
    public void showMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║    Student Management System - SE1201    ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("  1. View All Students");
        System.out.println("  2. Add New Student");
        System.out.println("  3. Search Student by ID");
        System.out.println("  4. Search Student by Name");
        System.out.println("  5. Update Student");
        System.out.println("  6. Delete Student");
        System.out.println("  7. Show Total Students");
        System.out.println("  0. Exit");
        System.out.print("\nEnter choice: ");
    }

    /** Run the application loop */
    public void run() {
        int choice = -1;
        while (choice != 0) {
            showMenu();
            try { choice = Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { choice = -1; }

            switch (choice) {
                case 1: viewAllStudents(); break;
                case 2: addStudent(); break;
                case 3: searchById(); break;
                case 4: searchByName(); break;
                case 5: updateStudent(); break;
                case 6: deleteStudent(); break;
                case 7: System.out.println("Total Students: " + service.getTotalStudents()); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void viewAllStudents() {
        List<Student> list = service.getAllStudents();
        System.out.println("\n--- All Students (" + list.size() + ") ---");
        if (list.isEmpty()) System.out.println("No students found.");
        else list.forEach(System.out::println);
    }

    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("GPA (0.0 - 4.0): "); double gpa;
        try { gpa = Double.parseDouble(scanner.nextLine()); }
        catch (NumberFormatException e) { System.out.println("Invalid GPA."); return; }
        System.out.print("Major: "); String major = scanner.nextLine();
        System.out.println(service.addStudent(name, email, gpa, major));
    }

    private void searchById() {
        System.out.print("\nEnter Student ID: ");
        try { int id = Integer.parseInt(scanner.nextLine());
              System.out.println(service.searchById(id)); }
        catch (NumberFormatException e) { System.out.println("Invalid ID."); }
    }

    private void searchByName() {
        System.out.print("\nEnter Name to Search: ");
        String name = scanner.nextLine();
        List<Student> results = service.searchByName(name);
        System.out.println("Found " + results.size() + " result(s):");
        results.forEach(System.out::println);
    }

    private void updateStudent() {
        System.out.print("\nEnter Student ID to Update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("New Name: "); String name = scanner.nextLine();
            System.out.print("New Email: "); String email = scanner.nextLine();
            System.out.print("New GPA: "); double gpa = Double.parseDouble(scanner.nextLine());
            System.out.print("New Major: "); String major = scanner.nextLine();
            System.out.println(service.updateStudent(id, name, email, gpa, major));
        } catch (NumberFormatException e) { System.out.println("Invalid input."); }
    }

    private void deleteStudent() {
        System.out.print("\nEnter Student ID to Delete: ");
        try { int id = Integer.parseInt(scanner.nextLine());
              System.out.println(service.deleteStudent(id)); }
        catch (NumberFormatException e) { System.out.println("Invalid ID."); }
    }
}
