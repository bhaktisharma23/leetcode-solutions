import java.math.BigInteger;
class Solution {
    public int numberOfSets(int n, int k) {
        
        int a=n+k-1;
        int b=2*k;
        BigInteger ans = BigInteger.ONE;
        for(int i=1;i<=b;i++){
            ans = ans.multiply(BigInteger.valueOf(a - b + i)).divide(BigInteger.valueOf(i));
        }
        return ans.mod(BigInteger.valueOf(1000000007)).intValue();
    }
}