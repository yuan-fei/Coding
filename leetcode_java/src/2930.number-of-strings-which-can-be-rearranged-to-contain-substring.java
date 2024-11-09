/*
 * @lc app=leetcode id=2930 lang=java
 *
 * [2930] Number of Strings Which Can Be Rearranged to Contain Substring
 *
 * https://leetcode.com/problems/number-of-strings-which-can-be-rearranged-to-contain-substring/description/
 *
 * algorithms
 * Medium (54.61%)
 * Likes:    170
 * Dislikes: 66
 * Total Accepted:    6.6K
 * Total Submissions: 11.8K
 * Testcase Example:  '4'
 *
 * You are given an integer n.
 * 
 * A string s is called good if it contains only lowercase English characters
 * and it is possible to rearrange the characters of s such that the new string
 * contains "leet" as a substring.
 * 
 * For example:
 * 
 * 
 * The string "lteer" is good because we can rearrange it to form "leetr" .
 * "letl" is not good because we cannot rearrange it to contain "leet" as a
 * substring.
 * 
 * 
 * Return the total number of good strings of length n.
 * 
 * Since the answer may be large, return it modulo 10^9 + 7.
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4
 * Output: 12
 * Explanation: The 12 strings which can be rearranged to have "leet" as a
 * substring are: "eelt", "eetl", "elet", "elte", "etel", "etle", "leet",
 * "lete", "ltee", "teel", "tele", and "tlee".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 10
 * Output: 83943898
 * Explanation: The number of strings with length 10 which can be rearranged to
 * have "leet" as a substring is 526083947580. Hence the answer is 526083947580
 * % (10^9 + 7) = 83943898.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    int MOD = (int)1e9 + 7;
    Map<List<Integer>, Integer> stateToId = buildStateToIds();
    int[][] transitionMatrix = buildTransitionMatrix();
    List<Integer> INIT_STATE = Arrays.asList(0, 0, 0);
    List<Integer> FINAL_STATE = Arrays.asList(1, 2, 1);
    
    public int stringCount(int n) {    
        int initialStateId = stateToId.get(INIT_STATE);
        int finalStateId = stateToId.get(FINAL_STATE);
        int[][] pow = quickPow(transitionMatrix, n);
        return pow[initialStateId][finalStateId];
    }

    Map<List<Integer>, Integer> buildStateToIds(){
        stateToId = new HashMap<>();
        int id = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 2; k++){
                    stateToId.put(Arrays.asList(i, j, k), id++);
                }
            }
        }
        return stateToId;
    }

    int[][] buildTransitionMatrix(){
        int n = stateToId.size();
        int[][] ret = new int[n][n];
        int[][] deltas = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        for(List<Integer> state : stateToId.keySet()){
            ret[stateToId.get(state)][stateToId.get(state)] += 23;
            for(int[] delta : deltas){
                List<Integer> newState = Arrays.asList(
                    Math.min(1, state.get(0) + delta[0]), 
                    Math.min(2, state.get(1) + delta[1]), 
                    Math.min(1, state.get(2) + delta[2]));
                ret[stateToId.get(state)][stateToId.get(newState)]++;
            }    
        }
        return ret;
    }

    int[][] mul(int[][] m1, int[][] m2){
        int[][] ret = new int[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m2[0].length; j++){
                for(int k = 0; k < m1[0].length; k++){
                    ret[i][j] += Math.floorMod(1L * m1[i][k] * m2[k][j], MOD);
                    ret[i][j] %= MOD;
                }
            }
        }
        return ret;
    }

    int[][] quickPow(int[][] m, int e){
        if(e == 1){
            return m;
        }
        else if(e % 2 == 1){
            return mul(m, quickPow(m, e - 1));
        }
        else{
            return quickPow(mul(m, m), e / 2);
        }
    }

}
// @lc code=end
