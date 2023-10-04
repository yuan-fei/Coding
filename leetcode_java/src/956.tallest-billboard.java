/*
 * @lc app=leetcode id=956 lang=java
 *
 * [956] Tallest Billboard
 *
 * https://leetcode.com/problems/tallest-billboard/description/
 *
 * algorithms
 * Hard (52.67%)
 * Likes:    2230
 * Dislikes: 55
 * Total Accepted:    55.5K
 * Total Submissions: 105.4K
 * Testcase Example:  '[1,2,3,6]'
 *
 * You are installing a billboard and want it to have the largest height. The
 * billboard will have two steel supports, one on each side. Each steel support
 * must be an equal height.
 * 
 * You are given a collection of rods that can be welded together. For example,
 * if you have rods of lengths 1, 2, and 3, you can weld them together to make
 * a support of length 6.
 * 
 * Return the largest possible height of your billboard installation. If you
 * cannot support the billboard, return 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: rods = [1,2,3,6]
 * Output: 6
 * Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the
 * same sum = 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: rods = [1,2,3,4,5,6]
 * Output: 10
 * Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the
 * same sum = 10.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: rods = [1,2]
 * Output: 0
 * Explanation: The billboard cannot be supported, so we return 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= rods.length <= 20
 * 1 <= rods[i] <= 1000
 * sum(rods[i]) <= 5000
 * 
 * 
 */

// @lc code=start
class Solution {
    // Helper function to collect every combination `(left, right)`
    Map<Integer, Integer> helper(int[] rods, int leftIndex, int rightIndex) {
        Set<Pair<Integer, Integer>> states = new HashSet<>();
        states.add(new Pair(0, 0));
        for (int i = leftIndex; i < rightIndex; ++i) {
            int r = rods[i];
            Set<Pair<Integer, Integer>> newStates = new HashSet<>();
            for (Pair<Integer, Integer> p : states) {
                newStates.add(new Pair(p.getKey() + r, p.getValue()));
                newStates.add(new Pair(p.getKey(), p.getValue() + r));
            }
            states.addAll(newStates);
        }

        Map<Integer, Integer> dp = new HashMap<>();
        for (Pair<Integer, Integer> p : states) {
            int left = p.getKey(), right = p.getValue();
            dp.put(left - right, Math.max(dp.getOrDefault(left - right, 0), left));
        }
        return dp;
    }
    
    public int tallestBillboard(int[] rods) {
        int n = rods.length;
        Map<Integer, Integer> firstHalf = helper(rods, 0, n / 2);
        Map<Integer, Integer> secondHalf = helper(rods, n / 2, n);

        int answer = 0;
        for (int diff : firstHalf.keySet()) {
            if (secondHalf.containsKey(-diff)) {
                answer = Math.max(answer, firstHalf.get(diff) + secondHalf.get(-diff));
            }
        }
        return answer;
    }
}
// @lc code=end
