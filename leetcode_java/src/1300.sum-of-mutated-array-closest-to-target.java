/*
 * @lc app=leetcode id=1300 lang=java
 *
 * [1300] Sum of Mutated Array Closest to Target
 *
 * https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/description/
 *
 * algorithms
 * Medium (41.52%)
 * Likes:    33
 * Dislikes: 4
 * Total Accepted:    1.7K
 * Total Submissions: 4.1K
 * Testcase Example:  '[4,9,3]\n10'
 *
 * Given an integer array arr and a target value target, return the integer
 * value such that when we change all the integers larger than value in the
 * given array to be equal to value, the sum of the array gets as close as
 * possible (in absolute difference) to target.
 * 
 * In case of a tie, return the minimum such integer.
 * 
 * Notice that the answer is not neccesarilly a number from arr.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [4,9,3], target = 10
 * Output: 3
 * Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's
 * the optimal answer.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [2,3,5], target = 10
 * Output: 5
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [60864,25176,27249,21296,20204], target = 56803
 * Output: 11361
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i], target <= 10^5
 * 
 */

// @lc code=start
class Solution {
    public int findBestValue(int[] arr, int target) {
        int low = 0;
        int high = 100000;
        int minDiff = Integer.MAX_VALUE;
        int minBar = -1;
        while(low + 1 < high){
        	int mid = (low + high) / 2;
        	int midDiff = diff(arr, target, mid);
        	if(minDiff > Math.abs(midDiff)){
        		minDiff = Math.abs(midDiff);
        		minBar = mid;
        	}
        	else if(minDiff == Math.abs(midDiff)){
        		minBar = Math.min(mid, minBar);	
        	}
        	if(midDiff >= 0){
        		high = mid;
        	}
        	else {
        		low = mid;
        	}
        	// System.out.println(low +", " +high+", " + mid + ", "+ midDiff + "," + minDiff + ", "+ minBar);
        }
        int lowDiff = diff(arr, target, low);
        int highDiff = diff(arr, target, high);
        if(minDiff > Math.abs(lowDiff)){
    		minDiff = Math.abs(lowDiff);
    		minBar = low;
    	}
    	if(minDiff > Math.abs(highDiff)){
    		minDiff = Math.abs(highDiff);
    		minBar = high;
    	}
    	return minBar;
    }

    int diff(int[] arr, int target, int bar){
    	int sum = 0;
    	for (int n : arr) {
    		sum += Math.min(n, bar);
    	}
    	return sum - target;
    }
}
// @lc code=end
