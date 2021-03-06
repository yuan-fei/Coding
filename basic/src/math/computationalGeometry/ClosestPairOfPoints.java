package math.computationalGeometry;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestPairOfPoints {

	public static void main(String[] args) {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(1, 0);
		Point p5 = new Point(2d / 3, 2d / 3);
		System.out.println(bruteForce(Arrays.asList(p1, p2, p3, p4, p5)));
		System.out.println(divideAndConcur(Arrays.asList(p1, p2, p3, p4, p5)));
		System.out.println(bruteForce(Arrays.asList(new Point(0.3215348546593775, 0.03629583077160248),
				new Point(0.02402358131857918, -0.2356728797179394),
				new Point(0.04590851212470659, -0.4156409924995536), new Point(0.3218384001607433, 0.1379850698988746),
				new Point(0.11506479756447, -0.1059521474930943), new Point(0.2622539999543261, -0.29702873322836),
				new Point(-0.161920957418085, -0.4055339716426413), new Point(0.1905378631228002, 0.3698601009043493),
				new Point(0.2387090918968516, -0.01629827079949742),
				new Point(0.07495888748668034, -0.1659825110491202), new Point(0.3319341836794598, -0.1821814101954749),
				new Point(0.07703635755650362, -0.2499430638271785), new Point(0.2069242999022122, -0.2232970760420869),
				new Point(0.04604079532068295, -0.1923573186549892), new Point(0.05054295812784038, 0.4754929463150845),
				new Point(-0.3900589168910486, 0.2797829520700341), new Point(0.3120693385713448, -0.0506329867529059),
				new Point(0.01138812723698857, 0.4002504701728471), new Point(0.009645149586391732, 0.1060251100976254),
				new Point(-0.03597933197019559, 0.2953639456959105),
				new Point(0.1818290866742182, 0.001454397571696298), new Point(0.444056063372694, 0.2502497166863175),
				new Point(-0.05301752458607545, -0.06553921621808712),
				new Point(0.4823896228171788, -0.4776170002088109),
				new Point(-0.3089226845734964, -0.06356112199235814), new Point(-0.271780741188471, 0.1810810595574612),
				new Point(0.4293626522918815, 0.2980897964891882), new Point(-0.004796652127799228, 0.382663812844701),
				new Point(0.430695573269106, -0.2995073500084759), new Point(0.1799668387323309, -0.2973467472915973),
				new Point(0.4932166845474547, 0.4928094162538735), new Point(-0.3521487911717489, 0.4352656197131292),
				new Point(-0.4907368011686362, 0.1865826865533206), new Point(-0.1047924716070224, -0.247073392148198),
				new Point(0.4374961861758457, -0.001606279519951237),
				new Point(0.003256207800708899, -0.2729194320486108),
				new Point(0.04310378203457577, 0.4452604050238248), new Point(0.4916198379282093, -0.345391701297268),
				new Point(0.001675087028811806, 0.1531837672490476),
				new Point(-0.4404289572876217, -0.2894855991839297))));
		System.out.println(divideAndConcur(Arrays.asList(new Point(0.3215348546593775, 0.03629583077160248),
				new Point(0.02402358131857918, -0.2356728797179394),
				new Point(0.04590851212470659, -0.4156409924995536), new Point(0.3218384001607433, 0.1379850698988746),
				new Point(0.11506479756447, -0.1059521474930943), new Point(0.2622539999543261, -0.29702873322836),
				new Point(-0.161920957418085, -0.4055339716426413), new Point(0.1905378631228002, 0.3698601009043493),
				new Point(0.2387090918968516, -0.01629827079949742),
				new Point(0.07495888748668034, -0.1659825110491202), new Point(0.3319341836794598, -0.1821814101954749),
				new Point(0.07703635755650362, -0.2499430638271785), new Point(0.2069242999022122, -0.2232970760420869),
				new Point(0.04604079532068295, -0.1923573186549892), new Point(0.05054295812784038, 0.4754929463150845),
				new Point(-0.3900589168910486, 0.2797829520700341), new Point(0.3120693385713448, -0.0506329867529059),
				new Point(0.01138812723698857, 0.4002504701728471), new Point(0.009645149586391732, 0.1060251100976254),
				new Point(-0.03597933197019559, 0.2953639456959105),
				new Point(0.1818290866742182, 0.001454397571696298), new Point(0.444056063372694, 0.2502497166863175),
				new Point(-0.05301752458607545, -0.06553921621808712),
				new Point(0.4823896228171788, -0.4776170002088109),
				new Point(-0.3089226845734964, -0.06356112199235814), new Point(-0.271780741188471, 0.1810810595574612),
				new Point(0.4293626522918815, 0.2980897964891882), new Point(-0.004796652127799228, 0.382663812844701),
				new Point(0.430695573269106, -0.2995073500084759), new Point(0.1799668387323309, -0.2973467472915973),
				new Point(0.4932166845474547, 0.4928094162538735), new Point(-0.3521487911717489, 0.4352656197131292),
				new Point(-0.4907368011686362, 0.1865826865533206), new Point(-0.1047924716070224, -0.247073392148198),
				new Point(0.4374961861758457, -0.001606279519951237),
				new Point(0.003256207800708899, -0.2729194320486108),
				new Point(0.04310378203457577, 0.4452604050238248), new Point(0.4916198379282093, -0.345391701297268),
				new Point(0.001675087028811806, 0.1531837672490476),
				new Point(-0.4404289572876217, -0.2894855991839297))));
	}

	public static List<Point> divideAndConcur(List<Point> points) {
		List<Point> pointsByX = sortByX(points);
		List<Point> pointsByY = sortByY(points);
		return findClosestPairOfPointsHelper(pointsByX, pointsByY);
	}

	public static List<Point> findClosestPairOfPointsHelper(List<Point> pointsByX, List<Point> pointsByY) {
		if (pointsByX.size() <= 3) {
			return bruteForce(pointsByX);
		}
		List<Point> lP = pointsByX.subList(0, pointsByX.size() / 2);
		List<Point> rP = pointsByX.subList(pointsByX.size() / 2, pointsByX.size());
		List<Point> pairLeft = findClosestPairOfPointsHelper(lP, pointsByY);
		List<Point> pairRight = findClosestPairOfPointsHelper(rP, pointsByY);
		double d = Point.getVector(pairLeft.get(0), pairLeft.get(1)).norm();
		List<Point> minDPair = pairLeft;
		double dMinRight = Point.getVector(pairRight.get(0), pairRight.get(1)).norm();
		if (d > dMinRight) {
			d = dMinRight;
			minDPair = pairRight;
		}
		double split = (pointsByX.get(pointsByX.size() / 2).x + pointsByX.get(pointsByX.size() / 2 + 1).x) / 2;
		List<Point> checkPoints = findXInRange(split - d, split + d, pointsByY);

		for (int i = 0; i < checkPoints.size(); i++) {
			for (int j = i + 1; j < checkPoints.size(); j++) {
				if (checkPoints.get(j).y - checkPoints.get(i).y < d) {
					double dCheck = Point.getVector(checkPoints.get(j), checkPoints.get(i)).norm();
					if (d > dCheck) {
						d = dCheck;
						minDPair = Arrays.asList(checkPoints.get(i), checkPoints.get(j));
					}
				}
			}
		}
		return minDPair;
	}

	public static List<Point> bruteForce(List<Point> points) {
		double dMin = Double.MAX_VALUE;
		List<Point> pMin = null;
		for (int i = 0; i < points.size(); i++) {
			Point p1 = points.get(i);
			for (int j = i + 1; j < points.size(); j++) {
				Point p2 = points.get(j);
				double d = Point.getVector(p1, p2).norm();
				if (dMin > d) {
					dMin = d;
					pMin = Arrays.asList(p1, p2);
				}
			}
		}
		return pMin;
	}

	private static List<Point> findXInRange(double left, double right, List<Point> pointsByY) {
		return pointsByY.stream().filter(p -> p.x >= left && p.x <= right).collect(Collectors.toList());
	}

	private static List<Point> sortByX(List<Point> points) {
		return points.stream().sorted(new Comparator<Point>() {

			@Override
			public int compare(Point arg0, Point arg1) {
				return Double.compare(arg0.x, arg1.x);
			}
		}).collect(Collectors.toList());
	}

	private static List<Point> sortByY(List<Point> points) {
		return points.stream().sorted(new Comparator<Point>() {

			@Override
			public int compare(Point arg0, Point arg1) {
				return Double.compare(arg0.y, arg1.y);
			}
		}).collect(Collectors.toList());
	}

}
