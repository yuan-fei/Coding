/*
 * @lc app=leetcode id=2572 lang=java
 *
 * [2572] Count the Number of Square-Free Subsets
 *
 * https://leetcode.com/problems/count-the-number-of-square-free-subsets/description/
 *
 * algorithms
 * Medium (9.87%)
 * Likes:    38
 * Dislikes: 32
 * Total Accepted:    1K
 * Total Submissions: 11.1K
 * Testcase Example:  '[3,4,4,5]'
 *
 * You are given a positive integer 0-indexed array nums.
 * 
 * A subset of the array nums is square-free if the product of its elements is
 * a square-free integer.
 * 
 * A square-free integer is an integer that is divisible by no square number
 * other than 1.
 * 
 * Return the number of square-free non-empty subsets of the array nums. Since
 * the answer may be too large, return it modulo 10^9 + 7.
 * 
 * A non-empty subset of nums is an array that can be obtained by deleting some
 * (possibly none but not all) elements from nums. Two subsets are different if
 * and only if the chosen indices to delete are different.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,4,4,5]
 * Output: 3
 * Explanation: There are 3 square-free subsets in this example:
 * - The subset consisting of the 0^th element [3]. The product of its elements
 * is 3, which is a square-free integer.
 * - The subset consisting of the 3^rd element [5]. The product of its elements
 * is 5, which is a square-free integer.
 * - The subset consisting of 0^th and 3^rd elements [3,5]. The product of its
 * elements is 15, which is a square-free integer.
 * It can be proven that there are no more than 3 square-free subsets in the
 * given array.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1]
 * Output: 1
 * Explanation: There is 1 square-free subset in this example:
 * - The subset consisting of the 0^th element [1]. The product of its elements
 * is 1, which is a square-free integer.
 * It can be proven that there is no more than 1 square-free subset in the
 * given array.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 30
 * 
 * 
 */

// @lc code=start
class Solution {
    int[] good = {1, 2, 3, 5, 6, 7, 10, 11, 13, 14, 15, 17, 19, 21, 22, 23, 26, 29, 30};
    public int squareFreeSubsets(int[] nums) {
        int n = good.length;
        Map<Integer, int[]> goodSet = IntStream.range(0, n).boxed().collect(Collectors.toMap(i -> good[i], i -> new int[]{i, getBitMap(good[i])}, (a, b) -> a));
        int[] cnt = new int[good.length];
        Arrays.stream(nums).filter(x -> goodSet.containsKey(x)).forEach(x -> cnt[goodSet.get(x)[0]]++);
        // System.out.println(Arrays.toString(cnt));
        long ans = 0;
        long cnt1 = pow(cnt[0]) - 1;
        for(int m = 1; m < (1 << n); m++){
            int bits = 0;
            boolean bad = false;
            for(int i = 0; i < n; i++){
                if(((m >> i) & 1) != 0){
                    if((bits & goodSet.get(good[i])[1]) != 0){
                        bad = true;
                        break;
                    }
                    else{
                        bits |= goodSet.get(good[i])[1];
                    }
                }
            }
            
            if(!bad){
                long t = 1;
                for(int i = 0; i < n; i++){
                    if(((m >> i) & 1) != 0){
                        t *= (i == 0) ? cnt1 : cnt[i];
                        t %= MOD;
                    }
                }
                ans += t;
                ans %= MOD;
                if(t > 0){
                    // System.out.println(m + ", " + t);    
                }
            }
            
        }
        return (int)ans;
    }
    long MOD = (long)1e9 + 7;
    long pow(long exp){
        if(exp == 0){
            return 1;
        }
        else if(exp % 2 == 0){
            long t = pow(exp / 2);
            return (t * t) % MOD;
        }
        else{
            return (pow(exp - 1) << 1) % MOD;
        }
    }
    int getBitMap(int x){
        int ret = 0;
        for(int i : good){
            if(i > 1 && x % i == 0){
                ret |= 1 << i;
            }
        }
        // System.out.println(x + ", " + ret);
        return ret;
    }
}
// @lc code=end
