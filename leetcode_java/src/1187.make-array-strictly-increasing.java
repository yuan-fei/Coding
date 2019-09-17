/*
 * @lc app=leetcode id=1187 lang=java
 *
 * [1187] Make Array Strictly Increasing
 *
 * https://leetcode.com/problems/make-array-strictly-increasing/description/
 *
 * algorithms
 * Hard (38.21%)
 * Total Accepted:    1.4K
 * Total Submissions: 3.8K
 * Testcase Example:  '[1,5,3,6,7]\n[1,3,2,4]'
 *
 * Given two integer arrays arr1 and arr2, return the minimum number of
 * operations (possibly zero) needed to make arr1 strictly increasing.
 * 
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j
 * < arr2.length and do the assignment arr1[i] = arr2[j].
 * 
 * If there is no way to make arr1 strictly increasing, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * Output: 1
 * Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
 * Output: 2
 * Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6,
 * 7].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
 * Output: -1
 * Explanation: You can't make arr1 strictly increasing.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr1.length, arr2.length <= 2000
 * 0 <= arr1[i], arr2[i] <= 10^9
 * 
 * 
 * 
 */
class Solution {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
		int N = arr1.length;
    	int[] arr = new int[N + 2];
    	System.arraycopy(arr1, 0, arr, 1, N);
    	arr[0] = -1;
    	arr[N + 1] = Integer.MAX_VALUE;

    	
		TreeMap<Integer, Integer> tm = compress(arr2);
		int[] dp = new int[N + 2];
		int MAX = N + 1;
		Arrays.fill(dp, MAX);
		dp[0] = 0;
		for(int i = 1; i <= N + 1; i++){
			for(int j = 0; j < i; j++){
				if(arr[i] > arr[j]){
					int maxReplacement = countBetween(tm, arr[j], arr[i]);
					// System.out.println(arr[j]+","+arr[i]+"="+maxReplacement);
					if(maxReplacement >= i - j - 1){
						dp[i] = Math.min(dp[i], dp[j] + i - j - 1);
					}
				}
			}
		}
		// System.out.println(Arrays.toString(arr));
		// System.out.println(tm.keySet());
		// System.out.println(Arrays.toString(dp));
		if(dp[N + 1] >= MAX){
			return -1;
		}
		else{
			return dp[N + 1];
		}
    }

    int countBetween(TreeMap<Integer, Integer> tm, int low, int high){
    	int ub = 0;
    	int lb = 0;
    	Map.Entry<Integer, Integer> ue = tm.lowerEntry(high);
    	if(ue != null){
    		ub = ue.getValue();
    	}
    	Map.Entry<Integer, Integer> le = tm.floorEntry(low);
    	if(le != null){
    		lb = le.getValue();
    	}
    	return Math.max(0, ub - lb);
    }

    TreeMap<Integer, Integer> compress(int[] arr){
		TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int a : arr) {
        	tm.put(a, 0);
        }
        int i = 1;
        for (int k : tm.keySet()) {
        	tm.put(k, i++);
        }
        return tm;
    }


}
