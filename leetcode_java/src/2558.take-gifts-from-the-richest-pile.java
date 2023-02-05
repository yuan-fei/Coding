/*
 * @lc app=leetcode id=2558 lang=java
 *
 * [2558] Take Gifts From the Richest Pile
 *
 * https://leetcode.com/problems/take-gifts-from-the-richest-pile/description/
 *
 * algorithms
 * Easy (63.19%)
 * Likes:    36
 * Dislikes: 4
 * Total Accepted:    13.6K
 * Total Submissions: 21.5K
 * Testcase Example:  '[25,64,9,4,100]\n4'
 *
 * You are given an integer array gifts denoting the number of gifts in various
 * piles. Every second, you do the following:
 * 
 * 
 * Choose the pile with the maximum number of gifts.
 * If there is more than one pile with the maximum number of gifts, choose
 * any.
 * Leave behind the floor of the square root of the number of gifts in the
 * pile. Take the rest of the gifts.
 * 
 * 
 * Return the number of gifts remaining after k seconds.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: gifts = [25,64,9,4,100], k = 4
 * Output: 29
 * Explanation: 
 * The gifts are taken in the following way:
 * - In the first second, the last pile is chosen and 10 gifts are left behind.
 * - Then the second pile is chosen and 8 gifts are left behind.
 * - After that the first pile is chosen and 5 gifts are left behind.
 * - Finally, the last pile is chosen again and 3 gifts are left behind.
 * The final remaining gifts are [5,8,9,4,3], so the total number of gifts
 * remaining is 29.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: gifts = [1,1,1,1], k = 4
 * Output: 4
 * Explanation: 
 * In this case, regardless which pile you choose, you have to leave behind 1
 * gift in each pile. 
 * That is, you can't take any pile with you. 
 * So, the total gifts remaining are 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= gifts.length <= 10^3
 * 1 <= gifts[i] <= 10^9
 * 1 <= k <= 10^3
 * 
 * 
 */

// @lc code=start
class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for(int x : gifts){
            q.offer(x);
        }
        for(int i = 0; i < k; i++){
            int x = q.poll();
            q.offer((int)Math.sqrt(x));
        }
        long ans = 0;
        while(!q.isEmpty()){
            ans += q.poll();
        }
        return ans;
    }
}
// @lc code=end
