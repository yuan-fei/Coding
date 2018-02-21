/*
 * [51] N-Queens
 *
 * https://leetcode.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (32.80%)
 * Total Accepted:    95K
 * Total Submissions: 289K
 * Testcase Example:  '4'
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * 
 * 
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively.
 * 
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * 
 * [
 * ⁠[".Q..",  // Solution 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 * 
 * ⁠["..Q.",  // Solution 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * 
 */
class Solution {
    public List<List<String>> solveNQueens(int n) {
    	List<List<String>> resultSet = new ArrayList<List<String>>();
        solveNQueens(n, 0, resultSet, new ArrayList<Integer>());
        return resultSet;
    }

    private void solveNQueens(int n, int row, List<List<String>> resultSet, List<Integer> current) {
    	if(!isValidPlacement(current)){
    		return;
    	}

    	if(row == n){
    		resultSet.add(generateSolution(current));
    		return;
    	}

    	for (int i = 0; i < n; i++) {
    		current.add(i);
    		solveNQueens(n, row + 1, resultSet, current);
    		current.remove(current.size() - 1);
    	}
    }

    private boolean isValidPlacement(List<Integer> current){
    	if(current.size() == 0){
    		return true;
    	}
    	int last = current.get(current.size() - 1);
    	for(int i = 0; i < current.size() - 1; i++){
    		int placement = current.get(i);
    		if(placement == last || Math.abs(placement - last) == Math.abs(i - current.size() + 1)){
    			return false;
    		}
    	}
    	return true;
    }

    private List<String> generateSolution(List<Integer> solution){
    	List<String> result = new ArrayList<String>();
    	for (int i = 0; i < solution.size(); i++) {
    		String s = "";
    		for (int j = 0; j < solution.size(); j++) {
    			if(j == solution.get(i)){
    				s += "Q";
    			}
    			else{
    				s += ".";
    			}
    		}
    		result.add(s);
    	}
    	return result;
    }
}
