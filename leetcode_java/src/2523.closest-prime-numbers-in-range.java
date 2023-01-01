/*
 * @lc app=leetcode id=2523 lang=java
 *
 * [2523] Closest Prime Numbers in Range
 *
 * https://leetcode.com/problems/closest-prime-numbers-in-range/description/
 *
 * algorithms
 * Medium (35.11%)
 * Likes:    105
 * Dislikes: 10
 * Total Accepted:    7.7K
 * Total Submissions: 22.1K
 * Testcase Example:  '10\n19'
 *
 * Given two positive integers left and right, find the two integers num1 and
 * num2 such that:
 * 
 * 
 * left <= nums1 < nums2 <= right .
 * nums1 and nums2 are both prime numbers.
 * nums2 - nums1 is the minimum amongst all other pairs satisfying the above
 * conditions.
 * 
 * 
 * Return the positive integer array ans = [nums1, nums2]. If there are
 * multiple pairs satisfying these conditions, return the one with the minimum
 * nums1 value or [-1, -1] if such numbers do not exist.
 * 
 * A number greater than 1 is called prime if it is only divisible by 1 and
 * itself.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: left = 10, right = 19
 * Output: [11,13]
 * Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
 * The closest gap between any pair is 2, which can be achieved by [11,13] or
 * [17,19].
 * Since 11 is smaller than 17, we return the first pair.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: left = 4, right = 6
 * Output: [-1,-1]
 * Explanation: There exists only one prime number in the given range, so the
 * conditions cannot be satisfied.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= left <= right <= 10^6
 * 
 * 
 * 
 * .spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px
 * 0px; font-size:150%; font-weight: bold; color:#000000;
 * background-color:cyan; outline:0; 
 * }
 * .spoiler {overflow:hidden;}
 * .spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s
 * ease;-o-transition: all 0s ease;transition: margin 0s ease;}
 * .spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
 * .spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] closestPrimes(int left, int right) {
        int MAX = (int)1e6;
        List<Integer> primes = sieve(MAX);
        // System.out.println(primes);
        int[] ans = {-1, -1};
        int minGap = right - left + 1;
        int last = -1;
        for(int i = 0; i < primes.size(); i++){
            if(left <= primes.get(i) && primes.get(i) <= right){
                if(last != -1){
                    if(primes.get(i) - last < minGap){
                        minGap = primes.get(i) - last;
                        ans = new int[]{last, primes.get(i)};
                    }
                }
                last = primes.get(i);
            }
        }
        return ans;
    }

    List<Integer> sieve(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isComposite = new boolean[n + 1];
        for (int i = 2; i < isComposite.length; i++) {
            if (!isComposite[i]) {
                primes.add(i);
            }
            for (long j = 1L * i * i; j < isComposite.length; j += i) {
                isComposite[(int)j] = true;
            }
        }

        return primes;
    }
}
// @lc code=end
