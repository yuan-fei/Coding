import java.util.Arrays;

/**
 * https://www.lintcode.com/problem/pour-water
 * 
 * Given an elevation map, heights[i] representing the height of the terrain at
 * that index. The width at each index is 1. After V units of water fall at
 * index K, how much water is at each index?
 * 
 * Water first drops at index K, and then it flows according to the following
 * rules:
 * 
 * First, the droplet can not move to higher level.
 * 
 * If the droplet would eventually fall by moving left, then move left.
 * 
 * Otherwise, if the droplet would eventually fall by moving right, then move
 * right.
 * 
 * Otherwise, rise at it's current position.
 * 
 * Here, "eventually fall" means that the droplet will eventually be at a lower
 * level if it moves in that direction. Also, "level" means the height of the
 * terrain plus any water in that column.
 * 
 * You can assume there's infinitely high terrain on the two sides out of bounds
 * of the array. Also, there could not be partial water being spread out evenly
 * on more than 1 grid block - each unit of water has to be in exactly one
 * block.
 **/

public class PourWater {

	public static void main(String[] args) {
		int[] heights = new int[] { 2, 1, 1, 2, 1, 2, 2 };
		int V = 4;
		int K = 3;
		System.out.println(Arrays.toString(heights));
		heights = pourWater(heights, V, K); // [2,2,2,3,2,2,2]
		System.out.println(Arrays.toString(heights));

	}

	public static int[] pourWater(int[] heights, int V, int K) {
		int n = heights.length;
		for (int v = 0; v < V; v++) {
			int index = K;
			for (int i = K - 1; i >= 0; i--) {
				if (heights[index] > heights[i]) {
					index = i;
				} else if (heights[index] < heights[i]) {
					break;
				}
			}
			if (index != K) {
				heights[index]++;
				continue;
			}
			for (int i = K + 1; i < n; i++) {
				if (heights[index] > heights[i]) {
					index = i;
				} else if (heights[index] < heights[i]) {
					break;
				}
			}
			heights[index]++;
			// System.out.println(Arrays.toString(heights));
		}
		return heights;
	}

}
