/*
 * @lc app=leetcode id=1806 lang=java
 *
 * [1806] Minimum Number of Operations to Reinitialize a Permutation
 *
 * https://leetcode.com/problems/minimum-number-of-operations-to-reinitialize-a-permutation/description/
 *
 * algorithms
 * Medium (69.75%)
 * Likes:    21
 * Dislikes: 9
 * Total Accepted:    4.1K
 * Total Submissions: 5.9K
 * Testcase Example:  '2'
 *
 * You are given an even integer n​​​​​​. You initially have a permutation perm
 * of size n​​ where perm[i] == i​ (0-indexed)​​​​.
 * 
 * In one operation, you will create a new array arr, and for each i:
 * 
 * 
 * If i % 2 == 0, then arr[i] = perm[i / 2].
 * If i % 2 == 1, then arr[i] = perm[n / 2 + (i - 1) / 2].
 * 
 * 
 * You will then assign arr​​​​ to perm.
 * 
 * Return the minimum non-zero number of operations you need to perform on perm
 * to return the permutation to its initial value.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 2
 * Output: 1
 * Explanation: prem = [0,1] initially.
 * After the 1^st operation, prem = [0,1]
 * So it takes only 1 operation.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4
 * Output: 2
 * Explanation: prem = [0,1,2,3] initially.
 * After the 1^st operation, prem = [0,2,1,3]
 * After the 2^nd operation, prem = [0,1,2,3]
 * So it takes only 2 operations.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 6
 * Output: 4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 1000
 * n​​​​​​ is even.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int reinitializePermutation(int n) {
    	if(n == 2){
    		return 1;
    	}
    	int x = 2 % (n - 1);
    	int cnt = 1;
    	while(x != 1){
    		x = (x << 1) % (n - 1);
    		cnt++;
    	}
    	return cnt;
    }
}
// @lc code=end
