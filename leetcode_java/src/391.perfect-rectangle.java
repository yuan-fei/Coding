/*
 * @lc app=leetcode id=391 lang=java
 *
 * [391] Perfect Rectangle
 *
 * https://leetcode.com/problems/perfect-rectangle/description/
 *
 * algorithms
 * Hard (29.91%)
 * Likes:    327
 * Dislikes: 70
 * Total Accepted:    23.4K
 * Total Submissions: 78.3K
 * Testcase Example:  '[[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]'
 *
 * Given N axis-aligned rectangles where N > 0, determine if they all together
 * form an exact cover of a rectangular region.
 * 
 * Each rectangle is represented as a bottom-left point and a top-right point.
 * For example, a unit square is represented as [1,1,2,2]. (coordinate of
 * bottom-left point is (1, 1) and top-right point is (2, 2)).
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * rectangles = [
 * ⁠ [1,1,3,3],
 * ⁠ [3,1,4,2],
 * ⁠ [3,2,4,4],
 * ⁠ [1,3,2,4],
 * ⁠ [2,3,3,4]
 * ]
 * 
 * Return true. All 5 rectangles together form an exact cover of a rectangular
 * region.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * rectangles = [
 * ⁠ [1,1,2,3],
 * ⁠ [1,3,2,4],
 * ⁠ [3,1,4,2],
 * ⁠ [3,2,4,4]
 * ]
 * 
 * Return false. Because there is a gap between the two rectangular
 * regions.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * rectangles = [
 * ⁠ [1,1,3,3],
 * ⁠ [3,1,4,2],
 * ⁠ [1,3,2,4],
 * ⁠ [3,2,4,4]
 * ]
 * 
 * Return false. Because there is a gap in the top center.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 4:
 * 
 * 
 * rectangles = [
 * ⁠ [1,1,3,3],
 * ⁠ [3,1,4,2],
 * ⁠ [1,3,2,4],
 * ⁠ [2,2,4,4]
 * ]
 * 
 * Return false. Because two of the rectangles overlap with each other.
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
    	int n = rectangles.length;
    	int[][] points = new int[2 * n][2];
    	for(int i = 0; i < n; i++){
    		points[i] = new int[]{rectangles[i][0], i, 0};
    		points[n + i] = new int[]{rectangles[i][2], i, -1};
    	}
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]) != 0 ? Integer.compare(a[0], b[0]) : Integer.compare(a[2], b[2]));

        TreeSet<Integer> h = new TreeSet<Integer>((a, b) -> Integer.compare(rectangles[a][1], rectangles[b][1]));
        int low = Integer.MAX_VALUE;
        int high = 0;
        // System.out.println(Arrays.deepToString(points));
        for ( int i = 0; i < points.length; i++) {
        	if(i > 0 && points[i][0] != points[i - 1][0]){
        		int curLow = Integer.MAX_VALUE;
        		int curHigh = Integer.MIN_VALUE;
        		int lastHigh = Integer.MIN_VALUE;
        		for (int j : h) {
        			curLow = Math.min(rectangles[j][1], curLow);
        			curHigh = Math.max(rectangles[j][3], curHigh);
        			if(lastHigh == Integer.MIN_VALUE || lastHigh == rectangles[j][1]){
        				// System.out.println(lastHigh + "=" + rectangles[j][1]);
        				lastHigh = rectangles[j][3];
        			}
        			else{
        				// System.out.println(lastHigh + "!=" + rectangles[j][1]);
        				
        				return false;
        			}
        		}
        		if(low == Integer.MAX_VALUE){
        			low = curLow;
        			high = curHigh;
        		}
        		if(curLow != low || curHigh != high){
        			return false;
        		}
        	}
        	if(points[i][2] == -1){
    			h.remove(points[i][1]);
    		}
    		else{
        		if(h.contains(points[i][1])){
        			// System.out.println(points[i][1]);
        			return false;
        		}	
        		else{
        			h.add(points[i][1]);
        		}
    		}
        	// System.out.println(h);
        }
        return h.isEmpty();
    }
}
// @lc code=end
