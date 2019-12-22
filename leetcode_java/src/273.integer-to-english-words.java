/*
 * @lc app=leetcode id=273 lang=java
 *
 * [273] Integer to English Words
 *
 * https://leetcode.com/problems/integer-to-english-words/description/
 *
 * algorithms
 * Hard (25.43%)
 * Likes:    708
 * Dislikes: 1998
 * Total Accepted:    131.8K
 * Total Submissions: 518.2K
 * Testcase Example:  '123'
 *
 * Convert a non-negative integer to its english words representation. Given
 * input is guaranteed to be less than 2^31 - 1.
 * 
 * Example 1:
 * 
 * 
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * 
 * Example 3:
 * 
 * 
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty
 * Seven"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty
 * Seven Thousand Eight Hundred Ninety One"
 * 
 * 
 */

// @lc code=start
class Solution {
    public String numberToWords(int num) {
    	if(num == 0){
    		return "Zero";
    	}
    	String ans = "";
        int d1e12 = num / 1000000000;
        String section = sectionToWords(d1e12);
        if(!section.isEmpty()){
        	ans += section + " Billion";
        }
        int d1e9 = (num % 1000000000) / 1000000;
        section = sectionToWords(d1e9);
        if(!section.isEmpty()){
        	ans += " " + section + " Million";
        }
        int d1e6 = (num % 1000000) / 1000;
        section = sectionToWords(d1e6);
        if(!section.isEmpty()){
        	ans += " " + section + " Thousand";
        }
        int d1e3 = num % 1000;
        section = sectionToWords(d1e3);
        if(!section.isEmpty()){
        	ans += " " + section;
        }
        return ans.trim();
    }

    private String sectionToWords(int num){
    	String[] dict20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};	
    	String[] dict100 = {"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    	String ret = "";
    	
    	if(num == 0){
    		return "";
    	}
    	int d1 = num / 100;
    	if(d1 > 0){
    		ret += dict20[d1] + " Hundred";
    	}
    	int d23 = num % 100;
    	if(d23 == 0){
    		return ret;
    	}
    	if(!ret.isEmpty()){
    		ret += " ";
    	}
    	if(d23 < 20){
    		ret += dict20[d23];
    	}
    	else{
    		ret += dict100[d23 / 10] + " " + dict20[d23 % 10];
    	}
    	
    	return ret.trim();
    }
}
// @lc code=end
