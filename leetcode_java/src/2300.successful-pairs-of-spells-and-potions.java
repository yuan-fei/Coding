/*
 * @lc app=leetcode id=2300 lang=java
 *
 * [2300] Successful Pairs of Spells and Potions
 *
 * https://leetcode.com/problems/successful-pairs-of-spells-and-potions/description/
 *
 * algorithms
 * Medium (26.56%)
 * Likes:    154
 * Dislikes: 9
 * Total Accepted:    11.1K
 * Total Submissions: 41.9K
 * Testcase Example:  '[5,1,3]\n[1,2,3,4,5]\n7'
 *
 * You are given two positive integer arrays spells and potions, of length n
 * and m respectively, where spells[i] represents the strength of the i^th
 * spell and potions[j] represents the strength of the j^th potion.
 * 
 * You are also given an integer success. A spell and potion pair is considered
 * successful if the product of their strengths is at least success.
 * 
 * Return an integer array pairs of length n where pairs[i] is the number of
 * potions that will form a successful pair with the i^th spell.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * Output: [4,0,3]
 * Explanation:
 * - 0^th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
 * - 1^st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
 * - 2^nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
 * Thus, [4,0,3] is returned.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: spells = [3,1,2], potions = [8,5,8], success = 16
 * Output: [2,0,2]
 * Explanation:
 * - 0^th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
 * - 1^st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful. 
 * - 2^nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful. 
 * Thus, [2,0,2] is returned.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == spells.length
 * m == potions.length
 * 1 <= n, m <= 10^5
 * 1 <= spells[i], potions[i] <= 10^5
 * 1 <= success <= 10^10
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int[][] spellsWithId = new int[n][];
        for(int i = 0; i < n; i++){
            spellsWithId[i] = new int[]{spells[i], i};
        }
        Arrays.sort(spellsWithId, (a, b) -> -Integer.compare(a[0], b[0]));
        Arrays.sort(potions);
        int cur = 0;
        int[] ans = new int[n];
        for(int[] spellWithId : spellsWithId){
            while(cur < potions.length && 1L * spellWithId[0] * potions[cur] < success){
                cur++;
            }
            ans[spellWithId[1]] = potions.length - cur;
        }
        return ans;
    }
}
// @lc code=end
