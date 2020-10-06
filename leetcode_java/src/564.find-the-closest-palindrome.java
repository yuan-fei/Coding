/*
 * @lc app=leetcode id=564 lang=java
 *
 * [564] Find the Closest Palindrome
 *
 * https://leetcode.com/problems/find-the-closest-palindrome/description/
 *
 * algorithms
 * Hard (19.99%)
 * Likes:    300
 * Dislikes: 884
 * Total Accepted:    22.4K
 * Total Submissions: 111.9K
 * Testcase Example:  '"1"'
 *
 * Given an integer n, find the closest integer (not including itself), which
 * is a palindrome. 
 * 
 * The 'closest' is defined as absolute difference minimized between two
 * integers.
 * 
 * Example 1:
 * 
 * Input: "123"
 * Output: "121"
 * 
 * 
 * 
 * Note:
 * 
 * The input n is a positive integer represented by string, whose length will
 * not exceed 18.
 * If there is a tie, return the smaller one as answer.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String nearestPalindromic(String n) {
    	long self = Long.parseLong(n);
        String half = n.substring(0, (n.length() + 1) / 2);
        long hn = Long.parseLong(half);
        long pSelf = getFull(hn, (n.length() % 2 == 1));
        long uhn = hn + 1;
        if(nextFlip(hn) && (n.length() % 2 == 1)){
        	uhn /= 10;
        }
        long upper = getFull(uhn, nextFlip(hn) ^ (n.length() % 2 == 1));
        long lhn = hn - 1;
        if((hn == 1 || prevFlip(hn)) && (n.length() % 2 == 0)){
        	lhn *= 10;
        	lhn += 9;
        }
        // System.out.println(lhn);
        long lower = getFull(lhn, (hn == 1 || prevFlip(hn)) ^ (n.length() % 2 == 1));
        System.out.println(upper + "<" + pSelf + ">" + lower);
        if(pSelf < self){
        	if(Math.abs(upper - self) < Math.abs(pSelf - self)){
        		return "" + upper;
	        }
	        else{
	        	return "" + pSelf;
	        }	
        }
        else if(pSelf > self){
	        if(Math.abs(lower - self) <= Math.abs(pSelf - self)){
	        	return "" + lower;
	        }
	        else{
	        	return "" + pSelf;
	        }	
        }
        else{
        	if(Math.abs(lower - self) <= Math.abs(upper - self)){
	        	return "" + lower;
	        }
	        else{
	        	return "" + upper;
	        }	
        }
    }

    long getFull(long half, boolean odd){
    	StringBuilder sb = new StringBuilder();
    	String n = "" + half;
    	sb.append(n.substring(0, odd ? n.length() -1 : n.length()));
    	sb.reverse();
    	return Long.parseLong(n + sb.toString());
    }

    boolean nextFlip(long n){
    	return ("" + (n + 1)).length() > ("" + n).length();
    }

    boolean prevFlip(long n){
    	return ("" + (n - 1)).length() < ("" + n).length();
    }
}
// @lc code=end
