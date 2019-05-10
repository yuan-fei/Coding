package math.computationalGeometry;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SegmentIntersection {

	public static void main(String[] args) {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(1, 0);
		System.out.println(segmentsIntersect(new Segment(p1, p2), new Segment(p3, p4)));
		System.out.println(segmentsIntersect(new Segment(p1, p3), new Segment(p2, p4)));
		System.out.println(segmentsIntersect(new Segment(p1, p2), new Segment(p2, p3)));
		System.out.println(anySegmentsIntersect(new Segment[] { new Segment(p1, p2), new Segment(p3, p4) }));
		System.out.println(anySegmentsIntersect(new Segment[] { new Segment(p1, p3), new Segment(p2, p4) }));
		System.out.println(anySegmentsIntersect(new Segment[] { new Segment(p1, p2), new Segment(p2, p3) }));
	}

	/**
	 * Shamos-Hoey (Sweep line) method for finding any two segments among a number
	 * of segments intersect in O(nlogn)
	 */
	public static boolean anySegmentsIntersect(Segment[] segments) {
		List<CheckPoint> checkPoints = getCheckPoints(segments);
		TreeSet<Segment> t = new TreeSet<Segment>();
		for (CheckPoint c : checkPoints) {
			if (c.isLeftEnd) {
				t.add(c.s);
				Segment above = t.higher(c.s);
				Segment below = t.lower(c.s);
				if ((above != null && segmentsIntersect(c.s, above))
						|| (below != null && segmentsIntersect(c.s, below))) {
					return true;
				}
			} else {
				t.remove(c.s);
				Segment above = t.higher(c.s);
				Segment below = t.lower(c.s);
				if (above != null && below != null && segmentsIntersect(above, below)) {
					return true;
				}
			}
		}
		return false;
	}

	private static List<CheckPoint> getCheckPoints(Segment[] segments) {
		return Arrays.stream(segments)
				.flatMap(s -> Arrays.asList(new CheckPoint(s.p1, s, true), new CheckPoint(s.p2, s, false)).stream())
				.sorted().collect(Collectors.toList());
	}

	public static boolean segmentsIntersect(Segment s1, Segment s2) {
		int d1 = Segment.direction(s1, s2.p1);
		int d2 = Segment.direction(s1, s2.p2);
		int d3 = Segment.direction(s2, s1.p1);
		int d4 = Segment.direction(s2, s1.p2);
		if (d1 * d2 < 0 && d3 * d4 < 0) {
			return true;
		} else if (d1 == 0 && Segment.onSegment(s1, s2.p1)) {
			return true;
		} else if (d2 == 0 && Segment.onSegment(s1, s2.p2)) {
			return true;
		} else if (d3 == 0 && Segment.onSegment(s2, s1.p1)) {
			return true;
		} else if (d4 == 0 && Segment.onSegment(s2, s1.p2)) {
			return true;
		}
		return false;
	}

}

class CheckPoint implements Comparable<CheckPoint> {
	Point p;
	Segment s;
	boolean isLeftEnd;

	public CheckPoint(Point p, Segment s, boolean isLeftEnd) {
		super();
		this.p = p;
		this.s = s;
		this.isLeftEnd = isLeftEnd;
	}

	@Override
	public int compareTo(CheckPoint c) {
		int xDiff = Double.compare(this.p.x, c.p.x);
		int yDiff = Double.compare(this.p.y, c.p.y);
		if (xDiff != 0) {
			return xDiff;
		} else if (yDiff != 0) {
			return yDiff;
		} else if (this.isLeftEnd && !c.isLeftEnd) {
			return -1;
		} else if (c.isLeftEnd && !this.isLeftEnd) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "<" + p + "->" + s + ">";
	}
}
