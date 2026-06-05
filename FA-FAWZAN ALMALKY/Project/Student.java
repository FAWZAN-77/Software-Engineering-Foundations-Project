package studentmanagement;

/**
 * Student class - Represents a student entity in the system.
 * Module: Data Model
 * Author: Group Project - SE1201
 */
public class Student {
    private int studentId;
    private String name;
    private String email;
    private double gpa;
    private String major;

    // Constructor
    public Student(int studentId, String name, String email, double gpa, String major) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.gpa = gpa;
        this.major = major;
    }

    // Getters
    public int getStudentId()  { return studentId; }
    public String getName()    { return name; }
    public String getEmail()   { return email; }
    public double getGpa()     { return gpa; }
    public String getMajor()   { return major; }

    // Setters
    public void setName(String name)    { this.name = name; }
    public void setEmail(String email)  { this.email = email; }
    public void setGpa(double gpa)      { this.gpa = gpa; }
    public void setMajor(String major)  { this.major = major; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-20s | Email: %-25s | GPA: %.2f | Major: %s",
                studentId, name, email, gpa, major);
    }
}
