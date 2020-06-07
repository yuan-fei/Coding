/*
 * @lc app=leetcode id=488 lang=java
 *
 * [488] Zuma Game
 *
 * https://leetcode.com/problems/zuma-game/description/
 *
 * algorithms
 * Hard (40.00%)
 * Likes:    190
 * Dislikes: 195
 * Total Accepted:    13.3K
 * Total Submissions: 33.3K
 * Testcase Example:  '"WRRBBW"\n"RB"'
 *
 * Think about Zuma Game. You have a row of balls on the table, colored red(R),
 * yellow(Y), blue(B), green(G), and white(W). You also have several balls in
 * your hand.
 * 
 * Each time, you may choose a ball in your hand, and insert it into the row
 * (including the leftmost place and rightmost place). Then, if there is a
 * group of 3 or more balls in the same color touching, remove these balls.
 * Keep doing this until no more balls can be removed.
 * 
 * Find the minimal balls you have to insert to remove all the balls on the
 * table. If you cannot remove all the balls, output -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: board = "WRRBBW", hand = "RB"
 * Output: -1
 * Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: board = "WWRRBBWW", hand = "WRBRW"
 * Output: 2
 * Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW ->
 * empty
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: board = "G", hand = "GGGGG"
 * Output: 2
 * Explanation: G -> G[G] -> GG[G] -> empty 
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: board = "RBYYBBRRB", hand = "YRBGB"
 * Output: 3
 * Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] ->
 * BB[B] -> empty 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * You may assume that the initial row of balls on the table won’t have any 3
 * or more consecutive balls with the same color.
 * 1 <= board.length <= 16
 * 1 <= hand.length <= 5
 * Both input strings will be non-empty and only contain characters
 * 'R','Y','B','G','W'.
 * 
 * 
 */

// @lc code=start
class Solution {
     private int aux(String s, int[] c){
        if("".equals(s)) return 0;
//worst case, every character needs 2 characters; plus one to make it impossible, ;-)
        int res = 2 * s.length() + 1; 
        for(int i = 0; i < s.length();){
            int j = i++;
            while(i < s.length() && s.charAt(i) == s.charAt(j)) i++;
            int inc = 3 - i + j;
            if(c[s.charAt(j)] >= inc){
                int used = inc <= 0 ? 0 : inc;
                c[s.charAt(j)] -= used;
                int temp = aux(s.substring(0, j) + s.substring(i), c);
                if(temp >= 0) res = Math.min(res, used + temp);
                c[s.charAt(j)] += used;
            }
        }
        return res == 2 * s.length() + 1 ? -1 : res;
    }
    
    public int findMinStep(String board, String hand) {
        int[] c = new int[128];
        for(char x : hand.toCharArray()){
            c[x]++;
        }
        return  aux(board, c);
    }
}
// @lc code=end
