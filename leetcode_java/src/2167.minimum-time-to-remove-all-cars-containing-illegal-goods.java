/*
 * @lc app=leetcode id=2167 lang=java
 *
 * [2167] Minimum Time to Remove All Cars Containing Illegal Goods
 *
 * https://leetcode.com/problems/minimum-time-to-remove-all-cars-containing-illegal-goods/description/
 *
 * algorithms
 * Hard (26.44%)
 * Likes:    161
 * Dislikes: 3
 * Total Accepted:    2.8K
 * Total Submissions: 9.8K
 * Testcase Example:  '"1100101"'
 *
 * You are given a 0-indexed binary string s which represents a sequence of
 * train cars. s[i] = '0' denotes that the i^th car does not contain illegal
 * goods and s[i] = '1' denotes that the i^th car does contain illegal goods.
 * 
 * As the train conductor, you would like to get rid of all the cars containing
 * illegal goods. You can do any of the following three operations any number
 * of times:
 * 
 * 
 * Remove a train car from the left end (i.e., remove s[0]) which takes 1 unit
 * of time.
 * Remove a train car from the right end (i.e., remove s[s.length - 1]) which
 * takes 1 unit of time.
 * Remove a train car from anywhere in the sequence which takes 2 units of
 * time.
 * 
 * 
 * Return the minimum time to remove all the cars containing illegal goods.
 * 
 * Note that an empty sequence of cars is considered to have no cars containing
 * illegal goods.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "1100101"
 * Output: 5
 * Explanation: 
 * One way to remove all the cars containing illegal goods from the sequence is
 * to
 * - remove a car from the left end 2 times. Time taken is 2 * 1 = 2.
 * - remove a car from the right end. Time taken is 1.
 * - remove the car containing illegal goods found in the middle. Time taken is
 * 2.
 * This obtains a total time of 2 + 1 + 2 = 5. 
 * 
 * An alternative way is to
 * - remove a car from the left end 2 times. Time taken is 2 * 1 = 2.
 * - remove a car from the right end 3 times. Time taken is 3 * 1 = 3.
 * This also obtains a total time of 2 + 3 = 5.
 * 
 * 5 is the minimum time taken to remove all the cars containing illegal
 * goods. 
 * There are no other ways to remove them with less time.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "0010"
 * Output: 2
 * Explanation:
 * One way to remove all the cars containing illegal goods from the sequence is
 * to
 * - remove a car from the left end 3 times. Time taken is 3 * 1 = 3.
 * This obtains a total time of 3.
 * 
 * Another way to remove all the cars containing illegal goods from the
 * sequence is to
 * - remove the car containing illegal goods found in the middle. Time taken is
 * 2.
 * This obtains a total time of 2.
 * 
 * Another way to remove all the cars containing illegal goods from the
 * sequence is to 
 * - remove a car from the right end 2 times. Time taken is 2 * 1 = 2. 
 * This obtains a total time of 2.
 * 
 * 2 is the minimum time taken to remove all the cars containing illegal
 * goods. 
 * There are no other ways to remove them with less time.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 2 * 10^5
 * s[i] is either '0' or '1'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumTime(String s) {
        int n = s.length();
        int[] left = new int[n + 2];
        int[] right = new int[n + 2];
        int last1 = 0;
        for(int i = 1; i <= n; i++){
            if(s.charAt(i - 1) == '0'){
                left[i] = left[i - 1];
            }
            else{
                left[i] = Math.min(left[last1] + 2, i);
                last1 = i;
            }
        }
        last1 = n + 1;
        for(int i = n; i >= 1; i--){
            if(s.charAt(i - 1) == '0'){
                right[i] = right[i + 1];
            }
            else{
                right[i] = Math.min(right[last1] + 2, n - i + 1);
                last1 = i;
            }
        }
        int ans = 2 * n;
        for(int i = 0; i <= n; i++){
            ans = Math.min(ans, left[i] + right[i + 1]);
        }
        return ans;
    }
}
// @lc code=end
