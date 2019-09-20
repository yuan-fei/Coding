/*
 * @lc app=leetcode id=164 lang=java
 *
 * [164] Maximum Gap
 *
 * https://leetcode.com/problems/maximum-gap/description/
 *
 * algorithms
 * Hard (33.32%)
 * Total Accepted:    75.3K
 * Total Submissions: 225.8K
 * Testcase Example:  '[3,6,9,1]'
 *
 * Given an unsorted array, find the maximum difference between the successive
 * elements in its sorted form.
 * 
 * Return 0 if the array contains less than 2 elements.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either
 * (3,6) or (6,9) has the maximum difference 3.
 * 
 * Example 2:
 * 
 * 
 * Input: [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 * 
 * Note:
 * 
 * 
 * You may assume all elements in the array are non-negative integers and fit
 * in the 32-bit signed integer range.
 * Try to solve it in linear time/space.
 * 
 * 
 */
class Solution {
    public int maximumGap(int[] nums) {
    	int N = nums.length;
    	if(N == 1){
    		return 0;
    	}
    	int min = Integer.MAX_VALUE;
    	int max = 0;
        for (int n : nums) {
        	min = Math.min(min, n);
        	max = Math.max(max, n);
        }
        int bucketSize = Math.max((max - min) / (N - 1), 1);
        int bucketNum = (max - min) / bucketSize + 1;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
        	buckets[i] = new Bucket();
        }
        for(int n : nums){
        	int id = (n - min) / bucketSize;
        	buckets[id].used = true;
        	buckets[id].min = Math.min(buckets[id].min, n);
        	buckets[id].max = Math.max(buckets[id].max, n);
        }
        int maxGap = 0;
        int lastMax = min;
        for (Bucket b : buckets) {
        	if(b.used){
        		maxGap = Math.max(b.min - lastMax, maxGap);
        		lastMax = b.max;
        	}
        }
        return maxGap;
    }

    class Bucket {
    	boolean used = false;
    	int min = Integer.MAX_VALUE;
    	int max = Integer.MIN_VALUE;
    }
}
