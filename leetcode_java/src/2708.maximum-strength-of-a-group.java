/*
 * @lc app=leetcode id=2708 lang=java
 *
 * [2708] Maximum Strength of a Group
 *
 * https://leetcode.com/problems/maximum-strength-of-a-group/description/
 *
 * algorithms
 * Medium (23.15%)
 * Likes:    261
 * Dislikes: 36
 * Total Accepted:    19K
 * Total Submissions: 82.1K
 * Testcase Example:  '[3,-1,-5,2,5,-9]'
 *
 * You are given a 0-indexed integer array nums representing the score of
 * students in an exam. The teacher would like to form one non-empty group of
 * students with maximal strength, where the strength of a group of students of
 * indices i0, i1, i2, ... , ik is defined as nums[i0] * nums[i1] * nums[i2] *
 * ... * nums[ik​].
 * 
 * Return the maximum strength of a group the teacher can create.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,-1,-5,2,5,-9]
 * Output: 1350
 * Explanation: One way to form a group of maximal strength is to group the
 * students at indices [0,2,3,4,5]. Their strength is 3 * (-5) * 2 * 5 * (-9) =
 * 1350, which we can show is optimal.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-4,-5,-4]
 * Output: 20
 * Explanation: Group the students at indices [0, 1] . Then, we’ll have a
 * resulting strength of 20. We cannot achieve greater strength.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 13
 * -9 <= nums[i] <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long maxStrength(int[] nums) {
        //sorting the array
        Arrays.sort(nums);
        //initializing with the bigger number
        long prdt=nums[nums.length-1];
        int p1=0;
        //counting neg numbers
        for(int x:nums){
            if(x<0)p1++;
        }
     
        int flag=0;
        //if count of neg numbers are odd then making it even
        if(p1%2==1)p1--;
        //mul the neg numbers
        for(int x:nums){
            if(p1==0)break;
            if(flag==0){
                prdt=1l;
                flag++;
            }
            prdt*=1l*x;
            p1--;
            
        }
       // mul the pos numbers
        int n=nums.length;
        for(int i=n-1;i>=0;i--){
            
            if(nums[i]<=0)break;
            if(flag==0){
                prdt=1l;
                flag++;
            }
            prdt*=1l*nums[i];
        }
        return prdt;
    }
}
// @lc code=end
