/*
 * @lc app=leetcode id=1414 lang=java
 *
 * [1414] Find the Minimum Number of Fibonacci Numbers Whose Sum Is K
 *
 * https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/description/
 *
 * algorithms
 * Medium (59.01%)
 * Likes:    98
 * Dislikes: 15
 * Total Accepted:    7.1K
 * Total Submissions: 12.1K
 * Testcase Example:  '7'
 *
 * Given the number k, return the minimum number of Fibonacci numbers whose sum
 * is equal to k, whether a Fibonacci number could be used multiple times.
 * 
 * The Fibonacci numbers are defined as:
 * 
 * 
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 , for n > 2.
 * 
 * It is guaranteed that for the given constraints we can always find such
 * fibonacci numbers that sum k.
 * 
 * Example 1:
 * 
 * 
 * Input: k = 7
 * Output: 2 
 * Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ... 
 * For k = 7 we can use 2 + 5 = 7.
 * 
 * Example 2:
 * 
 * 
 * Input: k = 10
 * Output: 2 
 * Explanation: For k = 10 we can use 2 + 8 = 10.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: k = 19
 * Output: 3 
 * Explanation: For k = 19 we can use 1 + 5 + 13 = 19.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= 10^9
 * 
 */

// @lc code=start
class Solution {
    public int findMinFibonacciNumbers(int k) {
        List<Integer> fibs = new ArrayList<>();
        fibs.add(1);
        fibs.add(1);
        int last = 1;
        int num = fibs.get(last) + fibs.get(last - 1);
        while(num <= 1000000000){
        	fibs.add(num);
        	last++;
        	num = fibs.get(last) + fibs.get(last - 1);
        }
        int cnt = 0;
        for(int i = fibs.size() - 1; i >= 0; i--){
        	if((i == fibs.size() - 1 || fibs.get(i + 1) > k) && fibs.get(i) <= k){
        		cnt++;
        		k -= fibs.get(i);
        		if(k == 0){
        			return cnt;
        		}
        	}
        }
        return cnt;
    }
}
// @lc code=end
