class Solution {
    private static final long MOD = (1L << 61) - 1;

    private static long mul(long a, long b) {
        long lo = a * b;
        long hi = Math.multiplyHigh(a, b);
        long r = (lo & MOD) + ((lo >>> 61) | (hi << 3));
        r = (r & MOD) + (r >>> 61);
        if (r >= MOD) r -= MOD;
        return r;
    }

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long base = 1_000_003L + new Random().nextInt(1_000_000_000);

        long[] pw = new long[n + 1];
        long[] h = new long[n + 1];
        pw[0] = 1;
        for (int i = 0; i < n; i++) {
            pw[i + 1] = mul(pw[i], base);
            long v = mul(h[i], base) + nums[i];
            if (v >= MOD) v -= MOD;
            h[i + 1] = v;
        }

        int lo = 1, hi = n;      
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (hasUnique(h, pw, n, mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private boolean hasUnique(long[] h, long[] pw, int n, int len) {
        int m = n - len + 1;
        long[] hashes = new long[m];
        for (int i = 0; i < m; i++) {
            long v = h[i + len] - mul(h[i], pw[len]);
            if (v < 0) v += MOD;
            hashes[i] = v;
        }
        Arrays.sort(hashes);
        for (int i = 0; i < m; i++) {
            boolean sameAsPrev = i > 0 && hashes[i] == hashes[i - 1];
            boolean sameAsNext = i + 1 < m && hashes[i] == hashes[i + 1];
            if (!sameAsPrev && !sameAsNext) return true;
        }
        return false;
    }
}