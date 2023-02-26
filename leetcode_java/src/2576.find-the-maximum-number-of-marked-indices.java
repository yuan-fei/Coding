/*
 * @lc app=leetcode id=2576 lang=java
 *
 * [2576] Find the Maximum Number of Marked Indices
 *
 * https://leetcode.com/problems/find-the-maximum-number-of-marked-indices/description/
 *
 * algorithms
 * Medium (29.59%)
 * Likes:    86
 * Dislikes: 10
 * Total Accepted:    5.3K
 * Total Submissions: 18.3K
 * Testcase Example:  '[3,5,2,4]'
 *
 * You are given a 0-indexed integer array nums.
 * 
 * Initially, all of the indices are unmarked. You are allowed to make this
 * operation any number of times:
 * 
 * 
 * Pick two different unmarked indices i and j such that 2 * nums[i] <=
 * nums[j], then mark i and j.
 * 
 * 
 * Return the maximum possible number of marked indices in nums using the above
 * operation any number of times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,5,2,4]
 * Output: 2
 * Explanation: In the first operation: pick i = 2 and j = 1, the operation is
 * allowed because 2 * nums[2] <= nums[1]. Then mark index 2 and 1.
 * It can be shown that there's no other valid operation so the answer is 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [9,2,5,4]
 * Output: 4
 * Explanation: In the first operation: pick i = 3 and j = 0, the operation is
 * allowed because 2 * nums[3] <= nums[0]. Then mark index 3 and 0.
 * In the second operation: pick i = 1 and j = 2, the operation is allowed
 * because 2 * nums[1] <= nums[2]. Then mark index 1 and 2.
 * Since there is no other operation, the answer is 4.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [7,6,8]
 * Output: 0
 * Explanation: There is no valid operation to do, so the answer is 0.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 * 
 * .spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px
 * 0px; font-size:150%; font-weight: bold; color:#000000;
 * background-color:cyan; outline:0; 
 * }
 * .spoiler {overflow:hidden;}
 * .spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s
 * ease;-o-transition: all 0s ease;transition: margin 0s ease;}
 * .spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
 * .spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxNumOfMarkedIndices(int[] nums) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int x : nums){
            tm.put(x, tm.getOrDefault(x, 0) + 1);
        }

        TreeMap<Integer, Integer> tmSmall = new TreeMap<>();
        while(!tm.isEmpty()){
            // System.out.println(tm);
            int x = tm.lastKey();
            Integer y = tm.floorKey(x / 2);
            if(y != null){
                tm.put(x, tm.get(x) - 1);
                if(tm.get(x) == 0){
                    tm.remove(x);    
                }
                tm.put(y, tm.get(y) - 1);
                if(tm.get(y) == 0){
                    tm.remove(y);
                    tmSmall.put(y, tmSmall.getOrDefault(y, 0) + 1);
                }
            }
            else{
                break;
            }
        }
        while(!tm.isEmpty() && !tmSmall.isEmpty()){
            // System.out.println(tm + ", " + tmSmall);
            int x = tm.lastKey();
            int z = tmSmall.lastKey();
            if(x >= z){
                break;
            }
            tm.put(x, tm.get(x) - 1);
            if(tm.get(x) == 0){
                tm.remove(x);    
            }

            tmSmall.put(z, tmSmall.get(z) - 1);
            if(tmSmall.get(z) == 0){
                tmSmall.remove(z);    
            }
            tm.put(z, tm.getOrDefault(z, 0) + 1);
            x = tm.lastKey();
            Integer y = tm.floorKey(x / 2);
            if(y != null){
                tm.put(x, tm.get(x) - 1);
                if(tm.get(x) == 0){
                    tm.remove(x);    
                }
                tm.put(y, tm.get(y) - 1);
                if(tm.get(y) == 0){
                    tm.remove(y);
                }
            }
            else{
                break;
            }
        }
        // System.out.println(tm);
        int remianing = 0;
        while(!tm.isEmpty()){
            remianing += tm.pollFirstEntry().getValue();
        }
        return nums.length - remianing;
    }
}
// @lc code=end
