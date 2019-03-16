package dp.opt;

import java.util.Comparator;
import java.util.TreeSet;

public class ConvexHullTrick {
	public static final int MIN = -1;
	public final TreeSet<ConvexHullTrick.Line> hull;
	int type;
	boolean query = false;
	Comparator<ConvexHullTrick.Line> comp = new Comparator<ConvexHullTrick.Line>() {
		public int compare(ConvexHullTrick.Line a, ConvexHullTrick.Line b) {
			if (!query)
				return type * Long.compare(a.m, b.m);
			if (a.left == b.left)
				return Long.compare(a.m, b.m);
			return Double.compare(a.left, b.left);
		}
	};

	public ConvexHullTrick(final int type) {
		this.type = type;
		this.hull = new TreeSet<>(comp);
	}

	public void add(long m, long b) {
		add(new ConvexHullTrick.Line(m, b));
	}

	public void add(ConvexHullTrick.Line a) {
		ConvexHullTrick.Line[] LR = { hull.lower(a), hull.ceiling(a) };
		for (int i = 0; i < 2; i++)
			if (LR[i] != null && LR[i].m == a.m) {
				if (type == 1 && LR[i].b >= a.b)
					return;
				if (type == -1 && LR[i].b <= a.b)
					return;
				remove(LR[i]);
			}

		hull.add(a);
		ConvexHullTrick.Line L = hull.lower(a), R = hull.higher(a);
		if (L != null && R != null && a.inter(R) <= R.left) {
			hull.remove(a);
			return;
		}
		ConvexHullTrick.Line LL = (L != null) ? hull.lower(L) : null;
		ConvexHullTrick.Line RR = (R != null) ? hull.higher(R) : null;
		if (L != null)
			a.left = a.inter(L);
		if (R != null)
			R.left = a.inter(R);
		while (LL != null && L.left >= a.inter(LL)) {
			remove(L);
			a.left = a.inter(L = LL);
			LL = hull.lower(L);
		}
		while (RR != null && R.inter(RR) <= a.inter(RR)) {
			remove(R);
			RR.left = a.inter(R = RR);
			RR = hull.higher(R);
		}
	}

	public long query(long x) {
		ConvexHullTrick.Line temp = new ConvexHullTrick.Line(0, 0, 0);
		temp.left = x;
		query = true;
		long ans = hull.floor(temp).eval(x);
		query = false;
		return ans;
	}

	private void remove(ConvexHullTrick.Line x) {
		hull.remove(x);
	}

	public static class Line {
		long m;
		long b;
		double left = Long.MIN_VALUE;

		public Line(long m, long x, long y) {
			this.m = m;
			this.b = -m * x + y;
		}

		public Line(long m, long b) {
			this.m = m;
			this.b = b;
		}

		public long eval(long x) {
			return m * x + b;
		}

		public double inter(ConvexHullTrick.Line x) {
			return (double) (x.b - this.b) / (double) (this.m - x.m);
		}

		public String toString() {
			return "Line{" + "m=" + m + ", b=" + b + ", left=" + left + '}';
		}

	}

}
