/*
 * @lc app=leetcode id=672 lang=java
 *
 * [672] Bulb Switcher II
 *
 * https://leetcode.com/problems/bulb-switcher-ii/description/
 *
 * algorithms
 * Medium (51.17%)
 * Likes:    168
 * Dislikes: 965
 * Total Accepted:    14.5K
 * Total Submissions: 28.3K
 * Testcase Example:  '1\n1'
 *
 * There is a room with n bulbs labeled from 1 to n that all are turned on
 * initially, and four buttons on the wall. Each of the four buttons has a
 * different functionality where:
 * 
 * 
 * Button 1: Flips the status of all the bulbs.
 * Button 2: Flips the status of all the bulbs with even labels (i.e., 2, 4,
 * ...).
 * Button 3: Flips the status of all the bulbs with odd labels (i.e., 1, 3,
 * ...).
 * Button 4: Flips the status of all the bulbs with a label j = 3k + 1 where k
 * = 0, 1, 2, ... (i.e., 1, 4, 7, 10, ...).
 * 
 * 
 * You will press one of the four mentioned buttons exactly presses times.
 * 
 * Given the two integers n and presses, return the number of different
 * statuses after pressing the four buttons exactly presses times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1, presses = 1
 * Output: 2
 * Explanation: Status can be: [on], [off].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, presses = 1
 * Output: 3
 * Explanation: Status can be: [on, off], [off, on], [off, off].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 3, presses = 1
 * Output: 4
 * Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off],
 * [off, on, on].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 1000
 * 0 <= presses <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int flipLights(int n, int presses) {
    	if(presses == 0){
    		return 1;
    	}
        n = Math.min(n, 6);
        if(presses % 2 == 0){
        	presses = Math.min(presses, 4);	
        }
        else{
        	presses = Math.min(presses, 3);
        }
        int[] masks = {0b111111, 0b101010, 0b010101, 0b001001};
        Queue<Integer> status = new LinkedList<>();
        status.offer(0);
		for(int i = 0; i < presses; i++){
			for(int x = status.size(); x > 0; x--){
				int cur = status.poll();
				for(int m : masks){
					status.offer((cur ^ m) & ((1 << n) - 1));
				}
			}
		}
		Set<Integer> finalStatus = new HashSet<>(status); 
		// System.out.println(finalStatus);
		return finalStatus.size();
    }

}
// @lc code=end
