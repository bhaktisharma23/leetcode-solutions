
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;

        int[] count = new int[k];

        // Available servers
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Busy servers: {finishTime, serverIndex}
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        for (int i = 0; i < n; i++) {

            // Free all servers whose tasks are completed
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            // Preferred server = i % k
            int preferred = i % k;

            // Find smallest available server >= preferred
            Integer server = available.ceiling(preferred);

            // If none, wrap around to smallest available
            if (server == null) {
                server = available.first();
            }

            // Assign request
            available.remove(server);
            count[server]++;

            // Mark server busy
            busy.offer(new int[]{arrival[i] + load[i], server});
        }

        // Find maximum requests handled
        int max = 0;
        for (int c : count) {
            max = Math.max(max, c);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
