public class Recursive {
    public static int backtracking(int n, int lastDigit)
    {
        if (n == 0) return 0;
        else if (n == 1) return (lastDigit == 1) ? 1: 2;

        if (lastDigit == 0) {
            return backtracking(n - 1, 0) + backtracking(n - 1, 1);
        } else {
            return backtracking(n - 1, 0);
        }
    }

    public static int bruteForce(int n, String out) {
        if (n == 0) return (out.contains("11")) ? 0: 1;
        return bruteForce(n -1, out+"0") + bruteForce(n-1,out+"1");
    }
}
