/*
 * @lc app=leetcode id=1234 lang=java
 *
 * [1234] Replace the Substring for Balanced String
 *
 * https://leetcode.com/problems/replace-the-substring-for-balanced-string/description/
 *
 * algorithms
 * Medium (27.18%)
 * Likes:    52
 * Dislikes: 17
 * Total Accepted:    2.8K
 * Total Submissions: 10.5K
 * Testcase Example:  '"QWER"'
 *
 * You are given a string containing only 4 kinds of characters 'Q', 'W', 'E'
 * and 'R'.
 * 
 * A string is said to be balanced if each of its characters appears n/4 times
 * where n is the length of the string.
 * 
 * Return the minimum length of the substring that can be replaced with any
 * other string of the same length to make the original string s balanced.
 * 
 * Return 0 if the string is already balanced.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "QWER"
 * Output: 0
 * Explanation: s is already balanced.
 * 
 * Example 2:
 * 
 * 
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is
 * balanced.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "QQQW"
 * Output: 2
 * Explanation: We can replace the first "QQ" to "ER". 
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "QQQQ"
 * Output: 3
 * Explanation: We can replace the last 3 'Q' to make s = "QWER".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s.length is a multiple of 4
 * s contains only 'Q', 'W', 'E' and 'R'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int balancedString(String s) {
    	Map<Character, Integer> map = new HashMap<>();
    	map.put('Q', 0);
    	map.put('W', 1);
    	map.put('E', 2);
    	map.put('R', 3);
    	int n = s.length();
    	int[][] pSum = new int[4][n + 1];
    	for (int i  = 1; i <= n; i++) {
    		int id = map.get(s.charAt(i - 1));
    		for (int j = 0; j < 4; j++) {
    			pSum[j][i] = pSum[j][i - 1];
    		}
    		pSum[id][i]++;
    	}

    	int low = 0;
    	int high = n;
    	while(low + 1 < high){
    		int mid = (low + high) / 2;
    		if(isOK(s, mid, pSum)){
    			high = mid;
    		}
    		else{
    			low = mid;
    		}
    	}
    	if(isOK(s, low, pSum)){
    		return low;
    	}
    	else{
    		return high;
    	}
    }

    private boolean isOK(String s, int l, int[][] pSum){
    	// System.out.println(l);
    	int n = s.length();
    	int total = n - l;
    	for (int i = 0; i < n - l + 1; i++) {
    		int max = 0;
    		for(int j = 0; j < 4; j++){
    			max = Math.max(max, pSum[j][n] - (pSum[j][i + l] - pSum[j][i]));
    		}
    		int diff = max * 4 - total;
    		// System.out.println(i+"-"+diff);
    		if(diff <= l && (l - diff) % 4 == 0){
    			return true;
    		}
    	}
    	return false;
    }
}
// @lc code=end
