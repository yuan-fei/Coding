/*
 * @lc app=leetcode id=1273 lang=java
 *
 * [1273] Delete Tree Nodes
 *
 * https://leetcode.com/problems/delete-tree-nodes/description/
 *
 * algorithms
 * Medium (57.51%)
 * Likes:    21
 * Dislikes: 13
 * Total Accepted:    1.4K
 * Total Submissions: 2.4K
 * Testcase Example:  '7\n[-1,0,0,1,2,2,2]\n[1,-2,4,0,-2,-1,-1]'
 *
 * A tree rooted at node 0 is given as follows:
 * 
 * 
 * The number of nodes is nodes;
 * The value of the i-th node is value[i];
 * The parent of the i-th node is parent[i].
 * 
 * 
 * Remove every subtree whose sum of values of nodes is zero.
 * 
 * After doing so, return the number of nodes remaining in the tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nodes <= 10^4
 * -10^5 <= value[i] <= 10^5
 * parent.length == nodes
 * parent[0] == -1 which indicates that 0 is the root.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
    	int N = parent.length;
		Map<Integer, Integer> s = new HashMap<>();
        for(int i = 0; i < N; i++){
        	if(!s.containsKey(i)){
        		s.put(i, 0);
        	}
        	if(parent[i] != -1){
				if(!s.containsKey(parent[i])){
	        		s.put(parent[i], 0);
	        	}
	        	s.put(parent[i], s.get(parent[i]) + 1);
        	}
        }

        int[] sum = new int[N];
        int[] cnt = new int[N];
        while(!s.isEmpty()){
        	Iterator<Map.Entry<Integer, Integer>> it = s.entrySet().iterator();
	        while(it.hasNext()){
	        	int k = it.next().getKey();
	        	if(s.get(k) == 0){
	        		it.remove();
	        		sum[k] += value[k];
	        		cnt[k] += 1;
	        		if(parent[k] >= 0){
		        		if(sum[k] != 0){
		        			sum[parent[k]] += sum[k];
		        			cnt[parent[k]] += cnt[k];
		        		}
		        		
		        		s.put(parent[k], s.get(parent[k]) - 1);	
	        		}
	        	}
	        }	
        }
        return cnt[0];
    }
}
// @lc code=end
