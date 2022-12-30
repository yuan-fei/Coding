/*
 * @lc app=leetcode id=793 lang=java
 *
 * [793] Preimage Size of Factorial Zeroes Function
 *
 * https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/description/
 *
 * algorithms
 * Hard (42.92%)
 * Likes:    356
 * Dislikes: 82
 * Total Accepted:    14.5K
 * Total Submissions: 33.8K
 * Testcase Example:  '0'
 *
 * Let f(x) be the number of zeroes at the end of x!. Recall that x! = 1 * 2 *
 * 3 * ... * x and by convention, 0! = 1.
 * 
 * 
 * For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) =
 * 2 because 11! = 39916800 has two zeroes at the end.
 * 
 * 
 * Given an longeger k, return the number of non-negative longegers x have the
 * property that f(x) = k.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: k = 0
 * Output: 5
 * Explanation: 0!, 1!, 2!, 3!, and 4! end with k = 0 zeroes.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: k = 5
 * Output: 0
 * Explanation: There is no x such that x! ends in k = 5 zeroes.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: k = 3
 * Output: 5
 * 
 * 
 * 
 * Constralongs:
 * 
 * 
 * 0 <= k <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int preimageSizeFZF(int k) {
        long ub = ub(k);
        if(ub == -1){
            return 0;
        }
        long lb = lb(k);
        System.out.println(Arrays.asList(lb, ub));
        return (int)(ub - lb) + 1;
    }

    long lb(long k){
        long low = 0;
        long high = 4L * Integer.MAX_VALUE;
        while(low + 1 < high){
            long mid = low + (high - low) / 2;
            long cnt = countTrailingZeros(mid);
            if(cnt >= k){
                high = mid;
            }
            else{
                low = mid;
            }
        }
        if(countTrailingZeros(low) == k){
            return low;
        }
        else if(countTrailingZeros(high) == k){
            return high;
        }
        else{
            return -1;
        }
    }

    long ub(long k){
        long low = 0;
        long high = 4L * Integer.MAX_VALUE;
        while(low + 1 < high){
            long mid = low + (high - low) / 2;
            long cnt = countTrailingZeros(mid);
            if(cnt > k){
                high = mid;
            }
            else{
                low = mid;
            }
        }
        if(countTrailingZeros(high) == k){
            return high;
        }
        else if(countTrailingZeros(low) == k){
            return low;
        }
        else{
            return -1;
        }
    }

    long countTrailingZeros(long n){
        long cnt = 0;
        long factor = 5;
        while(true){
            if(n / factor == 0){
                break;
            }
            else{
                cnt += n / factor;
                factor *= 5;
            }
        }
        System.out.println(Arrays.asList(n, cnt));
        return cnt;
    }
}
// @lc code=end
