/*
 * @lc app=leetcode id=1492 lang=java
 *
 * [1492] The kth Factor of n
 *
 * https://leetcode.com/problems/the-kth-factor-of-n/description/
 *
 * algorithms
 * Medium (73.46%)
 * Likes:    29
 * Dislikes: 7
 * Total Accepted:    5.7K
 * Total Submissions: 7.8K
 * Testcase Example:  '12\n3'
 *
 * Given two positive integers n and k.
 * 
 * A factor of an integer n is defined as an integer i where n % i == 0.
 * 
 * Consider a list of all factors of n sorted in ascending order, return the
 * kth factor in this list or return -1 if n has less than k factors.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 12, k = 3
 * Output: 3
 * Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 7, k = 2
 * Output: 7
 * Explanation: Factors list is [1, 7], the 2nd factor is 7.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 4, k = 4
 * Output: -1
 * Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should
 * return -1.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: n = 1, k = 1
 * Output: 1
 * Explanation: Factors list is [1], the 1st factor is 1.
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: n = 1000, k = 3
 * Output: 4
 * Explanation: Factors list is [1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125,
 * 200, 250, 500, 1000].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= n <= 1000
 * 
 */

// @lc code=start
class Solution {
    public int kthFactor(int n, int k) {
    	List<Integer> factors = new ArrayList<>();
        for(int i = 1; i * i <= n; i++){
        	if(n % i == 0){
        		factors.add(i);
        		if(n / i != i){
        			factors.add(n / i);	
        		}
        	}
        }
        Collections.sort(factors);
        return factors.size() >= k ? factors.get(k - 1) : -1;
    }
}
// @lc code=end
