package DataAnalysis;

import Student.CSVReader;
import Student.Student;

import java.util.ArrayList;
import java.util.HashMap;


/*
 * ProjectName:    DSAAProject
 * Author:         PRD
 * CreateDate:     2018/12/17 09:51
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

public class CountryUniMajor {

    private HashMap<String, Country> country;

    public static void main(String[] args) {
        CSVReader reader = new CSVReader("DSAAProject\\FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list
    }

    private MyTreeMap<String, Country> sortedCountry;

    public CountryUniMajor(Student[] student) {
        country = new HashMap<>();
        for (Student s : student) {
            if (!s.getDream().equals("出国深造") || s.getAbroadCountry().equals("")
                    || s.getAbroadUniversity().equals("") || s.getAbroadCountry().equals("中")) {
                continue;
            }
            String name = s.getAbroadCountry();
            if (country.containsKey(name)) {
                Country c = country.remove(name);
                c.add(s);
                country.put(name, c);// This is because get is remove
            } else {
                country.put(name, new Country(name, s));
            }
        }
    }

    public static void setCountryUniMajor(ArrayList<Student> students) {
        CountryUniMajor countryUniMajor = new CountryUniMajor(students.toArray(new Student[0]));
        System.out.println(countryUniMajor.toString());
    }

    public void sort() {
        sortedCountry = new MyTreeMap<>(country);
        for (Country c :
                sortedCountry.toArray(new Country[0])) {
            c.sort();
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
    private HashMap<String, University> university;
    private MyTreeMap<String, University> sortedUniversity;

    /**
     * When meet the new country
     *
     * @param countryName is a String
     */

    Country(String countryName, Student s) {
        this.countryName = countryName;
        num = 1;
        String name = s.getAbroadCountry();
        university = new HashMap<>();
        university.put(name, new University(name, s));
    }

    void sort() {
        sortedUniversity = new MyTreeMap<>(university);
        for (University u : sortedUniversity.toArray(new University[0])) {
            u.sort();
        }
    }

    /**
     * Add new Student in this country, so num++
     *
     * @param s is this student
     */
    @Override
    public void add(Student s) {
        String name = s.getAbroadUniversity();
        if (university.containsKey(name)) {
            University u = university.remove(name);
            u.add(s);
            university.put(name, u);//This is because get means remove
        } else {
            university.put(name, new University(name, s));
        }
        num++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (University u : sortedUniversity.toArray(new University[0])) {
            s.append(u.toString());
        }
        return "--" + this.countryName + "(" + num + ")" + "\n" + s;
    }
}

class University extends ComparableNode<University> implements AddAble<Student> {
    String universityName;
    HashMap<String, Major> major;
    MyTreeMap<String, Major> sortedMajor;

    University(String universityName, Student s) {
        this.universityName = s.getAbroadUniversity();
        num = 1;
        String name = s.getMajor1();
        major = new HashMap<>();
        major.put(name, new Major(name, s));
    }

    void sort() {
        sortedMajor = new MyTreeMap<>(major);
    }

    @Override
    public void add(Student s) {
        String name = s.getMajor1();
        if (major.containsKey(name)) {
            Major m = major.remove(name);
            m.add(s);
            major.put(name, m);
        } else {
            major.put(name, new Major(name, s));
        }
        num++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Major m : sortedMajor.toArray(new Major[0])) {
            s.append(m.toString());
        }
        return "------" + this.universityName + "(" + num + ")" + "\n" + s;
    }
}

class Major extends ComparableNode<Major> implements AddAble<Student> {
    String majorName;
    ArrayList<Student> students;

    Major(String majorName, Student s) {
        students = new ArrayList<>();
        students.add(s);
        this.majorName = majorName;
        num = 1;
    }

    @Override
    public void add(Student s) {
        students.add(s);
        num++;
    }

    public String toString() {
        return "-------------" + this.majorName + "(" + num + ")" + "\n";
    }
}
