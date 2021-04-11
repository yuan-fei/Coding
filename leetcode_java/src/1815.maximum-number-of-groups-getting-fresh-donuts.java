/*
 * @lc app=leetcode id=1815 lang=java
 *
 * [1815] Maximum Number of Groups Getting Fresh Donuts
 *
 * https://leetcode.com/problems/maximum-number-of-groups-getting-fresh-donuts/description/
 *
 * algorithms
 * Hard (26.64%)
 * Likes:    45
 * Dislikes: 8
 * Total Accepted:    813
 * Total Submissions: 3.1K
 * Testcase Example:  '3\n[1,2,3,4,5,6]'
 *
 * There is a donuts shop that bakes donuts in batches of batchSize. They have
 * a rule where they must serve all of the donuts of a batch before serving any
 * donuts of the next batch. You are given an integer batchSize and an integer
 * array groups, where groups[i] denotes that there is a group of groups[i]
 * customers that will visit the shop. Each customer will get exactly one
 * donut.
 * 
 * When a group visits the shop, all customers of the group must be served
 * before serving any of the following groups. A group will be happy if they
 * all get fresh donuts. That is, the first customer of the group does not
 * receive a donut that was left over from the previous group.
 * 
 * You can freely rearrange the ordering of the groups. Return the maximum
 * possible number of happy groups after rearranging the groups.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: batchSize = 3, groups = [1,2,3,4,5,6]
 * Output: 4
 * Explanation: You can arrange the groups as [6,2,4,5,1,3]. Then the 1^st,
 * 2^nd, 4^th, and 6^th groups will be happy.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: batchSize = 4, groups = [1,3,2,5,2,2,1,6]
 * Output: 4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= batchSize <= 9
 * 1 <= groups.length <= 30
 * 1 <= groups[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxHappyGroups(int batchSize, int[] groups) {
    	int[] freq = new int[batchSize];
    	int ans = 0;
    	// optimization for 1 or 2 complementary elements
        for(int x : groups){
        	if(x % batchSize == 0){
        		ans++;
        	}
        	else if(freq[batchSize - x % batchSize] > 0){
        		freq[batchSize - x % batchSize]--;
        		ans++;
        	}
        	else{
        		freq[x % batchSize]++;
        	}
        	// freq[x % batchSize]++;
        }
        return ans + dfs(0, freq);
    }
    Map<String, Integer> cache = new HashMap<>();
    int dfs(int remainder, int[] freq){
    	String key = Arrays.toString(freq);
    	if(!cache.containsKey(key)){
    		int ret = 0;
	    	for(int i = 0; i < freq.length; i++){
	    		if(freq[i] > 0){
	    			freq[i]--;
	    			ret = Math.max(ret, dfs((remainder + i) % freq.length, freq) + (remainder == 0 ? 1 : 0));
	    			freq[i]++;
	    		}
	    	}	
	    	cache.put(key, ret);
    	}
    	
    	return cache.get(key);
    }
}
// @lc code=end
