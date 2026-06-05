package studentmanagement;

import java.util.List;
import java.util.Optional;

/**
 * StudentService - Business logic layer for student operations.
 * Handles validation and processing before database operations.
 * Module: Business Logic Layer
 * Author: Group Project - SE1201
 */
public class StudentService {
    private StudentDatabase db;

    public StudentService(StudentDatabase db) {
        this.db = db;
    }

    /** Add student with validation */
    public String addStudent(String name, String email, double gpa, String major) {
        // Validate inputs
        if (name == null || name.trim().isEmpty())
            return "ERROR: Name cannot be empty.";
        if (email == null || !email.contains("@"))
            return "ERROR: Invalid email address.";
        if (gpa < 0.0 || gpa > 4.0)
            return "ERROR: GPA must be between 0.0 and 4.0.";
        if (major == null || major.trim().isEmpty())
            return "ERROR: Major cannot be empty.";

        Student s = db.addStudent(name.trim(), email.trim(), gpa, major.trim());
        return "SUCCESS: Student added with ID " + s.getStudentId();
    }

    /** Get all students */
    public List<Student> getAllStudents() {
        return db.getAllStudents();
    }

    /** Search student by ID */
    public String searchById(int id) {
        Optional<Student> opt = db.findById(id);
        return opt.map(Student::toString).orElse("ERROR: Student with ID " + id + " not found.");
    }

    /** Search students by name */
    public List<Student> searchByName(String name) {
        return db.findByName(name);
    }

    /** Update student */
    public String updateStudent(int id, String name, String email, double gpa, String major) {
        if (gpa < 0.0 || gpa > 4.0) return "ERROR: GPA must be between 0.0 and 4.0.";
        boolean updated = db.updateStudent(id, name, email, gpa, major);
        return updated ? "SUCCESS: Student " + id + " updated." : "ERROR: Student not found.";
    }

    /** Delete student */
    public String deleteStudent(int id) {
        boolean deleted = db.deleteStudent(id);
        return deleted ? "SUCCESS: Student " + id + " deleted." : "ERROR: Student not found.";
    }

    /** Get total count */
    public int getTotalStudents() {
        return db.count();
    }
}
