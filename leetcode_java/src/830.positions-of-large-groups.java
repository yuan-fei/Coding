/*
 * @lc app=leetcode id=830 lang=java
 *
 * [830] Positions of Large Groups
 *
 * https://leetcode.com/problems/positions-of-large-groups/description/
 *
 * algorithms
 * Easy (51.81%)
 * Likes:    737
 * Dislikes: 115
 * Total Accepted:    80.2K
 * Total Submissions: 154.9K
 * Testcase Example:  '"abbxxxxzzy"'
 *
 * In a string s of lowercase letters, these letters form consecutive groups of
 * the same character.
 * 
 * For example, a string like s = "abbxxxxzyy" has the groups "a", "bb",
 * "xxxx", "z", and "yy".
 * 
 * A group is identified by an interval [start, end], where start and end
 * denote the start and end indices (inclusive) of the group. In the above
 * example, "xxxx" has the interval [3,6].
 * 
 * A group is considered large if it has 3 or more characters.
 * 
 * Return the intervals of every large group sorted in increasing order by
 * start index.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abbxxxxzzy"
 * Output: [[3,6]]
 * Explanation: "xxxx" is the only large group with start index 3 and end index
 * 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abc"
 * Output: []
 * Explanation: We have groups "a", "b", and "c", none of which are large
 * groups.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "abcdddeeeeaabbbcd"
 * Output: [[3,5],[6,9],[12,14]]
 * Explanation: The large groups are "ddd", "eeee", and "bbb".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s contains lowercase English letters only.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        int left = 0;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) != s.charAt(left)){
                if(i - left >= 3){
                    ans.add(Arrays.asList(left, i - 1));
                }
                left = i;
            }
        }
        if(s.length() - left >= 3){
            ans.add(Arrays.asList(left, s.length() - 1));
        }
        return ans;
    }
}
// @lc code=end
