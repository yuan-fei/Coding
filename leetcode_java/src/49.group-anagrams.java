/*
 * [49] Group Anagrams
 *
 * https://leetcode.com/problems/group-anagrams/description/
 *
 * algorithms
 * Medium (37.66%)
 * Total Accepted:    194.7K
 * Total Submissions: 505.8K
 * Testcase Example:  '["eat","tea","tan","ate","nat","bat"]'
 *
 * Given an array of strings, group anagrams together.
 * 
 * 
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
 * Return:
 * 
 * [
 * ⁠ ["ate", "eat","tea"],
 * ⁠ ["nat","tan"],
 * ⁠ ["bat"]
 * ]
 * 
 * Note: All inputs will be in lower-case.
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
        	char[] chars = str.toCharArray();
        	Arrays.sort(chars);
        	String key = String.valueOf(chars);
        	if(!map.containsKey(key)){
        		map.put(key, new ArrayList<String>());
        	}
        	map.get(key).add(str);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
