import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix of 0s and 1s, find
 * 
 * 1. the min of any 0's max manhattan distance to 1s
 * 
 * 2. the max of any 0's min manhattan distance to 1s
 */
public class ManhattanDistanceInMatrix {

	public static void main(String[] args) {
		int[][] grid = new int[][] { new int[] { 1, 0, 1 }, new int[] { 0, 0, 0 }, new int[] { 1, 0, 1 } };
		System.out.println(maxMinDistance(grid));
		System.out.println(minMaxDistance(grid));
		grid = new int[][] { new int[] { 1, 0, 0 }, new int[] { 0, 0, 0 }, new int[] { 0, 0, 0 } };
		System.out.println(maxMinDistance(grid));
		System.out.println(minMaxDistance(grid));
	}

	public static int maxMinDistance(int[][] grid) {
		int N = grid.length;
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] == 1) {
					q.offer(new int[] { i, j });
					visited[i][j] = true;
				}
			}
		}
		int[] dx = new int[] { 0, 0, 1, -1 };
		int[] dy = new int[] { 1, -1, 0, 0 };
		int d = 0;
		while (!q.isEmpty()) {
			int n = q.size();
			for (int m = 0; m < n; m++) {
				int[] cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int x = cur[0] + dx[i];
					int y = cur[1] + dy[i];
					if (x >= 0 && y >= 0 && x < N && y < N && !visited[x][y]) {
						q.offer(new int[] { x, y });
						visited[x][y] = true;
					}
				}
			}
			if (!q.isEmpty()) {
				d++;
			}
		}
		return d;
	}

	public static int minMaxDistance(int[][] grid) {
		int r = grid.length;
		int c = grid[0].length;
		int lb = 0;
		int ub = r + c;
		while (lb + 1 < ub) {
			int mid = (lb + ub) / 2;
			if (feasible(grid, mid)) {
				ub = mid;
			} else {
				lb = mid;
			}
		}
		if (feasible(grid, lb)) {
			return lb;
		}
		if (feasible(grid, ub)) {
			return ub;
		}
		return -1;
	}

	private static boolean feasible(int[][] grid, int maxD) {
		int minSum = Integer.MAX_VALUE;
		int maxSum = Integer.MIN_VALUE;
		int minDiff = Integer.MAX_VALUE;
		int maxDiff = Integer.MIN_VALUE;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					minSum = Math.min(i + j + maxD, minSum);
					maxSum = Math.max(i + j - maxD, maxSum);
					minDiff = Math.min(i - j + maxD, minDiff);
					maxDiff = Math.max(i - j - maxD, maxDiff);
				}
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 0 && i + j <= minSum && i + j >= maxSum && i - j <= minDiff && i - j >= maxDiff) {
					return true;
				}
			}
		}
		return false;
	}
}
