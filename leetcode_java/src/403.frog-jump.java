/*
 * @lc app=leetcode id=403 lang=java
 *
 * [403] Frog Jump
 *
 * https://leetcode.com/problems/frog-jump/description/
 *
 * algorithms
 * Hard (38.93%)
 * Likes:    871
 * Dislikes: 93
 * Total Accepted:    78.5K
 * Total Submissions: 201.6K
 * Testcase Example:  '[0,1,3,4,5,7,9,10,12]'
 *
 * A frog is crossing a river. The river is divided into x units and at each
 * unit there may or may not exist a stone. The frog can jump on a stone, but
 * it must not jump into the water.
 * 
 * Given a list of stones' positions (in units) in sorted ascending order,
 * determine if the frog is able to cross the river by landing on the last
 * stone. Initially, the frog is on the first stone and assume the first jump
 * must be 1 unit.
 * 
 * 
 * If the frog's last jump was k units, then its next jump must be either k -
 * 1, k, or k + 1 units. Note that the frog can only jump in the forward
 * direction.
 * 
 * Note:
 * 
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 2^31.
 * The first stone's position is always 0.
 * 
 * 
 * 
 * Example 1:
 * 
 * [0,1,3,5,6,8,12,17]
 * 
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 * 
 * Return true. The frog can jump to the last stone by jumping 
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
 * 2 units to the 4th stone, then 3 units to the 6th stone, 
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 * 
 * 
 * 
 * Example 2:
 * 
 * [0,1,2,3,4,8,9,11]
 * 
 * Return false. There is no way to jump to the last stone as 
 * the gap between the 5th and 6th stone is too large.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canCross(int[] stones) {
    	Set<Integer> s = new HashSet<>();
    	for(int x : stones){
    		s.add(x);
    	}
    	if(!s.contains(1)){
    		return false;
    	}
        return dfs(s, stones[stones.length - 1], 1, 1);
    }

    Set<String> cache = new HashSet<>();
    boolean dfs(Set<Integer> stones, int target, int pos, int lastStep){
    	if(pos == target){
    		return true;
    	}
    	if(cache.contains(pos + "," + lastStep)){
    		return false;
    	}
    	for(int off : new int[]{-1, 0, 1}){
    		int nextStep = lastStep + off;
    		if(nextStep > 0 && stones.contains(pos + nextStep)){
    			if(dfs(stones, target, pos + nextStep, nextStep)){
    				return true;
    			}
    		}
    	}
    	cache.add(pos + "," + lastStep);
    	return false;
    }
}
// @lc code=end
