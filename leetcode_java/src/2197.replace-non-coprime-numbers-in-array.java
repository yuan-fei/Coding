/*
 * @lc app=leetcode id=2197 lang=java
 *
 * [2197] Replace Non-Coprime Numbers in Array
 *
 * https://leetcode.com/problems/replace-non-coprime-numbers-in-array/description/
 *
 * algorithms
 * Hard (32.60%)
 * Likes:    147
 * Dislikes: 4
 * Total Accepted:    4.9K
 * Total Submissions: 15.1K
 * Testcase Example:  '[6,4,3,2,7,6,2]'
 *
 * You are given an array of integers nums. Perform the following steps:
 * 
 * 
 * Find any two adjacent numbers in nums that are non-coprime.
 * If no such numbers are found, stop the process.
 * Otherwise, delete the two numbers and replace them with their LCM (Least
 * Common Multiple).
 * Repeat this process as long as you keep finding two adjacent non-coprime
 * numbers.
 * 
 * 
 * Return the final modified array. It can be shown that replacing adjacent
 * non-coprime numbers in any arbitrary order will lead to the same result.
 * 
 * The test cases are generated such that the values in the final array are
 * less than or equal to 10^8.
 * 
 * Two values x and y are non-coprime if GCD(x, y) > 1 where GCD(x, y) is the
 * Greatest Common Divisor of x and y.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [6,4,3,2,7,6,2]
 * Output: [12,7,6]
 * Explanation: 
 * - (6, 4) are non-coprime with LCM(6, 4) = 12. Now, nums = [12,3,2,7,6,2].
 * - (12, 3) are non-coprime with LCM(12, 3) = 12. Now, nums = [12,2,7,6,2].
 * - (12, 2) are non-coprime with LCM(12, 2) = 12. Now, nums = [12,7,6,2].
 * - (6, 2) are non-coprime with LCM(6, 2) = 6. Now, nums = [12,7,6].
 * There are no more adjacent non-coprime numbers in nums.
 * Thus, the final modified array is [12,7,6].
 * Note that there are other ways to obtain the same resultant array.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,2,1,1,3,3,3]
 * Output: [2,1,1,3]
 * Explanation: 
 * - (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,3,3].
 * - (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,3].
 * - (2, 2) are non-coprime with LCM(2, 2) = 2. Now, nums = [2,1,1,3].
 * There are no more adjacent non-coprime numbers in nums.
 * Thus, the final modified array is [2,1,1,3].
 * Note that there are other ways to obtain the same resultant array.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * The test cases are generated such that the values in the final array are
 * less than or equal to 10^8.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        Stack<Integer> s = new Stack<>();
        int cur = nums[0];
        for(int i = 1; i < nums.length; i++){
            int x = nums[i];
            int d = gcd(x, cur);
            if(d != 1){
                cur /= d;
                cur *= x;
            }
            else{
                while(!s.isEmpty()){
                    int dd = gcd(s.peek(), cur);
                    if(dd != 1){
                        cur /= dd;
                        cur *= s.pop();
                    }
                    else{
                        break;
                    }
                }
                s.push(cur);
                cur = x;
            }
        }
        while(!s.isEmpty()){
            int dd = gcd(s.peek(), cur);
            if(dd != 1){
                cur /= dd;
                cur *= s.pop();
            }
            else{
                break;
            }
        }
        s.push(cur);
        List<Integer> l = new ArrayList<>(s);
        return l;
    }

    int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        else{
            return gcd(b, a % b);
        }
    }
}
// @lc code=end
