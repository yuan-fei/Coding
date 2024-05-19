/*
 * @lc app=leetcode id=2831 lang=java
 *
 * [2831] Find the Longest Equal Subarray
 *
 * https://leetcode.com/problems/find-the-longest-equal-subarray/description/
 *
 * algorithms
 * Medium (34.77%)
 * Likes:    645
 * Dislikes: 16
 * Total Accepted:    18.5K
 * Total Submissions: 52.6K
 * Testcase Example:  '[1,3,2,3,1,3]\n3'
 *
 * You are given a 0-indexed integer array nums and an integer k.
 * 
 * A subarray is called equal if all of its elements are equal. Note that the
 * empty subarray is an equal subarray.
 * 
 * Return the length of the longest possible equal subarray after deleting at
 * most k elements from nums.
 * 
 * A subarray is a contiguous, possibly empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,2,3,1,3], k = 3
 * Output: 3
 * Explanation: It's optimal to delete the elements at index 2 and index 4.
 * After deleting them, nums becomes equal to [1, 3, 3, 3].
 * The longest equal subarray starts at i = 1 and ends at j = 3 with length
 * equal to 3.
 * It can be proven that no longer equal subarrays can be created.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,1,2,2,1,1], k = 2
 * Output: 4
 * Explanation: It's optimal to delete the elements at index 2 and index 3.
 * After deleting them, nums becomes equal to [1, 1, 1, 1].
 * The array itself is an equal subarray, so the answer is 4.
 * It can be proven that no longer equal subarrays can be created.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= nums.length
 * 0 <= k <= nums.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        Map<Integer, List<Section>> numToSections = new HashMap<>();
        int l = 0;
        for(int i = 0; i < nums.size(); i++){
            if(nums.get(i) != nums.get(l)){
                List<Section> sections = numToSections.getOrDefault(nums.get(l), new ArrayList<>());
                sections.add(new Section(l, i - 1));
                numToSections.put(nums.get(l), sections);
                l = i;
            }
        }
        List<Section> sections = numToSections.getOrDefault(nums.get(l), new ArrayList<>());
        sections.add(new Section(l, nums.size() - 1));
        numToSections.put(nums.get(l), sections);
        int ans = 0;
        for(List<Section> v : numToSections.values()){
            ans = Math.max(ans, getMax(v, k));
        }
        return ans;
    }

    int getMax(List<Section> sections, int k){
        // System.out.println(sections);
        int r = -1;
        int ans = 0;
        int consectutiveCnt = 0;
        int kk = k;
        Section prev = null;
        for(int l = 0 ; l < sections.size(); l++){
            Section cur = sections.get(l);
            if(prev != null){
                consectutiveCnt -= prev.end() - prev.start() + 1;
                kk += cur.start() - prev.end() - 1;
                kk = Math.min(kk, k);
            } 
            if(r < l){
                r = l;    
                consectutiveCnt = sections.get(r).end() - sections.get(r).start() + 1;
            }
            
            while(r + 1 < sections.size() && sections.get(r + 1).start() - sections.get(r).end() - 1 <= kk){
                consectutiveCnt += sections.get(r + 1).end() - sections.get(r + 1).start() + 1;
                kk -= sections.get(r + 1).start() - sections.get(r).end() - 1;
                r++;
            }
            // System.out.println(Arrays.asList(l, consectutiveCnt, kk));
            ans = Math.max(ans, consectutiveCnt);
            prev = cur;
        }
        return ans;
    }

    record Section(int start, int end){}
}
// @lc code=end
