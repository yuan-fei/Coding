/*
 * @lc app=leetcode id=440 lang=java
 *
 * [440] K-th Smallest in Lexicographical Order
 *
 * https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/description/
 *
 * algorithms
 * Hard (28.40%)
 * Likes:    296
 * Dislikes: 47
 * Total Accepted:    11.8K
 * Total Submissions: 41.4K
 * Testcase Example:  '13\n2'
 *
 * Given integers n and k, find the lexicographically k-th smallest integer in
 * the range from 1 to n.
 * 
 * Note: 1 ≤ k ≤ n ≤ 10^9.
 * 
 * Example:
 * 
 * Input:
 * n: 13   k: 2
 * 
 * Output:
 * 10
 * 
 * Explanation:
 * The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so
 * the second smallest number is 10.
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findKthNumber(int n, int k) {
    	String prefix = "";
    	int pos = 0;
    	String nn = "" + n;
    	boolean isTight = true;
    	// int cnt = count(nn, prefix + 1, 0, isTight && 1 == nn.charAt(pos) - '0');
    	// System.out.println(prefix + 1 +", "+cnt);
    	while(k > 0){
    		// int max = isTight ? nn.charAt(pos) - '0' : 9;
    		// System.out.println(prefix + ", "+max+", "+k);
    		for(int i = 0; i < 10; i++){
    			if(i == 0 && pos == 0){
    				continue;
    			}
    			int cnt = count(nn, prefix + i, pos, isTight && i == nn.charAt(pos) - '0');
    			// System.out.println(prefix + i+", "+cnt + "," + k);
	    		if(k > cnt){
	    			k -= cnt;
	    		}
	    		else{
	    			k--;
	    			prefix += i;
	    			isTight = isTight && i == nn.charAt(pos) - '0';
	    			break; 
	    		}
    		}
    		pos++;
    	}
    	return Integer.parseInt(prefix);
    }
    
    int count(String n, String prefix, int pos, boolean isTight){
    	// System.out.println(prefix + ", " + pos + ", "+ isTight);
    	if(Long.parseLong(prefix) > Long.parseLong(n)){
    		// System.out.println(prefix + ", " + pos + ", "+ isTight+"="+0);
    		return 0;
    	}
    	if(pos == n.length() - 1){
    		// System.out.println(prefix + ", " + pos + ", "+ isTight+"="+1);
    		return 1;
    	}
    	int cnt = 1;
    	if(isTight){
    		int d = n.charAt(pos + 1) - '0';
    		for(int i = 0; i < 10; i++){
    			cnt += count(n, prefix + i, pos + 1, d == i);
    		}
    		// System.out.println(prefix + ", " + pos + ", "+ isTight+"="+cnt);
    	}
    	else{
			cnt += 10 * count(n, prefix + "0", pos + 1, false);
			// System.out.println(prefix + ", " + pos + ", "+ isTight+"="+cnt);
    	}
    	return cnt;
    }
    
}
// @lc code=end
