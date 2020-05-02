/*
 * @lc app=leetcode id=1418 lang=java
 *
 * [1418] Display Table of Food Orders in a Restaurant
 *
 * https://leetcode.com/problems/display-table-of-food-orders-in-a-restaurant/description/
 *
 * algorithms
 * Medium (62.83%)
 * Likes:    29
 * Dislikes: 112
 * Total Accepted:    7.8K
 * Total Submissions: 12.4K
 * Testcase Example:  '[["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]'
 *
 * Given the array orders, which represents the orders that customers have done
 * in a restaurant. More specifically
 * orders[i]=[customerNamei,tableNumberi,foodItemi] where customerNamei is the
 * name of the customer, tableNumberi is the table customer sit at, and
 * foodItemi is the item customer orders.
 * 
 * Return the restaurant's “display table”. The “display table” is a table
 * whose row entries denote how many of each food item each table ordered. The
 * first column is the table number and the remaining columns correspond to
 * each food item in alphabetical order. The first row should be a header whose
 * first column is “Table”, followed by the names of the food items. Note that
 * the customer names are not part of the table. Additionally, the rows should
 * be sorted in numerically increasing order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: orders = [["David","3","Ceviche"],["Corina","10","Beef
 * Burrito"],["David","3","Fried
 * Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * Output: [["Table","Beef Burrito","Ceviche","Fried
 * Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]] 
 * Explanation:
 * The displaying table looks like:
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * 3    ,0           ,2      ,1            ,0
 * 5    ,0           ,1      ,0            ,1
 * 10   ,1           ,0      ,0            ,0
 * For the table 3: David orders "Ceviche" and "Fried Chicken", and Rous orders
 * "Ceviche".
 * For the table 5: Carla orders "Water" and "Ceviche".
 * For the table 10: Corina orders "Beef Burrito". 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried
 * Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian
 * Waffles"],["Brianna","1","Canadian Waffles"]]
 * Output: [["Table","Canadian Waffles","Fried
 * Chicken"],["1","2","0"],["12","0","3"]] 
 * Explanation: 
 * For the table 1: Adam and Brianna order "Canadian Waffles".
 * For the table 12: James, Ratesh and Amadeus order "Fried Chicken".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef
 * Burrito"],["Melissa","2","Soda"]]
 * Output: [["Table","Bean Burrito","Beef
 * Burrito","Soda"],["2","1","1","1"]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= orders.length <= 5 * 10^4
 * orders[i].length == 3
 * 1 <= customerNamei.length, foodItemi.length <= 20
 * customerNamei and foodItemi consist of lowercase and uppercase English
 * letters and the space character.
 * tableNumberi is a valid integer between 1 and 500.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Map<Integer, Map<String, Integer>> map = new TreeMap<>();
        Set<String> dishesName = new TreeSet<>();
        for(List<String> o : orders){
        	String cName = o.get(0);
        	int table = Integer.parseInt(o.get(1));
        	String dish = o.get(2);
        	if(!map.containsKey(table)){
        		map.put(table, new HashMap<>());
        	}
        	int cnt = 0;
        	dishesName.add(dish);
        	if(map.get(table).containsKey(dish)){
        		
        		cnt = map.get(table).getOrDefault(dish, 0);
        	}
        	
        	map.get(table).put(dish, cnt + 1);
        }
        List<List<String>> res = new ArrayList<>();
        List<String> line = new ArrayList<>();
        line.add("Table");
        for(String d : dishesName){
        	line.add(d);
        }
        res.add(line);
        for(int k : map.keySet()){
        	line = new ArrayList<>();
        	line.add("" + k);
        	for(String d : dishesName){
        		line.add("" + map.get(k).getOrDefault(d, 0));
        	}
        	res.add(line);
        }
        return res;
    }
}
// @lc code=end
