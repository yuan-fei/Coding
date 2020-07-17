/*
 * @lc app=leetcode id=1507 lang=java
 *
 * [1507] Reformat Date
 *
 * https://leetcode.com/problems/reformat-date/description/
 *
 * algorithms
 * Easy (59.34%)
 * Likes:    8
 * Dislikes: 17
 * Total Accepted:    4.4K
 * Total Submissions: 7.3K
 * Testcase Example:  '"20th Oct 2052"'
 *
 * Given a date string in the form Day Month Year, where:
 * 
 * 
 * Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
 * Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
 * "Sep", "Oct", "Nov", "Dec"}.
 * Year is in the range [1900, 2100].
 * 
 * 
 * Convert the date string to the format YYYY-MM-DD, where:
 * 
 * 
 * YYYY denotes the 4 digit year.
 * MM denotes the 2 digit month.
 * DD denotes the 2 digit day.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: date = "20th Oct 2052"
 * Output: "2052-10-20"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: date = "6th Jun 1933"
 * Output: "1933-06-06"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: date = "26th May 1960"
 * Output: "1960-05-26"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The given dates are guaranteed to be valid, so no error handling is
 * necessary.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String reformatDate(String date) {
        List<String> months = Arrays.asList(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
        String[] parts = date.split(" ");
        String dd = parts[0].substring(0, parts[0].length() - 2);
        if(dd.length() < 2){
        	dd = "0" + dd;
        }
        String mm = "" + (months.indexOf(parts[1]) + 1);
        if(mm.length() < 2){
        	mm = "0" + mm;
        }
        String yy = parts[2];
        return yy + "-" + mm + "-" + dd;
    }
}
// @lc code=end
