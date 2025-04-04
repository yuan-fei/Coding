/*
 * @lc app=leetcode id=3113 lang=java
 *
 * [3113] Find the Number of Subarrays Where Boundary Elements Are Maximum
 *
 * https://leetcode.com/problems/find-the-number-of-subarrays-where-boundary-elements-are-maximum/description/
 *
 * algorithms
 * Hard (29.96%)
 * Likes:    225
 * Dislikes: 5
 * Total Accepted:    11.4K
 * Total Submissions: 37.7K
 * Testcase Example:  '[1,4,3,3,2]'
 *
 * You are given an array of positive integers nums.
 * 
 * Return the number of subarrays of nums, where the first and the last
 * elements of the subarray are equal to the largest element in the
 * subarray.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,4,3,3,2]
 * 
 * Output: 6
 * 
 * Explanation:
 * 
 * There are 6 subarrays which have the first and the last elements equal to
 * the largest element of the subarray:
 * 
 * 
 * subarray [1,4,3,3,2], with its largest element 1. The first element is 1 and
 * the last element is also 1.
 * subarray [1,4,3,3,2], with its largest element 4. The first element is 4 and
 * the last element is also 4.
 * subarray [1,4,3,3,2], with its largest element 3. The first element is 3 and
 * the last element is also 3.
 * subarray [1,4,3,3,2], with its largest element 3. The first element is 3 and
 * the last element is also 3.
 * subarray [1,4,3,3,2], with its largest element 2. The first element is 2 and
 * the last element is also 2.
 * subarray [1,4,3,3,2], with its largest element 3. The first element is 3 and
 * the last element is also 3.
 * 
 * 
 * Hence, we return 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,3,3]
 * 
 * Output: 6
 * 
 * Explanation:
 * 
 * There are 6 subarrays which have the first and the last elements equal to
 * the largest element of the subarray:
 * 
 * 
 * subarray [3,3,3], with its largest element 3. The first element is 3 and the
 * last element is also 3.
 * subarray [3,3,3], with its largest element 3. The first element is 3 and the
 * last element is also 3.
 * subarray [3,3,3], with its largest element 3. The first element is 3 and the
 * last element is also 3.
 * subarray [3,3,3], with its largest element 3. The first element is 3 and the
 * last element is also 3.
 * subarray [3,3,3], with its largest element 3. The first element is 3 and the
 * last element is also 3.
 * subarray [3,3,3], with its largest element 3. The first element is 3 and the
 * last element is also 3.
 * 
 * 
 * Hence, we return 6.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1]
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * There is a single subarray of nums which is [1], with its largest element 1.
 * The first element is 1 and the last element is also 1.
 * 
 * Hence, we return 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class Solution {
    public long numberOfSubarrays(int[] nums) {
        int n = nums.length;
        Stack<Integer> stk = new Stack<>();
        Map<Integer, List<Integer>> valueToIndexes = new HashMap<>();
        int[] firstGreaterIndex = new int[n];
        Arrays.fill(firstGreaterIndex, n);
        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && nums[stk.peek()] < nums[i]) {
                firstGreaterIndex[stk.pop()] = i;
            }
            stk.push(i);
            List<Integer> indexes = valueToIndexes.computeIfAbsent(nums[i], x -> new ArrayList<>());
            indexes.add(i);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> indexes = valueToIndexes.get(nums[i]);
            int start = Collections.binarySearch(indexes, i);
            int end = Collections.binarySearch(indexes, firstGreaterIndex[i]);
            if (end < 0) {
                end = -end - 1;
            } else {
                end++;
            }
            ans += end - start;
        }
        return ans;

    }
}
// @lc code=end
