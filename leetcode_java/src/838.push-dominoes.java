/*
 * @lc app=leetcode id=838 lang=java
 *
 * [838] Push Dominoes
 *
 * https://leetcode.com/problems/push-dominoes/description/
 *
 * algorithms
 * Medium (43.61%)
 * Total Accepted:    12.9K
 * Total Submissions: 29K
 * Testcase Example:  '".L.R...LR..L.."'
 *
 * There are N dominoes in a line, and we place each domino vertically
 * upright.
 * 
 * In the beginning, we simultaneously push some of the dominoes either to the
 * left or to the right.
 * 
 * 
 * 
 * After each second, each domino that is falling to the left pushes the
 * adjacent domino on the left.
 * 
 * Similarly, the dominoes falling to the right push their adjacent dominoes
 * standing on the right.
 * 
 * When a vertical domino has dominoes falling on it from both sides, it stays
 * still due to the balance of the forces.
 * 
 * For the purposes of this question, we will consider that a falling domino
 * expends no additional force to a falling or already fallen domino.
 * 
 * Given a string "S" representing the initial state. S[i] = 'L', if the i-th
 * domino has been pushed to the left; S[i] = 'R', if the i-th domino has been
 * pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
 * 
 * Return a string representing the final state. 
 * 
 * Example 1:
 * 
 * 
 * Input: ".L.R...LR..L.."
 * Output: "LL.RR.LLRRLL.."
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "RR.L"
 * Output: "RR.L"
 * Explanation: The first domino expends no additional force on the second
 * domino.
 * 
 * 
 * Note:
 * 
 * 
 * 0 <= N <= 10^5
 * String dominoes contains only 'L', 'R' and '.'
 * 
 * 
 */
class Solution {
    public String pushDominoes(String dominoes) {
    	int n = dominoes.length();
        char[] ans = dominoes.toCharArray();
        int[] ts = new int[n];
        // Arrays.fill(ts, n+1);
        int curTs = 0;
        for (int i = 0; i < n; i++) {
        	switch(dominoes.charAt(i)){
        		case 'R': 
        			curTs = 1;
        			break;
        		case '.':
        			if(curTs>0){
        				ts[i] = curTs++;
        			}
        			break;
        		case 'L':
        			curTs = 0;
        			break;
        	}
        }
        curTs=0;
        for (int i = n-1; i>=0; i--) {
        	switch(dominoes.charAt(i)){
        		case 'R': 
        			curTs = 0;
        			break;
        		case '.':

        			if(curTs > 0 && ts[i] > curTs){
    					ans[i] = 'L';
    				}
    				else if(ts[i] > 0 && ts[i] < curTs){
    					ans[i] = 'R';
    				}
    				else if(ts[i] > curTs){
    					ans[i] = 'R';
    				}
    				else if(ts[i] < curTs){
    					ans[i] = 'L';
    				}
        			if(curTs>0){
        				curTs++;
        			}
        			
        			break;
        		case 'L':
        			curTs = 1;
        			break;
        	}
        }
        return String.valueOf(ans);
    }
}
