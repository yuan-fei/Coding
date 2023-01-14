/*
 * @lc app=leetcode id=2525 lang=java
 *
 * [2525] Categorize Box According to Criteria
 *
 * https://leetcode.com/problems/categorize-box-according-to-criteria/description/
 *
 * algorithms
 * Easy (30.97%)
 * Likes:    43
 * Dislikes: 13
 * Total Accepted:    14.4K
 * Total Submissions: 46.4K
 * Testcase Example:  '1000\n35\n700\n300'
 *
 * Given four integers length, width, height, and mass, representing the
 * dimensions and mass of a box, respectively, return a string representing the
 * category of the box.
 * 
 * 
 * The box is "Bulky" if:
 * 
 * 
 * Any of the dimensions of the box is greater or equal to 10^4.
 * Or, the volume of the box is greater or equal to 10^9.
 * 
 * 
 * If the mass of the box is greater or equal to 100, it is "Heavy".
 * If the box is both "Bulky" and "Heavy", then its category is "Both".
 * If the box is neither "Bulky" nor "Heavy", then its category is
 * "Neither".
 * If the box is "Bulky" but not "Heavy", then its category is "Bulky".
 * If the box is "Heavy" but not "Bulky", then its category is "Heavy".
 * 
 * 
 * Note that the volume of the box is the product of its length, width and
 * height.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: length = 1000, width = 35, height = 700, mass = 300
 * Output: "Heavy"
 * Explanation: 
 * None of the dimensions of the box is greater or equal to 10^4. 
 * Its volume = 24500000 <= 10^9. So it cannot be categorized as "Bulky".
 * However mass >= 100, so the box is "Heavy".
 * Since the box is not "Bulky" but "Heavy", we return "Heavy".
 * 
 * Example 2:
 * 
 * 
 * Input: length = 200, width = 50, height = 800, mass = 50
 * Output: "Neither"
 * Explanation: 
 * None of the dimensions of the box is greater or equal to 10^4.
 * Its volume = 8 * 10^6 <= 10^9. So it cannot be categorized as "Bulky".
 * Its mass is also less than 100, so it cannot be categorized as "Heavy"
 * either. 
 * Since its neither of the two above categories, we return "Neither".
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= length, width, height <= 10^5
 * 1 <= mass <= 10^3
 * 
 * 
 */

// @lc code=start
class Solution {
    public String categorizeBox(int length, int width, int height, int mass) {
        boolean isHeavy = (mass >= 100);
        long volume = 1L * length * width * height;
        boolean isBulky = length >= 10000 || width >= 10000 || height >= 10000 || volume >= (int)1e9;
        if(isBulky && isHeavy){
            return "Both";
        }
        if(isBulky){
            return "Bulky";
        }
        if(isHeavy){
            return "Heavy";
        }
        return "Neither";
    }
}
// @lc code=end
