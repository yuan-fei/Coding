/*
 * @lc app=leetcode id=1481 lang=java
 *
 * [1481] Least Number of Unique Integers after K Removals
 *
 * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/description/
 *
 * algorithms
 * Medium (53.71%)
 * Likes:    83
 * Dislikes: 3
 * Total Accepted:    10.1K
 * Total Submissions: 18.9K
 * Testcase Example:  '[5,5,4]\n1'
 *
 * Given an array of integers arr and an integer k. Find the least number of
 * unique integers after removing exactly k elements.
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3
 * will be left.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * 0 <= k <= arr.length
 * 
 */

// @lc code=start
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int x : arr) {
        	m.put(x, m.getOrDefault(x, 0) + 1);
        }
        List<Integer> cnts = new ArrayList<>(m.values());
        Collections.sort(cnts);
        int sum = 0;
        for(int i = 0; i< cnts.size(); i++){
        	if(sum + cnts.get(i) > k){
        		return cnts.size() - i;
        	}
        	else if(sum + cnts.get(i) == k){
        		return cnts.size() - i - 1;	
        	}
        	else{
        		sum += cnts.get(i);
        	}
        }
        return 0;
    }
}
// @lc code=end
