package dp;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * books[i]: {width, height}
 * 
 * https://leetcode.com/problems/filling-bookcase-shelves/
 */
public class FillingBookcaseShelves {
	public static void main(String[] args) {
		int[][] books = new int[][] { new int[] { 1, 1 }, new int[] { 2, 3 }, new int[] { 2, 3 }, new int[] { 1, 1 },
				new int[] { 1, 1 }, new int[] { 1, 1 }, new int[] { 1, 2 } };
		System.out.println(minHeightShelves(books, 4));// 6
		System.out.println(minHeightShelvesByCHT(books, 4));// 6
	}

	/**
	 * O(n^2): dp[i] = min(max(h[j],...,h[i])+dp[j-1], dp[i]) for all j that book j
	 * to book i can be put in one shelf layer
	 */
	public static int minHeightShelves(int[][] books, int shelf_width) {
		int[] dp = new int[books.length + 1];

		for (int i = 1; i <= books.length; i++) {
			dp[i] = Integer.MAX_VALUE;
			int maxHeight = 0;
			int freeWidth = shelf_width;
			for (int j = i; j > 0 && freeWidth - books[j - 1][0] >= 0; j--) {
				freeWidth -= books[j - 1][0];
				maxHeight = Math.max(maxHeight, books[j - 1][1]);
				dp[i] = Math.min(dp[i], dp[j - 1] + maxHeight);
			}
			// System.out.println(dp[i]);
		}
		return dp[books.length];
	}

	public static int minHeightShelvesByCHT(int[][] books, int shelf_width) {
		int[] dp = new int[books.length + 1];
		int[] prefSum = new int[books.length + 1];
		for (int i = 1; i < prefSum.length; i++) {
			prefSum[i] = prefSum[i - 1] + books[i - 1][0];
		}
		Deque<Interval> dq = new LinkedList<>();
		Multiset bst = new Multiset();
		for (int i = 1; i <= books.length; i++) {
			// merge with new height
			int start = i;
			int hCur = books[i - 1][1];
			while (!dq.isEmpty()) {
				int lastHMax = books[dq.peekLast().end - 1][1];
				if (lastHMax <= hCur) {
					Interval lastItv = dq.pollLast();
					int lastBest = dp[lastItv.start - 1] + books[lastItv.end - 1][1];
					bst.remove(lastBest);
					start = lastItv.start;
				} else {
					break;
				}
			}
			dq.offerLast(new Interval(start, i));
			bst.add(dp[start - 1] + books[i - 1][1]);

			// remove invalid
			start = dq.peekFirst().start;
			while (start < i && prefSum[i] - prefSum[start - 1] > shelf_width) {
				start++;
			}
			while (!dq.isEmpty() && dq.peekFirst().end < start) {
				Interval oldItv = dq.pollFirst();
				bst.remove(dp[oldItv.start - 1] + books[oldItv.end - 1][1]);
			}
			bst.remove(dp[dq.peekFirst().start - 1] + books[dq.peekFirst().end - 1][1]);
			bst.add(dp[start - 1] + books[dq.peekFirst().end - 1][1]);
			dq.peekFirst().start = start;
			dp[i] = bst.best();
		}
		return dp[books.length];
	}

	static class Multiset {
		TreeMap<Integer, Integer> bst = new TreeMap<>();

		public void add(int v) {
			if (bst.containsKey(v)) {
				bst.put(v, bst.get(v) + 1);
			} else {
				bst.put(v, 1);
			}
		}

		public void remove(int v) {
			if (bst.get(v) == 1) {
				bst.remove(v);
			} else {
				bst.put(v, bst.get(v) - 1);
			}
		}

		public int best() {
			return bst.firstKey();
		}
	}

	static class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}
}
