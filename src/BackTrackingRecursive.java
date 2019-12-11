public class BackTrackingRecursive {
    public static int backtrackingRecursive(int n, int lastDigit)
    {
        if (n == 0) return 0;
        else if (n == 1) return (lastDigit == 1) ? 1: 2;

        if (lastDigit == 0) {
            return backtrackingRecursive(n - 1, 0) + backtrackingRecursive(n - 1, 1);
        }
        else {
            return backtrackingRecursive(n - 1, 0);
        }
    }

    public static void main(String[] args)
    {
        int n = 5;

        System.out.println("Number of " + n + "-digit binary strings " +
                "without any consecutive 1’s are " + backtrackingRecursive(n, 0));
    }
}
