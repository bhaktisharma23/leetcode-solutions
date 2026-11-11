
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        long both = (long) Math.min(need1, need2) * costBoth;

        long remaining1 = (long) Math.max(0, need1 - need2);
        long remaining2 = (long) Math.max(0, need2 - need1);

        long cost = both
                  + remaining1 * Math.min(cost1, costBoth)
                  + remaining2 * Math.min(cost2, costBoth);

        return Math.min(cost,
                (long) need1 * cost1 + (long) need2 * cost2);
    }
}
