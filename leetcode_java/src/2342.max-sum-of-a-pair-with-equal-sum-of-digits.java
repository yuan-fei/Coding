/*
 * @lc app=leetcode id=2342 lang=java
 *
 * [2342] Max Sum of a Pair With Equal Sum of Digits
 *
 * https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/description/
 *
 * algorithms
 * Medium (52.36%)
 * Likes:    234
 * Dislikes: 2
 * Total Accepted:    19K
 * Total Submissions: 36.2K
 * Testcase Example:  '[18,43,36,13,7]'
 *
 * You are given a 0-indexed array nums consisting of positive integers. You
 * can choose two indices i and j, such that i != j, and the sum of digits of
 * the number nums[i] is equal to that of nums[j].
 * 
 * Return the maximum value of nums[i] + nums[j] that you can obtain over all
 * possible indices i and j that satisfy the conditions.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [18,43,36,13,7]
 * Output: 54
 * Explanation: The pairs (i, j) that satisfy the conditions are:
 * - (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18
 * + 36 = 54.
 * - (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43
 * + 7 = 50.
 * So the maximum sum that we can obtain is 54.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [10,12,19,14]
 * Output: -1
 * Explanation: There are no two numbers that satisfy the conditions, so we
 * return -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
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
    public int maximumSum(int[] nums) {
        Map<Integer, List<Integer>> m = new HashMap<>();
        for(int x : nums){
            int s = getDigitSum(x);
            if(!m.containsKey(s)){
                m.put(s, new ArrayList<>());
            }
            m.get(s).add(x);
        }
        int max = -1;
        for(List<Integer> l : m.values()){
            if(l.size() >= 2){
                Collections.sort(l, (a, b) -> Integer.compare(b, a));
                max = Math.max(max, l.get(0) + l.get(1));
            }
        }
        return max;
    }

    private int getDigitSum(int x){
        int ret = 0;
        while(x != 0){
            ret += x % 10;
            x /= 10;
        }
        return ret;
    }
}
// @lc code=end
