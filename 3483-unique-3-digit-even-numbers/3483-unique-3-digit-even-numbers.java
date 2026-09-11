class Solution {
    public int totalNumbers(int[] digits) {
        int ans = 0;
        for (int num = 100; num <= 998; num += 2) {
            int n = num;
            int a = n % 10;
            n /= 10;
            int b = n % 10;
            n /= 10;
            int c = n;
            int[] count = new int[10];
            for (int d : digits) count[d]++;
            if (count[a] > 0) count[a]--;
            else continue;
            if (count[b] > 0) count[b]--;
            else continue;
            if (count[c] > 0) ans++;
        }
        return ans;
    }
}