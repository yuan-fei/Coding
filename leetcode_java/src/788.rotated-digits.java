/*
 * @lc app=leetcode id=788 lang=java
 *
 * [788] Rotated Digits
 *
 * https://leetcode.com/problems/rotated-digits/description/
 *
 * algorithms
 * Medium (56.82%)
 * Likes:    637
 * Dislikes: 1816
 * Total Accepted:    93K
 * Total Submissions: 163.6K
 * Testcase Example:  '10'
 *
 * An integer x is a good if after rotating each digit individually by 180
 * degrees, we get a valid number that is different from x. Each digit must be
 * rotated - we cannot choose to leave it alone.
 * 
 * A number is valid if each digit remains a digit after rotation. For
 * example:
 * 
 * 
 * 0, 1, and 8 rotate to themselves,
 * 2 and 5 rotate to each other (in this case they are rotated in a different
 * direction, in other words, 2 or 5 gets mirrored),
 * 6 and 9 rotate to each other, and
 * the rest of the numbers do not rotate to any other number and become
 * invalid.
 * 
 * 
 * Given an integer n, return the number of good integers in the range [1,
 * n].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 10
 * Output: 4
 * Explanation: There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
 * Note that 1 and 10 are not good numbers, since they remain unchanged after
 * rotating.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 1
 * Output: 0
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 2
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int rotatedDigits(int n) {
        return solve(n, new HashSet(Arrays.asList(0,1,2,5,6,8,9))) - solve(n, new HashSet(Arrays.asList(0,1,8)));
    }

    
    int[][] cache;
    int solve(int n, Set<Integer> legalDigits){
        String s = "" + n;
        cache = new int[s.length()][2];
        for(int i = 0; i < cache.length; i++){
            Arrays.fill(cache[i], -1);
        }
        return rec(s, legalDigits, 0, true);
    }
    
    int rec(String n, Set<Integer> legalDigits, int pos, boolean isTight){
        if(pos == n.length()){
            return 1;
        }
        int b = isTight? 1 : 0;
        if(cache[pos][b] == -1){
            int max = isTight ? n.charAt(pos) - '0' : 9;
            cache[pos][b] = 0;
            for(int i = 0; i <= max; i++){
                if(legalDigits.contains(i)){
                    cache[pos][b] += rec(n, legalDigits, pos + 1, isTight && (i == max));
                }
            }
        }
        return cache[pos][b];
    }
}
// @lc code=end
