import DataAnalysis.CountryUniMajor;
import Student.CSVReader;
import Student.Student;

import java.util.ArrayList;

public class Start2 {
    public static void main(String[] args) {
        CSVReader reader = new CSVReader("DSAAProject\\FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list

        CountryUniMajor countryUniMajor = new CountryUniMajor(students.toArray(new Student[0]));
        FileIO.fileWriter(countryUniMajor.toString(), "DSAAProject\\FileStorage\\Country Uni Major.txt");
    }
}
