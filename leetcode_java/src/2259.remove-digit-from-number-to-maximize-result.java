/*
 * @lc app=leetcode id=2259 lang=java
 *
 * [2259] Remove Digit From Number to Maximize Result
 *
 * https://leetcode.com/problems/remove-digit-from-number-to-maximize-result/description/
 *
 * algorithms
 * Easy (44.13%)
 * Likes:    107
 * Dislikes: 8
 * Total Accepted:    11.6K
 * Total Submissions: 26.2K
 * Testcase Example:  '"123"\n"3"'
 *
 * You are given a string number representing a positive integer and a
 * character digit.
 * 
 * Return the resulting string after removing exactly one occurrence of digit
 * from number such that the value of the resulting string in decimal form is
 * maximized. The test cases are generated such that digit occurs at least once
 * in number.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: number = "123", digit = "3"
 * Output: "12"
 * Explanation: There is only one '3' in "123". After removing '3', the result
 * is "12".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: number = "1231", digit = "1"
 * Output: "231"
 * Explanation: We can remove the first '1' to get "231" or remove the second
 * '1' to get "123".
 * Since 231 > 123, we return "231".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: number = "551", digit = "5"
 * Output: "51"
 * Explanation: We can remove either the first or second '5' from "551".
 * Both result in the string "51".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= number.length <= 100
 * number consists of digits from '1' to '9'.
 * digit is a digit from '1' to '9'.
 * digit occurs at least once in number.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String removeDigit(String number, char digit) {
        int id = number.length();
        int last = number.length();
        for(int i = 0; i < number.length(); i++){
            if(number.charAt(i) == digit){
                last = i;
                if(i + 1 < number.length() && number.charAt(i + 1) > digit){
                    id = i;
                    break;    
                }
            }
        }
        id = Math.min(id, last);
        return number.substring(0, id) + number.substring(id + 1, number.length());
    }
}
// @lc code=end
