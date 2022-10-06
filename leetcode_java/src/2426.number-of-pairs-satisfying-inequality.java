/*
 * @lc app=leetcode id=2426 lang=java
 *
 * [2426] Number of Pairs Satisfying Inequality
 *
 * https://leetcode.com/problems/number-of-pairs-satisfying-inequality/description/
 *
 * algorithms
 * Hard (39.18%)
 * Likes:    152
 * Dislikes: 4
 * Total Accepted:    4.2K
 * Total Submissions: 10.6K
 * Testcase Example:  '[3,2,5]\n[2,2,1]\n1'
 *
 * You are given two 0-indexed integer arrays nums1 and nums2, each of size n,
 * and an integer diff. Find the number of pairs (i, j) such that:
 * 
 * 
 * 0 <= i < j <= n - 1 and
 * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
 * 
 * 
 * Return the number of pairs that satisfy the conditions.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
 * Output: 3
 * Explanation:
 * There are 3 pairs that satisfy the conditions:
 * 1. i = 0, j = 1: 3 - 2 <= 2 - 2 + 1. Since i < j and 1 <= 1, this pair
 * satisfies the conditions.
 * 2. i = 0, j = 2: 3 - 5 <= 2 - 1 + 1. Since i < j and -2 <= 2, this pair
 * satisfies the conditions.
 * 3. i = 1, j = 2: 2 - 5 <= 2 - 1 + 1. Since i < j and -3 <= 2, this pair
 * satisfies the conditions.
 * Therefore, we return 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
 * Output: 0
 * Explanation:
 * Since there does not exist any pair that satisfies the conditions, we return
 * 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums1.length == nums2.length
 * 2 <= n <= 10^5
 * -10^4 <= nums1[i], nums2[i] <= 10^4
 * -10^4 <= diff <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    int MIN = -10000;
    int MAX = 10000;
    int[] range = {MIN - MAX, MAX - MIN};
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = nums1[i] - nums2[i];
        }
        // System.out.println(Arrays.toString(a));
        long ans = 0;
        BinaryIndexedTree bit = new BinaryIndexedTree(mappedValue(range[1]) + 1);
        for(int x : a){
            int v = Math.min(mappedValue(range[1]), mappedValue(x + diff));
            if(v >= 0){
                ans += bit.prefixSum(v);
            }
            bit.inc(mappedValue(x), 1);
        }
        return ans;
    }

    int mappedValue(int v){
        return v - range[0];
    }


    public class BinaryIndexedTree {

        int N;

        // range from 1 to N
        int[] bit;

        public BinaryIndexedTree(int size) {
            N = size;
            bit = new int[N + 1];
        }

        public long sumRange(int i, int j) {
            return prefixSum(j) - prefixSum(i - 1);
        }

        public void inc(int i, long delta) {
            incInternal(i + 1, delta);
        }

        public long prefixSum(int i) {
            return prefixSumInternal(i + 1);
        }

        private void incInternal(int i, long delta) {
            while (i <= N) {
                bit[i] += delta;
                i += (i & (-i));
            }
        }

        private long prefixSumInternal(int i) {
            long sum = 0;
            while (i > 0) {
                sum += bit[i];
                i -= (i & (-i));
            }
            return sum;
        }
    }

}
// @lc code=end
