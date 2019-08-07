/*
 * @lc app=leetcode id=1147 lang=java
 *
 * [1147] Longest Chunked Palindrome Decomposition
 *
 * https://leetcode.com/problems/longest-chunked-palindrome-decomposition/description/
 *
 * algorithms
 * Hard (58.73%)
 * Total Accepted:    2.8K
 * Total Submissions: 4.8K
 * Testcase Example:  '"ghiabcdefhelloadamhelloabcdefghi"'
 *
 * Return the largest possible k such that there exists a_1, a_2, ..., a_k such
 * that:
 * 
 * 
 * Each a_i is a non-empty string;
 * Their concatenation a_1 + a_2 + ... + a_k is equal to text;
 * For all 1 <= i <= k,  a_i = a_{k+1 - i}.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: text = "ghiabcdefhelloadamhelloabcdefghi"
 * Output: 7
 * Explanation: We can split the string on
 * "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: text = "merchant"
 * Output: 1
 * Explanation: We can split the string on "(merchant)".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: text = "antaprezatepzapreanta"
 * Output: 11
 * Explanation: We can split the string on
 * "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)".
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: text = "aaa"
 * Output: 3
 * Explanation: We can split the string on "(a)(a)(a)".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * text consists only of lowercase English characters.
 * 1 <= text.length <= 1000
 * 
 * 
 */
class Solution {
    public int longestDecomposition(String text) {
        return longestDecomposition(text, 0, text.length()-1);
    }
    
    int longestDecomposition(String text, int start, int end) {
        // System.out.println(start+", "+end +" = " +text.substring(start, end+1));
        if(start>end){
            return 0;
        }
        int size = findMatch(text, start, end);
        if(size == 0){
            return 1;
        }
        return 2 + longestDecomposition(text, start + size, end - size);
    }
    
    int findMatch(String text, int start, int end){
        for(int i = 1; i <= (end-start+1)/2; i++){
            int j = 0;
            for(; j < i; j++){
                if(text.charAt(start+j)!=text.charAt(end-i+1+j)){
                    break;
                }
            }
            if(j == i){
                return i;
            }
        }
        return 0;
    }
}
