/*
 * @lc app=leetcode id=58 lang=java
 *
 * [58] Length of Last Word
 *
 * https://leetcode.com/problems/length-of-last-word/description/
 *
 * algorithms
 * Easy (32.29%)
 * Total Accepted:    288.4K
 * Total Submissions: 893.1K
 * Testcase Example:  '"Hello World"'
 *
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence consists of non-space
 * characters only.
 * 
 * Example:
 * 
 * 
 * Input: "Hello World"
 * Output: 5
 * 
 * 
 * 
 * 
 */
class Solution {
    public int lengthOfLastWord(String s) {
        String[] parts = s.split(" +");
        if(parts.length>0){
        	return parts[parts.length - 1].length();
        }
        else{
        	return 0;
        }
    }
}
