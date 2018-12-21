package Xchart;


import org.knowm.xchart.*;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ProjectName:    DSAAProject
 * Author:         Archibald Chain
 * CreateDate:     2018/12/17 17:16
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class GetChart implements ExampleChart<PieChart> {

    private String[] labels;
    private int[] values;
    private int[] values2;

    public static void main(String[] args) {
        String s[] = {"Gold","Silver","Platinum","Copper","Zinc"};
        int a[] = {24, 21, 39, 17, 40};
        int b[] = {12, 11, 29, 14, 20};
        GetChart getChart = new GetChart(s, b, a);
        getChart.drawBarChart("FileStorage/test graph", "Test Bar Graph","Test bar chart", "Test2",
                "x","y");
    }

    public GetChart(String[] labels, int[] values) {
        this.labels = labels;
        this.values = values;
    }

    public GetChart(String[] labels, int[] values, int[] values2) {
        this.labels = labels;
        this.values = values;
        this.values2 = values2;
    }

    public void drawBarChart(String path, String title, String seriesName, String xLabel, String yLabel){
        drawBarChart(path, title, seriesName, null, xLabel, yLabel);
    }

    public void drawBarChart(String path, String title, String seriesName1, String seriesName2,
                             String xLabel, String yLabel) {

        CategoryChart chart = getBarChart(title,seriesName1, seriesName2, xLabel, yLabel);
        new SwingWrapper<>(chart).displayChart();
    }

    private CategoryChart getBarChart(String title, String seriesName1, String seriesName2,
                                     String xLabel, String yLabel) {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title
                (title).xAxisTitle(xLabel).yAxisTitle(yLabel).build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);


        List xAxisLabel = Arrays.asList(this.labels);
        List<Integer> data1 = new ArrayList<>();
        for (int i: this.values) {
            data1.add(i);
        }
        // Series

        chart.addSeries(seriesName1, xAxisLabel, data1);

        if (this.values2.length > 0){
            List<Integer> data2 = new ArrayList<>();
            for (int i: this.values2) {
                data2.add(i);
            }
            chart.addSeries(seriesName2, xAxisLabel, data2);
        }

        return chart;
    }


    public void drawPieChart(String path, String title)  {

        PieChart chart = getChart();
        new SwingWrapper<>(chart).displayChart(title);

        try {
            BitmapEncoder.saveBitmap(chart, path, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PieChart getChart() {

        // Create Chart
        PieChart chart = new PieChartBuilder().width(1000).height(750).title
                (getClass().getSimpleName()).build();

        // Customize Chart
        int number = labels.length;
        Color[] sliceColors = new Color[number];
        System.out.println(number);

        if (labels.length%2 == 0){
            for (int i = 0; i < labels.length; i++) {
                if (i%2 == 0){
                    sliceColors[i] = new Color(224, 68, 14);
                } else {
                    sliceColors[i] = new Color(243, 180, 159);
                }
            }
        }
        else{
            sliceColors[0] = new Color(246, 199, 182);
            for (int i = 1; i < labels.length; i++) {
                if (i%2 == 0){
                    sliceColors[i] = new Color(224, 68, 14);
                } else {
                    sliceColors[i] = new Color(236, 143, 110);
                }
            }
        }
        chart.getStyler().setSeriesColors(sliceColors);

//        Color[] Colors = new Color[] {
//                new Color(255,0,0),
//                new Color(224, 68, 14),
//                new Color(230, 105, 62),
//                new Color(236, 143, 110),
//                new Color(243, 180, 159),
//                new Color(246, 199, 182) };

        // Series
        for (int i = 0; i < number; i++) {
            chart.addSeries(labels[i], values[i]);
        }


        return chart;
    }
}
