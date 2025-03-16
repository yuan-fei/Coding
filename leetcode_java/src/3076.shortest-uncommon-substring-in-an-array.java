/*
 * @lc app=leetcode id=3076 lang=java
 *
 * [3076] Shortest Uncommon Substring in an Array
 *
 * https://leetcode.com/problems/shortest-uncommon-substring-in-an-array/description/
 *
 * algorithms
 * Medium (46.93%)
 * Likes:    143
 * Dislikes: 24
 * Total Accepted:    26.7K
 * Total Submissions: 56.4K
 * Testcase Example:  '["cab","ad","bad","c"]'
 *
 * You are given an array arr of size n consisting of non-empty strings.
 * 
 * Find a string array answer of size n such that:
 * 
 * 
 * answer[i] is the shortest substring of arr[i] that does not occur as a
 * substring in any other string in arr. If multiple such substrings exist,
 * answer[i] should be the lexicographically smallest. And if no such substring
 * exists, answer[i] should be an empty string.
 * 
 * 
 * Return the array answer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = ["cab","ad","bad","c"]
 * Output: ["ab","","ba",""]
 * Explanation: We have the following:
 * - For the string "cab", the shortest substring that does not occur in any
 * other string is either "ca" or "ab", we choose the lexicographically smaller
 * substring, which is "ab".
 * - For the string "ad", there is no substring that does not occur in any
 * other string.
 * - For the string "bad", the shortest substring that does not occur in any
 * other string is "ba".
 * - For the string "c", there is no substring that does not occur in any other
 * string.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = ["abc","bcd","abcd"]
 * Output: ["","","abcd"]
 * Explanation: We have the following:
 * - For the string "abc", there is no substring that does not occur in any
 * other string.
 * - For the string "bcd", there is no substring that does not occur in any
 * other string.
 * - For the string "abcd", the shortest substring that does not occur in any
 * other string is "abcd".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == arr.length
 * 2 <= n <= 100
 * 1 <= arr[i].length <= 20
 * arr[i] consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public String[] shortestSubstrings(String[] arr) {
        List<List<String>> substrings = Arrays.stream(arr).map(this::getSubStrings).collect(Collectors.toList());
        Map<String, Integer> subStringSet = substrings.stream().flatMap(x -> x.stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.summingInt(_ -> 1)));

        // System.out.println(substrings);
        // System.out.println(subStringSet);
        return (String[]) substrings.stream()
                .map((List<String> l) -> l.stream().filter(s -> subStringSet.get(s) == 1).findFirst().orElse(""))
                .toArray(String[]::new);
    }

    List<String> getSubStrings(String s) {
        Set<String> substrSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                substrSet.add(s.substring(i, j));
            }
        }
        List<String> ret = new ArrayList<>(substrSet);
        Collections.sort(ret, Comparator.comparing((String x) -> x.length()).thenComparing(Comparator.naturalOrder()));

        return ret;
    }
}
// @lc code=end
