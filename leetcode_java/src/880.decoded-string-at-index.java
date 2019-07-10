/*
 * @lc app=leetcode id=880 lang=java
 *
 * [880] Decoded String at Index
 *
 * https://leetcode.com/problems/decoded-string-at-index/description/
 *
 * algorithms
 * Medium (22.99%)
 * Total Accepted:    7.3K
 * Total Submissions: 31.8K
 * Testcase Example:  '"leet2code3"\n10'
 *
 * An encoded string S is given.  To find and write the decoded string to a
 * tape, the encoded string is read one character at a time and the following
 * steps are taken:
 * 
 * 
 * If the character read is a letter, that letter is written onto the tape.
 * If the character read is a digit (say d), the entire current tape is
 * repeatedly written d-1 more times in total.
 * 
 * 
 * Now for some encoded string S, and an index K, find and return the K-th
 * letter (1 indexed) in the decoded string.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: S = "leet2code3", K = 10
 * Output: "o"
 * Explanation: 
 * The decoded string is "leetleetcodeleetleetcodeleetleetcode".
 * The 10th letter in the string is "o".
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: S = "ha22", K = 5
 * Output: "h"
 * Explanation: 
 * The decoded string is "hahahaha".  The 5th letter is "h".
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: S = "a2345678999999999999999", K = 1
 * Output: "a"
 * Explanation: 
 * The decoded string is "a" repeated 8301530446056247680 times.  The 1st
 * letter is "a".
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 2 <= S.length <= 100
 * S will only contain lowercase letters and digits 2 through 9.
 * S starts with a letter.
 * 1 <= K <= 10^9
 * The decoded string is guaranteed to have less than 2^63 letters.
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public String decodeAtIndex(String S, int K) {
        Stack<long[]> partStack = new Stack<>();
        Stack<Long> lengthStack = new Stack<>();
        long total = 0;
        long[] part = new long[]{-1,-1};
        int i = 0;
        while ( i<S.length()) {
        	if(Character.isLetter(S.charAt(i))){
        		while(i<S.length()&&Character.isLetter(S.charAt(i))){
	        		if(part[0]==-1){
	        			part[0] = i;
	        		}
	        		part[1] = i;	
	        		total++;
	        		if(total==K){
	        			return ""+S.charAt(i);
	        		}
	        		i++;
        		}

        		lengthStack.push(total);
        		partStack.push(part);
        	}
        	else{
        		boolean boundReached = false;
        		long multiplier = 1;
        		while(i<S.length()&&Character.isDigit(S.charAt(i))){
        			int t = Integer.parseInt(S.charAt(i++)+"");
        			multiplier*=t;
	        		if(total*multiplier>=K){
	        			K%=total;
	        			boundReached = true;
	        			break;
	        		}
        		}

        		if(boundReached){
        			break;
        		}
        		else{
        			total*=multiplier;
        			part = new long[]{-1,-1};
        		}
        	}
        	//System.out.println(total);
        }
        System.out.println(lengthStack);
        while(!lengthStack.isEmpty()){
        	total = lengthStack.pop();
        	part = partStack.pop();
        	K%=total;
        	if(K==0){
        		K = (int)total;
        	}
        	// System.out.println(K);
        	if(0<K-(total-(part[1]-part[0]+1))){
        		long idx = (K-(total-(part[1]-part[0]+1))-1)%(part[1]-part[0]+1)+part[0];
        		return ""+S.charAt((int)idx);
        	}
        }
        return "";
    }
}
