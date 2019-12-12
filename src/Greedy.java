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

    public static int maximunNumberOfConsecutive1s(int[] array) {
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

        for (int i = n-1; i >= 0; i--) {

            if (positions[i] == 0) {
                if (array[positions[i]+1] == 0) {
                    int pos = positions[i]+2;
                    while(pos < n && array[pos] != 0) {
                        max += 1;
                        pos++;
                    }
                    return max+1;
                }
            }

            if (positions[i] == n-1) {
                if (array[positions[i]-1] == 0) {
                    int pos = positions[i]+2;
                    while(pos < n && array[pos] != 0) {
                        accumulated[i] += 1;
                        pos++;
                    }
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
        /*
        String[] array =  subArrayMinimumWeight(new int[]{1,2,3,4,5,6,7,8,9},20);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }*/

        System.out.println(maximunNumberOfConsecutive1s(new int[]{1,0,0,1,1}));
    }
}
