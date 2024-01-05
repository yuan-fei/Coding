/*
 * @lc app=leetcode id=1047 lang=java
 *
 * [1047] Remove All Adjacent Duplicates In String
 *
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/
 *
 * algorithms
 * Easy (69.07%)
 * Likes:    6351
 * Dislikes: 239
 * Total Accepted:    511.6K
 * Total Submissions: 739.7K
 * Testcase Example:  '"abbaca"'
 *
 * You are given a string s consisting of lowercase English letters. A
 * duplicate removal consists of choosing two adjacent and equal letters and
 * removing them.
 * 
 * We repeatedly make duplicate removals on s until we no longer can.
 * 
 * Return the final string after all such duplicate removals have been made. It
 * can be proven that the answer is unique.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation: 
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent
 * and equal, and this is the only possible move.  The result of this move is
 * that the string is "aaca", of which only "aa" is possible, so the final
 * string is "ca".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "azxxzy"
 * Output: "ay"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        for(char c : s.toCharArray()){
            if(!dq.isEmpty() && dq.peekLast() == c){
                dq.pollLast();
            }
            else{
                dq.offerLast(c);
            }
                
            // System.out.println(dq);
        }
        
        String ans = "";
        while(!dq.isEmpty()){
            char c = dq.pollFirst();
            ans += c;
        }
        return ans;
    }

    record CharFreqency(char c, int freq) {}
}
// @lc code=end
