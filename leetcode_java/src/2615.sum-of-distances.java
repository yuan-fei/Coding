/*
 * @lc app=leetcode id=2615 lang=java
 *
 * [2615] Sum of Distances
 *
 * https://leetcode.com/problems/sum-of-distances/description/
 *
 * algorithms
 * Medium (20.76%)
 * Likes:    104
 * Dislikes: 42
 * Total Accepted:    4.6K
 * Total Submissions: 22.6K
 * Testcase Example:  '[1,3,1,1,2]'
 *
 * You are given a 0-indexed integer array nums. There exists an array arr of
 * length nums.length, where arr[i] is the sum of |i - j| over all j such that
 * nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.
 * 
 * Return the array arr.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,1,1,2]
 * Output: [5,0,3,4,0]
 * Explanation: 
 * When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] =
 * |0 - 2| + |0 - 3| = 5. 
 * When i = 1, arr[1] = 0 because there is no other index with value 3.
 * When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] =
 * |2 - 0| + |2 - 3| = 3. 
 * When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] =
 * |3 - 0| + |3 - 2| = 4. 
 * When i = 4, arr[4] = 0 because there is no other index with value 2. 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,5,3]
 * Output: [0,0,0]
 * Explanation: Since each element in nums is distinct, arr[i] = 0 for all
 * i.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long[] distance(int[] nums) {
        Map<Integer, List<Integer>> m = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            m.putIfAbsent(nums[i], new ArrayList<>());
            m.get(nums[i]).add(i);
        }

        long[] ans = new long[nums.length];
        for(List<Integer> l : m.values()){
            fillDistance(ans, l);
        }
        
        return ans;
    }

    void fillDistance(long[] ans, List<Integer> l){
        long total = 0;
        for(int i = 1; i <= l.size(); i++){
            total += l.get(i - 1) - l.get(0);
        }
        ans[l.get(0)] = total;
        for(int i = 1; i < l.size(); i++){
            total += 1L * (i - (l.size() - i)) * (l.get(i) - l.get(i - 1));
            ans[l.get(i)]  = total;
        }
    }
}
// @lc code=end
