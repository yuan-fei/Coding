import java.util.HashMap;

/**
 * There is a road with a length L and a width W. There are some circular
 * obstacles in the road. The radius is 1, there is a circular car with a radius
 * of 2. Ask if the car can pass this road. You can think of the road as a
 * rectangle on two-dimensional coordinates. The four points are (0,0), (0,W),
 * (L,0), (L,W). Now you need to start from x=0 To x=L, contact with obstacles
 * is not allowed, and all parts of the car are betweeny=0 and y=W, contact is
 * not allowed.
 * 
 * Example
 * 
 * Given L=8,W=8, the obstacle coordinates are [[1,1],[6,6]]. Return yes.
 * 
 * The center of the car can go from (0,5) to (2,5) to (5,2) to (8,2), so return
 * yes.
 * 
 * Give L=8, W=6, the obstacle coordinate is [[1,1]], and return no.
 * 
 * Regardless of how you drive, the car will always be tangent to or intersect
 * with obstacles, which is not allowed.
 */
public class DrivingProblem {
	/**
	 * @param L:
	 *            the length
	 * @param W:
	 *            the width
	 * @param p:
	 *            the obstacle coordinates
	 * @return: yes or no
	 */
	public String drivingProblem(int L, int W, double[][] p) {
		// ceiling
		makeSet(-1);
		// floor
		makeSet(-2);
		for (int i = 0; i < p.length; i++) {
			makeSet(i);
			if (blockCeiling(p[i], W)) {
				union(i, -1);
			}
			if (blockFloor(p[i])) {
				union(i, -2);
			}
		}

		for (int i = 0; i < p.length; i++) {
			for (int j = i + 1; j < p.length; j++) {
				if (block(p[i], p[j])) {
					union(i, j);
				}
			}
		}

		if (find(-1) == find(-2)) {
			return "no";
		} else {
			return "yes";
		}
	}

	HashMap<Integer, Integer> parent = new HashMap<Integer, Integer>();

	boolean blockCeiling(double[] i, int w) {
		return w - i[1] <= 5;
	}

	boolean blockFloor(double[] i) {
		return i[1] <= 5;
	}

	boolean block(double[] i, double[] j) {
		return Math.pow(i[0] - j[0], 2) + Math.pow(i[1] - j[1], 2) <= 36;
	}

	void makeSet(int i) {
		parent.put(i, i);
	}

	void union(int i, int j) {
		int pi = find(i);
		int pj = find(j);
		if (pi != pj) {
			parent.put(pi, pj);
		}
	}

	int find(int i) {
		if (parent.get(i) == i) {
			return i;
		} else {
			int j = find(parent.get(i));
			parent.put(i, j);
			return j;
		}

	}
}