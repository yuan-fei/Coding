/*
 * @lc app=leetcode id=1131 lang=java
 *
 * [1131] Maximum of Absolute Value Expression
 *
 * https://leetcode.com/problems/maximum-of-absolute-value-expression/description/
 *
 * algorithms
 * Medium (50.10%)
 * Total Accepted:    2.6K
 * Total Submissions: 5.3K
 * Testcase Example:  '[1,2,3,4]\n[-1,4,5,6]'
 *
 * Given two arrays of integers with equal lengths, return the maximum value
 * of:
 * 
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 * 
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 * Output: 13
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 * Output: 20
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= arr1.length == arr2.length <= 40000
 * -10^6 <= arr1[i], arr2[i] <= 10^6
 * 
 * 
 */
class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int n = arr1.length;
        
        int[] min = new int[4];
        int[] max = new int[4];
        for(int i = 0 ; i < n; i++){
            int j = 0;
            for(int m1: new int[]{-1,1}){
                for(int m2: new int[]{-1,1}){
                    int v = arr1[i]*m1+arr2[i]*m2+i;
                    min[j] = Math.min(v, min[j]);
                    max[j] = Math.max(v, max[j]);
                    j++;
                }
            }    
        }
        int ansMax =0;
        for(int i = 0; i < 4; i++){
            ansMax = Math.max(max[i]-min[i], ansMax);
        }
        
        return ansMax;
    }
    
    public int maxAbsValExpr0(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int[] max = new int[8];
        int[] min = new int[8];
        int[][] arr = new int[8][n];
        Arrays.fill(max, Integer.MIN_VALUE);
        Arrays.fill(min, Integer.MAX_VALUE);
        
        for(int i = 0 ; i < n; i++){
            int j = 0;
            for(int m1: new int[]{-1,1}){
                for(int m2: new int[]{-1,1}){
                    for(int m3: new int[]{-1,1}){
                        arr[j++][i]=arr1[i]*m1+arr2[i]*m2+i*m3;
                    }
                }
            }    
        }
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < 8; j++){
                min[j] = Math.min(arr[j][i],min[j]);
                max[j] = Math.max(arr[j][i]-min[j],max[j]);
            }
        }
        // System.out.println(Arrays.deepToString(arr));
        // System.out.println(Arrays.toString(max));
        int ansMax = 0;
        for(int i = 0 ; i < 8; i++){
            ansMax = Math.max(max[i], ansMax);
        }
        return ansMax;
    }
}
