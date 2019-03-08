package DataAnalysis;

import Student.CSVReader;
import Student.Student;

import java.util.ArrayList;
import java.util.HashMap;
import static DataAnalysis.CountryUniMajor.getInts;


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
            String name = s.getProvince();
            if (name.equals("")) {
                continue;
            }
            if (province.containsKey(name)) {
                Province p = province.remove(name);
                p.add(s);
                province.put(name, p);// This is because get is remove
            } else {
                province.put(name, new Province(name, s));
            }
        }
        sort();
    }

//    public static void main(String[] args) {
//        CSVReader reader = new CSVReader("DSAAProject\\FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
//        ArrayList<Student> students = reader.parse(); // Format the data into a list
//    }

    private HashMap<String, Province> province;
    private MyTreeMap<String, Province> sortedProvince;


    private void sort() {
        sortedProvince = new MyTreeMap<>(province);
        for (Province p :
                sortedProvince.toArray(new Province[0])) {
            p.sort();
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Province p : sortedProvince.toArray(new Province[0])) {
            s.append(p.toString());
        }
        return s.toString();

    }

    /**
     * The rest three method was used to draw graph
     * @return the string of the province name with the order from large to small
     */
    public String[] provinceLabel(){
        ArrayList<String> s = new ArrayList<>();
        for (Province p : sortedProvince.toArray(new Province[0])) {
            s.add(p.provinceName);
        }
        return s.toArray(new String[0]);
    }

    /**
     * This is for getting the number of people in correspond province
     * @return a array with the number of every province
     */
    public int[] provinceNumber(){
        ArrayList<Integer> n = new ArrayList<>();
        for (Province p : sortedProvince.toArray(new Province[0])) {
            n.add(p.num);
        }
        return getArray(n);
    }

    /**
     * This method is for return the number of students who will comeback their hometown
     * @return a array that contains the number of students who will comeback
     */
    public int[] backHometownNumber(){
        ArrayList<Integer> n = new ArrayList<>();
        for (Province p : sortedProvince.toArray(new Province[0])) {
            n.add(p.returnHometown);
        }
        return getArray(n);
    }

    private int[] getArray(ArrayList<Integer> n){
        return getInts(n);
    }


}

class Province extends ComparableNode<Province> implements AddAble<Student> {
    String provinceName;
    private HashMap<String, City> city;
    private MyTreeMap<String, City> sortedCity;
    int returnHometown; //count the number: how many student will come back their province

    /**
     * When meet the new province, we will use the construct method
     * @param provinceName is a String
     */

    Province(String provinceName, Student s) {
        this.provinceName = provinceName;
        num = 1;// This is in the super class, stands for the Number of City
        String name = s.getCity();
        city = new HashMap<>();
        city.put(name, new City(name, s));
        returnHometown = 0;

        if (s.getProvince().equals(s.getWorkProvince())){
            returnHometown++;
        }
    }

    void sort() {
        sortedCity = new MyTreeMap<>(city);
        for (City c : sortedCity.toArray(new City[0])) {
            c.sort();
        }
    }

    /**
     * Add new Student in this province, so num++
     * @param s is this student
     */
    @Override
    public void add(Student s) {
        String name = s.getCity();
        if (city.containsKey(name)) {
            City c = city.remove(name);
            c.add(s);
            city.put(name, c);//This is because get means remove
        } else {
            city.put(name, new City(name, s));
        }

        if (s.getProvince().equals(s.getWorkProvince())){
            returnHometown++;
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
    private String cityName;
    private HashMap<String, District> district;
    private MyTreeMap<String, District> sortedDistrict;

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
            District d = district.remove(name);
            d.add(s);
            district.put(name, d);//This is because get means remove
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
    private String districtName;
    private ArrayList<Student> students;

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
