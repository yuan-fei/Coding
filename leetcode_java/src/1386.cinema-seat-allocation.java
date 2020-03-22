/*
 * @lc app=leetcode id=1386 lang=java
 *
 * [1386] Cinema Seat Allocation
 *
 * https://leetcode.com/problems/cinema-seat-allocation/description/
 *
 * algorithms
 * Medium (25.24%)
 * Likes:    13
 * Dislikes: 22
 * Total Accepted:    1.8K
 * Total Submissions: 7.1K
 * Testcase Example:  '3\n[[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]'
 *
 * 
 * 
 * A cinema has n rows of seats, numbered from 1 to n and there are ten seats
 * in each row, labelled from 1 to 10 as shown in the figure above.
 * 
 * Given the array reservedSeats containing the numbers of seats already
 * reserved, for example, reservedSeats[i]=[3,8] means the seat located in row
 * 3 and labelled with 8 is already reserved. 
 * 
 * Return the maximum number of four-person families you can allocate on the
 * cinema seats. A four-person family occupies fours seats in one row, that are
 * next to each other. Seats across an aisle (such as [3,3] and [3,4]) are not
 * considered to be next to each other, however, It is permissible for the
 * four-person family to be separated by an aisle, but in that case, exactly
 * two people have to sit on each side of the aisle.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * Output: 4
 * Explanation: The figure above shows the optimal allocation for four
 * families, where seats mark with blue are already reserved and contiguous
 * seats mark with orange are for one family. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
 * Output: 2
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
 * Output: 4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^9
 * 1 <= reservedSeats.length <= min(10*n, 10^4)
 * reservedSeats[i].length == 2
 * 1 <= reservedSeats[i][0] <= n
 * 1 <= reservedSeats[i][1] <= 10
 * All reservedSeats[i] are distinct.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int pattern = (1 << 4) - 1;
        int pLeft = pattern << 1;
        int pMiddle = pattern << 3;
        int pRight = pattern << 5;
        
        Map<Integer, Integer> resv = new HashMap<>();
        for(int[] s : reservedSeats){
        	int row = s[0];
        	int col = s[1];
        	int r = resv.getOrDefault(row, 0);
        	r |= (1 << (col - 1));
        	resv.put(row, r);
        }

        int cnt = 0;
        for (int r : resv.values()) {
        	int side = 0;
        	int middle = 0;
			// System.out.println(Arrays.asList(pattern));
        	if((r & pLeft) == 0){
        		side++;
        	}
        	if((r & pRight) == 0){
        		side++;
        	}
        	if((r & pMiddle) == 0){
        		middle++;
        	}

        	cnt += Math.max(middle, side);
        	// System.out.println(cnt);
        }
        // System.out.println(resv);
        return 2 * (n - resv.size()) + cnt ;
    }
}
// @lc code=end
