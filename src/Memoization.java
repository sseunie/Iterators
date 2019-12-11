import java.util.HashMap;

public class Memoization {
    public static int memoization(int n, int l) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result;
        if (n == 0) {
            return 1;
        }
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

    public static void main(String[] args) {
        System.out.println(memoization(5,0));
    }
}

/*
public static int memoization(int n, int l, String out) {
        HashMap<int[], Integer> map = new HashMap<>();
        int result;
        if (n == 0) {
            System.out.println(out);
            return 1;
        }
        int[] key = new int[]{n,l};
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            if (l == 1) {
                result = memoization(n-1,0, out+"0");
            } else {
                result = memoization(n-1,0, out+"0") + memoization(n-1,1, out+"1");
            }
        }
        map.put(key,result);
        return map.get(key);
    }
 */
