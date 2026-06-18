public class Main {
    public static void main(String[] args) {

        StudentRecordSystem system = new StudentRecordSystem();

        System.out.println("\n ADDING STUDENTS ");
        system.addStudent(101, "Marta Kebede", "CS", 3.85);
        system.addStudent(102, "Abel Goshi", "SE", 3.78);
        system.addStudent(103, "Girma dereje", "Business", 3.45);
        system.addStudent(104, "Hiwot Mulu", "Medicine", 3.92);
        system.addStudent(105, " Hassan Kedir ", "CS", 3.72);

        System.out.println("\n DISPLAYING ALL STUDENTS");
        system.displayAllStudents();

        System.out.println("\n SEARCHING FOR STUDENT 103 ");
        Student found = system.searchStudentByID(103);
        if (found != null) {
            System.out.println(" Found: " + found.toString());
        }

        System.out.println("\n UPDATING STUDENT 102 GPA ");
        system.updateStudent(102, 3.95);

        System.out.println("\n SAVING TO TEXT FILE ");
        system.saveToTextFile();

        System.out.println("\n SAVING TO BINARY FILE ");
        system.saveToBinaryFile();

        System.out.println("\n SAVING TO SERIALIZED FILE ");
        system.saveToSerializedFile();

        System.out.println("\n CREATING BACKUP ");
        system.createBackup();

        System.out.println("\n DISPLAYING FILE PROPERTIES ");
        system.displayFileProperties();

        System.out.println("\n GENERATING REPORT");
        system.generateReport();

        System.out.println("\n DELETING STUDENT 105");
        system.deleteStudent(105);

        System.out.println("\n DISPLAYING UPDATED STUDENTS");
        system.displayAllStudents();

        System.out.println("\n TESTING LOAD FROM TEXT FILE ");
        StudentRecordSystem system2 = new StudentRecordSystem();
        system2.loadFromTextFile();
        system2.displayAllStudents();
        system2.generateReport();
    }
}
