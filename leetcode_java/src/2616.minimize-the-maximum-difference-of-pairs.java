/*
 * @lc app=leetcode id=2616 lang=java
 *
 * [2616] Minimize the Maximum Difference of Pairs
 *
 * https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/description/
 *
 * algorithms
 * Medium (17.44%)
 * Likes:    79
 * Dislikes: 9
 * Total Accepted:    2.1K
 * Total Submissions: 12.8K
 * Testcase Example:  '[10,1,2,7,1,3]\n2'
 *
 * You are given a 0-indexed integer array nums and an integer p. Find p pairs
 * of indices of nums such that the maximum difference amongst all the pairs is
 * minimized. Also, ensure no index appears more than once amongst the p
 * pairs.
 * 
 * Note that for a pair of elements at the index i and j, the difference of
 * this pair is |nums[i] - nums[j]|, where |x| represents the absolute value of
 * x.
 * 
 * Return the minimum maximum difference among all p pairs.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [10,1,2,7,1,3], p = 2
 * Output: 1
 * Explanation: The first pair is formed from the indices 1 and 4, and the
 * second pair is formed from the indices 2 and 5. 
 * The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) =
 * max(0, 1) = 1. Therefore, we return 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,2,1,2], p = 1
 * Output: 0
 * Explanation: Let the indices 1 and 3 form a pair. The difference of that
 * pair is |2 - 2| = 0, which is the minimum we can attain.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= p <= (nums.length)/2
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimizeMax(int[] A, int p) {
        Arrays.sort(A);
        int n = A.length, left = 0, right = A[n - 1] - A[0];
        while (left < right) {
            int mid = (left + right) / 2, k = 0;
            for (int i = 1; i < n && k < p; ++i) {
                if (A[i] - A[i - 1] <= mid) {
                    k++;
                    i++;
                }
            }
            if (k >= p)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
// @lc code=end
