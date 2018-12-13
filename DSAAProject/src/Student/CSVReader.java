package Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * This is an example to read the .csv file.
 * Or you may use the lib like OpenCSV to parse csv.
 */

public class CSVReader {
    private String URL; // The path of the file

    /**
     * Constructor
     * @param url
     */
    public CSVReader(String url){
        URL = url;
    }

    /**
     * Read the data and parse
     * @return ArrayList<Student.Student>: Student.Student Data store in list
     */
    public ArrayList<Student> parse(){

        String line; // The line br read each time
        ArrayList<Student> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(URL), StandardCharsets.UTF_8
                )
        )) {
// create an FileReader object and use buffer to improve the efficiency

            while ((line = br.readLine()) != null) {
                line = line.replace(",,", ", , "); // to make sure the length of the items below
                line += " "; // To avoid the length of items is 20

                // use comma as separator
                String[] items = line.split(",");


                for (int i = 0; i < items.length; i++) {

                    items[i] = items[i].replace(" ", ""); // reduce the useless space
                }


                // store the data into list or whatever you like
                if (Student.emptyJudger(items)){
                    // Jump over those who did not fill the blank
                    continue;
                }
                Student s = new Student(items);
                list.add(s);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // ignore

        return list;

    }
}
