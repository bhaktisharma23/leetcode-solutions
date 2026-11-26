class Solution {
    public boolean canAliceWin(int n) {
        boolean aliceTurn = true;
        int take = 10;

        while (take > 0 && n >= take){
            n -= take;
            take--;
            aliceTurn = !aliceTurn;
        }
        return !aliceTurn;
    }
}