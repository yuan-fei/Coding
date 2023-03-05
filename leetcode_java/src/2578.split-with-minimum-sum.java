/*
 * @lc app=leetcode id=2578 lang=java
 *
 * [2578] Split With Minimum Sum
 *
 * https://leetcode.com/problems/split-with-minimum-sum/description/
 *
 * algorithms
 * Easy (64.37%)
 * Likes:    22
 * Dislikes: 13
 * Total Accepted:    10.7K
 * Total Submissions: 16.7K
 * Testcase Example:  '4325'
 *
 * Given a positive integer num, split it into two non-negative integers num1
 * and num2 such that:
 * 
 * 
 * The concatenation of num1 and num2 is a permutation of num.
 * 
 * 
 * In other words, the sum of the number of occurrences of each digit in num1
 * and num2 is equal to the number of occurrences of that digit in
 * num.
 * 
 * 
 * num1 and num2 can contain leading zeros.
 * 
 * 
 * Return the minimum possible sum of num1 and num2.
 * 
 * Notes:
 * 
 * 
 * It is guaranteed that num does not contain any leading zeros.
 * The order of occurrence of the digits in num1 and num2 may differ from the
 * order of occurrence of num.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 4325
 * Output: 59
 * Explanation: We can split 4325 so that num1 is 24 and num2 is 35, giving a
 * sum of 59. We can prove that 59 is indeed the minimal possible sum.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = 687
 * Output: 75
 * Explanation: We can split 687 so that num1 is 68 and num2 is 7, which would
 * give an optimal sum of 75.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 10 <= num <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int splitNum(int n) {
        String s = "" + n;
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String s1 = "";
        String s2 = "";
        for(int i = chars.length - 1; i >= 0; i -= 2){
            s1 = chars[i] + s1;
            if(i >= 1){
                s2 = chars[i - 1] + s2;    
            }
        }
        return Integer.parseInt(s1) + Integer.parseInt(s2);
    }
}
// @lc code=end
