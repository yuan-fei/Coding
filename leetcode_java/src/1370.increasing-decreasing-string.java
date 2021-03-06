/*
 * @lc app=leetcode id=1370 lang=java
 *
 * [1370] Increasing Decreasing String
 *
 * https://leetcode.com/problems/increasing-decreasing-string/description/
 *
 * algorithms
 * Easy (76.99%)
 * Likes:    14
 * Dislikes: 13
 * Total Accepted:    2.6K
 * Total Submissions: 3.4K
 * Testcase Example:  '"aaaabbbbcccc"'
 *
 * Given a string s. You should re-order the string using the following
 * algorithm:
 * 
 * 
 * Pick the smallest character from s and append it to the result.
 * Pick the smallest character from s which is greater than the last appended
 * character to the result and append it.
 * Repeat step 2 until you cannot pick more characters.
 * Pick the largest character from s and append it to the result.
 * Pick the largest character from s which is smaller than the last appended
 * character to the result and append it.
 * Repeat step 5 until you cannot pick more characters.
 * Repeat the steps from 1 to 6 until you pick all characters from s.
 * 
 * 
 * In each step, If the smallest or the largest character appears more than
 * once you can choose any occurrence and append it to the result.
 * 
 * Return the result string after sorting s with this algorithm.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aaaabbbbcccc"
 * Output: "abccbaabccba"
 * Explanation: After steps 1, 2 and 3 of the first iteration, result = "abc"
 * After steps 4, 5 and 6 of the first iteration, result = "abccba"
 * First iteration is done. Now s = "aabbcc" and we go back to step 1
 * After steps 1, 2 and 3 of the second iteration, result = "abccbaabc"
 * After steps 4, 5 and 6 of the second iteration, result = "abccbaabccba"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "rat"
 * Output: "art"
 * Explanation: The word "rat" becomes "art" after re-ordering it with the
 * mentioned algorithm.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "leetcode"
 * Output: "cdelotee"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "ggggggg"
 * Output: "ggggggg"
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: s = "spo"
 * Output: "ops"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 500
 * s contains only lower-case English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String sortString(String s) {
        int[] cnt = new int[26];
        boolean inc = true;
        String ans = "";
        boolean isEmpty = false;
        for(char c : s.toCharArray()){
        	cnt[c - 'a']++;
        }
        
        while(!isEmpty){
        	isEmpty = true;
        	if(inc){
	        	for (int i = 0; i < 26; i++) {
	        		if(cnt[i] > 0){
	        			cnt[i]--;
	        			ans += (char)(i + 'a');
	        			if(cnt[i] > 0){
	        				isEmpty = false;
	        			}
	        		}
	        	}
        	}
        	else{
        		for (int i = 25; i >= 0; i--) {
	        		if(cnt[i] > 0){
	        			cnt[i]--;
	        			ans += (char)(i + 'a');
	        			if(cnt[i] > 0){
	        				isEmpty = false;
	        			}
	        		}
	        	}	
        	}
        	inc = !inc;
        	
        }
        return ans;
    }
}
// @lc code=end
