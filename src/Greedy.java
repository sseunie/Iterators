import java.util.Arrays;

public class Greedy {
    public static String[] subArrayMinimumWeight(int[] array, int n) {
        String res = "";
        Arrays.sort(array);
        int i = array.length -1;
        while (n >= 0 && i >= 0) {
            res += array[i] + ",";
            n -= array[i];
            i--;
        }
        String[] result = res.split(",");
        if (n > 0) return new String[0];
        else return result;
    }

    public static int maximumNumberOfConsecutive1s(int[] array) {
        int n = array.length;
        int[] accumulated = new int[n];
        int[] positions = new int[n];

        accumulated[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 1) {
                accumulated[i] = accumulated[i-1] + 1;
            } else {
                accumulated[i] = 0;
            }
            positions[i] = i;
        }

        int[] accumulatedOriginal = Arrays.copyOf(accumulated, n);
        bubbleSort(accumulated,positions);
        int max = accumulated[n-1];
        if (max == n) return max;


        //Greedy starts
        for (int i = n-1; i >= 0; i--) {

            if (positions[i] == 0) {
                if (array[positions[i]+1] == 0) {
                    int pos = positions[i]+2;
                    while(pos < n && array[pos] != 0) {
                        accumulated[i] += 1;
                        pos++;
                    }
                    return accumulated[i]+1;
                }
            }

            if (positions[i] == n-1) {
                if (array[positions[i]-1] == 0) {
                    if (positions[i]-2 >= 0 && array[positions[i]-2] != 0) {
                        accumulated[i] += accumulatedOriginal[i-2];
                    }
                    return accumulated[i]+1;
                }
            } else {
                if (array[positions[i]-1] == 0) {
                    int pos = positions[i]+1;
                    while(pos < n && array[pos] != 0) {
                        accumulated[i] += 1;
                        pos++;
                    }
                    if (positions[i]-2 >= 0 && array[positions[i]-2] != 0) {
                        accumulated[i] += accumulatedOriginal[i-2];
                    }
                    return accumulated[i]+1;
                }
                if (array[positions[i]+1] == 0) {
                    int pos = positions[i]+2;
                    while(pos < n && array[pos] != 0) {
                        accumulated[i] += 1;
                        pos++;
                    }
                    return accumulated[i]+1;
                }

            }
        }
        return max;
    }

    public static int greedyMinCost(int[][] cost){
        int m = cost.length-1;
        int n = cost[0].length-1;

        int accumulatedCost = cost[0][0];
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (cost[i+1][j] > cost[i][j+1]) {
                accumulatedCost += cost[i][j+1];
                j++;
            } else {
                accumulatedCost += cost[i+1][j];
                i++;
            }
        }
        return accumulatedCost+cost[m][n];
    }

    public static void bubbleSort(int[] x, int[] positions) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for(int i=1; i<x.length; i++) {
                int temp;
                if(x[i-1] > x[i]) {
                    temp = positions[i - 1];
                    positions[i - 1] = positions[i];
                    positions[i] = temp;

                    temp = x[i-1];
                    x[i-1] = x[i];
                    x[i] = temp;
                    swapped = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--------------------SUBARRAY MINIMUM WEIGHT--------------------");

        String[] array =  subArrayMinimumWeight(new int[]{1,2,3,4,5,6,7,8,9},20);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();

        System.out.println("--------------------MAXIMUM NUMBER OF CONSECUTIVE 1's--------------------");
        System.out.println("Should be 7 and returns: " + maximumNumberOfConsecutive1s(new int[]{1,1,0,1,1,0,1,1,1,1,0,0}));
        System.out.println("Should be 4 and returns: " + maximumNumberOfConsecutive1s(new int[]{1,1,0,1,0,0,1,1,0,1,0,0}));
        System.out.println("Should be 3 and returns: " + maximumNumberOfConsecutive1s(new int[]{1,1,0,0,1,1}));
        System.out.println("Should be 3 and returns: " + maximumNumberOfConsecutive1s(new int[]{0,1,0,0,1,1}));
        System.out.println("Should be 5 and returns: " + maximumNumberOfConsecutive1s(new int[]{1,1,1,0,1,0}));
        System.out.println("Should be 6 and returns: " + maximumNumberOfConsecutive1s(new int[]{1,1,0,0,1,1,1,1,1,0}));
        System.out.println("Should be 1 and returns: " + maximumNumberOfConsecutive1s(new int[]{0,0,0,0,0,0}));
        System.out.println("Should be 6 and returns: " + maximumNumberOfConsecutive1s(new int[]{1,1,1,1,1,1}));


        System.out.println("--------------------FIND MINIMUM COST--------------------");
        int[][] cost = {
                { 4, 7, 8, 6, 4 },
                { 6, 7, 3, 9, 2 },
                { 3, 8, 1, 2, 4 },
                { 7, 1, 7, 3, 7 },
                { 2, 9, 8, 9, 3 }};
        System.out.println("Greedy: the minimum cost is " + greedyMinCost(cost));

    }
}