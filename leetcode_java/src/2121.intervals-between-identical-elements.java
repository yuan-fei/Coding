/*
 * @lc app=leetcode id=2121 lang=java
 *
 * [2121] Intervals Between Identical Elements
 *
 * https://leetcode.com/problems/intervals-between-identical-elements/description/
 *
 * algorithms
 * Medium (36.44%)
 * Likes:    208
 * Dislikes: 11
 * Total Accepted:    4.8K
 * Total Submissions: 13.2K
 * Testcase Example:  '[2,1,3,1,2,3,3]'
 *
 * You are given a 0-indexed array of n integers arr.
 * 
 * The interval between two elements in arr is defined as the absolute
 * difference between their indices. More formally, the interval between arr[i]
 * and arr[j] is |i - j|.
 * 
 * Return an array intervals of length n where intervals[i] is the sum of
 * intervals between arr[i] and each element in arr with the same value as
 * arr[i].
 * 
 * Note: |x| is the absolute value of x.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [2,1,3,1,2,3,3]
 * Output: [4,2,7,2,4,4,5]
 * Explanation:
 * - Index 0: Another 2 is found at index 4. |0 - 4| = 4
 * - Index 1: Another 1 is found at index 3. |1 - 3| = 2
 * - Index 2: Two more 3s are found at indices 5 and 6. |2 - 5| + |2 - 6| = 7
 * - Index 3: Another 1 is found at index 1. |3 - 1| = 2
 * - Index 4: Another 2 is found at index 0. |4 - 0| = 4
 * - Index 5: Two more 3s are found at indices 2 and 6. |5 - 2| + |5 - 6| = 4
 * - Index 6: Two more 3s are found at indices 2 and 5. |6 - 2| + |6 - 5| = 5
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [10,5,10,10]
 * Output: [5,0,3,4]
 * Explanation:
 * - Index 0: Two more 10s are found at indices 2 and 3. |0 - 2| + |0 - 3| = 5
 * - Index 1: There is only one 5 in the array, so its sum of intervals to
 * identical elements is 0.
 * - Index 2: Two more 10s are found at indices 0 and 3. |2 - 0| + |2 - 3| = 3
 * - Index 3: Two more 10s are found at indices 0 and 2. |3 - 0| + |3 - 2| =
 * 4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == arr.length
 * 1 <= n <= 10^5
 * 1 <= arr[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public long[] getDistances(int[] arr) {
        Map<Integer, List<Integer>> m = new HashMap<>();
        for(int i = 0 ;i < arr.length; i++){
            if(!m.containsKey(arr[i])){
                m.put(arr[i], new ArrayList<>());
            }
            m.get(arr[i]).add(i);
        }
        long[] res = new long[arr.length];
        for(int k : m.keySet()){
            List<Integer> idx = m.get(k);
            long[] ret = getDistances(idx);
            for(int i = 0; i < idx.size(); i++){
                res[idx.get(i)] = ret[i];
            }
        }
        return res;
    }

    long[] getDistances(List<Integer> idx){

        int n = idx.size();
        long[] pSum = new long[n + 1];
        for(int i = 2; i < pSum.length; i++){
            pSum[i] = pSum[i - 1] + idx.get(i - 1) - idx.get(0);
        }
        // System.out.println(idx);
        // System.out.println(Arrays.toString(pSum));
        long[] ret = new long[n];
        for(int i = 1; i <= n; i++){
            long dif = idx.get(i - 1) - idx.get(0);
            ret[i - 1] = (pSum[n] - pSum[i]) - (n - i) * dif + i * dif - pSum[i];
        }
        return ret;
    }
}
// @lc code=end
