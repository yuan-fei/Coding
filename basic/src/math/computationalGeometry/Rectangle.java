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
}
