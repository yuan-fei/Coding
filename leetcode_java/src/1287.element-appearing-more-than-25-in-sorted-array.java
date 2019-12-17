/*
 * @lc app=leetcode id=1287 lang=java
 *
 * [1287] Element Appearing More Than 25% In Sorted Array
 *
 * https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/description/
 *
 * algorithms
 * Easy (59.51%)
 * Likes:    16
 * Dislikes: 2
 * Total Accepted:    1.9K
 * Total Submissions: 3.3K
 * Testcase Example:  '[1,2,2,6,6,6,6,7,10]'
 *
 * Given an integer array sorted in non-decreasing order, there is exactly one
 * integer in the array that occurs more than 25% of the time.
 * 
 * Return that integer.
 * 
 * 
 * Example 1:
 * Input: arr = [1,2,2,6,6,6,6,7,10]
 * Output: 6
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 * 
 */

// @lc code=start
class Solution {
    public int findSpecialInteger(int[] arr) {
    	int n = arr.length;
        Random r = new Random();
        boolean found = false;
        while(!found){
			int i = r.nextInt(n);
			if(check(arr, i)){
				return arr[i];
			}
        }
        return -1;
    }

    boolean check(int[] arr, int id){
    	int cnt = 0;
    	for(int a: arr){
    		if(a == arr[id]){
    			cnt++;
    		}
    	}
    	return cnt > arr.length / 4;
    }
}
// @lc code=end
