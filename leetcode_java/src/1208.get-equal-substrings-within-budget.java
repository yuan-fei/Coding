/*
 * @lc app=leetcode id=1208 lang=java
 *
 * [1208] Get Equal Substrings Within Budget
 *
 * https://leetcode.com/problems/get-equal-substrings-within-budget/description/
 *
 * algorithms
 * Medium (33.06%)
 * Total Accepted:    4.6K
 * Total Submissions: 13.9K
 * Testcase Example:  '"abcd"\n"bcdf"\n3'
 *
 * You are given two strings s and t of the same length. You want to change s
 * to t. Changing the i-th character of s to i-th character of t costs |s[i] -
 * t[i]| that is, the absolute difference between the ASCII values of the
 * characters.
 * 
 * You are also given an integer maxCost.
 * 
 * Return the maximum length of a substring of s that can be changed to be the
 * same as the corresponding substring of twith a cost less than or equal to
 * maxCost.
 * 
 * If there is no substring from s that can be changed to its corresponding
 * substring from t, return 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abcd", t = "bcdf", maxCost = 3
 * Output: 3
 * Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum
 * length is 3.
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abcd", t = "cdef", maxCost = 3
 * Output: 1
 * Explanation: Each character in s costs 2 to change to charactor in t, so the
 * maximum length is 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "abcd", t = "acde", maxCost = 0
 * Output: 1
 * Explanation: You can't make any change, so the maximum length is 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length, t.length <= 10^5
 * 0 <= maxCost <= 10^6
 * s and t only contain lower case English letters.
 * 
 * 
 */
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
    	int N = s.length();
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int sum = 0;
        int maxLen = 0;
        tm.put(sum, 0);
        for (int i = 0; i < N; i++) {
        	sum += Math.abs(s.charAt(i) - t.charAt(i));
        	if(!tm.containsKey(sum)){
				tm.put(sum, i + 1);
        	}
        	Map.Entry<Integer, Integer> e = tm.ceilingEntry(sum - maxCost);
        	if(e != null){
        		maxLen = Math.max(i + 1 - e.getValue(), maxLen);
        	}
        }
        // System.out.println(tm);
        return maxLen;
    }
}
