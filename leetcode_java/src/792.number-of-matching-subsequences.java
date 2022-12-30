/*
 * @lc app=leetcode id=792 lang=java
 *
 * [792] Number of Matching Subsequences
 *
 * https://leetcode.com/problems/number-of-matching-subsequences/description/
 *
 * algorithms
 * Medium (51.80%)
 * Likes:    4870
 * Dislikes: 205
 * Total Accepted:    204K
 * Total Submissions: 394K
 * Testcase Example:  '"abcde"\n["a","bb","acd","ace"]'
 *
 * Given a string s and an array of strings words, return the number of
 * words[i] that is a subsequence of s.
 * 
 * A subsequence of a string is a new string generated from the original string
 * with some characters (can be none) deleted without changing the relative
 * order of the remaining characters.
 * 
 * 
 * For example, "ace" is a subsequence of "abcde".
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s:
 * "a", "acd", "ace".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 5 * 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * s and words[i] consist of only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Node {
    String word;
    int index;
    Node(String word, int index) {
        this.word = word;
        this.index = index;
    }
}
class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        ArrayList<Node>[] buckets = new ArrayList[26];
        for (int i = 0; i < 26; ++i) buckets[i] = new ArrayList<>();
        for (String word : words) {
            char startingChar = word.charAt(0);
            buckets[startingChar-'a'].add(new Node(word, 0));
        }
        int ans = 0;
        for (char c : s.toCharArray()) {
            ArrayList<Node> currBucket = buckets[c-'a'];
            buckets[c-'a'] = new ArrayList<>();
            for (Node node : currBucket) {
                node.index += 1; // Point to next character of node.word
                if (node.index == node.word.length()) {
                    ans += 1;
                } else {
                    char startingChar = node.word.charAt(node.index);
                    buckets[startingChar - 'a'].add(node);
                }
            }
        }
        return ans;
    }
}
// @lc code=end
