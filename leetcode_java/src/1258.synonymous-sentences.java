/*
 * @lc app=leetcode id=1258 lang=java
 *
 * [1258] Synonymous Sentences
 *
 * https://leetcode.com/problems/synonymous-sentences/description/
 *
 * algorithms
 * Medium (53.58%)
 * Likes:    16
 * Dislikes: 13
 * Total Accepted:    1.1K
 * Total Submissions: 2K
 * Testcase Example:  '[["happy","joy"],["sad","sorrow"],["joy","cheerful"]]\n"I am happy today but was sad yesterday"'
 *
 * Given a list of pairs of equivalent words synonyms and a sentence text,
 * Return all possible synonymous sentences sorted lexicographically.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
 * text = "I am happy today but was sad yesterday"
 * Output:
 * ["I am cheerful today but was sad yesterday",
 * ​​​​​​​"I am cheerful today but was sorrow yesterday",
 * "I am happy today but was sad yesterday",
 * "I am happy today but was sorrow yesterday",
 * "I am joy today but was sad yesterday",
 * "I am joy today but was sorrow yesterday"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= synonyms.length <= 10
 * synonyms[i].length == 2
 * synonyms[0] != synonyms[1]
 * All words consist of at most 10 English letters only.
 * text is a single space separated sentence of at most 10 words.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
    	Map<String, List<String>> map = new HashMap<>();
        for (List<String> w : synonyms) {
        	List<String> l = map.getOrDefault(w.get(0), new ArrayList<>());
        	l.add(w.get(1));
        	map.put(w.get(0), l);
        	l = map.getOrDefault(w.get(1), new ArrayList<>());
        	l.add(w.get(0));
        	map.put(w.get(1), l);
        }
        Set<String> visited = new HashSet<>();
        List<TreeSet<String>> groups = new ArrayList<>();

        for(String s : map.keySet()){
        	if(!visited.contains(s)){
        		TreeSet<String> group = new TreeSet<>();
        		dfs(s, visited, map, group);
        		groups.add(group);
        	}
        }
        // System.out.println(groups);
        List<String> ans = new ArrayList<>();
        permutation(text.split(" "), 0, ans, groups);
        return ans;
    }

    void dfs(String s, Set<String> visited, Map<String, List<String>> map, TreeSet<String> ts){
    	ts.add(s);
    	visited.add(s);
    	for(String t : map.get(s)){
    		if(!visited.contains(t)){
    			dfs(t, visited, map, ts);	
    		}
    	}
    }

    void permutation(String[] src, int pos, List<String> res, List<TreeSet<String>> groups){
    	if(pos == src.length){
    		res.add(String.join(" ", src));
    		return;
    	}
    	Set<String> group = new TreeSet<>();
    	for(Set<String> s : groups){
    		if(s.contains(src[pos])){
    			group = s;
    			break;
    		}
    	}
    	if(group.isEmpty()){
    		permutation(src, pos + 1, res, groups);
    	}
    	else{
	    	for(String s:group){
	    		src[pos] = s;
	    		permutation(src, pos + 1, res, groups);
	    	}	
    	}
    	
    }
}
// @lc code=end
