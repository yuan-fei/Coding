/*
 * @lc app=leetcode id=551 lang=java
 *
 * [551] Student Attendance Record I
 *
 * https://leetcode.com/problems/student-attendance-record-i/description/
 *
 * algorithms
 * Easy (46.01%)
 * Likes:    312
 * Dislikes: 860
 * Total Accepted:    93.8K
 * Total Submissions: 203.9K
 * Testcase Example:  '"PPALLP"'
 *
 * You are given a string representing an attendance record for a student. The
 * record only contains the following three characters:
 * 
 * 
 * 
 * 'A' : Absent. 
 * 'L' : Late.
 * ⁠'P' : Present. 
 * 
 * 
 * 
 * 
 * A student could be rewarded if his attendance record doesn't contain more
 * than one 'A' (absent) or more than two continuous 'L' (late).    
 * 
 * You need to return whether the student could be rewarded according to his
 * attendance record.
 * 
 * Example 1:
 * 
 * Input: "PPALLP"
 * Output: True
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "PPALLL"
 * Output: False
 * 
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkRecord(String s) {
    	int cntA = 0;
    	int contL = 0;
        for(char c : s.toCharArray()){
        	if(c == 'A'){
        		cntA++;
        		if(cntA > 1){
        			return false;
        		}
        	}
        }
        int id = s.indexOf("LLL");
        return id < 0;
    }
}
// @lc code=end
