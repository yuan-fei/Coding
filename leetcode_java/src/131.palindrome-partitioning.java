/*
 * [131] Palindrome Partitioning
 *
 * https://leetcode.com/problems/palindrome-partitioning/description/
 *
 * algorithms
 * Medium (35.16%)
 * Total Accepted:    115.2K
 * Total Submissions: 326.9K
 * Testcase Example:  '"aab"'
 *
 * 
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.
 * 
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * 
 * For example, given s = "aab",
 * 
 * Return
 * 
 * [
 * ⁠ ["aa","b"],
 * ⁠ ["a","a","b"]
 * ]
 * 
 * 
 */
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> resultSet = new ArrayList<List<String>>();
        partitionHelper(s, 0, resultSet, new ArrayList<String>());
        return resultSet;
    }

    private void partitionHelper(String s, int k, List<List<String>> resultSet, List<String> current){
    	if(k == s.length()){
    		resultSet.add(new ArrayList<String>(current));
    	}
    	for (int i = k; i < s.length(); i++) {
    		if(isPalindrome(s.substring(k, i + 1))){
    			current.add(s.substring(k, i + 1));
    			partitionHelper(s, i + 1, resultSet, current);
    			current.remove(current.size() - 1);
    		}
    	}
    }

    private boolean isPalindrome(String s){
    	int i = 0;
    	int j = s.length() - 1;
    	while(i <= j){
    		if(s.charAt(i) != s.charAt(j)){
    			return false;
    		}
    		i++;
    		j--;
    	}
    	return true;
    }
}
