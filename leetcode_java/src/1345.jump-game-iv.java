/*
 * @lc app=leetcode id=1345 lang=java
 *
 * [1345] Jump Game IV
 *
 * https://leetcode.com/problems/jump-game-iv/description/
 *
 * algorithms
 * Hard (29.72%)
 * Likes:    48
 * Dislikes: 4
 * Total Accepted:    1.8K
 * Total Submissions: 6.2K
 * Testcase Example:  '[100,-23,-23,404,100,23,23,23,3,404]'
 *
 * Given an array of integers arr, you are initially positioned at the first
 * index of the array.
 * 
 * In one step you can jump from index i to index:
 * 
 * 
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * 
 * 
 * Return the minimum number of steps to reach the last index of the array.
 * 
 * Notice that you can not jump outside of the array at any time.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that
 * index 9 is the last index of the array.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You don't need to jump.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last
 * index of the array.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: arr = [6,1,9]
 * Output: 2
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 * 
 */

// @lc code=start
class Solution {
    public int minJumps(int[] arr) {
    	Map<Integer, Set<Integer>> m = new HashMap<>();
    	int n = arr.length;
        for(int i = 0; i < n; i++){
        	if(!m.containsKey(arr[i])){
        		m.put(arr[i], new HashSet<>());
        	}
        	m.get(arr[i]).add(i);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        m.get(arr[0]).remove(0);
        int d = 0;
        int[] dir = {-1, 1};
        while(!q.isEmpty()){
        	// System.out.println(q);
        	for(int l = q.size(); l > 0; l--){
	        	int cur = q.poll();
	        	if(cur == n - 1){
	        		return d;
	        	}
	        	if(m.containsKey(arr[cur])){
	        		for(int j: m.get(arr[cur])){
	        			q.offer(j);
	        		}
	        		m.remove(arr[cur]);
	        	}
	        	for(int j: dir){
	        		int nxt = cur + j;
	        		if(nxt >= 0 && nxt < n){
		        		if(m.containsKey(arr[nxt])){
		        			q.offer(nxt);
		        			m.get(arr[nxt]).remove(nxt);
		        		}	
	        		}
	        	}
        	}
        	d++;
        }
        return -1;
    }
}
// @lc code=end
