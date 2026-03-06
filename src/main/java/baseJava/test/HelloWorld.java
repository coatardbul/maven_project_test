package baseJava.test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class HelloWorld {


    public static double calculatePearson(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Arrays must have the same length");
        }

        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0, sumY2 = 0;
        int n = x.length;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumX2 += x[i] * x[i];
            sumY2 += y[i] * y[i];
        }

        double numerator = n * sumXY - sumX * sumY;
        double denominator = Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY));

        return numerator / denominator;
    }

    public static void main(String[] args) {
//        double[] array1 = {1.54, 1.79, 5.54, -3.58, 2.72, 1.81, 6.22, 4.12, -9.47, 5.2};
        double[] array1 ={12.9,6,-1.7,-2.5,5,14,5,13,-8,10};
        double[] array2 = {4.63, 6.1, 4, 1, 5, 2.2, 2.49, 2.6, -2, 4};

        double correlation = calculatePearson(array1, array2);
        System.out.println("Pearson Correlation: " + correlation);
    }


}