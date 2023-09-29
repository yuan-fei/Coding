/*
 * @lc app=leetcode id=916 lang=java
 *
 * [916] Word Subsets
 *
 * https://leetcode.com/problems/word-subsets/description/
 *
 * algorithms
 * Medium (53.07%)
 * Likes:    2651
 * Dislikes: 224
 * Total Accepted:    110.7K
 * Total Submissions: 209K
 * Testcase Example:  '["amazon","apple","facebook","google","leetcode"]\n["e","o"]'
 *
 * You are given two string arrays words1 and words2.
 * 
 * A string b is a subset of string a if every letter in b occurs in a
 * including multiplicity.
 * 
 * 
 * For example, "wrr" is a subset of "warrior" but is not a subset of
 * "world".
 * 
 * 
 * A string a from words1 is universal if for every string b in words2, b is a
 * subset of a.
 * 
 * Return an array of all the universal strings in words1. You may return the
 * answer in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 =
 * ["e","o"]
 * Output: ["facebook","google","leetcode"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 =
 * ["l","e"]
 * Output: ["apple","google","leetcode"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words1.length, words2.length <= 10^4
 * 1 <= words1[i].length, words2[i].length <= 10
 * words1[i] and words2[i] consist only of lowercase English letters.
 * All the strings of words1 are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] bmax = count("");
        for (String b: B) {
            int[] bCount = count(b);
            for (int i = 0; i < 26; ++i)
                bmax[i] = Math.max(bmax[i], bCount[i]);
        }

        List<String> ans = new ArrayList();
        search: for (String a: A) {
            int[] aCount = count(a);
            for (int i = 0; i < 26; ++i)
                if (aCount[i] < bmax[i])
                    continue search;
            ans.add(a);
        }

        return ans;
    }

    public int[] count(String S) {
        int[] ans = new int[26];
        for (char c: S.toCharArray())
            ans[c - 'a']++;
        return ans;
    }
}
// @lc code=end
