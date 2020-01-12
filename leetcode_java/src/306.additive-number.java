/*
 * @lc app=leetcode id=306 lang=java
 *
 * [306] Additive Number
 *
 * https://leetcode.com/problems/additive-number/description/
 *
 * algorithms
 * Medium (28.90%)
 * Likes:    278
 * Dislikes: 350
 * Total Accepted:    46.3K
 * Total Submissions: 160.2K
 * Testcase Example:  '"112358"'
 *
 * Additive number is a string whose digits can form additive sequence.
 * 
 * A valid additive sequence should contain at least three numbers. Except for
 * the first two numbers, each subsequent number in the sequence must be the
 * sum of the preceding two.
 * 
 * Given a string containing only digits '0'-'9', write a function to determine
 * if it's an additive number.
 * 
 * Note: Numbers in the additive sequence cannot have leading zeros, so
 * sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "112358"
 * Output: true
 * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5,
 * 8. 
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "199100199"
 * Output: true
 * Explanation: The additive sequence is: 1, 99, 100, 199. 
 * 1 + 99 = 100, 99 + 100 = 199
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * num consists only of digits '0'-'9'.
 * 1 <= num.length <= 35
 * 
 * 
 * Follow up:
 * How would you handle overflow for very large input integers?
 * 
 */

// @lc code=start
class Solution {
    public boolean isAdditiveNumber(String num) {
    	int n = num.length();
        for(int i = 0; i < n / 2; i++){
        	long last2 = Long.parseLong(num.substring(0, i + 1));
        	for(int j = i + 1; j < n && Math.max(j - i, i + 1) <= n - j - 1; j++){
				long last1 = Long.parseLong(num.substring(i + 1, j + 1));
				if(check(num, j + 1, last1, last1 + last2)){
					System.out.println(last2 + ", " + last1);
					return true;
				}
				if(last1 == 0){
					break;
        		}
        	}
        	if(last2 == 0){
				break;
    		}
        }
        return false;
    }
    boolean check(String num, int pos, long last1, long sum){
    	if(pos == num.length()){
    		return true;
    	}
    	String sumStr = "" + sum;
    	if(sumStr.equals(num.substring(pos, Math.min(num.length(), pos + sumStr.length())))){
    		return check(num, pos + sumStr.length(), sum, last1 + sum);
    	}
    	else{
    		return false;
    	}
    }
}
// @lc code=end
