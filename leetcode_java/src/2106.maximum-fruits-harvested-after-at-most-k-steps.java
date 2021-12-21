/*
 * @lc app=leetcode id=2106 lang=java
 *
 * [2106] Maximum Fruits Harvested After at Most K Steps
 *
 * https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps/description/
 *
 * algorithms
 * Hard (24.05%)
 * Likes:    34
 * Dislikes: 2
 * Total Accepted:    1.3K
 * Total Submissions: 5.4K
 * Testcase Example:  '[[2,8],[6,3],[8,6]]\n5\n4'
 *
 * Fruits are available at some positions on an infinite x-axis. You are given
 * a 2D integer array fruits where fruits[i] = [positioni, amounti] depicts
 * amounti fruits at the position positioni. fruits is already sorted by
 * positioni in ascending order, and each positioni is unique.
 * 
 * You are also given an integer startPos and an integer k. Initially, you are
 * at the position startPos. From any position, you can either walk to the left
 * or right. It takes one step to move one unit on the x-axis, and you can walk
 * at most k steps in total. For every position you reach, you harvest all the
 * fruits at that position, and the fruits will disappear from that position.
 * 
 * Return the maximum total number of fruits you can harvest.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
 * Output: 9
 * Explanation: 
 * The optimal way is to:
 * - Move right to position 6 and harvest 3 fruits
 * - Move right to position 8 and harvest 6 fruits
 * You moved 3 steps and harvested 3 + 6 = 9 fruits in total.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
 * Output: 14
 * Explanation: 
 * You can move at most k = 4 steps, so you cannot reach position 0 nor 10.
 * The optimal way is to:
 * - Harvest the 7 fruits at the starting position 5
 * - Move left to position 4 and harvest 1 fruit
 * - Move right to position 6 and harvest 2 fruits
 * - Move right to position 7 and harvest 4 fruits
 * You moved 1 + 3 = 4 steps and harvested 7 + 1 + 2 + 4 = 14 fruits in
 * total.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
 * Output: 0
 * Explanation:
 * You can move at most k = 2 steps and cannot reach any position with
 * fruits.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= fruits.length <= 10^5
 * fruits[i].length == 2
 * 0 <= startPos, positioni <= 2 * 10^5
 * positioni-1 < positioni for any i > 0 (0-indexed)
 * 1 <= amounti <= 10^4
 * 0 <= k <= 2 * 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int lToR = maxFromLToR(fruits, startPos, k);
        int[][] fruitsCopy = new int[fruits.length][2];
        fruitsCopy[0] = new int[]{0, fruits[n - 1][1]};
        for(int i = 1; i < n; i++){
            fruitsCopy[i] = new int[]{fruits[n - 1][0] - fruits[n - i - 1][0], fruits[n - i - 1][1]};
        }
        startPos = fruits[n - 1][0] - startPos;
        // System.out.println(lToR);
        int rToL = maxFromLToR(fruitsCopy, startPos, k);
        // System.out.println(rToL);
        return Math.max(lToR, rToL);
    }

    int maxFromLToR(int[][] fruits, int startPos, int k){
        // System.out.println(Arrays.deepToString(fruits));
        int n = fruits.length;
        int l = 0;
        // System.out.println(0);
        while(l < n && fruits[l][0] < startPos - k){
            l++;
        }
        // System.out.println(1);
        int ret = 0;
        int sum = 0;
        int r = l;

        while(r < n && fruits[r][0] <= startPos){
            sum += fruits[r][1];       
            r++;
        }
        while(r < n && fruits[r][0] - startPos <= k - 2 * Math.max(0, (startPos - fruits[l][0]))){
            sum += fruits[r][1];       
            r++;
        }
        // System.out.println(Arrays.asList(l, r, sum));
        ret = Math.max(ret, sum);
        l++;
        while(l < n && fruits[l][0] <= startPos){
            sum -= fruits[l - 1][1];
            while(r < n && fruits[r][0] - startPos <= k - 2 * (startPos - fruits[l][0])){
                sum += fruits[r][1];       
                r++;
            }
            // System.out.println(Arrays.asList(l, r, sum));
            ret = Math.max(ret, sum);
            l++;
        }
        return ret;
    }
}
// @lc code=end
