class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        int r = n - 1;

        Deque<Integer> dq = new ArrayDeque<>();

        for (int l = n - 1; l >= 0; l--) {
            // Add nums[l] on the left: it raises the ceiling of smaller blocks.
            while (!dq.isEmpty() && nums[dq.peekFirst()] <= nums[l]) {
                int j = dq.pollFirst();
                int next = dq.isEmpty() ? r + 1 : dq.peekFirst();
                cost += (long) (nums[l] - nums[j]) * (next - j);
            }
            dq.addFirst(l);

            // Shrink the window from the right until cost <= k.
            while (cost > k) {
                cost -= nums[dq.peekLast()] - nums[r];
                if (dq.peekLast() == r) {
                    dq.pollLast();
                }
                r--;
            }

            ans += r - l + 1;
        }
        return ans;
    }
}