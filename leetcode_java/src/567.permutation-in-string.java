/*
 * [567] Permutation in String
 *
 * https://leetcode.com/problems/permutation-in-string/description/
 *
 * algorithms
 * Medium (36.16%)
 * Total Accepted:    46.4K
 * Total Submissions: 121K
 * Testcase Example:  '"ab"\n"eidbaooo"'
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains
 * the permutation of s1. In other words, one of the first string's
 * permutations is the substring of the second string.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 * 
 * 
 */
/**
note: fixed sliding window
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
    	int m = s1.length();
    	int n = s2.length();
    	if(m > n){
    		return false;
    	}
    	int[] target = new int[26];	
    	int[] window = new int[26];	

    	int i = 0;
    	for(; i < m; i++){
    		target[s1.charAt(i)-'a']++;
    		window[s2.charAt(i)-'a']++;
    	}
    	for(; i < n; i++){
    		if(equals(target, window)){
    			return true;
    		}
    		window[s2.charAt(i)-'a']++;
    		window[s2.charAt(i - s1.length())-'a']--;
    	}
    	if(equals(target, window)){
    		return true;
    	}
    	return false;
    }

    private boolean equals(int[] s1, int[] s2){
    	for(int i = 0 ; i< s1.length; i++){
    		if(s1[i]!=s2[i]){
    			return false;
    		}
    	}
    	return true;
    }
}
