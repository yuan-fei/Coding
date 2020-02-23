/*
 * @lc app=leetcode id=1360 lang=java
 *
 * [1360] Number of Days Between Two Dates
 *
 * https://leetcode.com/problems/number-of-days-between-two-dates/description/
 *
 * algorithms
 * Easy (48.34%)
 * Likes:    9
 * Dislikes: 89
 * Total Accepted:    4.3K
 * Total Submissions: 8.9K
 * Testcase Example:  '"2019-06-29"\n"2019-06-30"'
 *
 * Write a program to count the number of days between two dates.
 * 
 * The two dates are given as strings, their format is YYYY-MM-DD as shown in
 * the examples.
 * 
 * 
 * Example 1:
 * Input: date1 = "2019-06-29", date2 = "2019-06-30"
 * Output: 1
 * Example 2:
 * Input: date1 = "2020-01-15", date2 = "2019-12-31"
 * Output: 15
 * 
 * 
 * Constraints:
 * 
 * 
 * The given dates are valid dates between the years 1971 and 2100.
 * 
 * 
 */

// @lc code=start
class Solution {
	int[] prefixTotalDaysByYear;
	int[] prefixTotalDaysByLeapYearMonth;
	int[] prefixTotalDaysByNonLeapYearMonth;
    public int daysBetweenDates(String date1, String date2) {
    	if(date1.compareTo(date2) > 0){
    		return daysBetweenDates(date2, date1);
    	}
    	prefixTotalDaysByYear = new int[2100 - 1971 + 1];
    	for(int y = 1971; y < 2100; y++){
    		prefixTotalDaysByYear[y - 1970] = prefixTotalDaysByYear[y - 1971] + (isLeapYear(y) ? 366 : 365);
    	}
    	prefixTotalDaysByLeapYearMonth = new int[13];
    	prefixTotalDaysByNonLeapYearMonth = new int[13];
    	int[] nonLeapYearMonthDays = new int[]{0, 31,28,31,30,31,30,31,31,30,31,30,31};
    	int[] leapYearMonthDays = new int[]{0, 31,29,31,30,31,30,31,31,30,31,30,31};
    	for(int i = 1; i <= 12; i++){
    		prefixTotalDaysByLeapYearMonth[i] = prefixTotalDaysByLeapYearMonth[i - 1] + leapYearMonthDays[i];
    		prefixTotalDaysByNonLeapYearMonth[i] = prefixTotalDaysByNonLeapYearMonth[i - 1] + nonLeapYearMonthDays[i];
    	}

    	String[] part = date1.split("-");
    	int y1 = Integer.parseInt(part[0]);
    	int m1 = Integer.parseInt(part[1]);
    	int d1 = Integer.parseInt(part[2]);
    	part = date2.split("-");
    	int y2 = Integer.parseInt(part[0]);
    	int m2 = Integer.parseInt(part[1]);
    	int d2 = Integer.parseInt(part[2]);
        return countDays(y2, m2, d2) - countDays(y1, m1, d1);
    }

    boolean isLeapYear(int y){
    	return y % 400 == 0 || (y % 4 == 0 && y % 100 != 0);
    }

    int countDays(int year, int month, int day){
    	int total = prefixTotalDaysByYear[year - 1971];
    	total += isLeapYear(year) ? prefixTotalDaysByLeapYearMonth[month - 1] : prefixTotalDaysByNonLeapYearMonth[month - 1];
    	total += day;
    	return total;
    }

}
// @lc code=end
