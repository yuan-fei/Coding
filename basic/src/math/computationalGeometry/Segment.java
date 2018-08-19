package math.computationalGeometry;

class Segment implements Comparable<Segment> {
	Point p1;
	Point p2;

	public Segment(Point p1, Point p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}

	public static int direction(Segment s, Point p) {
		return direction(s.p1, s.p2, p);
	}

	public static int direction(Point p1, Point p2, Point p3) {
		return (int) Math.signum(Point.getVector(p1, p2).dotProduct(Point.getVector(p2, p3)));
	}

	public static boolean onSegment(Segment s, Point p) {
		return onSegment(s.p1, s.p2, p);
	}

	public static boolean onSegment(Point p1, Point p2, Point p3) {
		return Math.min(p1.x, p2.x) <= p3.x && p3.x <= Math.max(p1.x, p2.x) && Math.min(p1.y, p2.y) <= p3.y
				&& p3.y <= Math.max(p1.y, p2.y) && (p3.y - p1.y) * (p2.x - p1.x) == (p3.x - p1.x) * (p2.y - p1.y);
	}

	@Override
	public String toString() {
		return "[" + p1 + ", " + p2 + "]";
	}

	@Override
	public int compareTo(Segment s) {
		if (!s.p1.equals(p1)) {
			if (s.p1.x == p1.x) {
				return Double.compare(p1.y, s.p1.y);
			}
			return direction(s, p1);
		} else {
			return direction(s, p2);
		}
	}

	public static void main(String[] args) {
		Point p3 = new Point(1, 1);
		Point p4 = new Point(1, 0);
		Point p5 = new Point(2d / 3, 2d / 3);
	}
}
