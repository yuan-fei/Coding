/*
 * @lc app=leetcode id=2335 lang=java
 *
 * [2335] Minimum Amount of Time to Fill Cups
 *
 * https://leetcode.com/problems/minimum-amount-of-time-to-fill-cups/description/
 *
 * algorithms
 * Easy (53.64%)
 * Likes:    153
 * Dislikes: 31
 * Total Accepted:    16.1K
 * Total Submissions: 30K
 * Testcase Example:  '[1,4,2]'
 *
 * You have a water dispenser that can dispense cold, warm, and hot water.
 * Every second, you can either fill up 2 cups with different types of water,
 * or 1 cup of any type of water.
 * 
 * You are given a 0-indexed integer array amount of length 3 where amount[0],
 * amount[1], and amount[2] denote the number of cold, warm, and hot water cups
 * you need to fill respectively. Return the minimum number of seconds needed
 * to fill up all the cups.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: amount = [1,4,2]
 * Output: 4
 * Explanation: One way to fill up the cups is:
 * Second 1: Fill up a cold cup and a warm cup.
 * Second 2: Fill up a warm cup and a hot cup.
 * Second 3: Fill up a warm cup and a hot cup.
 * Second 4: Fill up a warm cup.
 * It can be proven that 4 is the minimum number of seconds needed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: amount = [5,4,4]
 * Output: 7
 * Explanation: One way to fill up the cups is:
 * Second 1: Fill up a cold cup, and a hot cup.
 * Second 2: Fill up a cold cup, and a warm cup.
 * Second 3: Fill up a cold cup, and a warm cup.
 * Second 4: Fill up a warm cup, and a hot cup.
 * Second 5: Fill up a cold cup, and a hot cup.
 * Second 6: Fill up a cold cup, and a warm cup.
 * Second 7: Fill up a hot cup.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: amount = [5,0,0]
 * Output: 5
 * Explanation: Every second, we fill up a cold cup.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * amount.length == 3
 * 0 <= amount[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int fillCups(int[] amount) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for(int i = 0; i < 3; i++){
            if(amount[i] > 0){
                q.offer(amount[i]);
            }
        }
        int ans = 0;
        while(!q.isEmpty()){
            int x = q.poll();
            x--;

            if(!q.isEmpty()){
                int y = q.poll();
                y--;
                if(y > 0){
                    q.offer(y);
                }
            }
            if(x > 0){
                q.offer(x);
            }
            ans++;
        }
        return ans;
    }
}
// @lc code=end
