/*
 * @lc app=leetcode id=1268 lang=java
 *
 * [1268] Search Suggestions System
 *
 * https://leetcode.com/problems/search-suggestions-system/description/
 *
 * algorithms
 * Medium (43.69%)
 * Likes:    23
 * Dislikes: 23
 * Total Accepted:    2.8K
 * Total Submissions: 6.3K
 * Testcase Example:  '["mobile","mouse","moneypot","monitor","mousepad"]\r\n"mouse"\r'
 *
 * Given an array of strings products and a string searchWord. We want to
 * design a system that suggests at most three product names from products
 * after each character of searchWord is typed. Suggested products should have
 * common prefix with the searchWord. If there are more than three products
 * with a common prefix return the three lexicographically minimums products.
 * 
 * Return list of lists of the suggested products after each character of
 * searchWord is typed. 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"],
 * searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically =
 * ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user
 * ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: products = ["havana"], searchWord = "havana"
 * Output:
 * [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord =
 * "bags"
 * Output:
 * [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= products.length <= 1000
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        int[] len = new int[products.length];
       	Arrays.sort(products);
        for(int i = 0; i < products.length; i++){
        	len[i] = getMaxMatchLength(products[i], searchWord);
        }
        int left = 0;
        List<List<String>> ans = new ArrayList<>();
        for(int i = 1; i <= searchWord.length(); i++){
        	List<String> l = new ArrayList<>();
        	while(left < products.length && len[left] < i){
        		left++;
        	}
        	if(left < products.length){
        		int right = left;
        		while(right - left < 3 && right < products.length && len[right] >= i){
        			l.add(products[right++]);
        		}
        	}
        	ans.add(l);
        }
        return ans;
    }

    int getMaxMatchLength(String s, String p){
    	for(int i = 0; i < Math.min(s.length(), p.length()); i++){
    		if(p.charAt(i) != s.charAt(i)){
    			return i;
    		}
    	}
    	return Math.min(s.length(), p.length());
    }
}
// @lc code=end
