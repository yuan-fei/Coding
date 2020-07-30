/*
 * @lc app=leetcode id=1520 lang=java
 *
 * [1520] Maximum Number of Non-Overlapping Substrings
 *
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/description/
 *
 * algorithms
 * Medium (31.05%)
 * Likes:    183
 * Dislikes: 32
 * Total Accepted:    3.5K
 * Total Submissions: 11.4K
 * Testcase Example:  '"adefaddaccc"'
 *
 * Given a string s of lowercase letters, you need to find the maximum number
 * of non-empty substrings of s that meet the following conditions:
 * 
 * 
 * The substrings do not overlap, that is for any two substrings s[i..j] and
 * s[k..l], either j < k or i > l is true.
 * A substring that contains a certain character c must also contain all
 * occurrences of c.
 * 
 * 
 * Find the maximum number of substrings that meet the above conditions. If
 * there are multiple solutions with the same number of substrings, return the
 * one with minimum total length. It can be shown that there exists a unique
 * solution of minimum total length.
 * 
 * Notice that you can return the substrings in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "adefaddaccc"
 * Output: ["e","f","ccc"]
 * Explanation: The following are all the possible substrings that meet the
 * conditions:
 * [
 * "adefaddaccc"
 * "adefadda",
 * "ef",
 * "e",
 * ⁠ "f",
 * "ccc",
 * ]
 * If we choose the first string, we cannot choose anything else and we'd get
 * only 1. If we choose "adefadda", we are left with "ccc" which is the only
 * one that doesn't overlap, thus obtaining 2 substrings. Notice also, that
 * it's not optimal to choose "ef" since it can be split into two. Therefore,
 * the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No
 * other solution of the same number of substrings exist.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abbaccd"
 * Output: ["d","bb","cc"]
 * Explanation: Notice that while the set of substrings ["d","abba","cc"] also
 * has length 3, it's considered incorrect since it has larger total
 * length.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> maxNumOfSubstrings(String s) {
    	int n = s.length();
    	int[] start = new int[26];
    	int[] end = new int[26];
    	Arrays.fill(start, -1);
    	Arrays.fill(end, -1);
        for(int i = 0; i < n; i++){
        	if(start[s.charAt(i) - 'a'] == -1){
        		start[s.charAt(i) - 'a'] = i;
        	}
        	end[s.charAt(i) - 'a'] = i;
        }
        List<int[]> intervals = new ArrayList<>();
        for(int i = 0; i < 26; i++){
        	int[] interval = new int[2];
        	interval[0] = start[i];
        	interval[1] = end[i];
        	if(interval[0] != -1){
	        	for(int j = interval[0]; j <= interval[1]; j++){
	        		interval[0] = Math.min(interval[0], start[s.charAt(j) - 'a']);
					interval[1] = Math.max(interval[1], end[s.charAt(j) - 'a']);
	        	}	
	        	if(interval[0] == start[i]){
	        		intervals.add(interval);
	        	}
        	}
        	
        }
        Collections.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        
        List<String> ans = new ArrayList<>();
        int lastEnd = -1;
        for(int i = 0; i < intervals.size(); i++){
        	System.out.println(Arrays.toString(intervals.get(i)));
        	if(intervals.get(i)[0] > lastEnd){
        		lastEnd = intervals.get(i)[1];
        		ans.add(s.substring(intervals.get(i)[0], intervals.get(i)[1] + 1));
        	}
        }
        return ans;
    }
}
// @lc code=end
