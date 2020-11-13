/*
 * @lc app=leetcode id=1648 lang=java
 *
 * [1648] Sell Diminishing-Valued Colored Balls
 *
 * https://leetcode.com/problems/sell-diminishing-valued-colored-balls/description/
 *
 * algorithms
 * Medium (30.86%)
 * Likes:    119
 * Dislikes: 30
 * Total Accepted:    3.6K
 * Total Submissions: 11.7K
 * Testcase Example:  '[2,5]\n4'
 *
 * You have an inventory of different colored balls, and there is a customer
 * that wants orders balls of any color.
 * 
 * The customer weirdly values the colored balls. Each colored ball's value is
 * the number of balls of that color you currently have in your inventory. For
 * example, if you own 6 yellow balls, the customer would pay 6 for the first
 * yellow ball. After the transaction, there are only 5 yellow balls left, so
 * the next yellow ball is then valued at 5 (i.e., the value of the balls
 * decreases as you sell more to the customer).
 * 
 * You are given an integer array, inventory, where inventory[i] represents the
 * number of balls of the i^th color that you initially own. You are also given
 * an integer orders, which represents the total number of balls that the
 * customer wants. You can sell the balls in any order.
 * 
 * Return the maximum total value that you can attain after selling orders
 * colored balls. As the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: inventory = [2,5], orders = 4
 * Output: 14
 * Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4
 * + 3).
 * The maximum total value is 2 + 5 + 4 + 3 = 14.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: inventory = [3,5], orders = 6
 * Output: 19
 * Explanation: Sell the 1st color 2 times (3 + 2) and the 2nd color 4 times (5
 * + 4 + 3 + 2).
 * The maximum total value is 3 + 2 + 5 + 4 + 3 + 2 = 19.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: inventory = [2,8,4,10,6], orders = 20
 * Output: 110
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: inventory = [1000000000], orders = 1000000000
 * Output: 21
 * Explanation: Sell the 1st color 1000000000 times for a total value of
 * 500000000500000000. 500000000500000000 modulo 10^9 + 7 = 21.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= inventory.length <= 10^5
 * 1 <= inventory[i] <= 10^9
 * 1 <= orders <= min(sum(inventory[i]), 10^9)
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] inventory, int orders) {
    	long mod = 1000000007;
        Arrays.sort(inventory);
        int lb = findLowerBound(inventory, orders);
        
        long cnt = 0;
        long sum = 0;
        for(int i = inventory.length - 1; i >= 0; i--){
        	if(inventory[i] >= lb){
        		cnt += inventory[i] - lb + 1;
        		long t = (1L * (inventory[i] + lb) * (inventory[i] - lb + 1) / 2) % mod;
        		sum = (sum + t) % mod;
        	}
        	else{
        		break;
        	}
        }
        // System.out.println(cnt);
        if(cnt > orders){
        	sum = (sum + mod - ((cnt - orders) * lb) % mod) % mod;
        }
        return (int)sum;
    }

    int findLowerBound(int[] inventory, int orders){
    	int low = 0;
    	int high = 1000000000;
    	while(low + 1 < high){
    		int mid = low + (high - low) / 2;
    		if(count(inventory, mid) <= orders){
    			high = mid;
    		}
    		else{
    			low = mid;
    		}
    	}
    	if(count(inventory, high) >= orders){
    		// System.out.println(count(inventory, high));
    		return high;
    	}
    	// System.out.println(count(inventory, low));
    	return low;
    }

    long count(int[] inventory, int threshold){
    	long cnt = 0;
    	for(int i = inventory.length - 1; i >= 0; i--){
			if(inventory[i] >= threshold){
				cnt += inventory[i] - threshold + 1;
			}
			else{
				break;
			}
		}
		return cnt;
    }
}
// @lc code=end
