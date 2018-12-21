package Xchart;

import org.knowm.xchart.RadarChart;
import org.knowm.xchart.RadarChartBuilder;
import org.knowm.xchart.internal.chartpart.Chart;

/**
 * ProjectName:    DSAAProject
 * Author:         Archibald Chain
 * CreateDate:     2018/12/20 8:48
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class TestRadarChart {

    private String[] variablesLabel;
    private double[] data1;
    private double[] data2;

    public TestRadarChart(String[] variablesLabel, double[] data1) {
        this.variablesLabel = variablesLabel;
        this.data1 = data1;
    }

    public TestRadarChart(String[] variablesLabel, double[] data1, double[] data2) {
        new TestRadarChart(variablesLabel, data1);
        this.data2 = data2;
    }

    public static void main(String[] args) {
        String s[] = {"Gold","Silver","Platinum","Copper","Zinc"};
        double a[] = {24, 21, 39, 17, 40};
        TestRadarChart testRadarChart = new TestRadarChart(s, a);
        Chart chart = testRadarChart.getRadarChart();

    }

    private Chart getRadarChart(){
        RadarChart chart = new RadarChartBuilder().width(1000).height(750).title
                (getClass().getSimpleName()).build();

        chart.setVariableLabels(variablesLabel);
        chart.addSeries("test1", data1);
        chart.addSeries("test2", data2);

        return chart;
    }
}
