/*
 * @lc app=leetcode id=1787 lang=java
 *
 * [1787] Make the XOR of All Segments Equal to Zero
 *
 * https://leetcode.com/problems/make-the-xor-of-all-segments-equal-to-zero/description/
 *
 * algorithms
 * Hard (32.85%)
 * Likes:    88
 * Dislikes: 5
 * Total Accepted:    1.3K
 * Total Submissions: 3.9K
 * Testcase Example:  '[1,2,0,3,0]\n1'
 *
 * You are given an array nums​​​ and an integer k​​​​​. The XOR of a segment
 * [left, right] where left <= right is the XOR of all the elements with
 * indices between left and right, inclusive: nums[left] XOR nums[left+1] XOR
 * ... XOR nums[right].
 * 
 * Return the minimum number of elements to change in the array such that the
 * XOR of all segments of size k​​​​​​ is equal to zero.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,0,3,0], k = 1
 * Output: 3
 * Explanation: Modify the array from [1,2,0,3,0] to from [0,0,0,0,0].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,4,5,2,1,7,3,4,7], k = 3
 * Output: 3
 * Explanation: Modify the array from [3,4,5,2,1,7,3,4,7] to
 * [3,4,7,3,4,7,3,4,7].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,4,1,2,5,1,2,6], k = 3
 * Output: 3
 * Explanation: Modify the array from [1,2,4,1,2,5,1,2,6] to
 * [1,2,3,1,2,3,1,2,3].
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= nums.length <= 2000
 * ​​​​​​0 <= nums[i] < 2^10
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minChanges(int[] nums, int k) {
       // Basically to find a repeat 123 123 123 and xor sum =0
        int n = nums.length;
        int maxNum = 1024;
        int[][] count = new int[k][maxNum];
        int[] maxFreq = new int[k];
        List<Set<Integer>> numbers = new ArrayList<>();
        for(int i=0;i<k;i++) numbers.add(new HashSet<>());
        // Step1, construct count matrix for each layer
        for(int i=0;i<n;i++){
            count[i%k][nums[i]]++;
            maxFreq[i%k] = Math.max(maxFreq[i%k],count[i%k][nums[i]]);
            numbers.get(i%k).add(nums[i]);
        }
        // Step2, if we only choose k-1 from the current num list
        int minMaxFreq = Integer.MAX_VALUE;
        int sumMaxFreq = 0;
        for(int i=0;i<k;i++){
            sumMaxFreq+=maxFreq[i];
            minMaxFreq = Math.min(minMaxFreq,maxFreq[i]);
        }
        
        int ans = n-(sumMaxFreq-minMaxFreq);
        
        // Step3, if we choose all number from current list
        int[][] dp = new int[k][1024];
        
        for(int i = 0;i <k;i++){
            for(int j = 0;j < 1024;j++){
                if(i == 0) dp[i][j] = count[i][j];
                else{
                    for(int it:numbers.get(i)){
                        dp[i][j^it] = Math.max(dp[i][j^it] , (dp[i-1][j] + count[i][it]));
                    }
                }
            }
        }
    
        return Math.min(ans,n-dp[k-1][0]);
    }
}
// @lc code=end
