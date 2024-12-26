/*
 * @lc app=leetcode id=2983 lang=java
 *
 * [2983] Palindrome Rearrangement Queries
 *
 * https://leetcode.com/problems/palindrome-rearrangement-queries/description/
 *
 * algorithms
 * Hard (23.92%)
 * Likes:    90
 * Dislikes: 25
 * Total Accepted:    3.4K
 * Total Submissions: 13.4K
 * Testcase Example:  '"abcabc"\n[[1,1,3,5],[0,2,5,5]]'
 *
 * You are given a 0-indexed string s having an even length n.
 * 
 * You are also given a 0-indexed 2D integer array, queries, where queries[i] =
 * [ai, bi, ci, di].
 * 
 * For each query i, you are allowed to perform the following operations:
 * 
 * 
 * Rearrange the characters within the substring s[ai:bi], where 0 <= ai <= bi
 * < n / 2.
 * Rearrange the characters within the substring s[ci:di], where n / 2 <= ci <=
 * di < n.
 * 
 * 
 * For each query, your task is to determine whether it is possible to make s a
 * palindrome by performing the operations.
 * 
 * Each query is answered independently of the others.
 * 
 * Return a 0-indexed array answer, where answer[i] == true if it is possible
 * to make s a palindrome by performing operations specified by the i^th query,
 * and false otherwise.
 * 
 * 
 * A substring is a contiguous sequence of characters within a string.
 * s[x:y] represents the substring consisting of characters from the index x to
 * index y in s, both inclusive.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abcabc", queries = [[1,1,3,5],[0,2,5,5]]
 * Output: [true,true]
 * Explanation: In this example, there are two queries:
 * In the first query:
 * - a0 = 1, b0 = 1, c0 = 3, d0 = 5.
 * - So, you are allowed to rearrange s[1:1] => abcabc and s[3:5] => abcabc.
 * - To make s a palindrome, s[3:5] can be rearranged to become => abccba.
 * - Now, s is a palindrome. So, answer[0] = true.
 * In the second query:
 * - a1 = 0, b1 = 2, c1 = 5, d1 = 5.
 * - So, you are allowed to rearrange s[0:2] => abcabc and s[5:5] => abcabc.
 * - To make s a palindrome, s[0:2] can be rearranged to become => cbaabc.
 * - Now, s is a palindrome. So, answer[1] = true.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abbcdecbba", queries = [[0,2,7,9]]
 * Output: [false]
 * Explanation: In this example, there is only one query.
 * a0 = 0, b0 = 2, c0 = 7, d0 = 9.
 * So, you are allowed to rearrange s[0:2] => abbcdecbba and s[7:9] =>
 * abbcdecbba.
 * It is not possible to make s a palindrome by rearranging these substrings
 * because s[3:6] is not a palindrome.
 * So, answer[0] = false.
 * 
 * Example 3:
 * 
 * 
 * Input: s = "acbcab", queries = [[1,2,4,5]]
 * Output: [true]
 * Explanation: In this example, there is only one query.
 * a0 = 1, b0 = 2, c0 = 4, d0 = 5.
 * So, you are allowed to rearrange s[1:2] => acbcab and s[4:5] => acbcab.
 * To make s a palindrome s[1:2] can be rearranged to become abccab.
 * Then, s[4:5] can be rearranged to become abccba.
 * Now, s is a palindrome. So, answer[0] = true.
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n == s.length <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 4
 * ai == queries[i][0], bi == queries[i][1]
 * ci == queries[i][2], di == queries[i][3]
 * 0 <= ai <= bi < n / 2
 * n / 2 <= ci <= di < n 
 * n is even.
 * s consists of only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    Vector[] pSumFirst;
    Vector[] pSumLast;
    int[] maxIdentical;
    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
        int n = s.length() / 2;
        String first = s.substring(0, n);
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(n));
        sb.reverse();
        String last = sb.toString();
        pSumFirst = new Vector[n + 1];
        pSumLast = new Vector[n + 1];
        maxIdentical = new int[n + 1];
        pSumFirst[0] = new Vector(26, 0);
        pSumLast[0] = new Vector(26, 0);
        for(int i = 1; i <= n; i++){
            pSumFirst[i] = pSumFirst[i - 1].update(first.charAt(i - 1) - 'a', 1);
            pSumLast[i] = pSumLast[i - 1].update(last.charAt(i - 1) - 'a', 1);
            if(first.charAt(i - 1) == last.charAt(i - 1)){
                maxIdentical[i] = maxIdentical[i - 1] + 1;
            }
            
        }
        int m = queries.length;
        boolean[] ans = new boolean[m];
        for(int i = 0; i < m; i++){
            int[] qFirst = new int[]{queries[i][0], queries[i][1]};
            int[] qLast = new int[]{2 * n - 1 - queries[i][3], 2 * n - 1 - queries[i][2]};
            ans[i] = isIdentical(0, Math.min(qFirst[0], qLast[0]) - 1) 
                        && isIdentical(Math.max(qFirst[1], qLast[1]) + 1, n - 1);
            // System.out.println("query: " + Arrays.toString(qFirst) + Arrays.toString(qLast) + ans[i]);
            if(qFirst[1] < qLast[0] || qLast[1] < qFirst[0]){
                // System.out.println("no overlap");
                // no ovrelap
                ans[i] &= isSameFreq(qFirst[0], qFirst[1]) 
                            && isSameFreq(qLast[0], qLast[1])
                            && isIdentical(Math.min(qFirst[1], qLast[1]) + 1, Math.max(qFirst[0], qLast[0]) - 1);
            }
            else if(qFirst[0] < qLast[0] && qFirst[1] <= qLast[1]){
                // System.out.println("f m l");
                int[] mid = {Math.max(qFirst[0], qLast[0]), Math.min(qFirst[1], qLast[1])};
                Vector firstMid = getFreq(pSumFirst, qFirst[0], qFirst[1]).substract(getFreq(pSumLast, qFirst[0], mid[0] - 1));
                Vector lastMid = getFreq(pSumLast, qLast[0], qLast[1]).substract(getFreq(pSumFirst, mid[1] + 1, qLast[1]));
                ans[i] &= firstMid.isAllNonNegative() && lastMid.isAllNonNegative() && firstMid.equals(lastMid);

            }
            else if(qLast[0] < qFirst[0] && qLast[1] <= qFirst[1]){
                // System.out.println("l m f");
                int[] mid = {Math.max(qFirst[0], qLast[0]), Math.min(qFirst[1], qLast[1])};
                Vector firstMid = getFreq(pSumFirst, qFirst[0], qFirst[1]).substract(getFreq(pSumLast, mid[1] + 1, qFirst[1]));
                Vector lastMid = getFreq(pSumLast, qLast[0], qLast[1]).substract(getFreq(pSumFirst, qLast[0], mid[0] - 1));
                ans[i] &= firstMid.isAllNonNegative() && lastMid.isAllNonNegative() && firstMid.equals(lastMid);
            }
            else {
                // System.out.println("cover");
                ans[i] &= isSameFreq(Math.min(qFirst[0], qLast[0]), Math.max(qFirst[1], qLast[1]));
                
            }
        }
        return ans;
    }

    boolean isIdentical(int left, int right){
        if(left > right){
            return true;
        }
        return right - left + 1 <= maxIdentical[right + 1];
    }

    boolean isSameFreq(int left, int right){
        if(left > right){
            return true;
        }
        // System.out.println("isSameFreq: " + Arrays.asList(left, right));
        return getFreq(pSumFirst, left, right).equals(getFreq(pSumLast, left, right));
    }

    Vector getFreq(Vector[] pSum, int left, int right){
        return pSum[right + 1].substract(pSum[left]);
    }

    class Vector {
        int[] v;
        
        public Vector(int dim, int initValue){
            v = new int[dim];
            Arrays.fill(v, initValue);
        }
        
        public Vector(int[] vector){
            v = vector;
        }

        Vector update(int dim, int delta){
            int[] reminder = new int[v.length];
            for(int i = 0; i < v.length; i++){
                reminder[i] = v[i];
                if(i == dim){
                    reminder[i] += delta;
                }
            }
            return new Vector(reminder);
        }

        Vector substract(Vector other){
            int[] reminder = new int[v.length];
            for(int i = 0; i < v.length; i++){
                reminder[i] = v[i] - other.v[i];
            }
            return new Vector(reminder);
        }

        Vector add(Vector other){
            int[] reminder = new int[v.length];
            for(int i = 0; i < v.length; i++){
                reminder[i] = v[i] + other.v[i];
            }
            return new Vector(reminder);
        }

        @Override
        public boolean equals(Object o){
            return Arrays.equals(v, ((Vector) o).v);
        }

        boolean isZero(){
            for(int i = 0; i < v.length; i++){
                if(v[i] != 0){
                    return false;
                }
            }
            return true;
        }

        boolean isAllNonNegative(){
            for(int i = 0; i < v.length; i++){
                if(v[i] < 0){
                    return false;
                }
            }
            return true;
        }
    }
}
// @lc code=end
