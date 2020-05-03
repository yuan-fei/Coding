/*
 * @lc app=leetcode id=1383 lang=java
 *
 * [1383] Maximum Performance of a Team
 *
 * https://leetcode.com/problems/maximum-performance-of-a-team/description/
 *
 * algorithms
 * Hard (29.14%)
 * Likes:    150
 * Dislikes: 11
 * Total Accepted:    4K
 * Total Submissions: 13.9K
 * Testcase Example:  '6\n[2,10,3,1,5,8]\n[5,4,3,9,7,2]\n2'
 *
 * There are n engineers numbered from 1 to n and two arrays: speed and
 * efficiency, where speed[i] and efficiency[i] represent the speed and
 * efficiency for the i-th engineer respectively. Return the maximum
 * performance of a team composed of at most k engineers, since the answer can
 * be a huge number, return this modulo 10^9 + 7.
 * 
 * The performance of a team is the sum of their engineers' speeds multiplied
 * by the minimum efficiency among their engineers. 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation: 
 * We have the maximum performance of the team by selecting engineer 2 (with
 * speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7).
 * That is, performance = (10 + 5) * min(4, 7) = 60.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1,
 * engineer 2 and engineer 5 to get the maximum performance of the team. That
 * is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^5
 * speed.length == n
 * efficiency.length == n
 * 1 <= speed[i] <= 10^5
 * 1 <= efficiency[i] <= 10^8
 * 1 <= k <= n
 * 
 */

// @lc code=start
class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    	Eng[] engs = new Eng[n];
        for (int i = 0; i < n; i++) {
        	engs[i] = new Eng(speed[i], efficiency[i]);
        }
        Arrays.sort(engs, (a, b) -> -Long.compare(a.e, b.e));
        PriorityQueue<Long> h = new PriorityQueue<>();
        long totalSpeed = 0;
        long max = 0;
        for (Eng e: engs) {
        	h.offer(e.s);
        	totalSpeed += e.s;
        	if(h.size() > k){
        		long minSpeed = h.poll();
        		totalSpeed -= minSpeed;
        	}
        	max = Math.max(max, totalSpeed * e.e);
        }
        return (int)(max % 1000000007);
    }

    class Eng{
    	long s;
    	long e;
    	Eng(long speed, long efficiency){
    		s = speed;
    		e = efficiency;
    	}
    }
}
// @lc code=end