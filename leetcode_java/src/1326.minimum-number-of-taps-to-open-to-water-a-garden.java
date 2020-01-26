/*
 * @lc app=leetcode id=1326 lang=java
 *
 * [1326] Minimum Number of Taps to Open to Water a Garden
 *
 * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/description/
 *
 * algorithms
 * Hard (39.81%)
 * Likes:    99
 * Dislikes: 33
 * Total Accepted:    4.3K
 * Total Submissions: 10.7K
 * Testcase Example:  '5\n[3,4,1,1,0,0]'
 *
 * There is a one-dimensional garden on the x-axis. The garden starts at the
 * point 0 and ends at the point n. (i.e The length of the garden is n).
 * 
 * There are n + 1 taps located at points [0, 1, ..., n] in the garden.
 * 
 * Given an integer n and an integer array ranges of length n + 1 where
 * ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i],
 * i + ranges[i]] if it was open.
 * 
 * Return the minimum number of taps that should be open to water the whole
 * garden, If the garden cannot be watered return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5, ranges = [3,4,1,1,0,0]
 * Output: 1
 * Explanation: The tap at point 0 can cover the interval [-3,3]
 * The tap at point 1 can cover the interval [-3,5]
 * The tap at point 2 can cover the interval [1,3]
 * The tap at point 3 can cover the interval [2,4]
 * The tap at point 4 can cover the interval [4,4]
 * The tap at point 5 can cover the interval [5,5]
 * Opening Only the second tap will water the whole garden [0,5]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, ranges = [0,0,0,0]
 * Output: -1
 * Explanation: Even if you activate all the four taps you cannot water the
 * whole garden.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 7, ranges = [1,2,1,0,2,1,0,1]
 * Output: 3
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: n = 8, ranges = [4,0,0,0,0,0,0,0,4]
 * Output: 2
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: n = 8, ranges = [4,0,0,0,4,0,0,0,4]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^4
 * ranges.length == n + 1
 * 0 <= ranges[i] <= 100
 * 
 */

// @lc code=start
class Solution {
    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[n + 1][2];
        for(int i = 0; i <= n; i++){
        	intervals[i][0] = Math.max(0, i - ranges[i]);
        	intervals[i][1] = Math.min(n, i + ranges[i]);
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int right = 0;
        int maxRight = 0;
        int i = 0;
        int cnt = 0;
        while(i <= n){
        	// System.out.println(Arrays.toString(new int[]{right, maxRight, intervals[i][0], intervals[i][1]}));
        	if(intervals[i][0] <= right){
        		maxRight = Math.max(maxRight, intervals[i][1]);
        		i++;
        	}
        	else if(intervals[i][0] <= maxRight){
        		cnt++;
        		right = maxRight;
        	}
        	else{
        		return -1;
        	}
        }
        if(right != n){
        	cnt++;
        }
        return cnt;
    }
}
// @lc code=end
