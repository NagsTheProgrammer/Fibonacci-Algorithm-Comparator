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

            System.out.println("Please choose the number of sequences you would like to compute or press -1 to quit");
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
            long timeStart, timeEnd;
            if (fib == 1) {
                timeStart = System.nanoTime();
                runRecAlgP(num, arr, 0, 0);
                timeEnd = System.nanoTime();
                printArr(arr);
                printTime(timeStart, timeEnd);
            }
            else if (fib == 2) {
                timeStart = System.nanoTime();
                runLoopAlgP(num, arr);
                timeEnd = System.nanoTime();
                printArr(arr);
                printTime(timeStart, timeEnd);
            }
            else if (fib == 3) {
                timeStart = System.nanoTime();
                runMatrixAlgP(num, arr);
                timeEnd = System.nanoTime();
                printArr(arr);
                printTime(timeStart, timeEnd);
            }
            else if (fib == 4){
                long timeSRec, timeERec, timeSLoop, timeELoop, timeSMat, timeEMat;

                timeSRec = System.nanoTime();
                runRecAlgP(num, arr, 0, 0);
                timeERec = System.nanoTime();
                System.out.print("\n");
                printTime(timeSRec, timeERec);

                timeSLoop = System.nanoTime();
                runLoopAlgP(num, arr);
                timeELoop = System.nanoTime();
                System.out.print("\n");
                printTime(timeSLoop, timeELoop);

                timeSMat = System.nanoTime();
                runMatrixAlgP(num, arr);
                timeEMat = System.nanoTime();
                System.out.print("\n");
                printTime(timeSMat, timeEMat);

                System.out.print("\n");
            }
            else if (fib == 5){
                launch(args);
                cont = false;
            }
        }
    }

    /*
    * runRecAlgP runs the recursive algorithm to produce an Fibonacci array with printed values
    *
    * @param num    the number of iterations
    * @param arr[]  the array to set
    * @param temp1  the current value of the recursive formula
    * @param temp2  temp1 in the previous iteration
     */
    public static void runRecAlgP(int num, double arr[], double temp1, double temp2){
        long timeStart = System.nanoTime();
        if (num == 0)
            return;
        if (temp1 == 0) {
            arr[arr.length-num] = temp1;
            temp1 = 1;
        }
        else if (temp1 == 1 && temp2 ==0) {
            arr[arr.length-num] = temp1;
            temp2 = 1;
        }
        else if (temp1 == 1 && temp2 ==1) {
            arr[arr.length-num] = temp1;
            temp1 = 2;
        }
        else {
            double hold = temp1;
            arr[arr.length-num] = temp1;
            temp1 = temp1 + temp2;
            temp2 = hold;
        }
        long timeEnd = System.nanoTime();
        System.out.printf("\n%d nanoseconds to compute F%d with recursion alg 1: %d", arr.length-num, arr.length-num, timeEnd-timeStart);
        runRecAlgP(num-1, arr, temp1, temp2);
    }

    /*
    * runLoopAlgP runs the loop algorithm to produce an Fibonacci array with printed values
    *
    * @param num    the number of iterations
    * @param arr[]  the array to set
    */
    public static void runLoopAlgP(int num, double arr[]){
        double temp1 = 1, temp2 = 1, hold;
        long timeStart, timeEnd;

        if (num == 0)
            return;

        if (num > 0) {
            timeStart = System.nanoTime();
            arr[0] = 0;
            timeEnd = System.nanoTime();
            System.out.printf("\n0: nanoseconds to compute F0 with loop alg 2: %d", timeEnd - timeStart);
        }
        if (num > 1) {
            timeStart = System.nanoTime();
            arr[1] = 1;
            timeEnd = System.nanoTime();
            System.out.printf("\n1: nanoseconds to compute F1 with loop alg 2: %d", timeEnd - timeStart);
        }
        if (num > 2) {
            for (int z = 2; z < num; z++) {
                timeStart = System.nanoTime();
                hold = temp1;
                arr[z] = temp1;
                temp1 = temp1 + temp2;
                temp2 = hold;
                timeEnd = System.nanoTime();
                System.out.printf("\n%d: nanoseconds to compute F%d with loop alg 2: %d", z, z, timeEnd - timeStart);

            }
        }
    }

    /*
     * runMatrixAlgP runs the matrix exponentiation algorithm to produce an Fibonacci array with printed values
     *
     * @param num    the number of iterations
     * @param arr[]  the array to set
     */
    public static void runMatrixAlgP(int num, double arr[]){
        long timeStart, timeEnd;
        for (int z = 0; z < num; z++) {
            timeStart = System.nanoTime();
            arr[z] = Fibonacci(z);
            timeEnd = System.nanoTime();
            System.out.printf("\n%d nanoseconds to compute F%d with matrix alg 3: %d", z, z, timeEnd-timeStart);

        }
    }

    /*
     * runRecAlg runs the recursive algorithm to produce an Fibonacci array
     *
     * @param num    the number of iterations
     * @param arr[]  the array to set
     * @param temp1  the current value of the recursive formula
     * @param temp2  temp1 in the previous iteration
     */
    public static void runRecAlg(int num, double arr[], double temp1, double temp2){
        if (num == 0)
            return;
        if (temp1 == 0) {
            arr[arr.length-num] = temp1;
            temp1 = 1;
        }
        else if (temp1 == 1 && temp2 ==0) {
            arr[arr.length-num] = temp1;
            temp2 = 1;
        }
        else if (temp1 == 1 && temp2 ==1) {
            arr[arr.length-num] = temp1;
            temp1 = 2;
        }
        else {
            double hold = temp1;
            arr[arr.length-num] = temp1;
            temp1 = temp1 + temp2;
            temp2 = hold;
        }
        runRecAlg(num-1, arr, temp1, temp2);
    }

    /*
     * runLoopAlg runs the loop algorithm to produce an Fibonacci array
     *
     * @param num    the number of iterations
     * @param arr[]  the array to set
     */
    public static void runLoopAlg(int num, double arr[]){
        double temp1 = 1, temp2 = 1, hold;
        if (num == 0)
            return;

        if (num > 0) {
            arr[0] = 0;
        }
        if (num > 1) {
            arr[1] = 1;
        }
        if (num > 2) {
            for (int z = 2; z < num; z++) {
                hold = temp1;
                arr[z] = temp1;
                temp1 = temp1 + temp2;
                temp2 = hold;
            }
        }
    }

    /*
     * runMatrixAlg runs the matrix exponentiation algorithm to produce an Fibonacci array
     *
     * @param num    the number of iterations
     * @param arr[]  the array to set
     */
    public static void runMatrixAlg(int num, double arr[]){
        for (int z = 0; z < num; z++) {
            arr[z] = Fibonacci(z);
        }
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
        double prod[][] = new double[2][2];
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    prod[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        System.arraycopy(prod, 0, matrix1, 0, prod.length);
        //return prod;
    }

    /*
     * mult2x2Matrix overloaded function that multiplies the first matrix argument by the original FM definition
     *
     * @param matrix1    the first matrix that is multiplied and written to to return
     */
    public static void mult2x2Matrix(double matrix1[][]){
        double prod[][] = new double[2][2];
        int matrix2[][] = {{1, 1}, {1, 0}};
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    prod[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        System.arraycopy(prod, 0, matrix1, 0, prod.length);
        //return prod;
    }
}
