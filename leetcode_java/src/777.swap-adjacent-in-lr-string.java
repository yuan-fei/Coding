/*
 * @lc app=leetcode id=777 lang=java
 *
 * [777] Swap Adjacent in LR String
 *
 * https://leetcode.com/problems/swap-adjacent-in-lr-string/description/
 *
 * algorithms
 * Medium (37.03%)
 * Likes:    1040
 * Dislikes: 853
 * Total Accepted:    67.6K
 * Total Submissions: 182.7K
 * Testcase Example:  '"RXXLRXRXL"\n"XRLXXRRLX"'
 *
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a
 * move consists of either replacing one occurrence of "XL" with "LX", or
 * replacing one occurrence of "RX" with "XR". Given the starting string start
 * and the ending string end, return True if and only if there exists a
 * sequence of moves to transform one string to the other.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: true
 * Explanation: We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: start = "X", end = "L"
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= start.length <= 10^4
 * start.length == end.length
 * Both start and end will only consist of characters in 'L', 'R', and 'X'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canTransform(String start, String end) {
        int n = start.length();
        int i = 0;
        int j = 0;
        while(i < n && j < n){
            if(start.charAt(i) == 'X'){
                i++;
            }
            else if(end.charAt(j) == 'X'){
                j++;
            }
            else {
                if(start.charAt(i) != end.charAt(j)){
                    return false;
                }
                else if(start.charAt(i) == 'L' && i < j){
                    return false;
                }
                else if(start.charAt(i) == 'R' && i > j){
                    return false;
                }
                i++;
                j++;
            }
        }
        while(i < n){
            if(start.charAt(i) != 'X'){
                return false;
            }
            i++;
        }
        while(j < n){
            if(end.charAt(j) != 'X'){
                return false;
            }
            j++;
        }
        return true;
    }
}
// @lc code=end
