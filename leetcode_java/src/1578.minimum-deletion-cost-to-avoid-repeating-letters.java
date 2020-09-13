/*
 * @lc app=leetcode id=1578 lang=java
 *
 * [1578] Minimum Deletion Cost to Avoid Repeating Letters
 *
 * https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/description/
 *
 * algorithms
 * Medium (57.77%)
 * Likes:    83
 * Dislikes: 2
 * Total Accepted:    6.1K
 * Total Submissions: 10.6K
 * Testcase Example:  '"abaac"\n[1,2,3,4,5]'
 *
 * Given a string s and an array of integers cost where cost[i] is the cost of
 * deleting the character i in s.
 * 
 * Return the minimum cost of deletions such that there are no two identical
 * letters next to each other.
 * 
 * Notice that you will delete the chosen characters at the same time, in other
 * words, after deleting a character, the costs of deleting other characters
 * will not change.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abaac", cost = [1,2,3,4,5]
 * Output: 3
 * Explanation: Delete the letter "a" with cost 3 to get "abac" (String without
 * two identical letters next to each other).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abc", cost = [1,2,3]
 * Output: 0
 * Explanation: You don't need to delete any character because there are no
 * identical letters next to each other.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "aabaa", cost = [1,2,3,4,1]
 * Output: 2
 * Explanation: Delete the first and the last character, getting the string
 * ("aba").
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * s.length == cost.length
 * 1 <= s.length, cost.length <= 10^5
 * 1 <= cost[i] <= 10^4
 * s contains only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minCost(String s, int[] cost) {
    	int n = s.length();
    	if(n <= 1){
    		return 0;
    	}
        int[] prefSum  = new int[n + 1];
        int left = 0;
        int max = cost[0];
        int total = 0;
        prefSum[1] = cost[0];
        for(int i = 1; i < n; i++){
        	prefSum[i + 1] = prefSum[i] + cost[i];
        	if(s.charAt(i) != s.charAt(i - 1)){
        		total += prefSum[i] - prefSum[left] - max;
        		max = cost[i];
        		left = i;
        	}
        	max = Math.max(max, cost[i]);
        	// System.out.println(total);
        }
        total += prefSum[n] - prefSum[left] - max;
        return total;
    }
}
// @lc code=end
