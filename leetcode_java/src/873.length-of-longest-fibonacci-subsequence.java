/*
 * @lc app=leetcode id=873 lang=java
 *
 * [873] Length of Longest Fibonacci Subsequence
 *
 * https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/description/
 *
 * algorithms
 * Medium (48.32%)
 * Likes:    1814
 * Dislikes: 64
 * Total Accepted:    56.1K
 * Total Submissions: 116.1K
 * Testcase Example:  '[1,2,3,4,5,6,7,8]'
 *
 * A sequence x1, x2, ..., xn is Fibonacci-like if:
 * 
 * 
 * n >= 3
 * xi + xi+1 == xi+2 for all i + 2 <= n
 * 
 * 
 * Given a strictly increasing array arr of positive integers forming a
 * sequence, return the length of the longest Fibonacci-like subsequence of
 * arr. If one does not exist, return 0.
 * 
 * A subsequence is derived from another sequence arr by deleting any number of
 * elements (including none) from arr, without changing the order of the
 * remaining elements. For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6,
 * 7, 8].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [1,2,3,4,5,6,7,8]
 * Output: 5
 * Explanation: The longest subsequence that is fibonacci-like: [1,2,3,5,8].
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,3,7,11,12,14,18]
 * Output: 3
 * Explanation: The longest subsequence that is fibonacci-like: [1,11,12],
 * [3,11,14] or [7,11,18].
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= arr.length <= 1000
 * 1 <= arr[i] < arr[i + 1] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int ans = 0;
        Map<Integer, TreeSet<Integer>> m = new HashMap<>();
        for(int i = 0; i < n; i++){
            m.putIfAbsent(arr[i], new TreeSet<>());
            m.get(arr[i]).add(i);
        }
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int a = arr[i];
                int b = arr[j];
                int len = 2;
                int k = j;
                while(m.containsKey(a + b) && m.get(a + b).higher(k) != null){
                    k = m.get(a + b).higher(k);
                    len++;
                    // System.out.println(k + ", " + len);
                    a = b;
                    b = arr[k];
                }
                ans = Math.max(ans, len);
            }
        }
        if(ans <= 2){
            return 0;
        }
        return ans;
    }
}
// @lc code=end
