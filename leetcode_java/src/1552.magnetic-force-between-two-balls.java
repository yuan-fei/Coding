/*
 * @lc app=leetcode id=1552 lang=java
 *
 * [1552] Magnetic Force Between Two Balls
 *
 * https://leetcode.com/problems/magnetic-force-between-two-balls/description/
 *
 * algorithms
 * Medium (44.78%)
 * Likes:    247
 * Dislikes: 30
 * Total Accepted:    6.5K
 * Total Submissions: 14.6K
 * Testcase Example:  '[1,2,3,4,7]\n3'
 *
 * In universe Earth C-137, Rick discovered a special form of magnetic force
 * between two balls if they are put in his new invented basket. Rick has n
 * empty baskets, the i^th basket is at position[i], Morty has m balls and
 * needs to distribute the balls into the baskets such that the minimum
 * magnetic force between any two balls is maximum.
 * 
 * Rick stated that magnetic force between two different balls at positions x
 * and y is |x - y|.
 * 
 * Given the integer array position and the integer m. Return the required
 * force.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: position = [1,2,3,4,7], m = 3
 * Output: 3
 * Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the
 * magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is
 * 3. We cannot achieve a larger minimum magnetic force than 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: position = [5,4,3,2,1,1000000000], m = 2
 * Output: 999999999
 * Explanation: We can use baskets 1 and 1000000000.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == position.length
 * 2 <= n <= 10^5
 * 1 <= position[i] <= 10^9
 * All integers in position are distinct.
 * 2 <= m <= position.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int low = 1;
        int high = position[position.length - 1];
        while(low + 1 < high){
        	int mid = (low + high) / 2;
        	if(feasible(position, mid, m)){
        		low = mid;
        	}
        	else{
        		high = mid;
        	}
        }
        if(feasible(position, high, m)){
        	return high;
        }
        else{
        	return low;
        }
    }

    boolean feasible(int[] position, int minD, int m){
    	int left = position[0];
    	m--;
    	for(int i = 1; i < position.length; i++){
    		if(position[i] - left >= minD){
    			left = position[i];
    			m--;
    		}
    	}
    	return m <= 0;
    }
}
// @lc code=end
