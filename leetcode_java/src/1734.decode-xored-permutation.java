/*
 * @lc app=leetcode id=1734 lang=java
 *
 * [1734] Decode XORed Permutation
 *
 * https://leetcode.com/problems/decode-xored-permutation/description/
 *
 * algorithms
 * Medium (47.85%)
 * Likes:    178
 * Dislikes: 5
 * Total Accepted:    3.4K
 * Total Submissions: 7.1K
 * Testcase Example:  '[3,1]'
 *
 * There is an integer array perm that is a permutation of the first n positive
 * integers, where n is always odd.
 * 
 * It was encoded into another integer array encoded of length n - 1, such that
 * encoded[i] = perm[i] XOR perm[i + 1]. For example, if perm = [1,3,2], then
 * encoded = [2,1].
 * 
 * Given the encoded array, return the original array perm. It is guaranteed
 * that the answer exists and is unique.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: encoded = [3,1]
 * Output: [1,2,3]
 * Explanation: If perm = [1,2,3], then encoded = [1 XOR 2,2 XOR 3] = [3,1]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: encoded = [6,5,4,6]
 * Output: [2,4,1,5,3]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= n < 10^5
 * n is odd.
 * encoded.length == n - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] decode(int[] encoded) {
    	int n = encoded.length + 1;
    	int[] ans = new int[n];
    	int ub = 31;
    	for(; ub >= 0; ub--){
    		if(((n >> (ub - 1)) & 1) == 1){
    			break;
    		}
    	}
        for(int i = 0; i < ub; i++){
        	int cntOnes = 0;
        	for(int x = 1; x <= n; x++){
        		cntOnes += ((x >> i) & 1);
        	}
        	solve(encoded, ans, i, cntOnes);
        }
        return ans;
    }

    void solve(int[] encoded, int[] ans, int bit, int cntOnes){
    	int n = encoded.length;
    	int c1 = 0;
    	for(int i = 0; i < n; i++){
    		int v = (((ans[i] ^ encoded[i]) >> bit) & 1);
    		ans[i + 1] ^= v << bit;
    		if(v == 1){
    			c1++;	
    		}
    	}
    	if(c1 != cntOnes){
    		for(int i = 0; i <= n; i++){
    			ans[i] ^= 1 << bit;
    		}
    	}
    }
}
// @lc code=end
