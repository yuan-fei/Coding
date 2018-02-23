/*
 * [137] Single Number II
 *
 * https://leetcode.com/problems/single-number-ii/description/
 *
 * algorithms
 * Medium (42.42%)
 * Total Accepted:    130.8K
 * Total Submissions: 308K
 * Testcase Example:  '[1]'
 *
 * 
 * Given an array of integers, every element appears three times except for
 * one, which appears exactly once. Find that single one.
 * 
 * 
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 * 
 */
class Solution {
	final int REPEAT = 3;
    public int singleNumber(int[] nums) {
        int[] bitCount = new int[32];
        for (int i = 0; i < bitCount.length ; i++) {
        	bitCount[i] = 0;
        }
        for (int i = 0; i < bitCount.length ; i++) {
        	for (int j = 0; j < nums.length; j++) {
        		int n = nums[j];
        		if((n & (1 << i)) != 0){
        			bitCount[i] = (bitCount[i] + 1) % REPEAT;
        		}
        	}
        }
        int except = 0;
        for (int i = 0; i < bitCount.length ; i++) {
        	if(bitCount[i] != 0){
        		except |= (1 << i);
        	}
        }
        return except;
    }
}
