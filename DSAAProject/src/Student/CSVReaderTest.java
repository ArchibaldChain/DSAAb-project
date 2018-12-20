package Student;

import java.util.ArrayList;

public class CSVReaderTest {

    public static void main(String[] args) {
        CSVReader reader = new CSVReader("DSAAProject\\FileStorage\\ProjectData1.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list
        for (Student s : students){
            System.out.println(s.toString()); // Print the data in special format
        }
        System.out.println(students.get(0));
    }
}
