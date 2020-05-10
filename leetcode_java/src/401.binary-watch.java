/*
 * @lc app=leetcode id=401 lang=java
 *
 * [401] Binary Watch
 *
 * https://leetcode.com/problems/binary-watch/description/
 *
 * algorithms
 * Easy (46.82%)
 * Likes:    523
 * Dislikes: 888
 * Total Accepted:    79.4K
 * Total Submissions: 169.4K
 * Testcase Example:  '0'
 *
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and
 * the 6 LEDs on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the
 * right.
 * 
 * For example, the above binary watch reads "3:25".
 * 
 * Given a non-negative integer n which represents the number of LEDs that are
 * currently on, return all possible times the watch could represent.
 * 
 * Example:
 * Input: n = 1Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04",
 * "0:08", "0:16", "0:32"]
 * 
 * 
 * Note:
 * 
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid,
 * it should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero, for
 * example "10:2" is not valid, it should be "10:02".
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> readBinaryWatch(int num) {
    	List<String> res = new ArrayList<>();
    	for (int i = 0; i < (1 << 10); i++) {
			if(Integer.bitCount(i) == num){
				add(res, i);
			}	    		
    	}
    	return res;
    }

    void add(List<String> res, int n){
    	int m = n & 63;
    	int h = (n >> 6) & 15;
    	if(h <= 11 && m <= 59){
    		res.add(h + ":"+ ((m < 10) ? "0" + m : m));
    	}
    }
    // void subset(int[] digits, int max, int cnt, int pos, int sum, List<Integer> res){
    // 	if(cnt == 0){
    // 		if(sum <= max){
    // 			res.add(sum);
    // 		}
    // 		return;
    // 	}
    // 	sum += digits[pos];
    // 	for(int i = pos + 1; i < digits.length - cnt; i++){
    // 		subset(digits, max, cnt - 1, i, sum, res);
    // 	}
    // }
}
// @lc code=end
