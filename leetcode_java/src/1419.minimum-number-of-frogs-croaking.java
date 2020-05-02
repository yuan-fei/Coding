/*
 * @lc app=leetcode id=1419 lang=java
 *
 * [1419] Minimum Number of Frogs Croaking
 *
 * https://leetcode.com/problems/minimum-number-of-frogs-croaking/description/
 *
 * algorithms
 * Medium (43.76%)
 * Likes:    193
 * Dislikes: 12
 * Total Accepted:    8.5K
 * Total Submissions: 19.4K
 * Testcase Example:  '"croakcroak"'
 *
 * Given the string croakOfFrogs, which represents a combination of the string
 * "croak" from different frogs, that is, multiple frogs can croak at the same
 * time, so multiple “croak” are mixed. Return the minimum number of different
 * frogs to finish all the croak in the given string.
 * 
 * A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’
 * sequentially. The frogs have to print all five letters to finish a croak. If
 * the given string is not a combination of valid "croak" return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: croakOfFrogs = "croakcroak"
 * Output: 1 
 * Explanation: One frog yelling "croak" twice.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: croakOfFrogs = "crcoakroak"
 * Output: 2 
 * Explanation: The minimum number of frogs is two. 
 * The first frog could yell "crcoakroak".
 * The second frog could yell later "crcoakroak".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: croakOfFrogs = "croakcrook"
 * Output: -1
 * Explanation: The given string is an invalid combination of "croak" from
 * different frogs.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: croakOfFrogs = "croakcroa"
 * Output: -1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= croakOfFrogs.length <= 10^5
 * All characters in the string are: 'c', 'r', 'o', 'a' or 'k'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
    	int[] cnt = new int[4];
    	Map<Character, Integer> map = new HashMap<>();
    	map.put('c', 0);
    	map.put('r', 1);
    	map.put('o', 2);
    	map.put('a', 3);
    	map.put('k', 4);
    	int min = 0;
    	int total = 0;
        for (char c : croakOfFrogs.toCharArray()) {
        	int pos = map.get(c);
        	if(pos == 0){
        		cnt[0]++;
        		total++;
        		min = Math.max(min, total);
        	}
        	else{
        		if(cnt[pos - 1] == 0){
        			return -1;
        		}
        		else{
        			cnt[pos - 1]--;
        			total--;
        			if(pos < 4){
        				cnt[pos]++;	
        				total++;
        				min = Math.max(min, total);
        			}
        		}
        	}
        }
        for(int c : cnt){
        	if(c != 0){
        		return -1;
        	}
        }
        return min;
    }
}
// @lc code=end
