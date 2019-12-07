import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

public class Main {
    private static int n;

    private static boolean[] options = new boolean[8];

    private static File file;

    private static String help = "This program finds all N-Digit binary strings without consecutive 1’s.\n\n" +
            "Input options:\n" +
            "-file filename : input data from file\n" +
            "-bf : uses brute force\n" +
            "-bt : uses backtracking\n" +
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
            //else if (arguments.contains("-bf") && arguments.contains("-bt")) System.out.println("\nSólo usar una estrategia (Fuerza bruta -bf o Backtracking -bt).\n" + help); ???????????????
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
            printTime(options[4], calculateTime("bf"));
        }
        if (options[2]) {
            printTime(options[4], calculateTime("bt"));
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
            default:
                break;
        }
        return time;
    }

    private static void runWithBruteForce() {
        BruteForceIterator iterator = new BruteForceIterator(n);
        int count = 0;
        String combination;
        while (iterator.hasNext()) {
            combination = Arrays.toString(iterator.next());
            if (!combination.contains("1, 1")) {
                count++;
                if (options[6]) System.out.println(combination);
            }
        }
        if (options[6]) System.out.println(count);
    }

    private static void runWithBacktracking() {
        BacktrackingIterator iterator = new BacktrackingIterator(n);
        int count = 0;
        while (iterator.hasNext()) {
            if (options[6]) System.out.println(Arrays.toString(iterator.next()));
            else iterator.next();
            count++;
        }
        if (options[6]) System.out.println(count);
    }

    private static void printInput(boolean print) {
        if (print) System.out.println("Number of elements (n) = " + n + "\n");
    }

    private static void printTime(boolean print, double time) {
        if (print) System.out.println("Time: " + time);
    }
}
