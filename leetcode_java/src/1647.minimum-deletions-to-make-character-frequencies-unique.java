/*
 * @lc app=leetcode id=1647 lang=java
 *
 * [1647] Minimum Deletions to Make Character Frequencies Unique
 *
 * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/description/
 *
 * algorithms
 * Medium (52.72%)
 * Likes:    107
 * Dislikes: 5
 * Total Accepted:    6.9K
 * Total Submissions: 13.1K
 * Testcase Example:  '"aab"'
 *
 * A string s is called good if there are no two different characters in s that
 * have the same frequency.
 * 
 * Given a string s, return the minimum number of characters you need to delete
 * to make s good.
 * 
 * The frequency of a character in a string is the number of times it appears
 * in the string. For example, in the string "aab", the frequency of 'a' is 2,
 * while the frequency of 'b' is 1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aab"
 * Output: 0
 * Explanation: s is already good.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aaabbbcc"
 * Output: 2
 * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
 * Another way it to delete one 'b' and one 'c' resulting in the good string
 * "aaabbc".
 * 
 * Example 3:
 * 
 * 
 * Input: s = "ceabaacb"
 * Output: 2
 * Explanation: You can delete both 'c's resulting in the good string "eabaab".
 * Note that we only care about characters that are still in the string at the
 * end (i.e. frequency of 0 is ignored).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minDeletions(String s) {
        int[] cnt = new int[26];
        for(char c : s.toCharArray()){
        	cnt[c - 'a']++;
        }
        int[] freq = new int[s.length() + 1];
        for(int c : cnt){
        	freq[c]++;
        }
        int ans = 0;
        for(int i = freq.length - 1; i > 0; i--){
        	if(freq[i] > 1){
        		freq[i - 1] += freq[i] - 1;
        		ans += freq[i] - 1;
        	}
        }
        return ans;
    }
}
// @lc code=end
