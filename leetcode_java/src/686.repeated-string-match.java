/*
 * @lc app=leetcode id=686 lang=java
 *
 * [686] Repeated String Match
 *
 * https://leetcode.com/problems/repeated-string-match/description/
 *
 * algorithms
 * Medium (33.15%)
 * Likes:    1181
 * Dislikes: 867
 * Total Accepted:    112.3K
 * Total Submissions: 338.6K
 * Testcase Example:  '"abcd"\n"cdabcdab"'
 *
 * Given two strings a and b, return the minimum number of times you should
 * repeat string a so that string b is a substring of it. If it is impossible
 * for b​​​​​​ to be a substring of a after repeating it, return -1.
 * 
 * Notice: string "abc" repeated 0 times is "",  repeated 1 time is "abc" and
 * repeated 2 times is "abcabc".
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: a = "abcd", b = "cdabcdab"
 * Output: 3
 * Explanation: We return 3 because by repeating a three times "abcdabcdabcd",
 * b is a substring of it.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: a = "a", b = "aa"
 * Output: 2
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: a = "a", b = "a"
 * Output: 1
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: a = "abc", b = "wxyz"
 * Output: -1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= a.length <= 10^4
 * 1 <= b.length <= 10^4
 * a and b consist of lower-case English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while(sb.length() < b.length()){
            sb.append(a);
            cnt++;
        }
        String x = sb.toString();
        if(x.indexOf(b) >= 0){
            return cnt;
        }
        while(sb.length() - a.length() < b.length()){
            sb.append(a);
            cnt++;   
            x = sb.toString();
            if(x.indexOf(b) != -1){
                return cnt;
            }
        }
        return -1;
    }
}
// @lc code=end
