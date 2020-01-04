/*
 * @lc app=leetcode id=274 lang=java
 *
 * [274] H-Index
 *
 * https://leetcode.com/problems/h-index/description/
 *
 * algorithms
 * Medium (35.07%)
 * Likes:    488
 * Dislikes: 825
 * Total Accepted:    137.5K
 * Total Submissions: 391.9K
 * Testcase Example:  '[3,0,6,1,5]'
 *
 * Given an array of citations (each citation is a non-negative integer) of a
 * researcher, write a function to compute the researcher's h-index.
 * 
 * According to the definition of h-index on Wikipedia: "A scientist has index
 * h if h of his/her N papers have at least h citations each, and the other N −
 * h papers have no more than h citations each."
 * 
 * Example:
 * 
 * 
 * Input: citations = [3,0,6,1,5]
 * Output: 3 
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each
 * of them had 
 * ⁠            received 3, 0, 6, 1, 5 citations respectively. 
 * Since the researcher has 3 papers with at least 3 citations each and the
 * remaining 
 * two with no more than 3 citations each, her h-index is 3.
 * 
 * Note: If there are several possible values for h, the maximum one is taken
 * as the h-index.
 * 
 */

// @lc code=start
class Solution {
    public int hIndex(int[] citations) {
        int low = 0;
        int high = citations.length;
        while(low + 1 < high){
        	int mid = (low + high) / 2;
        	if(check(citations, mid)){
        		low = mid;
        	}
        	else{
        		high = mid;
        	}
        }
        if(check(citations, high)){
        	return high;
        }
        else{
        	return low;
        }
    }

    boolean check(int[] citations, int max){
    	int n = citations.length;
    	int cnt = 0;
    	for(int i = 0; i < n; i++){
    		if(citations[i] >= max){
    			cnt++;
    		}
    	}
    	return cnt >= max;
    }
}
// @lc code=end
