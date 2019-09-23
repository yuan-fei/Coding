/*
 * @lc app=leetcode id=1203 lang=java
 *
 * [1203] Sort Items by Groups Respecting Dependencies
 *
 * https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/description/
 *
 * algorithms
 * Hard (40.97%)
 * Total Accepted:    363
 * Total Submissions: 886
 * Testcase Example:  '8\n2\n[-1,-1,1,0,0,1,0,-1]\n[[],[6],[5],[6],[3,6],[],[],[]]'
 *
 * There are n items each belonging to zero or one of m groups where group[i]
 * is the group that the i-th item belongs to and it's equal to -1 if the i-th
 * item belongs to no group. The items and the groups are zero indexed. A group
 * can have no item belonging to it.
 * 
 * Return a sorted list of the items such that:
 * 
 * 
 * The items that belong to the same group are next to each other in the sorted
 * list.
 * There are some relations between these items where beforeItems[i] is a list
 * containing all the items that should come before the i-th item in the sorted
 * array (to the left of the i-th item).
 * 
 * 
 * Return any solution if there is more than one solution and return an empty
 * list if there is no solution.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems =
 * [[],[6],[5],[6],[3,6],[],[],[]]
 * Output: [6,3,4,1,5,2,0,7]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems =
 * [[],[6],[5],[6],[3],[],[4],[]]
 * Output: []
 * Explanation: This is the same as example 1 except that 4 needs to be before
 * 6 in the sorted list.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= m <= n <= 3*10^4
 * group.length == beforeItems.length == n
 * -1 <= group[i] <= m-1
 * 0 <= beforeItems[i].length <= n-1
 * 0 <= beforeItems[i][j] <= n-1
 * i != beforeItems[i][j]
 * beforeItems[i] does not contain duplicates elements.
 * 
 * 
 */
class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Graph globalGrp = new Graph();
        Map<Integer, Graph> localGrps = new HashMap<>();
        for(int i = 0; i < n; i++){
        	int groupId = group[i] >= 0? group[i] : -i - 1;	
    		globalGrp.addV(groupId);
    		if(!localGrps.containsKey(groupId)){
    			localGrps.put(groupId, new Graph());
    		}
    		localGrps.get(groupId).addV(i);
        }
        for(int i = 0; i < n; i++){
        	int toGroupId = group[i] >= 0? group[i] : -i - 1;
        	for(int itm : beforeItems.get(i)){
        		int fromGroupId = group[itm] >= 0? group[itm] : -itm - 1;
        		if(fromGroupId == toGroupId){
        			localGrps.get(fromGroupId).addE(itm, i);
        		}
        		else{
        			globalGrp.addE(fromGroupId, toGroupId);	
        		}
        		
        	}
        }
        List<Integer> orderedGrps = globalGrp.getList();
        // System.out.println(orderedGrps);
        if(orderedGrps.isEmpty()){
        	return new int[0];
        }
        int[] ans = new int[n];
        int i = 0;
        for (int gid : orderedGrps) {
        	List<Integer> orderedItms = localGrps.get(gid).getList();
        	// System.out.println(""+gid+"->"+orderedItms);
        	if(orderedItms.isEmpty()){
        		return new int[0];
        	}
        	for(int itm : orderedItms){
        		ans[i++] = itm;
        	}
        }
        return ans;
    }

    class Graph{
    	Map<Integer, List<Integer>> adj = new HashMap<>();
    	public void addV(int u){
    		if(!adj.containsKey(u)){
				adj.put(u, new ArrayList<>());
    		}
    	}

    	public void addE(int f, int t){
    		adj.get(f).add(t);
    	}

    	public List<Integer> getList(){
    		// System.out.println(adj);
    		List<Integer> res = new ArrayList<Integer>();
    		Map<Integer, Integer> visited = new HashMap<>();
    		for(int u : adj.keySet()){
    			if(!visited.containsKey(u)){
    				if(!topologicalSort(u, visited, res)){
    					return new ArrayList<>();
    				}
    			}
    		}
    		Collections.reverse(res);
    		return res;
    	}

    	boolean topologicalSort(int u, Map<Integer, Integer> visited, List<Integer> res){
    		visited.put(u, 1);
    		for(int v : adj.getOrDefault(u, new ArrayList<>())){
    			if(!visited.containsKey(v)){
    				if(!topologicalSort(v, visited, res)){
    					return false;
    				}	
    			}
    			else{
    				if(visited.get(v) == 1){
    					return false;	
    				}
    			}
    		}
    		visited.put(u, 2);
    		res.add(u);
    		return true;
    	}
    }
}
