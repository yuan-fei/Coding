/*
 * @lc app=leetcode id=957 lang=java
 *
 * [957] Prison Cells After N Days
 *
 * https://leetcode.com/problems/prison-cells-after-n-days/description/
 *
 * algorithms
 * Medium (39.00%)
 * Likes:    1442
 * Dislikes: 1711
 * Total Accepted:    155.3K
 * Total Submissions: 398.1K
 * Testcase Example:  '[0,1,0,1,1,0,0,1]\n7'
 *
 * There are 8 prison cells in a row and each cell is either occupied or
 * vacant.
 * 
 * Each day, whether the cell is occupied or vacant changes according to the
 * following rules:
 * 
 * 
 * If a cell has two adjacent neighbors that are both occupied or both vacant,
 * then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * 
 * 
 * Note that because the prison is a row, the first and the last cells in the
 * row can't have two adjacent neighbors.
 * 
 * You are given an integer array cells where cells[i] == 1 if the i^th cell is
 * occupied and cells[i] == 0 if the i^th cell is vacant, and you are given an
 * integer n.
 * 
 * Return the state of the prison after n days (i.e., n such changes described
 * above).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: cells = [0,1,0,1,1,0,0,1], n = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation: The following table summarizes the state of the prison on each
 * day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: cells = [1,0,0,1,0,0,1,0], n = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * cells.length == 8
 * cells[i] is either 0 or 1.
 * 1 <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> seq = new ArrayList<>();
        int code = encode(cells);
        seen.add(code);
        seq.add(code);
        int cycleStart = -1;
        for(int x = 0; x < n; x++){
            int newCode = 0;
            for(int i = 1; i < 7; i++){
                newCode |= (1 - (get(code, i - 1) ^ get(code, i + 1))) << i;
            }
            code = newCode;
            if(!seen.add(code)){
                for(int i = 0; i < seq.size(); i++){
                    if(seq.get(i) == code){
                        cycleStart = i;
                    }
                }
                break;
            }
            else{
                seq.add(code);
            }
        }
        // seq.forEach(c -> System.out.println(Arrays.toString(decode(c))));
        // System.out.println(Arrays.asList(cycleStart, seq.size()));
        if(cycleStart == -1 || n < cycleStart){
            return decode(seq.get(n));
        }
        return decode(seq.get(cycleStart + (n - cycleStart) % (seq.size() - cycleStart)));
    }

    int get(int x, int i){
        return (x >> i) & 1;
    }

    int encode(int[] state){
        int ret = 0;
        for(int i = 0; i < state.length; i++){
            ret |= (state[i] << i);
        }
        return ret;
    }

    int[] decode(int x){
        int[] ret = new int[8];
        for(int i = 0; i < ret.length; i++){
            ret[i] = get(x, i);
        }
        return ret;
    }
}
// @lc code=end
