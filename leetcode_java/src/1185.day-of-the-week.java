/*
 * @lc app=leetcode id=1185 lang=java
 *
 * [1185] Day of the Week
 *
 * https://leetcode.com/problems/day-of-the-week/description/
 *
 * algorithms
 * Easy (66.64%)
 * Total Accepted:    5.1K
 * Total Submissions: 7.6K
 * Testcase Example:  '31\n8\n2019'
 *
 * Given a date, return the corresponding day of the week for that date.
 * 
 * The input is given as three integers representing the day, month and year
 * respectively.
 * 
 * Return the answer as one of the following values {"Sunday", "Monday",
 * "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: day = 31, month = 8, year = 2019
 * Output: "Saturday"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: day = 18, month = 7, year = 1999
 * Output: "Sunday"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: day = 15, month = 8, year = 1993
 * Output: "Sunday"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The given dates are valid dates between the years 1971 and 2100.
 * 
 */
class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int days = getDaysBetweenYear(1971, year);
        days += getDaysToMonth(year, month);
        days += day;
        days += 4;
        days %= 7;
        return weekDays[days];
    }

    int getDaysBetweenYear(int inclusiveYear, int exclusiveYear){
    	int days = 0;
    	while(inclusiveYear < exclusiveYear){
    		days += 365;
    		if(isLeapYear(inclusiveYear)){
    			days += 1;
    		}
    		inclusiveYear++;
    	}
    	return days;
    }

    int getDaysToMonth(int year, int exclusiveMonth){
    	int[] monthDays = {31,28,31,30,31,30,31,31,30,31,30,31};
    	int days = 0;
    	for (int i  = 1; i < exclusiveMonth; i++) {
    		days += monthDays[i - 1];
    		if(i == 2 && isLeapYear(year)){
    			days += 1;
    		}
    	}
    	return days;
    }

    boolean isLeapYear(int year){
    	return year % 400 == 0 || (year %4 == 0 && year % 100 != 0);
    }
}
