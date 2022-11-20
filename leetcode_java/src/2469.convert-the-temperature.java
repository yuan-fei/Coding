/*
 * @lc app=leetcode id=2469 lang=java
 *
 * [2469] Convert the Temperature
 *
 * https://leetcode.com/problems/convert-the-temperature/description/
 *
 * algorithms
 * Easy (91.87%)
 * Likes:    59
 * Dislikes: 37
 * Total Accepted:    17.9K
 * Total Submissions: 19.4K
 * Testcase Example:  '36.50'
 *
 * You are given a non-negative floating point number rounded to two decimal
 * places celsius, that denotes the temperature in Celsius.
 * 
 * You should convert Celsius into Kelvin and Fahrenheit and return it as an
 * array ans = [kelvin, fahrenheit].
 * 
 * Return the array ans. Answers within 10^-5 of the actual answer will be
 * accepted.
 * 
 * Note that:
 * 
 * 
 * Kelvin = Celsius + 273.15
 * Fahrenheit = Celsius * 1.80 + 32.00
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: celsius = 36.50
 * Output: [309.65000,97.70000]
 * Explanation: Temperature at 36.50 Celsius converted in Kelvin is 309.65 and
 * converted in Fahrenheit is 97.70.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: celsius = 122.11
 * Output: [395.26000,251.79800]
 * Explanation: Temperature at 122.11 Celsius converted in Kelvin is 395.26 and
 * converted in Fahrenheit is 251.798.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= celsius <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15, celsius * 1.80 + 32.00};
    }
}
// @lc code=end
