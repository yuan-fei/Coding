/*
 * @lc app=leetcode id=2537 lang=java
 *
 * [2537] Count the Number of Good Subarrays
 *
 * https://leetcode.com/problems/count-the-number-of-good-subarrays/description/
 *
 * algorithms
 * Medium (37.04%)
 * Likes:    113
 * Dislikes: 8
 * Total Accepted:    4.4K
 * Total Submissions: 12K
 * Testcase Example:  '[1,1,1,1,1]\n10'
 *
 * Given an integer array nums and an integer k, return the number of good
 * subarrays of nums.
 * 
 * A subarray arr is good if it there are at least k pairs of indices (i, j)
 * such that i < j and arr[i] == arr[j].
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,1,1,1], k = 10
 * Output: 1
 * Explanation: The only good subarray is the array nums itself.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,1,4,3,2,2,4], k = 2
 * Output: 4
 * Explanation: There are 4 different good subarrays:
 * - [3,1,4,3,2,2] that has 2 pairs.
 * - [3,1,4,3,2,2,4] that has 3 pairs.
 * - [1,4,3,2,2,4] that has 2 pairs.
 * - [4,3,2,2,4] that has 2 pairs.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i], k <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Long> freq = new HashMap<>();
        long ans = 0;
        int right = -1;
        long goodPairs = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > 0){
                freq.put(nums[i - 1], freq.get(nums[i - 1]) - 1);
                goodPairs -= freq.get(nums[i - 1]);    
            }
            while(right < nums.length && goodPairs < k){
                right++;
                if(right < nums.length){
                    long cnt = freq.getOrDefault(nums[right], 0L);
                    goodPairs += cnt;
                    freq.put(nums[right], cnt + 1);    
                }
            }
            // System.out.println(Arrays.asList(i, right, goodPairs));
            if(goodPairs >= k){
                ans += nums.length - right;
            }
        }
        return ans;
    }
}
// @lc code=end
