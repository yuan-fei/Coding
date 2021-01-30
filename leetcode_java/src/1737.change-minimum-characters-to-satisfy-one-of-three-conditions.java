/*
 * @lc app=leetcode id=1737 lang=java
 *
 * [1737] Change Minimum Characters to Satisfy One of Three Conditions
 *
 * https://leetcode.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions/description/
 *
 * algorithms
 * Medium (27.29%)
 * Likes:    92
 * Dislikes: 153
 * Total Accepted:    4.1K
 * Total Submissions: 15K
 * Testcase Example:  '"aba"\n"caa"'
 *
 * You are given two strings a and b that consist of lowercase letters. In one
 * operation, you can change any character in a or b to any lowercase letter.
 * 
 * Your goal is to satisfy one of the following three conditions:
 * 
 * 
 * Every letter in a is strictly less than every letter in b in the
 * alphabet.
 * Every letter in b is strictly less than every letter in a in the
 * alphabet.
 * Both a and b consist of only one distinct letter.
 * 
 * 
 * Return the minimum number of operations needed to achieve your goal.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: a = "aba", b = "caa"
 * Output: 2
 * Explanation: Consider the best way to make each condition true:
 * 1) Change b to "ccc" in 2 operations, then every letter in a is less than
 * every letter in b.
 * 2) Change a to "bbb" and b to "aaa" in 3 operations, then every letter in b
 * is less than every letter in a.
 * 3) Change a to "aaa" and b to "aaa" in 2 operations, then a and b consist of
 * one distinct letter.
 * The best way was done in 2 operations (either condition 1 or condition 3).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: a = "dabadd", b = "cda"
 * Output: 3
 * Explanation: The best way is to make condition 1 true by changing b to
 * "eee".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= a.length, b.length <= 10^5
 * a and b consist only of lowercase letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minCharacters(String a, String b) {
        return Math.min(Math.min(makeStrictLess(a, b), makeStrictLess(b, a)), makeUnique(a) + makeUnique(b));
    }

    int makeStrictLess(String a, String b){
    	int[] pref = new int[28];
    	int[] suf = new int[28];
    	for(char c : a.toCharArray()){
    		suf[c - 'a' + 1]++;
    	}
    	for(char c : b.toCharArray()){
    		pref[c - 'a' + 1]++;
    	}
    	for(int i = 1; i <= 26; i++){
    		pref[i] += pref[i - 1];
    	}

    	for(int i = 26; i >= 1; i--){
    		suf[i] += suf[i + 1];
    	}
    	int ret = 2 * 100006;
    	for(int i = 1; i <= 25; i++){
    		ret = Math.min(ret, pref[i] + suf[i + 1]);
    	}
    	return ret;
    }

    int makeUnique(String s){
    	int[] cnt = new int[26];
    	int maxCnt = 0;
    	for(char c : s.toCharArray()){
    		cnt[c - 'a']++;
    		maxCnt = Math.max(maxCnt, cnt[c - 'a']);
    	}
    	return s.length() - maxCnt;
    }
}
// @lc code=end
