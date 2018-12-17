package Xchart;

import Student.CSVReader;
import Student.Student;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Collections;
=======
>>>>>>> 2 count methods added

/**
 * ProjectName:    DSAAProject
 * Author:         PRD
 * CreateDate:     2018/12/17 21:51
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class Count {
    public static int[] CountDream(Student[] student) {
        /*
        毕业去向统计（国内读研，出国深造，毕业工作）
         */
        int domesticCount = 0;
        int overseasCount = 0;
        int workCount = 0;
        for (Student s : student) {
            String dream = s.getDream();
            if (dream.equals("国内读研")) {
                domesticCount++;
            } else if (dream.equals("出国深造")) {
                overseasCount++;
            } else if (dream.equals("毕业工作")) {
                workCount++;
            }
        }
        int returnArray[] = new int[]{domesticCount, overseasCount, workCount};
        return returnArray;
    }

    public static int[] CountDegree(Student[] student) {
        /*
        国内外升学情况统计（国内硕士，国内博士，国外硕士，国外博士）
         */
        int domesticMasterCount = 0;
        int domesticPhdCount = 0;
        int overseasMasterCount = 0;
        int overseasPhdCount = 0;
        for (Student s : student) {
            String degree = s.getDegree();
            String dream = s.getDream();
            if (dream.equals("国内读研")) {
                if (degree.equals("硕士")) {
                    domesticMasterCount++;
                } else if (degree.equals("博士")) {
                    domesticPhdCount++;
                }
            } else if (dream.equals("出国深造")) {
                if (degree.equals("硕士")) {
                    overseasMasterCount++;
                } else if (degree.equals("博士")) {
                    overseasPhdCount++;
                }
            }
        }
        int returnArray[] = new int[]{domesticMasterCount, domesticPhdCount, overseasMasterCount
                , overseasPhdCount};
        return returnArray;
    }

<<<<<<< HEAD

    static int[] CountWorkType(Student[] students) {
        /*
        工作类型统计（国企 自己创业 其他企业）
         */
        int elseCount = 0;
        int startupCount = 0;
        int stateCompanyCount = 0;
        for (Student s : students) {
            String WorkType = s.getWorkPlace();
            if (WorkType.equals("国企")) {
                stateCompanyCount++;
            } else if (WorkType.equals("自己创业")) {
                startupCount++;
            } else if (!WorkType.isEmpty()) {
                elseCount++;
            }
        }
        int returnArray[] = new int[]{stateCompanyCount, startupCount, elseCount};
        return returnArray;
    }

    static ArrayList<Integer> SalaryCount(Student[] students) {
        /*
        将所有薪资排序并输出
         */
        ArrayList<Integer> SalaryList = new ArrayList<>();
        for (Student s : students) {
            float salary = s.getSalary();
            if (s.getWorkPlace().isEmpty() || salary == 0) {
                continue;
            }
            SalaryList.add((int) salary);
        }
        Collections.sort(SalaryList);
        return SalaryList;
    }

=======
>>>>>>> 2 count methods added
    public static void main(String[] args) {
        CSVReader reader = new CSVReader("DSAAProject\\FileStorage\\Project_data_20181208.csv"); // Use the relative path of the .csv file
        ArrayList<Student> students = reader.parse(); // Format the data into a list
        // 毕业去向
        int[] DreamArray = CountDream(students.toArray(new Student[0]));
        System.out.println(DreamArray[0] + " " + DreamArray[1] + " " + DreamArray[2]);
        // 硕博意愿
        int[] DegreeArray = CountDegree(students.toArray(new Student[0]));
        System.out.println(DegreeArray[0] + " " + DegreeArray[1] + " " + DegreeArray[2] + " " + DegreeArray[3]);
<<<<<<< HEAD
        // 工作类型
        int[] WorkTypeArray = CountWorkType(students.toArray(new Student[0]));
        System.out.println(WorkTypeArray[0] + " " + WorkTypeArray[1] + " " + WorkTypeArray[2]);
        // 工资统计
        ArrayList<Integer> salaryList = SalaryCount(students.toArray(new Student[0]));
        for (int i = 0; i < salaryList.size(); i++) {
            System.out.println(salaryList.get(i));
        }
=======
>>>>>>> 2 count methods added


    }
}