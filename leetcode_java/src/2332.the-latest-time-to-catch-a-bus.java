/*
 * @lc app=leetcode id=2332 lang=java
 *
 * [2332] The Latest Time to Catch a Bus
 *
 * https://leetcode.com/problems/the-latest-time-to-catch-a-bus/description/
 *
 * algorithms
 * Medium (20.04%)
 * Likes:    138
 * Dislikes: 450
 * Total Accepted:    7.3K
 * Total Submissions: 36.6K
 * Testcase Example:  '[10,20]\n[2,17,18,19]\n2'
 *
 * You are given a 0-indexed integer array buses of length n, where buses[i]
 * represents the departure time of the i^th bus. You are also given a
 * 0-indexed integer array passengers of length m, where passengers[j]
 * represents the arrival time of the j^th passenger. All bus departure times
 * are unique. All passenger arrival times are unique.
 * 
 * You are given an integer capacity, which represents the maximum number of
 * passengers that can get on each bus.
 * 
 * The passengers will get on the next available bus. You can get on a bus that
 * will depart at x minutes if you arrive at y minutes where y <= x, and the
 * bus is not full. Passengers with the earliest arrival times get on the bus
 * first.
 * 
 * Return the latest time you may arrive at the bus station to catch a bus. You
 * cannot arrive at the same time as another passenger.
 * 
 * Note: The arrays buses and passengers are not necessarily sorted.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: buses = [10,20], passengers = [2,17,18,19], capacity = 2
 * Output: 16
 * Explanation: 
 * The 1^st bus departs with the 1^st passenger. 
 * The 2^nd bus departs with you and the 2^nd passenger.
 * Note that you must not arrive at the same time as the passengers, which is
 * why you must arrive before the 2^nd^ passenger to catch the bus.
 * 
 * Example 2:
 * 
 * 
 * Input: buses = [20,30,10], passengers = [19,13,26,4,25,11,21], capacity = 2
 * Output: 20
 * Explanation: 
 * The 1^st bus departs with the 4^th passenger. 
 * The 2^nd bus departs with the 6^th and 2^nd^ passengers.
 * The 3^rd bus departs with the 1^s^t passenger and you.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == buses.length
 * m == passengers.length
 * 1 <= n, m, capacity <= 10^5
 * 2 <= buses[i], passengers[i] <= 10^9
 * Each element in buses is unique.
 * Each element in passengers is unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        int n = buses.length;
        int m = passengers.length;
        int[][] events = new int[n + m][];
        for(int i = 0; i < n; i++){
            events[i] = new int[]{0, buses[i]};
        }
        for(int i = 0; i < m; i++){
            events[n + i] = new int[]{1, passengers[i]};
        }
        Arrays.sort(events, (a, b) -> Integer.compare(a[1], b[1]) != 0 ? Integer.compare(a[1], b[1]) : Integer.compare(b[0], a[0]));
        Queue<Integer> q = new LinkedList<>();
        int max = 0;
        Set<Integer> s = new HashSet<>();
        // System.out.println(Arrays.deepToString(events));
        for(int[] e : events){
            if(e[0] == 1){
                q.offer(e[1]);
                s.add(e[1]);
            }
            else{
                int i = 0;
                for(; i < capacity; i++){
                    if(!q.isEmpty()){
                        int x = q.poll();
                        if(!s.contains(x - 1)){
                            max = Math.max(max, x - 1);
                        }
                    }
                    else{
                        break;
                    }
                }
                n--;
                if(n == 0){
                    if(i < capacity && !s.contains(e[1])){
                        max = Math.max(max, e[1]);
                    }
                    return max;
                }
            }
        }
        return max;
    }
}
// @lc code=end
