/*
 * @lc app=leetcode id=1177 lang=java
 *
 * [1177] Can Make Palindrome from Substring
 *
 * https://leetcode.com/problems/can-make-palindrome-from-substring/description/
 *
 * algorithms
 * Medium (23.54%)
 * Total Accepted:    2K
 * Total Submissions: 8K
 * Testcase Example:  '"abcda"\n[[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]'
 *
 * Given a string s, we make queries on substrings of s.
 * 
 * For each query queries[i] = [left, right, k], we may rearrange the substring
 * s[left], ..., s[right], and then choose up to k of them to replace with any
 * lowercase English letter. 
 * 
 * If the substring is possible to be a palindrome string after the operations
 * above, the result of the query is true. Otherwise, the result is false.
 * 
 * Return an array answer[], where answer[i] is the result of the i-th query
 * queries[i].
 * 
 * Note that: Each letter is counted individually for replacement so if for
 * example s[left..right] = "aaa", and k = 2, we can only replace two of the
 * letters.  (Also, note that the initial string s is never modified by any
 * query.)
 * 
 * 
 * Example :
 * 
 * 
 * Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
 * Output: [true,false,false,true,true]
 * Explanation:
 * queries[0] : substring = "d", is palidrome.
 * queries[1] : substring = "bc", is not palidrome.
 * queries[2] : substring = "abcd", is not palidrome after replacing only 1
 * character.
 * queries[3] : substring = "abcd", could be changed to "abba" which is
 * palidrome. Also this can be changed to "baab" first rearrange it "bacd" then
 * replace "cd" with "ab".
 * queries[4] : substring = "abcda", could be changed to "abcba" which is
 * palidrome.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length, queries.length <= 10^5
 * 0 <= queries[i][0] <= queries[i][1] < s.length
 * 0 <= queries[i][2] <= s.length
 * s only contains lowercase English letters.
 * 
 * 
 */
class Solution {
	List<Integer>[] occurance;
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
         occurance = new List[26];
         for (int i = 0; i<26; i++) {
         	occurance[i] = new ArrayList<>();
         }
         for(int i = 0; i< s.length(); i++){
         	occurance[s.charAt(i)-'a'].add(i);
         }
         List<Boolean> ans  =new ArrayList<>();
         for (int i = 0; i<queries.length; i++) {
         	int[] occ = getRangeOccurance(queries[i][0], queries[i][1]);
         	// System.out.println(Arrays.toString(occ));
         	ans.add(check(occ, queries[i][2]));
         }
         return ans;
    }

    private int[] getRangeOccurance(int left, int right){
    	int[] ret = new int[26];
    	for (int i = 0; i<26; i++) {
    		//ub
    		int idx1 = Collections.binarySearch(occurance[i], left);
    		if(idx1<0){
    			idx1 = -idx1-1;
    		}
    		//lb
    		int idx2 = Collections.binarySearch(occurance[i], right);
    		if(idx2<0){
    			idx2 = -idx2-2;
    		}
    		ret[i] = Math.max(0, idx2 - idx1 + 1);
    	}
    	return ret;
    }

    private boolean check(int[] rangeOccurance, int K){
    	int totoalOdd  =0;
    	for (int i = 0; i<26; i++) {
    		if(rangeOccurance[i]%2==1){
    			totoalOdd++;
    		}
    	}
    	return totoalOdd/2 <= K;
    }
}
