/*
 * @lc app=leetcode id=1451 lang=java
 *
 * [1451] Rearrange Words in a Sentence
 *
 * https://leetcode.com/problems/rearrange-words-in-a-sentence/description/
 *
 * algorithms
 * Medium (48.54%)
 * Likes:    5
 * Dislikes: 0
 * Total Accepted:    6.2K
 * Total Submissions: 12.7K
 * Testcase Example:  '"Leetcode is cool"'
 *
 * Given a sentence text (A sentence is a string of space-separated words) in
 * the following format:
 * 
 * 
 * First letter is in upper case.
 * Each word in text are separated by a single space.
 * 
 * 
 * Your task is to rearrange the words in text such that all words are
 * rearranged in an increasing order of their lengths. If two words have the
 * same length, arrange them in their original order.
 * 
 * Return the new text following the format shown above.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: text = "Leetcode is cool"
 * Output: "Is cool leetcode"
 * Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and
 * "cool" of length 4.
 * Output is ordered by length and the new first word starts with capital
 * letter.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: text = "Keep calm and code on"
 * Output: "On and keep calm code"
 * Explanation: Output is ordered as follows:
 * "On" 2 letters.
 * "and" 3 letters.
 * "keep" 4 letters in case of tie order by position in original text.
 * "calm" 4 letters.
 * "code" 4 letters.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: text = "To be or not to be"
 * Output: "To be or to be not"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * text begins with a capital letter and then contains lowercase letters and
 * single space between words.
 * 1 <= text.length <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public String arrangeWords(String text) {
        String[] parts = text.toLowerCase().split(" ");
        Word[] words = new Word[parts.length];
        for (int i = 0; i < parts.length; i++) {
        	words[i] = new Word(parts[i], i);
        }
        Arrays.sort(words, (a, b) -> Integer.compare(a.w.length(), b.w.length()) == 0 ? Integer.compare(a.p, b.p) : Integer.compare(a.w.length(), b.w.length()));
        words[0].w = Character.toUpperCase(words[0].w.charAt(0)) + words[0].w.substring(1);
        return Arrays.stream(words).map(w -> w.w).collect(Collectors.joining(" "));
    }

    static class Word{
    	String w;
    	int p;

    	Word(String word, int pos){
    		w = word;
    		p = pos;
    	}
    }
}
// @lc code=end
