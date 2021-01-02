/*
 * @lc app=leetcode id=638 lang=java
 *
 * [638] Shopping Offers
 *
 * https://leetcode.com/problems/shopping-offers/description/
 *
 * algorithms
 * Medium (52.29%)
 * Likes:    649
 * Dislikes: 499
 * Total Accepted:    34.1K
 * Total Submissions: 65.1K
 * Testcase Example:  '[2,5]\n[[3,0,5],[1,2,10]]\n[3,2]'
 *
 * 
 * In LeetCode Store, there are some kinds of items to sell. Each item has a
 * price.
 * 
 * 
 * 
 * However, there are some special offers, and a special offer consists of one
 * or more different kinds of items with a sale price.
 * 
 * 
 * 
 * You are given the each item's price, a set of special offers, and the number
 * we need to buy for each item.
 * The job is to output the lowest price you have to pay for exactly certain
 * items as given, where you could make optimal use of the special offers.
 * 
 * 
 * 
 * Each special offer is represented in the form of an array, the last number
 * represents the price you need to pay for this special offer, other numbers
 * represents how many specific items you could get if you buy this offer.
 * 
 * 
 * You could use any of special offers as many times as you want.
 * 
 * Example 1:
 * 
 * Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * Output: 14
 * Explanation: 
 * There are two kinds of items, A and B. Their prices are $2 and $5
 * respectively. 
 * In special offer 1, you can pay $5 for 3A and 0B
 * In special offer 2, you can pay $10 for 1A and 2B. 
 * You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer
 * #2), and $4 for 2A.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 * Output: 11
 * Explanation: 
 * The price of A is $2, and $3 for B, $4 for C. 
 * You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
 * You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special
 * offer #1), and $3 for 1B, $4 for 1C. 
 * You cannot add more items, though only $9 for 2A ,2B and 1C.
 * 
 * 
 * 
 * Note:
 * 
 * There are at most 6 kinds of items, 100 special offers.
 * For each item, you need to buy at most 6 of them.
 * You are not allowed to buy more items than you want, even if that would
 * lower the overall price.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Arrays.fill(cache, Integer.MAX_VALUE);
        int ans =  dfs(price, special, needs);
        // System.out.println(Arrays.toString(cache));
        return ans;
    }

    int cap = 7 * 7 * 7 * 7 * 7 * 7 + 5;
    int[] cache = new int[cap];
    int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs){

    	int c = encode(needs);
    	if(c == 0){
    		return 0;
    	}
    	if(cache[c] == Integer.MAX_VALUE){
    		List<Integer> newNeeds;
    		for(List<Integer> so : special){
    			newNeeds = new ArrayList();
    			boolean allGood = true;
    			for(int i = 0; i < needs.size(); i++){
    				newNeeds.add(needs.get(i) - so.get(i));
    				if(newNeeds.get(i) < 0){
    					allGood = false;
    					break;
    				}
    			}
    			if(allGood){
    				cache[c] = Math.min(cache[c], so.get(so.size() - 1) + dfs(price, special, newNeeds));
    			}
    		}
    		newNeeds = new ArrayList(needs);
    		for(int i = 0; i < needs.size(); i++){
    			if(needs.get(i) > 0){
    				newNeeds.set(i, newNeeds.get(i) - 1);
    				cache[c] = Math.min(cache[c], price.get(i) + dfs(price, special, newNeeds));
    				break;
    			}
    		}
    	}
    	// System.out.println(needs + "" + cache[c]);
    	return cache[c];
    }

    int encode(List<Integer> a){
    	int x = 0;
    	for(int i = 0; i < a.size(); i++){
    		x *= 7;
    		x += a.get(i);
    	}
    	return x;
    }

}
// @lc code=end
