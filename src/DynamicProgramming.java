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

    /*
    public static int memoizationExtra(int[][] cost, int m, int n) {
        if
    }*/

    public static int findMinCost(int[][] cost, int m, int n) {
        HashMap<String, Integer> map = new HashMap<>();
        int res;

        if (n == 0 || m == 0) {
            return Integer.MAX_VALUE;
        }
        // if we're at first cell (0, 0)
        if (m == 1 && n == 1) {
            return cost[0][0];
        }

        if (map.containsKey(""+m+n)) {
            return map.get(""+m+n);
        } else {
            res = Integer.min(findMinCost(cost, m - 1, n), findMinCost(cost, m, n - 1)) + cost[m - 1][n - 1];
        }
        map.put(""+m+n, res);
        return map.get(""+m+n);
    }

    public static void main(String[] args) {
        int n = 6;

        int[][] cost = {
                        { 4, 7, 8, 6, 4 },
                        { 6, 7, 3, 9, 2 },
                        { 3, 8, 1, 2, 4 },
                        { 7, 1, 7, 3, 7 },
                        { 2, 9, 8, 9, 3 }};

        System.out.println("The minimum cost is " + findMinCost(cost, cost.length, cost[0].length));

        System.out.println("Number of " + n + "-digit binary strings " +
                "without any consecutive 1’s are " + memoization(n, 0));
        System.out.println("Number of " + n + "-digit binary strings " +
                "without any consecutive 1’s are " + tabulation(n, 0));
    }
}
