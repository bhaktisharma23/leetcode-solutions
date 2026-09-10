/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int ans=0;
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }
    public int[] dfs(TreeNode node){
      if(node==null){
          return new int[]{0,0};
        }
        int[] l=dfs(node.left);
        int[] r = dfs(node.right);
        int sum = l[0] + r[0] + node.val;
        int count = l[1] + r[1] + 1;

        if(node.val==sum/count){
          ans++;
        }
        return new int[]{sum,count};
    }
}