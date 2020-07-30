/*
 * @lc app=leetcode id=1526 lang=java
 *
 * [1526] Minimum Number of Increments on Subarrays to Form a Target Array
 *
 * https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/description/
 *
 * algorithms
 * Hard (45.92%)
 * Likes:    53
 * Dislikes: 1
 * Total Accepted:    1.9K
 * Total Submissions: 4.2K
 * Testcase Example:  '[1,2,3,2,1]'
 *
 * Given an array of positive integers target and an array initial of same size
 * with all zeros.
 * 
 * Return the minimum number of operations to form a target array from initial
 * if you are allowed to do the following operation:
 * 
 * 
 * Choose any subarray from initial and increment each value by one.
 * 
 * The answer is guaranteed to fit within the range of a 32-bit signed
 * integer.
 * 
 * Example 1:
 * 
 * 
 * Input: target = [1,2,3,2,1]
 * Output: 3
 * Explanation: We need at least 3 operations to form the target array from the
 * initial array.
 * [0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
 * [1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
 * [1,2,2,2,1] increment 1 at index 2.
 * [1,2,3,2,1] target array is formed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: target = [3,1,1,2]
 * Output: 4
 * Explanation: (initial)[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] ->
 * [3,1,1,2] (target).
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: target = [3,1,5,4,2]
 * Output: 7
 * Explanation: (initial)[0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] ->
 * [3,1,1,1,1] 
 * ⁠                                 -> [3,1,2,2,2] -> [3,1,3,3,2] ->
 * [3,1,4,4,2] -> [3,1,5,4,2] (target).
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: target = [1,1,1,1]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= target.length <= 10^5
 * 1 <= target[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minNumberOperations(int[] target) {
        int bottom = 0;
        int total = 0;
        int lastSign = 1;
        int n = target.length;
        for (int i = 0; i < n - 1; i++) {
        	int sign = getSign(target[i], target[i + 1]);
        	if(sign * lastSign == -1){
        		if(sign == -1){
        			total += target[i] - bottom;
        		}
        		else{
        			bottom = target[i];
        		}
        		lastSign = sign;
        	}
        }
        if(lastSign == 1){
        	total += target[n - 1] - bottom;
        }
        return total;
    }

    int getSign(int a, int b){
    	if(a < b){
    		return 1;
    	}
    	if(a == b){
    		return 0;
    	}
    	return -1;
    }
}
// @lc code=end
