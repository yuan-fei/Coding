/*
 * @lc app=leetcode id=2801 lang=java
 *
 * [2801] Count Stepping Numbers in Range
 *
 * https://leetcode.com/problems/count-stepping-numbers-in-range/description/
 *
 * algorithms
 * Hard (26.63%)
 * Likes:    322
 * Dislikes: 10
 * Total Accepted:    7.2K
 * Total Submissions: 26.5K
 * Testcase Example:  '"1"\n"11"'
 *
 * Given two positive integers low and high represented as strings, find the
 * count of stepping numbers in the inclusive range [low, high].
 * 
 * A stepping number is an integer such that all of its adjacent digits have an
 * absolute difference of exactly 1.
 * 
 * Return an integer denoting the count of stepping numbers in the inclusive
 * range [low, high]. 
 * 
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * Note: A stepping number should not have a leading zero.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: low = "1", high = "11"
 * Output: 10
 * Explanation: The stepping numbers in the range [1,11] are 1, 2, 3, 4, 5, 6,
 * 7, 8, 9 and 10. There are a total of 10 stepping numbers in the range.
 * Hence, the output is 10.
 * 
 * Example 2:
 * 
 * 
 * Input: low = "90", high = "101"
 * Output: 2
 * Explanation: The stepping numbers in the range [90,101] are 98 and 101.
 * There are a total of 2 stepping numbers in the range. Hence, the output is
 * 2. 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= int(low) <= int(high) < 10^100
 * 1 <= low.length, high.length <= 100
 * low and high consist of only digits.
 * low and high don't have any leading zeros.
 * 
 * 
 */

// @lc code=start
class Solution {
    int MOD = (int)1e9 + 7;
    int[][][][] cache;
    public int countSteppingNumbers(String low, String high) {
        return (MOD + countSteppingNumbersLessOrEqual(high) - countSteppingNumbersLessOrEqual(low) + (isSteppingNumber(low) ? 1 : 0)) % MOD;
    }

    boolean isSteppingNumber(String s){
        return IntStream.range(0, s.length() - 1).allMatch(i -> Math.abs(s.charAt(i) - s.charAt(i + 1)) == 1);
    }

    int countSteppingNumbersLessOrEqual(String s){
        cache = new int[101][10][2][2];
        for(int i = 0; i < cache.length; i++){
            for(int j = 0; j < cache[i].length; j++){
                for(int k = 0; k < cache[i][j].length; k++){
                    Arrays.fill(cache[i][j][k], -1);
                }
            }
        }
        return countSteppingNumbersLessOrEqual(s, 0, 0, true, true);
    }

    int countSteppingNumbersLessOrEqual(String s, int pos, int lastDigit, boolean isTight, boolean hasLeadingZero){
        if(pos == s.length()){
            return 1;
        }
        if(cache[pos][lastDigit][isTight ? 1 : 0][hasLeadingZero ? 1 : 0] == -1){
            int ub = isTight ? s.charAt(pos) - '0': 9;
            IntStream is = hasLeadingZero ? IntStream.rangeClosed(0, ub) 
                                : IntStream.of(lastDigit - 1, lastDigit + 1).filter(x -> 0 <= x && x <= ub);
            cache[pos][lastDigit][isTight ? 1 : 0][hasLeadingZero ? 1 : 0] = is.map(curDigit -> 
                countSteppingNumbersLessOrEqual(s, pos + 1, curDigit, isTight && (curDigit == ub), hasLeadingZero && (curDigit == 0))
            ).reduce(0, (acc, cur) -> (acc + cur) % MOD);
        }
        return cache[pos][lastDigit][isTight ? 1 : 0][hasLeadingZero ? 1 : 0];
    }
}
// @lc code=end
