/*
 * @lc app=leetcode id=2064 lang=java
 *
 * [2064] Minimized Maximum of Products Distributed to Any Store
 *
 * https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/description/
 *
 * algorithms
 * Medium (39.48%)
 * Likes:    92
 * Dislikes: 6
 * Total Accepted:    3.3K
 * Total Submissions: 8.3K
 * Testcase Example:  '6\n[11,6]'
 *
 * You are given an integer n indicating there are n specialty retail stores.
 * There are m product types of varying amounts, which are given as a 0-indexed
 * integer array quantities, where quantities[i] represents the number of
 * products of the i^th product type.
 * 
 * You need to distribute all products to the retail stores following these
 * rules:
 * 
 * 
 * A store can only be given at most one product type but can be given any
 * amount of it.
 * After distribution, each store will be given some number of products
 * (possibly 0). Let x represent the maximum number of products given to any
 * store. You want x to be as small as possible, i.e., you want to minimize the
 * maximum number of products that are given to any store.
 * 
 * 
 * Return the minimum possible x.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 6, quantities = [11,6]
 * Output: 3
 * Explanation: One optimal way is:
 * - The 11 products of type 0 are distributed to the first four stores in
 * these amounts: 2, 3, 3, 3
 * - The 6 products of type 1 are distributed to the other two stores in these
 * amounts: 3, 3
 * The maximum number of products given to any store is max(2, 3, 3, 3, 3, 3) =
 * 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 7, quantities = [15,10,10]
 * Output: 5
 * Explanation: One optimal way is:
 * - The 15 products of type 0 are distributed to the first three stores in
 * these amounts: 5, 5, 5
 * - The 10 products of type 1 are distributed to the next two stores in these
 * amounts: 5, 5
 * - The 10 products of type 2 are distributed to the last two stores in these
 * amounts: 5, 5
 * The maximum number of products given to any store is max(5, 5, 5, 5, 5, 5,
 * 5) = 5.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 1, quantities = [100000]
 * Output: 100000
 * Explanation: The only optimal way is:
 * - The 100000 products of type 0 are distributed to the only store.
 * The maximum number of products given to any store is max(100000) =
 * 100000.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == quantities.length
 * 1 <= m <= n <= 10^5
 * 1 <= quantities[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        long sum = 0;
        for(int i = 0; i < quantities.length; i++){
            sum += quantities[i];
        }
        long low = 1;
        long high = sum;
        if(sum < n){
            return 1;
        }
        while(low + 1 < high){
            long mid = (low + high) / 2;
            if(feasible(n, sum, quantities, mid)){
                high = mid;
            }
            else{
                low = mid;
            }
        }
        // System.out.println(low + ", " + high);
        if(feasible(n, sum, quantities, low)){
            return (int)low;
        }
        else{
            return (int)high;
        }
    }

    boolean feasible(int n, long sum, int[] quantities, long max){
        // System.out.println(n + ", " + sum);
        
        long lb = 0;
        for(int x : quantities){
            lb += (x + max - 1) / max;
        }
        return lb <= n;
    }
}
// @lc code=end
