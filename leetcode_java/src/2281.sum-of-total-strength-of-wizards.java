/*
 * @lc app=leetcode id=2281 lang=java
 *
 * [2281] Sum of Total Strength of Wizards
 *
 * https://leetcode.com/problems/sum-of-total-strength-of-wizards/description/
 *
 * algorithms
 * Hard (11.54%)
 * Likes:    156
 * Dislikes: 8
 * Total Accepted:    922
 * Total Submissions: 8K
 * Testcase Example:  '[1,3,1,2]'
 *
 * As the ruler of a kingdom, you have an army of wizards at your command.
 * 
 * You are given a 0-indexed integer array strength, where strength[i] denotes
 * the strength of the i^th wizard. For a contiguous group of wizards (i.e. the
 * wizards' strengths form a subarray of strength), the total strength is
 * defined as the product of the following two values:
 * 
 * 
 * The strength of the weakest wizard in the group.
 * The total of all the individual strengths of the wizards in the group.
 * 
 * 
 * Return the sum of the total strengths of all contiguous groups of wizards.
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: strength = [1,3,1,2]
 * Output: 44
 * Explanation: The following are all the contiguous groups of wizards:
 * - [1] from [1,3,1,2] has a total strength of min([1]) * sum([1]) = 1 * 1 = 1
 * - [3] from [1,3,1,2] has a total strength of min([3]) * sum([3]) = 3 * 3 = 9
 * - [1] from [1,3,1,2] has a total strength of min([1]) * sum([1]) = 1 * 1 = 1
 * - [2] from [1,3,1,2] has a total strength of min([2]) * sum([2]) = 2 * 2 = 4
 * - [1,3] from [1,3,1,2] has a total strength of min([1,3]) * sum([1,3]) = 1 *
 * 4 = 4
 * - [3,1] from [1,3,1,2] has a total strength of min([3,1]) * sum([3,1]) = 1 *
 * 4 = 4
 * - [1,2] from [1,3,1,2] has a total strength of min([1,2]) * sum([1,2]) = 1 *
 * 3 = 3
 * - [1,3,1] from [1,3,1,2] has a total strength of min([1,3,1]) * sum([1,3,1])
 * = 1 * 5 = 5
 * - [3,1,2] from [1,3,1,2] has a total strength of min([3,1,2]) * sum([3,1,2])
 * = 1 * 6 = 6
 * - [1,3,1,2] from [1,3,1,2] has a total strength of min([1,3,1,2]) *
 * sum([1,3,1,2]) = 1 * 7 = 7
 * The sum of all the total strengths is 1 + 9 + 1 + 4 + 4 + 4 + 3 + 5 + 6 + 7
 * = 44.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: strength = [5,4,6]
 * Output: 213
 * Explanation: The following are all the contiguous groups of wizards: 
 * - [5] from [5,4,6] has a total strength of min([5]) * sum([5]) = 5 * 5 = 25
 * - [4] from [5,4,6] has a total strength of min([4]) * sum([4]) = 4 * 4 = 16
 * - [6] from [5,4,6] has a total strength of min([6]) * sum([6]) = 6 * 6 = 36
 * - [5,4] from [5,4,6] has a total strength of min([5,4]) * sum([5,4]) = 4 * 9
 * = 36
 * - [4,6] from [5,4,6] has a total strength of min([4,6]) * sum([4,6]) = 4 *
 * 10 = 40
 * - [5,4,6] from [5,4,6] has a total strength of min([5,4,6]) * sum([5,4,6]) =
 * 4 * 15 = 60
 * The sum of all the total strengths is 25 + 16 + 36 + 36 + 40 + 60 = 213.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= strength.length <= 10^5
 * 1 <= strength[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    int MOD = (int)1e9 + 7;
    public int totalStrength(int[] strength) {
        int[] newStrength = new int[strength.length + 2];
        for(int i = 0; i < strength.length; i++){
            newStrength[i + 1] = strength[i];
        }
        strength = newStrength;
        int n = strength.length;
        long[] pSum = new long[n + 1];
        for(int i = 1; i <= n; i++){
            pSum[i] = add(pSum[i - 1], strength[i - 1]);
        }
        long[] pSumSum = new long[n + 1];
        for(int i = 1; i <= n; i++){
            pSumSum[i] = add(pSumSum[i - 1], pSum[i]);
        }
        long ans = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 1; i <= n; i++){
            while(!stack.isEmpty() && strength[stack.peek() - 1] > strength[i - 1]){
                int cur = stack.pop();
                // System.out.println("pop: " + cur);
                int left = stack.peek();
                long lSum = add(times(pSum[cur], cur - left), -add(pSumSum[cur - 1], -pSumSum[left - 1]));
                long rSum = add(add(pSumSum[i - 1], -pSumSum[cur - 1]), - times(pSum[cur - 1], i - cur));
                ans = add(ans, times(strength[cur - 1], add(add(times(lSum, i - cur), times(rSum, cur - left)), - times(times(cur - left, i - cur), strength[cur - 1]))));
                // System.out.println("lSum, i - cur: " + lSum + ", " +(i - cur));
                // System.out.println("rSum, cur - left: " + rSum + ", " + (cur  -left));
                // System.out.println("ans: " + ans);
            }
            stack.push(i);
            // System.out.println(stack);
        }

        return (int)ans;
    }

    long add(long a, long b){
        long c = a + b;
        if(c < 0){
            c += MOD;
        }
        return c % MOD;
    }

    long times(long a, long b){
        return a * b % MOD;
    }

}
// @lc code=end
