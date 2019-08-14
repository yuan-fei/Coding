/*
 * @lc app=leetcode id=1154 lang=java
 *
 * [1154] Day of the Year
 *
 * https://leetcode.com/problems/day-of-the-year/description/
 *
 * algorithms
 * Easy (51.90%)
 * Total Accepted:    5.3K
 * Total Submissions: 10.3K
 * Testcase Example:  '"2019-01-09"\r'
 *
 * Given a string date representing a Gregorian calendar date formatted as
 * YYYY-MM-DD, return the day number of the year.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: date = "2019-01-09"
 * Output: 9
 * Explanation: Given date is the 9th day of the year in 2019.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: date = "2019-02-10"
 * Output: 41
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: date = "2003-03-01"
 * Output: 60
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: date = "2004-03-01"
 * Output: 61
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * date.length == 10
 * date[4] == date[7] == '-', and all other date[i]'s are digits
 * date represents a calendar date between Jan 1st, 1900 and Dec 31, 2019.
 * 
 * 
 */
class Solution {
    public int dayOfYear(String date) {
        String[] parts = date.split("-");
        int[] daysOfMonthNonLeap = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        int[] daysOfMonthLeap = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        int[] daysOfMonth = daysOfMonthNonLeap;
        if((year%4==0&&year%100!=0)||year%400==0){
        	daysOfMonth = daysOfMonthLeap;
        }
        int totalDays = day;
        for(int i = 0; i < month-1; i++){
        	totalDays+=daysOfMonth[i];
        }
        return totalDays;
    }
}
