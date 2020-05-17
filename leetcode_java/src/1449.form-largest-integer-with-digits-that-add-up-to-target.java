/*
 * @lc app=leetcode id=1449 lang=java
 *
 * [1449] Form Largest Integer With Digits That Add up to Target
 *
 * https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target/description/
 *
 * algorithms
 * Hard (33.70%)
 * Likes:    110
 * Dislikes: 2
 * Total Accepted:    2.7K
 * Total Submissions: 7.9K
 * Testcase Example:  '[4,3,2,5,6,7,2,5,5]\n9'
 *
 * Given an array of integers cost and an integer target. Return the maximum
 * integer you can paint under the following rules:
 * 
 * 
 * The cost of painting a digit (i+1) is given by cost[i] (0 indexed).
 * The total cost used must be equal to target.
 * Integer does not have digits 0.
 * 
 * 
 * Since the answer may be too large, return it as string.
 * 
 * If there is no way to paint any integer given the condition, return "0".
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: cost = [4,3,2,5,6,7,2,5,5], target = 9
 * Output: "7772"
 * Explanation:  The cost to paint the digit '7' is 2, and the digit '2' is 3.
 * Then cost("7772") = 2*3+ 3*1 = 9. You could also paint "997", but "7772" is
 * the largest number.
 * Digit    cost
 * ⁠ 1  ->   4
 * ⁠ 2  ->   3
 * ⁠ 3  ->   2
 * ⁠ 4  ->   5
 * ⁠ 5  ->   6
 * ⁠ 6  ->   7
 * ⁠ 7  ->   2
 * ⁠ 8  ->   5
 * ⁠ 9  ->   5
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: cost = [7,6,5,5,5,6,8,7,8], target = 12
 * Output: "85"
 * Explanation: The cost to paint the digit '8' is 7, and the digit '5' is 5.
 * Then cost("85") = 7 + 5 = 12.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: cost = [2,4,6,2,4,6,4,4,4], target = 5
 * Output: "0"
 * Explanation: It's not possible to paint any integer with total cost equal to
 * target.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: cost = [6,10,15,40,40,40,40,40,40], target = 47
 * Output: "32211"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * cost.length == 9
 * 1 <= cost[i] <= 5000
 * 1 <= target <= 5000
 * 
 * 
 */

// @lc code=start
class Solution {
    public String largestNumber(int[] cost, int target) {
        int[][] dp = new int[target + 1][9];
        int[] cnt = new int[target + 1];
        for(int i = 1; i <= target; i++){
        	for(int j = 0; j < 9; j++){
        		if(i == cost[j] || (i > cost[j] && cnt[i - cost[j]] != 0)){
        			int[] nums = Arrays.copyOf(dp[i - cost[j]], 9);
        			nums[j]++;
        			if(cnt[i - cost[j]] + 1 > cnt[i] || (cnt[i - cost[j]] + 1 == cnt[i] && greater(dp[i], nums) == nums)){
        				dp[i] = nums;
        				cnt[i] = cnt[i - cost[j]] + 1;
        			}
        		}
        	}
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 8; i >= 0; i--){
        	while(dp[target][i] > 0){
        		sb.append(i + 1);
        		dp[target][i]--;
        	}
        }
        if(sb.length() == 0){
        	return "0";
        }
        return sb.toString();
    }

    int[] greater(int[] n1, int[] n2){
    	for(int i = 8; i >= 0; i--){
    		if(n1[i] > n2[i]){
    			return n1;
    		}
    		if(n1[i] < n2[i]){
    			return n2;
    		}
    	}
    	return n1;
    }
}
// @lc code=end
