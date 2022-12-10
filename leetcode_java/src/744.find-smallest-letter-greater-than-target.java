/*
 * @lc app=leetcode id=744 lang=java
 *
 * [744] Find Smallest Letter Greater Than Target
 *
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
 *
 * algorithms
 * Easy (44.69%)
 * Likes:    2360
 * Dislikes: 1911
 * Total Accepted:    254.8K
 * Total Submissions: 569.7K
 * Testcase Example:  '["c","f","j"]\n"a"'
 *
 * You are given an array of characters letters that is sorted in
 * non-decreasing order, and a character target. There are at least two
 * different characters in letters.
 * 
 * Return the smallest character in letters that is lexicographically greater
 * than target. If such a character does not exist, return the first character
 * in letters.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: letters = ["c","f","j"], target = "a"
 * Output: "c"
 * Explanation: The smallest character that is lexicographically greater than
 * 'a' in letters is 'c'.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: letters = ["c","f","j"], target = "c"
 * Output: "f"
 * Explanation: The smallest character that is lexicographically greater than
 * 'c' in letters is 'f'.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: letters = ["x","x","y","y"], target = "z"
 * Output: "x"
 * Explanation: There are no characters in letters that is lexicographically
 * greater than 'z' so we return letters[0].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= letters.length <= 10^4
 * letters[i] is a lowercase English letter.
 * letters is sorted in non-decreasing order.
 * letters contains at least two different characters.
 * target is a lowercase English letter.
 * 
 * 
 */

// @lc code=start
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int ret = Arrays.binarySearch(letters, (char)(target + 1));
        if(ret < 0){
            ret = -ret - 1;
        }
        return ret == letters.length ? letters[0] : letters[ret];
    }
    
    public char nextGreatestLetter1(char[] letters, char target) {
        return IntStream.range(0, letters.length)
                .mapToObj(i -> letters[i])
                .filter(c -> c > target)
                .findFirst().orElse(letters[0]);
    }
}
// @lc code=end
