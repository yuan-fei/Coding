/*
 * @lc app=leetcode id=2178 lang=java
 *
 * [2178] Maximum Split of Positive Even Integers
 *
 * https://leetcode.com/problems/maximum-split-of-positive-even-integers/description/
 *
 * algorithms
 * Medium (49.45%)
 * Likes:    139
 * Dislikes: 36
 * Total Accepted:    8.3K
 * Total Submissions: 16.8K
 * Testcase Example:  '12'
 *
 * You are given an integer finalSum. Split it into a sum of a maximum number
 * of unique positive even integers.
 * 
 * 
 * For example, given finalSum = 12, the following splits are valid (unique
 * positive even integers summing up to finalSum): (2 + 10), (2 + 4 + 6), and
 * (4 + 8). Among them, (2 + 4 + 6) contains the maximum number of integers.
 * Note that finalSum cannot be split into (2 + 2 + 4 + 4) as all the numbers
 * should be unique.
 * 
 * 
 * Return a list of integers that represent a valid split containing a maximum
 * number of integers. If no valid split exists for finalSum, return an empty
 * list. You may return the integers in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: finalSum = 12
 * Output: [2,4,6]
 * Explanation: The following are some valid splits: (2 + 10), (2 + 4 + 6), and
 * (4 + 8).
 * (2 + 4 + 6) has the maximum number of integers, which is 3. Thus, we return
 * [2,4,6].
 * Note that [2,6,4], [6,2,4], etc. are also accepted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: finalSum = 7
 * Output: []
 * Explanation: There are no valid splits for the given finalSum.
 * Thus, we return an empty array.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: finalSum = 28
 * Output: [6,8,2,12]
 * Explanation: The following are some valid splits: (2 + 26), (6 + 8 + 2 +
 * 12), and (4 + 24). 
 * (6 + 8 + 2 + 12) has the maximum number of integers, which is 4. Thus, we
 * return [6,8,2,12].
 * Note that [10,2,4,12], [6,2,4,16], etc. are also accepted.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= finalSum <= 10^10
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        if(finalSum % 2 != 0){
            return new ArrayList<>();
        }
        finalSum /= 2;
        long cur = 1;
        List<Long> res = new ArrayList<>();
        while(cur < (finalSum + 1) / 2){
            res.add(cur * 2);
            finalSum -= cur;
            cur++;
        }
        if(finalSum != 0){
            res.add(finalSum * 2);
        }
        return res;
    }
}
// @lc code=end
