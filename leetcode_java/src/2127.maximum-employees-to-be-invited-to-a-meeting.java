/*
 * @lc app=leetcode id=2127 lang=java
 *
 * [2127] Maximum Employees to Be Invited to a Meeting
 *
 * https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/description/
 *
 * algorithms
 * Hard (29.03%)
 * Likes:    399
 * Dislikes: 4
 * Total Accepted:    4K
 * Total Submissions: 13.6K
 * Testcase Example:  '[2,2,1,2]'
 *
 * A company is organizing a meeting and has a list of n employees, waiting to
 * be invited. They have arranged for a large circular table, capable of
 * seating any number of employees.
 * 
 * The employees are numbered from 0 to n - 1. Each employee has a favorite
 * person and they will attend the meeting only if they can sit next to their
 * favorite person at the table. The favorite person of an employee is not
 * themself.
 * 
 * Given a 0-indexed integer array favorite, where favorite[i] denotes the
 * favorite person of the i^th employee, return the maximum number of employees
 * that can be invited to the meeting.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: favorite = [2,2,1,2]
 * Output: 3
 * Explanation:
 * The above figure shows how the company can invite employees 0, 1, and 2, and
 * seat them at the round table.
 * All employees cannot be invited because employee 2 cannot sit beside
 * employees 0, 1, and 3, simultaneously.
 * Note that the company can also invite employees 1, 2, and 3, and give them
 * their desired seats.
 * The maximum number of employees that can be invited to the meeting is 3. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: favorite = [1,2,0]
 * Output: 3
 * Explanation: 
 * Each employee is the favorite person of at least one other employee, and the
 * only way the company can invite them is if they invite every employee.
 * The seating arrangement will be the same as that in the figure given in
 * example 1:
 * - Employee 0 will sit between employees 2 and 1.
 * - Employee 1 will sit between employees 0 and 2.
 * - Employee 2 will sit between employees 1 and 0.
 * The maximum number of employees that can be invited to the meeting is 3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: favorite = [3,0,1,4,1]
 * Output: 4
 * Explanation:
 * The above figure shows how the company will invite employees 0, 1, 3, and 4,
 * and seat them at the round table.
 * Employee 2 cannot be invited because the two spots next to their favorite
 * employee 1 are taken.
 * So the company leaves them out of the meeting.
 * The maximum number of employees that can be invited to the meeting is 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == favorite.length
 * 2 <= n <= 10^5
 * 0 <= favorite[i] <= n - 1
 * favorite[i] != i
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumInvitations(int[] favorite) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < favorite.length; i++) {
            graph.add(new ArrayList<>());
        }

        int answer = 0;

        List<List<Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < favorite.length; i++) {
            if (i == favorite[favorite[i]]) {
                if (i < favorite[i]) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(favorite[i]);
                    pairs.add(pair);
                }
            } else {
                graph.get(favorite[i]).add(i);
            }
        }

        boolean[] visited = new boolean[favorite.length];
        for (List<Integer> pair: pairs) {
            answer += dfs(graph, pair.get(0), visited) + dfs(graph, pair.get(1), visited);
        }
        
        int[] counter = new int[favorite.length];
        int[] round = new int[favorite.length];

        int rnd = 1;

        int circleMax = 0;

        for (int i = 0; i < favorite.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (round[i] != 0) {
                continue;
            }

            int cnt = 1;
            int j = i;
            while (counter[j] == 0) {
                counter[j] = cnt;
                round[j] = rnd;
                j = favorite[j];
                cnt++;
            }
            if (round[j] == rnd) {
                circleMax = Math.max(circleMax, cnt - counter[j]);
            }
            rnd++;
        }
        return Math.max(circleMax, answer);
    }

    private int dfs(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        int max = 0;
        for (int neighbor: graph.get(node)) {
            max = Math.max(max, dfs(graph, neighbor, visited));
        }
        return max + 1;
    }
}
// @lc code=end
