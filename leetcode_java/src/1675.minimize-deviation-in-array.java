/*
 * @lc app=leetcode id=1675 lang=java
 *
 * [1675] Minimize Deviation in Array
 *
 * https://leetcode.com/problems/minimize-deviation-in-array/description/
 *
 * algorithms
 * Hard (29.76%)
 * Likes:    35
 * Dislikes: 5
 * Total Accepted:    652
 * Total Submissions: 2.2K
 * Testcase Example:  '[1,2,3,4]'
 *
 * You are given an array nums of n positive integers.
 * 
 * You can perform two types of operations on any element of the array any
 * number of times:
 * 
 * 
 * If the element is even, divide it by 2.
 * 
 * 
 * For example, if the array is [1,2,3,4], then you can do this operation on
 * the last element, and the array will be [1,2,3,2].
 * 
 * 
 * If the element is odd, multiply it by 2.
 * 
 * For example, if the array is [1,2,3,4], then you can do this operation on
 * the first element, and the array will be [2,2,3,4].
 * 
 * 
 * 
 * 
 * The deviation of the array is the maximum difference between any two
 * elements in the array.
 * 
 * Return the minimum deviation the array can have after performing some number
 * of operations.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: 1
 * Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2],
 * then the deviation will be 3 - 2 = 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,1,5,20,3]
 * Output: 3
 * Explanation: You can transform the array after two operations to
 * [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [2,10,8]
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 2 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
	public int minimumDeviation(int[] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int n = A.length, mi = Integer.MAX_VALUE, res = Integer.MAX_VALUE;
        for (int a : A) {
            if (a % 2 == 1) a *= 2;
            pq.add(-a);
            mi = Math.min(mi, a);
        }
        while (true) {
            int a = -pq.poll();
            if(a - mi < res){
            	System.out.println(mi + "->" + a + "=" + (a - mi));
            	res = Math.min(res, a - mi);
            }
            
            if (a % 2 == 1) break;
            mi = Math.min(mi, a / 2);
            pq.add(-a / 2);
        }
        return res;
    }

    public int minimumDeviation1(int[] nums) {
        Set<Pair> sections = new HashSet<>();
        List<Integer> candidates = getCandidates(nums[0]);
        for(int cand : candidates){
        	sections.add(new Pair(cand, cand));
        }
		System.out.println(sections);
        for(int i = 1; i < nums.length; i++){
        	candidates = getCandidates(nums[i]);
        	Set<Pair> sectionsNew = new HashSet<>();
        	for(int cand : candidates){
        		List<Pair> min = new ArrayList<>(Arrays.asList(new Pair(0, Integer.MAX_VALUE)));
	        	for(Pair section : sections){
	        		Pair tmp = new Pair(Math.min(section.min, cand), Math.max(section.max, cand));
	        		if(tmp.diff() == min.get(0).diff()){
	        			min.add(tmp);
	        		}
	        		else if(tmp.diff() < min.get(0).diff()){
	        			min = new ArrayList<>(Arrays.asList(tmp));
	        		}
	        	}	
	        	sectionsNew.addAll(min);
        	}
        	sections = sectionsNew;
        	System.out.println(sections);
        }
        int minDiff = Integer.MAX_VALUE;
        for(Pair section : sections){
        	minDiff = Math.min(minDiff, section.diff());
        }
        return minDiff;
    }

    List<Integer> getCandidates(int x){
    	List<Integer> candidates = new ArrayList<>();
    	if(x % 2 == 0){
	    	while(x % 2 == 0){
	    		candidates.add(x);
	    		x /= 2;
	    	}
	    	candidates.add(x);	
    	}
    	else{
    		candidates.add(x);
    		candidates.add(x * 2);
    	}
    	return candidates;
    }

    class Pair {
    	int min;
    	int max;
    	Pair(int min, int max){
    		this.min = min;
    		this.max = max;
    	}

    	int diff(){
    		return max - min;
    	}

    	public boolean equals(Object pair){
    		Pair that = (Pair)pair;
    		return that.min == that.min && this.max == that.max;
    	}

    	public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + max;
			result = prime * result + min;
			return result;
		}

		public String toString(){
			return min + "->" + max  + "=" + diff();
		}
    }
}
// @lc code=end
