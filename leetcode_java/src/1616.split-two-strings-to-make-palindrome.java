/*
 * @lc app=leetcode id=1616 lang=java
 *
 * [1616] Split Two Strings to Make Palindrome
 *
 * https://leetcode.com/problems/split-two-strings-to-make-palindrome/description/
 *
 * algorithms
 * Medium (36.85%)
 * Likes:    145
 * Dislikes: 109
 * Total Accepted:    8.3K
 * Total Submissions: 22.5K
 * Testcase Example:  '"x"\n"y"'
 *
 * You are given two strings a and b of the same length. Choose an index and
 * split both strings at the same index, splitting a into two strings: aprefix
 * and asuffix where a = aprefix + asuffix, and splitting b into two strings:
 * bprefix and bsuffix where b = bprefix + bsuffix. Check if aprefix + bsuffix
 * or bprefix + asuffix forms a palindrome.
 * 
 * When you split a string s into sprefix and ssuffix, either ssuffix or
 * sprefix is allowed to be empty. For example, if s = "abc", then "" + "abc",
 * "a" + "bc", "ab" + "c" , and "abc" + "" are valid splits.
 * 
 * Return true if it is possible to form a palindrome string, otherwise return
 * false.
 * 
 * Notice that x + y denotes the concatenation of strings x and y.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: a = "x", b = "y"
 * Output: true
 * Explaination: If either a or b are palindromes the answer is true since you
 * can split in the following way:
 * aprefix = "", asuffix = "x"
 * bprefix = "", bsuffix = "y"
 * Then, aprefix + bsuffix = "" + "y" = "y", which is a palindrome.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: a = "abdef", b = "fecab"
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: a = "ulacfd", b = "jizalu"
 * Output: true
 * Explaination: Split them at index 3:
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * Then, aprefix + bsuffix = "ula" + "alu" = "ulaalu", which is a palindrome.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: a = "xbdef", b = "xecab"
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= a.length, b.length <= 10^5
 * a.length == b.length
 * a and b consist of lowercase English letters
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkPalindromeFormation(String a, String b) {
    	int n = a.length();
        boolean[] pa = new boolean[n];
        boolean[] pb = new boolean[n];
        preCheckalindrome(a, pa, 0, n - 1);
        preCheckalindrome(b, pb, 0, n - 1);
        return pa[0] || pb[0] || check(a, b, pa, pb) || check(b, a, pb, pa);
    }

    void preCheckalindrome(String s, boolean[] isPalindrome, int start, int end){
    	if(start < end){
    		preCheckalindrome(s, isPalindrome, start + 1, end - 1);
    		isPalindrome[start] = (s.charAt(start) == s.charAt(end)) && isPalindrome[start + 1];
    	}
    	else{
    		isPalindrome[start] = true;
    	}
    }

    boolean check(String a, String b, boolean[] pa, boolean[] pb){
    	int n = a.length();
    	for(int i = 0; i < n; i++){
    		if(a.charAt(i) == b.charAt(n - 1 - i)){
    			if(pa[i + 1] || pb[i + 1]){
    				// System.out.println(a + ": " + i);
    				return true;
    			}
    		}
    		else{
    			return false;
    		}
    	}
    	return false;
    }


}
// @lc code=end
