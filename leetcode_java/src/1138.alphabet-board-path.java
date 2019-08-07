/*
 * @lc app=leetcode id=1138 lang=java
 *
 * [1138] Alphabet Board Path
 *
 * https://leetcode.com/problems/alphabet-board-path/description/
 *
 * algorithms
 * Medium (42.31%)
 * Total Accepted:    5K
 * Total Submissions: 11.9K
 * Testcase Example:  '"leet"'
 *
 * On an alphabet board, we start at position (0, 0), corresponding to
 * character board[0][0].
 * 
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown
 * in the diagram below.
 * 
 * 
 * 
 * We may make the following moves:
 * 
 * 
 * 'U' moves our position up one row, if the position exists on the board;
 * 'D' moves our position down one row, if the position exists on the
 * board;
 * 'L' moves our position left one column, if the position exists on the
 * board;
 * 'R' moves our position right one column, if the position exists on the
 * board;
 * '!' adds the character board[r][c] at our current position (r, c) to the
 * answer.
 * 
 * 
 * (Here, the only positions that exist on the board are positions with letters
 * on them.)
 * 
 * Return a sequence of moves that makes our answer equal to target in the
 * minimum number of moves.  You may return any path that does so.
 * 
 * 
 * Example 1:
 * Input: target = "leet"
 * Output: "DDR!UURRR!!DDD!"
 * Example 2:
 * Input: target = "code"
 * Output: "RR!DDRR!UUL!R!"
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= target.length <= 100
 * target consists only of English lowercase letters.
 * 
 */
class Solution {
    public String alphabetBoardPath(String target) {
        int prv = 0;
        String ans = "";
        for(char c: target.toCharArray()){
            ans+=move(prv, c-'a');
            prv = c-'a';
            ans+="!";
        }
        return ans;
    }
    
    private String move(int src, int des){
        String ans = "";
        if(src!=des){
            int[] srcPos = getPos(src);
            int[] desPos = getPos(des);
            // System.out.println(Arrays.toString(srcPos)+Arrays.toString(desPos));
            
            while(srcPos[1]!=desPos[1]||srcPos[0]!=desPos[0]){
                
                if(srcPos[1]>desPos[1]){
                    ans+="L";
                    srcPos[1]+=-1;    
                }
                
                else if(srcPos[0]>desPos[0]){
                    ans+="U";
                    srcPos[0]+=-1;    
                }
                
                else if(srcPos[1]<desPos[1]){
                    ans+="R";
                    srcPos[1]+=1;    
                }
                
                
                else if(srcPos[0]<desPos[0]){
                    ans+="D";
                    srcPos[0]+=1;    
                }
                
            }
            
        }
        return ans;
    }
    
    private int[] getPos(int c){
        return new int[]{c/5,c%5};
    }
}
