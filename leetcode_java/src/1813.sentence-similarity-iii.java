/*
 * @lc app=leetcode id=1813 lang=java
 *
 * [1813] Sentence Similarity III
 *
 * https://leetcode.com/problems/sentence-similarity-iii/description/
 *
 * algorithms
 * Medium (34.07%)
 * Likes:    46
 * Dislikes: 11
 * Total Accepted:    3.1K
 * Total Submissions: 9.2K
 * Testcase Example:  '"My name is Haley"\n"My Haley"'
 *
 * A sentence is a list of words that are separated by a single space with no
 * leading or trailing spaces. For example, "Hello World", "HELLO", "hello
 * world hello world" are all sentences. Words consist of only uppercase and
 * lowercase English letters.
 * 
 * Two sentences sentence1 and sentence2 are similar if it is possible to
 * insert an arbitrary sentence (possibly empty) inside one of these sentences
 * such that the two sentences become equal. For example, sentence1 = "Hello my
 * name is Jane" and sentence2 = "Hello Jane" can be made equal by inserting
 * "my name is" between "Hello" and "Jane" in sentence2.
 * 
 * Given two sentences sentence1 and sentence2, return true if sentence1 and
 * sentence2 are similar. Otherwise, return false.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
 * Output: true
 * Explanation: sentence2 can be turned to sentence1 by inserting "name is"
 * between "My" and "Haley".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: sentence1 = "of", sentence2 = "A lot of words"
 * Output: false
 * Explanation: No single sentence can be inserted inside one of the sentences
 * to make it equal to the other.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: sentence1 = "Eating right now", sentence2 = "Eating"
 * Output: true
 * Explanation: sentence2 can be turned to sentence1 by inserting "right now"
 * at the end of the sentence.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: sentence1 = "Luky", sentence2 = "Lucccky"
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= sentence1.length, sentence2.length <= 100
 * sentence1 and sentence2 consist of lowercase and uppercase English letters
 * and spaces.
 * The words in sentence1 and sentence2 are separated by a single space.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");
        if(words1.length < words2.length){
        	int s1 = longestPrefix(words1, words2);
        	int s2 = longestSuffix(words1, words2);
        	return s1 + s2 >= words1.length;
        }
        else {
        	int s1 = longestPrefix(words2, words1);
        	int s2 = longestSuffix(words2, words1);
        	return s1 + s2 >= words2.length;
        }
    }

    

    private int longestPrefix(String[] w1, String[] w2){
    	int i = 0;
    	for(; i < w1.length; i++){
    		if(!w1[i].equals(w2[i])){
    			break;
    		}
    	}
    	return i;
    }

    private int longestSuffix(String[] w1, String[] w2){
    	int i = 0;
    	for(; i < w1.length; i++){
    		if(!w1[w1.length - i - 1].equals(w2[w2.length - i - 1])){
    			break;
    		}
    	}
    	return i;
    }

}
// @lc code=end
