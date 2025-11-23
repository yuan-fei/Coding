/*
 * @lc app=leetcode id=3181 lang=java
 *
 * [3181] Maximum Total Reward Using Operations II
 *
 * https://leetcode.com/problems/maximum-total-reward-using-operations-ii/description/
 *
 * algorithms
 * Hard (20.76%)
 * Likes:    125
 * Dislikes: 32
 * Total Accepted:    7.7K
 * Total Submissions: 37.3K
 * Testcase Example:  '[1,1,3,3]'
 *
 * You are given an integer array rewardValues of length n, representing the
 * values of rewards.
 * 
 * Initially, your total reward x is 0, and all indices are unmarked. You are
 * allowed to perform the following operation any number of times:
 * 
 * 
 * Choose an unmarked index i from the range [0, n - 1].
 * If rewardValues[i] is greater than your current total reward x, then add
 * rewardValues[i] to x (i.e., x = x + rewardValues[i]), and mark the index
 * i.
 * 
 * 
 * Return an integer denoting the maximum total reward you can collect by
 * performing the operations optimally.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: rewardValues = [1,1,3,3]
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * During the operations, we can choose to mark the indices 0 and 2 in order,
 * and the total reward will be 4, which is the maximum.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: rewardValues = [1,6,4,3,2]
 * 
 * Output: 11
 * 
 * Explanation:
 * 
 * Mark the indices 0, 2, and 1 in order. The total reward will then be 11,
 * which is the maximum.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= rewardValues.length <= 5 * 10^4
 * 1 <= rewardValues[i] <= 5 * 10^4
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int maxTotalReward(int[] rewardValues) {
        // dp[i][j] = dp[i - 1][j − rewardValues[i]] if j − rewardValues[i] <
        // rewardValues[i]
        Arrays.sort(rewardValues);
        System.out.println(Arrays.toString(rewardValues));
        BitSet b = new BitSet(rewardValues[rewardValues.length - 1] * 2 + 1);
        b.set(0);
        for (int r : rewardValues) {
            b = b.fromLowestBits(r).shiftLeft(r).or(b);
            // System.out.println(Arrays.toString(b.sections));
        }
        return b.highestBit();
    }

    class BitSet {
        long[] sections;

        public BitSet(int size) {
            sections = new long[(size + 63) / 64];
        }

        public BitSet(long[] sections) {
            this.sections = sections;
        }

        void set(int bit) {
            int sectionId = bit / 64;
            int offs = bit % 64;
            sections[sectionId] |= 1L << offs;
        }

        int get(int bit) {
            int sectionId = bit / 64;
            int offs = bit % 64;
            return (int) ((sections[sectionId] >> offs) & 1);
        }

        BitSet fromLowestBits(int length) {
            int sectionCount = length / 64;
            int offset = length % 64;
            long[] resultSections = new long[sections.length];
            for (int i = 0; i < sections.length && i < sectionCount; i++) {
                resultSections[i] = sections[i];
            }
            resultSections[sectionCount] = sections[sectionCount] & ((1L << offset) - 1);
            return new BitSet(resultSections);
        }

        BitSet or(BitSet other) {
            long[] resultSections = new long[sections.length];
            for (int i = 0; i < sections.length; i++) {
                resultSections[i] = sections[i] | other.sections[i];
            }
            return new BitSet(resultSections);
        }

        BitSet shiftLeft(int length) {
            // 110, 010
            int sectionWiseShiftLength = length / 64;
            int adjacentSectionShiftLength = length % 64;
            long[] resultSections = new long[sections.length];
            for (int i = sectionWiseShiftLength; i < sections.length; i++) {
                resultSections[i] = sections[i - sectionWiseShiftLength];
            }
            if (adjacentSectionShiftLength > 0) {
                for (int i = sections.length - 1; i >= 0; i--) {
                    resultSections[i] <<= adjacentSectionShiftLength;
                    if (i >= 1) {
                        resultSections[i] |= resultSections[i - 1] >>> (64 - adjacentSectionShiftLength);
                    }
                }
            }

            return new BitSet(resultSections);
        }

        int highestBit() {
            for (int i = sections.length * 64 - 1; i >= 0; i--) {
                if (get(i) == 1) {
                    return i;
                }
            }
            return -1;
        }
    }
}
// @lc code=end
