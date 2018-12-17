import DataAnalysis.BornPlace;
import DataAnalysis.CityUniMajor;
import DataAnalysis.CountryUniMajor;
import FileIO.FileIO;
import Student.CSVReader;
import Student.Student;

import java.util.ArrayList;

public class Start2 {
    public static void main(String[] args) {
        CSVReader reader = new CSVReader("DSAAProject\\FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list
        // 出生统计
        BornPlace bornPlace = new BornPlace(students.toArray(new Student[0]));
        FileIO.fileWriter(bornPlace.toString(), "DSAAProject\\FileStorage\\BornPlace.txt");
        // 国外深造
        CountryUniMajor countryUniMajor = new CountryUniMajor(students.toArray(new Student[0]));
        FileIO.fileWriter(countryUniMajor.toString(), "DSAAProject\\FileStorage\\Country Uni Major.txt");
        // 国内读研
        CityUniMajor cityUniMajor = new CityUniMajor(students.toArray(new Student[0]));
        FileIO.fileWriter(cityUniMajor.toString(), "DSAAProject\\FileStorage\\DomesticCity Uni Major.txt");
    }
}
