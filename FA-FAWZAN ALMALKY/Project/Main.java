package studentmanagement;

/**
 * Main - Entry point of the Student Management System.
 * Module: Application Entry
 * Author: Group Project - SE1201
 * 
 * Architecture:
 *   Main → StudentUI → StudentService → StudentDatabase → Student
 */
public class Main {
    public static void main(String[] args) {
        // Initialize all layers
        StudentDatabase database = new StudentDatabase();
        StudentService service   = new StudentService(database);
        StudentUI ui             = new StudentUI(service);

        // Start the application
        ui.run();
    }
}
