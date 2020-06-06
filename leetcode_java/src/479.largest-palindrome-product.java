/*
 * @lc app=leetcode id=479 lang=java
 *
 * [479] Largest Palindrome Product
 *
 * https://leetcode.com/problems/largest-palindrome-product/description/
 *
 * algorithms
 * Hard (28.70%)
 * Likes:    87
 * Dislikes: 1304
 * Total Accepted:    16.7K
 * Total Submissions: 58K
 * Testcase Example:  '1'
 *
 * Find the largest palindrome made from the product of two n-digit numbers.
 * 
 * Since the result could be very large, you should return the largest
 * palindrome mod 1337.
 * 
 * 
 * 
 * Example:
 * 
 * Input: 2
 * 
 * Output: 987
 * 
 * Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
 * 
 * 
 * 
 * Note:
 * 
 * The range of n is [1,8].
 * 
 */

// @lc code=start
class Solution {
    public int largestPalindrome(int n) {
        long max = (long)Math.pow(10, n) - 1;
	    long min = max / 10;
	        
	    for (long h = max; h > min; h--) {
	        long left = h, right = 0;
	        for (long i = h; i != 0; right = right * 10 + i % 10, i /= 10, left *= 10);
	        long palindrom = left + right;      // construct the palindrome
	            
	        for (long i = max; i > min; i--) {
	            long j = palindrom / i;
	            if (j > i) break;     // terminate if the other number is greater than current number
	            if (palindrom % i == 0) return (int)(palindrom % 1337); // found if current number is a factor
	        }
	    }

	    return 9;    // account for case n = 1
    }
}
// @lc code=end
