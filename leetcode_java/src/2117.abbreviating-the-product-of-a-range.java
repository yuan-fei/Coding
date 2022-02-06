/*
 * @lc app=leetcode id=2117 lang=java
 *
 * [2117] Abbreviating the Product of a Range
 *
 * https://leetcode.com/problems/abbreviating-the-product-of-a-range/description/
 *
 * algorithms
 * Hard (24.64%)
 * Likes:    25
 * Dislikes: 28
 * Total Accepted:    845
 * Total Submissions: 3.4K
 * Testcase Example:  '1\n4'
 *
 * You are given two positive integers left and right with left <= right.
 * Calculate the product of all integers in the inclusive range [left, right].
 * 
 * Since the product may be very large, you will abbreviate it following these
 * steps:
 * 
 * 
 * Count all trailing zeros in the product and remove them. Let us denote this
 * count as C.
 * 
 * 
 * For example, there are 3 trailing zeros in 1000, and there are 0 trailing
 * zeros in 546.
 * 
 * 
 * Denote the remaining number of digits in the product as d. If d > 10, then
 * express the product as <pre>...<suf> where <pre> denotes the first 5 digits
 * of the product, and <suf> denotes the last 5 digits of the product after
 * removing all trailing zeros. If d <= 10, we keep it unchanged.
 * 
 * For example, we express 1234567654321 as 12345...54321, but 1234567 is
 * represented as 1234567.
 * 
 * 
 * Finally, represent the product as a string "<pre>...<suf>eC".
 * 
 * For example, 12345678987600000 will be represented as
 * "12345...89876e5".
 * 
 * 
 * 
 * 
 * Return a string denoting the abbreviated product of all integers in the
 * inclusive range [left, right].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: left = 1, right = 4
 * Output: "24e0"
 * Explanation:
 * The product is 1 × 2 × 3 × 4 = 24.
 * There are no trailing zeros, so 24 remains the same. The abbreviation will
 * end with "e0".
 * Since the number of digits is 2, which is less than 10, we do not have to
 * abbreviate it further.
 * Thus, the final representation is "24e0". 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: left = 2, right = 11
 * Output: "399168e2"
 * Explanation:
 * The product is 39916800.
 * There are 2 trailing zeros, which we remove to get 399168. The abbreviation
 * will end with "e2".
 * The number of digits after removing the trailing zeros is 6, so we do not
 * abbreviate it further.
 * Hence, the abbreviated product is "399168e2".  
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: left = 999998, right = 1000000
 * Output: "99999...00002e6"
 * Explanation:
 * The above diagram shows how we abbreviate the product to "99999...00002e6".
 * - It has 6 trailing zeros. The abbreviation will end with "e6".
 * - The first 5 digits are 99999.
 * - The last 5 digits after removing trailing zeros is 00002.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= left <= right <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public static final long LIMIT = 1_000_000_000_000l; // keep start/end smaller than 10^12
    
    public String abbreviateProduct(int left, int right) {
        long end = 1; // end part of the product (or the whole number if small enough)
        long nZeros = 0; // trailing zeros
        boolean usedModulo = false;
        for (int n = left; n <= right; n++) {
            end *= n;

            while (end % 10 == 0) { // extract trailing zeros into nZeros
                end /= 10;
                nZeros++;
            }

            if (end >= LIMIT) { // truncate if needed
                end %= LIMIT;
                usedModulo = true;
            }
        }

        if (!usedModulo && end < 10_000_000_000l) { // doesn't need abbreviation below 10^10
            return String.format("%de%d", end, nZeros);
        }

        double start = 1; // start part of the product
        for (int n = left; n <= right; n++) {
            start *= n;

            while (start >= LIMIT) { // truncate if needed
                start /= 10;
            }
        }

        return buildAbbreviation(usedModulo, start, end, nZeros);
    }

    private String buildAbbreviation(boolean usedModulo, double start, long end, long nZeros) {
        while (start >= 100_000) { // keep the 5 first digits
            start /= 10;
        }

        end %= 100_000; // keep the last 5 digits

        return String.format("%d...%05de%d", (int) start, end, nZeros); // zero-padding of the end
    }
}
// @lc code=end
