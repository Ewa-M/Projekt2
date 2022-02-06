package Project;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Statistics {

    int military = 50;
    int gold = 50;
    int food = 50;
    int advancement = 50;
    BarChart<String, Number> chart;
    XYChart.Data<String, Number> militaryData = new XYChart.Data<String, Number>("military", 50);
    XYChart.Data<String, Number> goldData = new XYChart.Data<String, Number>("gold", 50);
    XYChart.Data<String, Number> foodData = new XYChart.Data<String, Number>("food", 50);
    XYChart.Data<String, Number> advancementData = new XYChart.Data<String, Number>("advancement", 50);


    Statistics() {
        CategoryAxis xAxis    = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 100, 1);

        chart  = new BarChart<String, Number>(xAxis, yAxis);
        XYChart.Series<String, Number> series0 = new XYChart.Series<String, Number>();
        series0.getData().add(militaryData);
        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.getData().add(goldData);
        XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
        series2.getData().add(foodData);
        XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
        series3.getData().add(advancementData);
        chart.setLegendVisible(false);
       chart.getData().addAll(series0, series1, series2, series3);
    }

    public boolean ifLost(int increase, int increaseBy) {
        switch (increase) {
            case 0:
                military = Math.min( 100, Math.max(0, military + increaseBy) );
                militaryData.setYValue(military);
                if (military == 0)  return true;
                break;
            case 1:
                gold = Math.min( 100, Math.max(0, gold + increaseBy)  );
                goldData.setYValue(gold);
                if (gold == 0)  return true;
                break;
            case 2:
                food = Math.min( 100, Math.max(0, food + increaseBy)  );
                foodData.setYValue(food);
                if (food == 0)  return true;
                break;
            case 3:
                advancement = Math.min( 100, Math.max(0, advancement + increaseBy)  );
                advancementData.setYValue(advancement);
                if (advancement == 0)  return true;
                break;
        }
        return false;
    }

}
