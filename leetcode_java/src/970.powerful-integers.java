/*
 * @lc app=leetcode id=970 lang=java
 *
 * [970] Powerful Integers
 *
 * https://leetcode.com/problems/powerful-integers/description/
 *
 * algorithms
 * Medium (43.68%)
 * Likes:    360
 * Dislikes: 76
 * Total Accepted:    52.3K
 * Total Submissions: 119.7K
 * Testcase Example:  '2\n3\n10'
 *
 * Given three integers x, y, and bound, return a list of all the powerful
 * integers that have a value less than or equal to bound.
 * 
 * An integer is powerful if it can be represented as x^i + y^j for some
 * integers i >= 0 and j >= 0.
 * 
 * You may return the answer in any order. In your answer, each value should
 * occur at most once.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: x = 2, y = 3, bound = 10
 * Output: [2,3,4,5,7,9,10]
 * Explanation:
 * 2 = 2^0 + 3^0
 * 3 = 2^1 + 3^0
 * 4 = 2^0 + 3^1
 * 5 = 2^1 + 3^1
 * 7 = 2^2 + 3^1
 * 9 = 2^3 + 3^0
 * 10 = 2^0 + 3^2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: x = 3, y = 5, bound = 15
 * Output: [2,4,6,8,10,14]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= x, y <= 100
 * 0 <= bound <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        if(x == 1 && y == 1){
            if(bound >= 2){
                return Arrays.asList(2);
            }
            else{
                return new ArrayList<>();
            }
        }
        if(x < y){
            return powerfulIntegers(y, x, bound);
        }
        Set<Integer> uniquePowerfuls = new HashSet<>();
        int bx = bound;
        int by = bound;
        if(x == 1){
            bx = 1;
        }
        if(y == 1){
            by = 1;
        }
        for(int i = 1; i <= bx; i *= x){
            if(y == 1){
                if(i + 1 <= bound){
                    uniquePowerfuls.add(i + 1);
                }
            }
            else{
                for(int j = 1; j <= by; j *= y){
                    if(i + j <= bound){
                        uniquePowerfuls.add(i + j);
                    }
                }
            }
        }
        return new ArrayList<>(uniquePowerfuls);
    }
}
// @lc code=end
