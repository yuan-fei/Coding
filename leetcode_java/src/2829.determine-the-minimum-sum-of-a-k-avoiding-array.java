/*
 * @lc app=leetcode id=2829 lang=java
 *
 * [2829] Determine the Minimum Sum of a k-avoiding Array
 *
 * https://leetcode.com/problems/determine-the-minimum-sum-of-a-k-avoiding-array/description/
 *
 * algorithms
 * Medium (60.75%)
 * Likes:    322
 * Dislikes: 8
 * Total Accepted:    33.5K
 * Total Submissions: 54.9K
 * Testcase Example:  '5\n4'
 *
 * You are given two integers, n and k.
 * 
 * An array of distinct positive integers is called a k-avoiding array if there
 * does not exist any pair of distinct elements that sum to k.
 * 
 * Return the minimum possible sum of a k-avoiding array of length n.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5, k = 4
 * Output: 18
 * Explanation: Consider the k-avoiding array [1,2,4,5,6], which has a sum of
 * 18.
 * It can be proven that there is no k-avoiding array with a sum less than
 * 18.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, k = 6
 * Output: 3
 * Explanation: We can construct the array [1,2], which has a sum of 3.
 * It can be proven that there is no k-avoiding array with a sum less than
 * 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n, k <= 50
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumSum(int n, int k) {
        int ans = 0;
        Set<Integer> s = new HashSet<>();
        int i = 1;
        while(n != 0){
            if(!s.contains(k - i)){
                ans += i;
                s.add(i);
                n--;
            }
            i++;
        }
        return ans;
    }
}
// @lc code=end
