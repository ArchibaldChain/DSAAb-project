import DataAnalysis.BornPlace;
import DataAnalysis.CityUniMajor;
import DataAnalysis.Count;
import DataAnalysis.CountryUniMajor;
import FileIO.FileIO;
import Student.CSVReader;
import Student.Student;
import Xchart.*;

import java.util.ArrayList;

public class Start2 {
    public static void main(String[] args) {
        //Project_data_20181208.csv
        //ProjectData.csv

        //get the data from csv file
        CSVReader reader = new CSVReader("FileStorage\\ProjectData.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list

        otherGraph(students.toArray(new Student[0]));
        bornPlace(students);
        studyAbroad(students);
        studyDomesic(students);

    }

    private static void bornPlace(ArrayList<Student> students){
        // Born place
        BornPlace bornPlace = new BornPlace(students.toArray(new Student[0]));
        FileIO.fileWriter(bornPlace.toString(), "FileStorage\\BornPlace.txt");
        GetChart getChart1 = new GetChart(bornPlace.provinceLabel(), bornPlace.provinceNumber(),
                bornPlace.backHometownNumber());
        getChart1.drawBarChart("FileStorage/Born Place/bar chart", "Born Place", "number of born",
                "number of back", "province", "number of people");
        getChart1.drawRadarChart("FileStorage/Born Place/radar chart", "Born Place", "rate of born",
                "rate of back");
        getChart1.drawPieChart("FileStorage/Born Place/pie chart", "Born Place");
    }

    private static void studyAbroad(ArrayList<Student> students){
        // Study abroad
        CountryUniMajor countryUniMajor = new CountryUniMajor(students.toArray(new Student[0]));
        FileIO.fileWriter(countryUniMajor.toString(), "FileStorage\\Study abroad.txt");
        GetChart getChart1 = new GetChart(countryUniMajor.getCountryNameLabels(), countryUniMajor.getCountryValue());
        getChart1.drawPieChart("FileStorage/Study Abroad/Pie Chart", "Study Abroad");
        getChart1.drawBarChart("FileStorage/Study Abroad/Bar Chart", "Study Abroad",
                "number of people", "Country","number");
        GetChart getChart2 = new GetChart(countryUniMajor.getUniversityName(), countryUniMajor.getUniversityValue());
        getChart2.drawPieChart("FileStorage/Study Abroad/University Pie Chart", "Study Abroad");
        getChart2.drawBarChart("FileStorage/Study Abroad/University Bar Chart", "Study Abroad",
                "number of people", "University","number");

    }

    private static void studyDomesic(ArrayList<Student> students){
        // Study domestic
        CityUniMajor cityUniMajor = new CityUniMajor(students.toArray(new Student[0]));
        FileIO.fileWriter(cityUniMajor.toString(), "FileStorage\\Domestic Study.txt");
        GetChart getChart1 = new GetChart(cityUniMajor.getDomesticCityLabel(), cityUniMajor.getDomesticCityValue());
        getChart1.drawPieChart("FileStorage/Domestic Study/Pie Chart", "Study Abroad");
        getChart1.drawBarChart("FileStorage/Domestic Study/Bar Chart", "Study Abroad",
                "number of people", "Country","number");
        GetChart getChart2 = new GetChart(cityUniMajor.getUniversityName(), cityUniMajor.getUniversityValue());
        getChart2.drawPieChart("FileStorage/Domestic Study/University Pie Chart", "Study Abroad");
        getChart2.drawBarChart("FileStorage/Domestic Study/University Bar Chart", "Study Abroad",
                "number of people", "University","number");

    }

    private static void otherGraph(Student[] students){
        HistogramChart salary = new HistogramChart("Salary", "salary (k/month)", "number");
        salary.addHistogram(Count.SalaryCount(students), "salary");
        System.out.println("** 111");
        salary.draw("FileStorage/Other Graph/Salary Bar Chart", "Salary");

        GetChart getDreamChart = new GetChart(new String[] {"境内读研", "出境深造", "毕业工作"},
                Count.CountDream(students));
        getDreamChart.drawPieChart("FileStorage/Other Graph/Dream Pie Chart", "毕业出路");

        GetChart getDegreeChart = new GetChart(new String[] {"国内硕士", "国内博士", "国外硕士", "国外博士"},
                Count.CountDegree(students));
        getDegreeChart.drawPieChart("FileStorage/Other Graph/Degree Pie Chart", "Degree");

        GetChart getWorkPlaceChart = new GetChart(new String[] {"国企", "自己创业", "其他企业"},
                Count.CountWorkType(students));
        getWorkPlaceChart.drawPieChart("FileStorage/Other Graph/Work pace Pie Chart", "Work Place");


    }

}
