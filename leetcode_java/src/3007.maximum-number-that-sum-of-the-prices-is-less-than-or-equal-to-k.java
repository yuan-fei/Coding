/*
 * @lc app=leetcode id=3007 lang=java
 *
 * [3007] Maximum Number That Sum of the Prices Is Less Than or Equal to K
 *
 * https://leetcode.com/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k/description/
 *
 * algorithms
 * Medium (31.31%)
 * Likes:    309
 * Dislikes: 124
 * Total Accepted:    10.1K
 * Total Submissions: 27.6K
 * Testcase Example:  '9\n1'
 *
 * You are given an integer k and an integer x. The price of a number num is
 * calculated by the count of set bits at positions x, 2x, 3x, etc., in its
 * binary representation, starting from the least significant bit. The
 * following table contains examples of how price is
 * calculated.
 * 
 * 
 * 
 * 
 * x
 * num
 * Binary Representation
 * Price
 * 
 * 
 * 1
 * 13
 * 000001101
 * 3
 * 
 * 
 * 2
 * 13
 * 000001101
 * 1
 * 
 * 
 * 2
 * 233
 * 011101001
 * 3
 * 
 * 
 * 3
 * 13
 * 000001101
 * 1
 * 
 * 
 * 3
 * 362
 * 101101010
 * 2
 * 
 * 
 * 
 * 
 * The accumulated price of num is the total price of numbers from 1 to num.
 * num is considered cheap if its accumulated price is less than or equal to
 * k.
 * 
 * Return the greatest cheap number.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: k = 9, x = 1
 * 
 * Output: 6
 * 
 * Explanation:
 * 
 * As shown in the table below, 6 is the greatest cheap
 * number.
 * 
 * 
 * 
 * 
 * x
 * num
 * Binary Representation
 * Price
 * Accumulated Price
 * 
 * 
 * 1
 * 1
 * 001
 * 1
 * 1
 * 
 * 
 * 1
 * 2
 * 010
 * 1
 * 2
 * 
 * 
 * 1
 * 3
 * 011
 * 2
 * 4
 * 
 * 
 * 1
 * 4
 * 100
 * 1
 * 5
 * 
 * 
 * 1
 * 5
 * 101
 * 2
 * 7
 * 
 * 
 * 1
 * 6
 * 110
 * 2
 * 9
 * 
 * 
 * 1
 * 7
 * 111
 * 3
 * 12
 * 
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: k = 7, x = 2
 * 
 * Output: 9
 * 
 * Explanation:
 * 
 * As shown in the table below, 9 is the greatest cheap
 * number.
 * 
 * 
 * 
 * 
 * x
 * num
 * Binary Representation
 * Price
 * Accumulated Price
 * 
 * 
 * 2
 * 1
 * 0001
 * 0
 * 0
 * 
 * 
 * 2
 * 2
 * 0010
 * 1
 * 1
 * 
 * 
 * 2
 * 3
 * 0011
 * 1
 * 2
 * 
 * 
 * 2
 * 4
 * 0100
 * 0
 * 2
 * 
 * 
 * 2
 * 5
 * 0101
 * 0
 * 2
 * 
 * 
 * 2
 * 6
 * 0110
 * 1
 * 3
 * 
 * 
 * 2
 * 7
 * 0111
 * 1
 * 4
 * 
 * 
 * 2
 * 8
 * 1000
 * 1
 * 5
 * 
 * 
 * 2
 * 9
 * 1001
 * 1
 * 6
 * 
 * 
 * 2
 * 10
 * 1010
 * 2
 * 8
 * 
 * 
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= 10^15
 * 1 <= x <= 8
 * 
 * 
 */

// @lc code=start
class Solution {
    public long findMaximumNumber(long k, int x) {
        long low = 1;
        long high = Long.MAX_VALUE;
        while(low + 1 < high){
            long mid = low + (high - low) / 2;
            if(isLowerOrEqual(mid, x, k)){
                low = mid;
            }
            else{
                high = mid;
            }
        }
        if(isLowerOrEqual(high, x, k)){
            return high - 1;
        }
        return low - 1;
    }

    boolean isLowerOrEqual(long t, int x, long k){
        long ans = 0;
        for(int i = 1; i * x < 64; i++){
            k -= getOnes(t, i * x);
            if(k < 0){
                return false;
            }
        }
        return true;
    }

    long getOnes(long t, int x){
        long ret = t / (1L << x) * (1L << (x - 1)) + Math.max(0, t % (1L << x) - (1L << (x - 1)));
        // System.out.println(Arrays.asList(t, x, ret));
        return ret;
    }
}
// @lc code=end
