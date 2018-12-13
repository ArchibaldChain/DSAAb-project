package Xchart;

import java.util.Random;
import org.knowm.xchart.*;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import javax.swing.*;
import java.io.IOException;


/**
 * ProjectName: StatisticAnalysisProject
 * Author: Archibald Chain
 * CreateDate: 2018/12/3 16:54
 * Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class TestXChart  {

    public static void main(String[] args) {
//        double[] xData = new double[] { 0.0, 1.0, 2.0 };
//        double[] yData = new double[] { 2.0, 1.0, 0.0 };
//
//// Create Chart
//
//        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
//
//// Show it
//        JFrame jFrame = new SwingWrapper(chart).displayChart();
//
//// Save it
//
//        try {
//            BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//// or save it in high-res
//
//
//        try {
//            BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.PNG, 300);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Create Chart
        XYChart chart = new XYChartBuilder().width(600).height(500).title("Gaussian Blobs").xAxisTitle("X").yAxisTitle("Y").build();

// Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(16);

// Series
        chart.addSeries("Gaussian Blob 1",
                getGaussian(1000, 1, 10), getGaussian(1000, 1, 10));
        XYSeries series = chart.addSeries("Gaussian Blob 2",
                getGaussian(1000, 1, 10), getGaussian(1000, 0, 5));
        series.setMarker(SeriesMarkers.DIAMOND);

        new SwingWrapper(chart).displayChart();



    }

    private static double[] getGaussian(int n, int a, int b){


        Random random = new Random();
        double x[] = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = Math.sqrt(b) * (random.nextGaussian() + a);
        }
        return x;
    }



    private static double[] getUniform(int n, int a, int b){
        double x[] = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = Math.random() *(b - a) + a;
        }

        return x;

    }
}






