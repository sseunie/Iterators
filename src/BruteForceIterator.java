import java.util.Arrays;

public class BruteForceIterator {
    int[] currentCombination;
    int[] nextCombination;
    int n;

    public BruteForceIterator(int n) {
        this.n = n;
        currentCombination = new int[n];
        nextCombination = new int[n];
        Arrays.fill(currentCombination, 0);
        Arrays.fill(nextCombination, 0);
    }

    public int[] next() {
        int position = -1;
        currentCombination = Arrays.copyOf(nextCombination, n);

        for (int i = n-1; i >= 0; i--) {
            if (nextCombination[i] == 0) {
                nextCombination[i] = 1;
                position = i;
                break;
            }
        }
        if (position == -1) {
            nextCombination = null;
        }
        else if (position != n-1) {
            for (int i = position+1; i < n; i++) {
                nextCombination[i] = 0;
            }
        }
        return currentCombination;
    }

    public boolean hasNext() {
        if (nextCombination != null) {
            return true;
        }
        return false;
    }

    /*
    public static void main(String[] args) {
        BruteForceIterator iterator = new BruteForceIterator(5);
        int count = 0;
        String combination;
        while (iterator.hasNext()) {
            combination = Arrays.toString(iterator.next());
            if (!combination.contains("1, 1")) {
                count++;
                System.out.println(combination);
            }
        }
        System.out.println(count);
    }*/
}