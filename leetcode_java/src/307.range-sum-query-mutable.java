/*
 * @lc app=leetcode id=307 lang=java
 *
 * [307] Range Sum Query - Mutable
 *
 * https://leetcode.com/problems/range-sum-query-mutable/description/
 *
 * algorithms
 * Medium (31.64%)
 * Likes:    967
 * Dislikes: 70
 * Total Accepted:    89.6K
 * Total Submissions: 283K
 * Testcase Example:  '["NumArray","sumRange","update","sumRange"]\n[[[1,3,5]],[0,2],[1,2],[0,2]]'
 *
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * The update(i, val) function modifies nums by updating the element at index i
 * to val.
 * 
 * Example:
 * 
 * 
 * Given nums = [1, 3, 5]
 * 
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 
 * 
 * Note:
 * 
 * 
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is
 * distributed evenly.
 * 
 * 
 */

// @lc code=start
class NumArray {
	int[] a;
	int n;
    public NumArray(int[] nums) {
        n = nums.length;
		a = new int[2 * n];
		for(int i = 0; i < n; i++){
			a[n + i] = nums[i];
		}

		for(int i = n - 1; i >= 1; i--){
			a[i] = a[2 * i + 1] + a[2 * i];
		}
		// System.out.println(Arrays.toString(a));
    }
    

    public void update(int i, int val) {
    	int pos = n + i;
        a[pos] = val;
        pos /= 2;
        while(pos != 0){
        	a[pos] = a[pos * 2 + 1] + a[pos * 2];
        	pos /= 2;
        }
        // System.out.println(Arrays.toString(a));
    }
    
    public int sumRange(int i, int j) {
    	int sum = 0;
    	i += n;
    	j += n;
        while(i <= j){
        	if(i % 2 == 1){
        		sum += a[i];
        		i++;
        	}
        	if(j % 2 == 0){
        		sum += a[j];
        		j--;
        	}
        	i /= 2;
        	j /= 2;
        }
        // sum += a[i];
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
// @lc code=end
