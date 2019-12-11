import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

public class Main {
    private static int n;

    private static boolean[] options = new boolean[9];

    private static File file;

    private static String help = "This program finds all N-Digit binary strings without consecutive 1’s.\n\n" +
            "Input options:\n" +
            "-file filename : input data from file\n" +
            "-bf : uses brute force. Unless -r specified uses iteration\n" +
            "-bt : uses backtracking. Unless -r specified uses iteration\n" +
            "-t : dynamic programming using tabulation\n" +
            "-m : dynamic programming using memoization\n" +
            "-r : uses the recursive version\n" +
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
                        case "-bf":
                            options[1] = true;
                            break;
                        case "-bt":
                            options[2] = true;
                            break;
                        case "-r":
                            options[3] = true;
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
                            options[8] = true;
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
                if (scanner.hasNextLine()) {
                    n = Integer.parseInt(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {}
            runner();
        }
    }

    private static void runner() {
        printInput(options[5]);
        if (options[1]) {
            printTime(options[4], calculateTime("bf"), "Brute Force");
        }
        if (options[2]) {
            printTime(options[4], calculateTime("bt"), "Backtracking");
        }
        if (options[7]) {
            printTime(options[4], calculateTime("t"), "Tabulation");
        }
        if (options[8]) {
            printTime(options[4], calculateTime("m"), "Memoization");
        }
    }

    private static double calculateTime(String method) {
        double time = 0;
        double timeStart;
        double timeFinish;
        switch (method) {
            case "bf":
                //STARTS CALCULATING TIME
                timeStart = currentTimeMillis();
                runWithBruteForce();
                timeFinish = (currentTimeMillis() - timeStart)/1000;
                //ENDS CALCULATING TIME
                time += timeFinish;
                break;
            case "bt":
                //STARTS CALCULATING TIME
                timeStart = currentTimeMillis();
                runWithBacktracking();
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

    private static void runWithBruteForce() {
        int count = 0;
        if (options[3]) count = Recursive.bruteForce(n, "");
        else {
            BruteForceIterator iterator = new BruteForceIterator(n);
            while (iterator.hasNext()) {
                String combination = Arrays.toString(iterator.next());
                if (!combination.contains("1, 1")) {
                    count++;
                }
            }
        }
        if (options[6]) printOutput(count);
    }

    private static void runWithBacktracking() {
        int count = 0;
        if (options[3]) count = Recursive.backtracking(n, 0);
        else {
            BacktrackingIterator iterator = new BacktrackingIterator(n);
            while (iterator.hasNext()) {
                count++;
                iterator.next();
            }
        }
        if (options[6]) printOutput(count);
    }

    private static void runWithTabulation() {
        int count = DynamicProgramming.tabulation(n, 0);
        if(options[6]) printOutput(count);
    }

    private static void runWithMemoization() {
        int count = DynamicProgramming.memoization(n, 0);
        if(options[6]) printOutput(count);
    }

    private static void printOutput(int count) {
        System.out.println("Number of " + n + "-digit binary strings " +
                "without any consecutive 1’s are " + count);
    }

    private static void printInput(boolean print) {
        if (print) System.out.println("Number of elements (n) = " + n + "\n");
    }

    private static void printTime(boolean print, double time, String method) {
        if (print) System.out.println("Time of the execution using " + method + ": " + time + "\n");
    }
}
