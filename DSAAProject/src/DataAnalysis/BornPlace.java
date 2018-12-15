package DataAnalysis;

import Student.*;
import java.util.ArrayList;



/**
 * ProjectName:    DSAAProject
 * Author:         Archibald Chain
 * CreateDate:     2018/12/13 21:51
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

public class BornPlace {

    public static void main(String[] args) {
        CSVReader reader = new CSVReader("FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list
        BornPlace bornPlace = new BornPlace(students.toArray(new Student[0]));
        System.out.println(bornPlace.toString());
    }

    MyTreeMap<String, Province> province;

    public BornPlace(Student[] student) {
        province = new MyTreeMap<>();
        for (Student s:student)
        {
            String name =  s.getProvince();
            if (province.containsKey(name)){
                Province p = province.remove(name);
                p.add(s);
                province.put(name, p);// This is because get is remove
            }
            else {
                province.put(name, new Province(name, s));
            }
        }

        //System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Province p :   province.toArray(new Province[0])) {
            s.append(p.toString());
        }
        return s.toString();
    }

}

class Province extends ComparableNode<Province> implements AddAble<Student>{
    String provinceName;
    MyTreeMap<String, City> city;

    /**
     * When meet the new province
     * @param provinceName is a String
     */
    Province(String provinceName, Student s) {
        this.provinceName = provinceName;
        num = 1;// This is in the super class, stands for the Number of City
        String name = s.getCity();
        city = new MyTreeMap<>();
        city.put(name, new City(name, s));
    }

    /**
     * Add new Student in this province, so num++
     * @param s is this student
     */
    @Override
    public void add(Student s){
        String name = s.getCity();
        if (city.containsKey(name)){
            City c = city.remove(name);
            c.add(s);
            city.put(name, c);//This is because get means remove

        } else {
            city.put(name, new City(name, s));
        }
        num ++;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (City c: city.toArray(new City[0])) {
            s.append(c.toString());
        }
        return "--"+ this.provinceName+ "("+num+")"+ "\n" + s;
    }
}


class City extends ComparableNode<City> implements AddAble<Student>{
    String cityName;
    MyTreeMap<String, District> district;

    City(String cityName, Student s) {
        this.cityName = cityName;
        num = 1;
        String name = s.getDistrict();
        district = new MyTreeMap<>();
        district.put(name, new District(name, s));
    }

    @Override
    public void add(Student s){
        String name = s.getDistrict();
        if (district.containsKey(name)){
            District d = district.remove(name);
            d.add(s);
            district.put(name, d);//This is because get means remove
        } else {
            district.put(name, new District(name, s));
        }
        num ++;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (District d: district.toArray(new District[0])) {
            s.append(d.toString());
        }

        return "------"+ this.cityName+ "("+num+")"+ "\n" + s;
    }
}

class District extends ComparableNode<District> implements AddAble<Student>{
    String districtName;
    ArrayList<Student> students;

    District(String districtName, Student s) {
        students = new ArrayList<>();
        students.add(s);
        this.districtName = districtName;
        num = 1;
    }

    @Override
    public void add(Student s){
        students.add(s);
        num ++;
    }

    public String toString(){
        return "----------"+ this.districtName+ "("+num+")"+ "\n";
    }
}