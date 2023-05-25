package com.example.firstrestalishev.run;

import com.example.firstrestalishev.dto.MeasurementDTO;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;

import java.util.ArrayList;
import java.util.List;

public class ShowChart {

    public static final RunFunction runFunction = RunFunction.getInstance();

    //MVN -
    // <dependency>
    //    <groupId>org.knowm.xchart</groupId>
    //    <artifactId>xchart</artifactId>
    //    <version>3.8.1</version>
    //</dependency>

    public static void main(String[] args) {
        showChart();
    }

    private static void showChart() {

        // Создать экземпляр XYChart
        XYChart chart = buildChart();
        //3. Создайте данные для графика.
        // Например, вы можете использовать два массива,
        // один для значений X и один для значений Y:

        List<MeasurementDTO> measurementDTOList = runFunction.getAllMeasures();

        List<Integer> days = new ArrayList<>();
        List<Float> values = new ArrayList<>();
        int j = -1;
        /*for (int i = 0; i < measurementDTOList.size(); i++) {
            days.add(i);
        }*/

        for (MeasurementDTO measurementDTO : measurementDTOList) {
            j++;
            days.add(j);
            values.add(measurementDTO.getValue());
        }

        /*double[] xData = new double[] {0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yData = new double[] {0.0, 1.0, 4.0, 9.0, 16.0, 25.0};*/

        // Добавить данные на график
        XYSeries series = chart.addSeries("Мой график", days, values);

        new SwingWrapper<>(chart).displayChart();

    }

    private static XYChart buildChart() {
        return new XYChartBuilder()
                .width(600)
                .height(400)
                .title("Заголовок")
                .xAxisTitle("Ось X")
                .yAxisTitle("Ось Y")
                .build();
    }

}
