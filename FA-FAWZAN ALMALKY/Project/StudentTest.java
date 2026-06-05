package studentmanagement;

import java.util.List;

/**
 * StudentTest - Testing module for the Student Management System.
 * Covers: Unit Testing, Integration Testing, Static & Dynamic Testing.
 * Module: Testing Layer
 * Author: Group Project - SE1201
 */
public class StudentTest {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║        Running Test Suite - SE1201       ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ── UNIT TESTS ──────────────────────────────────────────────────
        System.out.println("▶ UNIT TESTS");
        testStudentCreation();
        testStudentGetters();

        // ── INTEGRATION TESTS ───────────────────────────────────────────
        System.out.println("\n▶ INTEGRATION TESTS");
        testAddStudent();
        testDeleteStudent();
        testUpdateStudent();
        testSearchById();
        testSearchByName();

        // ── VALIDATION TESTS (Dynamic Testing) ──────────────────────────
        System.out.println("\n▶ DYNAMIC / VALIDATION TESTS");
        testInvalidEmail();
        testInvalidGPA();
        testEmptyName();
        testNotFoundId();

        // ── SUMMARY ─────────────────────────────────────────────────────
        System.out.println("\n═══════════════════════════════════════════");
        System.out.printf(" Results: %d Passed ✔  |  %d Failed ✘%n", passed, failed);
        System.out.println("═══════════════════════════════════════════");
    }

    // ── UNIT TESTS ──────────────────────────────────────────────────────
    static void testStudentCreation() {
        Student s = new Student(1001, "Ali", "ali@test.com", 3.5, "CS");
        assert_true("Student creation - ID",    s.getStudentId() == 1001, "ID should be 1001");
        assert_true("Student creation - Name",  s.getName().equals("Ali"), "Name should be Ali");
    }

    static void testStudentGetters() {
        Student s = new Student(1002, "Sara", "sara@test.com", 3.8, "IS");
        assert_true("Getter - GPA",   s.getGpa() == 3.8,       "GPA should be 3.8");
        assert_true("Getter - Major", s.getMajor().equals("IS"), "Major should be IS");
    }

    // ── INTEGRATION TESTS ───────────────────────────────────────────────
    static void testAddStudent() {
        StudentService svc = freshService();
        String result = svc.addStudent("Test User", "test@x.com", 3.0, "CS");
        assert_true("Add student", result.startsWith("SUCCESS"), "Should succeed: " + result);
    }

    static void testDeleteStudent() {
        StudentService svc = freshService();
        String result = svc.deleteStudent(1001); // Sample data starts at 1001
        assert_true("Delete student", result.startsWith("SUCCESS"), "Should succeed: " + result);
    }

    static void testUpdateStudent() {
        StudentService svc = freshService();
        String result = svc.updateStudent(1001, "Ali Updated", "new@x.com", 3.9, "CS");
        assert_true("Update student", result.startsWith("SUCCESS"), "Should succeed: " + result);
    }

    static void testSearchById() {
        StudentService svc = freshService();
        String result = svc.searchById(1001);
        assert_true("Search by ID", result.contains("1001"), "Should contain ID 1001");
    }

    static void testSearchByName() {
        StudentService svc = freshService();
        List<Student> results = svc.searchByName("Ali");
        assert_true("Search by Name", results.size() >= 1, "Should find at least 1 student named Ali");
    }

    // ── VALIDATION TESTS ────────────────────────────────────────────────
    static void testInvalidEmail() {
        StudentService svc = freshService();
        String result = svc.addStudent("Test", "invalidemail", 3.0, "CS");
        assert_true("Invalid email rejected", result.startsWith("ERROR"), "Should reject bad email");
    }

    static void testInvalidGPA() {
        StudentService svc = freshService();
        String result = svc.addStudent("Test", "t@t.com", 5.5, "CS");
        assert_true("Invalid GPA rejected", result.startsWith("ERROR"), "Should reject GPA > 4.0");
    }

    static void testEmptyName() {
        StudentService svc = freshService();
        String result = svc.addStudent("", "t@t.com", 3.0, "CS");
        assert_true("Empty name rejected", result.startsWith("ERROR"), "Should reject empty name");
    }

    static void testNotFoundId() {
        StudentService svc = freshService();
        String result = svc.searchById(9999);
        assert_true("Not found ID", result.startsWith("ERROR"), "Should return error for missing ID");
    }

    // ── Helpers ─────────────────────────────────────────────────────────
    static StudentService freshService() {
        return new StudentService(new StudentDatabase());
    }

    static void assert_true(String testName, boolean condition, String message) {
        if (condition) {
            System.out.printf("  ✔ PASS: %s%n", testName);
            passed++;
        } else {
            System.out.printf("  ✘ FAIL: %s → %s%n", testName, message);
            failed++;
        }
    }
}
