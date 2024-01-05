/*
 * @lc app=leethash id=1044 lang=java
 *
 * [1044] Longest Duplicate Substring
 *
 * https://leethash.com/problems/longest-duplicate-substring/description/
 *
 * algorithms
 * Hard (30.49%)
 * Likes:    2109
 * Dislikes: 373
 * Total Accepted:    63.5K
 * Total Submissions: 208.2K
 * Testcase Example:  '"banana"'
 *
 * Given a string s, consider all duplicated substrings: (contiguous)
 * substrings of s that occur 2 or more times. The occurrences may overlap.
 * 
 * Return any duplicated substring that has the longest possible length. If s
 * does not have a duplicated substring, the answer is "".
 * 
 * 
 * Example 1:
 * Input: s = "banana"
 * Output: "ana"
 * Example 2:
 * Input: s = "abcd"
 * Output: ""
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= s.length <= 3 * 10^4
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String longestDupSubstring(String s) {
        int low = 1;
        int high = s.length();
        while(low + 1 < high){
            int mid = (high + low) / 2;
            String dupString = getDupSubstring(s, mid);
            if(!dupString.isEmpty()){
                low = mid;
            }
            else{
                high = mid;
            }
        }
        String dupString = getDupSubstring(s, high);
        if(!dupString.isEmpty()){
            return dupString;
        }
        return getDupSubstring(s, low);
    }

    String getDupSubstring(String s, int len){
        Set<Long> seen = new HashSet<>();
        long hash = 0;
        long base = (long)1e9 + 7;
        long multiplier = 1;
        for(int i = 0; i < len - 1; i++){
            hash *= base;
            hash += s.charAt(i);
            multiplier *= base;
        }
        for(int i = len - 1; i < s.length(); i++){
            hash *= base;
            hash += s.charAt(i);
            if(!seen.add(hash)){
                return s.substring(i - len + 1, i + 1);
            }
            hash -= multiplier * s.charAt(i - len + 1);
        }
        return "";
    }
}
// @lc code=end
