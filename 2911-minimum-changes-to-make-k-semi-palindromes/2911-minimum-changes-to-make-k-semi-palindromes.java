import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        final int INF = Integer.MAX_VALUE / 2;
        char[] c = s.toCharArray();

        List<Integer>[] divs = new List[n + 1];
        for (int i = 0; i <= n; i++) divs[i] = new ArrayList<>();
        for (int d = 1; d <= n; d++) {
            for (int L = 2 * d; L <= n; L += d) {
                divs[L].add(d);
            }
        }

        int[][] cost = new int[n][n];
        for (int[] row : cost) Arrays.fill(row, INF);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int L = j - i + 1;
                int best = INF;
                for (int d : divs[L]) {
                    int changes = 0;
                    for (int r = 0; r < d; r++) {
                        int lo = i + r;
                        int hi = j - d + 1 + r;
                        while (lo < hi) {
                            if (c[lo] != c[hi]) changes++;
                            lo += d;
                            hi -= d;
                        }
                    }
                    best = Math.min(best, changes);
                }
                cost[i][j] = best;
            }
        }

        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int j = 2 * p; j <= n; j++) {
                int m = INF;
                for (int i = 2 * (p - 1); i < j - 1; i++) {
                    if (dp[p - 1][i] >= INF || cost[i][j - 1] >= INF) continue;
                    m = Math.min(m, dp[p - 1][i] + cost[i][j - 1]);
                }
                dp[p][j] = m;
            }
        }
        return dp[k][n];
    }
}