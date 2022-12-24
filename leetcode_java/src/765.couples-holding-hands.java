/*
 * @lc app=leetcode id=765 lang=java
 *
 * [765] Couples Holding Hands
 *
 * https://leetcode.com/problems/couples-holding-hands/description/
 *
 * algorithms
 * Hard (56.94%)
 * Likes:    1981
 * Dislikes: 97
 * Total Accepted:    50.3K
 * Total Submissions: 88.3K
 * Testcase Example:  '[0,2,1,3]'
 *
 * There are n couples sitting in 2n seats arranged in a row and want to hold
 * hands.
 * 
 * The people and seats are represented by an integer array row where row[i] is
 * the ID of the person sitting in the i^th seat. The couples are numbered in
 * order, the first couple being (0, 1), the second couple being (2, 3), and so
 * on with the last couple being (2n - 2, 2n - 1).
 * 
 * Return the minimum number of swaps so that every couple is sitting side by
 * side. A swap consists of choosing any two people, then they stand up and
 * switch seats.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: row = [0,2,1,3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2])
 * person.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: row = [3,2,0,1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2n == row.length
 * 2 <= n <= 30
 * n is even.
 * 0 <= row[i] < 2n
 * All the elements of row are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] p2Idx = new int[n];
        for(int i = 0; i < n; i++){
            p2Idx[row[i]] = i / 2;
        }
        int[][] edges = new int[n / 2][2];
        for (int i = 0; i < n/2; i++) {
            int couple = row[i * 2] % 2 == 1? row[i * 2] - 1 : row[i * 2] + 1;
            edges[i][0] = p2Idx[couple];
            couple = row[i * 2 + 1] % 2 == 1? row[i * 2 + 1] - 1 : row[i * 2 + 1] + 1;
            edges[i][1] = p2Idx[couple];
        }
        // System.out.println(Arrays.toString(p2Idx));
        // System.out.println(Arrays.deepToString(edges));
        boolean[] seen = new boolean[n / 2];
        int ans = 0;
        for(int i = 0 ; i < n / 2; i++){
            if(!seen[i]){
                seen[i] = true;
                if(edges[i][0] == i){
                    continue;
                }
                int j = edges[i][0];
                while(!seen[j]){
                    seen[j] = true;
                    ans++;
                    if(!seen[edges[j][0]]){
                        j = edges[j][0];
                    } 
                    else{
                        j = edges[j][1];
                    }
                }    
            }
        }
        return ans;
    }
}
// @lc code=end
