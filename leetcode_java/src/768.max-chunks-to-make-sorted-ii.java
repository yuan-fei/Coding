/*
 * @lc app=leetcode id=768 lang=java
 *
 * [768] Max Chunks To Make Sorted II
 *
 * https://leetcode.com/problems/max-chunks-to-make-sorted-ii/description/
 *
 * algorithms
 * Hard (52.85%)
 * Likes:    1531
 * Dislikes: 44
 * Total Accepted:    50.4K
 * Total Submissions: 95.4K
 * Testcase Example:  '[5,4,3,2,1]'
 *
 * You are given an integer array arr.
 * 
 * We split arr into some number of chunks (i.e., partitions), and individually
 * sort each chunk. After concatenating them, the result should equal the
 * sorted array.
 * 
 * Return the largest number of chunks we can make to sort the array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [5,4,3,2,1]
 * Output: 1
 * Explanation:
 * Splitting into two or more chunks will not return the required result.
 * For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2,
 * 3], which isn't sorted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [2,1,3,4,4]
 * Output: 4
 * Explanation:
 * We can split into two chunks, such as [2, 1], [3, 4, 4].
 * However, splitting into [2, 1], [3], [4], [4] is the highest number of
 * chunks possible.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 2000
 * 0 <= arr[i] <= 10^8
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int[] orig = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr);
        Map<Integer, Stack<Integer>> m = new HashMap<>();
        for(int i = arr.length - 1; i >= 0; i--){
            m.putIfAbsent(arr[i], new Stack<>());
            m.get(arr[i]).push(i);
        }
        int ans = 0;
        int maxRight = 0;
        for(int i = 0; i < arr.length; i++){
            int r = m.get(orig[i]).pop();
            maxRight = Math.max(maxRight, r);
            if(maxRight == i){
                ans++;
                maxRight++;
            }
        }
        return ans;
    }
}
// @lc code=end