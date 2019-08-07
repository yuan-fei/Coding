/*
 * @lc app=leetcode id=1090 lang=java
 *
 * [1090] Largest Values From Labels
 *
 * https://leetcode.com/problems/largest-values-from-labels/description/
 *
 * algorithms
 * Medium (57.33%)
 * Total Accepted:    5.4K
 * Total Submissions: 9.3K
 * Testcase Example:  '[5,4,3,2,1]\n[1,1,2,2,3]\n3\n1'
 *
 * We have a set of items: the i-th item has value values[i] and label
 * labels[i].
 * 
 * Then, we choose a subset S of these items, such that:
 * 
 * 
 * |S| <= num_wanted
 * For every label L, the number of items in S with label L is <= use_limit.
 * 
 * 
 * Return the largest possible sum of the subset S.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit
 * = 1
 * Output: 9
 * Explanation: The subset chosen is the first, third, and fifth item.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit
 * = 2
 * Output: 12
 * Explanation: The subset chosen is the first, second, and third item.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit
 * = 1
 * Output: 16
 * Explanation: The subset chosen is the first and fourth item.
 * 
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit
 * = 2
 * Output: 24
 * Explanation: The subset chosen is the first, second, and fourth item.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= values.length == labels.length <= 20000
 * 0 <= values[i], labels[i] <= 20000
 * 1 <= num_wanted, use_limit <= values.length
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int n = values.length;
        int[][] t = new int[n][2];
        for(int i = 0; i < n; i++){
            t[i] = new int[]{values[i], labels[i]};
        }
        Arrays.sort(t, Comparator.comparingInt(o->-o[0]));
        Map<Integer, Integer> cnt = new HashMap<>();
        int sum = 0;
        for(int i = 0; i < n; i++){
            if(num_wanted>0){
                int c = cnt.getOrDefault(t[i][1], 0);
                if(c<use_limit){
                    sum+=t[i][0];
                    num_wanted--;
                }    
                cnt.put(t[i][1], c+1);
            }
            else{
                break;
            }
        }
        return sum;
    }
}
