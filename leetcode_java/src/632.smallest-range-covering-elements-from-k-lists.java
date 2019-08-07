/*
 * @lc app=leetcode id=632 lang=java
 *
 * [632] Smallest Range Covering Elements from K Lists
 *
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/description/
 *
 * algorithms
 * Hard (48.43%)
 * Total Accepted:    25.5K
 * Total Submissions: 52.7K
 * Testcase Example:  '[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]'
 *
 * You have k lists of sorted integers in ascending order. Find the smallest
 * range that includes at least one number from each of the k lists.
 * 
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c
 * if b-a == d-c.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * Output: [20,24]
 * Explanation: 
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The given list may contain duplicates, so ascending order means >= here.
 * 1 <= k <= 3500
 * -105 <= value of elements <= 105.
 * 
 * 
 */
/*
 * @lc app=leetcode id=632 lang=java
 *
 * [632] Smallest Range
 *
 * https://leetcode.com/problems/smallest-range/description/
 *
 * algorithms
 * Hard (47.25%)
 * Total Accepted:    25.4K
 * Total Submissions: 52.5K
 * Testcase Example:  '[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]'
 *
 * You have k lists of sorted integers in ascending order. Find the smallest
 * range that includes at least one number from each of the k lists. 
 * 
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c
 * if b-a == d-c.
 * 
 * Example 1:
 * 
 * Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * Output: [20,24]
 * Explanation: 
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * 
 * 
 * 
 * 
 * Note:
 * 
 * The given list may contain duplicates, so ascending order means >= here.
 * ⁠-105 value of elements 5.
 * For Java users, please note that the input type has been changed to
 * List<List<Integer>>. And after you reset the code template, you'll see this
 * point.
 * 
 * 
 * 
 */
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
		TreeMap<int[], Integer> bst = new TreeMap<>((a, b)->Integer.compare(a[0], b[0]) == 0?Integer.compare(a[1], b[1]):Integer.compare(a[0], b[0]));
		int n = nums.size();
		for(int i = 0; i < n; i++){
			List<Integer> l =nums.get(i);
			bst.put(new int[]{l.get(0), i}, 0);
		}
		int minD = Integer.MAX_VALUE; 
		int[] best = new int[2];
		while(bst.size() == n){
			Map.Entry<int[], Integer> min = bst.firstEntry();
			Map.Entry<int[], Integer> max = bst.lastEntry();
			if(max.getKey()[0] - min.getKey()[0] < minD){
				minD = max.getKey()[0] - min.getKey()[0];
				best = new int[]{min.getKey()[0], max.getKey()[0]};
			}
			// System.out.println(Arrays.toString(best));
			int ith = min.getKey()[1];
			int pos = min.getValue() + 1;
			bst.pollFirstEntry();
			if(pos  < nums.get(ith).size()){
				bst.put(new int[]{nums.get(ith).get(pos), ith}, pos);
			}
		}
		return best;
    }
}

