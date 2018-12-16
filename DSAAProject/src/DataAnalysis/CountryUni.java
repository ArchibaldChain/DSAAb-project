package DataAnalysis;

import Student.CSVReader;
import Student.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class CountryUni {
    private HashMap<String, Country> country;
    private MyTreeMap<String, Country> sortedCountry;

    public CountryUni(Student[] student) {
        country = new HashMap<>();
        for (Student s : student) {
            String name = s.getAbroadCountry();
            if (country.containsKey(name)) {
                Country p = country.remove(name);
                p.add(s);
                country.put(name, p);// This is because get is remove
            } else {
                country.put(name, new Country(name, s));
            }
        }
    }

    public static void main(String[] args) {
        CSVReader reader = new CSVReader("DSAAProject\\FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list
    }

    public static void setCountryUni(ArrayList<Student> students) {
        CountryUni countryUni = new CountryUni(students.toArray(new Student[0]));
        System.out.println(countryUni.toString());
    }

    public void sort() {
        sortedCountry = new MyTreeMap<>(country);
        for (Country p :
                sortedCountry.toArray(new Country[0])) {
            p.sort();
        }
    }

    @Override
    public String toString() {
        sort();
        StringBuilder s = new StringBuilder();
        for (Country p : sortedCountry.toArray(new Country[0])) {
            s.append(p.toString());
        }
        return s.toString();

    }
}

class Country extends ComparableNode<Country> implements AddAble<Student> {
    String countryName;
    HashMap<String, University> university;
    MyTreeMap<String, University> sortedUniversity;

    Country(String countryName, Student s) {
        this.countryName = countryName;
        num = 1;
        String name = s.getAbroadCountry();
        university = new HashMap<>();
        university.put(name, new University(name, s));
    }

    void sort() {
        sortedUniversity = new MyTreeMap<>(university);
    }

    @Override
    public void add(Student s) {
        String name = s.getAbroadUniversity();
        if (university.containsKey(name)) {
            University d = university.remove(name);
            d.add(s);
            university.put(name, d);//This is because get means remove
        } else {
            university.put(name, new University(name, s));
        }
        num++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (University d : sortedUniversity.toArray(new University[0])) {
            s.append(d.toString());
        }

        return "-" + this.countryName + "(" + num + ")" + "\n" + s;
    }
}

class University extends ComparableNode<University> implements AddAble<Student> {
    String universityName;
    ArrayList<Student> students;

    University(String universityName, Student s) {
        students = new ArrayList<>();
        students.add(s);
        this.universityName = universityName;
        num = 1;
    }

    @Override
    public void add(Student s) {
        students.add(s);
        num++;
    }

    public String toString() {
        return "------" + this.universityName + "(" + num + ")" + "\n";
    }
}