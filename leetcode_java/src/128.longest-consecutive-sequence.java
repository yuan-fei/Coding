/*
 * [128] Longest Consecutive Sequence
 *
 * https://leetcode.com/problems/longest-consecutive-sequence/description/
 *
 * algorithms
 * Hard (37.99%)
 * Total Accepted:    132.4K
 * Total Submissions: 348.3K
 * Testcase Example:  '[100,4,200,1,3,2]'
 *
 * 
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * 
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its
 * length: 4.
 * 
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 */
class Solution {
    public int longestConsecutive(int[] nums) {
    	if(nums == null || nums.length == 0){
    		return 0;
    	}
        Set s = new HashSet();
        for (int num: nums) {
        	s.add(num);
        }
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
        	if(s.contains(nums[i])){
        		s.remove(nums[i]);
        		int left = nums[i] - 1;
        		while(s.contains(left)){
        			s.remove(left);
        			left--;
        		}
        		int right = nums[i] + 1;
        		while(s.contains(right)){
        			s.remove(right);
        			right++;
        		}
        		maxLength = Math.max(maxLength, right - left - 1);
        	}
        }
        return maxLength;
    }
}
