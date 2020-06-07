/*
 * @lc app=leetcode id=481 lang=java
 *
 * [481] Magical String
 *
 * https://leetcode.com/problems/magical-string/description/
 *
 * algorithms
 * Medium (47.24%)
 * Likes:    107
 * Dislikes: 663
 * Total Accepted:    21.8K
 * Total Submissions: 46.2K
 * Testcase Example:  '1'
 *
 * 
 * A magical string S consists of only '1' and '2' and obeys the following
 * rules:
 * 
 * 
 * The string S is magical because concatenating the number of contiguous
 * occurrences of characters '1' and '2' generates the string S itself.
 * 
 * 
 * 
 * The first few elements of string S is the following:
 * S = "1221121221221121122……"
 * 
 * 
 * 
 * If we group the consecutive '1's and '2's in S, it will be:
 * 
 * 
 * 1   22  11  2  1  22  1  22  11  2  11  22 ......
 * 
 * 
 * and the occurrences of '1's or '2's in each group are:
 * 
 * 
 * 1   2       2    1   1    2     1    2     2    1    2    2 ......
 * 
 * 
 * 
 * You can see that the occurrence sequence above is the S itself. 
 * 
 * 
 * 
 * Given an integer N as input, return the number of '1's in the first N number
 * in the magical string S.
 * 
 * 
 * Note:
 * N will not exceed 100,000.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: 6
 * Output: 3
 * Explanation: The first 6 elements of magical string S is "12211" and it
 * contains three 1's, so return 3.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int magicalString(int n) {
        if(n == 0){
        	return 0;
        }
        if(n <= 3){
        	return 1;
        }
        int[] seq = new int[100005];
        seq[1] = 1;
        seq[2] = 2;
        seq[3] = 2;
        int cnt = 1;
        int right = 4;
        int cur = 2;
        for(int left = 3; left <= n; left++){
        	int t = right + seq[left];
        	cur = 3 - cur;
        	while(right < t){
        		seq[right] = cur;
        		cnt += (cur == 1)? 1: 0;
        		if(right == n){
        			return cnt;
        		}
        		right++;
        	}
        }
        return cnt;
    }
}
// @lc code=end
