
import java.util.*;

class Solution {

    TreeMap<Integer, Integer> low = new TreeMap<>();
    TreeMap<Integer, Integer> high = new TreeMap<>();

    int lowSize = 0, highSize = 0;
    long lowSum = 0, highSum = 0;

    void addMap(TreeMap<Integer, Integer> map, int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    void removeMap(TreeMap<Integer, Integer> map, int x) {
        int count = map.get(x);
        if (count == 1) map.remove(x);
        else map.put(x, count - 1);
    }

    void balance() {
        int target = (lowSize + highSize + 1) / 2;

        while (lowSize > target) {
            int v = low.lastKey();

            removeMap(low, v);
            lowSize--;
            lowSum -= v;

            addMap(high, v);
            highSize++;
            highSum += v;
        }

        while (lowSize < target) {
            int v = high.firstKey();

            removeMap(high, v);
            highSize--;
            highSum -= v;

            addMap(low, v);
            lowSize++;
            lowSum += v;
        }
    }

    void add(int x) {
        if (low.isEmpty() || x <= low.lastKey()) {
            addMap(low, x);
            lowSize++;
            lowSum += x;
        } else {
            addMap(high, x);
            highSize++;
            highSum += x;
        }

        balance();
    }

    void remove(int x) {
        if (low.containsKey(x)) {
            removeMap(low, x);
            lowSize--;
            lowSum -= x;
        } else {
            removeMap(high, x);
            highSize--;
            highSum -= x;
        }

        balance();
    }

    long getCost() {
        long median = low.lastKey();

        return median * lowSize - lowSum
             + highSum - median * highSize;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Calculate cost of every window
        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {
            remove(nums[i - x]);
            add(nums[i]);

            cost[i - x + 1] = getCost();
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j <= k; j++) {

                if (dp[j][i] == INF) continue;

                // Skip current element
                dp[j][i + 1] = Math.min(
                    dp[j][i + 1],
                    dp[j][i]
                );

                // Select window of size x
                if (j < k && i + x <= n) {

                    dp[j + 1][i + x] = Math.min(
                        dp[j + 1][i + x],
                        dp[j][i] + cost[i]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
