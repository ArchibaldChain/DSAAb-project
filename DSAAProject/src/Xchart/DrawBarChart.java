package Xchart;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.Histogram;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.demo.charts.bar.BarChart01;
import org.knowm.xchart.demo.charts.bar.BarChart06;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DrawBarChart implements ExampleChart<CategoryChart> {

//    public static void main(String[] args) {
//        DrawBarChart drawBarChart = new DrawBarChart();
//        drawBarChart.draw();
//    }

    public static void main(String[] args) {

        DrawBarChart drawBarChart = new DrawBarChart();
        CategoryChart chart = drawBarChart.getBarChart();
        new SwingWrapper<CategoryChart>(chart).displayChart();
    }

    public CategoryChart getBarChart() {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title
                ("Score Histogram").xAxisTitle("Score").yAxisTitle("Number").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        String[] a = { "a","b", "c", "d","f" };
        List xLabel = Arrays.asList(a);
        // Series
        chart.addSeries("test 1", xLabel,
                Arrays.asList(new Integer[] { 4, 5, 9, 6, 5 }));
        chart.addSeries("test 2", Arrays.asList(new String[] { "f","d", "e", "b","a" }),
                Arrays.asList(new Integer[] { 2, 3, 4, 3, 24 }));
        chart.setYAxisGroupTitle(0, "A");
        chart.setYAxisGroupTitle(1, "B");

        return chart;
    }

    @Override
    public CategoryChart getChart() {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(1000).height(750).title("Score Histogram").xAxisTitle("Mean").yAxisTitle("Count").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setAvailableSpaceFill(1);
        chart.getStyler().setOverlapped(true);

        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(0);
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(6);
        lista.add(7);
        lista.add(0);
        lista.add(-1);
        lista.add(-2);
        lista.add(-3);
        lista.add(-4);
        lista.add(-5);
        lista.add(-6);
        lista.add(-7);

        ArrayList<Integer> listb = new ArrayList<>();
        listb.add(0);
        listb.add(0);
        listb.add(0);

        // Series
        Histogram histogram1 = new Histogram(lista, 14, -14,14);
        //Histogram histogram2 = new Histogram(listb,4,-2,4);
        chart.addSeries("histogram 1", histogram1.getxAxisData(), histogram1.getyAxisData());
        //chart.addSeries("histogram 2", histogram2.getxAxisData(), histogram2.getyAxisData());

        return chart;
    }



}