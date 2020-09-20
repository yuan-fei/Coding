/*
 * @lc app=leetcode id=1585 lang=java
 *
 * [1585] Check If String Is Transformable With Substring Sort Operations
 *
 * https://leetcode.com/problems/check-if-string-is-transformable-with-substring-sort-operations/description/
 *
 * algorithms
 * Hard (30.89%)
 * Likes:    24
 * Dislikes: 2
 * Total Accepted:    782
 * Total Submissions: 2.6K
 * Testcase Example:  '"84532"\n"34852"'
 *
 * Given two strings s and t, you want to transform string s into string t
 * using the following operation any number of times:
 * 
 * 
 * Choose a non-empty substring in s and sort it in-place so the characters are
 * in ascending order.
 * 
 * 
 * For example, applying the operation on the underlined substring in "14234"
 * results in "12344".
 * 
 * Return true if it is possible to transform string s into string t.
 * Otherwise, return false.
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "84532", t = "34852"
 * Output: true
 * Explanation: You can transform s into t using the following sort operations:
 * "84532" (from index 2 to 3) -> "84352"
 * "84352" (from index 0 to 2) -> "34852"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "34521", t = "23415"
 * Output: true
 * Explanation: You can transform s into t using the following sort operations:
 * "34521" -> "23451"
 * "23451" -> "23415"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "12345", t = "12435"
 * Output: false
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "1", t = "2"
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * s.length == t.length
 * 1 <= s.length <= 10^5
 * s and t only contain digits from '0' to '9'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isTransformable(String s, String t) {
    	int n = s.length();
    	Queue<Integer>[] pos = new Queue[10];
    	for(int i = 0; i < 10; i++){
    		pos[i] = new LinkedList<>();
    	}
    	for(int i = 0; i < n; i++){
    		pos[s.charAt(i) - '0'].offer(i);
    	}
    	int i = 0;
    	int j = 0;
    	while(j < n){
    		// System.out.println(i + ", " + j + " ="+ Arrays.toString(pos));
    		if(pos[s.charAt(i) - '0'].isEmpty() || pos[s.charAt(i) - '0'].peek() > i){
    			//used before
    			i++;
    			continue;
    		}
    		if(pos[t.charAt(j) - '0'].isEmpty()){
				return false;
			}
    		if(t.charAt(j) != s.charAt(i)){
    			for(int d = 0; d < t.charAt(j) - '0'; d++){
    				if(!pos[d].isEmpty() && pos[d].peek() < pos[t.charAt(j) - '0'].peek()){
    					return false;
    				}
    			}
    			pos[t.charAt(j) - '0'].poll();
    			j++;
    		}
    		else{
    			if(pos[s.charAt(i) - '0'].isEmpty()){
    				return false;
    			}
    			pos[s.charAt(i) - '0'].poll();
    			i++;
    			j++;
    		}
    	}
    	return true;
    }
}
// @lc code=end
