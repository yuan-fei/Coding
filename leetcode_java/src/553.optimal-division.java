/*
 * @lc app=leetcode id=553 lang=java
 *
 * [553] Optimal Division
 *
 * https://leetcode.com/problems/optimal-division/description/
 *
 * algorithms
 * Medium (57.01%)
 * Likes:    183
 * Dislikes: 1162
 * Total Accepted:    25.9K
 * Total Submissions: 45.5K
 * Testcase Example:  '[1000,100,10,2]'
 *
 * Given a list of positive integers, the adjacent integers will perform the
 * float division. For example, [2,3,4] -> 2 / 3 / 4.
 * 
 * However, you can add any number of parenthesis at any position to change the
 * priority of operations. You should find out how to add parenthesis to get
 * the maximum result, and return the corresponding expression in string
 * format. Your expression should NOT contain redundant parenthesis.
 * 
 * Example:
 * 
 * Input: [1000,100,10,2]
 * Output: "1000/(100/10/2)"
 * Explanation:
 * 1000/(100/10/2) = 1000/((100/10)/2) = 200
 * However, the bold parenthesis in "1000/((100/10)/2)" are redundant, since
 * they don't influence the operation priority. So you should return
 * "1000/(100/10/2)". 
 * 
 * Other cases:
 * 1000/(100/10)/2 = 50
 * 1000/(100/(10/2)) = 50
 * 1000/100/10/2 = 0.5
 * 1000/100/(10/2) = 2
 * 
 * 
 * 
 * Note:
 * 
 * The length of the input array is [1, 10].
 * Elements in the given array will be in range [2, 1000].
 * There is only one optimal division for each test case.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        if(n == 1){
        	return "" + nums[0];
        }
        if(n == 2){
        	return "" + nums[0] + "/" + nums[1];	
        }
        String ans = "";
        for(int i = 0; i < n; i++){
        	ans += nums[i];
        	if(i == 0){
        		ans += "/(";
        	}
        	else if(i == n - 1){
        		ans += ")";
        	}
        	else{
        		ans += "/";
        	}
        }
        return ans;
    }
}
// @lc code=end
