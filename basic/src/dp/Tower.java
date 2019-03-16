package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * DP trick: Exchange argument
 * 
 * https://atcoder.jp/contests/dp/tasks/dp_x
 * 
 * There are N blocks, numbered 1,2,…,N. For each i (1≤i≤N), Block i has a
 * weight of wi, a solidness of si and a value of vi.
 * 
 * Taro has decided to build a tower by choosing some of the N blocks and
 * stacking them vertically in some order. Here, the tower must satisfy the
 * following condition:
 * 
 * For each Block i contained in the tower, the sum of the weights of the blocks
 * stacked above it is not greater than si.
 * 
 * Find the maximum possible sum of the values of the blocks contained in the
 * tower.
 */
public class Tower {
	public static void main(String[] args) {
		int n = 3;
		int[][] a = new int[][] { new int[] { 2, 2, 20 }, new int[] { 2, 1, 30 }, new int[] { 3, 1, 40 } };
		long r = solve(n, a);
		System.out.println(r); // 50
	}

	private static long solve(int n, int[][] a) {
		Arrays.sort(a, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0] + o1[1], o2[0] + o2[1]);
			}
		});

		long[][] dp = new long[n + 1][20101];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 20101; j++) {
				dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
				if (j <= a[i][1]) {
					dp[i + 1][j + a[i][0]] = Math.max(dp[i + 1][j + a[i][0]], dp[i][j] + a[i][2]);
				}
			}
		}
		long max = 0;
		for (int i = 0; i < 20101; i++) {
			max = Math.max(max, dp[n][i]);
		}
		return max;
	}

	static String str(int[] a) {
		String[] str = new String[a.length];
		for (int i = 0; i < a.length; i++) {
			str[i] = a[i] + "";
		}
		return String.join(" ", str);
	}

	static int[] getIntArr(Scanner in, int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = in.nextInt();
		}
		return arr;
	}

	static int[][] getIntArr(Scanner in, int row, int col) {
		int[][] arr = new int[row][];
		for (int i = 0; i < row; i++) {
			arr[i] = getIntArr(in, col);
		}
		return arr;
	}

	static char[] getCharArr(Scanner in, int size) {
		char[] arr = in.next().toCharArray();
		return arr;
	}

	static String[] getStringArr(Scanner in, int size) {
		String[] arr = new String[size];
		for (int i = 0; i < size; i++) {
			arr[i] = in.next();
		}
		return arr;
	}

	static Map<Integer, List<Integer>> getEdges(Scanner in, int size, boolean directed) {
		Map<Integer, List<Integer>> edges = new HashMap<>();
		for (int i = 0; i < size; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			if (!edges.containsKey(from)) {
				edges.put(from, new ArrayList<Integer>());
			}
			edges.get(from).add(to);
			if (!directed) {
				if (!edges.containsKey(to)) {
					edges.put(to, new ArrayList<Integer>());
				}
				edges.get(to).add(from);
			}

		}
		return edges;
	}
}
