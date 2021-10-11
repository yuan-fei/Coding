/*
 * @lc app=leetcode id=2024 lang=java
 *
 * [2024] Maximize the Confusion of an Exam
 *
 * https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/
 *
 * algorithms
 * Medium (48.71%)
 * Likes:    114
 * Dislikes: 2
 * Total Accepted:    4.2K
 * Total Submissions: 8.6K
 * Testcase Example:  '"TTFF"\n2'
 *
 * A teacher is writing a test with n true/false questions, with 'T' denoting
 * true and 'F' denoting false. He wants to confuse the students by maximizing
 * the number of consecutive questions with the same answer (multiple trues or
 * multiple falses in a row).
 * 
 * You are given a string answerKey, where answerKey[i] is the original answer
 * to the i^th question. In addition, you are given an integer k, the maximum
 * number of times you may perform the following operation:
 * 
 * 
 * Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i]
 * to 'T' or 'F').
 * 
 * 
 * Return the maximum number of consecutive 'T's or 'F's in the answer key
 * after performing the operation at most k times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: answerKey = "TTFF", k = 2
 * Output: 4
 * Explanation: We can replace both the 'F's with 'T's to make answerKey =
 * "TTTT".
 * There are four consecutive 'T's.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: answerKey = "TFFT", k = 1
 * Output: 3
 * Explanation: We can replace the first 'T' with an 'F' to make answerKey =
 * "FFFT".
 * Alternatively, we can replace the second 'T' with an 'F' to make answerKey =
 * "TFFF".
 * In both cases, there are three consecutive 'F's.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: answerKey = "TTFTTFTT", k = 1
 * Output: 5
 * Explanation: We can replace the first 'F' to make answerKey = "TTTTTFTT"
 * Alternatively, we can replace the second 'F' to make answerKey =
 * "TTFTTTTT". 
 * In both cases, there are five consecutive 'T's.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == answerKey.length
 * 1 <= n <= 5 * 10^4
 * answerKey[i] is either 'T' or 'F'
 * 1 <= k <= n
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(solve(answerKey, 'T', k), solve(answerKey, 'F', k));
    }

    int solve(String answerKey, int ch, int k){
        int n = answerKey.length();
        int[] pSum = new int[n + 1];
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int i = 1; i <= n; i++){
            pSum[i] = pSum[i - 1];
            if(answerKey.charAt(i - 1) == ch){
                pSum[i] += 1;    

            }
            tm.put(pSum[i], i);
        }
        int max = 0;
        for(int i = 0; i <= n; i++){
            Integer key = tm.floorKey(pSum[i] + k);
            if(key != null){
                max = Math.max(tm.get(key) - i, max);
            }
        }
        return max;
    }
}
// @lc code=end
