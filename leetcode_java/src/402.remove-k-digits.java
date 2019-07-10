/*
 * @lc app=leetcode id=402 lang=java
 *
 * [402] Remove K Digits
 *
 * https://leetcode.com/problems/remove-k-digits/description/
 *
 * algorithms
 * Medium (26.59%)
 * Total Accepted:    62.4K
 * Total Submissions: 233.5K
 * Testcase Example:  '"1432219"\n3'
 *
 * Given a non-negative integer num represented as a string, remove k digits
 * from the number so that the new number is the smallest possible.
 * 
 * 
 * Note:
 * 
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219
 * which is the smallest.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the
 * output must not contain leading zeroes.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with
 * nothing which is 0.
 * 
 * 
 */
class Solution {
    public String removeKdigits(String num, int k) {
    	String r="";
        LinkedList<Character> s = new LinkedList<>();

        for(char c : num.toCharArray()){
        	while(k>0 && !s.isEmpty() && c < s.peekLast()){
    			s.pollLast();
    			k--;
    		}
        	if(!s.isEmpty() || c!='0'){
        		s.offer(c);
        	}
        }
        while(k>0&&!s.isEmpty())
        {
        	s.pollLast();
        	k--;
        }
        if(s.isEmpty()){
        	return "0";
        }
        else{
	        for(char c:s){
	        	r+=c;
	        }
	        return r;	
        }
    }
}
