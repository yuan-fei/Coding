/*
 * @lc app=leetcode id=3093 lang=java
 *
 * [3093] Longest Common Suffix Queries
 *
 * https://leetcode.com/problems/longest-common-suffix-queries/description/
 *
 * algorithms
 * Hard (37.68%)
 * Likes:    155
 * Dislikes: 14
 * Total Accepted:    11.6K
 * Total Submissions: 31K
 * Testcase Example:  '["abcd","bcd","xbcd"]\n["cd","bcd","xyz"]'
 *
 * You are given two arrays of strings wordsContainer and wordsQuery.
 * 
 * For each wordsQuery[i], you need to find a string from wordsContainer that
 * has the longest common suffix with wordsQuery[i]. If there are two or more
 * strings in wordsContainer that share the longest common suffix, find the
 * string that is the smallest in length. If there are two or more such strings
 * that have the same smallest length, find the one that occurred earlier in
 * wordsContainer.
 * 
 * Return an array of integers ans, where ans[i] is the index of the string in
 * wordsContainer that has the longest common suffix with wordsQuery[i].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: wordsContainer = ["abcd","bcd","xbcd"], wordsQuery =
 * ["cd","bcd","xyz"]
 * 
 * Output: [1,1,1]
 * 
 * Explanation:
 * 
 * Let's look at each wordsQuery[i] separately:
 * 
 * 
 * For wordsQuery[0] = "cd", strings from wordsContainer that share the longest
 * common suffix "cd" are at indices 0, 1, and 2. Among these, the answer is
 * the string at index 1 because it has the shortest length of 3.
 * For wordsQuery[1] = "bcd", strings from wordsContainer that share the
 * longest common suffix "bcd" are at indices 0, 1, and 2. Among these, the
 * answer is the string at index 1 because it has the shortest length of 3.
 * For wordsQuery[2] = "xyz", there is no string from wordsContainer that
 * shares a common suffix. Hence the longest common suffix is "", that is
 * shared with strings at index 0, 1, and 2. Among these, the answer is the
 * string at index 1 because it has the shortest length of 3.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: wordsContainer = ["abcdefgh","poiuygh","ghghgh"], wordsQuery =
 * ["gh","acbfgh","acbfegh"]
 * 
 * Output: [2,0,2]
 * 
 * Explanation:
 * 
 * Let's look at each wordsQuery[i] separately:
 * 
 * 
 * For wordsQuery[0] = "gh", strings from wordsContainer that share the longest
 * common suffix "gh" are at indices 0, 1, and 2. Among these, the answer is
 * the string at index 2 because it has the shortest length of 6.
 * For wordsQuery[1] = "acbfgh", only the string at index 0 shares the longest
 * common suffix "fgh". Hence it is the answer, even though the string at index
 * 2 is shorter.
 * For wordsQuery[2] = "acbfegh", strings from wordsContainer that share the
 * longest common suffix "gh" are at indices 0, 1, and 2. Among these, the
 * answer is the string at index 2 because it has the shortest length of
 * 6.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= wordsContainer.length, wordsQuery.length <= 10^4
 * 1 <= wordsContainer[i].length <= 5 * 10^3
 * 1 <= wordsQuery[i].length <= 5 * 10^3
 * wordsContainer[i] consists only of lowercase English letters.
 * wordsQuery[i] consists only of lowercase English letters.
 * Sum of wordsContainer[i].length is at most 5 * 10^5.
 * Sum of wordsQuery[i].length is at most 5 * 10^5.
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    long P = (long) 1e9 + 7;

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Map<Long, Integer> hashToIdx = new HashMap<>();
        for (int i = 0; i < wordsContainer.length; i++) {
            indexWord(wordsContainer, i, hashToIdx);
        }
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = findLongestSuffixMatch(wordsQuery[i], hashToIdx);
        }
        return ans;
    }

    void indexWord(String[] wordsContainer, int id, Map<Long, Integer> hashToIdx) {
        String s = wordsContainer[id];
        long hash = 0;
        // for empty string
        if (hashToIdx.containsKey(hash)) {
            int curId = hashToIdx.get(hash);
            if (wordsContainer[id].length() < wordsContainer[curId].length()
                    || (wordsContainer[id].length() == wordsContainer[curId].length() && id < curId)) {
                hashToIdx.put(hash, id);
            }
        } else {
            hashToIdx.put(hash, id);
        }
        for (int i = 0; i < s.length(); i++) {
            hash *= P;
            hash += s.charAt(s.length() - i - 1);
            if (hashToIdx.containsKey(hash)) {
                int curId = hashToIdx.get(hash);
                if (wordsContainer[id].length() < wordsContainer[curId].length()
                        || (wordsContainer[id].length() == wordsContainer[curId].length() && id < curId)) {
                    hashToIdx.put(hash, id);
                }
            } else {
                hashToIdx.put(hash, id);
            }
        }
    }

    int findLongestSuffixMatch(String s, Map<Long, Integer> hashToIdx) {
        long hash = 0;
        int ret = hashToIdx.get(hash);
        for (int i = 0; i < s.length(); i++) {
            hash *= P;
            hash += s.charAt(s.length() - i - 1);
            if (!hashToIdx.containsKey(hash)) {
                break;
            } else {
                ret = hashToIdx.get(hash);
            }
        }
        return ret;
    }
}
// @lc code=end
