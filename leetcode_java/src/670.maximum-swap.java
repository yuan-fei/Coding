/*
 * @lc app=leetcode id=670 lang=java
 *
 * [670] Maximum Swap
 *
 * https://leetcode.com/problems/maximum-swap/description/
 *
 * algorithms
 * Medium (45.35%)
 * Likes:    1477
 * Dislikes: 93
 * Total Accepted:    97.4K
 * Total Submissions: 214.7K
 * Testcase Example:  '2736'
 *
 * 
 * Given a non-negative integer, you could swap two digits at most once to get
 * the maximum valued number. Return the maximum valued number you could get.
 * 
 * 
 * Example 1:
 * 
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * The given number is in the range [0, 10^8]
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumSwap(int num) {
    	int[] lastSeen = new int[10];
    	Arrays.fill(lastSeen, -1);
        String s = "" + num;
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++){
        	int d = chars[i] - '0';
        	lastSeen[d] = i;
        }
        
        for(int i = 0; i < chars.length; i++){
        	int d = chars[i] - '0';
        	for(int j = 9; j > d; j--){
        		if(lastSeen[j] > i){
        			System.out.println(Arrays.asList(lastSeen[j], i));
        			char t = chars[i];
        			chars[i] = chars[lastSeen[j]];
        			chars[lastSeen[j]] = t;
        			return Integer.parseInt(String.valueOf(chars));
        		}
        	}
        }
        return num;
    }
}
// @lc code=end
