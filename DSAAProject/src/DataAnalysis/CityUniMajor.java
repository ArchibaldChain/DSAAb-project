package DataAnalysis;

import Student.CSVReader;
import Student.Student;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * ProjectName:    DSAAProject
 * Author:         PRD
 * CreateDate:     2018/12/17 13:05
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

public class CityUniMajor {
    private HashMap<String, DomesticCity> domesticCity;
    private MyTreeMap<String, DomesticCity> sortedDomesticCity;
    public int Unicount = 0;

    public CityUniMajor(Student[] student) {
        domesticCity = new HashMap<>();
        for (Student s : student) {
            if (!s.getDream().equals("内地读研") && !s.getDream().equals("国内读研")|| s.getDomesticCity().equals("")
                    || s.getDomesticUniversity().equals("")) {
                continue;
            }
            String name = s.getDomesticCity();
            if (domesticCity.containsKey(name)) {
                DomesticCity c = domesticCity.get(name);
                c.add(s);
            } else {
                domesticCity.put(name, new DomesticCity(name, s));
            }
        }
    }

    public static void main(String[] args) {
        CSVReader reader = new CSVReader("DSAAProject\\FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list

    }

    public static void setCityUniMajor(ArrayList<Student> students) {
        CityUniMajor cityUniMajor = new CityUniMajor(students.toArray(new Student[0]));
        System.out.println(cityUniMajor.toString());
    }

    public void sort() {
        sortedDomesticCity = new MyTreeMap<>(domesticCity);
        for (DomesticCity c :
                sortedDomesticCity.toArray(new DomesticCity[0])) {
            c.sort();
        }
    }

    @Override
    public String toString() {
        sort();
        StringBuilder s = new StringBuilder();
        for (DomesticCity p : sortedDomesticCity.toArray(new DomesticCity[0])) {
            s.append(p.toString());
        }
        return s.toString();

    }

}

class DomesticCity extends ComparableNode<DomesticCity> implements AddAble<Student> {
    String cityName;
    private HashMap<String, DomesticUniversity> university;
    private MyTreeMap<String, DomesticUniversity> sortedUniversity;

    /**
     * When meet the new city
     *
     * @param cityName is a String
     */

    DomesticCity(String cityName, Student s) {
        this.cityName = cityName;
        num = 1;
        String name = s.getDomesticUniversity();
        university = new HashMap<>();
        university.put(name, new DomesticUniversity(name, s));
    }

    void sort() {
        sortedUniversity = new MyTreeMap<>(university);
        for (DomesticUniversity u : sortedUniversity.toArray(new DomesticUniversity[0])) {
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
        String name = s.getDomesticUniversity();
        if (university.containsKey(name)) {
            DomesticUniversity u = university.get(name);
            u.add(s);
        } else {
            university.put(name, new DomesticUniversity(name, s));
        }
        num++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (DomesticUniversity u : sortedUniversity.toArray(new DomesticUniversity[0])) {
            s.append(u.toString());
        }
        return "--" + this.cityName + "(" + num + ")" + "\n" + s;
    }
}

class DomesticUniversity extends ComparableNode<DomesticUniversity> implements AddAble<Student> {
    String universityName;
    HashMap<String, DomesticMajor> major;
    MyTreeMap<String, DomesticMajor> sortedMajor;

    DomesticUniversity(String universityName, Student s) {
        this.universityName = universityName;
        num = 1;
        String name = s.getMajor2();
        major = new HashMap<>();
        major.put(name, new DomesticMajor(name, s));
    }

    void sort() {
        sortedMajor = new MyTreeMap<>(major);
    }

    @Override
    public void add(Student s) {
        String name = s.getMajor2();
        if (major.containsKey(name)) {
            DomesticMajor m = major.get(name);
            m.add(s);
        } else {
            major.put(name, new DomesticMajor(name, s));
        }
        num++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (DomesticMajor m : sortedMajor.toArray(new DomesticMajor[0])) {
            s.append(m.toString());
        }
        return "------" + this.universityName + "(" + num + ")" + "\n" + s;
    }
}

class DomesticMajor extends ComparableNode<DomesticMajor> implements AddAble<Student> {
    String majorName;
    ArrayList<Student> students;

    DomesticMajor(String majorName, Student s) {
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
