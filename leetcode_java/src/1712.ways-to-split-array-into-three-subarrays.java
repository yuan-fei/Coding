/*
 * @lc app=leetcode id=1712 lang=java
 *
 * [1712] Ways to Split Array Into Three Subarrays
 *
 * https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/description/
 *
 * algorithms
 * Medium (28.40%)
 * Likes:    150
 * Dislikes: 22
 * Total Accepted:    3.8K
 * Total Submissions: 13.5K
 * Testcase Example:  '[1,1,1]'
 *
 * A split of an integer array is good if:
 * 
 * 
 * The array is split into three non-empty contiguous subarrays - named left,
 * mid, right respectively from left to right.
 * The sum of the elements in left is less than or equal to the sum of the
 * elements in mid, and the sum of the elements in mid is less than or equal to
 * the sum of the elements in right.
 * 
 * 
 * Given nums, an array of non-negative integers, return the number of good
 * ways to split nums. As the number may be too large, return it modulo 10^9 +
 * 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,1]
 * Output: 1
 * Explanation: The only good way to split nums is [1] [1] [1].
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,2,2,5,0]
 * Output: 3
 * Explanation: There are three good ways of splitting nums:
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: There is no good way to split nums.
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^4
 * 
 */

// @lc code=start
class Solution {
    public int waysToSplit(int[] nums) {
    	int n = nums.length;
        int[] pSum = new int[n + 1];
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int i = 1; i < pSum.length; i++){
        	pSum[i] = pSum[i - 1] + nums[i - 1];
        	tm.put(pSum[i], i);
        }
        tm.put(-1, 0);
        // System.out.println(tm);
        int cnt = 0;
        int MOD = 1000000007;
        for(int i = 1; i < pSum.length; i++){
        	int x = pSum[i];
        	Integer midKey = tm.lowerKey(2 * x);
        	if(midKey == null){
        		break;
        	}
        	else{
        		int mid = Math.max(tm.get(midKey), i);
        		Integer highKey = tm.floorKey((x + pSum[n]) / 2);
        		if(midKey != null){
        			int high = Math.min(tm.get(highKey), n - 1);
        			// System.out.println(x + ", " + mid + ", " + high);
        			cnt += Math.max(0, high - mid);
        			cnt %= MOD;
        		}
        	}
        }
        return cnt;
    }


}
// @lc code=end
