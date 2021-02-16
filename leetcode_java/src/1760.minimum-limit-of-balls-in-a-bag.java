/*
 * @lc app=leetcode id=1760 lang=java
 *
 * [1760] Minimum Limit of Balls in a Bag
 *
 * https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/description/
 *
 * algorithms
 * Medium (49.80%)
 * Likes:    207
 * Dislikes: 14
 * Total Accepted:    4.5K
 * Total Submissions: 8.9K
 * Testcase Example:  '[9]\n2'
 *
 * You are given an integer array nums where the i^th bag contains nums[i]
 * balls. You are also given an integer maxOperations.
 * 
 * You can perform the following operation at most maxOperations times:
 * 
 * 
 * Take any bag of balls and divide it into two new bags with a positive number
 * of balls.
 * 
 * 
 * For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or
 * two new bags of 2 and 3 balls.
 * 
 * 
 * 
 * 
 * Your penalty is the maximum number of balls in a bag. You want to minimize
 * your penalty after the operations.
 * 
 * Return the minimum possible penalty after performing the operations.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [9], maxOperations = 2
 * Output: 3
 * Explanation: 
 * - Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
 * - Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] ->
 * [3,3,3].
 * The bag with the most number of balls has 3 balls, so your penalty is 3 and
 * you should return 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,4,8,2], maxOperations = 4
 * Output: 2
 * Explanation:
 * - Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] ->
 * [2,4,4,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] ->
 * [2,2,2,4,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2]
 * -> [2,2,2,2,2,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2.
 * [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
 * The bag with the most number of balls has 2 balls, so your penalty is 2 an
 * you should return 2.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [7,17], maxOperations = 2
 * Output: 7
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= maxOperations, nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int low = 1;
        int high = 1000000000;
        while(low + 1 < high){
        	int mid = (low + high) / 2;
        	if(isfeasible(nums, maxOperations, mid)){
        		high = mid;
        	}
        	else{
        		low = mid;
        	}
        }
        if(isfeasible(nums, maxOperations, low)){
        	return low;
        }
        else{
        	return high;
        }
    }

    boolean isfeasible(int[] nums, int maxOperations, int target){
    	int cnt = 0;
    	for(int x : nums){
    		cnt += (x - 1) / target;
    	}
    	return cnt <= maxOperations;
    }
}
// @lc code=end
