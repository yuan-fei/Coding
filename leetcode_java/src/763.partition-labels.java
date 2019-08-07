/*
 * @lc app=leetcode id=763 lang=java
 *
 * [763] Partition Labels
 *
 * https://leetcode.com/problems/partition-labels/description/
 *
 * algorithms
 * Medium (70.22%)
 * Total Accepted:    59.6K
 * Total Submissions: 83.3K
 * Testcase Example:  '"ababcbacadefegdehijhklij"'
 *
 * 
 * A string S of lowercase letters is given.  We want to partition this string
 * into as many parts as possible so that each letter appears in at most one
 * part, and return a list of integers representing the size of these parts.
 * 
 * 
 * Example 1:
 * 
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it
 * splits S into less parts.
 * 
 * 
 * 
 * Note:
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 * 
 */
class Solution {
    public List<Integer> partitionLabels(String S) {
    	int[] last = new int[26];
    	int n = S.length();
        for (int i = 0; i < n; i++) {
        	last[S.charAt(i)-'a'] = i;
        }
        int right = -1;
        int left = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
        	right = Math.max(last[S.charAt(i)-'a'], right);
        	if(right == i){
        		res.add(right - left + 1);
        		left = right+1;
        	}
        }
        return res;
    }
}
