/*
 * @lc app=leetcode id=3048 lang=java
 *
 * [3048] Earliest Second to Mark Indices I
 *
 * https://leetcode.com/problems/earliest-second-to-mark-indices-i/description/
 *
 * algorithms
 * Medium (35.38%)
 * Likes:    179
 * Dislikes: 89
 * Total Accepted:    9.2K
 * Total Submissions: 25.7K
 * Testcase Example:  '[2,2,0]\n[2,2,2,2,3,2,2,1]'
 *
 * You are given two 1-indexed integer arrays, nums and, changeIndices, having
 * lengths n and m, respectively.
 * 
 * Initially, all indices in nums are unmarked. Your task is to mark all
 * indices in nums.
 * 
 * In each second, s, in order from 1 to m (inclusive), you can perform one of
 * the following operations:
 * 
 * 
 * Choose an index i in the range [1, n] and decrement nums[i] by 1.
 * If nums[changeIndices[s]] is equal to 0, mark the index
 * changeIndices[s].
 * Do nothing.
 * 
 * 
 * Return an integer denoting the earliest second in the range [1, m] when all
 * indices in nums can be marked by choosing operations optimally, or -1 if it
 * is impossible.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,2,0], changeIndices = [2,2,2,2,3,2,2,1]
 * Output: 8
 * Explanation: In this example, we have 8 seconds. The following operations
 * can be performed to mark all indices:
 * Second 1: Choose index 1 and decrement nums[1] by one. nums becomes [1,2,0].
 * Second 2: Choose index 1 and decrement nums[1] by one. nums becomes [0,2,0].
 * Second 3: Choose index 2 and decrement nums[2] by one. nums becomes [0,1,0].
 * Second 4: Choose index 2 and decrement nums[2] by one. nums becomes [0,0,0].
 * Second 5: Mark the index changeIndices[5], which is marking index 3, since
 * nums[3] is equal to 0.
 * Second 6: Mark the index changeIndices[6], which is marking index 2, since
 * nums[2] is equal to 0.
 * Second 7: Do nothing.
 * Second 8: Mark the index changeIndices[8], which is marking index 1, since
 * nums[1] is equal to 0.
 * Now all indices have been marked.
 * It can be shown that it is not possible to mark all indices earlier than the
 * 8th second.
 * Hence, the answer is 8.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,3], changeIndices = [1,1,1,2,1,1,1]
 * Output: 6
 * Explanation: In this example, we have 7 seconds. The following operations
 * can be performed to mark all indices:
 * Second 1: Choose index 2 and decrement nums[2] by one. nums becomes [1,2].
 * Second 2: Choose index 2 and decrement nums[2] by one. nums becomes [1,1].
 * Second 3: Choose index 2 and decrement nums[2] by one. nums becomes [1,0].
 * Second 4: Mark the index changeIndices[4], which is marking index 2, since
 * nums[2] is equal to 0.
 * Second 5: Choose index 1 and decrement nums[1] by one. nums becomes [0,0].
 * Second 6: Mark the index changeIndices[6], which is marking index 1, since
 * nums[1] is equal to 0.
 * Now all indices have been marked.
 * It can be shown that it is not possible to mark all indices earlier than the
 * 6th second.
 * Hence, the answer is 6.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [0,1], changeIndices = [2,2,2]
 * Output: -1
 * Explanation: In this example, it is impossible to mark all indices because
 * index 1 isn't in changeIndices.
 * Hence, the answer is -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n == nums.length <= 2000
 * 0 <= nums[i] <= 10^9
 * 1 <= m == changeIndices.length <= 2000
 * 1 <= changeIndices[i] <= n
 * 
 * 
 */

// @lc code=start
class Solution {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int low = 0;
        int high = changeIndices.length - 1;
        while(low + 1 < high){
            int mid = (low + high) / 2;
            if(feasible(nums, changeIndices, mid)){
                high = mid;
            }
            else{
                low = mid;
            }
        }
        if(feasible(nums, changeIndices, low)){
            return low + 1;
        }
        if(feasible(nums, changeIndices, high)){
            return high + 1;
        }
        return -1;
    }

    boolean feasible(int[] nums, int[] changeIndices, int end){
        int[] lastPos = new int[nums.length];
        Arrays.fill(lastPos, -1);
        for (int i = 0; i <= end; i++) {
            lastPos[changeIndices[i] - 1] = i;
        }
        for(int x : lastPos){
            if(x < 0){
                return false;
            }
        }
        int buffer = 0;
        for (int i = 0; i <= end; i++) {
            if(lastPos[changeIndices[i] - 1] == i){
                buffer -= nums[changeIndices[i] - 1];
                if(buffer < 0){
                    return false;
                }
            }
            else{
                buffer++;
            }
        }
        return true;
    }
}
// @lc code=end
