/*
 * @lc app=leetcode id=67 lang=java
 *
 * [67] Add Binary
 *
 * https://leetcode.com/problems/add-binary/description/
 *
 * algorithms
 * Easy (40.12%)
 * Total Accepted:    327.5K
 * Total Submissions: 816.3K
 * Testcase Example:  '"11"\n"1"'
 *
 * Given two binary strings, return their sum (also a binary string).
 * 
 * The input strings are both non-empty and contains only characters 1 or 0.
 * 
 * Example 1:
 * 
 * 
 * Input: a = "11", b = "1"
 * Output: "100"
 * 
 * Example 2:
 * 
 * 
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * 
 */
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sba = new StringBuilder(a);
        StringBuilder sbb = new StringBuilder(b);
        sba.reverse();
        sbb.reverse();
        StringBuilder sbr = new StringBuilder();
        int carry = 0;
        for(int i = 0; i <Math.max(sba.length(), sbb.length()); i++){
        	int bitA = 0;
        	int bitB = 0;
        	if(i < sba.length()){
        		bitA = sba.charAt(i) - '0';
        	}
        	if(i < sbb.length()){
        		bitB = sbb.charAt(i) - '0';
        	}
        	sbr.append((bitA+bitB+carry)%2);
        	carry = (bitA+bitB+carry)/2;
        }
        if(carry==1){
        	sbr.append(1);
        }
        return sbr.reverse().toString();
    }
}
