/*
 * @lc app=leetcode id=3080 lang=java
 *
 * [3080] Mark Elements on Array by Performing Queries
 *
 * https://leetcode.com/problems/mark-elements-on-array-by-performing-queries/description/
 *
 * algorithms
 * Medium (49.05%)
 * Likes:    119
 * Dislikes: 26
 * Total Accepted:    19.7K
 * Total Submissions: 40.1K
 * Testcase Example:  '[1,2,2,1,2,3,1]\n[[1,2],[3,3],[4,2]]'
 *
 * You are given a 0-indexed array nums of size n consisting of positive
 * integers.
 * 
 * You are also given a 2D array queries of size m where queries[i] = [indexi,
 * ki].
 * 
 * Initially all elements of the array are unmarked.
 * 
 * You need to apply m queries on the array in order, where on the i^th query
 * you do the following:
 * 
 * 
 * Mark the element at index indexi if it is not already marked.
 * Then mark ki unmarked elements in the array with the smallest values. If
 * multiple such elements exist, mark the ones with the smallest indices. And
 * if less than ki unmarked elements exist, then mark all of them.
 * 
 * 
 * Return an array answer of size m where answer[i] is the sum of unmarked
 * elements in the array after the i^th query.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,2,1,2,3,1], queries = [[1,2],[3,3],[4,2]]
 * 
 * Output: [8,3,0]
 * 
 * Explanation:
 * 
 * We do the following queries on the array:
 * 
 * 
 * Mark the element at index 1, and 2 of the smallest unmarked elements with
 * the smallest indices if they exist, the marked elements now are nums =
 * [1,2,2,1,2,3,1]. The sum of unmarked elements is 2 + 2 + 3 + 1 = 8.
 * Mark the element at index 3, since it is already marked we skip it. Then we
 * mark 3 of the smallest unmarked elements with the smallest indices, the
 * marked elements now are nums = [1,2,2,1,2,3,1]. The sum of unmarked elements
 * is 3.
 * Mark the element at index 4, since it is already marked we skip it. Then we
 * mark 2 of the smallest unmarked elements with the smallest indices if they
 * exist, the marked elements now are nums = [1,2,2,1,2,3,1]. The sum of
 * unmarked elements is 0.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,4,2,3], queries = [[0,1]]
 * 
 * Output: [7]
 * 
 * Explanation:  We do one query which is mark the element at index 0 and mark
 * the smallest element among unmarked elements. The marked elements will be
 * nums = [1,4,2,3], and the sum of unmarked elements is 4 + 3 = 7.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * m == queries.length
 * 1 <= m <= n <= 10^5
 * 1 <= nums[i] <= 10^5
 * queries[i].length == 2
 * 0 <= indexi, ki <= n - 1
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        boolean[] isMarked = new boolean[n];
        int[] sortedByValue = IntStream.range(0, n).boxed()
                .sorted(Comparator.comparing((Integer i) -> nums[i]).thenComparing(i -> i))
                .mapToInt(i -> i)
                .toArray();
        long sumOfUnmarked = Arrays.stream(nums).asLongStream().sum();

        long[] ans = new long[m];
        int j = 0;
        for (int i = 0; i < m; i++) {
            int id = queries[i][0];
            int k = queries[i][1];
            if (!isMarked[id]) {
                isMarked[id] = true;
                sumOfUnmarked -= nums[id];
            }
            while (j < n && k > 0) {
                if (!isMarked[sortedByValue[j]]) {
                    isMarked[sortedByValue[j]] = true;
                    sumOfUnmarked -= nums[sortedByValue[j]];
                    k--;
                }
                j++;
            }
            ans[i] = sumOfUnmarked;
        }
        return ans;
    }
}
// @lc code=end
