/*
 * @lc app=leetcode id=1424 lang=java
 *
 * [1424] Diagonal Traverse II
 *
 * https://leetcode.com/problems/diagonal-traverse-ii/description/
 *
 * algorithms
 * Medium (38.28%)
 * Likes:    158
 * Dislikes: 24
 * Total Accepted:    8.3K
 * Total Submissions: 21.6K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given a list of lists of integers, nums, return all elements of nums in
 * diagonal order as shown in the below images.
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,4,2,7,5,3,8,6,9]
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 * Output: [1,4,2,5,3,8,6,9,7,10,11]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums = [[1,2,3,4,5,6]]
 * Output: [1,2,3,4,5,6]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i].length <= 10^5
 * 1 <= nums[i][j] <= 10^9
 * There at most 10^5 elements in nums.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Queue<int[]> q = new LinkedList<>();
        List<Integer> out = new ArrayList<>();
        Set<String> s = new HashSet<>();
        q.offer(new int[]{0, 0});
        while(!q.isEmpty()){
        	for(int l = q.size(); l > 0; l--){
				int[] pos = q.poll();
				out.add(nums.get(pos[0]).get(pos[1]));
				for(int[] offset : new int[][]{{1, 0},{0, 1}}){
					int[] newPos = new int[]{pos[0] + offset[0], pos[1] + offset[1]};
					String hash = newPos[0] + "," + newPos[1];
					if(0 <= newPos[0] && newPos[0] < nums.size() && 0 <= newPos[1] && newPos[1] < nums.get(newPos[0]).size() && !s.contains(hash)){
						q.offer(newPos);
						s.add(hash);
					}
				}
        	}
        }
        return out.stream().mapToInt(Integer::intValue).toArray();
    }
}
// @lc code=end
