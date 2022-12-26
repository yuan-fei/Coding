/*
 * @lc app=leetcode id=2514 lang=java
 *
 * [2514] Count Anagrams
 *
 * https://leetcode.com/problems/count-anagrams/description/
 *
 * algorithms
 * Hard (29.95%)
 * Likes:    78
 * Dislikes: 9
 * Total Accepted:    2.9K
 * Total Submissions: 9.6K
 * Testcase Example:  '"too hot"'
 *
 * You are given a string s containing one or more words. Every consecutive
 * pair of words is separated by a single space ' '.
 * 
 * A string t is an anagram of string s if the i^th word of t is a permutation
 * of the i^th word of s.
 * 
 * 
 * For example, "acb dfe" is an anagram of "abc def", but "def cab" and "adc
 * bef" are not.
 * 
 * 
 * Return the number of distinct anagrams of s. Since the answer may be very
 * large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "too hot"
 * Output: 18
 * Explanation: Some of the anagrams of the given string are "too hot", "oot
 * hot", "oto toh", "too toh", and "too oht".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aa"
 * Output: 1
 * Explanation: There is only one anagram possible for the given string.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters and spaces ' '.
 * There is single space between consecutive words.
 * 
 * 
 */

// @lc code=start
class Solution {
    long MOD = (long)1e9 + 7;
    long[] facts;
    public int countAnagrams(String s) {
        facts = getFacts(s.length() + 1);
        String[] words = s.split(" ");
        long ans = 1;
        for(String w : words){
            int[] cnts = countDup(w);
            ans *= countAnagrams(w.length(), cnts);
            ans %= MOD;
        }
        return (int)ans;
    }

    int[] countDup(String s){
        int[] cnt = new int[26];
        for(char c : s.toCharArray()){
            cnt[c - 'a']++;
        }
        return cnt;
    }

    long countAnagrams(int n, int[] m){
        long ret = facts[n];
        for(int v : m){
            if(v > 1){
                ret *= inv((int)facts[v]);
                ret %= MOD;
            }
        }
        return ret;
    }

    long[] getFacts(int n){
        long[] facts = new long[n];
        facts[0] = 1;
        for(int i = 1; i < facts.length; i++){
            facts[i] = facts[i - 1] * i;
            facts[i] %= MOD;
        }
        return facts;
    }

    long inv(int n) {
        return modularExpRecursive(n, MOD - 2, MOD);
    }

    long modularExpRecursive(int base, long exp, long m) {
        if (exp == 0) {
            return 1;
        } else if (exp % 2 == 1) {
            long l = base * modularExpRecursive(base, exp - 1, m);
            return l % m;
        } else {
            long t = modularExpRecursive(base, exp / 2, m);
            return (t * t) % m;
        }
    }
}
// @lc code=end
