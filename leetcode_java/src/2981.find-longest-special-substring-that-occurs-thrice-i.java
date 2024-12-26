/*
 * @lc app=leetcode id=2981 lang=java
 *
 * [2981] Find Longest Special Substring That Occurs Thrice I
 *
 * https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/description/
 *
 * algorithms
 * Medium (41.18%)
 * Likes:    675
 * Dislikes: 63
 * Total Accepted:    120.7K
 * Total Submissions: 193.9K
 * Testcase Example:  '"aaaa"'
 *
 * You are given a string s that consists of lowercase English letters.
 * 
 * A string is called special if it is made up of only a single character. For
 * example, the string "abc" is not special, whereas the strings "ddd", "zz",
 * and "f" are special.
 * 
 * Return the length of the longest special substring of s which occurs at
 * least thrice, or -1 if no special substring occurs at least thrice.
 * 
 * A substring is a contiguous non-empty sequence of characters within a
 * string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aaaa"
 * Output: 2
 * Explanation: The longest special substring which occurs thrice is "aa":
 * substrings "aaaa", "aaaa", and "aaaa".
 * It can be shown that the maximum length achievable is 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abcdef"
 * Output: -1
 * Explanation: There exists no special substring which occurs at least thrice.
 * Hence return -1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "abcaba"
 * Output: 1
 * Explanation: The longest special substring which occurs thrice is "a":
 * substrings "abcaba", "abcaba", and "abcaba".
 * It can be shown that the maximum length achievable is 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= s.length <= 50
 * s consists of only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumLength(String s) {
        PriorityQueue<Integer>[] pq = new PriorityQueue[26];
        for(int i = 0; i < 26; i++){
            pq[i] = new PriorityQueue<Integer>();
        }
        int left = 0;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) != s.charAt(i - 1)){
                pq[s.charAt(i - 1) - 'a'].offer(i - left);
                if(pq[s.charAt(i - 1) - 'a'].size() > 3){
                    pq[s.charAt(i - 1) - 'a'].poll();
                }
                left = i;
            }
        }
        pq[s.charAt(s.length() - 1) - 'a'].offer(s.length() - left);
        if(pq[s.charAt(s.length() - 1) - 'a'].size() > 3){
            pq[s.charAt(s.length() - 1) - 'a'].poll();
        }
        int ans = 0;
        for(PriorityQueue q : pq){
            int cur = 0;
            if(q.size() == 1){
                cur = Math.max((int)q.poll() - 2, cur);
            }
            else if(q.size() == 2){
                int[] a = {(int)q.poll(), (int)q.poll()};
                if(a[0] >= a[1] - 1){
                    cur = Math.max(a[1] - 1, cur);    
                }
                else{
                    cur = Math.max(a[1] - 2, cur);
                }
            }
            if(q.size() == 3){
                int[] a = {(int)q.poll(), (int)q.poll(), (int)q.poll()};
                if(a[0] == a[2]){
                    cur = Math.max(cur, a[2]);
                }
                else if(a[1] >= a[2] - 1){
                    cur = Math.max(a[2] - 1, cur);    
                }
                else{
                    cur = Math.max(a[2] - 2, cur);
                }
            }
            ans = Math.max(ans, cur);
        }
        return ans == 0 ? -1 : ans;
    }
}
// @lc code=end
