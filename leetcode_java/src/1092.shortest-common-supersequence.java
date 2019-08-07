/*
 * @lc app=leetcode id=1092 lang=java
 *
 * [1092] Shortest Common Supersequence 
 *
 * https://leetcode.com/problems/shortest-common-supersequence/description/
 *
 * algorithms
 * Hard (48.18%)
 * Total Accepted:    3.6K
 * Total Submissions: 7.5K
 * Testcase Example:  '"abac"\n"cab"'
 *
 * Given two strings str1 and str2, return the shortest string that has both
 * str1 and str2 as subsequences.  If multiple answers exist, you may return
 * any of them.
 * 
 * (A string S is a subsequence of string T if deleting some number of
 * characters from T (possibly 0, and the characters are chosen anywhere from
 * T) results in the string S.)
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation: 
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first
 * "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last
 * "ac".
 * The answer provided is the shortest such string that satisfies these
 * properties.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of lowercase English letters.
 * 
 * 
 */
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];
        int[][] d = new int[n+1][m+1];
        for(int i = 0; i<=n; i++){
            for(int j=0;j<=m;j++){
                dp[i][j]=Integer.MAX_VALUE-5;        
                if(i==0){
                    dp[0][j]=j;
                    d[0][j]=1;
        
                }
                if(j==0){
                    dp[i][0]=i;        
                    d[i][0]=-1;
                }
                
            }
        }
        
        
        for(int i = 1; i<=n; i++){
            for(int j=1;j<=m;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;    
                    d[i][j] = 0;
                }
                else{
                    dp[i][j] = dp[i-1][j-1]+2;    
                        d[i][j] = 2;
                }    
                if(dp[i][j-1]+1<dp[i][j]){
                    dp[i][j] = dp[i][j-1]+1;    
                    d[i][j] = 1;
                }
                if(dp[i-1][j]+1<dp[i][j]){
                    dp[i][j] = dp[i-1][j]+1;
                    d[i][j] = -1;
                }
                
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        // System.out.println(Arrays.deepToString(d));
        //System.out.println(dp[n][m]);
        int i = n;
        int j = m;
        StringBuilder sb = new StringBuilder();
        while(i!=0||j!=0){
            if(d[i][j]==0){
                sb.append(str1.charAt(i-1));
                i--;
                j--;
            }
            else if(d[i][j]==-1){
                sb.append(str1.charAt(i-1));
                i--;
            }
            else if(d[i][j]==1){
                sb.append(str2.charAt(j-1));
                j--;
            }
            else{
                sb.append(str1.charAt(i-1));
                sb.append(str2.charAt(j-1));
                i--;
                j--;
            }
        }
        return sb.reverse().toString();
    }
}
