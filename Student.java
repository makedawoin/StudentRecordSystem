import java.io.Serializable;
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int studentID;
    private String name;
    private String department;
    private double gpa;

    public Student (int studentID, String name, String department, double gpa){
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.gpa = gpa;
    }

    public int getStudentID() {
        return studentID;
    }
    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return studentID + "," + name + "," + department + "," + gpa;
    }
}
