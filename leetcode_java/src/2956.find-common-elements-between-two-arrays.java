/*
 * @lc app=leetcode id=2956 lang=java
 *
 * [2956] Find Common Elements Between Two Arrays
 *
 * https://leetcode.com/problems/find-common-elements-between-two-arrays/description/
 *
 * algorithms
 * Easy (81.99%)
 * Likes:    232
 * Dislikes: 94
 * Total Accepted:    69.6K
 * Total Submissions: 83.2K
 * Testcase Example:  '[2,3,2]\n[1,2]'
 *
 * You are given two integer arrays nums1 and nums2 of sizes n and m,
 * respectively. Calculate the following values:
 * 
 * 
 * answer1 : the number of indices i such that nums1[i] exists in nums2.
 * answer2 : the number of indices i such that nums2[i] exists in nums1.
 * 
 * 
 * Return [answer1,answer2].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [2,3,2], nums2 = [1,2]
 * 
 * Output: [2,1]
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [4,3,2,3,1], nums2 = [2,2,5,2,3,6]
 * 
 * Output: [3,4]
 * 
 * Explanation:
 * 
 * The elements at indices 1, 2, and 3 in nums1 exist in nums2 as well. So
 * answer1 is 3.
 * 
 * The elements at indices 0, 1, 3, and 4 in nums2 exist in nums1. So answer2
 * is 4.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums1 = [3,4,2,3], nums2 = [1,5]
 * 
 * Output: [0,0]
 * 
 * Explanation:
 * 
 * No numbers are common between nums1 and nums2, so answer is [0,0].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums1.length
 * m == nums2.length
 * 1 <= n, m <= 100
 * 1 <= nums1[i], nums2[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[][] nums = {nums1, nums2};
        Set<Integer>[] numSets = new Set[]{
            Arrays.stream(nums2).boxed().collect(Collectors.toSet()),
            Arrays.stream(nums1).boxed().collect(Collectors.toSet())
        };
        return IntStream.range(0, 2).map(i -> countExistingNumber(nums[i], numSets[i])).toArray();
    }

    int countExistingNumber(int[] nums, Set<Integer> numSet){
        return (int)Arrays.stream(nums).filter(numSet::contains).count();
    }


}
// @lc code=end
