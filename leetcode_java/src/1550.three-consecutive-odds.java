/*
 * @lc app=leetcode id=1550 lang=java
 *
 * [1550] Three Consecutive Odds
 *
 * https://leetcode.com/problems/three-consecutive-odds/description/
 *
 * algorithms
 * Easy (68.03%)
 * Likes:    62
 * Dislikes: 8
 * Total Accepted:    12.8K
 * Total Submissions: 18.7K
 * Testcase Example:  '[2,6,4,1]'
 *
 * Given an integer array arr, return true if there are three consecutive odd
 * numbers in the array. Otherwise, return false.
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [2,6,4,1]
 * Output: false
 * Explanation: There are no three consecutive odds.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,2,34,3,4,5,7,23,12]
 * Output: true
 * Explanation: [5,7,23] are three consecutive odds.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
    	int cnt = 0;
       	for(int x : arr){
       		if(x % 2 == 1){
       			cnt++;
       			if(cnt == 3){
       				return true;
       			}
       		}
       		else{
       			cnt = 0;
       		}
       	}
       	return false;
    }
}
// @lc code=end
