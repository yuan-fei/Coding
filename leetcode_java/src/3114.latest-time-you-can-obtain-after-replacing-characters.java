/*
 * @lc app=leetcode id=3114 lang=java
 *
 * [3114] Latest Time You Can Obtain After Replacing Characters
 *
 * https://leetcode.com/problems/latest-time-you-can-obtain-after-replacing-characters/description/
 *
 * algorithms
 * Easy (35.63%)
 * Likes:    104
 * Dislikes: 47
 * Total Accepted:    37.1K
 * Total Submissions: 104.1K
 * Testcase Example:  '"1?:?4"'
 *
 * You are given a string s representing a 12-hour format time where some of
 * the digits (possibly none) are replaced with a "?".
 * 
 * 12-hour times are formatted as "HH:MM", where HH is between 00 and 11, and
 * MM is between 00 and 59. The earliest 12-hour time is 00:00, and the latest
 * is 11:59.
 * 
 * You have to replace all the "?" characters in s with digits such that the
 * time we obtain by the resulting string is a valid 12-hour format time and is
 * the latest possible.
 * 
 * Return the resulting string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "1?:?4"
 * 
 * Output: "11:54"
 * 
 * Explanation: The latest 12-hour format time we can achieve by replacing "?"
 * characters is "11:54".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "0?:5?"
 * 
 * Output: "09:59"
 * 
 * Explanation: The latest 12-hour format time we can achieve by replacing "?"
 * characters is "09:59".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * s.length == 5
 * s[2] is equal to the character ":".
 * All characters except s[2] are digits or "?" characters.
 * The input is generated such that there is at least one time between "00:00"
 * and "11:59" that you can obtain after replacing the "?" characters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String findLatestTime(String s) {
        String[] parts = s.split(":");
        for (int i = 11; i >= 0; i--) {
            String numberStr = toStr(i);
            if (matches(numberStr, parts[0])) {
                parts[0] = numberStr;
            }
        }
        for (int i = 59; i >= 0; i--) {
            String numberStr = toStr(i);
            if (matches(numberStr, parts[1])) {
                parts[1] = numberStr;
            }
        }
        return parts[0] + ":" + parts[1];
    }

    String toStr(int n) {
        String numberStr = "" + n;
        if (numberStr.length() == 1) {
            numberStr = "0" + numberStr;
        }
        return numberStr;
    }

    boolean matches(String numberStr, String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != '?' && pattern.charAt(i) != numberStr.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
