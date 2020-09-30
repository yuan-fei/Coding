/*
 * @lc app=leetcode id=537 lang=java
 *
 * [537] Complex Number Multiplication
 *
 * https://leetcode.com/problems/complex-number-multiplication/description/
 *
 * algorithms
 * Medium (67.97%)
 * Likes:    253
 * Dislikes: 751
 * Total Accepted:    50.2K
 * Total Submissions: 73.9K
 * Testcase Example:  '"1+1i"\n"1+1i"'
 *
 * 
 * Given two strings representing two complex numbers.
 * 
 * 
 * You need to return a string representing their multiplication. Note i^2 = -1
 * according to the definition.
 * 
 * 
 * Example 1:
 * 
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i, and you need convert
 * it to the form of 0+2i.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "1+-1i", "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i^2 - 2 * i = -2i, and you need convert
 * it to the form of 0+-2i.
 * 
 * 
 * 
 * Note:
 * 
 * The input strings will not have extra blank.
 * The input strings will be given in the form of a+bi, where the integer a and
 * b will both belong to the range of [-100, 100]. And the output should be
 * also in this form.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String complexNumberMultiply(String a, String b) {
        int[] partsA = getNumbers(a);
        int[] partsB = getNumbers(b);
        int[] res = new int[]{partsA[0] * partsB[0] - partsA[1] * partsB[1], partsA[0] * partsB[1] + partsA[1] * partsB[0]};
        return res[0] + "+" + res[1] + "i";
    }

    int[] getNumbers(String im){
    	String[] parts = im.split("\\+");
    	return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1].substring(0, parts[1].length() - 1))};
    }
}
// @lc code=end
