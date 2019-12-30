/*
 * @lc app=leetcode id=119 lang=java
 *
 * [119] Pascal's Triangle II
 *
 * https://leetcode.com/problems/pascals-triangle-ii/description/
 *
 * algorithms
 * Easy (46.29%)
 * Likes:    599
 * Dislikes: 183
 * Total Accepted:    241.3K
 * Total Submissions: 521K
 * Testcase Example:  '3'
 *
 * Given a non-negative index k where k ≤ 33, return the k^th index row of the
 * Pascal's triangle.
 * 
 * Note that the row index starts from 0.
 * 
 * 
 * In Pascal's triangle, each number is the sum of the two numbers directly
 * above it.
 * 
 * Example:
 * 
 * 
 * Input: 3
 * Output: [1,3,3,1]
 * 
 * 
 * Follow up:
 * 
 * Could you optimize your algorithm to use only O(k) extra space?
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> getRow(int rowIndex) {
		List<Integer> ans = new ArrayList<>();        
		for(int i = 0; i <= rowIndex; i++){
			ans.add(1);
			for(int j = i - 1; j >= 1; j--){
				ans.set(j, ans.get(j) + ans.get(j - 1));
			}
		}
		return ans;
    }
}
// @lc code=end
