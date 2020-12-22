/*
 * @lc app=leetcode id=1691 lang=java
 *
 * [1691] Maximum Height by Stacking Cuboids 
 *
 * https://leetcode.com/problems/maximum-height-by-stacking-cuboids/description/
 *
 * algorithms
 * Hard (48.88%)
 * Likes:    114
 * Dislikes: 7
 * Total Accepted:    3.1K
 * Total Submissions: 6.3K
 * Testcase Example:  '[[50,45,20],[95,37,53],[45,23,12]]'
 *
 * Given n cuboids where the dimensions of the i^th cuboid is cuboids[i] =
 * [widthi, lengthi, heighti] (0-indexed). Choose a subset of cuboids and place
 * them on each other.
 * 
 * You can place cuboid i on cuboid j if widthi <= widthj and lengthi <=
 * lengthj and heighti <= heightj. You can rearrange any cuboid's dimensions by
 * rotating it to put it on another cuboid.
 * 
 * Return the maximum height of the stacked cuboids.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: cuboids = [[50,45,20],[95,37,53],[45,23,12]]
 * Output: 190
 * Explanation:
 * Cuboid 1 is placed on the bottom with the 53x37 side facing down with height
 * 95.
 * Cuboid 0 is placed next with the 45x20 side facing down with height 50.
 * Cuboid 2 is placed next with the 23x12 side facing down with height 45.
 * The total height is 95 + 50 + 45 = 190.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: cuboids = [[38,25,45],[76,35,3]]
 * Output: 76
 * Explanation:
 * You can't place any of the cuboids on the other.
 * We choose cuboid 1 and rotate it so that the 35x3 side is facing down and
 * its height is 76.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: cuboids =
 * [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
 * Output: 102
 * Explanation:
 * After rearranging the cuboids, you can see that all cuboids have the same
 * dimension.
 * You can place the 11x7 side down on all cuboids so their heights are 17.
 * The maximum height of stacked cuboids is 6 * 17 = 102.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == cuboids.length
 * 1 <= n <= 100
 * 1 <= widthi, lengthi, heighti <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
	List<int[]>[] adj;
	int[] max;
    public int maxHeight(int[][] cuboids) {
    	int n = cuboids.length;
        for(int i = 0; i < n; i++){
        	Arrays.sort(cuboids[i]);
        }
        Map<List<Integer>, Integer> m = new HashMap<>();
        for (int[] cuboid : cuboids) {
        	List<Integer> l = new ArrayList<>();
        	for(int c : cuboid){
        		l.add(c);
        	}
        	m.put(l, m.getOrDefault(l, 0) + 1);
        }
        //sentinel
        m.put(Arrays.asList(200, 200, 200), 1);
        List<List<Integer>> uniqCuboids = new ArrayList<>(m.keySet());

        adj = new List[uniqCuboids.size()];
        for(int i = 0; i < adj.length; i++){
        	adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < uniqCuboids.size(); i++){
        	// System.out.println(uniqCuboids.get(i));
        	for(int j = 0; j < uniqCuboids.size(); j++){
        		if(i != j){
        			boolean flag = true;
        			for(int k = 0; k < 3; k++){
        				if(uniqCuboids.get(i).get(k) > uniqCuboids.get(j).get(k)){
        					flag = false;
        					break;
        				}
        			}
        			if(flag){
        				adj[i].add(new int[]{j, uniqCuboids.get(i).get(2) * m.get(uniqCuboids.get(i))});
        			}
        		}
        	}
        }

        max = new int[uniqCuboids.size()];
        // Arrays.fill(max, -1);
        int ans = 0;
        for(int i = 0; i < uniqCuboids.size(); i++){
        	if(max[i] == 0){
        		dfs(i);	
        	}
        	ans = Math.max(ans, max[i]);
        }
        return ans;
    }

    void dfs(int u){
    	for(int[] e : adj[u]){
    		int v = e[0];
    		int h = e[1];
    		if(max[v] == 0){
    			dfs(v);
    		}
    		max[u] = Math.max(max[u], h + max[v]);
    	}
    }
}
// @lc code=end
