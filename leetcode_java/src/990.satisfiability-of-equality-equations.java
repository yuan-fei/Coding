/*
 * @lc app=leetcode id=990 lang=java
 *
 * [990] Satisfiability of Equality Equations
 *
 * https://leetcode.com/problems/satisfiability-of-equality-equations/description/
 *
 * algorithms
 * Medium (50.47%)
 * Likes:    3571
 * Dislikes: 55
 * Total Accepted:    117.7K
 * Total Submissions: 233.1K
 * Testcase Example:  '["a==b","b!=a"]'
 *
 * You are given an array of strings equations that represent relationships
 * between variables where each string equations[i] is of length 4 and takes
 * one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are
 * lowercase letters (not necessarily different) that represent one-letter
 * variable names.
 * 
 * Return true if it is possible to assign integers to variable names so as to
 * satisfy all the given equations, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: equations = ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is
 * satisfied, but not the second.
 * There is no way to assign the variables to satisfy both equations.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: equations = ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] is a lowercase letter.
 * equations[i][1] is either '=' or '!'.
 * equations[i][2] is '='.
 * equations[i][3] is a lowercase letter.
 * 
 * 
 */

// @lc code=start
class Solution {
    int[] parent = new int[26];
    public boolean equationsPossible(String[] equations) {
        Arrays.fill(parent, -1);
        for(String equation : equations){
            char[] e = equation.toCharArray();
            if(e[1] == '='){
                int a = e[0] - 'a';
                int b = e[3] - 'a';
                init(a);
                init(b);
                union(a, b);
            }
        }

        for(String equation : equations){
            char[] e = equation.toCharArray();
            if(e[1] == '!'){
                int a = e[0] - 'a';
                int b = e[3] - 'a';
                init(a);
                init(b);
                if(find(a) == find(b)){
                    return false;
                }
            }
        }
        return true;
    }

    void init(int x){
        if(parent[x] == -1){
            parent[x] = x;    
        }
    }

    int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px != py){
            parent[px] = py;
        }
    }

}
// @lc code=end
