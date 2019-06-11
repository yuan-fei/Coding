/*
 * [480] Sliding Window Median
 *
 * https://leetcode.com/problems/sliding-window-median/description/
 *
 * algorithms
 * Hard (30.90%)
 * Total Accepted:    24.8K
 * Total Submissions: 76.7K
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
 *
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * Examples: 
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5 
 * 
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one
 * position. Your job is to output the median array for each window in the
 * original array.
 * 
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 * 
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * ⁠1 [3  -1  -3] 5  3  6  7       -1
 * ⁠1  3 [-1  -3  5] 3  6  7       -1
 * ⁠1  3  -1 [-3  5  3] 6  7       3
 * ⁠1  3  -1  -3 [5  3  6] 7       5
 * ⁠1  3  -1  -3  5 [3  6  7]      6
 * 
 * 
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 * 
 * Note: 
 * You may assume k is always valid, ie: k is always smaller than input array's
 * size for non-empty array.
 */
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        TreeMap<Double, Integer> lt = new TreeMap<>();
        TreeMap<Double, Integer> rt = new TreeMap<>();
        int lCnt = 0;
        int rCnt = 0;
        int n = nums.length;
        double[] ret = new double[n-k+1];
        for(int i = 0; i < k; i++){
        	lt.put((double)nums[i], lt.getOrDefault((double)nums[i], 0) + 1);
        }
        for(int i = 0; i < k/2; i++){
        	double lk = lt.lastKey();
        	int lv = lt.get(lk) - 1;
        	lt.put(lk, lv);
        	lt.remove(lk, 0);
        	rt.put(lk, rt.getOrDefault(lk, 0) + 1);
        }
        lCnt = (k+1)/2;
        rCnt = k/2;
        if(k%2==0){
        	ret[0] = (lt.lastKey()+rt.firstKey())/2;
        }
		else{
			ret[0] = lt.lastKey();
		}

        for(int i = k; i < n; i++){
        	if(lt.containsKey((double)nums[i-k])){
        		int lv = lt.get((double)nums[i-k]) - 1;
        		lt.put((double)nums[i-k], lv);
        		lt.remove((double)nums[i-k], 0);
        		lCnt--;
        	}
        	else{
        		int rv = rt.get((double)nums[i-k]) - 1;
        		rt.put((double)nums[i-k], rv);
        		rt.remove((double)nums[i-k], 0);
        		rCnt--;
        	}
        	if(lt.isEmpty() || lt.lastKey()>=(double)nums[i]){
        		lt.put((double)nums[i], lt.getOrDefault((double)nums[i], 0) + 1);
        		lCnt++;		
        		if(lCnt - rCnt >= 2){
	        		double lk = lt.lastKey();
		        	int lv = lt.get(lk) - 1;
		        	lt.put(lk, lv);
		        	lt.remove(lk, 0);
		        	rt.put(lk, rt.getOrDefault(lk, 0) + 1);
		        	lCnt--;
		        	rCnt++;
	        	}
        	}
        	else{
        		rt.put((double)nums[i], rt.getOrDefault((double)nums[i], 0) + 1);
        		rCnt++;
        		if(lCnt < rCnt){
	        		double rk = rt.firstKey();
		        	int rv = rt.get(rk) - 1;
		        	rt.put(rk, rv);
		        	rt.remove(rk, 0);
		        	lt.put(rk, lt.getOrDefault(rk, 0) + 1);
		        	lCnt++;
		        	rCnt--;
	        	}
        	}
        	
        	
        	
        	// System.out.println("l: "+lt);
        	// System.out.println("r: "+rt);
        	if(k%2==0){
	        	ret[i-k+1] = (lt.lastKey()+rt.firstKey())/2;
	        }
			else{
				ret[i-k+1] = lt.lastKey();
			}
        }
        return ret;
    }
}
