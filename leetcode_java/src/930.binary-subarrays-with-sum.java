/*
 * @lc app=leetcode id=930 lang=java
 *
 * [930] Binary Subarrays With Sum
 *
 * https://leetcode.com/problems/binary-subarrays-with-sum/description/
 *
 * algorithms
 * Medium (37.79%)
 * Total Accepted:    11.1K
 * Total Submissions: 28.5K
 * Testcase Example:  '[1,0,1,0,1]\n2'
 *
 * In an array A of 0s and 1s, how many non-empty subarrays have sum S?
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: A = [1,0,1,0,1], S = 2
 * Output: 4
 * Explanation: 
 * The 4 subarrays are bolded below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] is either 0 or 1.
 * 
 */
class Solution {
    public int numSubarraysWithSum(int[] A, int S) {
    	int n = A.length;
    	int cnt = 0;
    	Map<Integer,Integer> map = new HashMap<>();
    	int sum = 0;
    	map.put(0, 1);
    	for(int i = 0; i < n; i++){
    		sum+=A[i];
    		int counterPart = sum - S;
    		int cpCnt = map.getOrDefault(counterPart, 0);
    		cnt += cpCnt;
			map.put(sum, map.getOrDefault(sum, 0) + 1);
    	}
		return cnt;
    }
}
