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
            if ((!s.getDream().equals("出国深造") && !s.getDream().equals("出国留学") &&
                    !s.getDream().equals("香港读研")) || s.getAbroadCountry().equals("")
                    || s.getAbroadUniversity().equals("") || s.getAbroadCountry().equals("中")) {
                continue;
            }
            String name = s.getAbroadCountry();
            if (country.containsKey(name)) {
                Country c = country.get(name);
                c.add(s);
            } else {
                country.put(name, new Country(name, s));
            }
        }
        sort();
    }

    private void sort() {
        sortedCountry = new MyTreeMap<>(country);
        for (Country c :
                sortedCountry.toArray(new Country[0])) {
            c.sort();
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Country p : sortedCountry.toArray(new Country[0])) {
            s.append(p.toString());
        }
        return s.toString();

    }

    /**
     * The following four method was used to graph
     * @return the country name String with the order from large to small
     */
    public String[] getCountryNameLabels(){
        ArrayList<String> s = new ArrayList<>();
        for (Country c : sortedCountry.toArray(new Country[0])) {
            s.add(c.countryName);
        }
        return s.toArray(new String[0]);
    }

    /**
     * @return the number of every country
     */
    public int[] getCountryValue(){
        int[] a = new int[sortedCountry.size()];
        int i = 0;
        for (Country c : sortedCountry.toArray(new Country[0])) {
            a[i] = c.num;
            i++;
        }
        return a;
    }

    /**
     * @return the university label
     */
    public String[] getUniversityName(){
        ArrayList<String> s = new ArrayList<>();
        for (Country c : sortedCountry.toArray(new Country[0])) {
            for (University u:c.sortedUniversity.toArray(new University[0])) {
                s.add(u.universityName);
            }
        }
        return s.toArray(new String[0]);
    }

    /**
     * @return the number of every label
     */
    public int[] getUniversityValue(){
        ArrayList<Integer> i = new ArrayList<>();
        for (Country c : sortedCountry.toArray(new Country[0])) {
            for (University u:c.sortedUniversity.toArray(new University[0])) {
                i.add(u.num);
            }
        }
        return getInts(i);
    }

    static int[] getInts(ArrayList<Integer> n) {
        int[] a = new int[n.size()];
        Integer[] b = n.toArray(new Integer[0]);
        for (int i = 0; i < a.length; i++) {
            a[i] = b[i];
        }
        return a;
    }
}


class Country extends ComparableNode<Country> implements AddAble<Student> {
    String countryName;
    private HashMap<String, University> university;
    MyTreeMap<String, University> sortedUniversity;

    /**
     * When meet the new country
     *
     * @param countryName is a String
     */

    Country(String countryName, Student s) {
        this.countryName = countryName;
        num = 1;
        String name = s.getAbroadUniversity();
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
            University u = university.get(name);
            u.add(s);
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
    private HashMap<String, Major> major;
    private MyTreeMap<String, Major> sortedMajor;

    University(String universityName, Student s) {
        this.universityName = universityName;
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
            Major m = major.get(name);
            m.add(s);
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
    private String majorName;
    private ArrayList<Student> students;

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
