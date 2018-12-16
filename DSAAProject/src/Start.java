import Student.CSVReader;
import Student.Student;
import DataAnalysis.BornPlace;


import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * ProjectName: DSAAProject
 * Author: Archibald Chain
 * CreateDate: 2018/12/10 19:24
 * Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class Start {
    public static void main(String[] args) {
        CSVReader reader = new CSVReader("FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list

        BornPlace bornPlace = new BornPlace(students.toArray(new Student[0]));
        FileIO.fileWriter(bornPlace.toString(),"FileStorage/born place.txt");
    }
}


