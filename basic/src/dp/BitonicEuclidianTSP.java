package dp;

import java.util.Arrays;

/** O(n^2) */
public class BitonicEuclidianTSP {

	public static void main(String[] args) {
		Point[] p = new Point[] { new Point(0, 6), new Point(1, 0), new Point(2, 3), new Point(5, 4), new Point(6, 1),
				new Point(7, 5), new Point(8, 2) };
		Arrays.sort(p);
		System.out.println(BitonicEuclidianTSP.shortestPath(p));
	}

	private static double shortestPath(Point[] p) {
		int n = p.length;
		double[][] dist = new double[n][n];
		dist[0][0] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				if (j < i - 1) {
					dist[i][j] = dist[i - 1][j] + Point.distance(p[i], p[i - 1]);
				} else if (j == i - 1) {
					dist[i][j] = Double.MAX_VALUE;
					for (int k = 0; k <= i - 1; k++) {
						dist[i][j] = Math.min(dist[i][j], dist[i - 1][k] + Point.distance(p[i], p[k]));
					}
				} else if (i == j && i > 0) {
					dist[i][i] = dist[i][i - 1] + Point.distance(p[i], p[i - 1]);
				}
			}
		}
		return dist[n - 1][n - 1];
	}

}

class Point implements Comparable<Point> {
	double x, y;

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		return Double.compare(x, o.x);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
}
