/*
 * @lc app=leetcode id=1925 lang=java
 *
 * [1925] Count Square Sum Triples
 *
 * https://leetcode.com/problems/count-square-sum-triples/description/
 *
 * algorithms
 * Easy (62.78%)
 * Likes:    57
 * Dislikes: 8
 * Total Accepted:    8.9K
 * Total Submissions: 14.2K
 * Testcase Example:  '5'
 *
 * A square triple (a,b,c) is a triple where a, b, and c are integers and a^2 +
 * b^2 = c^2.
 * 
 * Given an integer n, return the number of square triples such that 1 <= a, b,
 * c <= n.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5
 * Output: 2
 * Explanation: The square triples are (3,4,5) and (4,3,5).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 10
 * Output: 4
 * Explanation: The square triples are (3,4,5), (4,3,5), (6,8,10), and
 * (8,6,10).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 250
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countTriples(int n) {
        Set<Integer> s = IntStream.range(1, n + 1).map(i -> i * i).boxed().collect(Collectors.toSet());
        int ans = 0;
        for(int x : s){
            for(int y : s){
                if(x != y && s.contains(x + y)){
                    ans++;
                }
            }
        }
        return ans;
    }
}
// @lc code=end
