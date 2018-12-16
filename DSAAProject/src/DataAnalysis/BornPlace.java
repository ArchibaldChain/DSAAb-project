package DataAnalysis;

import Student.CSVReader;
import Student.Student;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * ProjectName:    DSAAProject
 * Author:         Archibald Chain
 * CreateDate:     2018/12/13 21:51
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

public class BornPlace {

    public BornPlace(Student[] student) {
        province = new HashMap<>();
        for (Student s : student) {
            if (s.getProvince().equals("")){
                continue;
            }
            String name = s.getProvince();
            if (province.containsKey(name)) {
                Province p = province.remove(name);
                p.add(s);
                province.put(name, p);// This is because get is remove
            } else {
                province.put(name, new Province(name, s));
            }
        }
    }

    public static void main(String[] args) {
        CSVReader reader = new CSVReader("DSAAProject\\FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list

    }


    private HashMap<String, Province> province;
    private MyTreeMap<String, Province> sortedProvince;

    public static void setBornPlace(ArrayList<Student> students) {
        BornPlace bornPlace = new BornPlace(students.toArray(new Student[0]));
        System.out.println(bornPlace.toString());
    }

    public void sort() {
        sortedProvince = new MyTreeMap<>(province);
        for (Province p :
                sortedProvince.toArray(new Province[0])) {
            p.sort();
        }
    }

    @Override
    public String toString() {
        sort();
        StringBuilder s = new StringBuilder();
        for (Province p : sortedProvince.toArray(new Province[0])) {
            s.append(p.toString());
        }
        return s.toString();

    }


}

class Province extends ComparableNode<Province> implements AddAble<Student> {
    String provinceName;
    private HashMap<String, City> city;
    private MyTreeMap<String, City> sortedCity;

    /**
     * When meet the new province
     *
     * @param provinceName is a String
     */
    Province(String provinceName, Student s) {
        this.provinceName = provinceName;
        num = 1;// This is in the super class, stands for the Number of City
        String name = s.getCity();
        city = new HashMap<>();
        city.put(name, new City(name, s));
    }

    void sort() {
        sortedCity = new MyTreeMap<>(city);
        for (City c : sortedCity.toArray(new City[0])) {
            c.sort();
        }
    }

    /**
     * Add new Student in this province, so num++
     *
     * @param s is this student
     */
    @Override
    public void add(Student s) {
        String name = s.getCity();
        if (city.containsKey(name)) {
            City c = city.get(name);
            c.add(s);

        } else {
            city.put(name, new City(name, s));
        }
        num++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (City c : sortedCity.toArray(new City[0])) {
            s.append(c.toString());
        }
        return "--" + this.provinceName + "(" + num + ")" + "\n" + s;
    }
}


class City extends ComparableNode<City> implements AddAble<Student> {
    String cityName;
    HashMap<String, District> district;
    MyTreeMap<String, District> sortedDistrict;

    City(String cityName, Student s) {
        this.cityName = cityName;
        num = 1;
        String name = s.getDistrict();
        district = new HashMap<>();
        district.put(name, new District(name, s));
    }

    void sort() {
        sortedDistrict = new MyTreeMap<>(district);
    }

    @Override
    public void add(Student s) {
        String name = s.getDistrict();
        if (district.containsKey(name)) {
            District d = district.get(name);
            d.add(s);
        } else {
            district.put(name, new District(name, s));
        }
        num++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (District d : sortedDistrict.toArray(new District[0])) {
            s.append(d.toString());
        }

        return "------" + this.cityName + "(" + num + ")" + "\n" + s;
    }
}

class District extends ComparableNode<District> implements AddAble<Student> {
    String districtName;
    ArrayList<Student> students;

    District(String districtName, Student s) {
        students = new ArrayList<>();
        students.add(s);
        this.districtName = districtName;
        num = 1;
    }

    @Override
    public void add(Student s) {
        students.add(s);
        num++;
    }

    public String toString() {
        return "----------" + this.districtName + "(" + num + ")" + "\n";
    }
}
