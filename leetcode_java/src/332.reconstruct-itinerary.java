/*
 * @lc app=leetcode id=332 lang=java
 *
 * [332] Reconstruct Itinerary
 *
 * https://leetcode.com/problems/reconstruct-itinerary/description/
 *
 * algorithms
 * Medium (33.74%)
 * Likes:    1190
 * Dislikes: 763
 * Total Accepted:    114.3K
 * Total Submissions: 338.5K
 * Testcase Example:  '[["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]'
 *
 * Given a list of airline tickets represented by pairs of departure and
 * arrival airports [from, to], reconstruct the itinerary in order. All of the
 * tickets belong to a man who departs from JFK. Thus, the itinerary must begin
 * with JFK.
 * 
 * Note:
 * 
 * 
 * If there are multiple valid itineraries, you should return the itinerary
 * that has the smallest lexical order when read as a single string. For
 * example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
 * ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is
 * ["JFK","SFO","ATL","JFK","ATL","SFO"].
 * But it is larger in lexical order.
 * 
 * 
 */

// @lc code=start
class Solution {
	List<List<String>> tickets;
	Map<String, TreeSet<Integer>> adj;
	List<String> ans;
	Set<Integer> visited;
    public List<String> findItinerary(List<List<String>> tickets) {
    	this.tickets = tickets;
        adj = new HashMap<>();
        for(int i = 0; i < tickets.size(); i++){
        	List<String> t = tickets.get(i);
        	String from = t.get(0);
        	if(!adj.containsKey(from)){
				adj.put(from, new TreeSet<>((a, b) -> tickets.get(a).get(1).compareTo(tickets.get(b).get(1)) != 0 ? tickets.get(a).get(1).compareTo(tickets.get(b).get(1)) : Integer.compare(a, b)));
        	}
        	adj.get(from).add(i);
        }
        ans = new ArrayList<>();
        visited = new HashSet<>();
        String from = "JFK";
        if(!adj.containsKey(from)){
        	return ans;
        }
        dfs(from, -1);
        return ans;
    }

    boolean dfs(String from, int edge){
    	visited.add(edge);
    	ans.add(from);
    	// System.out.println(ans);
    	if(visited.size() == tickets.size() + 1){
    		return true;
    	}
    	for(int e : adj.getOrDefault(from, new TreeSet<>())){
    		if(!visited.contains(e)){
    			if(dfs(tickets.get(e).get(1), e)){
    				return true;
    			}
    		}
    	}
    	ans.remove(ans.size() - 1);
    	visited.remove(edge);
    	return false;
    }
}
// @lc code=end
