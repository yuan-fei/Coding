/*
 * @lc app=leetcode id=1728 lang=java
 *
 * [1728] Cat and Mouse II
 *
 * https://leetcode.com/problems/cat-and-mouse-ii/description/
 *
 * algorithms
 * Hard (38.02%)
 * Likes:    48
 * Dislikes: 15
 * Total Accepted:    1.8K
 * Total Submissions: 4.8K
 * Testcase Example:  '["####F","#C...","M...."]\n1\n2'
 *
 * A game is played by a cat and a mouse named Cat and Mouse.
 * 
 * The environment is represented by a grid of size rows x cols, where each
 * element is a wall, floor, player (Cat, Mouse), or food.
 * 
 * 
 * Players are represented by the characters 'C'(Cat),'M'(Mouse).
 * Floors are represented by the character '.' and can be walked on.
 * Walls are represented by the character '#' and cannot be walked on.
 * Food is represented by the character 'F' and can be walked on.
 * There is only one of each character 'C', 'M', and 'F' in grid.
 * 
 * 
 * Mouse and Cat play according to the following rules:
 * 
 * 
 * Mouse moves first, then they take turns to move.
 * During each turn, Cat and Mouse can jump in one of the four directions
 * (left, right, up, down). They cannot jump over the wall nor outside of the
 * grid.
 * catJump, mouseJump are the maximum lengths Cat and Mouse can jump at a time,
 * respectively. Cat and Mouse can jump less than the maximum length.
 * Staying in the same position is allowed.
 * Mouse can jump over Cat.
 * 
 * 
 * The game can end in 4 ways:
 * 
 * 
 * If Cat occupies the same position as Mouse, Cat wins.
 * If Cat reaches the food first, Cat wins.
 * If Mouse reaches the food first, Mouse wins.
 * If Mouse cannot get to the food within 1000 turns, Cat wins.
 * 
 * 
 * Given a rows x cols matrix grid and two integers catJump and mouseJump,
 * return true if Mouse can win the game if both Cat and Mouse play optimally,
 * otherwise return false.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
 * Output: true
 * Explanation: Cat cannot catch Mouse on its turn nor can it get the food
 * before Mouse.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: grid = ["M.C...F"], catJump = 1, mouseJump = 4
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = ["M.C...F"], catJump = 1, mouseJump = 3
 * Output: false
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
 * Output: false
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3,
 * mouseJump = 1
 * Output: true
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * rows == grid.length
 * cols = grid[i].length
 * 1 <= rows, cols <= 8
 * grid[i][j] consist only of characters 'C', 'M', 'F', '.', and '#'.
 * There is only one of each character 'C', 'M', and 'F' in grid.
 * 1 <= catJump, mouseJump <= 8
 * 
 */

// @lc code=start
class Solution {
	private static char FOOD = 'F';
	private static char MOUSE = 'M';
	private static char CAT = 'C';
	private static char WALL = '#';
	private static char FLOOR = '.';
	private final static int[][] DIRS = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static char[][] board;
	int m = 0;
	int n = 0;

	public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
		m = grid.length;
		n = grid[0].length();
		board = new char[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = grid[i].charAt(j);
			}
		}

		int len = m * n;
		int[][][] color = new int[len][len][3];
		int degree[][][] = new int[len][len][3];
		int catStart = 0;
		int mouseStart = 0;

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				degree[i][j][1] = getNbs(i, mouseJump).size();
				degree[i][j][2] = getNbs(j, catJump).size();
			}
		}

		Queue<int[]> q = new LinkedList<>();;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == FOOD) {
					int index = getIndex(i, j);
					for (int x = 0; x < m; x++) {
						for (int y = 0; y < n; y++) {
							if (board[x][y] == WALL) {
								continue;
							}
							color[index][getIndex(x, y)][2] = 1;
							q.offer(new int[]{index, getIndex(x, y), 2});
						}
					}
				}
				if (board[i][j] == CAT) {
					catStart = getIndex(i, j);
				}
				if (board[i][j] == MOUSE) {
					mouseStart = getIndex(i, j);
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == WALL) continue;
				int index = getIndex(i, j);
				color[index][index][1] = 2;
				color[index][index][2] = 2;
				q.offer(new int[]{index, index, 2});
				q.offer(new int[]{index, index, 1});
			}
		}

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int i = cur[0];
			int j = cur[1];
			int t = cur[2];
			int c = color[i][j][t];
			List<Integer> nbs = t == 2 ? getNbs(i, mouseJump) : getNbs(j, catJump);
			int t2 = 3 - t;
			for (int nb : nbs) {
				int i2 = i;
				int j2 = j;
				if (t2 == 1) {
					i2 = nb;
				}
				else {
					j2 = nb;
				}
				if (color[i2][j2][t2] != 0) continue;
				if (c == t2) {
					color[i2][j2][t2] = c;
					q.offer(new int[]{i2, j2, t2});
				} else {
					if (--degree[i2][j2][t2] == 0) {
						color[i2][j2][t2] = c;
						q.offer(new int[]{i2, j2, t2});
					}
				}
			}
		}

		return color[mouseStart][catStart][1] == 1;
	}

	private int getIndex(int i, int j) {
		return i * n + j;
	}

	private List<Integer> getNbs (int index, int jump) {
		int x = index / n;
		int y = index % n;
		List<Integer> res = new ArrayList<>();
		res.add(index);
		for (int[] dir : DIRS) {
			int nX = x + dir[0];
			int nY = y + dir[1];
			int jumped = 1;
			while (nX >= 0 && nY >= 0 && nX < m && nY < n && board[nX][nY] != WALL && jumped <= jump) {
				res.add(getIndex(nX, nY));
				nX += dir[0];
				nY += dir[1];
				jumped++;
			}
		}
		return res;
	}
}
// @lc code=end
