/*
 * @lc app=leetcode id=1291 lang=java
 *
 * [1291] Sequential Digits
 *
 * https://leetcode.com/problems/sequential-digits/description/
 *
 * algorithms
 * Medium (49.65%)
 * Likes:    43
 * Dislikes: 5
 * Total Accepted:    3.8K
 * Total Submissions: 7.7K
 * Testcase Example:  '100\n300'
 *
 * An integer has sequential digits if and only if each digit in the number is
 * one more than the previous digit.
 * 
 * Return a sorted list of all the integers in the range [low, high] inclusive
 * that have sequential digits.
 * 
 * 
 * Example 1:
 * Input: low = 100, high = 300
 * Output: [123,234]
 * Example 2:
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 * 
 * 
 * Constraints:
 * 
 * 
 * 10 <= low <= high <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
		List<Integer> all = new ArrayList<>();
		for(int l = 2; l <= 9; l++){
			for(int i = 1; i <= 9 - l + 1; i++){
				int d = 0;
				for(int j = i; j < i + l; j++){
					d = d * 10 + j;
				}
				all.add(d);
			}
		}
		List<Integer> ans = new ArrayList<>();
		for(int i: all){
			if(low <= i && i <= high){
				ans.add(i);
			}
		}
		return ans;
    }
}
// @lc code=end
