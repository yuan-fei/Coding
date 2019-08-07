/*
 * @lc app=leetcode id=828 lang=java
 *
 * [828] Unique Letter String
 *
 * https://leetcode.com/problems/unique-letter-string/description/
 *
 * algorithms
 * Hard (39.34%)
 * Total Accepted:    5.8K
 * Total Submissions: 14.4K
 * Testcase Example:  '"ABC"'
 *
 * A character is unique in string S if it occurs exactly once in it.
 * 
 * For example, in string S = "LETTER", the only unique characters are "L" and
 * "R".
 * 
 * Let's define UNIQ(S) as the number of unique characters in string S.
 * 
 * For example, UNIQ("LETTER") =  2.
 * 
 * Given a string S with only uppercases, calculate the sum of UNIQ(substring)
 * over all non-empty substrings of S.
 * 
 * If there are two or more equal substrings at different positions in S, we
 * consider them different.
 * 
 * Since the answer can be very large, return the answer modulo 10 ^ 9 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Evey substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 * 
 * Example 2:
 * 
 * 
 * Input: "ABA"
 * Output: 8
 * Explanation: The same as example 1, except uni("ABA") = 1.
 * 
 * 
 * 
 * 
 * Note: 0 <= S.length <= 10000.
 * 
 */
class Solution {
	long m = 1000000007;
    public int uniqueLetterString(String S) {
    	int n = S.length();
    	Queue<Integer>[] q = new Queue[26];
    	for (int i = 0; i < q.length; i++) {
    		q[i] = new LinkedList<Integer>();
    		q[i].offer(-1);
    	}
    	long total = 0;
        for (int i = 0; i < n; i++) {
        	int c = S.charAt(i) - 'A';
        	if(q[c].size() == 2){
        		int left = q[c].poll();
        		int middle = q[c].peek();
        		int right = i;
        		total = (total + countUnique(middle - left, right - middle))%m;
        	}
        	q[c].offer(i);
        }
        for (int i = 0; i < q.length; i++) {
    		if(q[i].size() == 2){
        		int left = q[i].poll();
        		int middle = q[i].peek();
        		int right = n;
        		total = (total + countUnique(middle - left, right - middle))%m;
        	}
    	}
        return (int)total;
    }

    long countUnique(long left, long right){
    	return (left * right)%m;
    }
}
