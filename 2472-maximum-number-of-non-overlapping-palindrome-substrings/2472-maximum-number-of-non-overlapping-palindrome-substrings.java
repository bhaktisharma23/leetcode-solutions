class Solution {
    public int maxPalindromes(String s, int k) {
        int ans=0;
        int i=0;
        while(i+k<=s.length()){
            if(isPalindrome(s,i,i+k-1)){
                ans++;
                i+=k;
            }else if(i+k+1<=s.length() && isPalindrome(s,i,i+k)){
                ans++;
                i+=k+1;
            }else{
                i++;
            }
        }
        return ans;
    }
    public boolean isPalindrome(String s,int l,int r){
        while(l<r){
            if(s.charAt(l)!=s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}