/*
 * @lc app=leetcode id=2384 lang=java
 *
 * [2384] Largest Palindromic Number
 *
 * https://leetcode.com/problems/largest-palindromic-number/description/
 *
 * algorithms
 * Medium (28.49%)
 * Likes:    196
 * Dislikes: 139
 * Total Accepted:    11.7K
 * Total Submissions: 41.1K
 * Testcase Example:  '"444947137"'
 *
 * You are given a string num consisting of digits only.
 * 
 * Return the largest palindromic integer (in the form of a string) that can be
 * formed using digits taken from num. It should not contain leading zeroes.
 * 
 * Notes:
 * 
 * 
 * You do not need to use all the digits of num, but you must use at least one
 * digit.
 * The digits can be reordered.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = "444947137"
 * Output: "7449447"
 * Explanation: 
 * Use the digits "4449477" from "444947137" to form the palindromic integer
 * "7449447".
 * It can be shown that "7449447" is the largest palindromic integer that can
 * be formed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = "00009"
 * Output: "9"
 * Explanation: 
 * It can be shown that "9" is the largest palindromic integer that can be
 * formed.
 * Note that the integer returned should not contain leading zeroes.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num.length <= 10^5
 * num consists of digits.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String largestPalindromic(String num) {
        int[] cnt = new int[10];
        for(char c : num.toCharArray()){
            cnt[c - '0']++;
        }
        StringBuilder head = new StringBuilder();
        StringBuilder tail = new StringBuilder();
        boolean seenNonZero = false;
        for(int i = 9; i >= 0; i--){
            if(i != 0 || seenNonZero){
                while(cnt[i] >= 2){
                    if(i > 0){
                        seenNonZero = true;
                    }
                    cnt[i] -= 2;
                    head.append(i);
                    tail.insert(0, i);
                }    
            }
        }
        int max = -1;
        for(int i = 9; i >= 0; i--){
            if(cnt[i] > 0){
                max = i;
                break;
            }
        }
        if(max != -1){
            head.append(max);
        }
        return head.toString() + tail.toString();
    }
}
// @lc code=end
