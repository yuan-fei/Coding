/*
 * @lc app=leetcode id=1282 lang=java
 *
 * [1282] Group the People Given the Group Size They Belong To
 *
 * https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/description/
 *
 * algorithms
 * Medium (84.41%)
 * Likes:    17
 * Dislikes: 2
 * Total Accepted:    3K
 * Total Submissions: 3.6K
 * Testcase Example:  '[3,3,3,3,3,1,3]'
 *
 * There are n people whose IDs go from 0 to n - 1 and each person belongs
 * exactly to one group. Given the array groupSizes of length n telling the
 * group size each person belongs to, return the groups there are and the
 * people's IDs each group includes.
 * 
 * You can return any solution in any order and the same applies for IDs. Also,
 * it is guaranteed that there exists at least one solution. 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: groupSizes = [3,3,3,3,3,1,3]
 * Output: [[5],[0,1,2],[3,4,6]]
 * Explanation: 
 * Other possible solutions are [[2,1,6],[5],[0,4,3]] and
 * [[5],[0,6,2],[4,3,1]].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: groupSizes = [2,1,3,3,3,2]
 * Output: [[1],[0,5],[2,3,4]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * groupSizes.length == n
 * 1 <= n <= 500
 * 1 <= groupSizes[i] <= n
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer>[] groups = new List[groupSizes.length + 1];
        for(int i = 0 ; i < groupSizes.length; i++){
        	if(groups[groupSizes[i]] == null){
        		groups[groupSizes[i]] = new ArrayList<>();
        	}
        	groups[groupSizes[i]].add(i);
        	if(groups[groupSizes[i]].size() == groupSizes[i]){
        		ans.add(groups[groupSizes[i]]);
        		groups[groupSizes[i]] = new ArrayList<>();
        	}
        }
        return ans;
    }
}
// @lc code=end
