/*
 * @lc app=leetcode id=2748 lang=java
 *
 * [2748] Number of Beautiful Pairs
 *
 * https://leetcode.com/problems/number-of-beautiful-pairs/description/
 *
 * algorithms
 * Easy (49.34%)
 * Likes:    190
 * Dislikes: 33
 * Total Accepted:    30.7K
 * Total Submissions: 62.2K
 * Testcase Example:  '[2,5,1,4]'
 *
 * You are given a 0-indexed integer array nums. A pair of indices i, j where 0
 * <= i < j < nums.length is called beautiful if the first digit of nums[i] and
 * the last digit of nums[j] are coprime.
 * 
 * Return the total number of beautiful pairs in nums.
 * 
 * Two integers x and y are coprime if there is no integer greater than 1 that
 * divides both of them. In other words, x and y are coprime if gcd(x, y) == 1,
 * where gcd(x, y) is the greatest common divisor of x and y.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,5,1,4]
 * Output: 5
 * Explanation: There are 5 beautiful pairs in nums:
 * When i = 0 and j = 1: the first digit of nums[0] is 2, and the last digit of
 * nums[1] is 5. We can confirm that 2 and 5 are coprime, since gcd(2,5) == 1.
 * When i = 0 and j = 2: the first digit of nums[0] is 2, and the last digit of
 * nums[2] is 1. Indeed, gcd(2,1) == 1.
 * When i = 1 and j = 2: the first digit of nums[1] is 5, and the last digit of
 * nums[2] is 1. Indeed, gcd(5,1) == 1.
 * When i = 1 and j = 3: the first digit of nums[1] is 5, and the last digit of
 * nums[3] is 4. Indeed, gcd(5,4) == 1.
 * When i = 2 and j = 3: the first digit of nums[2] is 1, and the last digit of
 * nums[3] is 4. Indeed, gcd(1,4) == 1.
 * Thus, we return 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [11,21,12]
 * Output: 2
 * Explanation: There are 2 beautiful pairs:
 * When i = 0 and j = 1: the first digit of nums[0] is 1, and the last digit of
 * nums[1] is 1. Indeed, gcd(1,1) == 1.
 * When i = 0 and j = 2: the first digit of nums[0] is 1, and the last digit of
 * nums[2] is 2. Indeed, gcd(1,2) == 1.
 * Thus, we return 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 9999
 * nums[i] % 10 != 0
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countBeautifulPairs(int[] nums) {
        int cnt = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(gcd(firtDigit(nums[j]), lastDigit(nums[i])) == 1){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    int gcd(int a, int b){
        return b == 0? a : gcd(b, a % b);
    }

    int firtDigit(int x){
        for(int m = 1000; m > 0; m /= 10){
            if(x / m > 0){
                return x / m;
            }
        }
        return 0;
    }

    int lastDigit(int x){
        return x % 10;
    }
}
// @lc code=end
