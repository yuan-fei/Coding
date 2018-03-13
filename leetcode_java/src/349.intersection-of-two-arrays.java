/*
 * [349] Intersection of Two Arrays
 *
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 *
 * algorithms
 * Easy (48.02%)
 * Total Accepted:    123.8K
 * Total Submissions: 257.4K
 * Testcase Example:  '[]\n[]'
 *
 * 
 * Given two arrays, write a function to compute their intersection.
 * 
 * 
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * 
 * 
 * Note:
 * 
 * Each element in the result must be unique.
 * The result can be in any order.
 * 
 * 
 */
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        for (int num : nums1) {
        	set.add(num);
        }
        for (int num : nums2) {
        	if(set.contains(num)){
        		set.remove(num);
        		result.add(num);
        	}
        }
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
        	resultArray[i] = result.get(i);
        }
        return resultArray;
    }
}
