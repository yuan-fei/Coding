package greedy;

import java.util.PriorityQueue;

public class BreakingString {

	public static void main(String[] args) {
		System.out.println(cut(21, new int[] { 5, 8, 8 }));
	}

	/**
	 * Problem: Cut a string into sizes, a cut of s cost len(s), find the minimum
	 * cost cut of a string
	 */
	public static int cut(int totalSize, int[] size) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int s : size) {
			q.offer(s);
		}
		q.offer(Integer.MAX_VALUE);
		int cost = 0;
		while (!q.isEmpty()) {
			int min1 = q.poll();
			int min2 = q.poll();
			if (min2 == Integer.MAX_VALUE) {
				return cost;
			} else {
				cost += (min1 + min2);
				q.offer(min1 + min2);
			}
		}
		return -1;
	}

}
