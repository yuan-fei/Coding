/*
 * [260] Single Number III
 *
 * https://leetcode.com/problems/single-number-iii/description/
 *
 * algorithms
 * Medium (52.84%)
 * Total Accepted:    78.6K
 * Total Submissions: 148.6K
 * Testcase Example:  '[1,2,1,3,2,5]'
 *
 * 
 * Given an array of numbers nums, in which exactly two elements appear only
 * once and all the other elements appear exactly twice. Find the two elements
 * that appear only once.
 * 
 * 
 * For example:
 * 
 * 
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * 
 * 
 * Note:
 * 
 * The order of the result is not important. So in the above example, [5, 3] is
 * also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement
 * it using only constant space complexity?
 * 
 * 
 * 
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and
 * creating all test cases.
 */
class Solution {
    public int[] singleNumber(int[] nums) {
    	int[] result = new int[2];
        int xor = 0;
        for (int num : nums) {
        	xor ^= num;
        }

        // find the 1st 1 to the lowest bit
        int mask = xor & -xor;

        for (int num : nums) {
        	if((num & mask) == 0){
        		result[0] ^= num;
        	}
        	else{
        		result[1] ^= num;	
        	}
        }
        return result;
    }
}
