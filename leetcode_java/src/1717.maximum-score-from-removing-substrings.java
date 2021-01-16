/*
 * @lc app=leetcode id=1717 lang=java
 *
 * [1717] Maximum Score From Removing Substrings
 *
 * https://leetcode.com/problems/maximum-score-from-removing-substrings/description/
 *
 * algorithms
 * Medium (37.15%)
 * Likes:    153
 * Dislikes: 12
 * Total Accepted:    3.8K
 * Total Submissions: 10.1K
 * Testcase Example:  '"cdbcbbaaabab"\n4\n5'
 *
 * You are given a string s and two integers x and y. You can perform two types
 * of operations any number of times.
 * 
 * 
 * Remove substring "ab" and gain x points.
 * 
 * 
 * For example, when removing "ab" from "cabxbae" it becomes
 * "cxbae".
 * 
 * 
 * Remove substring "ba" and gain y points.
 * 
 * For example, when removing "ba" from "cabxbae" it becomes
 * "cabxe".
 * 
 * 
 * 
 * 
 * Return the maximum points you can gain after applying the above operations
 * on s.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "cdbcbbaaabab", x = 4, y = 5
 * Output: 19
 * Explanation:
 * - Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5
 * points are added to the score.
 * - Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4
 * points are added to the score.
 * - Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points
 * are added to the score.
 * - Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are
 * added to the score.
 * Total score = 5 + 4 + 5 + 5 = 19.
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aabbaaxybbaabb", x = 5, y = 4
 * Output: 20
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * 1 <= x, y <= 10^4
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumGain(String s, int x, int y) {
    	int left = -1;
    	int gain = 0;
        for(int right = 0; right < s.length(); right++){
        	if(s.charAt(right) > 'b'){
        		if(left != -1){
        			gain += getMaximumGain(s.substring(left, right), x, y);
        			left = -1;
        		}
        	}
        	else{
        		if(left == -1){
        			left = right;	
        		}
        	}
        }
        if(left != -1){
			gain += getMaximumGain(s.substring(left, s.length()), x, y);
		}
        return gain;
    }

    int getMaximumGain(String s, int x, int y){
    	int[] ret = getMaxGain(s, x > y ? 'a' : 'b');
    	return ret[0] * x + ret[1] * y;
    }

    int[] getMaxGain(String s, char firstChar){

    	int[] cnt = new int[2];
    	int[] ret = new int[2];
    	for(char c : s.toCharArray()){
    		if(c == firstChar){
    			cnt[firstChar - 'a']++;
    		}
    		else{
    			if(cnt[firstChar - 'a'] > 0){
					cnt[firstChar - 'a']--;
					ret[firstChar - 'a']++;
				}
				else{
					cnt[c - 'a']++;
				}
    		}
    	}
    	ret[1 - (firstChar - 'a')] += Math.min(cnt[0], cnt[1]);
    	// System.out.println(s +", " +firstChar+ Arrays.toString(ret));
    	return ret;

    }
}
// @lc code=end
