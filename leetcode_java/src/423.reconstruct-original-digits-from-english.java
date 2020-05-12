/*
 * @lc app=leetcode id=423 lang=java
 *
 * [423] Reconstruct Original Digits from English
 *
 * https://leetcode.com/problems/reconstruct-original-digits-from-english/description/
 *
 * algorithms
 * Medium (46.67%)
 * Likes:    159
 * Dislikes: 534
 * Total Accepted:    23.1K
 * Total Submissions: 49.5K
 * Testcase Example:  '"owoztneoer"'
 *
 * Given a non-empty string containing an out-of-order English representation
 * of digits 0-9, output the digits in ascending order.
 * 
 * Note:
 * 
 * Input contains only lowercase English letters.
 * Input is guaranteed to be valid and can be transformed to its original
 * digits. That means invalid inputs such as "abc" or "zerone" are not
 * permitted.
 * Input length is less than 50,000.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: "owoztneoer"
 * 
 * Output: "012"
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "fviefuro"
 * 
 * Output: "45"
 * 
 * 
 */

// @lc code=start
class Solution {
	int[][] pattern = new int[10][26];
	String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public String originalDigits(String s) {
    	for(int i = 0; i < 10; i++){
    		String d = digits[i];
    		for(char c : d.toCharArray()){
    			pattern[i][c -'a']++;
    		}
    	}
    	int[] t = new int[26];
    	int[] cnt = new int[10];
    	for (char c : s.toCharArray()) {
    		t[c - 'a']++;
    		switch(c){
    			case 'z': cnt[0]++; break;
    			case 'o': cnt[1]++; break;
    			case 'w': cnt[2]++; break;
    			case 'r': cnt[3]++; break;
    			case 'u': cnt[4]++; break;
    			case 'f': cnt[5]++; break;
    			case 'x': cnt[6]++; break;
    			case 's': cnt[7]++; break;
    			case 'g': cnt[8]++; break;
    			case 'i': cnt[9]++; break;
    		}
    	}
    	// System.out.println(Arrays.toString(cnt));
    	cnt[1] -= cnt[0] + cnt[2] + cnt[4];
    	cnt[3] -= cnt[4] + cnt[0];
    	cnt[5] -= cnt[4];
    	cnt[7] -= cnt[6];
    	cnt[9] -= cnt[5] + cnt[6] + cnt[8];
    	String ret = "";
    	for(int i = 0; i < 10; i++){
    		for(int c = 0; c < cnt[i]; c++){
    			ret += i;
    		}
    	}
    	return ret;
    }
    

	void takeDigit(int[] cnt, int d, int times){
		for(int i = 0; i < cnt.length; i++){
			cnt[i] -= times * pattern[d][i];
		}
	}
}
// @lc code=end
