/*
 * @lc app=leetcode id=135 lang=java
 *
 * [135] Candy
 *
 * https://leetcode.com/problems/candy/description/
 *
 * algorithms
 * Hard (29.25%)
 * Total Accepted:    110.2K
 * Total Submissions: 376.5K
 * Testcase Example:  '[1,0,2]'
 *
 * There are N children standing in a line. Each child is assigned a rating
 * value.
 * 
 * You are giving candies to these children subjected to the following
 * requirements:
 * 
 * 
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * 
 * 
 * What is the minimum candies you must give?
 * 
 * Example 1:
 * 
 * 
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2,
 * 1, 2 candies respectively.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1,
 * 2, 1 candies respectively.
 * ⁠            The third child gets 1 candy because it satisfies the above two
 * conditions.
 * 
 * 
 */
class Solution {
    public int candy(int[] ratings) {
    	int N = ratings.length;
        int[][] ratingsWithIndices = new int[N][];
        for(int i = 0; i < N; i++){
        	ratingsWithIndices[i] = new int[]{ratings[i], i};
        }
        Arrays.sort(ratingsWithIndices, (a,b) -> Integer.compare(a[0], b[0]) != 0 ? Integer.compare(a[0], b[0]) : Integer.compare(b[1], a[1]));
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
        	int j = ratingsWithIndices[i][1];
        	int left = 0;
        	if(j > 0 && ratings[j] != ratings[j - 1]){
        		left = ans[j - 1];
        	}
        	int right = 0;
        	if(j < N - 1 && ratings[j] != ratings[j + 1]){
        		right = ans[j + 1];
        	}
        	ans[j] = Math.max(left, right) + 1;
        }
        int sum = 0;
        for (int n : ans) {
        	sum += n;
        }
        return sum;
    }
}
