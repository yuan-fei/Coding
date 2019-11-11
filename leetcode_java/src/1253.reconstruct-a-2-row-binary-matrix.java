/*
 * @lc app=leetcode id=1253 lang=java
 *
 * [1253] Reconstruct a 2-Row Binary Matrix
 *
 * https://leetcode.com/problems/reconstruct-a-2-row-binary-matrix/description/
 *
 * algorithms
 * Medium (36.22%)
 * Likes:    39
 * Dislikes: 1
 * Total Accepted:    4.1K
 * Total Submissions: 11.3K
 * Testcase Example:  '2\n1\n[1,1,1]'
 *
 * Given the following details of a matrix with n columns and 2 rows :
 * 
 * 
 * The matrix is a binary matrix, which means each element in the matrix can be
 * 0 or 1.
 * The sum of elements of the 0-th(upper) row is given as upper.
 * The sum of elements of the 1-st(lower) row is given as lower.
 * The sum of elements in the i-th column(0-indexed) is colsum[i], where colsum
 * is given as an integer array with length n.
 * 
 * 
 * Your task is to reconstruct the matrix with upper, lower and colsum.
 * 
 * Return it as a 2-D integer array.
 * 
 * If there are more than one valid solution, any of them will be accepted.
 * 
 * If no valid solution exists, return an empty 2-D array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: upper = 2, lower = 1, colsum = [1,1,1]
 * Output: [[1,1,0],[0,0,1]]
 * Explanation: [[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct
 * answers.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: upper = 2, lower = 3, colsum = [2,2,1,1]
 * Output: []
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
 * Output: [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= colsum.length <= 10^5
 * 0 <= upper, lower <= colsum.length
 * 0 <= colsum[i] <= 2
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
    	List<List<Integer>> ans = Arrays.asList(new ArrayList<Integer>(), new ArrayList<Integer>());
        int diff = Math.abs(upper - lower);
        int total = 0;
        int oneTotal = 0;
        for (int s : colsum) {
        	total += s;
        	oneTotal += (s == 1)? 1 : 0;
        }
        if(total != upper + lower || oneTotal < diff){
        	return new ArrayList<>();
        }
        List<Integer> large;
        List<Integer> small;
        if(upper > lower){
        	large = ans.get(0);
        	small = ans.get(1);
        }
        else{
        	large = ans.get(1);
        	small = ans.get(0);	
        }

        int coin = 1;
        for(int i = 0; i < colsum.length; i++){
        	if(colsum[i] == 2){
        		large.add(1);
        		small.add(1);
        	}
        	else if(colsum[i] == 0){
        		large.add(0);
        		small.add(0);
        	}
        	else{
        		if(diff > 0){
        			large.add(1);
        			small.add(0);
        			diff--;
        		}
        		else{
        			large.add(coin);
        			small.add(1 - coin);
        			coin = 1 - coin;
        		}
        	}
        }
        return ans;
    }
}
// @lc code=end
