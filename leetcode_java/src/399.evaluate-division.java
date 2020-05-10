/*
 * @lc app=leetcode id=399 lang=java
 *
 * [399] Evaluate Division
 *
 * https://leetcode.com/problems/evaluate-division/description/
 *
 * algorithms
 * Medium (50.46%)
 * Likes:    2013
 * Dislikes: 153
 * Total Accepted:    118.1K
 * Total Submissions: 234.1K
 * Testcase Example:  '[["a","b"],["b","c"]]\n[2.0,3.0]\n[["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]'
 *
 * Equations are given in the format A / B = k, where A and B are variables
 * represented as strings, and k is a real number (floating point number).
 * Given some queries, return the answers. If the answer does not exist, return
 * -1.0.
 * 
 * Example:
 * Given  a / b = 2.0, b / c = 3.0.
 * queries are:  a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return  [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * 
 * The input is:  vector<pair<string, string>> equations, vector<double>&
 * values, vector<pair<string, string>> queries , where equations.size() ==
 * values.size(), and the values are positive. This represents the equations.
 * Return  vector<double>.
 * 
 * According to the example above:
 * 
 * 
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"]
 * ]. 
 * 
 * 
 * 
 * The input is always valid. You may assume that evaluating the queries will
 * result in no division by zero and there is no contradiction.
 * 
 */

// @lc code=start
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    	Map<String, List<Edge>> m = new HashMap<>();
    	Map<String, Double> vars = new HashMap();
    	for(int i = 0; i < values.length; i++){
    		List<String> e = equations.get(i);
    		if(values[i] == 0d){
    			vars.put(e.get(0), 0d);
    		}
    		else{
	    		if(!m.containsKey(e.get(0))){
	    			m.put(e.get(0), new ArrayList<>());
	    		}
	    		m.get(e.get(0)).add(new Edge(e.get(1), values[i]));
	    		if(!m.containsKey(e.get(1))){
	    			m.put(e.get(1), new ArrayList<>());
	    		}
	    		m.get(e.get(1)).add(new Edge(e.get(0), 1 / values[i]));	
    		}
    		
    	}
    	Set<String> visited = new HashSet<>();
    	
        // System.out.println(m);
        // System.out.println(vars);
        double[] ret = new double[queries.size()];
        Arrays.fill(ret, -1);
		for(String k : m.keySet()){
    		if(!visited.contains(k)){
    			vars = new HashMap<>();
    			visited.add(k);
	    		vars.put(k, 1d);
				dfs(m, vars, visited, k);	
				for (int i = 0; i < queries.size(); i++) {
		        	if(vars.containsKey(queries.get(i).get(0)) && vars.containsKey(queries.get(i).get(1))){
		        		ret[i] = vars.get(queries.get(i).get(0)) / vars.get(queries.get(i).get(1));	
		        	}
		        }
    		}
    	}
        
        return ret;
    }

    void dfs(Map<String, List<Edge>> adj, Map<String, Double> vars, Set<String> visited, String cur){
    	for(Edge e : adj.getOrDefault(cur, new ArrayList<Edge>())){
    		if(!vars.containsKey(e.v)){
    			visited.add(e.v);
    			vars.put(e.v, vars.get(cur) / e.c);
    			dfs(adj, vars, visited, e.v);
    		}
    	}
    }

    static class Edge {
    	String v;
    	double c;
    	Edge(String vertex, double value){
    		v = vertex;
    		c = value;
    	}
    	public String toString(){
    		return "<" + v + ", " + c + ">";
    	}
    }
}
// @lc code=end
