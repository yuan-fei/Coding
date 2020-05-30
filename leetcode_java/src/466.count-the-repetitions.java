/*
 * @lc app=leetcode id=466 lang=java
 *
 * [466] Count The Repetitions
 *
 * https://leetcode.com/problems/count-the-repetitions/description/
 *
 * algorithms
 * Hard (28.10%)
 * Likes:    165
 * Dislikes: 133
 * Total Accepted:    10.1K
 * Total Submissions: 36K
 * Testcase Example:  '"acb"\n4\n"ab"\n2'
 *
 * Define S = [s,n] as the string S which consists of n connected strings s.
 * For example, ["abc", 3] ="abcabcabc". 
 * On the other hand, we define that string s1 can be obtained from string s2
 * if we can remove some characters from s2 such that it becomes s1. For
 * example, “abc”  can be obtained from “abdbec” based on our definition, but
 * it can not be obtained from “acbbe”.
 * You are given two non-empty strings s1 and s2 (each at most 100 characters
 * long) and two integers 0 ≤ n1 ≤ 10^6 and 1 ≤ n2 ≤ 10^6. Now consider the
 * strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer
 * M such that [S2,M] can be obtained from S1.
 * 
 * Example:
 * 
 * Input:
 * s1="acb", n1=4
 * s2="ab", n2=2
 * 
 * Return:
 * 2
 * 
 * 
 */

// @lc code=start
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0){
	        return 0;
        }
	    int[] indexr = new int[s2.length() + 1]; // index at start of each s1 block
	    int[] countr = new int[s2.length() + 1]; // count of repititions till the present s1 block
	    int index = 0;
	    int count = 0;
	    for (int i = 0; i < n1; i++) {
	        for (int j = 0; j < s1.length(); j++) {
	            if (s1.charAt(j) == s2.charAt(index)){
	                ++index;
	            }
	            if (index == s2.length()) {
	                index = 0;
	                ++count;
	            }
	        }
	        countr[i] = count;
	        indexr[i] = index;
	        for (int k = 0; k < i; k++) {
	            if (indexr[k] == index) {
	                int prev_count = countr[k];
	                int pattern_count = (countr[i] - countr[k]) * ((n1 - 1 - k) / (i - k));
	                int remain_count = countr[k + (n1 - 1 - k) % (i - k)] - countr[k];
	                return (prev_count + pattern_count + remain_count) / n2;
	            }
	        }
	    }
	    return countr[n1 - 1] / n2;
    }
}
// @lc code=end
