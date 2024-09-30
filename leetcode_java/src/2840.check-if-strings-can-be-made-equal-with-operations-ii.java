/*
 * @lc app=leetcode id=2840 lang=java
 *
 * [2840] Check if Strings Can be Made Equal With Operations II
 *
 * https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-ii/description/
 *
 * algorithms
 * Medium (54.93%)
 * Likes:    247
 * Dislikes: 3
 * Total Accepted:    25.9K
 * Total Submissions: 46.6K
 * Testcase Example:  '"abcdba"\n"cabdab"'
 *
 * You are given two strings s1 and s2, both of length n, consisting of
 * lowercase English letters.
 * 
 * You can apply the following operation on any of the two strings any number
 * of times:
 * 
 * 
 * Choose any two indices i and j such that i < j and the difference j - i is
 * even, then swap the two characters at those indices in the string.
 * 
 * 
 * Return true if you can make the strings s1 and s2 equal, and false
 * otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s1 = "abcdba", s2 = "cabdab"
 * Output: true
 * Explanation: We can apply the following operations on s1:
 * - Choose the indices i = 0, j = 2. The resulting string is s1 = "cbadba".
 * - Choose the indices i = 2, j = 4. The resulting string is s1 = "cbbdaa".
 * - Choose the indices i = 1, j = 5. The resulting string is s1 = "cabdab" =
 * s2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s1 = "abe", s2 = "bea"
 * Output: false
 * Explanation: It is not possible to make the two strings equal.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == s1.length == s2.length
 * 1 <= n <= 10^5
 * s1 and s2 consist only of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkStrings(String s1, String s2) {
        return checkWithOffset(s1, s2, 0) && checkWithOffset(s1, s2, 1);
    }

    boolean checkWithOffset(String s1, String s2, int start){
        List<Character> subString1 = new ArrayList<>();
        List<Character> subString2 = new ArrayList<>();
        for(int i = start; i < s1.length(); i += 2){
            subString1.add(s1.charAt(i));
            subString2.add(s2.charAt(i));
        }
        Collections.sort(subString1);
        Collections.sort(subString2);
        return subString1.equals(subString2);
    }
}
// @lc code=end
