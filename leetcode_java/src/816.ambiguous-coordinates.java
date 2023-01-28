/*
 * @lc app=leetcode id=816 lang=java
 *
 * [816] Ambiguous Coordinates
 *
 * https://leetcode.com/problems/ambiguous-coordinates/description/
 *
 * algorithms
 * Medium (56.17%)
 * Likes:    284
 * Dislikes: 626
 * Total Accepted:    27.2K
 * Total Submissions: 48.4K
 * Testcase Example:  '"(123)"'
 *
 * We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)". Then, we
 * removed all commas, decimal points, and spaces and ended up with the string
 * s.
 * 
 * 
 * For example, "(1, 3)" becomes s = "(13)" and "(2, 0.5)" becomes s =
 * "(205)".
 * 
 * 
 * Return a list of strings representing all possibilities for what our
 * original coordinates could have been.
 * 
 * Our original representation never had extraneous zeroes, so we never started
 * with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other
 * number that can be represented with fewer digits. Also, a decimal point
 * within a number never occurs without at least one digit occurring before it,
 * so we never started with numbers like ".1".
 * 
 * The final answer list can be returned in any order. All coordinates in the
 * final answer have exactly one space between them (occurring after the
 * comma.)
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "(123)"
 * Output: ["(1, 2.3)","(1, 23)","(1.2, 3)","(12, 3)"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "(0123)"
 * Output: ["(0, 1.23)","(0, 12.3)","(0, 123)","(0.1, 2.3)","(0.1, 23)","(0.12,
 * 3)"]
 * Explanation: 0.0, 00, 0001 or 00.01 are not allowed.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "(00011)"
 * Output: ["(0, 0.011)","(0.001, 1)"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 4 <= s.length <= 12
 * s[0] == '(' and s[s.length - 1] == ')'.
 * The rest of s are digits.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> ambiguousCoordinates(String s) {
        String num = s.substring(1, s.length() -1);
        List<String> res = new ArrayList<>();
        for(int i = 1; i < num.length(); i++){
            List<String> cord1 = convert(num.substring(0, i));
            List<String> cord2 = convert(num.substring(i, num.length()));
            if(!cord1.isEmpty() && !cord2.isEmpty()){
                for(String c1 : cord1){
                    for(String c2 : cord2){
                        res.add("(" + c1 + ", " + c2 + ")");
                    }
                }
            }
        }
        return res;
    }

    List<String> convert(String num){
        // System.out.println(num);
        if(num.length() == 1){
            return Arrays.asList(num);
        }
        List<String> res = new ArrayList<>();
        if(testIntegral(num)){
            res.add(num);
        }
        for(int i = 1; i < num.length(); i++){
            String n = num.substring(0, i) + "." + num.substring(i, num.length());
            if(testIntegral(num.substring(0, i)) && testFractional(num.substring(i, num.length()))){
                res.add(n);
            }
        }
        return res;
    }

    boolean testIntegral(String integral){
        if(integral.length() > 1 && integral.charAt(0) == '0'){
            return false;
        }
        return true;
    }

    boolean testFractional(String fractional){
        if(fractional.charAt(fractional.length() - 1) == '0'){
            return false;
        }
        return true;
    }
}
// @lc code=end
