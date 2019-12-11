import java.util.HashMap;

public class DynamicProgramming {
    public static int memoization(int n, int l) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result;
        if (n == 0) return 1;

        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            if (l == 1) {
                result = memoization(n-1,0);
            } else {
                result = memoization(n-1,0) + memoization(n-1,1);
            }
        }
        map.put(n,result);
        return map.get(n);
    }

    public static int tabulation(int n, int l) {
        int[][] map = new int[n+1][2];

        map[0][0] = 1;
        map[0][1] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 1; j++) {
                if (j == 1) {
                    map[i][j] = map[i-1][0];
                } else {
                    map[i][j] = map[i-1][0] + map[i-1][1];
                }
            }
        }
        return map[n][l];
    }

    public static void main(String[] args) {
        int n = 6;

        System.out.println("Number of " + n + "-digit binary strings " +
                "without any consecutive 1’s are " + memoization(n, 0));
        System.out.println("Number of " + n + "-digit binary strings " +
                "without any consecutive 1’s are " + tabulation(n, 0));
    }
}
