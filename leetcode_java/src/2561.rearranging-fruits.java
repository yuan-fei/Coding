/*
 * @lc app=leetcode id=2561 lang=java
 *
 * [2561] Rearranging Fruits
 *
 * https://leetcode.com/problems/rearranging-fruits/description/
 *
 * algorithms
 * Hard (23.61%)
 * Likes:    50
 * Dislikes: 4
 * Total Accepted:    1.8K
 * Total Submissions: 7.6K
 * Testcase Example:  '[4,2,2,2]\n[1,4,1,2]'
 *
 * You have two fruit baskets containing n fruits each. You are given two
 * 0-indexed integer arrays basket1 and basket2 representing the cost of fruit
 * in each basket. You want to make both baskets equal. To do so, you can use
 * the following operation as many times as you want:
 * 
 * 
 * Chose two indices i and j, and swap the i^th fruit of basket1 with the j^th
 * fruit of basket2.
 * The cost of the swap is min(basket1[i],basket2[j]).
 * 
 * 
 * Two baskets are considered equal if sorting them according to the fruit cost
 * makes them exactly the same baskets.
 * 
 * Return the minimum cost to make both the baskets equal or -1 if
 * impossible.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: basket1 = [4,2,2,2], basket2 = [1,4,1,2]
 * Output: 1
 * Explanation: Swap index 1 of basket1 with index 0 of basket2, which has cost
 * 1. Now basket1 = [4,1,2,2] and basket2 = [2,4,1,2]. Rearranging both the
 * arrays makes them equal.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: basket1 = [2,3,4,1], basket2 = [3,2,5,1]
 * Output: -1
 * Explanation: It can be shown that it is impossible to make both the baskets
 * equal.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * basket1.length == bakste2.length
 * 1 <= basket1.length <= 10^5
 * 1 <= basket1[i],basket2[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freqTotal = new HashMap<>();
        Map<Integer, Integer> freqB1 = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for(int x : basket1){
            freqTotal.put(x, freqTotal.getOrDefault(x, 0) + 1);
            freqB1.put(x, freqB1.getOrDefault(x, 0) + 1);
            min = Math.min(min, x);
        }
        for(int x : basket2){
            freqTotal.put(x, freqTotal.getOrDefault(x, 0) + 1);
            min = Math.min(min, x);
        }
        List<Integer> swap1 = new ArrayList<>();
        List<Integer> swap2 = new ArrayList<>();
        for(int x : freqTotal.keySet()){
            if(freqTotal.get(x) % 2 == 1){
                return -1;
            }
            if(freqTotal.get(x) / 2 < freqB1.getOrDefault(x, 0)){
                for(int i = freqTotal.get(x) / 2; i < freqB1.getOrDefault(x, 0); i++){
                    swap1.add(x);
                }
            }
            else if(freqTotal.get(x) / 2 > freqB1.getOrDefault(x, 0)){
                for(int i = freqB1.getOrDefault(x, 0); i < freqTotal.get(x) / 2; i++){
                    swap2.add(x);
                }
            }
        }
        
        Collections.sort(swap1);
        Collections.sort(swap2, Comparator.reverseOrder());
        long ans = 0;
        // System.out.println(swap1 + ", " + swap2 + ", " + min);
        for(int i = 0; i < swap1.size(); i++){
            ans += Math.min(Math.min(swap1.get(i), swap2.get(i)), 2 * min);
        }
        return ans;
    }
}
// @lc code=end
