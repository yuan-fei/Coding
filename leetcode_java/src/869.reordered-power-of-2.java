/*
 * @lc app=leetcode id=869 lang=java
 *
 * [869] Reordered Power of 2
 *
 * https://leetcode.com/problems/reordered-power-of-2/description/
 *
 * algorithms
 * Medium (63.47%)
 * Likes:    2037
 * Dislikes: 421
 * Total Accepted:    107.1K
 * Total Submissions: 168.8K
 * Testcase Example:  '1'
 *
 * You are given an integer n. We reorder the digits in any order (including
 * the original order) such that the leading digit is not zero.
 * 
 * Return true if and only if we can do this so that the resulting number is a
 * power of two.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 10
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean reorderedPowerOf2(int n) {
        List<int[]> codes = new ArrayList<>();
        for(int i = 0; i < 31; i++){
            codes.add(encode(1 << i));
        }
        int[] t = encode(n);
        for(int[] c : codes){
            if(Arrays.equals(c, t)){
                return true;
            }
        }
        return false;
    }

    int[] encode(int x){
        int[] cnt = new int[10];
        for(char c : ("" +x).toCharArray()){
            cnt[c - '0']++;
        }
        return cnt;
    }
}
// @lc code=end
