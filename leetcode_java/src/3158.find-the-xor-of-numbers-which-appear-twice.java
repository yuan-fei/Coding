/*
 * @lc app=leetcode id=3158 lang=java
 *
 * [3158] Find the XOR of Numbers Which Appear Twice
 *
 * https://leetcode.com/problems/find-the-xor-of-numbers-which-appear-twice/description/
 *
 * algorithms
 * Easy (81.08%)
 * Likes:    136
 * Dislikes: 13
 * Total Accepted:    60.5K
 * Total Submissions: 78K
 * Testcase Example:  '[1,2,1,3]'
 *
 * You are given an array nums, where each number in the array appears either
 * once or twice.
 * 
 * Return the bitwise XOR of all the numbers that appear twice in the array, or
 * 0 if no number appears twice.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,1,3]
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The only number that appears twice in nums is 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * No number appears twice in nums.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,2,1]
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * Numbers 1 and 2 appeared twice. 1 XOR 2 == 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 * Each number in nums appears either once or twice.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public int duplicateNumbersXOR(int[] nums) {
        Map<Integer, Long> freq = Arrays.stream(nums).mapToObj(x -> x)
                .collect(Collectors.toMap(Function.identity(), _ -> 1L, (a, b) -> a + b));
        return freq.entrySet().stream().filter(e -> e.getValue() == 2).map(e -> e.getKey())
                .collect(Collectors.<Integer>reducing(0, (a, b) -> a ^ b));

    }
}
// @lc code=end
