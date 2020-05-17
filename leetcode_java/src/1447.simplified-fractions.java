/*
 * @lc app=leetcode id=1447 lang=java
 *
 * [1447] Simplified Fractions
 *
 * https://leetcode.com/problems/simplified-fractions/description/
 *
 * algorithms
 * Medium (57.36%)
 * Likes:    29
 * Dislikes: 4
 * Total Accepted:    5.7K
 * Total Submissions: 10K
 * Testcase Example:  '2\r'
 *
 * Given an integer n, return a list of all simplified fractions between 0 and
 * 1 (exclusive) such that the denominator is less-than-or-equal-to n. The
 * fractions can be in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 2
 * Output: ["1/2"]
 * Explanation: "1/2" is the only unique fraction with a denominator
 * less-than-or-equal-to 2.
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3
 * Output: ["1/2","1/3","2/3"]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 4
 * Output: ["1/2","1/3","1/4","2/3","3/4"]
 * Explanation: "2/4" is not a simplified fraction because it can be simplified
 * to "1/2".
 * 
 * Example 4:
 * 
 * 
 * Input: n = 1
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 100
 * 
 */

// @lc code=start
class Solution {
    public List<String> simplifiedFractions(int n) {
    	List<String> res = new ArrayList<>();
    	for(int i = 2; i <= n; i++){
    		res.add(makeFrac(1, i));
    	}
        for(int i = 2; i <= n; i++){
        	for(int j = i + 1; j <= n; j++){
        		if(gcd(j, i) == 1){
        			res.add(makeFrac(i, j));
        		}
        	}
        }
        return res;
    }

    int gcd(int a, int b){
    	if(b == 0){
    		return a;
    	}
    	if(a < b){
    		return gcd(b, a);
    	}
    	return gcd(b, a % b);
    }
    String makeFrac(int n, int d){
    	return n + "/" + d;
    }
}
// @lc code=end
