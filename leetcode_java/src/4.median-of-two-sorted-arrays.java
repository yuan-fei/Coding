/*
 * [4] Median of Two Sorted Arrays
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays
 *
 * algorithms
 * Hard (22.39%)
 * Total Accepted:    213.4K
 * Total Submissions: 952.9K
 * Testcase Example:  '[1,3]\n[2]'
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 * 
 * Example 1:
 * 
 * nums1 = [1, 3]
 * nums2 = [2]
 * 
 * The median is 2.0
 * 
 * 
 * 
 * Example 2:
 * 
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 
 * The median is (2 + 3)/2 = 2.5
 * 
 * 
 */

/*	
 *  Assumption: 
 *  	A is partitioned by i, B is partitioned by j
 *		Left = {A[0..i-1], B[0..j-1]}, Right = {A[i..m-1], B[j..n-1]}
 *	
 *	Then 'meadian' means
 *		1. len(Left) = len(right) => i+j == m+n-i-j (or m+n-i-j+1)
 *		2. max(left) <= min(right) => A[i] >= B[j-1] && B[j] >= A[i-1]
 *	
 *	meadian = [max(A[i-1],B[j-1])+min(A[i],B[j])]/2
 *	
 *	Time complexcity O(log(min(m,n)))
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] largeArray, smallArray;
        if(nums1.length > nums2.length){
        	largeArray = nums1;
        	smallArray = nums2;
        }
        else{
        	largeArray = nums2;
        	smallArray = nums1;	
        }
        int m = smallArray.length;
        int n = largeArray.length;
        int iMin,iMax,i,j;
        
        
        for (iMin = 0, iMax = m; iMin <= iMax; ) {
        	i = (iMin+iMax)/2;
        	j = (m+n+1)/2-i;
	        if(i < iMax  && smallArray[i] < largeArray[j-1]){
	        	iMin = i+1;
	        }
	        else if(i > iMin && smallArray[i-1] > largeArray[j]){
	        	iMax = i-1;
	        }
	        else{
	        	int maxLeft, minRight;
	        	if(i == 0){
	        		maxLeft = largeArray[j-1];
	        	}
	        	else if (j == 0) { 
	        		maxLeft = smallArray[i-1]; 
	        	}
	        	else{
	        		maxLeft = Math.max(smallArray[i-1], largeArray[j-1]);	
	        	}

	        	if ( (m + n) % 2 == 1 ) { return maxLeft; }

	        	if(i == m){
	        		minRight = largeArray[j];
	        	}
	        	else if (j == n) {
	        		minRight = smallArray[i]; 
	        	}
	        	else{
	        		minRight = Math.min(smallArray[i], largeArray[j]);	
	        	}
	        	return (maxLeft + minRight) / 2.0;
	        }
        }
        return 0.0;
        
    }
}
