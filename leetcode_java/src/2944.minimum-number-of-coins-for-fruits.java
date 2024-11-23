/*
 * @lc app=leetcode id=2944 lang=java
 *
 * [2944] Minimum Number of Coins for Fruits
 *
 * https://leetcode.com/problems/minimum-number-of-coins-for-fruits/description/
 *
 * algorithms
 * Medium (43.40%)
 * Likes:    261
 * Dislikes: 47
 * Total Accepted:    17.1K
 * Total Submissions: 36.5K
 * Testcase Example:  '[3,1,2]'
 *
 * You are given an integer array prices where prices[i] denotes the number of
 * coins needed to purchase the i^th fruit.
 * 
 * The fruit market has the following reward for each fruit:
 * 
 * 
 * If you purchase the i^th fruit at prices[i] coins, you can get any number of
 * the next (i + 1) fruits for free.
 * 
 * 
 * Note that even if you can take fruit j for free, you can still purchase it
 * for prices[j] coins to receive its reward.
 * 
 * Return the minimum number of coins needed to acquire all the fruits.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: prices = [3,1,2]
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * 
 * Purchase the 1^st fruit with prices[0] = 3 coins, you are allowed to take
 * the 2^nd fruit for free.
 * Purchase the 2^nd fruit with prices[1] = 1 coin, you are allowed to take the
 * 3^rd fruit for free.
 * Take the 3^rd fruit for free.
 * 
 * 
 * Note that even though you could take the 2^nd fruit for free as a reward of
 * buying 1^st fruit, you purchase it to receive its reward, which is more
 * optimal.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: prices = [1,10,1,1]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * 
 * Purchase the 1^st fruit with prices[0] = 1 coin, you are allowed to take the
 * 2^nd fruit for free.
 * Take the 2^nd fruit for free.
 * Purchase the 3^rd fruit for prices[2] = 1 coin, you are allowed to take the
 * 4^th fruit for free.
 * Take the 4^t^h fruit for free.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: prices = [26,18,6,12,49,7,45,45]
 * 
 * Output: 39
 * 
 * Explanation:
 * 
 * 
 * Purchase the 1^st fruit with prices[0] = 26 coin, you are allowed to take
 * the 2^nd fruit for free.
 * Take the 2^nd fruit for free.
 * Purchase the 3^rd fruit for prices[2] = 6 coin, you are allowed to take the
 * 4^th, 5^th and 6^th (the next three) fruits for free.
 * Take the 4^t^h fruit for free.
 * Take the 5^t^h fruit for free.
 * Purchase the 6^th fruit with prices[5] = 7 coin, you are allowed to take the
 * 8^th and 9^th fruit for free.
 * Take the 7^t^h fruit for free.
 * Take the 8^t^h fruit for free.
 * 
 * 
 * Note that even though you could take the 6^th fruit for free as a reward of
 * buying 3^rd fruit, you purchase it to receive its reward, which is more
 * optimal.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= prices.length <= 1000
 * 1 <= prices[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = 100000005;
    public int minimumCoins(int[] prices) {
        int n = prices.length;
        int sum = prices[0];
        int[] dp = new int[n + 1];
        int[] cache = new int[n + 1];
        int left = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(MAX, 1);
        for(int i = 0; i < n; i++){
            while(left + (left + 1) < i){
                dec(tm, cache[left + 1]);
                left++;
            }

            int reachPrev = dp[i];
            int reachCur = tm.firstKey();
            dp[i + 1] = Math.min(reachCur, reachPrev + prices[i]);
            cache[i + 1] = Math.min(reachCur + prices[i], reachPrev + prices[i]);
            inc(tm, cache[i + 1]);
        }
        // System.out.println(Arrays.toString(dp));
        // System.out.println(Arrays.toString(cache));
        return dp[n];
    }

    void inc(Map<Integer, Integer> map, int k){
        map.put(k, map.getOrDefault(k, 0) + 1);
    }

    void dec(Map<Integer, Integer> map, int k){
        int v = map.get(k) - 1;
        map.put(k, v);
        if(v == 0){
            map.remove(k);
        }
    }
}
// @lc code=end
