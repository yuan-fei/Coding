/*
 * @lc app=leetcode id=691 lang=java
 *
 * [691] Stickers to Spell Word
 *
 * https://leetcode.com/problems/stickers-to-spell-word/description/
 *
 * algorithms
 * Hard (46.46%)
 * Likes:    630
 * Dislikes: 61
 * Total Accepted:    26.9K
 * Total Submissions: 57.8K
 * Testcase Example:  '["with","example","science"]\n"thehat"'
 *
 * We are given n different types of stickers. Each sticker has a lowercase
 * English word on it.
 * 
 * You would like to spell out the given string target by cutting individual
 * letters from your collection of stickers and rearranging them. You can use
 * each sticker more than once if you want, and you have infinite quantities of
 * each sticker.
 * 
 * Return the minimum number of stickers that you need to spell out target. If
 * the task is impossible, return -1.
 * 
 * Note: In all test cases, all words were chosen randomly from the 1000 most
 * common US English words, and target was chosen as a concatenation of two
 * random words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: stickers = ["with","example","science"], target = "thehat"
 * Output: 3
 * Explanation:
 * We can use 2 "with" stickers, and 1 "example" sticker.
 * After cutting and rearrange the letters of those stickers, we can form the
 * target "thehat".
 * Also, this is the minimum number of stickers necessary to form the target
 * string.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: stickers = ["notice","possible"], target = "basicbasic"
 * Output: -1
 * Explanation:
 * We cannot form the target "basicbasic" from cutting letters from the given
 * stickers.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == stickers.length
 * 1 <= n <= 50
 * 1 <= stickers[i].length <= 10
 * 1 <= target <= 15
 * stickers[i] and target consist of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    
    static final int INF = (int)1e4;
    
    public int minStickers(String[] stickers, String target) {
        
        int dp[] = new int[(1 << target.length()) + 1];
        Arrays.fill(dp, -1);
        
        int ans = solve(stickers, target, 0, dp);
        
        return ans == INF ? - 1: ans;   
    }
    
    int solve(String[] stickers, String target, int mask, int dp[]) {
        int n = target.length();
        
        if(mask == ((1 << n) - 1)) {
            return 0;
        }
        
        if(dp[mask] != -1) {
            return dp[mask];
        }
        
        int ans = INF;
        
        for(int pos = 0; pos < stickers.length; pos++) {
            
            int newMask = mask;
            
            int f [] = new int[26];
            for(char c: stickers[pos].toCharArray()) f[c - 'a']++;
            
            for(int k = 0; k < n ; k++)
                if((newMask & (1 << k)) == 0 && f[target.charAt(k) - 'a'] > 0) {
                    newMask |= (1<< k);
                    f[target.charAt(k) - 'a']--;
                }

            if(newMask != mask) {
                ans = Math.min(ans, solve(stickers, target, newMask, dp) + 1);
            }
            
        }
        
        return dp[mask] = ans;
    }
    
}
// @lc code=end
