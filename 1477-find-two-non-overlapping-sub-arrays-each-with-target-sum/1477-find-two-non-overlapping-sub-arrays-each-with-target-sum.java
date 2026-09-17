class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n=arr.length;
        int[] best=new int[n];
        Arrays.fill(best,Integer.MAX_VALUE);
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int sum=0;
        int ans=Integer.MAX_VALUE;
        int minLen=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            sum+=arr[i];
            if(map.containsKey(sum-target)){
                int j=map.get(sum-target);
                int len=i-j;
                if(j>=0 && best[j]!=Integer.MAX_VALUE)
                    ans=Math.min(ans,len+best[j]);
                minLen=Math.min(minLen,len);
            }
            if(i>0)
                best[i]=Math.min(best[i-1],minLen);
            else
                best[i]=minLen;

            map.put(sum,i);
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
}