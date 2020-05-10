/*
 * @lc app=leetcode id=406 lang=java
 *
 * [406] Queue Reconstruction by Height
 *
 * https://leetcode.com/problems/queue-reconstruction-by-height/description/
 *
 * algorithms
 * Medium (63.25%)
 * Likes:    2322
 * Dislikes: 278
 * Total Accepted:    112.4K
 * Total Submissions: 177.7K
 * Testcase Example:  '[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]'
 *
 * Suppose you have a random list of people standing in a queue. Each person is
 * described by a pair of integers (h, k), where h is the height of the person
 * and k is the number of people in front of this person who have a height
 * greater than or equal to h. Write an algorithm to reconstruct the queue.
 * 
 * Note:
 * The number of people is less than 1,100.
 * 
 * 
 * Example
 * 
 * 
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> Integer.compare(a[0], b[0]));
        int[][] ret = new int[people.length][];
        for(int i = 0; i < people.length; i++){
        	int j = -1;
        	int cnt = 0;
        	while(cnt < people[i][1] + 1){
        		j++;
        		if(ret[j] == null || ret[j][0] >= people[i][0]){
        			cnt++;
        		}
        		
        	}
        	ret[j] = people[i];
        }
        return ret;
    }
}
// @lc code=end
