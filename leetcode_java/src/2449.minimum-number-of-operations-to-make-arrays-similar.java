/*
 * @lc app=leetcode id=2449 lang=java
 *
 * [2449] Minimum Number of Operations to Make Arrays Similar
 *
 * https://leetcode.com/problems/minimum-number-of-operations-to-make-arrays-similar/description/
 *
 * algorithms
 * Hard (58.14%)
 * Likes:    51
 * Dislikes: 6
 * Total Accepted:    2.6K
 * Total Submissions: 4.5K
 * Testcase Example:  '[8,12,6]\n[2,14,10]'
 *
 * You are given two positive integer arrays nums and target, of the same
 * length.
 * 
 * In one operation, you can choose any two distinct indices i and j where 0 <=
 * i, j < nums.length and:
 * 
 * 
 * set nums[i] = nums[i] + 2 and
 * set nums[j] = nums[j] - 2.
 * 
 * 
 * Two arrays are considered to be similar if the frequency of each element is
 * the same.
 * 
 * Return the minimum number of operations required to make nums similar to
 * target. The test cases are generated such that nums can always be similar to
 * target.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [8,12,6], target = [2,14,10]
 * Output: 2
 * Explanation: It is possible to make nums similar to target in two
 * operations:
 * - Choose i = 0 and j = 2, nums = [10,12,4].
 * - Choose i = 1 and j = 2, nums = [10,14,2].
 * It can be shown that 2 is the minimum number of operations needed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,5], target = [4,1,3]
 * Output: 1
 * Explanation: We can make nums similar to target in one operation:
 * - Choose i = 1 and j = 2, nums = [1,4,3].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,1,1,1,1], target = [1,1,1,1,1]
 * Output: 0
 * Explanation: The array nums is already similiar to target.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length == target.length
 * 1 <= n <= 10^5
 * 1 <= nums[i], target[i] <= 10^6
 * It is possible to make nums similar to target.
 * 
 * 
 */

// @lc code=start
class Solution {
    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);
        List<Integer> numsOdd = new ArrayList<>();
        List<Integer> numsEven = new ArrayList<>();
        List<Integer> targetOdd = new ArrayList<>();
        List<Integer> targetEven = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] % 2 == 0){
                numsEven.add(nums[i]);
            }
            else{
                numsOdd.add(nums[i]);   
            }
            if(target[i] % 2 == 0){
                targetEven.add(target[i]);
            }
            else{
                targetOdd.add(target[i]);
            }
        }
        // System.out.println(numsOdd);
        // System.out.println(numsEven);
        // System.out.println(targetOdd);
        // System.out.println(targetEven);
        int n = nums.length;
        long ans = 0;
        for(int i = 0; i < numsOdd.size(); i++){
            if(numsOdd.get(i) < targetOdd.get(i)){
                ans += (targetOdd.get(i) - numsOdd.get(i)) / 2;
            }
        }
        for(int i = 0; i < numsEven.size(); i++){
            if(numsEven.get(i) < targetEven.get(i)){
                ans += (targetEven.get(i) - numsEven.get(i)) / 2;
            }
        }
        return ans;
    }
}
// @lc code=end
