/*
 * @lc app=leetcode id=1604 lang=java
 *
 * [1604] Alert Using Same Key-Card Three or More Times in a One Hour Period
 *
 * https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/description/
 *
 * algorithms
 * Medium (32.56%)
 * Likes:    6
 * Dislikes: 27
 * Total Accepted:    2.3K
 * Total Submissions: 7K
 * Testcase Example:  '["daniel","daniel","daniel","luis","luis","luis","luis"]\n["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]'
 *
 * Leetcode company workers use key-cards to unlock office doors. Each time a
 * worker uses their key-card, the security system saves the worker's name and
 * the time when it was used. The system emits an alert if any worker uses the
 * key-card three or more times in a one-hour period.
 * 
 * You are given a list of strings keyName and keyTime where [keyName[i],
 * keyTime[i]] corresponds to a person's name and the time when their key-card
 * was used in a single day.
 * 
 * Access times are given in the 24-hour time format "HH:MM", such as "23:51"
 * and "09:49".
 * 
 * Return a list of unique worker names who received an alert for frequent
 * keycard use. Sort the names in ascending order alphabetically.
 * 
 * Notice that "10:00" - "11:00" is considered to be within a one-hour period,
 * while "23:51" - "00:10" is not considered to be within a one-hour period.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"],
 * keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
 * Output: ["daniel"]
 * Explanation: "daniel" used the keycard 3 times in a one-hour period
 * ("10:00","10:40", "11:00").
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime
 * = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
 * Output: ["bob"]
 * Explanation: "bob" used the keycard 3 times in a one-hour period
 * ("21:00","21:20", "21:30").
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: keyName = ["john","john","john"], keyTime = ["23:58","23:59","00:01"]
 * Output: []
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: keyName =
 * ["leslie","leslie","leslie","clare","clare","clare","clare"], keyTime =
 * ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"]
 * Output: ["clare","leslie"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= keyName.length, keyTime.length <= 10^5
 * keyName.length == keyTime.length
 * keyTime are in the format "HH:MM".
 * [keyName[i], keyTime[i]] is unique.
 * 1 <= keyName[i].length <= 10
 * keyName[i] contains only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> alertNames(String[] keyNames, String[] keyTime) {
        Map<String, Set<Integer>> accesses = new HashMap<>();
        for(String keyName : keyNames){
        	accesses.put(keyName, new TreeSet<Integer>());
        }
        for(int i = 0; i < keyNames.length; i++){
        	accesses.get(keyNames[i]).add(Integer.parseInt(keyTime[i].substring(0, 2)) * 60 + Integer.parseInt(keyTime[i].substring(3)));
        }
        TreeSet<String> ans = new TreeSet<>();
        for(String keyName : keyNames){
        	Integer last1 = null;
        	Integer last2 = null;
        	for(int t : accesses.get(keyName)){
        		if(last2 == null){
        			last2 = t;
        		}
        		else if(last1 == null){
        			last1 = t;
        		}
        		else{
        			if(t - last2 <= 60){
        				ans.add(keyName);
        			}
	        		last2 = last1;
	        		last1 = t;	
        		}
        	}
        }
        return new ArrayList<>(ans);
    }
}
// @lc code=end
