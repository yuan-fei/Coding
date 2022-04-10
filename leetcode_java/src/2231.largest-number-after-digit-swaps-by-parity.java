/*
 * @lc app=leetcode id=2231 lang=java
 *
 * [2231] Largest Number After Digit Swaps by Parity
 *
 * https://leetcode.com/problems/largest-number-after-digit-swaps-by-parity/description/
 *
 * algorithms
 * Easy (50.31%)
 * Likes:    27
 * Dislikes: 47
 * Total Accepted:    8.4K
 * Total Submissions: 16.8K
 * Testcase Example:  '1234'
 *
 * You are given a positive integer num. You may swap any two digits of num
 * that have the same parity (i.e. both odd digits or both even digits).
 * 
 * Return the largest possible value of num after any number of swaps.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 1234
 * Output: 3412
 * Explanation: Swap the digit 3 with the digit 1, this results in the number
 * 3214.
 * Swap the digit 2 with the digit 4, this results in the number 3412.
 * Note that there may be other sequences of swaps but it can be shown that
 * 3412 is the largest possible number.
 * Also note that we may not swap the digit 4 with the digit 1 since they are
 * of different parities.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = 65875
 * Output: 87655
 * Explanation: Swap the digit 8 with the digit 6, this results in the number
 * 85675.
 * Swap the first digit 5 with the digit 7, this results in the number 87655.
 * Note that there may be other sequences of swaps but it can be shown that
 * 87655 is the largest possible number.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int largestInteger(int num) {
        String s = "" + num;
        int n = s.length();
        List<Character> even = new ArrayList<>();
        List<Character> odd = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if((s.charAt(i) - '0') % 2 ==0){
                even.add(s.charAt(i));    
            }
            else{
                odd.add(s.charAt(i));
            }
        }
        Collections.sort(even, (a, b) -> Integer.compare(b, a));
        Collections.sort(odd, (a, b) -> Integer.compare(b, a));
        char[] chars = new char[n];
        int idxEven = 0;
        int idxOdd = 0;
        for(int i = 0 ; i < n; i++){
            if((s.charAt(i) - '0') % 2 ==0){
                chars[i] = even.get(idxEven++);
            }
            else{
                chars[i] = odd.get(idxOdd++);
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
// @lc code=end
