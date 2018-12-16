import DataAnalysis.CountryUni;
import Student.CSVReader;
import Student.Student;
import FileIO.*;
import java.util.ArrayList;

public class Start2 {
    public static void main(String[] args) {
        CSVReader reader = new CSVReader("DSAAProject\\FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list

        CountryUni countryUni = new CountryUni(students.toArray(new Student[0]));
        FileIO.fileWriter(countryUni.toString(), "DSAAProject\\FileStorage\\Country Uni.txt");
    }
}
