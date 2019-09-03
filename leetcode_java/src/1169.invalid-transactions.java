/*
 * @lc app=leetcode id=1169 lang=java
 *
 * [1169] Invalid Transactions
 *
 * https://leetcode.com/problems/invalid-transactions/description/
 *
 * algorithms
 * Easy (24.32%)
 * Total Accepted:    2.9K
 * Total Submissions: 12K
 * Testcase Example:  '["alice,20,800,mtv","alice,50,100,beijing"]'
 *
 * A transaction is possibly invalid if:
 * 
 * 
 * the amount exceeds $1000, or;
 * if it occurs within (and including) 60 minutes of another transaction with
 * the same name in a different city.
 * 
 * 
 * Each transaction string transactions[i] consists of comma separated values
 * representing the name, time (in minutes), amount, and city of the
 * transaction.
 * 
 * Given a list of transactions, return a list of transactions that are
 * possibly invalid.  You may return the answer in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
 * Output: ["alice,20,800,mtv","alice,50,100,beijing"]
 * Explanation: The first transaction is invalid because the second transaction
 * occurs within a difference of 60 minutes, have the same name and is in a
 * different city. Similarly the second one is invalid too.
 * 
 * Example 2:
 * 
 * 
 * Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
 * Output: ["alice,50,1200,mtv"]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
 * Output: ["bob,50,1200,mtv"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * transactions.length <= 1000
 * Each transactions[i] takes the form "{name},{time},{amount},{city}"
 * Each {name} and {city} consist of lowercase English letters, and have
 * lengths between 1 and 10.
 * Each {time} consist of digits, and represent an integer between 0 and
 * 1000.
 * Each {amount} consist of digits, and represent an integer between 0 and
 * 2000.
 * 
 * 
 */
class Solution {
    public List<String> invalidTransactions(String[] transactions) {
    	int N = transactions.length;
    	boolean[] invalid = new boolean[N];
    	String[] name = new String[N];
    	String[] city = new String[N];
    	int[] time = new int[N];
    	int[] amount = new int[N];
    	List<String> ans = new ArrayList<>();
    	for (int i = 0; i< N; i++) {
    		String[] cur = transactions[i].split(",");
    		name[i] = cur[0];
    		city[i] = cur[3];
    		time[i] = Integer.parseInt(cur[1]);
    		amount[i] = Integer.parseInt(cur[2]);
    		if(amount[i]>1000){
        		invalid[i] = true;
        		ans.add(transactions[i]);
        	}
    		for(int j = 0; j < i; j++){	
	    		if(name[i].equals(name[j]) && !city[i].equals(city[j]) && Math.abs(time[i] - time[j])<=60){
	    			if(!invalid[i]){
						invalid[i] = true;
						ans.add(transactions[i]);
	    			}
	    			if(!invalid[j]){
	    				invalid[j] = true;	
	    				ans.add(transactions[j]);
	    			}
	    		}
    		}
    	}
        return ans;
    }
}
