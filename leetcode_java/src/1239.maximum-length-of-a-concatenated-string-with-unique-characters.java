/*
 * @lc app=leetcode id=1239 lang=java
 *
 * [1239] Maximum Length of a Concatenated String with Unique Characters
 *
 * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/description/
 *
 * algorithms
 * Medium (34.69%)
 * Likes:    46
 * Dislikes: 7
 * Total Accepted:    3.3K
 * Total Submissions: 8.8K
 * Testcase Example:  '["un","iq","ue"]'
 *
 * Given an array of strings arr. String s is a concatenation of a sub-sequence
 * of arr which have unique characters.
 * 
 * Return the maximum possible length of s.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and
 * "ique".
 * Maximum length is 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lower case English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxLength(List<String> arr) {
    	int n = arr.size();
    	int[] dups = getDupMap(arr);
    	//total length of non-dup subset
        int[] dp = new int[1 << n];
        for(int i = 0; i < n; i++){
        	if((dups[i] & (1 << i)) == 0){
        		dp[1 << i] = arr.get(i).length();
        	}
        }
        
        int max = 0;
        for(int i = 0; i < (1 << n); i++){
        	for(int j = 0; j < n; j++){
        		if((i & (1 << j)) != 0){
        			int m = i & ~(1 << j);
        			if((dups[j] & m) == 0){
        				dp[i] = dp[m] + dp[1 << j];
        				max = Math.max(max, dp[i]);
        			}
        		}
        	}
        }
        return max;
    }

    private int[] getDupMap(List<String> arr){
    	int n = arr.size();
    	int[] ret = new int[n];
    	int[] cnt;
    	for(int i = 0; i < n; i++){
    		cnt = new int[26];
    		for(char c: arr.get(i).toCharArray()){
    			cnt[c - 'a']++;
    			if(cnt[c - 'a'] > 1){
    				ret[i] |= 1 << i;
    				break;
    			}
    		}
    	}
    	for(int i = 0; i < n; i++){
    		cnt = new int[26];
			for(char c: arr.get(i).toCharArray()){
    			cnt[c - 'a']++;
    		}
    		for(int j = i + 1; j < n; j++){	
    			for(char c: arr.get(j).toCharArray()){
    				if(cnt[c - 'a'] > 0){
    					ret[i] |= (1 << j);	
	    				ret[j] |= (1 << i);
	    				break;
    				}
    			}
    		}
    	}
    	return ret;
    }
}
// @lc code=end
