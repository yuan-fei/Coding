/*
 * @lc app=leetcode id=2645 lang=java
 *
 * [2645] Minimum Additions to Make Valid String
 *
 * https://leetcode.com/problems/minimum-additions-to-make-valid-string/description/
 *
 * algorithms
 * Medium (42.72%)
 * Likes:    69
 * Dislikes: 4
 * Total Accepted:    8.7K
 * Total Submissions: 20.4K
 * Testcase Example:  '"b"'
 *
 * Given a string word to which you can insert letters "a", "b" or "c" anywhere
 * and any number of times, return the minimum number of letters that must be
 * inserted so that word becomes valid.
 * 
 * A string is called valid if it can be formed by concatenating the string
 * "abc" several times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "b"
 * Output: 2
 * Explanation: Insert the letter "a" right before "b", and the letter "c"
 * right next to "a" to obtain the valid string "abc".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word = "aaa"
 * Output: 6
 * Explanation: Insert letters "b" and "c" next to each "a" to obtain the valid
 * string "abcabcabc".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: word = "abc"
 * Output: 0
 * Explanation: word is already valid. No modifications are needed. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word.length <= 50
 * word consists of letters "a", "b" and "c" only. 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int addMinimum(String word) {
        int i = 0;
        int state = 3;
        int ans = 0;
        while(i < word.length()){
            switch(word.charAt(i)){
                case 'a':
                    if(state != 3){
                        ans += 3 - state;
                    }
                    state = 1;
                    break;
                case 'b':
                    if(state != 1){
                        ans += (4 - state) % 3;
                    }
                    state = 2;
                    break;
                case 'c':
                    if(state != 2){
                        ans += (5 - state) % 3;
                    }
                    state = 3;
                    break;
            }
            i++;
        }
        ans += 3 - state;
        return ans;
    }
}
// @lc code=end
