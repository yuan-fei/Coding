/*
 * @lc app=leetcode id=2559 lang=java
 *
 * [2559] Count Vowel Strings in Ranges
 *
 * https://leetcode.com/problems/count-vowel-strings-in-ranges/description/
 *
 * algorithms
 * Medium (47.30%)
 * Likes:    74
 * Dislikes: 2
 * Total Accepted:    10.5K
 * Total Submissions: 22.3K
 * Testcase Example:  '["aba","bcb","ece","aa","e"]\n[[0,2],[1,4],[1,1]]'
 *
 * You are given a 0-indexed array of strings words and a 2D array of integers
 * queries.
 * 
 * Each query queries[i] = [li, ri] asks us to find the number of strings
 * present in the range li to ri (both inclusive) of words that start and end
 * with a vowel.
 * 
 * Return an array ans of size queries.length, where ans[i] is the answer to
 * the i^th query.
 * 
 * Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
 * Output: [2,3,0]
 * Explanation: The strings starting and ending with a vowel are "aba", "ece",
 * "aa" and "e".
 * The answer to the query [0,2] is 2 (strings "aba" and "ece").
 * to query [1,4] is 3 (strings "ece", "aa", "e").
 * to query [1,1] is 0.
 * We return [2,3,0].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
 * Output: [3,2,1]
 * Explanation: Every string satisfies the conditions, so we return [3,2,1].
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 10^5
 * 1 <= words[i].length <= 40
 * words[i] consists only of lowercase English letters.
 * sum(words[i].length) <= 3 * 10^5
 * 1 <= queries.length <= 10^5
 * 0 <= li <= ri < words.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] pSum = new int[n + 1];
        for(int i = 0; i < n; i++){
            pSum[i + 1] = pSum[i];
            if(isVowel(words[i].charAt(0)) && isVowel(words[i].charAt(words[i].length() - 1))){
                pSum[i + 1] += 1;
            }
        }
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            ans[i] = pSum[queries[i][1] + 1] - pSum[queries[i][0]];
        }
        return ans;
    }

    boolean isVowel(char c){
        return "aeiou".indexOf(c) >= 0;
    }
}
// @lc code=end
