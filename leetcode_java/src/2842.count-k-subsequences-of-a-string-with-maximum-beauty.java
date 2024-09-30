/*
 * @lc app=leetcode id=2842 lang=java
 *
 * [2842] Count K-Subsequences of a String With Maximum Beauty
 *
 * https://leetcode.com/problems/count-k-subsequences-of-a-string-with-maximum-beauty/description/
 *
 * algorithms
 * Hard (28.30%)
 * Likes:    320
 * Dislikes: 32
 * Total Accepted:    11.8K
 * Total Submissions: 40.6K
 * Testcase Example:  '"bcca"\n2'
 *
 * You are given a string s and an integer k.
 * 
 * A k-subsequence is a subsequence of s, having length k, and all its
 * characters are unique, i.e., every character occurs once.
 * 
 * Let f(c) denote the number of times the character c occurs in s.
 * 
 * The beauty of a k-subsequence is the sum of f(c) for every character c in
 * the k-subsequence.
 * 
 * For example, consider s = "abbbdd" and k = 2:
 * 
 * 
 * f('a') = 1, f('b') = 3, f('d') = 2
 * Some k-subsequences of s are:
 * 
 * "abbbdd" -> "ab" having a beauty of f('a') + f('b') = 4
 * "abbbdd" -> "ad" having a beauty of f('a') + f('d') = 3
 * "abbbdd" -> "bd" having a beauty of f('b') + f('d') = 5
 * 
 * 
 * 
 * 
 * Return an integer denoting the number of k-subsequences whose beauty is the
 * maximum among all k-subsequences. Since the answer may be too large, return
 * it modulo 10^9 + 7.
 * 
 * A subsequence of a string is a new string formed from the original string by
 * deleting some (possibly none) of the characters without disturbing the
 * relative positions of the remaining characters.
 * 
 * Notes
 * 
 * 
 * f(c) is the number of times a character c occurs in s, not a
 * k-subsequence.
 * Two k-subsequences are considered different if one is formed by an index
 * that is not present in the other. So, two k-subsequences may form the same
 * string.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "bcca", k = 2
 * Output: 4
 * Explanation: From s we have f('a') = 1, f('b') = 1, and f('c') = 2.
 * The k-subsequences of s are: 
 * bcca having a beauty of f('b') + f('c') = 3 
 * bcca having a beauty of f('b') + f('c') = 3 
 * bcca having a beauty of f('b') + f('a') = 2 
 * bcca having a beauty of f('c') + f('a') = 3
 * bcca having a beauty of f('c') + f('a') = 3 
 * There are 4 k-subsequences that have the maximum beauty, 3. 
 * Hence, the answer is 4. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abbcd", k = 4
 * Output: 2
 * Explanation: From s we have f('a') = 1, f('b') = 2, f('c') = 1, and f('d') =
 * 1. 
 * The k-subsequences of s are: 
 * abbcd having a beauty of f('a') + f('b') + f('c') + f('d') = 5
 * abbcd having a beauty of f('a') + f('b') + f('c') + f('d') = 5 
 * There are 2 k-subsequences that have the maximum beauty, 5. 
 * Hence, the answer is 2. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 2 * 10^5
 * 1 <= k <= s.length
 * s consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    long MOD = (long)1e9 + 7;
    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int[] freqs = new int[26];
        for(char c : s.toCharArray()){
            freqs[c - 'a']++;
        }
        long ans = 1;
        TreeMap<Integer, Integer> freqToCnt = new TreeMap<>(Comparator.reverseOrder());
        for(int f : freqs){
            if(f > 0){
                freqToCnt.put(f, freqToCnt.getOrDefault(f, 0) + 1);    
            }
        }
        for(int freq : freqToCnt.keySet()){
            int cnt = freqToCnt.get(freq);
            int requiredCnt = Math.min(cnt, k);
            for(int j = 0; j < requiredCnt; j++){
                ans *= freq;
                ans %= MOD;
            }
            if(cnt > k){
                ans *= choose(cnt, k);
                ans %= MOD;
            }
            k -= requiredCnt;
            if(k == 0){
                break;
            }
        }
        return k == 0 ? (int)ans : 0;
    }

    long choose(int n, int k){
        long ans = 1;
        for(int i = 0; i < k; i++){
            ans *= n - i;
            ans /= i + 1;
        }
        return ans;
    }
}
// @lc code=end
