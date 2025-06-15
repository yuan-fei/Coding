/*
 * @lc app=leetcode id=3171 lang=java
 *
 * [3171] Find Subarray With Bitwise OR Closest to K
 *
 * https://leetcode.com/problems/find-subarray-with-bitwise-or-closest-to-k/description/
 *
 * algorithms
 * Hard (30.27%)
 * Likes:    190
 * Dislikes: 7
 * Total Accepted:    13.8K
 * Total Submissions: 46.5K
 * Testcase Example:  '[1,2,4,5]\n3'
 *
 * You are given an array nums and an integer k. You need to find a subarray of
 * nums such that the absolute difference between k and the bitwise OR of the
 * subarray elements is as small as possible. In other words, select a subarray
 * nums[l..r] such that |k - (nums[l] OR nums[l + 1] ... OR nums[r])| is
 * minimum.
 * 
 * Return the minimum possible value of the absolute difference.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,4,5], k = 3
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * The subarray nums[0..1] has OR value 3, which gives the minimum absolute
 * difference |3 - 3| = 0.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,3,1,3], k = 2
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The subarray nums[1..1] has OR value 3, which gives the minimum absolute
 * difference |3 - 2| = 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1], k = 10
 * 
 * Output: 9
 * 
 * Explanation:
 * 
 * There is a single subarray with OR value 1, which gives the minimum absolute
 * difference |10 - 1| = 9.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumDifference(int[] nums, int k) {
        SqrtDecomposition sqrd = new SqrtDecomposition();
        sqrd.build(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // System.out.println(sqrd.query(i, k));
            ans = Math.min(ans, sqrd.query(i, k));
        }
        return ans;
    }

    public class SqrtDecomposition {

        int[] a;
        int[] chunks;
        int chunckSize;

        public void build(int[] arr) {
            a = arr;
            int n = a.length;
            chunckSize = (int) Math.sqrt(n);
            int m = (n + chunckSize - 1) / chunckSize;
            chunks = new int[m];
            for (int i = 0; i < m; i++) {
                buildChunck(i);
            }
        }

        private void buildChunck(int i) {
            int n = a.length;
            chunks[i] = f(i * chunckSize, Math.min((i + 1) * chunckSize - 1, n - 1));
        }

        /** apply calculation in [start, end] */
        int f(int start, int end) {
            int ret = 0;
            for (int i = start; i <= end; i++) {
                ret = accumulate(ret, a[i]);
            }
            return ret;
        }

        /** accumulate ans to total */
        int accumulate(int ans, int chuckValue) {
            return ans | chuckValue;
        }

        /** O(n^0.5) */
        public int query(int left, int target) {
            int i = left;
            int ans = Integer.MAX_VALUE;
            int cur = 0;
            while (i < a.length) {
                if (i % chunckSize == 0) {
                    int chunck = chunks[i / chunckSize];
                    // chunck step
                    if (accumulate(cur, chunck) <= target) {
                        cur = accumulate(cur, chunck);
                        ans = Math.min(ans, Math.abs(cur - target));
                        i += chunckSize;
                        continue;
                    }
                }
                // single step
                cur = accumulate(cur, a[i]);
                ans = Math.min(ans, Math.abs(cur - target));
                if (cur > target) {
                    return ans;
                }
                i++;
            }
            return ans;
        }

        /** O(n^0.5) */
        public void update(int i, int v) {
            a[i] = v;
            buildChunck(i / chunckSize);
        }
    }
}
// @lc code=end
