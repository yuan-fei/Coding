/*
 * @lc app=leetcode id=218 lang=java
 *
 * [218] The Skyline Problem
 *
 * https://leetcode.com/problems/the-skyline-problem/description/
 *
 * algorithms
 * Hard (32.17%)
 * Total Accepted:    99.6K
 * Total Submissions: 309.5K
 * Testcase Example:  '[[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]'
 *
 * A city's skyline is the outer contour of the silhouette formed by all the
 * buildings in that city when viewed from a distance. Now suppose you are
 * given the locations and height of all the buildings as shown on a cityscape
 * photo (Figure A), write a program to output the skyline formed by these
 * buildings collectively (Figure B).
 * ⁠   
 * 
 * The geometric information of each building is represented by a triplet of
 * integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and
 * right edge of the ith building, respectively, and Hi is its height. It is
 * guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You
 * may assume all buildings are perfect rectangles grounded on an absolutely
 * flat surface at height 0.
 * 
 * For instance, the dimensions of all buildings in Figure A are recorded as: [
 * [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * 
 * The output is a list of "key points" (red dots in Figure B) in the format of
 * [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key
 * point is the left endpoint of a horizontal line segment. Note that the last
 * key point, where the rightmost building ends, is merely used to mark the
 * termination of the skyline, and always has zero height. Also, the ground in
 * between any two adjacent buildings should be considered part of the skyline
 * contour.
 * 
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3
 * 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * 
 * Notes:
 * 
 * 
 * The number of buildings in any input list is guaranteed to be in the range
 * [0, 10000].
 * The input list is already sorted in ascending order by the left x position
 * Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output
 * skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not
 * acceptable; the three lines of height 5 should be merged into one in the
 * final output as such: [...[2 3], [4 5], [12 7], ...]
 * 
 * 
 */
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
    	int N = buildings.length;
        int[][] events = new int[2*N][2]; 
        int i = 0;
        for (int[] building: buildings) {
        	events[i++]= new int[]{building[0], building[2]};
        	events[i++]= new int[]{building[1], -building[2]};
        }
        Arrays.sort(events, (a, b)->Integer.compare(a[0], b[0]));
        TreeMap<Integer, Integer> tm = new TreeMap<>((a, b)->-Integer.compare(a, b));
        List<List<Integer>> ans = new ArrayList<>();
        if(buildings.length==0){
        	return ans;
        }
        i = 0;
        int curX = events[i][0];
        //int curH = 0;
        tm.put(0, 1);
        while(i <= events.length){
        	if(i==events.length || events[i][0]>curX){
        		int curH = tm.firstKey();
        		//System.out.println(curX+", "+curH);
        		if(ans.isEmpty()||ans.get(ans.size()-1).get(1)!=curH){
					ans.add(Arrays.asList(curX, curH));
        		}
        	}
        	if(i < events.length){
	        	int[] event = events[i];
	        	curX = event[0];
	        	if(event[1]>0){
	        		//curH=Math.max(curH, event[1]);
	        		int cnt = tm.getOrDefault(event[1], 0) + 1;
	        		tm.put(event[1], cnt);
	        	}
	        	else{
	        		//curH=Math.max(curH, event[1]);
	        		int cnt = tm.getOrDefault(-event[1], 0) - 1;
	        		if(cnt == 0){
	        			tm.remove(-event[1]);	
	        		}
	        		else{
	        			tm.put(-event[1], cnt);
	        		}
	        	}	
        	}
        	//System.out.println(tm);
        	i++;
        }
        return ans;
    }
}
