/*
 * @lc app=leetcode id=1962 lang=java
 *
 * [1962] Remove Stones to Minimize the Total
 *
 * https://leetcode.com/problems/remove-stones-to-minimize-the-total/description/
 *
 * algorithms
 * Medium (51.57%)
 * Likes:    119
 * Dislikes: 5
 * Total Accepted:    9.4K
 * Total Submissions: 18.2K
 * Testcase Example:  '[5,4,9]\n2'
 *
 * You are given a 0-indexed integer array piles, where piles[i] represents the
 * number of stones in the i^th pile, and an integer k. You should apply the
 * following operation exactly k times:
 * 
 * 
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * 
 * 
 * Notice that you can apply the operation on the same pile more than once.
 * 
 * Return the minimum possible total number of stones remaining after applying
 * the k operations.
 * 
 * floor(x) is the greatest integer that is smaller than or equal to x (i.e.,
 * rounds x down).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: piles = [5,4,9], k = 2
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [5,4,5].
 * - Apply the operation on pile 0. The resulting piles are [3,4,5].
 * The total number of stones in [3,4,5] is 12.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: piles = [4,3,6,7], k = 3
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 3. The resulting piles are [4,3,3,7].
 * - Apply the operation on pile 4. The resulting piles are [4,3,3,4].
 * - Apply the operation on pile 0. The resulting piles are [2,3,3,4].
 * The total number of stones in [2,3,3,4] is 12.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= piles.length <= 10^5
 * 1 <= piles[i] <= 10^4
 * 1 <= k <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for(int x : piles){
            q.offer(x);
        }
        for(int i = 0; i < k; i++){
            int x = q.poll();
            q.offer(x - x / 2);
        }
        int ans = 0;
        while(!q.isEmpty()){
            ans += q.poll();
        }
        return ans;
    }
}
// @lc code=end
