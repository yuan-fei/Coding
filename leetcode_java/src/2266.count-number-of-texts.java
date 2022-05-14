/*
 * @lc app=leetcode id=2266 lang=java
 *
 * [2266] Count Number of Texts
 *
 * https://leetcode.com/problems/count-number-of-texts/description/
 *
 * algorithms
 * Medium (46.45%)
 * Likes:    115
 * Dislikes: 2
 * Total Accepted:    4K
 * Total Submissions: 8.6K
 * Testcase Example:  '"22233"'
 *
 * Alice is texting Bob using her phone. The mapping of digits to letters is
 * shown in the figure below.
 * 
 * In order to add a letter, Alice has to press the key of the corresponding
 * digit i times, where i is the position of the letter in the key.
 * 
 * 
 * For example, to add the letter 's', Alice has to press '7' four times.
 * Similarly, to add the letter 'k', Alice has to press '5' twice.
 * Note that the digits '0' and '1' do not map to any letters, so Alice does
 * not use them.
 * 
 * 
 * However, due to an error in transmission, Bob did not receive Alice's text
 * message but received a string of pressed keys instead.
 * 
 * 
 * For example, when Alice sent the message "bob", Bob received the string
 * "2266622".
 * 
 * 
 * Given a string pressedKeys representing the string received by Bob, return
 * the total number of possible text messages Alice could have sent.
 * 
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: pressedKeys = "22233"
 * Output: 8
 * Explanation:
 * The possible text messages Alice could have sent are:
 * "aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae", and "ce".
 * Since there are 8 possible messages, we return 8.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: pressedKeys = "222222222222222222222222222222222222"
 * Output: 82876089
 * Explanation:
 * There are 2082876103 possible text messages Alice could have sent.
 * Since we need to return the answer modulo 10^9 + 7, we return 2082876103 %
 * (10^9 + 7) = 82876089.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= pressedKeys.length <= 10^5
 * pressedKeys only consists of digits from '2' - '9'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countTexts(String pressedKeys) {
        int MAX = (int)1e9 + 7;
        int n = pressedKeys.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            int ub = 3;
            if(pressedKeys.charAt(i - 1) == '7' || pressedKeys.charAt(i - 1) == '9'){
                ub = 4;
            }
            for(int j = 0; j < ub && i - j > 0; j++){
                if(pressedKeys.charAt(i - j - 1) == pressedKeys.charAt(i - 1)){
                    dp[i] += dp[i - j - 1];
                    dp[i] %= MAX;
                }
                else{
                    break;
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
// @lc code=end
