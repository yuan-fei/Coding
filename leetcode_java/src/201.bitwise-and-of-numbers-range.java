/*
 * @lc app=leetcode id=201 lang=java
 *
 * [201] Bitwise AND of Numbers Range
 *
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/description/
 *
 * algorithms
 * Medium (36.89%)
 * Likes:    541
 * Dislikes: 74
 * Total Accepted:    92.8K
 * Total Submissions: 251.4K
 * Testcase Example:  '5\n7'
 *
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND
 * of all numbers in this range, inclusive.
 * 
 * Example 1:
 * 
 * 
 * Input: [5,7]
 * Output: 4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [0,1]
 * Output: 0
 */

// @lc code=start
class Solution {

	public int rangeBitwiseAnd(int m, int n) {
		int ans = 0;
		int bitMask = n ^ m;
		for(int i = 31; i >= 0; i--){
			int mBit = (m >> i) & 1;
			int nBit = (n >> i) & 1;
			if(mBit != nBit){
				break;
			}
			ans |= mBit << i;
		}
		return ans;
	}

	// See https://leetcode.com/problems/bitwise-and-of-numbers-range/discuss/56721/2-line-Solution-with-detailed-explanation
	public int rangeBitwiseAnd1(int m, int n) {
		while(n > m){
			//remove rightmost 1
			n = n & (n - 1);
		}
		return n;
	}

    public int rangeBitwiseAnd0(int m, int n) {
        int ans = 0;
        int bitMask = m & n;
        for(int i = 0; i < 31; i++){
			int bit = (bitMask >> i) & 1;
        	if(bit == 1){
	        	int c1 = getBlock(m, i);
	        	int c2 = getBlock(n, i);
	        	// System.out.println(i+"->"+c1 +", "+ c2);
	        	if(c1 != c2){
	        		bit = 0;
	        	}
        	}
        	ans |= (bit << i);
        }
        return ans;
    }

    int getBlock(int m, int bit){
    	int d = 1 << bit;
    	return m / d;
    }
}
// @lc code=end
