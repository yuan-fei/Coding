/*
 * @lc app=leetcode id=927 lang=java
 *
 * [927] Three Equal Parts
 *
 * https://leetcode.com/problems/three-equal-parts/description/
 *
 * algorithms
 * Hard (39.57%)
 * Likes:    787
 * Dislikes: 118
 * Total Accepted:    28.1K
 * Total Submissions: 71K
 * Testcase Example:  '[1,0,1,0,1]'
 *
 * You are given an array arr which consists of only zeros and ones, divide the
 * array into three non-empty parts such that all of these parts represent the
 * same binary value.
 * 
 * If it is possible, return any [i, j] with i + 1 < j, such that:
 * 
 * 
 * arr[0], arr[1], ..., arr[i] is the first part,
 * arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
 * arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
 * All three parts have equal binary values.
 * 
 * 
 * If it is not possible, return [-1, -1].
 * 
 * Note that the entire part is used when considering what binary value it
 * represents. For example, [1,1,0] represents 6 in decimal, not 3. Also,
 * leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
 * 
 * 
 * Example 1:
 * Input: arr = [1,0,1,0,1]
 * Output: [0,3]
 * Example 2:
 * Input: arr = [1,1,0,1,1]
 * Output: [-1,-1]
 * Example 3:
 * Input: arr = [1,1,0,0,1]
 * Output: [0,2]
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= arr.length <= 3 * 10^4
 * arr[i] is 0 or 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] threeEqualParts(int[] arr) {
        int[] indexForOnes = IntStream.range(0, arr.length).filter(i -> arr[i] == 1).toArray();
        if(indexForOnes.length == 0){
            return new int[]{0, 2};
        }
        if(indexForOnes.length % 3 != 0){
            return new int[]{-1, -1};
        }
        int diff = arr.length - 1 - indexForOnes[indexForOnes.length - 1];
        int[] rights = new int[]{indexForOnes[indexForOnes.length / 3 - 1] + diff, indexForOnes[2 * indexForOnes.length / 3 - 1] + diff, arr.length - 1};
        int[] lefts = new int[]{0, rights[0] + 1, rights[1] + 1};
        for(int i = 0; i < 3; i++){
            while(lefts[i] < rights[i] && arr[lefts[i]] == 0){
                lefts[i]++;
            }
        }
        if(rights[0] - lefts[0] != rights[1] - lefts[1] || rights[0] - lefts[0] != rights[2] - lefts[2]){
            return new int[]{-1, -1};   
        }
        for(int i = 0; i < rights[0] - lefts[0] + 1; i++){
            if(arr[lefts[0] + i] != arr[lefts[1] + i] || arr[lefts[0] + i] != arr[lefts[2] + i]){
                return new int[]{-1, -1};       
            }
        }
        return new int[]{rights[0], rights[1] + 1};
    }
}
// @lc code=end
