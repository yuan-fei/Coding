/*
 * @lc app=leetcode id=77 lang=java
 *
 * [77] Combinations
 *
 * https://leetcode.com/problems/combinations/description/
 *
 * algorithms
 * Medium (47.59%)
 * Total Accepted:    215.9K
 * Total Submissions: 442K
 * Testcase Example:  '4\n2'
 *
 * Given two integers n and k, return all possible combinations of k numbers
 * out of 1 ... n.
 * 
 * Example:
 * 
 * 
 * Input: n = 4, k = 2
 * Output:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 * 
 * 
 */
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> cur = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(n, k, 1, cur, result);
        return result;
    }

    private void dfs(int n, int k, int pos, List<Integer> cur, List<List<Integer>> result){
    	if(0 == k){
    		result.add(new ArrayList<>(cur));
    		return;
    	}
    	for(int i = pos; i <= n; i++){
    		cur.add(i);
    		dfs(n, k-1, i+1, cur, result);
    		cur.remove(cur.size()-1);
    	}
    }
}
