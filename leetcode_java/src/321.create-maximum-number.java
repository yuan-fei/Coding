/*
 * @lc app=leetcode id=321 lang=java
 *
 * [321] Create Maximum Number
 *
 * https://leetcode.com/problems/create-maximum-number/description/
 *
 * algorithms
 * Hard (26.14%)
 * Likes:    521
 * Dislikes: 185
 * Total Accepted:    34.8K
 * Total Submissions: 132.9K
 * Testcase Example:  '[3,4,6,5]\n[9,1,2,5,8,3]\n5'
 *
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 * Create the maximum number of length k <= m + n from digits of the two. The
 * relative order of the digits from the same array must be preserved. Return
 * an array of the k digits.
 * 
 * Note: You should try to optimize your time and space complexity.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * Output:
 * [9, 8, 6, 5, 3]
 * 
 * Example 2:
 * 
 * 
 * Input:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * Output:
 * [6, 7, 6, 0, 4]
 * 
 * Example 3:
 * 
 * 
 * Input:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * Output:
 * [9, 8, 9]
 * 
 */

// @lc code=start
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
    	int[] ans = null;
        for(int i = 0; i <= k; i++){
        	if(i <= nums1.length && k - i <= nums2.length){
        		int[] a1 = maxNumberSingleArray(nums1, i);
        		int[] a2 = maxNumberSingleArray(nums2, k - i);
        		int[] cand = merge(a1, a2);
        		// System.out.println(Arrays.toString(a1) + " + " + Arrays.toString(a2) + " = " +Arrays.toString(cand));
        		if(ans == null || greater(cand, ans, 0, 0)){
        			ans = cand;
        		}
        	}
        }
        return ans == null ? new int[0] : ans;
    }

    private int[] maxNumberSingleArray(int[] num, int k){
    	int n = num.length;
    	int[] ret = new int[k];
    	Deque<Integer> q = new LinkedList<>();
    	for(int i = 0; i < n; i++){
    		while(!q.isEmpty() && num[q.peekLast()] < num[i] && n - i + q.size() > k){
    			q.pollLast();	
    		}
    		q.offerLast(i);
    	}
    	// System.out.println(k + ", " + q);
    	for(int i = 0; i < k; i++){
    		ret[i] = num[q.poll()];
    	}
    	return ret;
    }

    private int[] merge(int[] a1, int[] a2){
    	int[] ret = new int[a1.length + a2.length];
    	int i = 0;
    	int j = 0;
    	int k = 0;
    	while(i < a1.length && j < a2.length){
			if(greater(a1, a2, i, j)){
				ret[k++] = a1[i++];
			}
			else{
				ret[k++] = a2[j++];	
			}
    	}
    	while(i < a1.length){
    		ret[k++] = a1[i++];
    	}
    	while(j < a2.length){
    		ret[k++] = a2[j++];
    	}
    	return ret;
    }

    private boolean greater(int[] a1, int[] a2, int s1, int s2){

    	while(s1 < a1.length && s2 < a2.length){
    		if(a1[s1] < a2[s2]){
    			return false;
    		}
    		else if(a2[s2] < a1[s1]){
    			return true;
    		}
    		s1++;
    		s2++;
    	}
    	if(s2 < a2.length){
    		return false;
    	}
    	else{
    		return true;
    	}
    	
    }
}
// @lc code=end
