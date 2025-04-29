/*
 * @lc app=leetcode id=3145 lang=java
 *
 * [3145] Find Products of Elements of Big Array
 *
 * https://leetcode.com/problems/find-products-of-elements-of-big-array/description/
 *
 * algorithms
 * Hard (22.79%)
 * Likes:    58
 * Dislikes: 15
 * Total Accepted:    2.4K
 * Total Submissions: 11.2K
 * Testcase Example:  '[[1,3,7]]'
 *
 * The powerful array of a non-negative integer x is defined as the shortest
 * sorted array of powers of two that sum up to x. The table below illustrates
 * examples of how the powerful array is determined. It can be proven that the
 * powerful array of x is unique.
 * 
 * 
 * 
 * 
 * num
 * Binary Representation
 * powerful array
 * 
 * 
 * 1
 * 00001
 * [1]
 * 
 * 
 * 8
 * 01000
 * [8]
 * 
 * 
 * 10
 * 01010
 * [2, 8]
 * 
 * 
 * 13
 * 01101
 * [1, 4, 8]
 * 
 * 
 * 23
 * 10111
 * [1, 2, 4, 16]
 * 
 * 
 * 
 * 
 * The array big_nums is created by concatenating the powerful arrays for every
 * positive integer i in ascending order: 1, 2, 3, and so on. Thus, big_nums
 * begins as [1, 2, 1, 2, 4, 1, 4, 2, 4, 1, 2, 4, 8, ...].
 * 
 * You are given a 2D integer matrix queries, where for queries[i] = [fromi,
 * toi, modi] you should calculate (big_nums[fromi] * big_nums[fromi + 1] * ...
 * * big_nums[toi]) % modi.
 * 
 * Return an integer array answer such that answer[i] is the answer to the i^th
 * query.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: queries = [[1,3,7]]
 * 
 * Output: [4]
 * 
 * Explanation:
 * 
 * There is one query.
 * 
 * big_nums[1..3] = [2,1,2]. The product of them is 4. The result is 4 % 7 =
 * 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: queries = [[2,5,3],[7,7,4]]
 * 
 * Output: [2,2]
 * 
 * Explanation:
 * 
 * There are two queries.
 * 
 * First query: big_nums[2..5] = [1,2,4,1]. The product of them is 8. The
 * result is 8 % 3 = 2.
 * 
 * Second query: big_nums[7] = 2. The result is 2 % 4 = 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= queries.length <= 500
 * queries[i].length == 3
 * 0 <= queries[i][0] <= queries[i][1] <= 10^15
 * 1 <= queries[i][2] <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int[] findProductsOfElements(long[][] queries) {
        return IntStream.range(0, queries.length)
                .mapToLong(i -> findProductsOfElements(queries[i][0], queries[i][1],
                        queries[i][2]))
                .mapToInt(i -> (int) i).toArray();
        // System.out.println(Arrays.toString(getNumberOfOnesUnder(1)));
        // return new int[0];
    }

    long getNumberOfOnesUnder(long n, int b) {
        // 2^i ones in every 2^(i + 1) numbers
        n++;
        return n / (1L << (b + 1)) * (1L << b) + Math.max(0, (n % (1L << (b + 1))) - (1L << b));
    }

    long[] getNumberOfOnesUnder(long n) {
        return IntStream.range(0, 52).mapToLong(i -> getNumberOfOnesUnder(n, i)).toArray();
    }

    long getNearestNumberWithTotalBits(long bitCount) {
        long low = 0;
        long high = 1L << 52;
        while (low + 1 < high) {
            long mid = (low + high) / 2;
            long[] bits = getNumberOfOnesUnder(mid);

            if (bitCount < Arrays.stream(bits).sum()) {
                high = mid;
            } else {
                low = mid;
            }
        }
        long[] bits = getNumberOfOnesUnder(high);
        if (bitCount >= Arrays.stream(bits).sum()) {
            return high;
        } else {
            return low;
        }
    }

    long[] getBitCountArrays(long bitCount) {
        long n = getNearestNumberWithTotalBits(bitCount);
        // System.out.println(Arrays.asList(bitCount, n));
        long[] bits = getNumberOfOnesUnder(n);
        long sum = Arrays.stream(bits).sum();
        long next = n + 1;
        int bit = 0;
        while (sum < bitCount && next > 0) {
            sum += next % 2;
            bits[bit] += next % 2;
            next /= 2;
            bit++;
        }
        return bits;
    }

    long findProductsOfElements(long start, long end, long mod) {
        long[] bitsEnd = getBitCountArrays(end + 1);
        long[] bitsStart = getBitCountArrays(start);
        // System.out.println(Arrays.toString(bitsEnd));
        // System.out.println(Arrays.toString(bitsStart));
        long total = 0;
        for (int i = 0; i < bitsEnd.length; i++) {
            total += i * (bitsEnd[i] - bitsStart[i]);
        }
        return modularExpRecursive(2, total, mod);
    }

    long modularExpRecursive(int base, long exp, long m) {
        if (exp == 0) {
            return 1 % m;
        } else if (exp % 2 == 1) {
            long l = base * modularExpRecursive(base, exp - 1, m);
            return l % m;
        } else {
            long t = modularExpRecursive(base, exp / 2, m);
            return (t * t) % m;
        }
    }

}
// @lc code=end
