/*
 * @lc app=leetcode id=38 lang=java
 *
 * [38] Count and Say
 *
 * https://leetcode.com/problems/count-and-say/description/
 *
 * algorithms
 * Easy (41.31%)
 * Total Accepted:    303.2K
 * Total Submissions: 733.6K
 * Testcase Example:  '1'
 *
 * The count-and-say sequence is the sequence of integers with the first five
 * terms as following:
 * 
 * 
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 
 * 
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * 
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the
 * count-and-say sequence.
 * 
 * Note: Each term of the sequence of integers will be represented as a
 * string.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: 1
 * Output: "1"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 4
 * Output: "1211"
 * 
 */
class Solution {
    public String countAndSay(int n) {
    	List<Integer> l = Arrays.asList(1);
        for (int i = 1; i < n; i++) {
        	int cur = l.get(0);
        	int cnt  = 1;
        	List<Integer> nl = new ArrayList<>();
        	for(int j = 1; j < l.size(); j++){
        		if(l.get(j) != cur){
        			nl.add(cnt);
        			nl.add(cur);
					cur = l.get(j);
					cnt=1;
        		}
        		else{
        			cnt++;
        		}
        	}
        	nl.add(cnt);
        	nl.add(cur);
        	l = nl;
        }
        String s="";
        for (int e: l) {
        	s+=e;
        }
        return s;
    }
}
