/*
 * @lc app=leetcode id=1707 lang=java
 *
 * [1707] Maximum XOR With an Element From Array
 *
 * https://leetcode.com/problems/maximum-xor-with-an-element-from-array/description/
 *
 * algorithms
 * Hard (43.08%)
 * Likes:    82
 * Dislikes: 7
 * Total Accepted:    2.3K
 * Total Submissions: 5.2K
 * Testcase Example:  '[0,1,2,3,4]\n[[3,1],[1,3],[5,6]]'
 *
 * You are given an array nums consisting of non-negative integers. You are
 * also given a queries array, where queries[i] = [xi, mi].
 * 
 * The answer to the i^th query is the maximum bitwise XOR value of xi and any
 * element of nums that does not exceed mi. In other words, the answer is
 * max(nums[j] XOR xi) for all j such that nums[j] <= mi. If all elements in
 * nums are larger than mi, then the answer is -1.
 * 
 * Return an integer array answer where answer.length == queries.length and
 * answer[i] is the answer to the i^th query.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * Output: [3,3,7]
 * Explanation:
 * 1) 0 and 1 are the only two integers not greater than 1. 0 XOR 3 = 3 and 1
 * XOR 3 = 2. The larger of the two is 3.
 * 2) 1 XOR 2 = 3.
 * 3) 5 XOR 2 = 7.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * Output: [15,-1,5]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length, queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= nums[j], xi, mi <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
    	Integer[] idx = new Integer[queries.length];
    	for(int i = 0; i < queries.length; i++){
    		idx[i] = i;
    	}
        Arrays.sort(idx, (a, b) -> Integer.compare(queries[a][1], queries[b][1]));
        Arrays.sort(nums);
        int[] ans = new int[queries.length];
        int j = 0;
        Trie root = new Trie(0);
        for(int i = 0; i < queries.length; i++){
        	while(j < nums.length && nums[j] <= queries[idx[i]][1]){
        		insert(root, nums[j]);
        		j++;
        	}
        	ans[idx[i]] = findMaxXor(root, queries[idx[i]][0]);
        }
        return ans;
    }

    class Trie{
    	int val;
    	Trie[] children = new Trie[2];
    	Trie(int v){
    		val = v;
    	}
    }

    void insert(Trie root, int n){
    	for(int i = 31; i >= 0; i--){
    		int v = (n >> i) & 1;
    		if(root.children[v] == null){
    			root.children[v] = new Trie(v);
    		}
    		root = root.children[v];
    	}
    }

    int findMaxXor(Trie root, int n){
    	// System.out.println(n);
    	int ret = 0;
    	for(int i = 31; i >= 0; i--){
    		int v = (n >> i) & 1;
    		if(root.children[1 - v] == null && root.children[v] == null){
    			return -1;
    		}
    		else if(root.children[1 - v] == null){
    			root = root.children[v];
    			ret &= ~(1 << i);
    			// System.out.print(v);
    		}
    		else{
    			root = root.children[1 - v];
    			ret |= 1 << i;
    			// System.out.print(1 - v);
    		}
    	}
    	// System.out.println(ret);
    	return ret;
    }
}
// @lc code=end
