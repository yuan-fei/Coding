/*
 * @lc app=leetcode id=2478 lang=java
 *
 * [2478] Number of Beautiful Partitions
 *
 * https://leetcode.com/problems/number-of-beautiful-partitions/description/
 *
 * algorithms
 * Hard (20.80%)
 * Likes:    49
 * Dislikes: 6
 * Total Accepted:    1.9K
 * Total Submissions: 8.9K
 * Testcase Example:  '"23542185131"\n3\n2'
 *
 * You are given a string s that consists of the digits '1' to '9' and two
 * integers k and minLength.
 * 
 * A partition of s is called beautiful if:
 * 
 * 
 * s is partitioned into k non-intersecting substrings.
 * Each substring has a length of at least minLength.
 * Each substring starts with a prime digit and ends with a non-prime digit.
 * Prime digits are '2', '3', '5', and '7', and the rest of the digits are
 * non-prime.
 * 
 * 
 * Return the number of beautiful partitions of s. Since the answer may be very
 * large, return it modulo 10^9 + 7.
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "23542185131", k = 3, minLength = 2
 * Output: 3
 * Explanation: There exists three ways to create a beautiful partition:
 * "2354 | 218 | 5131"
 * "2354 | 21851 | 31"
 * "2354218 | 51 | 31"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "23542185131", k = 3, minLength = 3
 * Output: 1
 * Explanation: There exists one way to create a beautiful partition: "2354 |
 * 218 | 5131".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "3312958", k = 3, minLength = 1
 * Output: 1
 * Explanation: There exists one way to create a beautiful partition: "331 | 29
 * | 58".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k, minLength <= s.length <= 1000
 * s consists of the digits '1' to '9'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int beautifulPartitions(String s, int k, int minLength) {
        int MOD = (int)1e9 + 7;
        Set<Character> primes = new HashSet<>(Arrays.asList('2', '3', '5', '7'));
        if(s.length() == 1 || !primes.contains(s.charAt(0))){
            return 0;
        }
        Deque<int[]> q = new ArrayDeque<>();
        int[] initial = new int[k + 1];
        initial[0] = 1;
        for(int i = 0; i < minLength; i++){
            q.offerLast(initial);
        }
        
        int[] lastGood = Arrays.copyOf(initial, k + 1);
        for(int i = minLength - 1; i < s.length(); i++){
            int[] first = q.pollFirst();
            if(i - minLength >= 0 && !primes.contains(s.charAt(i - minLength)) && primes.contains(s.charAt(i - minLength + 1))){
                for(int j = 1; j < lastGood.length; j++){
                    lastGood[j] += first[j];
                    lastGood[j] %= MOD;
                }
            }
            int[] newState = Arrays.copyOf(initial, k + 1);
            if(!primes.contains(s.charAt(i))){
                for(int j = 1; j < newState.length; j++){
                    newState[j] += lastGood[j - 1];
                    newState[j] %= MOD;
                }    
            }
            // System.out.println(s.charAt(i) + "=" +Arrays.toString(newState) + ", " + Arrays.toString(lastGood));
            q.offerLast(newState);
        }
        return q.peekLast()[k];
    }
}
// @lc code=end
