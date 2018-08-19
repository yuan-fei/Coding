package math.computationalGeometry;

class Point {
	double x;
	double y;

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public boolean equals(Object p) {
		Point that = (Point) p;
		return x == that.x && y == that.y;
	}

	public double dotProduct(Point p) {
		return this.x * p.y - this.y * p.x;
	}

	public static Point getVector(Point from, Point to) {
		return new Point(to.x - from.x, to.y - from.y);
	}

	public double norm() {
		return Math.sqrt(x * x + y * y);
	}
}
