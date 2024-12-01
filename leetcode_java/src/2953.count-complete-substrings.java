/*
 * @lc app=leetcode id=2953 lang=java
 *
 * [2953] Count Complete Substrings
 *
 * https://leetcode.com/problems/count-complete-substrings/description/
 *
 * algorithms
 * Hard (25.36%)
 * Likes:    214
 * Dislikes: 36
 * Total Accepted:    7.6K
 * Total Submissions: 27.7K
 * Testcase Example:  '"igigee"\n2'
 *
 * You are given a string word and an integer k.
 * 
 * A substring s of word is complete if:
 * 
 * 
 * Each character in s occurs exactly k times.
 * The difference between two adjacent characters is at most 2. That is, for
 * any two adjacent characters c1 and c2 in s, the absolute difference in their
 * positions in the alphabet is at most 2.
 * 
 * 
 * Return the number of complete substrings of word.
 * 
 * A substring is a non-empty contiguous sequence of characters in a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "igigee", k = 2
 * Output: 3
 * Explanation: The complete substrings where each character appears exactly
 * twice and the difference between adjacent characters is at most 2 are:
 * igigee, igigee, igigee.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word = "aaabbbccc", k = 3
 * Output: 6
 * Explanation: The complete substrings where each character appears exactly
 * three times and the difference between adjacent characters is at most 2 are:
 * aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word.length <= 10^5
 * word consists only of lowercase English letters.
 * 1 <= k <= word.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countCompleteSubstrings(String word, int k) {
        int n = word.length();
        int[][] pSum = new int[n + 1][];
        pSum[0] = new int[26];
        int ans = 0;
        int left = 0;
        int right = 0;
        for(; right < n; right++){
            pSum[right + 1] = Arrays.copyOf(pSum[right], 26);
            pSum[right + 1][word.charAt(right) - 'a']++;
            
            if(right > 0 && Math.abs(word.charAt(right) - word.charAt(right - 1)) > 2){
                for(int r = left + 1; r <= right; r++){
                    ans += countCompleteSubstrings(word, k, pSum, left, r);    
                }
                left = right;
            }
        }
        for(int r = left + 1; r <= right; r++){
            ans += countCompleteSubstrings(word, k, pSum, left, r);    
        }
        return ans;
    }

    int countCompleteSubstrings(String word, int k, int[][] pSum, int left, int right){
        // System.out.println(Arrays.deepToString(pSum) + ", " + left + ", " + right);
        // System.out.println(left + ", " + right);
        int cnt = 0;
        for(int i = 1; i <= 26 && left + i * k <= right; i++){
            boolean cntOfK = true;
            for(int j = 0; j < 26; j++){
                // System.out.println(Arrays.asList(right , j, i * k, pSum[right][j] - pSum[right - i * k][j]));
                if(pSum[right][j] - pSum[right - i * k][j] != k && pSum[right][j] - pSum[right - i * k][j] != 0){
                    cntOfK = false;
                    break;
                }
            }
            if(cntOfK){
                // System.out.println(Arrays.asList(right, i * k));
                cnt++;
            }
        }
        return cnt;
    }
}
// @lc code=end
