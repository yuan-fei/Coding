/*
 * @lc app=leetcode id=3049 lang=java
 *
 * [3049] Earliest Second to Mark Indices II
 *
 * https://leetcode.com/problems/earliest-second-to-mark-indices-ii/description/
 *
 * algorithms
 * Hard (20.59%)
 * Likes:    80
 * Dislikes: 18
 * Total Accepted:    2.8K
 * Total Submissions: 13.4K
 * Testcase Example:  '[3,2,3]\n[1,3,2,2,2,2,3]'
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
 * Set nums[changeIndices[s]] to any non-negative value.
 * Choose an index i in the range [1, n], where nums[i] is equal to 0, and mark
 * index i.
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
 * Input: nums = [3,2,3], changeIndices = [1,3,2,2,2,2,3]
 * Output: 6
 * Explanation: In this example, we have 7 seconds. The following operations
 * can be performed to mark all indices:
 * Second 1: Set nums[changeIndices[1]] to 0. nums becomes [0,2,3].
 * Second 2: Set nums[changeIndices[2]] to 0. nums becomes [0,2,0].
 * Second 3: Set nums[changeIndices[3]] to 0. nums becomes [0,0,0].
 * Second 4: Mark index 1, since nums[1] is equal to 0.
 * Second 5: Mark index 2, since nums[2] is equal to 0.
 * Second 6: Mark index 3, since nums[3] is equal to 0.
 * Now all indices have been marked.
 * It can be shown that it is not possible to mark all indices earlier than the
 * 6th second.
 * Hence, the answer is 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,0,1,2], changeIndices = [1,2,1,2,1,2,1,2]
 * Output: 7
 * Explanation: In this example, we have 8 seconds. The following operations
 * can be performed to mark all indices:
 * Second 1: Mark index 1, since nums[1] is equal to 0.
 * Second 2: Mark index 2, since nums[2] is equal to 0.
 * Second 3: Decrement index 4 by one. nums becomes [0,0,1,1].
 * Second 4: Decrement index 4 by one. nums becomes [0,0,1,0].
 * Second 5: Decrement index 3 by one. nums becomes [0,0,0,0].
 * Second 6: Mark index 3, since nums[3] is equal to 0.
 * Second 7: Mark index 4, since nums[4] is equal to 0.
 * Now all indices have been marked.
 * It can be shown that it is not possible to mark all indices earlier than the
 * 7th second.
 * Hence, the answer is 7.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,3], changeIndices = [1,2,3]
 * Output: -1
 * Explanation: In this example, it can be shown that it is impossible to mark
 * all indices, as we don't have enough seconds. 
 * Hence, the answer is -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n == nums.length <= 5000
 * 0 <= nums[i] <= 10^9
 * 1 <= m == changeIndices.length <= 5000
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
        TreeSet<Integer> ts = new TreeSet<>(Comparator.comparing((Integer i) -> nums[i]).thenComparing(i -> i));
        Map<Integer, Integer> valueToFirstOccurance = new HashMap<>();
        int buffer = 0;
        
        for (int i = 0; i <= end; i++) {
            if(!valueToFirstOccurance.containsKey(changeIndices[i] - 1) && nums[changeIndices[i] - 1] != 0){
                valueToFirstOccurance.put(changeIndices[i] - 1, i);
            }
        }
        
        for (int i = end; i >= 0; i--) {
            if(valueToFirstOccurance.getOrDefault(changeIndices[i] - 1, -1) == i){
                ts.add(changeIndices[i] - 1);
                if(buffer > 0){
                    buffer--;
                }
                else{
                    ts.removeFirst();
                    buffer++;
                }
            }
            else{
                buffer++;
            }
        }
        // System.out.println(Arrays.asList(end, valueToFirstOccurance, ts, buffer));
        for (int i = 0; i < nums.length; i++) {
            if(!ts.contains(i)){
                buffer -= nums[i] + 1;
                if(buffer < 0){
                    return false;
                }
            }
        }
        return true;
    }
}
// @lc code=end
