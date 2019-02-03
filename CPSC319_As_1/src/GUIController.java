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

        /*recX.setAutoRanging(false);
        recX.setLowerBound(0);
        recX.setUpperBound(100);
        recX.setTickUnit(10);

        recY.setAutoRanging(false);
        recY.setLowerBound(0);
        recY.setUpperBound(1000);
        recY.setTickUnit(1000);*/

        loopX.setAutoRanging(false);
        loopX.setLowerBound(0);
        loopX.setUpperBound(1000);
        loopX.setTickUnit(100);

        /*loopY.setAutoRanging(false);
        loopY.setLowerBound(0);
        loopY.setUpperBound(1500);
        loopY.setTickUnit(100);

        matX.setAutoRanging(false);
        matX.setLowerBound(0);
        matX.setUpperBound(80);
        matX.setTickUnit(10);

        matY.setAutoRanging(false);
        matY.setLowerBound(0);
        matY.setUpperBound(10000);
        matY.setTickUnit(1000);*/

        recX.setLabel("nth Value of Fibonacci Computed");
        loopX.setLabel("nth Value of Fibonacci Computed");
        matX.setLabel("nth Value of Fibonacci Computed");
        recY.setLabel("Duration of Computation (ms)");
        loopY.setLabel("Duration of Computation (ms)");
        matY.setLabel("Duration of Computation (ms)");

        long timeStart, timeEnd, timeTotal, temp, val;
        double dub;
        int numRec = 40, numLoop = 1000, numMat = 80;
        int multRec = 1, multLoop = 5, multMat = 1;

        System.out.println("Initialize called to run");

        // recChart
        System.out.println("Initializing Recursive Algorithm Chart");
        XYChart.Series<Double, Double> seriesRecChart = new XYChart.Series<>();

        for (int x = 0; x < numRec; x++){
            temp = 0;
            for(int y = 0; y < multRec; y++) {
                timeStart = System.nanoTime();
                val = Main.runRecAlg(x);
                timeEnd = System.nanoTime();
                //Main.printVal(x, val, timeStart, timeEnd, 1);
                temp = temp + (timeEnd - timeStart);
            }
            timeTotal = temp / multRec;
            seriesRecChart.getData().add(new XYChart.Data<>((double) (x), (double) timeTotal));
        }

        // loopChart
        System.out.println("Initializing Loop Algorithm Chart");
        XYChart.Series<Double, Double> seriesLoopChart = new XYChart.Series<>();

        for (int x = 0; x < numLoop; x++){
            temp = 0;
            for(int y = 0; y < multLoop; y++) {
                timeStart = System.nanoTime();
                dub = Main.runLoopAlg(x);
                timeEnd = System.nanoTime();
                Main.printVal(x, (long) dub, timeStart, timeEnd, 2);
                temp = temp + (timeEnd - timeStart);
                dub = 0;
            }
            timeTotal = temp / multLoop;
            seriesLoopChart.getData().add(new XYChart.Data<>((double) (x), (double) timeTotal));
        }

        // matChart
        System.out.println("Initializing Matrix Exponentiation Algorithm Chart");
        XYChart.Series<Double, Double> seriesMatChart = new XYChart.Series<>();

        for (int x = 0; x < numMat; x++){
            temp = 0;
            for(int y = 0; y < multMat; y++) {
                timeStart = System.nanoTime();
                dub = Main.Fibonacci(x);
                timeEnd = System.nanoTime();
                Main.printVal(x, (long) dub, timeStart, timeEnd, 3);
                temp = temp + (timeEnd - timeStart);
                dub = 0;
            }
            timeTotal = temp / multMat;
            seriesMatChart.getData().add(new XYChart.Data<>((double) (x), (double) timeTotal));
        }

        loopChart.getData().add(seriesLoopChart);
        recChart.getData().add(seriesRecChart);
        matChart.getData().add(seriesMatChart);



    }
}