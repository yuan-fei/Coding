/*
 * @lc app=leetcode id=2251 lang=java
 *
 * [2251] Number of Flowers in Full Bloom
 *
 * https://leetcode.com/problems/number-of-flowers-in-full-bloom/description/
 *
 * algorithms
 * Hard (48.30%)
 * Likes:    195
 * Dislikes: 8
 * Total Accepted:    5K
 * Total Submissions: 10.3K
 * Testcase Example:  '[[1,6],[3,7],[9,12],[4,13]]\n[2,3,7,11]'
 *
 * You are given a 0-indexed 2D integer array flowers, where flowers[i] =
 * [starti, endi] means the i^th flower will be in full bloom from starti to
 * endi (inclusive). You are also given a 0-indexed integer array persons of
 * size n, where persons[i] is the time that the i^th person will arrive to see
 * the flowers.
 * 
 * Return an integer array answer of size n, where answer[i] is the number of
 * flowers that are in full bloom when the i^th person arrives.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: flowers = [[1,6],[3,7],[9,12],[4,13]], persons = [2,3,7,11]
 * Output: [1,2,2,2]
 * Explanation: The figure above shows the times when the flowers are in full
 * bloom and when the people arrive.
 * For each person, we return the number of flowers in full bloom during their
 * arrival.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: flowers = [[1,10],[3,3]], persons = [3,3,2]
 * Output: [2,2,1]
 * Explanation: The figure above shows the times when the flowers are in full
 * bloom and when the people arrive.
 * For each person, we return the number of flowers in full bloom during their
 * arrival.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= flowers.length <= 5 * 10^4
 * flowers[i].length == 2
 * 1 <= starti <= endi <= 10^9
 * 1 <= persons.length <= 5 * 10^4
 * 1 <= persons[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        int n = flowers.length;
        int m = persons.length;
        int[][] events =new int[n * 2 + m][];
        for(int i = 0; i < n; i++){
            events[i] = new int[]{flowers[i][0], 1, i};
            events[n + i] = new int[]{flowers[i][1] + 1, -1, i};
        }
        for(int i = 0; i < m; i++){
            events[2 * n + i] = new int[]{persons[i], 2, i};
        }
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]) != 0 ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        int[] ans = new int[m];
        int curBloom = 0;
        for(int i = 0; i < events.length; i++){
            if(events[i][1] == 2){
                ans[events[i][2]] = curBloom;
            }
            else{
                curBloom += events[i][1];
            }
        }
        return ans;
    }
}
// @lc code=end
