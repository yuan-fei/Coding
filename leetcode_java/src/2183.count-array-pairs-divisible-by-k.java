/*
 * @lc app=leetcode id=2183 lang=java
 *
 * [2183] Count Array Pairs Divisible by K
 *
 * https://leetcode.com/problems/count-array-pairs-divisible-by-k/description/
 *
 * algorithms
 * Hard (11.09%)
 * Likes:    44
 * Dislikes: 6
 * Total Accepted:    1.2K
 * Total Submissions: 12.4K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * Given a 0-indexed integer array nums of length n and an integer k, return
 * the number of pairs (i, j) such that:
 * 
 * 
 * 0 <= i < j <= n - 1 and
 * nums[i] * nums[j] is divisible by k.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 7
 * Explanation: 
 * The 7 pairs of indices whose corresponding products are divisible by 2 are
 * (0, 1), (0, 3), (1, 2), (1, 3), (1, 4), (2, 3), and (3, 4).
 * Their products are 2, 4, 6, 8, 10, 12, and 20 respectively.
 * Other pairs such as (0, 2) and (2, 4) have products 3 and 15 respectively,
 * which are not divisible by 2.    
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4], k = 5
 * Output: 0
 * Explanation: There does not exist any pair of indices whose corresponding
 * product is divisible by 5.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i], k <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public long countPairs(int[] nums, int k) {
        Set<Integer> factors = getFactors(k);
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int x : factors){
            cnt.put(x, 0);
        }
        
        long ret = 0;
        for(int i = 0; i < nums.length; i++){
            int g = gcd(nums[i], k);
            ret += cnt.getOrDefault(k / g, 0);
            for(int x : cnt.keySet()){
                if(nums[i] % x == 0){
                    cnt.put(x, cnt.getOrDefault(x, 0) + 1);
                }
            }
        }
        return ret;
    }

    Set<Integer> getFactors(int n){
        Set<Integer> ts = new HashSet<>();
        for(int i = 1; i * i <= n; i++){
            if(n % i == 0){
                ts.add(i);
                ts.add(n / i);
            }
        }
        return ts;
    }

    int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }
}
// @lc code=end
