/*
 * @lc app=leetcode id=2382 lang=java
 *
 * [2382] Maximum Segment Sum After Removals
 *
 * https://leetcode.com/problems/maximum-segment-sum-after-removals/description/
 *
 * algorithms
 * Hard (43.09%)
 * Likes:    197
 * Dislikes: 2
 * Total Accepted:    3.4K
 * Total Submissions: 7.9K
 * Testcase Example:  '[1,2,5,6,1]\n[0,3,2,4,1]'
 *
 * You are given two 0-indexed integer arrays nums and removeQueries, both of
 * length n. For the i^th query, the element in nums at the index
 * removeQueries[i] is removed, splitting nums into different segments.
 * 
 * A segment is a contiguous sequence of positive integers in nums. A segment
 * sum is the sum of every element in a segment.
 * 
 * Return an integer array answer, of length n, where answer[i] is the maximum
 * segment sum after applying the i^th removal.
 * 
 * Note: The same index will not be removed more than once.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,5,6,1], removeQueries = [0,3,2,4,1]
 * Output: [14,7,2,2,0]
 * Explanation: Using 0 to indicate a removed element, the answer is as
 * follows:
 * Query 1: Remove the 0th element, nums becomes [0,2,5,6,1] and the maximum
 * segment sum is 14 for segment [2,5,6,1].
 * Query 2: Remove the 3rd element, nums becomes [0,2,5,0,1] and the maximum
 * segment sum is 7 for segment [2,5].
 * Query 3: Remove the 2nd element, nums becomes [0,2,0,0,1] and the maximum
 * segment sum is 2 for segment [2]. 
 * Query 4: Remove the 4th element, nums becomes [0,2,0,0,0] and the maximum
 * segment sum is 2 for segment [2]. 
 * Query 5: Remove the 1st element, nums becomes [0,0,0,0,0] and the maximum
 * segment sum is 0, since there are no segments.
 * Finally, we return [14,7,2,2,0].
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,2,11,1], removeQueries = [3,2,1,0]
 * Output: [16,5,3,0]
 * Explanation: Using 0 to indicate a removed element, the answer is as
 * follows:
 * Query 1: Remove the 3rd element, nums becomes [3,2,11,0] and the maximum
 * segment sum is 16 for segment [3,2,11].
 * Query 2: Remove the 2nd element, nums becomes [3,2,0,0] and the maximum
 * segment sum is 5 for segment [3,2].
 * Query 3: Remove the 1st element, nums becomes [3,0,0,0] and the maximum
 * segment sum is 3 for segment [3].
 * Query 4: Remove the 0th element, nums becomes [0,0,0,0] and the maximum
 * segment sum is 0, since there are no segments.
 * Finally, we return [16,5,3,0].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length == removeQueries.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= removeQueries[i] < n
 * All the values of removeQueries are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    long[] pSum;
    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        pSum = new long[n + 1];
        for(int i = 1; i <= n; i++){
            pSum[i] = pSum[i - 1] + nums[i - 1];
        }
        long[] ans = new long[n];
        TreeSet<int[]> vMap = new TreeSet<>((a, b) -> 
            Long.compare(getValue(a), getValue(b)) != 0 ? 
                Long.compare(getValue(a), getValue(b))
                : Integer.compare(a[1], b[1])
        );
        TreeSet<int[]> rMap = new TreeSet<>((a, b) -> Integer.compare(a[0], b[0]));
        vMap.add(new int[]{0, n - 1});
        rMap.add(new int[]{0, n - 1});
        for(int i = 0; i < removeQueries.length; i++){
            int[] range = rMap.floor(new int[]{removeQueries[i], removeQueries[i]});
            // System.out.println(Arrays.toString(range));
            rMap.remove(range);
            vMap.remove(range);
            int[] rLower = new int[]{range[0], removeQueries[i] - 1};
            int[] rUpper = new int[]{removeQueries[i] + 1, range[1]};
            if(rLower[0] <= rLower[1]){
                rMap.add(rLower);
                vMap.add(rLower);
            }
            if(rUpper[0] <= rUpper[1]){
                rMap.add(rUpper);
                vMap.add(rUpper);
            }
            // System.out.println(vMap.size());
            if(!vMap.isEmpty()){
                ans[i] = getValue(vMap.last());    
            }
        }
        return ans;
    }

    long getValue(int[] range){
        return pSum[range[1] + 1] - pSum[range[0]];
    }
}
// @lc code=end
