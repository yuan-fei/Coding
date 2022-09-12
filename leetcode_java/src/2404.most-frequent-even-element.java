/*
 * @lc app=leetcode id=2404 lang=java
 *
 * [2404] Most Frequent Even Element
 *
 * https://leetcode.com/problems/most-frequent-even-element/description/
 *
 * algorithms
 * Easy (51.43%)
 * Likes:    111
 * Dislikes: 3
 * Total Accepted:    17.4K
 * Total Submissions: 33.8K
 * Testcase Example:  '[0,1,2,2,4,4,1]'
 *
 * Given an integer array nums, return the most frequent even element.
 * 
 * If there is a tie, return the smallest one. If there is no such element,
 * return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [0,1,2,2,4,4,1]
 * Output: 2
 * Explanation:
 * The even elements are 0, 2, and 4. Of these, 2 and 4 appear the most.
 * We return the smallest one, which is 2.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,4,4,9,2,4]
 * Output: 4
 * Explanation: 4 is the even element appears the most.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [29,47,21,41,13,37,25,7]
 * Output: -1
 * Explanation: There is no even element.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2000
 * 0 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int mostFrequentEven(int[] nums) {
        int[] cnt = new int[2001];
        for(int x : nums){
            cnt[x]++;
        }
        int max = 0;
        int ans = -1;
        for(int i = 0; i < 2001; i += 2){
            if(cnt[i] > max){
                max = cnt[i];
                ans = i;
            }
        }
        return ans;
    }
}
// @lc code=end
