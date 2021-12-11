/*
 * @lc app=leetcode id=2094 lang=java
 *
 * [2094] Finding 3-Digit Even Numbers
 *
 * https://leetcode.com/problems/finding-3-digit-even-numbers/description/
 *
 * algorithms
 * Easy (49.04%)
 * Likes:    52
 * Dislikes: 72
 * Total Accepted:    6K
 * Total Submissions: 12.2K
 * Testcase Example:  '[2,1,3,0]'
 *
 * You are given an integer array digits, where each element is a digit. The
 * array may contain duplicates.
 * 
 * You need to find all the unique integers that follow the given
 * requirements:
 * 
 * 
 * The integer consists of the concatenation of three elements from digits in
 * any arbitrary order.
 * The integer does not have leading zeros.
 * The integer is even.
 * 
 * 
 * For example, if the given digits were [1, 2, 3], integers 132 and 312 follow
 * the requirements.
 * 
 * Return a sorted array of the unique integers.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: digits = [2,1,3,0]
 * Output: [102,120,130,132,210,230,302,310,312,320]
 * Explanation: 
 * All the possible integers that follow the requirements are in the output
 * array. 
 * Notice that there are no odd integers or integers with leading zeros.
 * 
 * Example 2:
 * 
 * 
 * Input: digits = [2,2,8,8,2]
 * Output: [222,228,282,288,822,828,882]
 * Explanation: 
 * The same digit can be used as many times as it appears in digits. 
 * In this example, the digit 8 is used twice each time in 288, 828, and
 * 882. 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: digits = [3,7,5]
 * Output: []
 * Explanation: 
 * No even integers can be formed using the given digits.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: digits = [0,2,0,0]
 * Output: [200]
 * Explanation: 
 * The only valid integer that can be formed with three digits and no leading
 * zeros is 200.
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: digits = [0,0,0]
 * Output: []
 * Explanation: 
 * All the integers that can be formed have leading zeros. Thus, there are no
 * valid integers.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> s = new HashSet<>();
        int n = digits.length;
        for(int i = 0; i < n; i++){
            if(digits[i] != 0){
                for(int j = 0; j < n; j++){
                    for(int k = 0; k < n; k++){
                        if(i != j && i != k && j != k){
                            if(digits[k] % 2 == 0){
                                s.add(digits[i] * 100 + digits[j] * 10 + digits[k]);
                            }
                        }
                    }
                }    
            }
        }
        int[] rt = new int[s.size()];
        int i = 0;
        for(int x : s){
            rt[i++] = x;
        }
        Arrays.sort(rt);
        return rt;
    }
}
// @lc code=end
