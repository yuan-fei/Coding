/*
 * @lc app=leetcode id=2053 lang=java
 *
 * [2053] Kth Distinct String in an Array
 *
 * https://leetcode.com/problems/kth-distinct-string-in-an-array/description/
 *
 * algorithms
 * Easy (72.99%)
 * Likes:    39
 * Dislikes: 0
 * Total Accepted:    4.9K
 * Total Submissions: 6.7K
 * Testcase Example:  '["d","b","c","b","c","a"]\n2'
 *
 * A distinct string is a string that is present only once in an array.
 * 
 * Given an array of strings arr, and an integer k, return the k^th distinct
 * string present in arr. If there are fewer than k distinct strings, return an
 * empty string "".
 * 
 * Note that the strings are considered in the order in which they appear in
 * the array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = ["d","b","c","b","c","a"], k = 2
 * Output: "a"
 * Explanation:
 * The only distinct strings in arr are "d" and "a".
 * "d" appears 1^st, so it is the 1^st distinct string.
 * "a" appears 2^nd, so it is the 2^nd distinct string.
 * Since k == 2, "a" is returned. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = ["aaa","aa","a"], k = 1
 * Output: "aaa"
 * Explanation:
 * All strings in arr are distinct, so the 1^st string "aaa" is returned.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = ["a","b","a"], k = 3
 * Output: ""
 * Explanation:
 * The only distinct string is "b". Since there are fewer than 3 distinct
 * strings, we return an empty string "".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= arr.length <= 1000
 * 1 <= arr[i].length <= 5
 * arr[i] consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> m = new HashMap<>();
        for(String s : arr){
            m.put(s, m.getOrDefault(s, 0) + 1);
        }

        for(String s : arr){
            if(m.get(s) == 1){
                k--;
                if(k == 0){
                    return s;
                }
            }
        }
        return "";
    }
}
// @lc code=end
