/*
 * @lc app=leetcode id=539 lang=java
 *
 * [539] Minimum Time Difference
 *
 * https://leetcode.com/problems/minimum-time-difference/description/
 *
 * algorithms
 * Medium (51.78%)
 * Likes:    538
 * Dislikes: 157
 * Total Accepted:    53.7K
 * Total Submissions: 103.7K
 * Testcase Example:  '["23:59","00:00"]'
 *
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the
 * minimum minutes difference between any two time points in the list. 
 * 
 * Example 1:
 * 
 * Input: ["23:59","00:00"]
 * Output: 1
 * 
 * 
 * 
 * Note:
 * 
 * The number of time points in the given list is at least 2 and won't exceed
 * 20000.
 * The input time is legal and ranges from 00:00 to 23:59.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findMinDifference(List<String> timePoints) {
    	
    	Collections.sort(timePoints);
    	int min = Integer.MAX_VALUE;
    	for(int i = 1; i <= timePoints.size(); i++){
			min = Math.min(min, getDiff(timePoints.get((i - 1) % timePoints.size()), timePoints.get(i % timePoints.size())));
    	}
        return min;
    }

    int getDiff(String s1, String s2){
    	String[] parts1 = s1.split(":");
    	String[] parts2 = s2.split(":");
    	// System.out.println(Arrays.toString(parts1));
    	int[] digits1 = new int[]{Integer.parseInt(parts1[0]), Integer.parseInt(parts1[1])};
    	int[] digits2 = new int[]{Integer.parseInt(parts2[0]), Integer.parseInt(parts2[1])};
    	if(s2.compareTo(s1) < 0){
    		digits2[0] += 24;
    	}
    	int diffH = digits2[0] - digits1[0];
    	int diffM = digits2[1] - digits1[1];
    	return diffH * 60 + diffM;
    }
}
// @lc code=end
