package math.computationalGeometry;

import math.BitManipulation;

public class Rectangle {

	public static void main(String[] args) {
		Rectangle a = new Rectangle(0, 0, 1, 1);
		Rectangle b = new Rectangle(0, 0, 1, 1);
		System.out.println(Rectangle.intersect(a, b));
		b = new Rectangle(0, 0, 2, 2);
		System.out.println(Rectangle.intersect(a, b));
		b = new Rectangle(1, 1, 2, 2);
		System.out.println(Rectangle.intersect(a, b));
		b = new Rectangle(2, 2, 3, 3);
		System.out.println(Rectangle.intersect(a, b));
		Rectangle[] rects = new Rectangle[] { new Rectangle(0, 0, 1, 1), new Rectangle(0, 0, 2, 2),
				new Rectangle(1, 1, 2, 2), new Rectangle(2, 2, 3, 3) };
		System.out.println(Rectangle.unionArea(rects));
		rects = new Rectangle[] { new Rectangle(0, 0, 1, 1), new Rectangle(2, 2, 3, 3) };
		System.out.println(Rectangle.unionArea(rects));
		Rectangle r = new Rectangle(-5203, -1795, -4648, 1721);
		System.out.println(r.intersectWithCircle(1206, -5597, -276));
	}

	double left;
	double bottom;
	double right;
	double top;

	public Rectangle(double left, double bottom, double right, double top) {
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
	}

	@Override
	public String toString() {
		return "[" + left + ", " + bottom + ", " + right + ", " + top + "]";
	}

	public double getArea() {
		return (right - left) * (top - bottom);
	}

	public boolean isValid() {
		return bottom <= top && left <= right;
	}

	public static Rectangle intersect(Rectangle a, Rectangle b) {
		double iLeft = Math.max(a.left, b.left);
		double iBottom = Math.max(a.bottom, b.bottom);
		double iRight = Math.min(a.right, b.right);
		double iTop = Math.min(a.top, b.top);
		return new Rectangle(iLeft, iBottom, iRight, iTop);
	}

	public static double unionArea(Rectangle[] rects) {
		int n = rects.length;
		int cnt = 1 << n;
		double area = 0;
		for (int mask = 1; mask < cnt; mask++) {
			Rectangle intersection = null;
			for (int j = 0; j < n; j++) {
				if (((mask >> j) & 1) == 1) {
					if (intersection == null) {
						intersection = rects[j];
					} else {
						intersection = Rectangle.intersect(rects[j], intersection);
					}
				}
			}
			double intersectArea = intersection.isValid() ? intersection.getArea() : 0;
			area += (BitManipulation.countOnes(mask) % 2 == 1) ? intersectArea : -intersectArea;
		}
		return area;
	}

	/** https://leetcode.com/problems/circle-and-rectangle-overlapping/discuss/563341/4-lines-C%2B%2B-O(1)%3A-Test-shortest-distance-from-center-to-rect-(with-pics) */
	public boolean intersectWithCircle(double radius, double x_center, double y_center) {
		// find closest point
		double x = (x_center <= left) ? left : (x_center >= right) ? right : x_center;
		double y = (y_center <= bottom) ? bottom : (y_center >= top) ? top : y_center;
		return checkInCircle(radius, x_center, y_center, x, y);
	}

	static boolean checkInCircle(double radius, double x_center, double y_center, double x, double y) {
		return radius * radius >= (x_center - x) * (x_center - x) + (y_center - y) * (y_center - y);
	}
}
