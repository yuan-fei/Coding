/*
 * @lc app=leetcode id=372 lang=java
 *
 * [372] Super Pow
 *
 * https://leetcode.com/problems/super-pow/description/
 *
 * algorithms
 * Medium (36.13%)
 * Likes:    171
 * Dislikes: 660
 * Total Accepted:    32K
 * Total Submissions: 88.5K
 * Testcase Example:  '2\n[3]'
 *
 * Your task is to calculate a^b mod 1337 where a is a positive integer and b
 * is an extremely large positive integer given in the form of an array.
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: a = 2, b = [3]
 * Output: 8
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: a = 2, b = [1,0]
 * Output: 1024
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int superPow(int a, int[] b) {
    	a %= 1337;
    	int n = b.length;
    	int ans = 1;
    	int base = a;
        for(int i = n - 1; i >= 0; i--){
        	ans = (ans * quickPow(base, b[i])) % 1337;
        	base = quickPow(base, 10);
        }
        return ans;
    }

	int quickPow(int a, int x){
		if(x == 0){
			return 1;
		}
		if(x % 2 == 1){
			return (a * quickPow(a, x - 1)) % 1337;
		}
		else{
			int h = quickPow(a, x / 2);
			return (h * h) % 1337;
		}
	}
}
// @lc code=end
