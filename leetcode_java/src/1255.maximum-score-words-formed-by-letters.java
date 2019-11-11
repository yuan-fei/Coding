/*
 * @lc app=leetcode id=1255 lang=java
 *
 * [1255] Maximum Score Words Formed by Letters
 *
 * https://leetcode.com/problems/maximum-score-words-formed-by-letters/description/
 *
 * algorithms
 * Hard (71.79%)
 * Likes:    49
 * Dislikes: 1
 * Total Accepted:    2.4K
 * Total Submissions: 3.3K
 * Testcase Example:  '["dog","cat","dad","good"]\n["a","a","c","d","d","d","g","o","o"]\n[1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]'
 *
 * Given a list of words, list of  single letters (might be repeating) and
 * score of every character.
 * 
 * Return the maximum score of any valid set of words formed by using the given
 * letters (words[i] cannot be used two or more times).
 * 
 * It is not necessary to use all characters in letters and each letter can
 * only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by
 * score[0], score[1], ... , score[25] respectively.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["dog","cat","dad","good"], letters =
 * ["a","a","c","d","d","d","g","o","o"], score =
 * [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
 * Output: 23
 * Explanation:
 * Score  a=1, c=9, d=5, g=3, o=2
 * Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with
 * a score of 23.
 * Words "dad" and "dog" only get a score of 21.
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["xxxz","ax","bx","cx"], letters =
 * ["z","a","b","c","x","x","x"], score =
 * [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
 * Output: 27
 * Explanation:
 * Score  a=4, b=4, c=4, x=5, z=10
 * Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5)
 * with a score of 27.
 * Word "xxxz" only get a score of 25.
 * 
 * Example 3:
 * 
 * 
 * Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score =
 * [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
 * Output: 0
 * Explanation:
 * Letter "e" can only be used once.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 14
 * 1 <= words[i].length <= 15
 * 1 <= letters.length <= 100
 * letters[i].length == 1
 * score.length == 26
 * 0 <= score[i] <= 10
 * words[i], letters[i] contains only lower case English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
    	int N = words.length;
        int[][] state = new int[1 << N][26];
        int[] dp = new int[1 << N];
        int[] target = new int[26];
        for(char c: letters){
        	target[c - 'a']++;
        }

        for(int i = 0; i < N; i++){
        	count(words[i], state[1 << i]);
        	dp[1 << i] = calc(state[1 << i], target, score);
        }

        int max = 0;
        for (int m = 0; m < (1 << N); m++) {
        	int lowestOne = m & (-m);
        	int comp = m & (~lowestOne);
        	if(dp[comp] == -1){
        		dp[m] = -1;
        	}
        	else{
        		add(state[lowestOne], state[comp], state[m]);
        		dp[m] = calc(state[m], target, score);
        		max = Math.max(max, dp[m]);
        	}
        }
        return max;
    }

    void count(String word, int[] ctr){
    	for (char c : word.toCharArray()) {
    		ctr[c - 'a']++;
    	}
    }

    void add(int[] ctr1, int[] ctr2, int[] ctr){
    	for (int i = 0; i < ctr1.length; i++) {
    		ctr[i] = ctr1[i] + ctr2[i];
    	}
    }

    int calc(int[] ctr, int[] target, int[] score){
    	int total = 0;
    	for (int i = 0; i < ctr.length; i++) {
    		if(target[i] < ctr[i]){
    			return -1;
    		}
    		else{
    			total += score[i] * ctr[i];
    		}
    	}
    	return total;
    }
}
// @lc code=end
