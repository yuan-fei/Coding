/*
 * @lc app=leetcode id=1432 lang=java
 *
 * [1432] Max Difference You Can Get From Changing an Integer
 *
 * https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/description/
 *
 * algorithms
 * Medium (41.57%)
 * Likes:    31
 * Dislikes: 39
 * Total Accepted:    4.8K
 * Total Submissions: 11.6K
 * Testcase Example:  '555'
 *
 * You are given an integer num. You will apply the following steps exactly two
 * times:
 * 
 * 
 * Pick a digit x (0 <= x <= 9).
 * Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
 * Replace all the occurrences of x in the decimal representation of num by
 * y.
 * The new integer cannot have any leading zeros, also the new integer cannot
 * be 0.
 * 
 * 
 * Let a and b be the results of applying the operations to num the first and
 * second times, respectively.
 * 
 * Return the max difference between a and b.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 555
 * Output: 888
 * Explanation: The first time pick x = 5 and y = 9 and store the new integer
 * in a.
 * The second time pick x = 5 and y = 1 and store the new integer in b.
 * We have now a = 999 and b = 111 and max difference = 888
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = 9
 * Output: 8
 * Explanation: The first time pick x = 9 and y = 9 and store the new integer
 * in a.
 * The second time pick x = 9 and y = 1 and store the new integer in b.
 * We have now a = 9 and b = 1 and max difference = 8
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: num = 123456
 * Output: 820000
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: num = 10000
 * Output: 80000
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: num = 9288
 * Output: 8700
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num <= 10^8
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxDiff(int num) {
        String n = "" + num;
        String rep = "9";
        for(char x : n.toCharArray()){
        	if(x != '9'){
        		rep = "" + x;
        		break;
        	}
        }
        String n1 = n.replaceAll(rep, "9");
        String n2 = "";
        rep = "1";
        if(n.charAt(0) != '1'){
        	rep = "" + n.charAt(0);
        	n2 = n.replaceAll(rep, "1");
        }
        else{
        	rep = "0";
	        for(int i = 1; i < n.length(); i++){
	        	char x = n.charAt(i);
	        	if(x != '0' && x != '1'){
	        		rep = "" + x;
	        		break;
	        	}
	        }
	        n2 = n.replaceAll(rep, "0");
        }
        return Integer.parseInt(n1) - Integer.parseInt(n2);
    }
}
// @lc code=end
