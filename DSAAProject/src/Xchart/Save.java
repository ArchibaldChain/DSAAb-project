package Xchart;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.internal.chartpart.Chart;
import javax.swing.*;
import java.io.IOException;



class Save {
    static int savePNG(Chart chart, String path) {
        JFrame f = new JFrame();
        f.setSize(400, 300);
        f.setLocation(580, 240);
        f.setLayout(null);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setVisible(false);

        int option = JOptionPane.showConfirmDialog(f, "Do you want to save it？");
        if (JOptionPane.OK_OPTION == option)
        {

            try {
                BitmapEncoder.saveBitmap(chart, path, BitmapEncoder.BitmapFormat.PNG);
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*JOptionPane.showMessageDialog(f, "Successfully Saved");
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            String  Path =chooser.getSelectedFile().getPath() ;
            System.out.println ( Path );
            int returnVal = chooser.showOpenDialog(new JPanel());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
            }*/
            return 0;
        } else if (JOptionPane.CANCEL_OPTION == option){
            return 1;
        } else
            return 0;
    }

}