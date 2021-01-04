/*
 * @lc app=leetcode id=1711 lang=java
 *
 * [1711] Count Good Meals
 *
 * https://leetcode.com/problems/count-good-meals/description/
 *
 * algorithms
 * Medium (23.88%)
 * Likes:    95
 * Dislikes: 98
 * Total Accepted:    6.1K
 * Total Submissions: 25.6K
 * Testcase Example:  '[1,3,5,7,9]'
 *
 * A good meal is a meal that contains exactly two different food items with a
 * sum of deliciousness equal to a power of two.
 * 
 * You can pick any two different foods to make a good meal.
 * 
 * Given an array of integers deliciousness where deliciousness[i] is the
 * deliciousness of the i^​​​​​​th​​​​​​​​ item of food, return the number of
 * different good meals you can make from this list modulo 10^9 + 7.
 * 
 * Note that items with different indices are considered different even if they
 * have the same deliciousness value.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: deliciousness = [1,3,5,7,9]
 * Output: 4
 * Explanation: The good meals are (1,3), (1,7), (3,5) and, (7,9).
 * Their respective sums are 4, 8, 8, and 16, all of which are powers of 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: deliciousness = [1,1,1,3,3,3,7]
 * Output: 15
 * Explanation: The good meals are (1,1) with 3 ways, (1,3) with 9 ways, and
 * (1,7) with 3 ways.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= deliciousness.length <= 10^5
 * 0 <= deliciousness[i] <= 2^20
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countPairs(int[] deliciousness) {
        List<Integer> pow2 = new ArrayList<>();
        for(int i = 0; i <= 21; i++){
        	pow2.add(1 << i);
        }
        Map<Integer, Integer> m = new HashMap<>();
        int ans = 0;
        for(int x : deliciousness){
        	for(int p : pow2){
        		if(m.containsKey(p - x)){
        			ans += m.get(p - x);
        			ans %= 1000000007;
        		}
        	}
        	m.put(x, m.getOrDefault(x, 0) + 1);
        }
        return ans;
    }
}
// @lc code=end
