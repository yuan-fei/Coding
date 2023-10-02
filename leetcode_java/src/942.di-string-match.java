/*
 * @lc app=leetcode id=942 lang=java
 *
 * [942] DI String Match
 *
 * https://leetcode.com/problems/di-string-match/description/
 *
 * algorithms
 * Easy (77.77%)
 * Likes:    2338
 * Dislikes: 940
 * Total Accepted:    141.3K
 * Total Submissions: 181.4K
 * Testcase Example:  '"IDID"'
 *
 * A permutation perm of n + 1 integers of all the integers in the range [0, n]
 * can be represented as a string s of length n where:
 * 
 * 
 * s[i] == 'I' if perm[i] < perm[i + 1], and
 * s[i] == 'D' if perm[i] > perm[i + 1].
 * 
 * 
 * Given a string s, reconstruct the permutation perm and return it. If there
 * are multiple valid permutations perm, return any of them.
 * 
 * 
 * Example 1:
 * Input: s = "IDID"
 * Output: [0,4,1,3,2]
 * Example 2:
 * Input: s = "III"
 * Output: [0,1,2,3]
 * Example 3:
 * Input: s = "DDI"
 * Output: [3,2,0,1]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s[i] is either 'I' or 'D'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] diStringMatch(String s) {
        int max = 0;
        int min = 0;
        int[] ans = new int[s.length() + 1];
        int i = 0;
        ans[i++] = 0;
        for(char c : s.toCharArray()){
            if(c == 'I'){
                ans[i++] = max + 1;
                max++;
            }
            else{
                ans[i++] = min - 1;
                min--;
            }
        }
        for(i = 0; i < ans.length; i++){
            ans[i] -= min;
        }
        return ans;
    }
}
// @lc code=end
