/*
 * @lc app=leetcode id=870 lang=java
 *
 * [870] Advantage Shuffle
 *
 * https://leetcode.com/problems/advantage-shuffle/description/
 *
 * algorithms
 * Medium (51.87%)
 * Likes:    1478
 * Dislikes: 92
 * Total Accepted:    60.4K
 * Total Submissions: 116.5K
 * Testcase Example:  '[2,7,11,15]\n[1,10,4,11]'
 *
 * You are given two integer arrays nums1 and nums2 both of the same length.
 * The advantage of nums1 with respect to nums2 is the number of indices i for
 * which nums1[i] > nums2[i].
 * 
 * Return any permutation of nums1 that maximizes its advantage with respect to
 * nums2.
 * 
 * 
 * Example 1:
 * Input: nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * Output: [2,11,7,15]
 * Example 2:
 * Input: nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * Output: [24,32,8,12]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums1.length <= 10^5
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for(int x : nums1){
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        // int[][] nums2WithId = new int[n][2];
        // for(int i = 0; i < n; i++){
        //     nums2WithId[i] = {nums2[i], i};
        // }
        // Arrays.sort(nums2WithId, (a, b) -> Integer.compare(a[0], b[0]));
        int[] ans = new int[nums1.length];
        List<Integer> noMatch = new ArrayList<>();
        for(int i = 0; i < nums2.length; i++){
            Integer k = freq.higherKey(nums2[i]);
            if(k != null){
                ans[i] = k;
                freq.put(k, freq.get(k) - 1);
                if(freq.get(k) == 0){
                    freq.remove(k);
                }
            }
            else{
                noMatch.add(i);
            }
        }
        int i = 0;
        for(int k : freq.keySet()){
            int v = freq.get(k);
            while(v > 0){
                ans[noMatch.get(i++)] = k;
                v--;
            }
        }
        return ans;
    }
}
// @lc code=end
