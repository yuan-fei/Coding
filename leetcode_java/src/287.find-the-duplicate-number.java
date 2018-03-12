/*
 * [287] Find the Duplicate Number
 *
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 *
 * algorithms
 * Medium (44.13%)
 * Total Accepted:    100.4K
 * Total Submissions: 227.1K
 * Testcase Example:  '[1,1]'
 *
 * 
 * Given an array nums containing n + 1 integers where each integer is between
 * 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * 
 * 
 * 
 * Note:
 * 
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated
 * more than once.
 * 
 * 
 * 
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and
 * creating all test cases.
 */
class Solution {
	public int findDuplicate(int[] nums) {
		return solve2(nums);
	}

	public int solve2(int[] nums) {
		int slow = nums[0];
		int fast = nums[0];
		while(true){
			slow = nums[slow];
			fast = nums[nums[fast]];
			if(slow == fast){
				break;
			}
		}
		slow = nums[0];
		while(slow != fast){
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}

    public int solve1(int[] nums) {
        int low = 1;
        int high = nums.length - 1;
        while(low + 1 < high){
        	int mid = low + (high - low) / 2;
        	if(mid >= countBelow(nums, mid)){
        		low = mid;
        	}
        	else{
        		high = mid;
        	}
        }
        if(count(nums, low) > 1){
        	return low;
        }
        else{
        	return high;
        }
    }

    private int countBelow(int[] nums, int target){
    	int i = 0;
    	for (int num : nums) {
    		if(num <= target){
    			i++;
    		}
    	}
    	return i;
    }

    private int count(int[] nums, int target){
    	int i = 0;
    	for (int num : nums) {
    		if(num == target){
    			i++;
    		}
    	}
    	return i;
    }
}
