/*
 * @lc app=leetcode id=1736 lang=java
 *
 * [1736] Latest Time by Replacing Hidden Digits
 *
 * https://leetcode.com/problems/latest-time-by-replacing-hidden-digits/description/
 *
 * algorithms
 * Easy (41.33%)
 * Likes:    48
 * Dislikes: 30
 * Total Accepted:    8.4K
 * Total Submissions: 20.3K
 * Testcase Example:  '"2?:?0"'
 *
 * You are given a string time in the form of  hh:mm, where some of the digits
 * in the string are hidden (represented by ?).
 * 
 * The valid times are those inclusively between 00:00 and 23:59.
 * 
 * Return the latest valid time you can get from time by replacing the hidden
 * digits.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: time = "2?:?0"
 * Output: "23:50"
 * Explanation: The latest hour beginning with the digit '2' is 23 and the
 * latest minute ending with the digit '0' is 50.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: time = "0?:3?"
 * Output: "09:39"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: time = "1?:22"
 * Output: "19:22"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * time is in the format hh:mm.
 * It is guaranteed that you can produce a valid time from the given string.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String maximumTime(String time) {
    	time = time.replace(":", "");
    	String ans = "";
        if(time.charAt(0) == '?' && time.charAt(1) == '?'){
        	ans += "23";
        }else if(time.charAt(0) != '?' && time.charAt(1) != '?'){
        	ans += time.substring(0, 2);
        }else if(time.charAt(0) == '?'){
        	if('0' <= time.charAt(1) && time.charAt(1) <= '3'){
        		ans += "2" + time.charAt(1);
        	}
        	else{
        		ans += "1" + time.charAt(1);	
        	}
        }else if(time.charAt(1) == '?'){
        	if('0' <= time.charAt(0) && time.charAt(0) <= '1'){
        		ans += time.charAt(0) + "9";
        	}
        	else{
        		ans += time.charAt(0) + "3";	
        	}
        }

        ans += ":";

        if(time.charAt(2) == '?' && time.charAt(3) == '?'){
        	ans += "59";
        }else if(time.charAt(2) != '?' && time.charAt(3) != '?'){
        	ans += time.substring(2, 4);
        }else if(time.charAt(2) == '?'){
        	ans += "5" + time.charAt(3);
        }else if(time.charAt(3) == '?'){
        	ans += time.charAt(2) + "9";
        }
        return ans;
    }
}
// @lc code=end
