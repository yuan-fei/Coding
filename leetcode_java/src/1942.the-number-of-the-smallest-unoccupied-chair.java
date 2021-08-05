/*
 * @lc app=leetcode id=1942 lang=java
 *
 * [1942] The Number of the Smallest Unoccupied Chair
 *
 * https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/description/
 *
 * algorithms
 * Medium (35.70%)
 * Likes:    243
 * Dislikes: 15
 * Total Accepted:    7K
 * Total Submissions: 19.5K
 * Testcase Example:  '[[1,4],[2,3],[4,6]]\n1'
 *
 * There is a party where n friends numbered from 0 to n - 1 are attending.
 * There is an infinite number of chairs in this party that are numbered from 0
 * to infinity. When a friend arrives at the party, they sit on the unoccupied
 * chair with the smallest number.
 * 
 * 
 * For example, if chairs 0, 1, and 5 are occupied when a friend comes, they
 * will sit on chair number 2.
 * 
 * 
 * When a friend leaves the party, their chair becomes unoccupied at the moment
 * they leave. If another friend arrives at that same moment, they can sit in
 * that chair.
 * 
 * You are given a 0-indexed 2D integer array times where times[i] = [arrivali,
 * leavingi], indicating the arrival and leaving times of the i^th friend
 * respectively, and an integer targetFriend. All arrival times are distinct.
 * 
 * Return the chair number that the friend numbered targetFriend will sit
 * on.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
 * Output: 1
 * Explanation: 
 * - Friend 0 arrives at time 1 and sits on chair 0.
 * - Friend 1 arrives at time 2 and sits on chair 1.
 * - Friend 1 leaves at time 3 and chair 1 becomes empty.
 * - Friend 0 leaves at time 4 and chair 0 becomes empty.
 * - Friend 2 arrives at time 4 and sits on chair 0.
 * Since friend 1 sat on chair 1, we return 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
 * Output: 2
 * Explanation: 
 * - Friend 1 arrives at time 1 and sits on chair 0.
 * - Friend 2 arrives at time 2 and sits on chair 1.
 * - Friend 0 arrives at time 3 and sits on chair 2.
 * - Friend 1 leaves at time 5 and chair 0 becomes empty.
 * - Friend 2 leaves at time 6 and chair 1 becomes empty.
 * - Friend 0 leaves at time 10 and chair 2 becomes empty.
 * Since friend 0 sat on chair 2, we return 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == times.length
 * 2 <= n <= 10^4
 * times[i].length == 2
 * 1 <= arrivali < leavingi <= 10^5
 * 0 <= targetFriend <= n - 1
 * Each arrivali time is distinct.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        int maxNonAllocated = 0;
        int n = times.length;
        int[][] events =  new int[2 * n][3];
        for(int i = 0; i < n; i++){
            int[] t = times[i];
            events[i][0] = t[0];
            events[i][1] = i;
            events[i][2] = 0;
            events[i + n][0] = t[1];
            events[i + n][1] = i;
            events[i + n][2] = -1;
        }
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]) != 0 ? Integer.compare(a[0], b[0]) : Integer.compare(a[2], b[2]));
        // System.out.println(Arrays.deepToString(events));
        int[] seatNo = new int[n];
        Arrays.fill(seatNo, -1);
        TreeSet<Integer> ts = new TreeSet<>();
        for(int[] e : events){
            if(e[2] == -1){
                ts.add(seatNo[e[1]]);
            }
            else{
                if(ts.isEmpty()){
                    seatNo[e[1]] = maxNonAllocated++;
                }
                else{
                    seatNo[e[1]] = ts.pollFirst();
                }
            }
            // System.out.println(Arrays.toString(seatNo));
            if(seatNo[targetFriend] != -1){
                return seatNo[targetFriend];
            }
        }
        return seatNo[targetFriend];
    }
}
// @lc code=end
