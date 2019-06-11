/*
 * @lc app=leetcode id=992 lang=java
 *
 * [992] Subarrays with K Different Integers
 *
 * https://leetcode.com/problems/subarrays-with-k-different-integers/description/
 *
 * algorithms
 * Hard (44.75%)
 * Total Accepted:    5.9K
 * Total Submissions: 13.2K
 * Testcase Example:  '[1,2,1,2,3]\n2'
 *
 * Given an array A of positive integers, call a (contiguous, not necessarily
 * distinct) subarray of A good if the number of different integers in that
 * subarray is exactly K.
 * 
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 * 
 * Return the number of good subarrays of A.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: A = [1,2,1,2,3], K = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2],
 * [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: A = [1,2,1,3,4], K = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3],
 * [2,1,3], [1,3,4].
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 * 
 */
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K)-atMostKDistinct(A, K-1);
    }

    private int atMostKDistinct(int[] A, int K){
    	Map<Integer,Integer> cntMap = new HashMap<>();
        int left = 0;
        int ways = 0;
        for(int i = 0; i<A.length; i++){
        	int cnt = 0;
        	if(cntMap.containsKey(A[i])){
        		cnt = cntMap.get(A[i]);
        	}
        	cntMap.put(A[i], cnt+1);
        	
        	while(cntMap.size() > K){
    			cnt = cntMap.get(A[left]) - 1;
    			if(cnt==0){
    				cntMap.remove(A[left]);
    			}
    			else{
    				cntMap.put(A[left], cnt);
    			}
    			left++;
    		}
        	ways+=i-left+1;
        }
        return ways;
    }

    public int subarraysWithKDistinct1(int[] A, int K) {
    	Map<Integer,Integer> cntMap = new HashMap<>();
        int left = 0;
        int lastRemove=-1;
        int curWays = 0;
        int ways = 0;
        for(int i = 0; i<A.length; i++){
        	int cnt = 0;
        	if(cntMap.containsKey(A[i])){
        		cnt = cntMap.get(A[i]);
        	}
        	cntMap.put(A[i], cnt+1);
        	if(cntMap.size() == K-1 && lastRemove==A[i]){
        		
        	}
        	else if(cntMap.size() == K){
        		if(lastRemove!=A[i]){
        			curWays=0;
        		}
        		while(cntMap.size() == K){
        			cnt = cntMap.get(A[left]) - 1;
        			if(cnt==0){
        				cntMap.remove(A[left]);
        				lastRemove=A[left];
        			}
        			else{
        				cntMap.put(A[left], cnt);
        			}
        			curWays++;
        			left++;
        		}
        	}
        	ways+=curWays;
        }
        return ways;
    }
}
