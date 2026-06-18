import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class StudentRecordSystem {

    private ArrayList<Student> students = new ArrayList<>();
    private String textFile = "students.txt";
    private String binaryFile = "students.dat";
    private String serialFile = "students.ser";
    private String backupFile = "students_backup.txt";

    public void addStudent(int id, String name, String dept, double gpa) {
        Student newStudent = new Student(id, name, dept, gpa);
        students.add(newStudent);
        System.out.println(" Student added successfully!");
    }
    public Student searchStudentByID(int id) {
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getStudentID() == id) {
                return s;
            }
        }
        return null;
    }
    public void updateStudent(int id, double newGPA) {
        Student s = searchStudentByID(id);
        if (s != null) {
            s.setGpa(newGPA);
            System.out.println(" GPA updated successfully!");
        } else {
            System.out.println(" Student not found!");
        }
    }
    public void deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID() == id) {
                students.remove(i);
                System.out.println(" Student deleted!");
                return;
            }
        }
        System.out.println(" Student not found!");
    }
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the list!");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).toString());
        }
    }
    public void saveToTextFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(textFile));
            for (int i = 0; i < students.size(); i++) {
                writer.println(students.get(i).toString());
            }
            writer.close();
            System.out.println(" Saved to text file!");
        } catch (IOException e) {
            System.out.println("Error saving text file: " + e.getMessage());
        }
    }

    public void loadFromTextFile() {
        try {
            File file = new File(textFile);
            if (file.exists() == false) {
                file.createNewFile();
                System.out.println("Created a new blank file.");
                return;
            }
            students.clear();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String dept = parts[2];
                    double gpa = Double.parseDouble(parts[3]);

                    students.add(new Student(id, name, dept, gpa));
                }
            }
            scanner.close();
            System.out.println(" Loaded from text file!");
        } catch (IOException e) {
            System.out.println("Error loading text file: " + e.getMessage());
        }
    }

    public void saveToBinaryFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(binaryFile);
            DataOutputStream out = new DataOutputStream(fileOut);

            out.writeInt(students.size());
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                out.writeInt(s.getStudentID());
                out.writeUTF(s.getName());
                out.writeUTF(s.getDepartment());
                out.writeDouble(s.getGpa());
            }
            out.close();
            System.out.println(" Saved to binary file!");
        } catch (IOException e) {
            System.out.println("Error saving binary file: " + e.getMessage());
        }
    }

    public void loadFromBinaryFile() {
        try {
            FileInputStream fileIn = new FileInputStream(binaryFile);
            DataInputStream in = new DataInputStream(fileIn);

            students.clear();
            int count = in.readInt();

            for (int i = 0; i < count; i++) {
                int id = in.readInt();
                String name = in.readUTF();
                String dept = in.readUTF();
                double gpa = in.readDouble();

                students.add(new Student(id, name, dept, gpa));
            }
            in.close();
            System.out.println(" Loaded from binary file!");
        } catch (IOException e) {
            System.out.println("Binary file error or file is missing.");
        }
    }
    public void saveToSerializedFile() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(serialFile));
            out.writeObject(students);
            out.close();
            System.out.println("Saved to serial file!");
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }
    }
    public void loadFromSerializedFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(serialFile));
            students = (ArrayList<Student>) in.readObject();
            in.close();
            System.out.println("Loaded from serial file!");
        } catch (Exception e) {
            System.out.println("Serial file error or file is missing.");
        }
    }
    public void createBackup() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(textFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            reader.close();
            writer.close();
            System.out.println(" Backup file created!");
        } catch (IOException e) {
            System.out.println("Error creating backup: " + e.getMessage());
        }
    }
    public void displayFileProperties() {
        System.out.println("\n Checking File Properties ");
        ArrayList<String> filePaths = new ArrayList<>();
        filePaths.add(textFile);
        filePaths.add(binaryFile);
        filePaths.add(serialFile);
        filePaths.add(backupFile);

        for (int i = 0; i < filePaths.size(); i++) {
            File f = new File(filePaths.get(i));
            if (f.exists()) {
                System.out.println("Name: " + f.getName());
                System.out.println("  Path: " + f.getAbsolutePath());
                System.out.println("  Size: " + f.length() + " bytes");
                System.out.println("  Modified: " + new Date(f.lastModified()));
            } else {
                System.out.println(f.getName() + " does not exist yet.");
            }
        }
    }
    public void generateReport() {
        if (students.isEmpty()) {
            System.out.println("No records to build a report.");
            return;
        }

        double highest = students.get(0).getGpa();
        double lowest = students.get(0).getGpa();
        double runningSum = 0;

        for (int i = 0; i < students.size(); i++) {
            double currentGpa = students.get(i).getGpa();
            runningSum = runningSum + currentGpa;

            if (currentGpa > highest) {
                highest = currentGpa;
            }
            if (currentGpa < lowest) {
                lowest = currentGpa;
            }
        }

        double average = runningSum / students.size();
        System.out.println("\n STUDENT REPORT ");
        System.out.println("Total Students Count: " + students.size());
        System.out.println("Highest GPA Found: " + highest);
        System.out.println("Lowest GPA Found: " + lowest);
        System.out.println("Average GPA of Class: " + average);
        System.out.println("\n");
    }
}
