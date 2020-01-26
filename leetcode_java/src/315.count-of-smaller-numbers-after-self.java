/*
 * @lc app=leetcode id=315 lang=java
 *
 * [315] Count of Smaller Numbers After Self
 *
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
 *
 * algorithms
 * Hard (40.30%)
 * Likes:    1670
 * Dislikes: 66
 * Total Accepted:    102.7K
 * Total Submissions: 254.7K
 * Testcase Example:  '[5,2,6,1]'
 *
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 * 
 * Example:
 * 
 * 
 * Input: [5,2,6,1]
 * Output: [2,1,1,0] 
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * 
 */

// @lc code=start
class Solution {
	int[] a;
	int N;
    public List<Integer> countSmaller(int[] nums) {
    	build(nums.length);
    	Map<Integer, Integer> ranks  = compress(nums);
    	List<Integer> ans = new ArrayList<>();
    	for(int i = N - 1; i >= 0; i--){
    		int rk = ranks.get(nums[i]);
    		ans.add(pSum(rk - 1));
    		update(rk);
    	}
    	Collections.reverse(ans);
    	return ans;
    }

    Map<Integer, Integer> compress(int[] nums){
    	int[] numsCopy = Arrays.copyOf(nums, nums.length);
    	Arrays.sort(numsCopy);
    	Map<Integer, Integer> ret = new HashMap<>();
    	for (int i = 0; i < numsCopy.length; i++) {
    		ret.put(numsCopy[i], i + 1);
    	}
    	return ret;
    }

    void build(int n){
    	N = n;
    	a = new int[N + 1];
    }

    void update(int i){
    	while(i <= N){
    		a[i]++;
    		i += (i & -i);
    	}
    }

    int pSum(int i){
    	int sum = 0;
    	while(i > 0){
    		sum += a[i];
    		i &= i - 1;
    	}
    	return sum;
    }

}
// @lc code=end
