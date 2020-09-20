/*
 * @lc app=leetcode id=1593 lang=java
 *
 * [1593] Split a String Into the Max Number of Unique Substrings
 *
 * https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/description/
 *
 * algorithms
 * Medium (37.64%)
 * Likes:    57
 * Dislikes: 5
 * Total Accepted:    3.8K
 * Total Submissions: 10.2K
 * Testcase Example:  '"ababccc"'
 *
 * Given a string s, return the maximum number of unique substrings that the
 * given string can be split into.
 * 
 * You can split string s into any list of non-empty substrings, where the
 * concatenation of the substrings forms the original string. However, you must
 * split the substrings such that all of them are unique.
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc'].
 * Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a'
 * and 'b' multiple times.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "aa"
 * Output: 1
 * Explanation: It is impossible to split the string any further.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 
 * 1 <= s.length <= 16
 * 
 * 
 * s contains only lower case English letters.
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxUniqueSplit(String s) {
    	dfs(s, new HashSet<>(), 0);
    	return max;    
    }

    int max = 0;
    void dfs(String s, Set<String> set, int pos){
    	if(pos >= s.length()){
    		max = Math.max(max, set.size());
    	}
    	for(int i = pos + 1; i <= s.length(); i++){
    		if(!set.contains(s.substring(pos, i))){
    			set.add(s.substring(pos, i));
    			dfs(s, set, i);
    			set.remove(s.substring(pos, i));
    		}
    	}
    }
}
// @lc code=end
