/*
 * @lc app=leetcode id=519 lang=java
 *
 * [519] Random Flip Matrix
 *
 * https://leetcode.com/problems/random-flip-matrix/description/
 *
 * algorithms
 * Medium (36.82%)
 * Likes:    191
 * Dislikes: 59
 * Total Accepted:    8.5K
 * Total Submissions: 23K
 * Testcase Example:  '["Solution", "flip", "flip", "flip", "flip"]\n[[2, 2], [], [], [], []]'
 *
 * You are given the number of rows n_rows and number of columns n_cols of a 2D
 * binary matrix where all values are initially 0. Write a function flip which
 * chooses a 0 value uniformly at random, changes it to 1, and then returns the
 * position [row.id, col.id] of that value. Also, write a function reset which
 * sets all values back to 0. Try to minimize the number of calls to system's
 * Math.random() and optimize the time and space complexity.
 * 
 * Note:
 * 
 * 
 * 1 <= n_rows, n_cols <= 10000
 * 0 <= row.id < n_rows and 0 <= col.id < n_cols
 * flip will not be called when the matrix has no 0 values left.
 * the total number of calls to flip and reset will not exceed 1000.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: 
 * ["Solution","flip","flip","flip","flip"]
 * [[2,3],[],[],[],[]]
 * Output: [null,[0,1],[1,2],[1,0],[1,1]]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 
 * ["Solution","flip","flip","reset","flip"]
 * [[1,2],[],[],[],[]]
 * Output: [null,[0,0],[0,1],null,[0,0]]
 * 
 * 
 * Explanation of Input Syntax:
 * 
 * The input is two lists: the subroutines called and their arguments.
 * Solution's constructor has two arguments, n_rows and n_cols. flip and reset
 * have no arguments. Arguments are always wrapped with a list, even if there
 * aren't any.
 * 
 */

// @lc code=start
class Solution {

	int n_rows;
	int n_cols;
	int total;
	Map<Integer, Integer> m;
	Random rnd = new Random();
    public Solution(int n_rows, int n_cols) {
        this.n_rows = n_rows;
        this.n_cols = n_cols;
        reset();
    }
    
    public int[] flip() {
        int r = rnd.nextInt(total--);
        int x = m.getOrDefault(r, r);
        m.put(r, m.getOrDefault(total, total));
        return new int[]{x / n_cols, x % n_cols};
    }
    
    public void reset() {
        m = new HashMap<>();
        total = n_rows * n_cols;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n_rows, n_cols);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */
// @lc code=end
