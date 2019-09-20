/*
 * @lc app=leetcode id=149 lang=java
 *
 * [149] Max Points on a Line
 *
 * https://leetcode.com/problems/max-points-on-a-line/description/
 *
 * algorithms
 * Hard (16.09%)
 * Total Accepted:    129.5K
 * Total Submissions: 804.5K
 * Testcase Example:  '[[1,1],[2,2],[3,3]]'
 *
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 * 
 * Example 1:
 * 
 * 
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o  
 * +------------->
 * 0  1  2  3  4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * 
 * 
 * NOTE: input types have been changed on April 15, 2019. Please reset to
 * default code definition to get new method signature.
 * 
 */
class Solution {
    public int maxPoints(int[][] pointsArray) {
    	Map<Point, Integer> cntMap = new HashMap<Point, Integer>();
    	for(int[] pa : pointsArray){
    		Point p = new Point(pa[0], pa[1]);
    		cntMap.put(p, cntMap.getOrDefault(p, 0) + 1);
    	}
    	Point[] points = cntMap.keySet().toArray(new Point[0]);
    	int N = points.length;
    	if(N == 0){
    		return 0;
    	}
    	if(N == 1){
    		return cntMap.get(points[0]);
    	}
        Map<Line, Set<Point>> map = new HashMap<>();
        
        for(int i = 0; i < N; i++){
        	for(int j = i + 1; j < N; j++){
        		Line l = new Line(points[i], points[j]);
        		Set<Point> set = map.getOrDefault(l, new HashSet<Point>());
        		set.add(points[i]);
        		set.add(points[j]);
        		map.put(l, set);
        	}
        }
        int max = 0;
        for (Set<Point> ps : map.values()) {
        	int sum = 0;
        	for (Point p : ps) {
        		sum += cntMap.get(p);
        	}
        	max = Math.max(max, sum);
        }
        // System.out.println(map);
        return max;
    }

    class Point{
    	int x;
    	int y;
    	Point(int xx, int yy){
    		x = xx;
    		y = yy;
    	}

    	@Override
    	public boolean equals(Object o){
    		Point that = (Point)o;
    		return x == that.x && y == that.y;
    	}

    	@Override
		public int hashCode() {
			return x * 37 + y;
		}

		@Override
		public String toString(){
			return "[" + x + ", " + y + "]";
		}
    }

    class Line {
    	Point p1;
    	Point p2;
    	Line(Point pp1, Point pp2){
    		p1 = pp1;
    		p2 = pp2;
    	}
    	
    	@Override
    	public boolean equals(Object o){
    		Line that = (Line)o;
    		// should work for type y = c, and x = c
    		if((p1.y - p2.y) * (that.p1.x - that.p2.x) != (p1.x - p2.x) * (that.p1.y - that.p2.y)){
    			return false;
    		}
    		if((p1.y - p2.y) * (that.p1.x - p2.x) != (p1.x - p2.x) * (that.p1.y - p2.y)){
    			return false;
    		}
    		return true;
    	}

    	@Override
		public int hashCode() {
			if(p1.x != p2.x){
				return (p1.y - p2.y)/(p1.x - p2.x);
			}else{
				return 0;
			}
		}

		@Override
		public String toString(){
			return "" + p1 + "-" + p2;
		}
    }
}
