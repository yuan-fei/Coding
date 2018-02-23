/*
 * [169] Majority Element
 *
 * https://leetcode.com/problems/majority-element/description/
 *
 * algorithms
 * Easy (47.71%)
 * Total Accepted:    244.8K
 * Total Submissions: 512.5K
 * Testcase Example:  '[1]'
 *
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 * 
 * Credits:Special thanks to @ts for adding this problem and creating all test
 * cases.
 */
class Solution {
    public int majorityElement(int[] nums) {
    	Integer majority = null;
    	int count = 0;
        for (int num: nums) {
        	if(count == 0){
        		majority = num;        		
        	}
        	if(majority == num){
        		count++;
        	}
        	else{
        		count--;
        	}
        }
        return majority;
    }
}
