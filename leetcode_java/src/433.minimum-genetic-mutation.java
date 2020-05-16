/*
 * @lc app=leetcode id=433 lang=java
 *
 * [433] Minimum Genetic Mutation
 *
 * https://leetcode.com/problems/minimum-genetic-mutation/description/
 *
 * algorithms
 * Medium (40.85%)
 * Likes:    367
 * Dislikes: 49
 * Total Accepted:    29.1K
 * Total Submissions: 71.1K
 * Testcase Example:  '"AACCGGTT"\n"AACCGGTA"\n["AACCGGTA"]'
 *
 * A gene string can be represented by an 8-character long string, with choices
 * from "A", "C", "G", "T".
 * 
 * Suppose we need to investigate about a mutation (mutation from "start" to
 * "end"), where ONE mutation is defined as ONE single character changed in the
 * gene string.
 * 
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * 
 * Also, there is a given gene "bank", which records all the valid gene
 * mutations. A gene must be in the bank to make it a valid gene string.
 * 
 * Now, given 3 things - start, end, bank, your task is to determine what is
 * the minimum number of mutations needed to mutate from "start" to "end". If
 * there is no such a mutation, return -1.
 * 
 * Note:
 * 
 * 
 * Starting point is assumed to be valid, so it might not be included in the
 * bank.
 * If multiple mutations are needed, all mutations during in the sequence must
 * be valid.
 * You may assume start and end string is not the same.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * 
 * return: 1
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * 
 * return: 2
 * 
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * 
 * return: 3
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minMutation(String start, String end, String[] bank) {
    	Set<String> s = new HashSet<>();
    	for(int i = 0 ; i < bank.length; i++){
    		s.add(bank[i]);
    	}
    	if(!s.contains(end)){
    		return -1;
    	}
    	s.remove(start);
    	s.remove(end);
    	List<String> gene = new ArrayList<>(s);
    	gene.add(start);
    	gene.add(end);
    	// System.out.println(gene);
    	List<Integer>[] adj = new List[gene.size()];
    	for(int i = 0; i < gene.size(); i++){
    		adj[i] = new ArrayList<>();
    	}
        for(int i = 0 ; i < gene.size(); i++){
        	for(int j = i + 1; j < gene.size(); j++){
        		if(diff(gene.get(i), gene.get(j)) == 1){
        			adj[i].add(j);
        			adj[j].add(i);
        		}
        	}
        }
        int n = gene.size();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.offer(n - 2);
        visited[n - 2] = true;
        int l = 0;
        while(!q.isEmpty()){
        	// System.out.println(q);
        	for(int i = q.size(); i > 0; i--){
        		int cur = q.poll();
        		if(cur == n - 1){
        			return l;
        		}
        		else{
        			for (int next : adj[cur]) {
        				if(!visited[next]){
        					q.offer(next);	
        				}
        			}
        		}
        	}
        	l++;
        }
        return -1;
    }

    int diff(String s, String t){
    	int ret = 0;
    	for(int i = 0; i < s.length(); i++){
    		if(s.charAt(i) != t.charAt(i)){
    			ret++;
    		}

    	}
    	return ret;
    }
}
// @lc code=end
