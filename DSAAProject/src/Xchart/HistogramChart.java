package Xchart;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Histogram Overlapped
 *
 * Demonstrates the following:
 * <ul>
 * <li>Histogram
 * <li>Bar Chart styles - overlapped, bar width
 */
public class HistogramChart  {

    private CategoryChart chart;

    public static void main(String[] args) {

        HistogramChart drawBarChart = new HistogramChart("ti", "x", "y");
        drawBarChart.addHistogram(new int[] {1,2,3,42,42,3,2}, "Test");
        drawBarChart.draw("FileStorage", "Test");

    }

    public HistogramChart(String title, String xAxisTitle, String yAxisTitle) {

        chart = new CategoryChartBuilder().width(1600).height(800).
                title(title).xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).build();
        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setAvailableSpaceFill(0.96);

    }

    public void draw(String path, String title){
        drawing(path, title);
        chart.getStyler().setOverlapped(true);
    }

    private void drawing(String path, String title) {
        new SwingWrapper<>(chart).displayChart(title);

        try {
            BitmapEncoder.saveBitmap(chart, path, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addHistogram(int[] value, String seriesName){
        ArrayList<Integer> list = new ArrayList<>();
        int max = value[0], min = value[0];
        for (int i : value) {
            list.add(i);
            if (max < i)
                max = i;
            if (min > i)
                min = i;
        }
        int number = getProperNumber(max - min);

        Histogram histogram = new Histogram(list, number, min,max);
        chart.addSeries(seriesName, histogram.getxAxisData(), histogram.getyAxisData());
    }

    public void addHistogram(double[] value, String seriesName){
        ArrayList<Double> list = new ArrayList<>();
        double max = value[0], min = value[0];
        for (double d :
                value) {
            list.add(d);
            if (max < d)
                max = d;
            if (min > d)
                min = d;
        }
        int number = getProperNumber((int)(max+1) - (int)min);
        Histogram histogram = new Histogram(list, number, (int)(max+1),(int)min);
        chart.addSeries(seriesName, histogram.getxAxisData(), histogram.getyAxisData());
    }

    private int getProperNumber(int number){
        while (number > 30)
            number = number / 2;
        while (number < 10)
            number *= 2;
        return number;
    }
}