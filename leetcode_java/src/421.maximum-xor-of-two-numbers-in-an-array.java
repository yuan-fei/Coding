/*
 * @lc app=leetcode id=421 lang=java
 *
 * [421] Maximum XOR of Two Numbers in an Array
 *
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
 *
 * algorithms
 * Medium (53.04%)
 * Likes:    1032
 * Dislikes: 168
 * Total Accepted:    48.2K
 * Total Submissions: 90.9K
 * Testcase Example:  '[3,10,5,25,2,8]'
 *
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai <
 * 2^31.
 * 
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * 
 * Could you do this in O(n) runtime?
 * 
 * Example:
 * 
 * 
 * Input: [3, 10, 5, 25, 2, 8]
 * 
 * Output: 28
 * 
 * Explanation: The maximum result is 5 ^ 25 = 28.
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {

    public int findMaximumXOR(int[] nums) {
		TrieNode root = new TrieNode();
		insert(root, nums[0]);
		int max = 0;
		for (int i = 1; i < nums.length; i++) {
			max = Math.max(max, maxXor(root, nums[i]));
			insert(root, nums[i]);
		}	
		return max;
    }

    static class TrieNode{
    	TrieNode[] children = new TrieNode[2];
    }

    void insert(TrieNode root, int n){
    	for(int i = 31; i >= 0; i--){
    		int d = (n >> i) & 1;
    		if(root.children[d] == null){
    			root.children[d] = new TrieNode();
    		}
    		root = root.children[d];
    	}
    }

    int maxXor(TrieNode root, int n){
    	int xor = 0;
    	for(int i = 31; i >= 0; i--){
    		int d = (n >> i) & 1;
    		if(root.children[1 - d] != null){
    			root = root.children[1 - d];
    			xor ^= (1 << i);
    		}
    		else{
    			root = root.children[d];	
    		}
    	}
    	return xor;
    }
}
// @lc code=end
