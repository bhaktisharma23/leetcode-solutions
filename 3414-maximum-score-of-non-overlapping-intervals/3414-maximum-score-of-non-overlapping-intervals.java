class Solution {
    static class State {
        long weight;
        int[] indices;
        State(long weight,int[] indices) {
            this.weight=weight;
            this.indices=indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n=intervals.size();
        List<List<Integer>> a=new ArrayList<>();

        for(int i=0;i<n;i++)
            a.add(Arrays.asList(intervals.get(i).get(0),intervals.get(i).get(1),intervals.get(i).get(2),i));

        a.sort((x,y)->Integer.compare(x.get(0),y.get(0)));

        State[][] dp=new State[n+1][5];

        for(int k=0;k<=4;k++)
            dp[n][k]=new State(0,new int[0]);

        for(int i=n-1;i>=0;i--) {
            int start=a.get(i).get(0);
            int end=a.get(i).get(1);
            int index=a.get(i).get(3);
            long weight=a.get(i).get(2);

            int next=findNext(a,end);

            for(int k=0;k<=4;k++) {
                State skip=dp[i+1][k];
                State best=skip;

                if(k>0) {
                    State temp=dp[next][k-1];
                    int[] arr=new int[temp.indices.length+1];
                    arr[0]=index;
                    for(int j=0;j<temp.indices.length;j++)
                        arr[j+1]=temp.indices[j];
                    Arrays.sort(arr);

                    State take=new State(weight+temp.weight,arr);

                    if(take.weight>best.weight||(take.weight==best.weight&&compare(take.indices,best.indices)<0))
                        best=take;
                }

                dp[i][k]=best;
            }
        }

        return dp[0][4].indices;
    }

    private int findNext(List<List<Integer>> a,int end) {
        int l=0,r=a.size();

        while(l<r) {
            int mid=(l+r)/2;

            if(a.get(mid).get(0)>end)
                r=mid;
            else
                l=mid+1;
        }

        return l;
    }

    private int compare(int[] a,int[] b) {
        for(int i=0;i<Math.min(a.length,b.length);i++) {
            if(a[i]!=b[i])
                return Integer.compare(a[i],b[i]);
        }
        return Integer.compare(a.length,b.length);
    }
}