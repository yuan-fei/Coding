/*
 * @lc app=leetcode id=1562 lang=java
 *
 * [1562] Find Latest Group of Size M
 *
 * https://leetcode.com/problems/find-latest-group-of-size-m/description/
 *
 * algorithms
 * Medium (37.64%)
 * Likes:    182
 * Dislikes: 36
 * Total Accepted:    6.1K
 * Total Submissions: 16.1K
 * Testcase Example:  '[3,5,1,2,4]\n1'
 *
 * Given an array arr that represents a permutation of numbers from 1 to n. You
 * have a binary string of size n that initially has all its bits set to zero.
 * 
 * At each step i (assuming both the binary string and arr are 1-indexed) from
 * 1 to n, the bit at position arr[i] is set to 1. You are given an integer m
 * and you need to find the latest step at which there exists a group of ones
 * of length m. A group of ones is a contiguous substring of 1s such that it
 * cannot be extended in either direction.
 * 
 * Return the latest step at which there exists a group of ones of length
 * exactly m. If no such group exists, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [3,5,1,2,4], m = 1
 * Output: 4
 * Explanation:
 * Step 1: "00100", groups: ["1"]
 * Step 2: "00101", groups: ["1", "1"]
 * Step 3: "10101", groups: ["1", "1", "1"]
 * Step 4: "11101", groups: ["111", "1"]
 * Step 5: "11111", groups: ["11111"]
 * The latest step at which there exists a group of size 1 is step 4.
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [3,1,5,4,2], m = 2
 * Output: -1
 * Explanation:
 * Step 1: "00100", groups: ["1"]
 * Step 2: "10100", groups: ["1", "1"]
 * Step 3: "10101", groups: ["1", "1", "1"]
 * Step 4: "10111", groups: ["1", "111"]
 * Step 5: "11111", groups: ["11111"]
 * No group of size 2 exists during any step.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [1], m = 1
 * Output: 1
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: arr = [2,1], m = 2
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == arr.length
 * 1 <= n <= 10^5
 * 1 <= arr[i] <= n
 * All integers in arr are distinct.
 * 1 <= m <= arr.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findLatestStep(int[] arr, int m) {
    	this.m = m;
    	int n = arr.length;
    	parent = new int[n + 2];
    	size = new int[n + 2];
    	int last = -1;
    	for(int i = 0; i < arr.length; i++){
    		makeSet(arr[i]);

    		if(size[arr[i] - 1] != 0){
    			union(arr[i], arr[i] - 1);
    		}
    		// System.out.println(Arrays.toString(parent));
    		// System.out.println(Arrays.toString(size));
    		// System.out.println(s);
    		if(size[arr[i] + 1] != 0){
    			union(arr[i], arr[i] + 1);
    		}
    		// System.out.println(Arrays.toString(parent));
    		// System.out.println(Arrays.toString(size));
    		// System.out.println(s);
    		if(!s.isEmpty()){
    			last = i + 1;
    		}
    	}
    	return last;
    }
    int[] parent;
    int[] size;
    int m;
    Set<Integer> s = new HashSet<>();
    void setSize(int x, int sz){
    	size[x] = sz;
    	if(sz == m){
    		s.add(x);
    	}
    	else{
    		s.remove(x);
    	}
    }

    void makeSet(int x){
    	parent[x] = x;
    	setSize(x, 1);
    }

    int find(int x){
    	// System.out.println(x + ", " + parent[x]);
    	if(parent[x] != x){
    		parent[x] = find(parent[x]);
    		// setSize(x, size[parent[x]]);
    	}
    	return parent[x];
    }

    void union(int x, int y){
    	// System.out.println(x + ", ");
    	// System.out.println(y + ", ");
    	// System.out.println(Arrays.toString(parent));
    	int px = find(x);
    	int py = find(y);
    	// System.out.println(x + ", " +px);
    	// System.out.println(y + ", " + py);
    	if(px != py){
    		parent[px] = py;
    		setSize(py, size[py] + size[px]);
    		if(size[px] == m){
    			s.remove(px);
    		}
    		size[px] = size[py];
    	}
    }
}
// @lc code=end
