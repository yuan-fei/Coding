/*
 * @lc app=leetcode id=318 lang=java
 *
 * [318] Maximum Product of Word Lengths
 *
 * https://leetcode.com/problems/maximum-product-of-word-lengths/description/
 *
 * algorithms
 * Medium (49.88%)
 * Likes:    666
 * Dislikes: 56
 * Total Accepted:    89K
 * Total Submissions: 178.3K
 * Testcase Example:  '["abcw","baz","foo","bar","xtfn","abcdef"]'
 *
 * Given a string array words, find the maximum value of length(word[i]) *
 * length(word[j]) where the two words do not share common letters. You may
 * assume that each word will contain only lower case letters. If no such two
 * words exist, return 0.
 * 
 * Example 1:
 * 
 * 
 * Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16 
 * Explanation: The two words can be "abcw", "xtfn".
 * 
 * Example 2:
 * 
 * 
 * Input: ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4 
 * Explanation: The two words can be "ab", "cd".
 * 
 * Example 3:
 * 
 * 
 * Input: ["a","aa","aaa","aaaa"]
 * Output: 0 
 * Explanation: No such pair of words.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxProduct(String[] words) {
    	int n = words.length;
        Arrays.sort(words, (a, b) -> Integer.compare(b.length(), a.length()));
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> -Integer.compare(words[a[0]].length() * words[a[1]].length(), words[b[0]].length() * words[b[1]].length()));
        for (int i = 0; i < n; i++) {
        	q.offer(new int[]{0, i});
        }
        while(!q.isEmpty()){
        	int[] cur = q.poll();
        	if(!checkDup(words, cur[0], cur[1])){
        		return words[cur[0]].length() * words[cur[1]].length();
        	}
        	if(cur[0] + 1 < cur[1]){
        		q.offer(new int[]{cur[0] + 1, cur[1]});
        	}
        }
        return 0;
    }

    boolean checkDup(String[] words, int i, int j){
    	boolean[] dict = new boolean[26];
		for(char c: words[i].toCharArray()){
    		dict[c - 'a'] = true;
    	}
    	
		for(char c: words[j].toCharArray()){
    		if(dict[c - 'a']){
    			return true;
    		}
    	}
    	return false;
    }
}
// @lc code=end
