/*
 * @lc app=leetcode id=1611 lang=java
 *
 * [1611] Minimum One Bit Operations to Make Integers Zero
 *
 * https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/description/
 *
 * algorithms
 * Hard (45.22%)
 * Likes:    36
 * Dislikes: 39
 * Total Accepted:    1.1K
 * Total Submissions: 2.4K
 * Testcase Example:  '0'
 *
 * Given an integer n, you must transform it into 0 using the following
 * operations any number of times:
 * 
 * 
 * Change the rightmost (0^th) bit in the binary representation of n.
 * Change the i^th bit in the binary representation of n if the (i-1)^th bit is
 * set to 1 and the (i-2)^th through 0^th bits are set to 0.
 * 
 * 
 * Return the minimum number of operations to transform n into 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 0
 * Output: 0
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3
 * Output: 2
 * Explanation: The binary representation of 3 is "11".
 * "11" -> "01" with the 2nd operation since the 0th bit is 1.
 * "01" -> "00" with the 1st operation.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 6
 * Output: 4
 * Explanation: The binary representation of 6 is "110".
 * "110" -> "010" with the 2nd operation since the 1st bit is 1 and 0th through
 * 0th bits are 0.
 * "010" -> "011" with the 1st operation.
 * "011" -> "001" with the 2nd operation since the 0th bit is 1.
 * "001" -> "000" with the 1st operation.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: n = 9
 * Output: 14
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: n = 333
 * Output: 393
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumOneBitOperations(int n) {
        return make0(n);
    }

    Map<Integer, Integer> cache0 = new HashMap<>();

    int make0(int n){
    	if(n == 0){
    		return 0;
    	}
    	if(!cache0.containsKey(n)){
    		int cnt = 0;
    		int target = Integer.highestOneBit(n);
	    	int newTarget = target >> 1;
	    	cnt += make1(n & (target - 1), newTarget);
	    	cnt++;
	    	cnt += make0(newTarget);
	    	cache0.put(n, cnt);
    	}
    	return cache0.get(n);
    }

    /* n = xxxxx, make n -> 10000
     */
    int make1(int n, int target){
    	if(n == target){
    		return 0;
    	}

    	if((n & target) != 0){
    		// 1xxxx
    		return make0(n & (target - 1));
    	}
    	else{
    		// 0xxxx
    		int cnt = 0;
	    	// in order to make n: 0xxxx -> 11000, make xxxx -> 1000 first
	    	int newTarget = target >> 1;
	    	cnt += make1(n & (target - 1), newTarget);
	    	// make n 01000 -> 11000 by rule 2
	    	cnt++;
	    	cnt += make0(newTarget);
	    	return cnt;
    	}
    	
    }
}
// @lc code=end
