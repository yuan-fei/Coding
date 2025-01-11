/*
 * @lc app=leetcode id=3013 lang=java
 *
 * [3013] Divide an Array Into Subarrays With Minimum Cost II
 *
 * https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/description/
 *
 * algorithms
 * Hard (30.86%)
 * Likes:    123
 * Dislikes: 18
 * Total Accepted:    5.6K
 * Total Submissions: 18.1K
 * Testcase Example:  '[1,3,2,6,4,2]\n3\n3'
 *
 * You are given a 0-indexed array of integers nums of length n, and two
 * positive integers k and dist.
 * 
 * The cost of an array is the value of its first element. For example, the
 * cost of [1,2,3] is 1 while the cost of [3,4,1] is 3.
 * 
 * You need to divide nums into k disjoint contiguous subarrays, such that the
 * difference between the starting index of the second subarray and the
 * starting index of the kth subarray should be less than or equal to dist. In
 * other words, if you divide nums into the subarrays nums[0..(i1 - 1)],
 * nums[i1..(i2 - 1)], ..., nums[ik-1..(n - 1)], then ik-1 - i1 <= dist.
 * 
 * Return the minimum possible sum of the cost of these subarrays.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,2,6,4,2], k = 3, dist = 3
 * Output: 5
 * Explanation: The best possible way to divide nums into 3 subarrays is:
 * [1,3], [2,6,4], and [2]. This choice is valid because ik-1 - i1 is 5 - 2 = 3
 * which is equal to dist. The total cost is nums[0] + nums[2] + nums[5] which
 * is 1 + 2 + 2 = 5.
 * It can be shown that there is no possible way to divide nums into 3
 * subarrays at a cost lower than 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [10,1,2,2,2,1], k = 4, dist = 3
 * Output: 15
 * Explanation: The best possible way to divide nums into 4 subarrays is: [10],
 * [1], [2], and [2,2,1]. This choice is valid because ik-1 - i1 is 3 - 1 = 2
 * which is less than dist. The total cost is nums[0] + nums[1] + nums[2] +
 * nums[3] which is 10 + 1 + 2 + 2 = 15.
 * The division [10], [1], [2,2,2], and [1] is not valid, because the
 * difference between ik-1 and i1 is 5 - 1 = 4, which is greater than dist.
 * It can be shown that there is no possible way to divide nums into 4
 * subarrays at a cost lower than 15.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [10,8,18,9], k = 3, dist = 1
 * Output: 36
 * Explanation: The best possible way to divide nums into 4 subarrays is: [10],
 * [8], and [18,9]. This choice is valid because ik-1 - i1 is 2 - 1 = 1 which
 * is equal to dist.The total cost is nums[0] + nums[1] + nums[2] which is 10 +
 * 8 + 18 = 36.
 * The division [10], [8,18], and [9] is not valid, because the difference
 * between ik-1 and i1 is 3 - 1 = 2, which is greater than dist.
 * It can be shown that there is no possible way to divide nums into 3
 * subarrays at a cost lower than 36.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 * 3 <= k <= n
 * k - 2 <= dist <= n - 2
 * 
 * 
 */

// @lc code=start
class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        TopKSum tks = new TopKSum(k - 1);
        long ans = Long.MAX_VALUE;
        for(int i = 1; i + dist < n; i++){
            if(i == 1){
                for(int j = 1; j <= dist + 1; j++){
                    tks.add(new int[]{nums[j], j});
                }
                // System.out.println(tks.getSum());
            }
            else{
                tks.remove(new int[]{nums[i - 1], i - 1});
                // System.out.println(tks.getSum());
                tks.add(new int[]{nums[i + dist], i + dist});
                // System.out.println(tks.getSum());
            }
            
            ans = Math.min(ans, tks.getSum());
            
        }
        return ans + nums[0];
    }

    static class TopKSum {
        int k;
        TopKSum(int k){ this.k = k; }

        TreeSet<int[]> ts = new TreeSet<>(Comparator.comparing((int[] x) -> x[0]).thenComparing(x -> x[1]));
        int cnt = 0;
        long sum = 0;
        int[] kSmallest = new int[2];

        long getSum(){
            return sum;
        }

        void add(int[] x){
            inc(x);
            if(ts.size() <= k){
                sum += x[0];
                kSmallest = ts.last();
            }
            else{
                if(lessThen(x, kSmallest)){
                    sum += x[0];
                    sum -= kSmallest[0];
                    kSmallest = ts.lower(kSmallest);
                }
            }
            // System.out.println(Arrays.toString(kSmallest));
        }

        void remove(int[] x){
            dec(x);
            if(ts.size() < k - 1){
                kSmallest = ts.last();
            }
            else{
                if(!lessThen(kSmallest, x)){
                    sum -= x[0];
                    kSmallest = ts.higher(kSmallest);
                    if(kSmallest != null){
                        sum += kSmallest[0];    
                    }
                }    
            }
            // System.out.println(Arrays.toString(kSmallest));
        }

        boolean lessThen(int[] x, int[] y){
            return y[0] > x[0] || (y[0] == x[0] && y[1] > x[1]);
        }

        void inc(int[] x){
            ts.add(x);
        }

        void dec(int[] x){
            ts.remove(x);
        }
    }
}
// @lc code=end
