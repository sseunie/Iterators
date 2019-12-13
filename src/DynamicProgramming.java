import java.util.HashMap;

public class DynamicProgramming {

    public static int memoization(int n, int l, HashMap<Integer,Integer> map) {
        if (n == 0) return 1;
        if (!map.containsKey((n+l))) {
            if (l == 1) {
                map.put(n+l, memoization(n-1, 0, map));
            } else {
                map.put(n+l, memoization(n-1,1,map) + memoization(n-1,0,map));
            }
        }
        return map.get(n+l);
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
     * EJERCICIOS EXTRA EN PROGRAMACIÓN DINÁMICA
     */
    public static int findMinCostMem(int[][] cost, int m, int n, HashMap<String, Integer> map2) {
        int res;

        if (n == 0 || m == 0) {
            return Integer.MAX_VALUE;
        }

        if (m == 1 && n == 1) {
            return cost[0][0];
        }

        if (map2.containsKey(""+m+n)) {
            return map2.get(""+m+n);
        } else {
            res = Integer.min(findMinCostMem(cost, m - 1, n, map2), findMinCostMem(cost, m, n - 1, map2)) + cost[m - 1][n - 1];
        }
        map2.put(""+m+n, res);
        return map2.get(""+m+n);
    }

    public static int findMinCostTab(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[][] copy = new int[m][n];

        copy[0][0] = Integer.MAX_VALUE;
        copy[1][0] = arr[1][0];
        copy[0][1] = arr[0][1];
        System.out.println(copy[0][0]);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                copy[i][j] = Integer.min(copy[i-1][j], copy[i][j-1] + arr[i-1][j-1]);
            }
        }
        return copy[m-1][n-1];
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] cost = {
                        { 4, 7, 8, 6, 4 },
                        { 6, 7, 3, 9, 2 },
                        { 3, 8, 1, 2, 4 },
                        { 7, 1, 7, 3, 7 },
                        { 2, 9, 8, 9, 3 }};
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        System.out.println("Memoization: the minimum cost is " + findMinCostMem(cost, cost.length, cost[0].length, map2));
        System.out.println("Tabulation: the minimum cost is " + findMinCostTab(cost));
        System.out.println("Number of " + n + "-digit binary strings " +
                "without any consecutive 1’s are " + memoization(15, 0, map));
    }
}
