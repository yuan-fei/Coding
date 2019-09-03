/*
 * @lc app=leetcode id=1178 lang=java
 *
 * [1178] Number of Valid Words for Each Puzzle
 *
 * https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/description/
 *
 * algorithms
 * Hard (22.79%)
 * Total Accepted:    854
 * Total Submissions: 3.8K
 * Testcase Example:  '["aaaa","asas","able","ability","actt","actor","access"]\n["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]'
 *
 * With respect to a given puzzle string, a word is valid if both the following
 * conditions are satisfied:
 * 
 * word contains the first letter of puzzle.
 * For each letter in word, that letter is in puzzle.
 * For example, if the puzzle is "abcdefg", then valid words are "faced",
 * "cabbage", and "baggage"; while invalid words are "beefed" (doesn't include
 * "a") and "based" (includes "s" which isn't in the puzzle).
 * 
 * Return an array answer, where answer[i] is the number of words in the given
 * word list words that are valid with respect to the puzzle puzzles[i].
 * 
 * Example :
 * 
 * 
 * Input: 
 * words = ["aaaa","asas","able","ability","actt","actor","access"], 
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * Output: [1,1,3,2,4,0]
 * Explanation:
 * 1 valid word for "aboveyz" : "aaaa" 
 * 1 valid word for "abrodyz" : "aaaa"
 * 3 valid words for "abslute" : "aaaa", "asas", "able"
 * 2 valid words for "absoryz" : "aaaa", "asas"
 * 4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
 * There're no valid words for "gaswxyz" cause none of the words in the list
 * contains letter 'g'.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j] are English lowercase letters.
 * Each puzzles[i] doesn't contain repeated characters.
 * 
 * 
 */
class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> encodedWords = new HashMap<>();
        for(String word: words){
        	int encodedWord = encode(word);
        	// System.out.printf("%s %d, ",word, encodedWord);
        	if(Integer.bitCount(encodedWord) <= 7){
        		encodedWords.put(encodedWord, encodedWords.getOrDefault(encodedWord, 0)+1);	
        	}
        }
        List<Integer> ans = new ArrayList<>();
        for (int i=0 ; i<puzzles.length ; i++) {
        	int cnt = 0;
        	int puzzle = encode(puzzles[i]);
        	int mask = 1<<(puzzles[i].charAt(0)-'a');
        	int last6 = puzzle & ~mask;
        	int x = last6;
        	//at most 2^6
        	while(x!=0){
        		cnt+=encodedWords.getOrDefault(x|mask, 0);
        		x = (x-1) & last6;
        	}
        	// only the first letter
        	cnt+=encodedWords.getOrDefault(mask, 0);
        	ans.add(cnt);
        }
        return ans;
    }



    int encode(String s){
    	int ret = 0;
    	for (char c: s.toCharArray()) {
    		ret|=(1<<(c-'a'));
    	}
    	return ret;
    }
}
