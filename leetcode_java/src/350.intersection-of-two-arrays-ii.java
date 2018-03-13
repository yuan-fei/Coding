/*
 * [350] Intersection of Two Arrays II
 *
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
 *
 * algorithms
 * Easy (44.70%)
 * Total Accepted:    98.9K
 * Total Submissions: 222.4K
 * Testcase Example:  '[]\n[]'
 *
 * 
 * Given two arrays, write a function to compute their intersection.
 * 
 * 
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * 
 * 
 * Note:
 * 
 * Each element in the result should appear as many times as it shows in both
 * arrays.
 * The result can be in any order.
 * 
 * 
 * 
 * Follow up:
 * 
 * What if the given array is already sorted? How would you optimize your
 * algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is
 * better?
 * What if elements of nums2 are stored on disk, and the memory is limited such
 * that you cannot load all elements into the memory at once?
 * 
 * 
 */
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> result = new ArrayList<Integer>();
        for (int num : nums1) {
        	Integer count = map.get(num);
        	if(count == null){
        		count = 0;
        	}
        	map.put(num, ++count);
        }
        for (int num : nums2) {
        	Integer count = map.get(num);
        	if(count != null){
        		result.add(num);
        		count--;
        		if(count == 0){
        			map.remove(num);
        		}else{
        			map.put(num, count);
        		}
        	}
        }
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
        	resultArray[i] = result.get(i);
        }
        return resultArray;
    }
}
