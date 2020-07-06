package puzzle;

/**
 * You are given two jugs with capacities x and y litres. There is an infinite
 * amount of water supply available. You need to determine
 * 
 * 1. whether it is possible to measure exactly z litres using these two jugs.
 * 
 * 2. how to measure the water
 */

public class WaterAndJug {

	public static void main(String[] args) {
		System.out.println(canMeasureWater(9, 4, 6));
		measure(9, 4, 6);
	}

	public static boolean canMeasureWater(int x, int y, int z) {
		if (x == 0) {
			return z == 0 || y == z;
		}
		if (y == 0) {
			return canMeasureWater(y, x, z);
		}
		int gcd = gcd(x, y);
		if (z % gcd == 0 && z <= x + y) {
			return true;
		}
		return false;
	}

	/** https://www.youtube.com/watch?v=0Oef3MHYEC0&t=8s */
	public static void measure(int x, int y, int z) {
		int xx = x;
		int yy = 0;
		while (xx != z && yy != z) {
			output(xx, yy);
			if (xx == 0) {
				// fill x
				xx = x;
			} else if (yy == y) {
				yy = 0;
			} else {
				int delta = Math.min(xx, y - yy);
				yy += delta;
				xx -= delta;
			}
		}
		output(xx, yy);
	}

	static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}

	static void output(int x, int y) {
		System.out.println("(" + x + ", " + y + ")");
	}
}
