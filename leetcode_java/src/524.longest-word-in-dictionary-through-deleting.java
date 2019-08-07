/*
 * @lc app=leetcode id=524 lang=java
 *
 * [524] Longest Word in Dictionary through Deleting
 *
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/
 *
 * algorithms
 * Medium (45.69%)
 * Total Accepted:    47K
 * Total Submissions: 101.6K
 * Testcase Example:  '"abpcplea"\n["ale","apple","monkey","plea"]'
 *
 * 
 * Given a string and a string dictionary, find the longest string in the
 * dictionary that can be formed by deleting some characters of the given
 * string. If there are more than one possible results, return the longest word
 * with the smallest lexicographical order. If there is no possible result,
 * return the empty string.
 * 
 * Example 1:
 * 
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 
 * Output: 
 * "apple"
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 * 
 * Output: 
 * "a"
 * 
 * 
 * 
 * Note:
 * 
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 * 
 * 
 */
class Solution {
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, (s1, s2)-> Integer.compare(s1.length(), s2.length()) == 0?s1.compareTo(s2):-Integer.compare(s1.length(), s2.length()));
        //System.out.println(d);
        for(String s2: d){
        	if(convertable(s, s2)){
        		return s2;
        	}
        }
        return "";
    }

    private boolean convertable(String s1, String s2){
    	int n = s1.length();
    	int m = s2.length();
    	int j = 0;
    	for(int i = 0; i < n; i++){
    		if(s1.charAt(i) == s2.charAt(j)){
    			j++;
    			if(j==m){
    				return true;
    			}
    		}
    	}
    	return false;
    }
}
