/*
 * @lc app=leetcode id=87 lang=java
 *
 * [87] Scramble String
 *
 * https://leetcode.com/problems/scramble-string/description/
 *
 * algorithms
 * Hard (32.03%)
 * Total Accepted:    94.3K
 * Total Submissions: 294.2K
 * Testcase Example:  '"great"\n"rgeat"'
 *
 * Given a string s1, we may represent it as a binary tree by partitioning it
 * to two non-empty substrings recursively.
 * 
 * Below is one possible representation of s1 = "great":
 * 
 * 
 * ⁠   great
 * ⁠  /    \
 * ⁠ gr    eat
 * ⁠/ \    /  \
 * g   r  e   at
 * ⁠          / \
 * ⁠         a   t
 * 
 * 
 * To scramble the string, we may choose any non-leaf node and swap its two
 * children.
 * 
 * For example, if we choose the node "gr" and swap its two children, it
 * produces a scrambled string "rgeat".
 * 
 * 
 * ⁠   rgeat
 * ⁠  /    \
 * ⁠ rg    eat
 * ⁠/ \    /  \
 * r   g  e   at
 * ⁠          / \
 * ⁠         a   t
 * 
 * 
 * We say that "rgeat" is a scrambled string of "great".
 * 
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it
 * produces a scrambled string "rgtae".
 * 
 * 
 * ⁠   rgtae
 * ⁠  /    \
 * ⁠ rg    tae
 * ⁠/ \    /  \
 * r   g  ta  e
 * ⁠      / \
 * ⁠     t   a
 * 
 * 
 * We say that "rgtae" is a scrambled string of "great".
 * 
 * Given two strings s1 and s2 of the same length, determine if s2 is a
 * scrambled string of s1.
 * 
 * Example 1:
 * 
 * 
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 * 
 */
class Solution {
	int[][] cnt1;
    int[][] cnt2;
    char[] s1;
    char[] s2;
    int n;
    public boolean isScramble(String s1, String s2) {
    	n = s1.length();
        cnt1 = new int[n+1][26];
        cnt2 = new int[n+1][26];
		char[] letters1 = s1.toCharArray();
        char[] letters2 = s2.toCharArray();
        for(int i = 1; i <= n; i++){
        	for(int j = 0; j < 26; j++){
        		cnt1[i][j] += cnt1[i-1][j];
        		if(letters1[i-1] - 'a'== j){
        			cnt1[i][j]++;
        		}
        		cnt2[i][j] += cnt2[i-1][j];
        		if(letters2[i-1] - 'a'== j){
        			cnt2[i][j]++;
        		}
        	}
        }
        this.s1=letters1;
        this.s2=letters2;
        cache = new int[n][n][n][n];
        return isScramble(0, n-1, 0, n-1);
    }

    private boolean isScramble(int l1, int r1, int l2, int r2) {
    	if(l1==r1){
    		return s1[l1]==s2[l2];
    	}
    	int n = s1.length;
    	for(int m = 1; m <= r1-l1; m++){
    		if(isPermutation(l1,l1+m-1,l2,l2+m-1) && isPermutation(l1+m,r1,l2+m,r2) && isScramble(l1,l1+m-1,l2,l2+m-1) && isScramble(l1+m,r1,l2+m,r2)){
    			return true;
    		}
    		if(isPermutation(l1,l1+m-1,r2-m+1,r2) && isPermutation(l1+m,r1,l2,r2-m) && isScramble(l1,l1+m-1,r2-m+1,r2) && isScramble(l1+m,r1,l2,r2-m)){
    			return true;
    		}
    	}
    	return false;
    }

    int[][][][] cache;
    private boolean isPermutation(int l1, int r1, int l2, int r2){
    	if(cache[l1][r1][l2][r2]==0){
	    	for(int j = 0; j < 26; j++){
	    		if(cnt1[r1+1][j] - cnt1[l1][j]!=cnt2[r2+1][j] - cnt2[l2][j]){
	    			cache[l1][r1][l2][r2]=-1;
	    			return false;
	    		}
	    	}	
	    	cache[l1][r1][l2][r2]=1;
	    	return true;
    	}
    	
    	if(cache[l1][r1][l2][r2]==1){
    		return true;	
    	}
    	else{
    		return false;
    	}
    	
    }
}
