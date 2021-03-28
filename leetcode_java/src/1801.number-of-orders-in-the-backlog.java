/*
 * @lc app=leetcode id=1801 lang=java
 *
 * [1801] Number of Orders in the Backlog
 *
 * https://leetcode.com/problems/number-of-orders-in-the-backlog/description/
 *
 * algorithms
 * Medium (41.39%)
 * Likes:    35
 * Dislikes: 75
 * Total Accepted:    3.4K
 * Total Submissions: 8.3K
 * Testcase Example:  '[[10,5,0],[15,2,1],[25,1,1],[30,4,0]]'
 *
 * You are given a 2D integer array orders, where each orders[i] = [pricei,
 * amounti, orderTypei] denotes that amounti orders have been placed of type
 * orderTypei at the price pricei. The orderTypei is:
 * 
 * 
 * 0 if it is a batch of buy orders, or
 * 1 if it is a batch of sell orders.
 * 
 * 
 * Note that orders[i] represents a batch of amounti independent orders with
 * the same price and order type. All orders represented by orders[i] will be
 * placed before all orders represented by orders[i+1] for all valid i.
 * 
 * There is a backlog that consists of orders that have not been executed. The
 * backlog is initially empty. When an order is placed, the following
 * happens:
 * 
 * 
 * If the order is a buy order, you look at the sell order with the smallest
 * price in the backlog. If that sell order's price is smaller than or equal to
 * the current buy order's price, they will match and be executed, and that
 * sell order will be removed from the backlog. Else, the buy order is added to
 * the backlog.
 * Vice versa, if the order is a sell order, you look at the buy order with the
 * largest price in the backlog. If that buy order's price is larger than or
 * equal to the current sell order's price, they will match and be executed,
 * and that buy order will be removed from the backlog. Else, the sell order is
 * added to the backlog.
 * 
 * 
 * Return the total amount of orders in the backlog after placing all the
 * orders from the input. Since this number can be large, return it modulo 10^9
 * + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
 * Output: 6
 * Explanation: Here is what happens with the orders:
 * - 5 orders of type buy with price 10 are placed. There are no sell orders,
 * so the 5 orders are added to the backlog.
 * - 2 orders of type sell with price 15 are placed. There are no buy orders
 * with prices larger than or equal to 15, so the 2 orders are added to the
 * backlog.
 * - 1 order of type sell with price 25 is placed. There are no buy orders with
 * prices larger than or equal to 25 in the backlog, so this order is added to
 * the backlog.
 * - 4 orders of type buy with price 30 are placed. The first 2 orders are
 * matched with the 2 sell orders of the least price, which is 15 and these 2
 * sell orders are removed from the backlog. The 3^rd order is matched with the
 * sell order of the least price, which is 25 and this sell order is removed
 * from the backlog. Then, there are no more sell orders in the backlog, so the
 * 4^th order is added to the backlog.
 * Finally, the backlog has 5 buy orders with price 10, and 1 buy order with
 * price 30. So the total number of orders in the backlog is 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
 * Output: 999999984
 * Explanation: Here is what happens with the orders:
 * - 10^9 orders of type sell with price 7 are placed. There are no buy orders,
 * so the 10^9 orders are added to the backlog.
 * - 3 orders of type buy with price 15 are placed. They are matched with the 3
 * sell orders with the least price which is 7, and those 3 sell orders are
 * removed from the backlog.
 * - 999999995 orders of type buy with price 5 are placed. The least price of a
 * sell order is 7, so the 999999995 orders are added to the backlog.
 * - 1 order of type sell with price 5 is placed. It is matched with the buy
 * order of the highest price, which is 5, and that buy order is removed from
 * the backlog.
 * Finally, the backlog has (1000000000-3) sell orders with price 7, and
 * (999999995-1) buy orders with price 5. So the total number of orders =
 * 1999999991, which is equal to 999999984 % (10^9 + 7).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= orders.length <= 10^5
 * orders[i].length == 3
 * 1 <= pricei, amounti <= 10^9
 * orderTypei is either 0 or 1.
 * 
 */

// @lc code=start
class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
    	TreeMap<Integer, Integer>[] tm = new TreeMap[2];
    	tm[0] = new TreeMap<>();
    	tm[1] = new TreeMap<>();
    	for(int[] o : orders){
    		if(o[2] == 0){
    			// System.out.println(0);
    			while(!tm[1].isEmpty() && tm[1].firstKey() <= o[0]){
    				int k = tm[1].firstKey();
    				int amt = tm[1].get(k);
    				int offset = Math.min(o[1], amt);
    				o[1] -= offset;
    				tm[1].put(k, amt - offset);
    				if(o[1] == 0) {
    					break;
    				}
    				if(tm[1].get(k) == 0){
    					tm[1].remove(k);
    				}
    			}	
    			if(o[1] > 0){
    				tm[0].put(o[0], tm[0].getOrDefault(o[0], 0) + o[1]);
    			}
    		}
    		else{
    			// System.out.println(1);
    			while(!tm[0].isEmpty() && tm[0].lastKey() >= o[0]){
    				int k = tm[0].lastKey();
    				int amt = tm[0].get(k);
    				int offset = Math.min(o[1], amt);
    				o[1] -= offset;
    				tm[0].put(k, amt - offset);
    				if(o[1] == 0) {
    					break;
    				}
    				if(tm[0].get(k) == 0){
    					tm[0].remove(k);
    				}
    			}	
    			if(o[1] > 0){
    				tm[1].put(o[0], tm[1].getOrDefault(o[0], 0) + o[1]);
    			}
    		}
    		// System.out.println(tm[0] + ", " + tm[1]);
    	}
    	int ans = 0;
    	for(int i : new int[]{0, 1}){
			for(int v : tm[i].values()){
	    		ans += v;
	    		ans %= 1000000007;
	    	}
    	}
        return ans;
    }
}
// @lc code=end
