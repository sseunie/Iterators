public class Tabulation {
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
        System.out.println(tabulation(6,0));
    }
}

/*
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
        System.out.println(tabulation(6,0));
    }
 */