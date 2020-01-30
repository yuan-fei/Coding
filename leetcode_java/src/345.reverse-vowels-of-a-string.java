/*
 * @lc app=leetcode id=345 lang=java
 *
 * [345] Reverse Vowels of a String
 *
 * https://leetcode.com/problems/reverse-vowels-of-a-string/description/
 *
 * algorithms
 * Easy (42.89%)
 * Likes:    524
 * Dislikes: 940
 * Total Accepted:    190.7K
 * Total Submissions: 444.3K
 * Testcase Example:  '"hello"'
 *
 * Write a function that takes a string as input and reverse only the vowels of
 * a string.
 * 
 * Example 1:
 * 
 * 
 * Input: "hello"
 * Output: "holle"
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "leetcode"
 * Output: "leotcede"
 * 
 * 
 * Note:
 * The vowels does not include the letter "y".
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while(left < right){
        	if(!vowels.contains(chars[left])){
        		left++;
        		continue;
        	}
        	if(!vowels.contains(chars[right])){
        		right--;
        		continue;
        	}
        	swap(chars, left++, right--);

        }
        return String.valueOf(chars);
    }

    void swap(char[] chars, int i, int j){
    	char t = chars[i];
    	chars[i] = chars[j];
    	chars[j] = t;
    }
}
// @lc code=end
