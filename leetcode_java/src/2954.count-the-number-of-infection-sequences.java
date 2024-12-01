/*
 * @lc app=leetcode id=2954 lang=java
 *
 * [2954] Count the Number of Infection Sequences
 *
 * https://leetcode.com/problems/count-the-number-of-infection-sequences/description/
 *
 * algorithms
 * Hard (35.42%)
 * Likes:    119
 * Dislikes: 27
 * Total Accepted:    3.5K
 * Total Submissions: 10.1K
 * Testcase Example:  '5\n[0,4]'
 *
 * You are given an integer n and an array sick sorted in increasing order,
 * representing positions of infected people in a line of n people.
 * 
 * At each step, one uninfected person adjacent to an infected person gets
 * infected. This process continues until everyone is infected.
 * 
 * An infection sequence is the order in which uninfected people become
 * infected, excluding those initially infected.
 * 
 * Return the number of different infection sequences possible, modulo
 * 10^9+7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5, sick = [0,4]
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * There is a total of 6 different sequences overall.
 * 
 * 
 * Valid infection sequences are [1,2,3], [1,3,2], [3,2,1] and [3,1,2].
 * [2,3,1] and [2,1,3] are not valid infection sequences because the person at
 * index 2 cannot be infected at the first step.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, sick = [1]
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * There is a total of 6 different sequences overall.
 * 
 * 
 * Valid infection sequences are [0,2,3], [2,0,3] and [2,3,0].
 * [3,2,0], [3,0,2], and [0,3,2] are not valid infection sequences because the
 * infection starts at the person at index 1, then the order of infection is 2,
 * then 3, and hence 3 cannot be infected earlier than 2.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 10^5
 * 1 <= sick.length <= n - 1
 * 0 <= sick[i] <= n - 1
 * sick is sorted in increasing order.
 * 
 * 
 */

// @lc code=start
class Solution {
    long MOD = (long)1e9 + 7;
    public int numberOfSequence(int n, int[] sick) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int i = 1; i < sick.length; i++){
            int gap = sick[i] - sick[i - 1] - 1;
            if(gap > 0){
                cnt.put(gap, cnt.getOrDefault(gap, 0) + 1);
            }
        }
        int gapHead = Math.max(0, sick[0]);
        int gapTail = Math.max(0, n - sick[sick.length - 1] - 1);
        long[] invFact = getInverseFact(n, MOD);
        
        // n!/gap1!gap2!... * 2^(gap1 - 1) * 2^(gap2 - 1)...
        long ans = 1;
        long exp = 0;
        int total = gapHead + gapTail;
        for(int k : cnt.keySet()){
            ans *= modularExpRecursive(invFact[k], cnt.get(k), MOD);
            ans %= MOD;
            exp += (k - 1) * cnt.get(k);
            total += k * cnt.get(k);
        }
        ans *= getFact(total, MOD);
        ans %= MOD;
        // System.out.println(ans);
        ans *= modularExpRecursive(2, exp, MOD);
        ans %= MOD;
        // System.out.println(ans);
        ans *= invFact[gapHead];
        ans %= MOD;
        // System.out.println(ans);
        ans *= invFact[gapTail];
        ans %= MOD;
        // System.out.println(ans);
        return (int)ans;
    }

    long modularExpRecursive(long base, long exp, long m) {
        if (exp == 0) {
            return 1;
        } else if (exp % 2 == 1) {
            long l = base * modularExpRecursive(base, exp - 1, m);
            return l % m;
        } else {
            long t = modularExpRecursive(base, exp / 2, m);
            return (t * t) % m;
        }
    }

    long[] getInverseFact(int n, long p) {
        long[] state = new long[n + 1];
        long[] iFact = new long[n + 1];
        state[0] = 1;
        state[1] = 1;
        iFact[0] = 1;
        iFact[1] = 1;
        for (int i = 2; i <= n; i++) {
            // https://cp-algorithms.com/algebra/module-inverse.html
            long t = (state[(int) (p % i)] * (p - p / i));
            state[i] = t % p;
            iFact[i] = (iFact[i - 1] * state[i]) % p;
        }
        return iFact;
    }

    long getFact(int n, long p) {
        long r = 1;
        for (int i = 1; i <= n; i++) {
            r = (r * i) % p;
        }
        return r;
    }
}
// @lc code=end
