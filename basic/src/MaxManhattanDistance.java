/**
 * Given a set of points, find the max manhattan distance of a pair of points
 */
public class MaxManhattanDistance {

	public static void main(String[] args) {
		int[][] points = new int[][] { new int[] { 0, 0 }, new int[] { 1, 0 }, new int[] { 0, 1 },
				new int[] { 1, 1 }, };
		System.out.println(getMaxDistance(points));
	}

	/** |x1-x2|+|y1-y2| = max(|(x1+y1)+(x2+y2)|,|(x1+y1)-(x2+y2)|) */
	public static int getMaxDistance(int[][] points) {
		int maxSum = Integer.MIN_VALUE;
		int minSum = Integer.MAX_VALUE;
		int maxDiff = Integer.MIN_VALUE;
		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			int x = points[i][0];
			int y = points[i][1];
			maxSum = Math.max(maxSum, x + y);
			minSum = Math.min(minSum, x + y);
			maxDiff = Math.max(maxDiff, x - y);
			minDiff = Math.min(minDiff, x - y);
		}
		return Math.max(maxSum - minSum, maxDiff - minDiff);
	}
}
