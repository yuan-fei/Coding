/*
 * @lc app=leetcode id=2528 lang=java
 *
 * [2528] Maximize the Minimum Powered City
 *
 * https://leetcode.com/problems/maximize-the-minimum-powered-city/description/
 *
 * algorithms
 * Hard (25.44%)
 * Likes:    132
 * Dislikes: 2
 * Total Accepted:    1.9K
 * Total Submissions: 7.4K
 * Testcase Example:  '[1,2,4,5,0]\n1\n2'
 *
 * You are given a 0-indexed integer array stations of length n, where
 * stations[i] represents the number of power stations in the i^th city.
 * 
 * Each power station can provide power to every city in a fixed range. In
 * other words, if the range is denoted by r, then a power station at city i
 * can provide power to all cities j such that |i - j| <= r and 0 <= i, j <= n
 * - 1.
 * 
 * 
 * Note that |x| denotes absolute value. For example, |7 - 5| = 2 and |3 - 10|
 * = 7.
 * 
 * 
 * The power of a city is the total number of power stations it is being
 * provided power from.
 * 
 * The government has sanctioned building k more power stations, each of which
 * can be built in any city, and have the same range as the pre-existing ones.
 * 
 * Given the two integers r and k, return the maximum possible minimum power of
 * a city, if the additional power stations are built optimally.
 * 
 * Note that you can build the k power stations in multiple cities.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: stations = [1,2,4,5,0], r = 1, k = 2
 * Output: 5
 * Explanation: 
 * One of the optimal ways is to install both the power stations at city 1. 
 * So stations will become [1,4,4,5,0].
 * - City 0 is provided by 1 + 4 = 5 power stations.
 * - City 1 is provided by 1 + 4 + 4 = 9 power stations.
 * - City 2 is provided by 4 + 4 + 5 = 13 power stations.
 * - City 3 is provided by 5 + 4 = 9 power stations.
 * - City 4 is provided by 5 + 0 = 5 power stations.
 * So the minimum power of a city is 5.
 * Since it is not possible to obtain a larger power, we return 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: stations = [4,4,4,4], r = 0, k = 3
 * Output: 4
 * Explanation: 
 * It can be proved that we cannot make the minimum power of a city greater
 * than 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == stations.length
 * 1 <= n <= 10^5
 * 0 <= stations[i] <= 10^5
 * 0 <= r <= n - 1
 * 0 <= k <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] sum = new long[n];
        for(int i = 0; i <= r; i++){
            sum[0] += stations[i];
        }
        for(int i = 1; i < n; i++){
            sum[i] = sum[i - 1];
            if(i + r < n){
                 sum[i] += stations[i + r];
            }
            if(i - r - 1 >= 0){
                sum[i] -= stations[i - r - 1];
            }
        }
        // System.out.println(Arrays.toString(sum));
        long low = 0;
        long high = (long)1e10 + (long)1e9 + 1;
        while(low + 1 < high){
            long mid = (high + low) / 2;
            // System.out.println(mid);
            if(check(sum, r, k, mid)){
                low = mid;
            }
            else{
                high = mid;
            }
        }
        if(check(sum, r, k, high)){
            return high;
        }
        return low;
    }

    boolean check(long[] sum, int r, long k, long min){

        int n = sum.length;
        long[] offset = new long[n + 1];
        for(int i = 0; i < n; i++){
            if(i > 0){
                offset[i] += offset[i - 1];    
            }
            long diff = Math.max(0, min - sum[i] + offset[i]);
            k -= diff;
            if(k < 0){
                return false;
            }
            if(i + 1 < n){
                offset[i + 1] -= diff;
                if(i + 2 * r + 1 <= n){
                    offset[i + 2 * r + 1] += diff;
                }
            }
        }
        return true;
    }
}
// @lc code=end
