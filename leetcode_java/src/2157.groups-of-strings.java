/*
 * @lc app=leetcode id=2157 lang=java
 *
 * [2157] Groups of Strings
 *
 * https://leetcode.com/problems/groups-of-strings/description/
 *
 * algorithms
 * Hard (24.45%)
 * Likes:    236
 * Dislikes: 35
 * Total Accepted:    5.3K
 * Total Submissions: 21.6K
 * Testcase Example:  '["a","b","ab","cde"]'
 *
 * You are given a 0-indexed array of strings words. Each string consists of
 * lowercase English letters only. No letter occurs more than once in any
 * string of words.
 * 
 * Two strings s1 and s2 are said to be connected if the set of letters of s2
 * can be obtained from the set of letters of s1 by any one of the following
 * operations:
 * 
 * 
 * Adding exactly one letter to the set of the letters of s1.
 * Deleting exactly one letter from the set of the letters of s1.
 * Replacing exactly one letter from the set of the letters of s1 with any
 * letter, including itself.
 * 
 * 
 * The array words can be divided into one or more non-intersecting groups. A
 * string belongs to a group if any one of the following is true:
 * 
 * 
 * It is connected to at least one other string of the group.
 * It is the only string present in the group.
 * 
 * 
 * Note that the strings in words should be grouped in such a manner that a
 * string belonging to a group cannot be connected to a string present in any
 * other group. It can be proved that such an arrangement is always unique.
 * 
 * Return an array ans of size 2 where:
 * 
 * 
 * ans[0] is the maximum number of groups words can be divided into, and
 * ans[1] is the size of the largest group.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["a","b","ab","cde"]
 * Output: [2,3]
 * Explanation:
 * - words[0] can be used to obtain words[1] (by replacing 'a' with 'b'), and
 * words[2] (by adding 'b'). So words[0] is connected to words[1] and words[2].
 * - words[1] can be used to obtain words[0] (by replacing 'b' with 'a'), and
 * words[2] (by adding 'a'). So words[1] is connected to words[0] and words[2].
 * - words[2] can be used to obtain words[0] (by deleting 'b'), and words[1]
 * (by deleting 'a'). So words[2] is connected to words[0] and words[1].
 * - words[3] is not connected to any string in words.
 * Thus, words can be divided into 2 groups ["a","b","ab"] and ["cde"]. The
 * size of the largest group is 3.  
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["a","ab","abc"]
 * Output: [1,3]
 * Explanation:
 * - words[0] is connected to words[1].
 * - words[1] is connected to words[0] and words[2].
 * - words[2] is connected to words[1].
 * Since all strings are connected to each other, they should be grouped
 * together.
 * Thus, the size of the largest group is 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 2 * 10^4
 * 1 <= words[i].length <= 26
 * words[i] consists of lowercase English letters only.
 * No letter occurs more than once in words[i].
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] groupStrings(String[] words) {
        HashMap<Integer, Integer> counts = new HashMap<>(), indices = new HashMap<>();
        int n = words.length, output = 0;
        int[] masks = new int[n], uf = new int[n];
        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) masks[i] |= 1 << (c - 'a');
            indices.put(masks[i], i);
            uf[i] = i;
        };
        for (int i = 0; i < n; i++) {
            int mask = masks[i];
            for (int j = 0; j < 26; j++) {
                int num = (mask & 1 << j) == 0 ? mask + (1 << j) : mask - (1 << j);
                if (indices.containsKey(num)) uf[find(i, uf)] = uf[find(indices.get(num), uf)];
            }
            for (int j = 0; j < 26; j++) if ((mask & 1 << j) > 0) for (int k = 0; k < 26; k++) if (k == j || (mask & 1 << k) == 0) {
                int num = mask - (1 << j) + (1 << k);
                if (indices.containsKey(num)) uf[find(i, uf)] = uf[find(indices.get(num), uf)];
            }
        }
        for (int i = 0; i < n; i++) {
            find(i, uf);
            counts.put(uf[i], counts.getOrDefault(uf[i], 0) + 1);
            output = Math.max(counts.get(uf[i]), output);
        }
        return new int[] {counts.size(), output};
    }

    private int find(int i, int[] uf) {
        if (i == uf[i]) return i;
        return uf[i] = find(uf[i], uf);
    }
}
// @lc code=end
