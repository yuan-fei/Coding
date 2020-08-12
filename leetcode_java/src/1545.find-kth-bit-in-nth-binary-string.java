/*
 * @lc app=leetcode id=1545 lang=java
 *
 * [1545] Find Kth Bit in Nth Binary String
 *
 * https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/description/
 *
 * algorithms
 * Medium (57.20%)
 * Likes:    96
 * Dislikes: 7
 * Total Accepted:    9.1K
 * Total Submissions: 15.9K
 * Testcase Example:  '3\n1'
 *
 * Given two positive integers n and k, the binary string  Sn is formed as
 * follows:
 * 
 * 
 * S1 = "0"
 * Si = Si-1 + "1" + reverse(invert(Si-1)) for i > 1
 * 
 * 
 * Where + denotes the concatenation operation, reverse(x) returns the reversed
 * string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1
 * changes to 0).
 * 
 * For example, the first 4 strings in the above sequence are:
 * 
 * 
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * 
 * 
 * Return the k^th bit in Sn. It is guaranteed that k is valid for the given
 * n.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, k = 1
 * Output: "0"
 * Explanation: S3 is "0111001". The first bit is "0".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, k = 11
 * Output: "1"
 * Explanation: S4 is "011100110110001". The 11th bit is "1".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 1, k = 1
 * Output: "0"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: n = 2, k = 3
 * Output: "1"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 20
 * 1 <= k <= 2^n - 1
 * 
 */

// @lc code=start
class Solution {
	int[] len;
    public char findKthBit(int n, int k) {
        len = new int[n + 1];
        len[1] = 1;
        for(int i = 2; i < n; i++){
        	len[i] = (len[i - 1] << 1) + 1;
        }
        return (char)('0' + findKthBitHelper(n, k));
    }

    int findKthBitHelper(int n, int k){
    	if(n == 1){
    		return 0;
    	}
    	if(k == len[n - 1] + 1){
    		return 1;
    	}
    	else if(k <= len[n - 1]){
    		return findKthBitHelper(n - 1, k);
    	}
    	else{
    		return 1 - findKthBitHelper(n - 1, len[n - 1] - (k - len[n - 1] - 1) + 1);
    	}
    }
}
// @lc code=end
