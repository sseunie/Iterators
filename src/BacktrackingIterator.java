import java.util.Arrays;

public class BacktrackingIterator {
    private int[] currentCombination;
    private int[] nextCombination;
    private int n;

    public BacktrackingIterator(int n) {
        this.n = n;
        currentCombination = new int[n];
        nextCombination = new int[n];
        Arrays.fill(currentCombination, 0);
        Arrays.fill(nextCombination, 0);
    }

    public int[] next() {
        currentCombination = Arrays.copyOf(nextCombination, n);
        int position = -1;

        for (int i = n-1; i >= 0; i--) {
            if (nextCombination[i] == 0) {
                while (i > 0 && nextCombination[i-1] == 1) {
                    i -= 2;
                }
                if (i >= 0) nextCombination[i] = 1;
                position = i;
                break;
            }
        }
        if (position == -1) {
            nextCombination = null;
        }
        else if(position != n-1) {
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
}





