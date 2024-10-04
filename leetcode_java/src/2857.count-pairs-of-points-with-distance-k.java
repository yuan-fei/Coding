/*
 * @lc app=leetcode id=2857 lang=java
 *
 * [2857] Count Pairs of Points With Distance k
 *
 * https://leetcode.com/problems/count-pairs-of-points-with-distance-k/description/
 *
 * algorithms
 * Medium (31.73%)
 * Likes:    268
 * Dislikes: 41
 * Total Accepted:    11.1K
 * Total Submissions: 34.5K
 * Testcase Example:  '[[1,2],[4,2],[1,3],[5,2]]\n5'
 *
 * You are given a 2D integer array coordinates and an integer k, where
 * coordinates[i] = [xi, yi] are the coordinates of the i^th point in a 2D
 * plane.
 * 
 * We define the distance between two points (x1, y1) and (x2, y2) as (x1 XOR
 * x2) + (y1 XOR y2) where XOR is the bitwise XOR operation.
 * 
 * Return the number of pairs (i, j) such that i < j and the distance between
 * points i and j is equal to k.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: coordinates = [[1,2],[4,2],[1,3],[5,2]], k = 5
 * Output: 2
 * Explanation: We can choose the following pairs:
 * - (0,1): Because we have (1 XOR 4) + (2 XOR 2) = 5.
 * - (2,3): Because we have (1 XOR 5) + (3 XOR 2) = 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: coordinates = [[1,3],[1,3],[1,3],[1,3],[1,3]], k = 0
 * Output: 10
 * Explanation: Any two chosen pairs will have a distance of 0. There are 10
 * ways to choose two pairs.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= coordinates.length <= 50000
 * 0 <= xi, yi <= 10^6
 * 0 <= k <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    long base = 1000000L;
    public int countPairs(List<List<Integer>> coordinates, int k) {
        Map<Long, Integer> freq = new HashMap<>();
        int ans = 0;
        for(List<Integer> c : coordinates){
            for(int i = 0; i <= k; i++){
                long codePair = encode(c.get(0) ^ i, c.get(1) ^ (k - i));
                ans += freq.getOrDefault(codePair, 0);
                // System.out.println(Arrays.asList(c.get(0), c.get(1), c.get(0) ^ i, c.get(1) ^ (k - i)));
            }
            long code = encode(c.get(0), c.get(1));
            freq.put(code, freq.getOrDefault(code , 0) + 1);
            // System.out.println(freq);
        }
        return ans;
    }

    long encode(int x, int y){
        return base * x + y;
    }
}
// @lc code=end
