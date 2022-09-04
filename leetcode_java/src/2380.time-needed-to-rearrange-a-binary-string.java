/*
 * @lc app=leetcode id=2380 lang=java
 *
 * [2380] Time Needed to Rearrange a Binary String
 *
 * https://leetcode.com/problems/time-needed-to-rearrange-a-binary-string/description/
 *
 * algorithms
 * Medium (46.56%)
 * Likes:    151
 * Dislikes: 79
 * Total Accepted:    14.1K
 * Total Submissions: 30.3K
 * Testcase Example:  '"0110101"'
 *
 * You are given a binary string s. In one second, all occurrences of "01" are
 * simultaneously replaced with "10". This process repeats until no occurrences
 * of "01" exist.
 * 
 * Return the number of seconds needed to complete this process.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "0110101"
 * Output: 4
 * Explanation: 
 * After one second, s becomes "1011010".
 * After another second, s becomes "1101100".
 * After the third second, s becomes "1110100".
 * After the fourth second, s becomes "1111000".
 * No occurrence of "01" exists any longer, and the process needed 4 seconds to
 * complete,
 * so we return 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "11100"
 * Output: 0
 * Explanation:
 * No occurrence of "01" exists in s, and the processes needed 0 seconds to
 * complete,
 * so we return 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s[i] is either '0' or '1'.
 * 
 * 
 * 
 * Follow up:
 * 
 * Can you solve this problem in O(n) time complexity?
 * 
 */

// @lc code=start
class Solution {
    public int secondsToRemoveOccurrences(String s) {
        char[] chars = s.toCharArray();
        boolean changed = true;
        int ans = 0;
        while(changed){
            changed = false;
            for(int i = 1; i < chars.length; ){
                if(chars[i - 1] == '0' && chars[i] == '1'){
                    chars[i - 1] = '1';
                    chars[i] = '0';
                    i += 2;
                    changed = true;
                }
                else{
                    i++;
                }
            }
            if(changed){
                ans++;
            }
        }
        return ans;
    }
}
// @lc code=end
