import java.util.*;
// JavaFX imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    // Global Variables
    static double FM[][] = new double[2][2];

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        System.out.println("Running: Assignment 1 Charts\n");
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("Main.fxml"));
        System.out.println("Connection Established\n");
        primaryStage.setTitle("Assignment 1 Algorithm Chart Display");
        primaryStage.setScene(new Scene(root, 1200, 443));
        primaryStage.show();
    }

    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);
        boolean cont = true;

        while (cont) {

            System.out.println("Please choose the nth value you would like to compute or press -1 to quit");
            int num = scan.nextInt();
            if (num < -1) {
                System.out.println("\nYou've entered an invalid number");
                cont = false;
                break;
            } else if (num == -1) {
                System.out.println("\nProgram finished");
                cont = false;
                break;
            }

            // Initializing array arr of length num
            double arr[] = new double[num];

            System.out.println("\nPlease choose which Fibonacci Sequence you would like to run: \n1.) Recursive\n2.) Loop\n3.) Matrix Exponention\n4.) All Programs\n5.) Display Charts for Pre-Determined Sequence Sizes (does not print time for each iteration to system)");
            int fib = scan.nextInt();
            if (fib < 0 || fib > 5) {
                System.out.println("\nYou've entered an invalid number");
                cont = false;
                break;
            }

            // running different algorithms
            long timeStart, timeEnd, val = 0;
            double dub;
            if (fib == 1) {
                timeStart = System.nanoTime();
                val = runRecAlg(num);
                timeEnd = System.nanoTime();
                printVal(num, val, timeStart, timeEnd, 1);
            }
            else if (fib == 2) {
                timeStart = System.nanoTime();
                dub = runLoopAlg(num);
                timeEnd = System.nanoTime();
                printVal(num, (long) dub, timeStart, timeEnd, 2);
            }
            else if (fib == 3) {
                timeStart = System.nanoTime();
                dub = Fibonacci(num);
                timeEnd = System.nanoTime();
                printVal(num, (long) dub, timeStart, timeEnd, 3);
            }
            else if (fib == 4){
                long timeSRec, timeERec, timeSLoop, timeELoop, timeSMat, timeEMat, timeTotal, temp;
                int numRec, numLoop, numMat, multRec, multLoop, multMat;
                if (num == 0) {
                    numRec = 40; numLoop = 100; numMat = 50;
                    multRec = 1; multLoop = 500; multMat = 1;
                }
                else {
                    numRec = num; numLoop = num; numMat = num;
                    multRec = 3; multLoop = 3; multMat = 3;
                }

                // recChart
                System.out.print("\n\nInitializing Recursive Algorithm Chart");

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
                    printVal(x, val, 0, timeTotal, 1);
                }

                // loopChart
                System.out.print("\n\nInitializing Loop Algorithm Chart");

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
                }

                // matChart
                System.out.print("\n\nInitializing Matrix Exponentiation Algorithm Chart");

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
                }

                System.out.print("\n");
            }
            else if (fib == 5){
                launch(args);
                cont = false;
            }
            System.out.println();
        }
    }

    /*
     * runRecAlg runs the recursive algorithm to produce an Fibonacci array with printed values
     *
     * @param num    the number of iterations
     * @param arr[]  the array to set
     * @param temp1  the current value of the recursive formula
     * @param temp2  temp1 in the previous iteration
     */
    public static long runRecAlg(int num){
        if (num == 0)
            return 0;
        else if (num == 1)
            return 1;
        else
            return runRecAlg(num-1)+runRecAlg(num-2);
    }

    /*
     * runLoopAlg runs the loop algorithm to produce an Fibonacci value
     *
     * @param num    the number of iterations
     */
    public static double runLoopAlg(int num){
        double temp1 = 1, temp2 = 0, hold;
        if (num == 0)
            return 0;
        else {
            for (int z = 1; z < num; z++) {
                hold = temp1;
                temp1 = temp1 + temp2;
                temp2 = hold;
            }
        }
        return temp1;
    }

    /*
     * Fibonacci returns the value of the Fibonacci sequence corresponding to the argument value
     *
     * @param n  the sequence to which to return a value
     * @return the value of the Fibonacci sequence at the sequence specified
     */
    public static double Fibonacci(int n){
        if (n == 0)
            return 0;
        else {
            FM = new double[][]{{1, 1}, {1, 0}};
            MatrixPower(n-1, FM);
            return FM[0][0];
        }
    }

    /*
     * MatrixPower produces a matrix to return the Fibonacci sequence value
     *
     * @param n  the sequence to which to return a value
     * @param FM reference to the matrix to which to use as input and as return value
     */
    public static void MatrixPower(int n, double FM[][]){
        if (n > 1){
            MatrixPower(n/2, FM);
            mult2x2Matrix(FM, FM);

            if ((n % 2) != 0){
                mult2x2Matrix(FM);
            }
        }
    }

    /*
     * mult2x2Matrix overwrites the first matrix argument with the matrix multiplication of both arguments in a 2x2 matrix
     *
     * @param matrix1    the first matrix that is multiplied and written to to return
     * @param matrix2    the second matrix that is multiplied
     */
    public static void mult2x2Matrix(double matrix1[][], double matrix2[][]){

        double a, b, c, d;
        a = matrix1[0][0] * matrix2[0][0] + matrix1[1][0] * matrix2[0][1];
        b = matrix1[0][0] * matrix2[1][0] + matrix1[1][0] * matrix2[1][1];
        c = matrix1[0][1] * matrix2[0][0] + matrix1[1][1] * matrix2[0][1];
        d = matrix1[0][1] * matrix2[1][0] + matrix1[1][1] * matrix2[1][1];

        matrix1[0][0] = a;
        matrix1[1][0] = b;
        matrix1[0][1] = c;
        matrix1[1][1] = d;
    }

    /*
     * mult2x2Matrix overloaded function that multiplies the first matrix argument by the original FM definition
     *
     * @param matrix1    the first matrix that is multiplied and written to to return
     */
    public static void mult2x2Matrix(double matrix1[][]){

        double a, b, c, d;
        a = matrix1[0][0] + matrix1[1][0];
        b = matrix1[0][0];
        c = matrix1[0][1] + matrix1[1][1];
        d = matrix1[0][1];

        matrix1[0][0] = a;
        matrix1[1][0] = b;
        matrix1[0][1] = c;
        matrix1[1][1] = d;
    }

    /*
    * printArr prints an array of values
    *
    * @param arr    the array to print
     */
    public static void printArr(double arr[]){
        System.out.println("\n\nPrinting sequence:");
        for (double z = 0; z < arr.length; z++){
            System.out.println(arr[(int)z]);
        }
    }

    /*
     * printVal prints a value
     *
     * @param arr    the array to print
     */
    public static void printVal(int num, long val, long timeStart, long timeEnd, int alg){
        System.out.printf("\n%d: nanoseconds to compute F%d (%d) with alg %d is: %d", num, num, val, alg, timeEnd - timeStart);
    }

    /*
    * printTime prints a formatted version of the nanosecond input
    *
    * @param timeStart  time the algorithm started
    * @param timeEnd    time the algorithm ended
     */
    public static void printTime(long timeStart, long timeEnd){
        long timeTotal = timeEnd - timeStart;
        double secondsDub = (double)timeTotal;
        double seconds = secondsDub / (Math.pow(10,9));
        System.out.printf("\nTime to run algorithm: %d nanoseconds / %.8f seconds\n", timeTotal, seconds);
    }
}
