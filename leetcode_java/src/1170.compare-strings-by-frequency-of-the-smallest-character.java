/*
 * @lc app=leetcode id=1170 lang=java
 *
 * [1170] Compare Strings by Frequency of the Smallest Character
 *
 * https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character/description/
 *
 * algorithms
 * Easy (63.87%)
 * Total Accepted:    3.9K
 * Total Submissions: 6.1K
 * Testcase Example:  '["cbd"]\n["zaaaz"]'
 *
 * Let's define a function f(s) over a non-empty string s, which calculates the
 * frequency of the smallest character in s. For example, if s = "dcce" then
 * f(s) = 2 because the smallest character is "c" and its frequency is 2.
 * 
 * Now, given string arrays queries and words, return an integer array answer,
 * where each answer[i] is the number of words such that f(queries[i]) < f(W),
 * where W is a word in words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: queries = ["cbd"], words = ["zaaaz"]
 * Output: [1]
 * Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so
 * f("cbd") < f("zaaaz").
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * Output: [1,2]
 * Explanation: On the first query only f("bbb") < f("aaaa"). On the second
 * query both f("aaa") and f("aaaa") are both > f("cc").
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j], words[i][j] are English lowercase letters.
 * 
 * 
 */
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
    	int[] len = new int[12];
        for (String w: words) {
        	len[f(w)]++;
        }
        for (int i = len.length-2; i > 0; i--) {
        	len[i] += len[i+1];
        }
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
        	ans[i] = len[f(queries[i])+1];
        }
        return ans;
    }

    int f(String s){
    	char[] c = s.toCharArray();
    	Arrays.sort(c);
    	int i = 0;
    	while(i<c.length && c[i]==c[0]){
    		i++;
    	}
    	return i;
    }
}
