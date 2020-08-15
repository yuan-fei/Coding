/*
 * @lc app=leetcode id=506 lang=java
 *
 * [506] Relative Ranks
 *
 * https://leetcode.com/problems/relative-ranks/description/
 *
 * algorithms
 * Easy (50.50%)
 * Likes:    261
 * Dislikes: 542
 * Total Accepted:    55.3K
 * Total Submissions: 109.5K
 * Testcase Example:  '[5,4,3,2,1]'
 *
 * 
 * Given scores of N athletes, find their relative ranks and the people with
 * the top three highest scores, who will be awarded medals: "Gold Medal",
 * "Silver Medal" and "Bronze Medal".
 * 
 * Example 1:
 * 
 * Input: [5, 4, 3, 2, 1]
 * Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * Explanation: The first three athletes got the top three highest scores, so
 * they got "Gold Medal", "Silver Medal" and "Bronze Medal". For the left two
 * athletes, you just need to output their relative ranks according to their
 * scores.
 * 
 * 
 * 
 * Note:
 * 
 * N is a positive integer and won't exceed 10,000.
 * All the scores of athletes are guaranteed to be unique.
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public String[] findRelativeRanks(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
        	m.put(nums[i], i);
        }
        Arrays.sort(nums);
        String[] ret = new String[nums.length];
        if(nums.length > 0){
        	ret[m.get(nums[nums.length - 1])] = "Gold Medal";
        }
        if(nums.length > 1){
        	ret[m.get(nums[nums.length - 2])] = "Silver Medal";
        }
        if(nums.length > 2){
        	ret[m.get(nums[nums.length - 3])] = "Bronze Medal";
        }
        for(int i = 3; i < nums.length && nums.length - i >= 1; i++){
        	ret[m.get(nums[nums.length - i - 1])] = "" + (i + 1);
        }
        return ret;
    }
}
// @lc code=end
