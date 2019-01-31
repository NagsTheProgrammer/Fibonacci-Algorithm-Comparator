import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;
import javafx.collections.*;

public class GUIController implements Initializable {

    @FXML
    private LineChart<Double, Double> matChart;

    @FXML
    private LineChart<Double, Double> recChart;

    @FXML
    private LineChart<Double, Double> loopChart;

    @FXML
    private NumberAxis recX, recY, loopX, loopY, matX, matY;

    @FXML
    private Button setChart;


    public void initialize(URL u, ResourceBundle r) {

        recX.setAutoRanging(false);
        recX.setLowerBound(0);
        recX.setUpperBound(100);
        recX.setTickUnit(10);

        recY.setAutoRanging(false);
        recY.setLowerBound(0);
        recY.setUpperBound(10000000);
        recY.setTickUnit(500000);

        loopX.setAutoRanging(false);
        loopX.setLowerBound(0);
        loopX.setUpperBound(100);
        loopX.setTickUnit(10);

        loopY.setAutoRanging(false);
        loopY.setLowerBound(0);
        loopY.setUpperBound(10000000);
        loopY.setTickUnit(500000);

        matX.setAutoRanging(false);
        matX.setLowerBound(0);
        matX.setUpperBound(100);
        matX.setTickUnit(10);

        matY.setAutoRanging(false);
        matY.setLowerBound(0);
        matY.setUpperBound(10000000);
        matY.setTickUnit(500000);

        long timeStart, timeEnd, timeTotal, temp;
        int num = 100;

        System.out.println("Initialize called to run");

        // recChart
        System.out.println("Initializing Recursive Algorithm Chart");
        XYChart.Series<Double, Double> seriesRecChart = new XYChart.Series<>();

        for (int x = 0; x < num; x++){
            temp = 0;
            for(int y = 0; y < 10; y++) {
                double arr[] = new double[x];
                timeStart = System.nanoTime();
                Main.runRecAlgP(x, arr, 0, 0);
                timeEnd = System.nanoTime();
                temp += (timeEnd - timeStart);
            }
            timeTotal = temp / 10;
            seriesRecChart.getData().add(new XYChart.Data<>((double) (x), (double) timeTotal));
        }


        /*seriesRecChart.getData().add(new XYChart.Data<>(4.0, 29.0));
        seriesRecChart.getData().add(new XYChart.Data<>(5.0, 23.0));
        seriesRecChart.getData().add(new XYChart.Data<>(7.0, 25.0));
        seriesRecChart.getData().add(new XYChart.Data<>(2.0, 28.0));*/

        // loopChart
        System.out.println("Initializing Loop Algorithm Chart");
        XYChart.Series<Double, Double> seriesLoopChart = new XYChart.Series<>();

        for (int x = 0; x < num; x++) {
            temp = 0;
            for (int y = 0; y < 10; y++) {
                double arr[] = new double[x];
                timeStart = System.nanoTime();
                Main.runLoopAlgP(x, arr);
                timeEnd = System.nanoTime();
                temp += (timeEnd - timeStart);
            }
            timeTotal = temp / 10;
            seriesLoopChart.getData().add(new XYChart.Data<>((double)(x), (double) timeTotal));
        }

        // matChart
        System.out.println("Initializing Matrix Exponentiation Algorithm Chart");
        XYChart.Series<Double, Double> seriesMatChart = new XYChart.Series<>();

        for (int x = 0; x < num; x++) {
            temp = 0;
            for (int y = 0; y < 10; y++) {
                double arr[] = new double[x];
                timeStart = System.nanoTime();
                Main.runMatrixAlgP(x, arr);
                timeEnd = System.nanoTime();
                temp += (timeEnd - timeStart);
            }
            timeTotal = temp / 10;
            seriesMatChart.getData().add(new XYChart.Data<>((double)(x), (double) timeTotal));
        }

        loopChart.getData().add(seriesLoopChart);
        recChart.getData().add(seriesRecChart);
        matChart.getData().add(seriesMatChart);



    }
}