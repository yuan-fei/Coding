/*
 * @lc app=leetcode id=1847 lang=java
 *
 * [1847] Closest Room
 *
 * https://leetcode.com/problems/closest-room/description/
 *
 * algorithms
 * Hard (24.81%)
 * Likes:    117
 * Dislikes: 6
 * Total Accepted:    2K
 * Total Submissions: 8K
 * Testcase Example:  '[[2,2],[1,2],[3,2]]\n[[3,1],[3,3],[5,2]]'
 *
 * There is a hotel with n rooms. The rooms are represented by a 2D integer
 * array rooms where rooms[i] = [roomIdi, sizei] denotes that there is a room
 * with room number roomIdi and size equal to sizei. Each roomIdi is guaranteed
 * to be unique.
 * 
 * You are also given k queries in a 2D array queries where queries[j] =
 * [preferredj, minSizej]. The answer to the j^th query is the room number id
 * of a room such that:
 * 
 * 
 * The room has a size of at least minSizej, and
 * abs(id - preferredj) is minimized, where abs(x) is the absolute value of
 * x.
 * 
 * 
 * If there is a tie in the absolute difference, then use the room with the
 * smallest such id. If there is no such room, the answer is -1.
 * 
 * Return an array answer of length k where answer[j] contains the answer to
 * the j^th query.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
 * Output: [3,-1,3]
 * Explanation: The answers to the queries are as follows:
 * Query = [3,1]: Room number 3 is the closest as abs(3 - 3) = 0, and its size
 * of 2 is at least 1. The answer is 3.
 * Query = [3,3]: There are no rooms with a size of at least 3, so the answer
 * is -1.
 * Query = [5,2]: Room number 3 is the closest as abs(3 - 5) = 2, and its size
 * of 2 is at least 2. The answer is 3.
 * 
 * Example 2:
 * 
 * 
 * Input: rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries =
 * [[2,3],[2,4],[2,5]]
 * Output: [2,1,3]
 * Explanation: The answers to the queries are as follows:
 * Query = [2,3]: Room number 2 is the closest as abs(2 - 2) = 0, and its size
 * of 3 is at least 3. The answer is 2.
 * Query = [2,4]: Room numbers 1 and 3 both have sizes of at least 4. The
 * answer is 1 since it is smaller.
 * Query = [2,5]: Room number 3 is the only room with a size of at least 5. The
 * answer is 3.
 * 
 * 
 * Constraints:
 * 
 * 
 * n == rooms.length
 * 1 <= n <= 10^5
 * k == queries.length
 * 1 <= k <= 10^4
 * 1 <= roomIdi, preferredj <= 10^7
 * 1 <= sizei, minSizej <= 10^7
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
    	int n = rooms.length;
    	int m = queries.length;
        Integer[] ids = new Integer[m];
        for(int i = 0; i < m; i++){
        	ids[i] = i;
        }
        Arrays.sort(rooms, (a, b) -> -Integer.compare(a[1], b[1]));
        Arrays.sort(ids, (a, b) -> -Integer.compare(queries[a][1], queries[b][1]));
        TreeSet<Integer> s = new TreeSet<>();
        int[] ans = new int[m];
        int j = 0;
        int MAX = (int)3e7;
        for(int i = 0; i < m; i++){
        	ans[ids[i]] = MAX;
        	while(j < n && rooms[j][1] >= queries[ids[i]][1]){
        		s.add(rooms[j][0]);
        		j++;
        	}
        	Integer ceiling = s.ceiling(queries[ids[i]][0]);
        	Integer floor = s.floor(queries[ids[i]][0]);
        	if(ceiling == null && floor == null){
        		ans[ids[i]] = -1;
        	}
        	else {
        		if(ceiling != null){
	        		if(Math.abs(ans[ids[i]] - queries[ids[i]][0]) > Math.abs(ceiling - queries[ids[i]][0])){
	        			ans[ids[i]] = ceiling;	
	        		}
	        	}
	        	if(floor != null){
	        		if(Math.abs(ans[ids[i]] - queries[ids[i]][0]) >= Math.abs(floor - queries[ids[i]][0])){
	        			ans[ids[i]] = floor;
	        		}
	        	}
        	}
        }
        return ans;
    }
}
// @lc code=end
