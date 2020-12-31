/*
 * @lc app=leetcode id=1705 lang=java
 *
 * [1705] Maximum Number of Eaten Apples
 *
 * https://leetcode.com/problems/maximum-number-of-eaten-apples/description/
 *
 * algorithms
 * Medium (30.23%)
 * Likes:    121
 * Dislikes: 95
 * Total Accepted:    4.9K
 * Total Submissions: 16.3K
 * Testcase Example:  '[1,2,3,5,2]\n[3,2,1,4,2]'
 *
 * There is a special kind of apple tree that grows apples every day for n
 * days. On the i^th day, the tree grows apples[i] apples that will rot after
 * days[i] days, that is on day i + days[i] the apples will be rotten and
 * cannot be eaten. On some days, the apple tree does not grow any apples,
 * which are denoted by apples[i] == 0 and days[i] == 0.
 * 
 * You decided to eat at most one apple a day (to keep the doctors away). Note
 * that you can keep eating after the first n days.
 * 
 * Given two integer arrays days and apples of length n, return the maximum
 * number of apples you can eat.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * Output: 7
 * Explanation: You can eat 7 apples:
 * - On the first day, you eat an apple that grew on the first day.
 * - On the second day, you eat an apple that grew on the second day.
 * - On the third day, you eat an apple that grew on the second day. After this
 * day, the apples that grew on the third day rot.
 * - On the fourth to the seventh days, you eat apples that grew on the fourth
 * day.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * Output: 5
 * Explanation: You can eat 5 apples:
 * - On the first to the third day you eat apples that grew on the first day.
 * - Do nothing on the fouth and fifth days.
 * - On the sixth and seventh days you eat apples that grew on the sixth
 * day.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * apples.length == n
 * days.length == n
 * 1 <= n <= 2 * 10^4
 * 0 <= apples[i], days[i] <= 2 * 10^4
 * days[i] = 0 if and only if apples[i] = 0.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int eatenApples(int[] apples, int[] days) {
        int res = 0;
        int n = apples.length;
                                        
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[1] - b[1]); //[]{apple count, expiry date}
        
        for(int i=0; i<n || !queue.isEmpty(); ++i) { // || !queue.isEmpty() cos we need to continue even after n
            if(i<n) 
                queue.offer(new int[]{ apples[i], i + days[i] });
            
            while(!queue.isEmpty() && queue.peek()[1] <= i) { // spoiled apples, so remove the tray
                queue.poll();
            }
            
            if(!queue.isEmpty()) {
                ++res;
                if(--queue.peek()[0] == 0) // remove if no apples left on this tray
                    queue.poll();
            }
        }
        
        return res;
    }
}
// @lc code=end
