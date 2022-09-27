/*
 * @lc app=leetcode id=2411 lang=java
 *
 * [2411] Smallest Subarrays With Maximum Bitwise OR
 *
 * https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or/description/
 *
 * algorithms
 * Medium (35.28%)
 * Likes:    173
 * Dislikes: 12
 * Total Accepted:    5K
 * Total Submissions: 14.1K
 * Testcase Example:  '[1,0,2,1,3]'
 *
 * You are given a 0-indexed array nums of length n, consisting of non-negative
 * integers. For each index i from 0 to n - 1, you must determine the size of
 * the minimum sized non-empty subarray of nums starting at i (inclusive) that
 * has the maximum possible bitwise OR.
 * 
 * 
 * In other words, let Bij be the bitwise OR of the subarray nums[i...j]. You
 * need to find the smallest subarray starting at i, such that bitwise OR of
 * this subarray is equal to max(Bik) where i <= k <= n - 1.
 * 
 * 
 * The bitwise OR of an array is the bitwise OR of all the numbers in it.
 * 
 * Return an integer array answer of size n where answer[i] is the length of
 * the minimum sized subarray starting at i with maximum bitwise OR.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,0,2,1,3]
 * Output: [3,3,2,2,1]
 * Explanation:
 * The maximum possible bitwise OR starting at any index is 3. 
 * - Starting at index 0, the shortest subarray that yields it is [1,0,2].
 * - Starting at index 1, the shortest subarray that yields the maximum bitwise
 * OR is [0,2,1].
 * - Starting at index 2, the shortest subarray that yields the maximum bitwise
 * OR is [2,1].
 * - Starting at index 3, the shortest subarray that yields the maximum bitwise
 * OR is [1,3].
 * - Starting at index 4, the shortest subarray that yields the maximum bitwise
 * OR is [3].
 * Therefore, we return [3,3,2,2,1]. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2]
 * Output: [2,1]
 * Explanation:
 * Starting at index 0, the shortest subarray that yields the maximum bitwise
 * OR is of length 2.
 * Starting at index 1, the shortest subarray that yields the maximum bitwise
 * OR is of length 1.
 * Therefore, we return [2,1].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 1 <= n <= 10^5
 * 0 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] smallestSubarrays(int[] nums) {
        Queue<Integer>[] q = new Queue[32];
        for(int i = 0; i < 32; i++){
            q[i] = new LinkedList<Integer>();
        }
        for(int j = 0; j < nums.length; j++){
            int x = nums[j];
            for(int i = 0; i < 32; i++){
                if(((x >> i) & 1) == 1){
                    q[i].offer(j);
                }
            }
        }
        // System.out.println(Arrays.toString(q));
        int[] ans = new int[nums.length];
        Arrays.fill(ans, 1);
        for(int j = 0; j < nums.length; j++){
            int x = nums[j];
            for(int i = 0; i < 32; i++){
                if(!q[i].isEmpty()){
                    ans[j] = Math.max(q[i].peek() - j + 1, ans[j]);
                    if(q[i].peek() == j){
                       q[i].poll(); 
                    }
                }
            }
        }
        return ans;
    }
}
// @lc code=end
