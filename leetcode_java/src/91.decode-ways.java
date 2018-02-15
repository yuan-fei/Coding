/*
 * [91] Decode Ways
 *
 * https://leetcode.com/problems/decode-ways/description/
 *
 * algorithms
 * Medium (20.23%)
 * Total Accepted:    159.9K
 * Total Submissions: 790.4K
 * Testcase Example:  '""'
 *
 * 
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * 
 * 
 * Given an encoded message containing digits, determine the total number of
 * ways to decode it.
 * 
 * 
 * 
 * For example,
 * Given encoded message "12",
 * it could be decoded as "AB" (1 2) or "L" (12).
 * 
 * 
 * 
 * The number of ways decoding "12" is 2.
 * 
 */
class Solution {
    public int numDecodings(String s) {
		if(s == null || s.equals("")){
			return 0;
		}

        int[] solutions = new int[s.length() + 1];
        solutions[0] = 1;
        if(isLegalEncoding(s.substring(0, 1))){
			solutions[1] = 1;        	
        }
        else{
        	solutions[1] = 0;
        }


        for (int i = 2; i < solutions.length; i++) {
        	solutions[i] = 0;
        	if(solutions[i - 1] > 0 && isLegalEncoding(s.substring(i - 1, i))){
        		solutions[i] += solutions[i - 1];
	        }
	        if(solutions[i - 2] > 0 && isLegalEncoding(s.substring(i-2, i))){
	        	solutions[i] += solutions[i - 2];
	        }
        }
        return solutions[s.length()];
    }

    private boolean isLegalEncoding(String encoding){
    	if(encoding.length() == 1){
    		return encoding.compareTo("1") >= 0 && encoding.compareTo("9") <= 0;
    	}
    	else{
    		return encoding.compareTo("10") >= 0 && encoding.compareTo("26") <= 0;	
    	}
    	
    }

}
