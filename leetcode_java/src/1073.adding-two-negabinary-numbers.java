/*
 * @lc app=leetcode id=1073 lang=java
 *
 * [1073] Adding Two Negabinary Numbers
 *
 * https://leetcode.com/problems/adding-two-negabinary-numbers/description/
 *
 * algorithms
 * Medium (36.61%)
 * Likes:    308
 * Dislikes: 123
 * Total Accepted:    17.1K
 * Total Submissions: 46.5K
 * Testcase Example:  '[1,1,1,1,1]\n[1,0,1]'
 *
 * Given two numbers arr1 and arr2 in base -2, return the result of adding them
 * together.
 * 
 * Each number is given in array format:  as an array of 0s and 1s, from most
 * significant bit to least significant bit.  For example, arr = [1,1,0,1]
 * represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array,
 * format is also guaranteed to have no leading zeros: either arr == [0] or
 * arr[0] == 1.
 * 
 * Return the result of adding arr1 and arr2 in the same format: as an array of
 * 0s and 1s with no leading zeros.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
 * Output: [1,0,0,0,0]
 * Explanation: arr1 represents 11, arr2 represents 5, the output represents
 * 16.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr1 = [0], arr2 = [0]
 * Output: [0]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr1 = [0], arr2 = [1]
 * Output: [1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr1.length, arr2.length <= 1000
 * arr1[i] and arr2[i] are 0 or 1
 * arr1 and arr2 have no leading zeros
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        List<Integer> res = new ArrayList<>();
        int adder = 0;
        for(int i = 0; i < Math.max(arr1.length, arr2.length) || adder != 0; i++){
            int a = 0;
            if(i < arr1.length){
                a = arr1[arr1.length - i - 1];
            }
            int b = 0;
            if(i < arr2.length){
                b = arr2[arr2.length - i - 1];
            }
            switch(a + b + adder){
                case -1: adder = 1; res.add(1); break;
                case 0: adder = 0; res.add(0); break;
                case 1: adder = 0; res.add(1); break;
                case 2: adder = -1; res.add(0); break;
                case 3: adder = -1; res.add(1); break;
            }
        }   
        while(!res.isEmpty() && res.get(res.size() - 1) == 0){
            res.remove(res.size() - 1);
        }
        if(res.isEmpty()){
            res.add(0);
        }
        int[] ans = new int[res.size()];
        Collections.reverse(res);
        for(int i = 0; i < res.size(); i++){
            ans[i] = res.get(i);
        }
        return ans;
    }
}
// @lc code=end
