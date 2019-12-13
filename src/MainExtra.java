import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.setOut;

public class MainExtra {
    private static int n;
    private static boolean[] options = new boolean[8];
    private static int subArrayN;
    private static int[] array;
    private static int[][] matrix;
    private static File file;

    private static String help = "This program finds all N-Digit binary strings without consecutive 1’s.\n\n" +
            "Input options:\n" +
            "-file filename : input data from file\n" +
            "-m : dynamic programming using memoization\n" +
            "-t : dynamic programming using tabulation\n" +
            "-g1 : greedy problem 1\n" +
            "-g2 : greedy problem 2\n" +
            "Display options:\n" +
            "-h : shows this help message and exit\n" +
            "-dt : display time in seconds\n" +
            "-di : display input\n" +
            "-do : display output";

    public static void main(String[] args) {
        String arguments = Arrays.toString(args);
        if (arguments.contains("-h")) System.out.println(help);
        else {
            if (!arguments.contains("-file")) System.out.println("\nFalta archivo de datos.\n" + help);
            else {
                for (int i = 0; i < args.length; i++) {
                    switch (args[i]) {
                        case "-file":
                            try {
                                file = new File(args[i+1]);
                                options[0] = true;
                            } catch (Exception e) {}
                            break;
                        case "-g1":
                            options[1] = true;
                            break;
                        case "-g2":
                            options[2] = true;
                            break;
                        case "-dt":
                            options[4] = true;
                            break;
                        case "-di":
                            options[5] = true;
                            break;
                        case "-do":
                            options[6] = true;
                            break;
                        case "-t":
                            options[7] = true;
                            break;
                        case "-m":
                            options[3] = true;
                            break;
                        default:
                            break;
                    }
                }
                openFile();
            }
        }
    }

    private static void openFile() {
        if (options[0]) {
            try {
                Scanner scanner = new Scanner(file);
                if (options[1]) {
                    if (scanner.hasNextLine()) {
                        subArrayN = Integer.parseInt(scanner.nextLine());
                        String arrayString = scanner.nextLine();
                        array = stringArrayToInt(arrayString.split(" "));
                    }
                }
                if (options[2]) {
                    if (scanner.hasNextLine()) {
                        String arrayString = scanner.nextLine();
                        array = stringArrayToInt(arrayString.split(" "));
                    }
                }
                if(options[3] || options[7]) {
                    if (scanner.hasNextLine()) {
                        n = Integer.parseInt(scanner.nextLine());
                        matrix = new int[n][];
                        for (int i = 0; i < n; i++) {
                            String arrayString = scanner.nextLine();
                            matrix[i] = stringArrayToInt(arrayString.split(" "));
                        }
                    }
                }

            } catch (FileNotFoundException e) {}
            runner();
        }
    }

    private static int[] stringArrayToInt(String[] stringArray) {
        int[] array = new int[stringArray.length];
        for (int i=0; i < stringArray.length; i++) {
            array[i] = Integer.parseInt(stringArray[i]);
        }
        return array;
    }

    private static void runner() {
        printInput(options[5]);
        if (options[1]) {
            printTime(options[4], calculateTime("g1"), "Greedy (minimum sub array)");
        }
        if (options[2]) {
            printTime(options[4], calculateTime("g2"), "Greedy (maximum number of consecutive 1's)");
        }
        if (options[3]) {
            printTime(options[4], calculateTime("m"), "Memoization (find minimum cost)");
        }
        if (options[7]) {
            printTime(options[4], calculateTime("t"), "Tabulation (find minimum cost)");
        }
    }

    private static double calculateTime(String method) {
        double time = 0;
        double timeStart;
        double timeFinish;
        switch (method) {
            case "g1":
                //STARTS CALCULATING TIME
                timeStart = currentTimeMillis();
                runWithGreedyProblem1();
                timeFinish = (currentTimeMillis() - timeStart)/1000;
                //ENDS CALCULATING TIME
                time += timeFinish;
                break;
            case "g2":
                //STARTS CALCULATING TIME
                timeStart = currentTimeMillis();
                runWithGreedyProblem2();
                timeFinish = (currentTimeMillis() - timeStart)/1000;
                //ENDS CALCULATING TIME
                time+=timeFinish;
                break;
            case "t":
                //STARTS CALCULATING TIME
                timeStart = currentTimeMillis();
                runWithTabulation();
                timeFinish = (currentTimeMillis() - timeStart)/1000;
                //ENDS CALCULATING TIME
                time+=timeFinish;
                break;
            case "m":
                //STARTS CALCULATING TIME
                timeStart = currentTimeMillis();
                runWithMemoization();
                timeFinish = (currentTimeMillis() - timeStart)/1000;
                //ENDS CALCULATING TIME
                time+=timeFinish;
                break;
            default:
                break;
        }
        return time;
    }

    private static void runWithGreedyProblem1() {
        String print;
        print = Greedy.subArrayMinimumWeight(array, subArrayN);
        if (options[6]) printOutput(print);
    }

    private static void runWithGreedyProblem2() {
        int count;
        count = Greedy.maximumNumberOfConsecutive1s(array);
        if (options[6]) printOutput(count);
    }

    private static void runWithTabulation() {
        int count = DynamicProgramming.findMinCostTab(matrix);
        if(options[6]) printOutput(count);
    }

    private static void runWithMemoization() {
        HashMap<String,Integer> map = new HashMap<>();
        int count = DynamicProgramming.findMinCostMem(matrix, matrix.length,matrix[0].length, map);
        if(options[6]) printOutput(count);
    }

    private static void printOutput(int count) {
        if (options[3]) System.out.println("The path with minimum cost is: " + count);
        if (options[2]) System.out.println("Number of maximum consecutive 1s by replacing one 0 is: " + count);
    }

    private static void printOutput(String print) {
        if(options[1]) System.out.print("The smallest array that sums more or equal than " + subArrayN + " is: " + print + "\n");
    }

    private static void printInput(boolean print) {
        if (print) {
            if (options[1]) {
                System.out.println("\nn = " + subArrayN + "\n" +
                        "Initial array = ");
                for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i] + " ");
                }
                System.out.println();
            }
            if (options[2]) {
                for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i] + " ");
                }
                System.out.println();
            }
            if (options[3] || options[7]) {
                for(int i = 0; i < matrix.length; i++){
                    for (int j = 0; j < matrix[0].length; j++) {
                        System.out.print(matrix[i][j]);
                    }
                    System.out.println();
                }
            }
        }
    }

    private static void printTime(boolean print, double time, String method) {
        if (print) System.out.println("Time of the execution using " + method + ": " + time + "\n");
    }
}