/*
 * @lc app=leetcode id=2182 lang=java
 *
 * [2182] Construct String With Repeat Limit
 *
 * https://leetcode.com/problems/construct-string-with-repeat-limit/description/
 *
 * algorithms
 * Medium (43.90%)
 * Likes:    52
 * Dislikes: 8
 * Total Accepted:    4.7K
 * Total Submissions: 10.7K
 * Testcase Example:  '"cczazcc"\n3'
 *
 * You are given a string s and an integer repeatLimit. Construct a new string
 * repeatLimitedString using the characters of s such that no letter appears
 * more than repeatLimit times in a row. You do not have to use all characters
 * from s.
 * 
 * Return the lexicographically largest repeatLimitedString possible.
 * 
 * A string a is lexicographically larger than a string b if in the first
 * position where a and b differ, string a has a letter that appears later in
 * the alphabet than the corresponding letter in b. If the first min(a.length,
 * b.length) characters do not differ, then the longer string is the
 * lexicographically larger one.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "cczazcc", repeatLimit = 3
 * Output: "zzcccac"
 * Explanation: We use all of the characters from s to construct the
 * repeatLimitedString "zzcccac".
 * The letter 'a' appears at most 1 time in a row.
 * The letter 'c' appears at most 3 times in a row.
 * The letter 'z' appears at most 2 times in a row.
 * Hence, no letter appears more than repeatLimit times in a row and the string
 * is a valid repeatLimitedString.
 * The string is the lexicographically largest repeatLimitedString possible so
 * we return "zzcccac".
 * Note that the string "zzcccca" is lexicographically larger but the letter
 * 'c' appears more than 3 times in a row, so it is not a valid
 * repeatLimitedString.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aababab", repeatLimit = 2
 * Output: "bbabaa"
 * Explanation: We use only some of the characters from s to construct the
 * repeatLimitedString "bbabaa". 
 * The letter 'a' appears at most 2 times in a row.
 * The letter 'b' appears at most 2 times in a row.
 * Hence, no letter appears more than repeatLimit times in a row and the string
 * is a valid repeatLimitedString.
 * The string is the lexicographically largest repeatLimitedString possible so
 * we return "bbabaa".
 * Note that the string "bbabaaa" is lexicographically larger but the letter
 * 'a' appears more than 2 times in a row, so it is not a valid
 * repeatLimitedString.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= repeatLimit <= s.length <= 10^5
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder(String.valueOf(arr));
        sb.reverse();
        arr = sb.toString().toCharArray();
        // System.out.println(Arrays.toString(arr));
        int n = arr.length;
        int fast = 1;
        int limit = 1;
        for(int i = 1; i < n; i++){
            if(arr[i] == arr[i - 1]){
                limit++;
            }
            else{
                limit = 1;
            }
            if(limit > repeatLimit){
                fast = Math.max(fast, i);
                while(fast < n && arr[fast] == arr[i]){
                    fast++;
                }
                if(fast == n){
                    return String.valueOf(arr, 0, i);
                }
                else{
                    char t = arr[i];
                    arr[i] = arr[fast];
                    arr[fast] = t;
                }
                limit = 0;
            }
        }
        return String.valueOf(arr);
    }
}
// @lc code=end
