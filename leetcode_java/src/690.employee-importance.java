/*
 * @lc app=leetcode id=690 lang=java
 *
 * [690] Employee Importance
 *
 * https://leetcode.com/problems/employee-importance/description/
 *
 * algorithms
 * Easy (59.33%)
 * Likes:    1065
 * Dislikes: 935
 * Total Accepted:    122.1K
 * Total Submissions: 205.3K
 * Testcase Example:  '[[1,5,[2,3]],[2,3,[]],[3,3,[]]]\n1'
 *
 * You have a data structure of employee information, which includes the
 * employee's unique id, their importance value, and their direct subordinates'
 * id.
 * 
 * You are given an array of employees employees where:
 * 
 * 
 * employees[i].id is the ID of the i^th employee.
 * employees[i].importance is the importance value of the i^th employee.
 * employees[i].subordinates is a list of the IDs of the subordinates of the
 * i^th employee.
 * 
 * 
 * Given an integer id that represents the ID of an employee, return the total
 * importance value of this employee and all their subordinates.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
 * Output: 11
 * Explanation: Employee 1 has importance value 5, and he has two direct
 * subordinates: employee 2 and employee 3.
 * They both have importance value 3.
 * So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
 * Output: -3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= employees.length <= 2000
 * 1 <= employees[i].id <= 2000
 * All employees[i].id are unique.
 * -100 <= employees[i].importance <= 100
 * One employee has at most one direct leader and may have several
 * subordinates.
 * id is guaranteed to be a valid employee id.
 * 
 * 
 */

// @lc code=start
/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class Solution {
	List<Employee> employees;
	Map<Integer, Integer> score;
	int MIN = Integer.MIN_VALUE;
	Map<Integer, Employee> map;
    public int getImportance(List<Employee> employees, int id) {
    	map = new HashMap<>();
    	score = new HashMap<>();
    	int n = employees.size();
        this.employees = employees;
        for (Employee e : employees) {
        	map.put(e.id, e);
        }
        return dfs(id);
    }
    
    int dfs(int id){
    	if(!score.containsKey(id)){
    		int s = map.get(id).importance;
    		for(int e : map.get(id).subordinates){
    			s += dfs(e);
    		}
    		score.put(id, s);
    	}
    	return score.get(id);
    }
}
// @lc code=end
