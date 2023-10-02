/*
 * @lc app=leetcode id=949 lang=java
 *
 * [949] Largest Time for Given Digits
 *
 * https://leetcode.com/problems/largest-time-for-given-digits/description/
 *
 * algorithms
 * Medium (35.11%)
 * Likes:    674
 * Dislikes: 1028
 * Total Accepted:    82K
 * Total Submissions: 233.5K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given an array arr of 4 digits, find the latest 24-hour time that can be
 * made using each digit exactly once.
 * 
 * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and
 * MM is between 00 and 59. The earliest 24-hour time is 00:00, and the latest
 * is 23:59.
 * 
 * Return the latest 24-hour time in "HH:MM" format. If no valid time can be
 * made, return an empty string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [1,2,3,4]
 * Output: "23:41"
 * Explanation: The valid 24-hour times are "12:34", "12:43", "13:24", "13:42",
 * "14:23", "14:32", "21:34", "21:43", "23:14", and "23:41". Of these times,
 * "23:41" is the latest.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [5,5,5,5]
 * Output: ""
 * Explanation: There are no valid 24-hour times as "55:55" is not valid.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * arr.length == 4
 * 0 <= arr[i] <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public String largestTimeFromDigits(int[] arr) {
        TreeSet<String> ts = new TreeSet<>();
        rec(arr, 0, ts);
        return ts.isEmpty() ? "" : ts.last();
    }

    void rec(int[] arr, int pos, TreeSet<String> res){
        if(pos == arr.length){
            // System.out.println(Arrays.toString(arr));
            if(check(arr)){
                res.add("" + arr[0] + arr[1] + ":" + arr[2] + arr[3]);
            }
            return;
        }
        for(int i = pos; i < arr.length; i++){
            swap(arr, pos, i);
            rec(arr, pos + 1, res);
            swap(arr, pos, i);
        }
    }

    boolean check(int[] a){
        return a[0] * 10 + a[1] < 24 && a[2] * 10 + a[3] < 60;
    }

    void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
// @lc code=end
