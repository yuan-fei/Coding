/*
 * @lc app=leetcode id=784 lang=java
 *
 * [784] Letter Case Permutation
 *
 * https://leetcode.com/problems/letter-case-permutation/description/
 *
 * algorithms
 * Medium (73.58%)
 * Likes:    3999
 * Dislikes: 150
 * Total Accepted:    255.6K
 * Total Submissions: 347.3K
 * Testcase Example:  '"a1b2"'
 *
 * Given a string s, you can transform every letter individually to be
 * lowercase or uppercase to create another string.
 * 
 * Return a list of all possible strings we could create. Return the output in
 * any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "3z4"
 * Output: ["3z4","3Z4"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 12
 * s consists of lowercase English letters, uppercase English letters, and
 * digits.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> letterCasePermutation(String s) {
        int cnt = 0;
        for(char c : s.toCharArray()){
            if(Character.isLetter(c)){
                cnt++;
            }
        }
        List<String> ans = new ArrayList<>();
        for(int i = 0; i < (1 << cnt); i++){
            ans.add(generate(s, i));
        }
        return ans;
    }

    String generate(String s, int mask){
        int i = 0;
        String ans = "";
        for(char c : s.toCharArray()){
            if(Character.isLetter(c)){
                if(((mask >> i) & 1) == 1){
                    c = Character.toUpperCase(c);
                }
                else{
                    c = Character.toLowerCase(c);
                }
                i++;
            }
            ans += c;
        }
        return ans;
    }
}
// @lc code=end
