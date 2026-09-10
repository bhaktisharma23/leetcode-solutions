class Solution {
    public boolean canJump(int[] nums) {
        int n=nums.length;
        int further=nums[0];
        for(int i=1;i<n;i++){
            if(i>further) return false;
            further= Math.max(further,i+nums[i]);
            if(further>=n-1) return true;
        }
        
        return true;
    }
}