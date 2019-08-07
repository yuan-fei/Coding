/*
 * @lc app=leetcode id=1093 lang=java
 *
 * [1093] Statistics from a Large Sample
 *
 * https://leetcode.com/problems/statistics-from-a-large-sample/description/
 *
 * algorithms
 * Medium (43.34%)
 * Total Accepted:    4.4K
 * Total Submissions: 10.2K
 * Testcase Example:  '[0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]'
 *
 * We sampled integers between 0 and 255, and stored the results in an array
 * count:  count[k] is the number of integers we sampled equal to k.
 * 
 * Return the minimum, maximum, mean, median, and mode of the sample
 * respectively, as an array of floating point numbers.  The mode is guaranteed
 * to be unique.
 * 
 * (Recall that the median of a sample is:
 * 
 * 
 * The middle element, if the elements of the sample were sorted and the number
 * of elements is odd;
 * The average of the middle two elements, if the elements of the sample were
 * sorted and the number of elements is even.)
 * 
 * 
 * 
 * Example 1:
 * Input: count =
 * [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
 * Example 2:
 * Input: count =
 * [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: [1.00000,4.00000,2.18182,2.00000,1.00000]
 * 
 * 
 * Constraints:
 * 
 * 
 * count.length == 256
 * 1 <= sum(count) <= 10^9
 * The mode of the sample that count represents is unique.
 * Answers within 10^-5 of the true value will be accepted as correct.
 * 
 * 
 */
class Solution {
    public double[] sampleStats(int[] count) {
        double max=-1; 
        double min=256;
        double cnt = 0;
        double sum = 0;
        double mode = -1;
        int maxCnt = 0;
        for(int i = 0; i < 256; i++){
            if(count[i]>0){
                min=Math.min(min, i);
                max=Math.max(max, i);
                sum+=count[i]*i;
                cnt+=count[i];
                if(maxCnt<count[i]){
                    mode=i;
                    maxCnt=count[i];
                }
            }
        }
        double median = 0;
        boolean fi = true;
        int halfCnt = 0;
        for(int i = 0; i < 256; i++){
            if(count[i]>0){
                if(cnt%2==0&&fi){
                    if(halfCnt + count[i]>=cnt/2){
                        median=i;
                        fi=false;
                    }
                }
                if(halfCnt + count[i]>=cnt/2+1){
                    median+=i;
                    break;
                }
                halfCnt+=count[i];
            }
        }
        if(cnt%2==0){
            median/=2;
        }
        return new double[]{min,max,sum/cnt,median,mode};
    }
}
