package studentmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * StudentDatabase - Handles storage and retrieval of students.
 * Module: Data Access Layer
 * Author: Group Project - SE1201
 */
public class StudentDatabase {
    private List<Student> students;
    private int nextId;

    public StudentDatabase() {
        this.students = new ArrayList<>();
        this.nextId = 1001;
        loadSampleData(); // Pre-load sample data
    }

    /** Pre-load sample students for demonstration */
    private void loadSampleData() {
        students.add(new Student(nextId++, "Ali Al-Ghamdi",   "ali@university.edu",   3.8, "Computer Science"));
        students.add(new Student(nextId++, "Sara Al-Otaibi",  "sara@university.edu",  3.5, "Information Systems"));
        students.add(new Student(nextId++, "Omar Al-Zahrani", "omar@university.edu",  3.2, "Software Engineering"));
        students.add(new Student(nextId++, "Nora Al-Shehri",  "nora@university.edu",  3.9, "Computer Science"));
    }

    /** Add a new student */
    public Student addStudent(String name, String email, double gpa, String major) {
        Student s = new Student(nextId++, name, email, gpa, major);
        students.add(s);
        return s;
    }

    /** Get all students */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /** Find student by ID */
    public Optional<Student> findById(int id) {
        return students.stream().filter(s -> s.getStudentId() == id).findFirst();
    }

    /** Find students by name (partial match) */
    public List<Student> findByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) result.add(s);
        }
        return result;
    }

    /** Update student info */
    public boolean updateStudent(int id, String name, String email, double gpa, String major) {
        Optional<Student> opt = findById(id);
        if (opt.isPresent()) {
            Student s = opt.get();
            s.setName(name); s.setEmail(email); s.setGpa(gpa); s.setMajor(major);
            return true;
        }
        return false;
    }

    /** Delete student by ID */
    public boolean deleteStudent(int id) {
        return students.removeIf(s -> s.getStudentId() == id);
    }

    /** Get count of students */
    public int count() { return students.size(); }
}
