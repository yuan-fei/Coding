/*
 * @lc app=leetcode id=969 lang=java
 *
 * [969] Pancake Sorting
 *
 * https://leetcode.com/problems/pancake-sorting/description/
 *
 * algorithms
 * Medium (70.30%)
 * Likes:    1413
 * Dislikes: 1476
 * Total Accepted:    87.7K
 * Total Submissions: 124.7K
 * Testcase Example:  '[3,2,4,1]'
 *
 * Given an array of integers arr, sort the array by performing a series of
 * pancake flips.
 * 
 * In one pancake flip we do the following steps:
 * 
 * 
 * Choose an integer k where 1 <= k <= arr.length.
 * Reverse the sub-array arr[0...k-1] (0-indexed).
 * 
 * 
 * For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k =
 * 3, we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake
 * flip at k = 3.
 * 
 * Return an array of the k-values corresponding to a sequence of pancake flips
 * that sort arr. Any valid answer that sorts the array within 10 * arr.length
 * flips will be judged as correct.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [3,2,4,1]
 * Output: [4,2,4,3]
 * Explanation: 
 * We perform 4 pancake flips, with k values 4, 2, 4, and 3.
 * Starting state: arr = [3, 2, 4, 1]
 * After 1st flip (k = 4): arr = [1, 4, 2, 3]
 * After 2nd flip (k = 2): arr = [4, 1, 2, 3]
 * After 3rd flip (k = 4): arr = [3, 2, 1, 4]
 * After 4th flip (k = 3): arr = [1, 2, 3, 4], which is sorted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,2,3]
 * Output: []
 * Explanation: The input is already sorted, so there is no need to flip
 * anything.
 * Note that other answers, such as [3, 3], would also be accepted.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= arr.length
 * All integers in arr are unique (i.e. arr is a permutation of the integers
 * from 1 to arr.length).
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        int n = arr.length;
        List<Integer> res = new ArrayList<>();
        int[] pos = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pos[arr[i]] = i;
        }
        for(int i = n; i > 0; i--){
            res.add(pos[i] + 1);
            res.add(i);
            shift(arr, i, i - 1 - pos[i]);
            reverse(arr, 0, Math.max(0, i - 2 - pos[i]));
            
            for(int j = 0; j <= i - 1; j++){
                pos[arr[j]] = j;
            }
            // System.out.println(Arrays.toString(arr) + ": " + Arrays.toString(pos));
        }
        return res;
    }

    void shift(int[] a, int ub, int ofs){
        int[] b = Arrays.copyOf(a, ub);
        for(int i = 0; i < ub; i++){
            a[(i + ofs) % ub] = b[i];
        }
    }

    void reverse(int[] a, int l, int r){
        while(l < r){
            swap(a, l++, r--);
        }
    }

    void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
// @lc code=end
