/*
 * @lc app=leetcode id=1071 lang=java
 *
 * [1071] Greatest Common Divisor of Strings
 *
 * https://leetcode.com/problems/greatest-common-divisor-of-strings/description/
 *
 * algorithms
 * Easy (52.02%)
 * Likes:    4696
 * Dislikes: 1113
 * Total Accepted:    334.1K
 * Total Submissions: 645.7K
 * Testcase Example:  '"ABCABC"\n"ABC"'
 *
 * For two strings s and t, we say "t divides s" if and only if s = t + ... + t
 * (i.e., t is concatenated with itself one or more times).
 * 
 * Given two strings str1 and str2, return the largest string x such that x
 * divides both str1 and str2.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of English uppercase letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        return IntStream.rangeClosed(1, Math.min(str1.length(), str2.length()))
                .mapToObj(l -> str1.substring(0, l))
                .filter(divisor -> isDivisible(str1, divisor) && isDivisible(str2, divisor))
                .max(Comparator.naturalOrder())
                .orElse("");
    }

    boolean isDivisible(String str, String divisor){
        return str.length() % divisor.length() == 0 
            && IntStream.range(0, str.length())
                .allMatch(i -> str.charAt(i) == divisor.charAt(i % divisor.length()));
        
    }
}
// @lc code=end
