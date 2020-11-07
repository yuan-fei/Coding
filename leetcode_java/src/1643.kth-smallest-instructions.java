/*
 * @lc app=leetcode id=1643 lang=java
 *
 * [1643] Kth Smallest Instructions
 *
 * https://leetcode.com/problems/kth-smallest-instructions/description/
 *
 * algorithms
 * Hard (40.60%)
 * Likes:    117
 * Dislikes: 2
 * Total Accepted:    3.1K
 * Total Submissions: 7.6K
 * Testcase Example:  '[2,3]\n1'
 *
 * Bob is standing at cell (0, 0), and he wants to reach destination: (row,
 * column). He can only travel right and down. You are going to help Bob by
 * providing instructions for him to reach destination.
 * 
 * The instructions are represented as a string, where each character is
 * either:
 * 
 * 
 * 'H', meaning move horizontally (go right), or
 * 'V', meaning move vertically (go down).
 * 
 * 
 * Multiple instructions will lead Bob to destination. For example, if
 * destination is (2, 3), both "HHHVV" and "HVHVH" are valid instructions.
 * 
 * 
 * 
 * 
 * However, Bob is very picky. Bob has a lucky number k, and he wants the k^th
 * lexicographically smallest instructions that will lead him to destination. k
 * is 1-indexed.
 * 
 * Given an integer array destination and an integer k, return the k^th
 * lexicographically smallest instructions that will take Bob to
 * destination.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: destination = [2,3], k = 1
 * Output: "HHHVV"
 * Explanation: All the instructions that reach (2, 3) in lexicographic order
 * are as follows:
 * ["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH",
 * "VHVHH", "VVHHH"].
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: destination = [2,3], k = 2
 * Output: "HHVHV"
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: destination = [2,3], k = 3
 * Output: "HHVVH"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * destination.length == 2
 * 1 <= row, column <= 15
 * 1 <= k <= nCr(row + column, row), where nCr(a, b) denotes a choose b​​​​​.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String kthSmallestPath(int[] destination, int k) {
    	int n = destination[0];
    	int m = destination[1];
        int[][] cnt = new int[n + 2][m + 2];
        cnt[n + 1][m] = 1;
        for(int i = n; i >= 0; i--){
        	for(int j = m; j >= 0; j--){
        		cnt[i][j] = cnt[i][j + 1] + cnt[i + 1][j];
        	}
        }
        int i = 0;
        int j = 0;
        String s = "";
        while(i != n || j != m){
        	// System.out.println(i + ", " + j);
        	if(k <= cnt[i][j + 1]){
        		s += "H";
        		j++;
        	}
        	else{
        		s += "V";
        		k -= cnt[i][j + 1];
        		i++;
        	}
        }
        return s;
    }
}
// @lc code=end
