/*
 * @lc app=leetcode id=2517 lang=java
 *
 * [2517] Maximum Tastiness of Candy Basket
 *
 * https://leetcode.com/problems/maximum-tastiness-of-candy-basket/description/
 *
 * algorithms
 * Medium (59.60%)
 * Likes:    137
 * Dislikes: 5
 * Total Accepted:    3.5K
 * Total Submissions: 5.9K
 * Testcase Example:  '[13,5,1,8,21,2]\n3'
 *
 * You are given an array of positive integers price where price[i] denotes the
 * price of the i^th candy and a positive integer k.
 * 
 * The store sells baskets of k distinct candies. The tastiness of a candy
 * basket is the smallest absolute difference of the prices of any two candies
 * in the basket.
 * 
 * Return the maximum tastiness of a candy basket.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: price = [13,5,1,8,21,2], k = 3
 * Output: 8
 * Explanation: Choose the candies with the prices [13,5,21].
 * The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) =
 * min(8, 8, 16) = 8.
 * It can be proven that 8 is the maximum tastiness that can be achieved.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: price = [1,3,1], k = 2
 * Output: 2
 * Explanation: Choose the candies with the prices [1,3].
 * The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
 * It can be proven that 2 is the maximum tastiness that can be achieved.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: price = [7,7,7,7], k = 2
 * Output: 0
 * Explanation: Choosing any two distinct candies from the candies we have will
 * result in a tastiness of 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= price.length <= 10^5
 * 1 <= price[i] <= 10^9
 * 2 <= k <= price.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int low = 0;
        int high = (int)1e9;
        while(low + 1 < high){
            int mid = low + (high - low) / 2;
            if(feasible(price, mid, k)){
                low = mid;
            }
            else{
                high = mid;
            }
        }
        if(feasible(price, high, k)){
            return high;
        }
        else {
            return low;
        }
    }

    boolean feasible(int[] price, int gap, int k){
        int last = price[0];
        int cnt = 1;
        for(int i = 1; i < price.length; i++){
            if(price[i] - last >= gap){
                cnt++;
                last = price[i];
            }
        }
        return cnt >= k;
    }
}
// @lc code=end
