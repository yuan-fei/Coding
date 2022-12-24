/*
 * @lc app=leetcode id=756 lang=java
 *
 * [756] Pyramid Transition Matrix
 *
 * https://leetcode.com/problems/pyramid-transition-matrix/description/
 *
 * algorithms
 * Medium (52.97%)
 * Likes:    482
 * Dislikes: 452
 * Total Accepted:    29.5K
 * Total Submissions: 55.6K
 * Testcase Example:  '"BCD"\n["BCC","CDE","CEA","FFF"]'
 *
 * You are stacking blocks to form a pyramid. Each block has a color, which is
 * represented by a single letter. Each row of blocks contains one less block
 * than the row beneath it and is centered on top.
 * 
 * To make the pyramid aesthetically pleasing, there are only specific
 * triangular patterns that are allowed. A triangular pattern consists of a
 * single block stacked on top of two blocks. The patterns are given as a list
 * of three-letter strings allowed, where the first two characters of a pattern
 * represent the left and right bottom blocks respectively, and the third
 * character is the top block.
 * 
 * 
 * For example, "ABC" represents a triangular pattern with a 'C' block stacked
 * on top of an 'A' (left) and 'B' (right) block. Note that this is different
 * from "BAC" where 'B' is on the left bottom and 'A' is on the right bottom.
 * 
 * 
 * You start with a bottom row of blocks bottom, given as a single string, that
 * you must use as the base of the pyramid.
 * 
 * Given bottom and allowed, return true if you can build the pyramid all the
 * way to the top such that every triangular pattern in the pyramid is in
 * allowed, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: bottom = "BCD", allowed = ["BCC","CDE","CEA","FFF"]
 * Output: true
 * Explanation: The allowed triangular patterns are shown on the right.
 * Starting from the bottom (level 3), we can build "CE" on level 2 and then
 * build "A" on level 1.
 * There are three triangular patterns in the pyramid, which are "BCC", "CDE",
 * and "CEA". All are allowed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: bottom = "AAAA", allowed = ["AAB","AAC","BCD","BBE","DEF"]
 * Output: false
 * Explanation: The allowed triangular patterns are shown on the right.
 * Starting from the bottom (level 4), there are multiple ways to build level
 * 3, but trying all the possibilites, you will get always stuck before
 * building level 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= bottom.length <= 6
 * 0 <= allowed.length <= 216
 * allowed[i].length == 3
 * The letters in all input strings are from the set {'A', 'B', 'C', 'D', 'E',
 * 'F'}.
 * All the values of allowed are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    Map<String, String> m = new HashMap<>();
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for(String s : allowed){
            m.putIfAbsent(s.substring(0, 2), "");
            m.put(s.substring(0, 2), m.get(s.substring(0, 2)) + s.substring(2));
        }
        return rec(bottom, 1, "");
    }

    boolean rec(String bottom, int pos, String cur){
        if(bottom.length() == 1){
            return true;
        }
        if(pos == bottom.length()){
            return rec(cur, 1, "");
        }
        String prefix = bottom.substring(pos - 1, pos + 1);
        for(char c : m.getOrDefault(prefix, "").toCharArray()){
            if(rec(bottom, pos + 1, cur + c)){
                return true;
            }
        }
        return false;
    }
}
// @lc code=end
