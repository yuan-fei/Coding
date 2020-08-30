/*
 * @lc app=leetcode id=1569 lang=java
 *
 * [1569] Number of Ways to Reorder Array to Get Same BST
 *
 * https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/description/
 *
 * algorithms
 * Hard (42.95%)
 * Likes:    27
 * Dislikes: 12
 * Total Accepted:    1.2K
 * Total Submissions: 2.8K
 * Testcase Example:  '[2,1,3]'
 *
 * Given an array nums that represents a permutation of integers from 1 to n.
 * We are going to construct a binary search tree (BST) by inserting the
 * elements of nums in order into an initially empty BST. Find the number of
 * different ways to reorder nums so that the constructed BST is identical to
 * that formed from the original array nums.
 * 
 * For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left
 * child, and 3 as a right child. The array [2,3,1] also yields the same BST
 * but [3,2,1] yields a different BST.
 * 
 * Return the number of ways to reorder nums such that the BST formed is
 * identical to the original BST formed from nums.
 * 
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: nums = [2,1,3]
 * Output: 1
 * Explanation: We can reorder nums to be [2,3,1] which will yield the same
 * BST. There are no other ways to reorder nums which will yield the same
 * BST.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: nums = [3,4,5,1,2]
 * Output: 5
 * Explanation: The following 5 arrays will yield the same BST: 
 * [3,1,2,4,5]
 * [3,1,4,2,5]
 * [3,1,4,5,2]
 * [3,4,1,2,5]
 * [3,4,1,5,2]
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: nums = [1,2,3]
 * Output: 0
 * Explanation: There are no other orderings of nums that will yield the same
 * BST.
 * 
 * 
 * Example 4:
 * 
 * 
 * 
 * 
 * Input: nums = [3,1,2,5,4,6]
 * Output: 19
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: nums = [9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18]
 * Output: 216212978
 * Explanation: The number of ways to reorder nums to get the same BST is
 * 3216212999. Taking this number modulo 10^9 + 7 gives 216212978.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= nums.length
 * All integers in nums are distinct.
 * 
 * 
 */

// @lc code=start
class Solution {
	int[] nums;
	int[] left;
    int[] right;
    long[][] C;
    int mod = 1000000007;
    public int numOfWays(int[] nums) {
    	int n = nums.length;
    	this.nums = nums;
        left = new int[n];
        right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        for(int i = 1; i < n; i++){
        	int root = 0;
        	while(true){
	        	if(nums[root] > nums[i]){
	        		if(left[root] == -1){
	        			left[root] = i;
	        			break;
	        		}
	        		else{
	        			root = left[root];
	        		}
	        	}
	        	else{
	        		if(right[root] == -1){
	        			right[root] = i;
	        			break;
	        		}
	        		else{
	        			root = right[root];
	        		}	
	        	}
	        }
        }
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        C = precompute(n);
        // System.out.println(Arrays.deepToString(C));
        return (int)dfs(0)[0] - 1;
    }

    long[] dfs(int r){
    	if(r == -1){
    		return new long[]{1, 0};
    	}
    	if(left[r] == -1 && right[r] == -1){
    		return new long[]{1, 1};
    	}
    	else{
    		long[] ret = new long[2];
    		long[] lc = dfs(left[r]);
    		long[] rc = dfs(right[r]);
    		ret[1] = lc[1] + rc[1] + 1;
    		ret[0] = C[(int)(lc[1] + rc[1])][(int)(lc[1])];
    		ret[0] = (ret[0] * lc[0]) % mod;
    		ret[0] = (ret[0] * rc[0]) % mod;
    		// System.out.println(r + " = " + Arrays.toString(ret));
    		return ret;
    	}
    }

    long[][] precompute(int n){
    	long[][] combination = new long[n + 1][n + 1];	
    	combination[0][0] = 1;
    	for(int i = 1; i <= n; i++){
    		combination[i][0] = 1;
    		combination[i][1] = i;	
    		combination[i][i] = 1;	
    	}
    	
    	for(int i = 1; i <= n; i++){
    		for(int j = 1; j < i; j++){
    			combination[i][j] = (combination[i - 1][j - 1] + combination[i - 1][j]) % mod;
    		}
    	}
    	return combination;
    }
    long C(long n, long m){
    	long ans = 1;
    	for(int i = 1; i <= m; i++){
    		ans *= n - i + 1;
    		ans /= i;
    	}
    	return ans;
    }



}
// @lc code=end
