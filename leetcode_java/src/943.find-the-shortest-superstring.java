/*
 * @lc app=leetcode id=943 lang=java
 *
 * [943] Find the Shortest Superstring
 *
 * https://leetcode.com/problems/find-the-shortest-superstring/description/
 *
 * algorithms
 * Hard (43.91%)
 * Likes:    1376
 * Dislikes: 144
 * Total Accepted:    27.1K
 * Total Submissions: 61.9K
 * Testcase Example:  '["alex","loves","leetcode"]'
 *
 * Given an array of strings words, return the smallest string that contains
 * each string in words as a substring. If there are multiple valid strings of
 * the smallest length, return any of them.
 * 
 * You may assume that no string in words is a substring of another string in
 * words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be
 * accepted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 12
 * 1 <= words[i].length <= 20
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String shortestSuperstring(String[] words) {
        int n = words.length;
        int[][][] dp = new int[n + 1][1 << n][n];
        int[][][] trace = new int[1 << n][n][];
        int[][] cost = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j){
                    cost[i][j] = getCommon(words[i], words[j]);
                }
            }
        }
        // System.out.println(Arrays.deepToString(cost));

        List<Integer>[] masksByBitCount = new List[n + 1];
        for(int i = 0; i <= n; i++){
            masksByBitCount[i] = new ArrayList<>();
        }
        for(int mask = 0; mask < (1 << n); mask++){
            masksByBitCount[Integer.bitCount(mask)].add(mask);
        }
        // System.out.println(Arrays.toString(masksByBitCount));
        for(int m : masksByBitCount[1]){
            for(int i = 0; i < n; i++){         
                if(((m >> i) & 1) == 1){
                    dp[1][m][i] = words[i].length();
                    trace[m][i] = new int[]{-1, -1};
                    break;
                }
            }
        }

        for(int l = 2; l <= n; l++){
            for(int m : masksByBitCount[l]){
                for(int i = 0; i < n; i++){  
                    dp[l][m][i] = Integer.MAX_VALUE;
                    if(((m >> i) & 1) == 1){
                        int maskOthers = (m ^ (1 << i));
                        for(int j = 0; j < n; j++){
                            if(((maskOthers >> j) & 1) == 1){
                                int curCost = dp[l - 1][maskOthers][j] + words[i].length() - cost[i][j];
                                if(curCost < dp[l][m][i]){
                                    dp[l][m][i] = dp[l - 1][maskOthers][j] + words[i].length() - cost[i][j];
                                    trace[m][i] = new int[]{maskOthers, j};
                                }
                                
                            }
                        }
                    }
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        int minCost = Integer.MAX_VALUE;
        int target = -1;
        for(int i = 0; i < n; i++){
            if(minCost > dp[n][(1 << n) - 1][i]){
                minCost = dp[n][(1 << n) - 1][i];
                target = i;
            }
        }
        // System.out.println(target);
        int mask = (1 << n) - 1;
        int lastTarget = -1;
        String ans = "";
        while(target != -1){
            if(lastTarget == -1){
                ans += words[target];
            }
            else{
                ans += words[target].substring(cost[lastTarget][target]);
            }
            int[] previous = trace[mask][target];
            lastTarget = target;
            mask = previous[0];
            target = previous[1];
        }
        return ans;
        
    }

    int getCommon(String a, String b){
        for(int d = Math.max(0, a.length() - b.length()); d < a.length(); d++){
            boolean match = true;
            for(int i = d; i < a.length(); i++){
                if(a.charAt(i) != b.charAt(i - d)){
                    match = false;
                    break;
                }
            }
            if(match){
                return a.length() - d;
            }
        }
        return 0;
    }
}
// @lc code=end
