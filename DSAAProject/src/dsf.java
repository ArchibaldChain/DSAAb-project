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
public class dsf {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        Integer a[] = arrayList.toArray(new Integer[0]);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        String x = "中国";
        x = x.substring(0, x.length() - 1);
        System.out.println(x);
    }


    public static void test(String a){
        a = " bddddsfa";
        System.out.println("new change in local master");

    }
}


