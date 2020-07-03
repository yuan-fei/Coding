/*
 * @lc app=leetcode id=1496 lang=java
 *
 * [1496] Path Crossing
 *
 * https://leetcode.com/problems/path-crossing/description/
 *
 * algorithms
 * Easy (55.86%)
 * Likes:    82
 * Dislikes: 3
 * Total Accepted:    10.1K
 * Total Submissions: 18.1K
 * Testcase Example:  '"NES"'
 *
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing
 * moving one unit north, south, east, or west, respectively. You start at the
 * origin (0, 0) on a 2D plane and walk on the path specified by path.
 * 
 * Return True if the path crosses itself at any point, that is, if at any time
 * you are on a location you've previously visited. Return False otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: path = "NES"
 * Output: false 
 * Explanation: Notice that the path doesn't cross any point more than once.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: path = "NESWW"
 * Output: true
 * Explanation: Notice that the path visits the origin twice.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= path.length <= 10^4
 * path will only consist of characters in {'N', 'S', 'E', 'W}
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isPathCrossing(String path) {
        int x = 0;
        int y = 0;
        Set<String> pos = new HashSet<>();
        pos.add(x + "|" + y);
        for (char c : path.toCharArray()) {
        	switch(c){
        		case 'N': x += 1;break;
        		case 'S': x -= 1;break;
        		case 'E': y += 1;break;
        		case 'W': y -= 1;break;
        	}
        	if(!pos.add(x+"|"+y)){
        		return true;
        	}
        }
        return false;
    }
}
// @lc code=end
