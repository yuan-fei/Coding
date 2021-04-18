/*
 * @lc app=leetcode id=1830 lang=java
 *
 * [1830] Minimum Number of Operations to Make String Sorted
 *
 * https://leetcode.com/problems/minimum-number-of-operations-to-make-string-sorted/description/
 *
 * algorithms
 * Hard (38.02%)
 * Likes:    51
 * Dislikes: 43
 * Total Accepted:    719
 * Total Submissions: 1.9K
 * Testcase Example:  '"cba"'
 *
 * You are given a string s (0-indexed)​​​​​​. You are asked to perform the
 * following operation on s​​​​​​ until you get a sorted string:
 * 
 * 
 * Find the largest index i such that 1 <= i < s.length and s[i] < s[i -
 * 1].
 * Find the largest index j such that i <= j < s.length and s[k] < s[i - 1] for
 * all the possible values of k in the range [i, j] inclusive.
 * Swap the two characters at indices i - 1​​​​ and j​​​​​.
 * Reverse the suffix starting at index i​​​​​​.
 * 
 * 
 * Return the number of operations needed to make the string sorted. Since the
 * answer can be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "cba"
 * Output: 5
 * Explanation: The simulation goes as follows:
 * Operation 1: i=2, j=2. Swap s[1] and s[2] to get s="cab", then reverse the
 * suffix starting at 2. Now, s="cab".
 * Operation 2: i=1, j=2. Swap s[0] and s[2] to get s="bac", then reverse the
 * suffix starting at 1. Now, s="bca".
 * Operation 3: i=2, j=2. Swap s[1] and s[2] to get s="bac", then reverse the
 * suffix starting at 2. Now, s="bac".
 * Operation 4: i=1, j=1. Swap s[0] and s[1] to get s="abc", then reverse the
 * suffix starting at 1. Now, s="acb".
 * Operation 5: i=2, j=2. Swap s[1] and s[2] to get s="abc", then reverse the
 * suffix starting at 2. Now, s="abc".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aabaa"
 * Output: 2
 * Explanation: The simulation goes as follows:
 * Operation 1: i=3, j=4. Swap s[2] and s[4] to get s="aaaab", then reverse the
 * substring starting at 3. Now, s="aaaba".
 * Operation 2: i=4, j=4. Swap s[3] and s[4] to get s="aaaab", then reverse the
 * substring starting at 4. Now, s="aaaab".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "cdbea"
 * Output: 63
 * 
 * Example 4:
 * 
 * 
 * Input: s = "leetcodeleetcodeleetcode"
 * Output: 982157772
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 3000
 * s​​​​​​ consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int makeStringSorted(String s) {
    	int n = s.length();
        C = new int[n + 1][n + 1];
        comn(n);
        // System.out.println(Arrays.deepToString(C));
        int[] cnt = new int[26];
        for(char c : s.toCharArray()){
        	cnt[c - 'a']++;
        }
        return (int)rec(s, 0, 1, cnt);
    }

    long rec(String s, int pos, int isTight, int[] cnt){
    	long ret = 0;
    	if(pos == s.length()){
    		return 0;
    	}
    	int max = (isTight == 1) ? (s.charAt(pos) - 'a') : 25;

    	for(int c = 0; c <= max; c++){
    		if(c != max){
    			if(cnt[c] > 0){
    				cnt[c]--;
    				ret += calc(cnt, s.length() - pos - 1);
    				ret %= MOD;
    				cnt[c]++;
    			}
    		}
    		else{
    			cnt[c]--;
    			ret += rec(s, pos + 1, isTight, cnt);
    			ret %= MOD;
    			cnt[c]++;
    		}
    	}
    	return ret;
    }

    int[][] C;
    int MOD = (int)1e9 + 7;
    void comn(int n){
    	for(int i = 0; i <= n; i++){
    		C[0][i] = 1;
    	}
    	for(int i = 1; i <= n; i++){
    		for(int j = 1; j <= i; j++){
    			C[j][i] = (C[j][i - 1] + C[j - 1][i - 1]) % MOD;
    		}
    	}
    }

    long calc(int[] cnt, int total){
    	long ret = 1;
    	for(int x : cnt){
    		ret *= C[x][total];
    		ret %= MOD;
    		total -= x;
    	}
    	// System.out.println(Arrays.toString(cnt) + ", " + total + "=" + ret);
    	return ret;
    }
}
// @lc code=end
