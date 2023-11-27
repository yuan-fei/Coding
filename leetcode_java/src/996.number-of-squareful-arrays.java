/*
 * @lc app=leetcode id=996 lang=java
 *
 * [996] Number of Squareful Arrays
 *
 * https://leetcode.com/problems/number-of-squareful-arrays/description/
 *
 * algorithms
 * Hard (49.09%)
 * Likes:    948
 * Dislikes: 40
 * Total Accepted:    33.5K
 * Total Submissions: 68.2K
 * Testcase Example:  '[1,17,8]'
 *
 * An array is squareful if the sum of every pair of adjacent elements is a
 * perfect square.
 * 
 * Given an integer array nums, return the number of permutations of nums that
 * are squareful.
 * 
 * Two permutations perm1 and perm2 are different if there is some index i such
 * that perm1[i] != perm2[i].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,17,8]
 * Output: 2
 * Explanation: [1,8,17] and [17,8,1] are the valid permutations.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,2,2]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 12
 * 0 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    Map<Integer, Integer> cntMap = new HashMap<>();
    Map<Integer, Set<Integer>> squareMap = new HashMap<>();
    int cnt = 0;
    public int numSquarefulPerms(int[] A) {
        for (int num : A) {
            if (!cntMap.containsKey(num)) {
                cntMap.put(num, 1);
                squareMap.put(num, new HashSet<>());
            } else {
                cntMap.put(num, cntMap.get(num) + 1);
            }
        }
        for (int num1 : cntMap.keySet()) {
            for (int num2 : cntMap.keySet()) {
                double c = Math.sqrt(num1 + num2);
                if (c == Math.floor(c)) {
                    squareMap.get(num1).add(num2);
                    squareMap.get(num2).add(num1);
                }
            }
        }
        for (int num: cntMap.keySet()) {
            countPerm(num, A.length - 1);
        }
        return cnt;
    }
    
    private void countPerm(int num, int left) {
        cntMap.put(num, cntMap.get(num) - 1);
        if (left == 0) { cnt++; }
        else {
            for (int next : squareMap.get(num)) {
                if (cntMap.get(next) != 0) {
                    countPerm(next, left - 1);
                }
            }
        }
        cntMap.put(num, cntMap.get(num) + 1);
    }
}
// @lc code=end
