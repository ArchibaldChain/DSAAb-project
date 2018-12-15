import java.io.*;
import java.util.Scanner;

/**
 * ProjectName:    DSAAProject
 * Author:         Archibald Chain
 * CreateDate:     2018/12/14 13:39
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */




public class FileIO {
    public static String fileReader(String path) {
        StringBuilder sb = new StringBuilder();

        try{
            Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
            BufferedReader bf = new BufferedReader(reader);
            String a = null;
            // read line by line
            while ((a = bf.readLine()) != null){
                sb.append(a+"\n");
            }
            bf.close();
            reader.close();

            System.out.println("Finish Reading");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void fileWriter(String path){
        try{
            Writer wr = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write("utf-8");
            bw.close();
            wr.close();

            System.out.println("Finish Writing");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Input the name of file name:");
        Scanner sc = new Scanner(System.in);
        String file = sc.nextLine();
        String str = fileReader(file);
        System.out.println(str);
        //fileWriter();

        System.out.println("Please enter the file name:");
        file = sc.next();
        fileWriter(file);
    }
}

