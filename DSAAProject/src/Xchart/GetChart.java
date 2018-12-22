package Xchart;


import org.knowm.xchart.*;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.RadarStyler;
import org.knowm.xchart.RadarSeries;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;



/**
 * ProjectName:    DSAAProject
 * Author:         Archibald Chain
 * CreateDate:     2018/12/17 17:16
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class GetChart {

    private String[] labels;
    private int[] values;
    private int[] values2;

    public static void main(String[] args) {
        String[] s = {"Gold", "Silver", "Platinum", "Copper", "Zinc"};
        int[] a = {24, 21, 39, 17, 40};
        int[] b = {12, 11, 29, 14, 20};
        GetChart getChart = new GetChart(s, b, a);
        getChart.drawBarChart("FileStorage/test graph", "Test Bar Graph","Test bar chart", "Test2",
                "x","y");
    }

    /**
     * Two construct method
     * @param labels the label of every value
     * @param values    the first group of values
     * @param values2   the second group of values, used for comparing
     */

    public GetChart(String[] labels, int[] values, int[] values2) {
        this.labels = labels;
        this.values = values;
        this.values2 = values2;
    }

    public GetChart(String[] labels, int[] values) {
        this.labels = labels;
        this.values = values;
    }



    public void drawRadarChart(String path, String title, String nameSeries){
        double max = findMaxValue(this.values);
        double[] data1 = new double[this.values.length];

        for (int i = 0; i < values.length; i++) {
            data1[i] = this.values[i]/max;
        }
        Chart<RadarStyler, RadarSeries>
                chart = getRadarChart(title, nameSeries, null, data1, null);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);


        closeWindowAction(new SwingWrapper<>(chart).displayChart(title), chart, path);
    }

    public void drawRadarChart(String path, String title, String nameSeries1, String nameSeries2){

        double[] data1 = new double[this.values.length];
        double[] data2 = new double[this.values2.length];
        double max = findMaxValue(this.values);
        if (max < findMaxValue(this.values2)){
            max = findMaxValue(this.values2);
        }

        for (int i = 0; i < data1.length; i++) {
            data1[i] = this.values[i]/max;
        }
        for (int i = 0; i < data2.length; i++) {
            data2[i] = ((double)this.values2[i])/(double)this.values[i];
        }
        Chart<RadarStyler, RadarSeries>
                chart = getRadarChart(title, nameSeries1, nameSeries2,data1,data2);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setPlotContentSize(.9);

        closeWindowAction(new SwingWrapper<>(chart).displayChart(title), chart, path);

    }

    public void drawBarChart(String path, String title, String seriesName, String xLabel, String yLabel){
        drawBarChart(path, title, seriesName, null, xLabel, yLabel);
    }

    public void drawBarChart(String path, String title, String seriesName1, String seriesName2,
                             String xLabel, String yLabel) {

        CategoryChart chart = getBarChart(title, seriesName1, seriesName2, xLabel, yLabel);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);

        closeWindowAction(new SwingWrapper<>(chart).displayChart(title), chart, path);
    }

    public void drawPieChart(String path, String title)  {

        PieChart chart = getChart(title);

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.Label);
        chart.getStyler().setDefaultSeriesRenderStyle(PieSeries.PieSeriesRenderStyle.Pie);

        chart.getStyler().setAnnotationDistance(1.05);
        chart.getStyler().setPlotContentSize(.85);
        closeWindowAction(new SwingWrapper<>(chart).displayChart(title), chart, path);
    }

    public void drawDonutChart(String path, String title){
        PieChart chart = getChart(title);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.Label);
        chart.getStyler().setAnnotationDistance(1.1);
        chart.getStyler().setPlotContentSize(.85);
        chart.getStyler().setDefaultSeriesRenderStyle(PieSeries.PieSeriesRenderStyle.Donut);

        closeWindowAction(new SwingWrapper<>(chart).displayChart(title), chart, path);

    }



    private CategoryChart getBarChart(String title, String seriesName1, String seriesName2,
                                      String xLabel, String yLabel) {

        // Create Chart
        int length = getLength();
        CategoryChart chart = new CategoryChartBuilder().width(length).height(4*length/7).title
                (title).xAxisTitle(xLabel).yAxisTitle(yLabel).build();

        // Customize Chart

        chart.getStyler().setHasAnnotations(false);
        chart.getStyler().setOverlapped(true);


        List xAxisLabel = Arrays.asList(this.labels);
        List<Integer> data1 = new ArrayList<>();
        for (int i: this.values) {
            data1.add(i);
        }
        // Series

        chart.addSeries(seriesName1, xAxisLabel, data1);

        if (this.values2 != null && seriesName2 != null && this.values2.length > 0){
            List<Integer> data2 = new ArrayList<>();
            for (int i: this.values2) {
                data2.add(i);
            }
            chart.addSeries(seriesName2, xAxisLabel, data2);
        }

        return chart;
    }


    private double findMaxValue(int[] a){
        double max = a[0];
        for (int i1 : a) {
            if (i1 > max)
                max = i1;
        }
        return max;
    }

    private Chart<RadarStyler, RadarSeries> getRadarChart
            (String title, String nameSeries1, String nameSeries2, double[] data1, double[] data2)
    {
        int length = getLength();
        RadarChart chart = new RadarChartBuilder().width(length).height(3*length/4).title
                (title).build();

        chart.setVariableLabels(labels);
        chart.addSeries(nameSeries1, data1);
        if (data2 != null && nameSeries2 != null){
            chart.addSeries(nameSeries2, data2);
        }

        return chart;
    }

    private PieChart getChart(String title) {

        // Create Chart
        int length = getLength();
        PieChart chart = new PieChartBuilder().width(length).height(3*length/4).title
                (title).build();

        // Customize Chart
        int number = labels.length;
        Color[] sliceColors = new Color[number];

        for (int i = 0; i < labels.length; i++) {
            int a = (int)(Math.random() * 256);
            int b = (int)(Math.random() * 256);
            int c = (int)(Math.random() * 256);

            sliceColors[i] = new Color(a, b, c);

        }
        chart.getStyler().setSeriesColors(sliceColors);

        // Series
        for (int i = 0; i < number; i++) {
            chart.addSeries(labels[i], values[i]);
        }


        return chart;
    }

    private int getLength(){

        if (values.length < 8)
            return 800;
        if (values.length < 15)
            return 1000;
        if (values.length < 20)
            return 1200;
        else
            return 1600;
    }

    static void closeWindowAction(JFrame jFrame, Chart chart, String path){
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = Save.savePNG(chart, path);

                if (a == 1) {
                    jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                } else
                    jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });


    }
}
