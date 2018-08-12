package math;

public class GCD {
	public static void main(String[] args) {
		System.out.println(euclid(1, 3));
		System.out.println(euclid(3, 2));
		System.out.println(euclid(2, 4));
		System.out.println(euclid(4, 0));

		System.out.println(euclidExtended(1, 3));
		System.out.println(euclidExtended(3, 2));
		System.out.println(euclidExtended(2, 4));
		System.out.println(euclidExtended(4, 0));
	}

	public static int euclid(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return euclid(b, a % b);
		}
	}

	public static GCDResult euclidExtended(int a, int b) {
		if (b == 0) {
			return new GCDResult(1, 0, a);
		} else {
			GCDResult r = euclidExtended(b, a % b);
			return new GCDResult(r.y, r.x - (a / b) * r.y, r.d);
		}
	}
}

class GCDResult {
	int x;
	int y;
	int d;

	public GCDResult(int x, int y, int d) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
	}

	@Override
	public String toString() {
		return "x: " + x + ", y: " + y + ", d: " + d;
	}
}