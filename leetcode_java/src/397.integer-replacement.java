/*
 * @lc app=leetcode id=397 lang=java
 *
 * [397] Integer Replacement
 *
 * https://leetcode.com/problems/integer-replacement/description/
 *
 * algorithms
 * Medium (32.45%)
 * Likes:    322
 * Dislikes: 300
 * Total Accepted:    51.9K
 * Total Submissions: 159.7K
 * Testcase Example:  '8'
 *
 * 
 * Given a positive integer n and you can do operations as follow:
 * 
 * 
 * 
 * 
 * If n is even, replace n with n/2.
 * If n is odd, you can replace n with either n + 1 or n - 1.
 * 
 * 
 * 
 * 
 * What is the minimum number of replacements needed for n to become 1?
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * Input:
 * 8
 * 
 * Output:
 * 3
 * 
 * Explanation:
 * 8 -> 4 -> 2 -> 1
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * 7
 * 
 * Output:
 * 4
 * 
 * Explanation:
 * 7 -> 8 -> 4 -> 2 -> 1
 * or
 * 7 -> 6 -> 3 -> 2 -> 1
 * 
 * 
 */

// @lc code=start
class Solution {
	Map<Long, Integer> m = new HashMap<>();
	public int integerReplacement(int num){
		return integerReplacement((long)num);
	}

    public int integerReplacement(long n) {
    	if(n == 1){
    		return 0;
    	}
    	if(!m.containsKey(n)){
    		if(n % 2 == 0){
    			m.put(n, integerReplacement(n / 2) + 1);
    		}
    		else{
    			m.put(n, Math.min(integerReplacement(n + 1), integerReplacement(n - 1)) + 1);
    		}
    	}
    	return m.get(n);
    }

    
}
// @lc code=end
