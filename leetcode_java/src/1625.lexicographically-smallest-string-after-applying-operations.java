/*
 * @lc app=leetcode id=1625 lang=java
 *
 * [1625] Lexicographically Smallest String After Applying Operations
 *
 * https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/description/
 *
 * algorithms
 * Medium (58.18%)
 * Likes:    53
 * Dislikes: 115
 * Total Accepted:    3.2K
 * Total Submissions: 5.5K
 * Testcase Example:  '"5525"\n9\n2'
 *
 * You are given a string s of even length consisting of digits from 0 to 9,
 * and two integers a and b.
 * 
 * You can apply either of the following two operations any number of times and
 * in any order on s:
 * 
 * 
 * Add a to all odd indices of s (0-indexed). Digits post 9 are cycled back to
 * 0. For example, if s = "3456" and a = 5, s becomes "3951".
 * Rotate s to the right by b positions. For example, if s = "3456" and b = 1,
 * s becomes "6345".
 * 
 * 
 * Return the lexicographically smallest string you can obtain by applying the
 * above operations any number of times on s.
 * 
 * A string a is lexicographically smaller than a string b (of the same length)
 * if in the first position where a and b differ, string a has a letter that
 * appears earlier in the alphabet than the corresponding letter in b. For
 * example, "0158" is lexicographically smaller than "0190" because the first
 * position they differ is at the third letter, and '5' comes before '9'.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "5525", a = 9, b = 2
 * Output: "2050"
 * Explanation: We can apply the following operations:
 * Start:  "5525"
 * Rotate: "2555"
 * Add:    "2454"
 * Add:    "2353"
 * Rotate: "5323"
 * Add:    "5222"
 * ​​​​​​​Add:    "5121"
 * ​​​​​​​Rotate: "2151"
 * ​​​​​​​Add:    "2050"​​​​​​​​​​​​
 * There is no way to obtain a string that is lexicographically smaller then
 * "2050".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "74", a = 5, b = 1
 * Output: "24"
 * Explanation: We can apply the following operations:
 * Start:  "74"
 * Rotate: "47"
 * ​​​​​​​Add:    "42"
 * ​​​​​​​Rotate: "24"​​​​​​​​​​​​
 * There is no way to obtain a string that is lexicographically smaller then
 * "24".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "0011", a = 4, b = 2
 * Output: "0011"
 * Explanation: There are no sequence of operations that will give us a
 * lexicographically smaller string than "0011".
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "43987654", a = 7, b = 3
 * Output: "00553311"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= s.length <= 100
 * s.length is even.
 * s consists of digits from 0 to 9 only.
 * 1 <= a <= 9
 * 1 <= b <= s.length - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public String findLexSmallestString(String s, int a, int b) {
    	int n = s.length();
        int offset = gcd(n, b);
        String ans = s;
        for(int i = 0; i < n / offset; i++){
        	s = s.substring(offset) + s.substring(0, offset);
        	String t = getMin(s, a, 1);
        	if(offset % 2 == 1){
        		t = getMin(t, a, 0);
        	}
        	// System.out.println(t);
        	if(t.compareTo(ans) < 0){
        		ans = t;
        	}
        }
        return ans;
    }

    String getMin(String s, int a, int offset){
    	char[] chars = s.toCharArray();
    	int d = chars[offset] - '0';
    	int min = d;
    	int minOp = 0;
    	int op = 0;
    	while(true){
    		d += a;
    		d %= 10;
    		op++;
    		if(d < min){
    			min = d;
    			minOp = op;
    		}
    		if(d == chars[offset] - '0'){
    			break;
    		}
    	}
    	for(int i = offset; i < s.length(); i += 2){
    		int c = chars[i] - '0';
    		chars[i] = (char)('0' + (c + minOp * a) % 10);
    	}
    	return String.valueOf(chars);
    }

    int gcd(int a, int b){
    	if(b == 0){
    		return a;
    	}
    	else{
    		return gcd(b, a % b);
    	}
    }
}
// @lc code=end
