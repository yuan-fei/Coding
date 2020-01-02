/*
 * @lc app=leetcode id=5297 lang=java
 *
 * [5297] Jump Game III
 *
 * https://leetcode.com/problems/jump-game-iii/description/
 *
 * algorithms
 * Medium (58.92%)
 * Likes:    38
 * Dislikes: 0
 * Total Accepted:    3.4K
 * Total Submissions: 5.8K
 * Testcase Example:  '[4,2,3,0,3,1,2]\n5'
 *
 * Given an array of non-negative integers arr, you are initially positioned at
 * start index of the array. When you are at index i, you can jump to i +
 * arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * 
 * Notice that you can not jump outside of the array at any time.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation: 
 * All possible ways to reach at index 3 with value 0 are: 
 * index 5 -> index 4 -> index 1 -> index 3 
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true 
 * Explanation: 
 * One possible way to reach at index 3 with value 0 is: 
 * index 0 -> index 4 -> index 1 -> index 3
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 * 
 */

// @lc code=start
class Solution {
	int[] arr;
	boolean[] visited;
    public boolean canReach(int[] arr, int start) {
    	int n = arr.length;
    	visited = new boolean[n];
        this.arr = arr;
        return dfs(start);
    }

    boolean dfs(int pos){
    	if(arr[pos] == 0){
    		return true;
    	}
    	visited[pos] = true;
    	int nextPos = pos + arr[pos];
    	if(nextPos < arr.length && !visited[nextPos] && dfs(nextPos)){
    		return true;
    	}
    	nextPos = pos - arr[pos];
    	if(nextPos >= 0 && !visited[nextPos] && dfs(nextPos)){
    		return true;
    	}
    	return false;
    }
}
// @lc code=end
