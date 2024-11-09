/*
 * @lc app=leetcode id=2933 lang=java
 *
 * [2933] High-Access Employees
 *
 * https://leetcode.com/problems/high-access-employees/description/
 *
 * algorithms
 * Medium (43.06%)
 * Likes:    196
 * Dislikes: 20
 * Total Accepted:    23.1K
 * Total Submissions: 51.5K
 * Testcase Example:  '[["a","0549"],["b","0457"],["a","0532"],["a","0621"],["b","0540"]]'
 *
 * You are given a 2D 0-indexed array of strings, access_times, with size n.
 * For each i where 0 <= i <= n - 1, access_times[i][0] represents the name of
 * an employee, and access_times[i][1] represents the access time of that
 * employee. All entries in access_times are within the same day.
 * 
 * The access time is represented as four digits using a 24-hour time format,
 * for example, "0800" or "2250".
 * 
 * An employee is said to be high-access if he has accessed the system three or
 * more times within a one-hour period.
 * 
 * Times with exactly one hour of difference are not considered part of the
 * same one-hour period. For example, "0815" and "0915" are not part of the
 * same one-hour period.
 * 
 * Access times at the start and end of the day are not counted within the same
 * one-hour period. For example, "0005" and "2350" are not part of the same
 * one-hour period.
 * 
 * Return a list that contains the names of high-access employees with any
 * order you want.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: access_times =
 * [["a","0549"],["b","0457"],["a","0532"],["a","0621"],["b","0540"]]
 * Output: ["a"]
 * Explanation: "a" has three access times in the one-hour period of [05:32,
 * 06:31] which are 05:32, 05:49, and 06:21.
 * But "b" does not have more than two access times at all.
 * So the answer is ["a"].
 * 
 * Example 2:
 * 
 * 
 * Input: access_times =
 * [["d","0002"],["c","0808"],["c","0829"],["e","0215"],["d","1508"],["d","1444"],["d","1410"],["c","0809"]]
 * Output: ["c","d"]
 * Explanation: "c" has three access times in the one-hour period of [08:08,
 * 09:07] which are 08:08, 08:09, and 08:29.
 * "d" has also three access times in the one-hour period of [14:10, 15:09]
 * which are 14:10, 14:44, and 15:08.
 * However, "e" has just one access time, so it can not be in the answer and
 * the final answer is ["c","d"].
 * 
 * Example 3:
 * 
 * 
 * Input: access_times =
 * [["cd","1025"],["ab","1025"],["cd","1046"],["cd","1055"],["ab","1124"],["ab","1120"]]
 * Output: ["ab","cd"]
 * Explanation: "ab" has three access times in the one-hour period of [10:25,
 * 11:24] which are 10:25, 11:20, and 11:24.
 * "cd" has also three access times in the one-hour period of [10:25, 11:24]
 * which are 10:25, 10:46, and 10:55.
 * So the answer is ["ab","cd"].
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= access_times.length <= 100
 * access_times[i].length == 2
 * 1 <= access_times[i][0].length <= 10
 * access_times[i][0] consists only of English small letters.
 * access_times[i][1].length == 4
 * access_times[i][1] is in 24-hour time format.
 * access_times[i][1] consists only of '0' to '9'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        Map<Integer, List<String>> cntToEmployee = new HashMap<>();
        Map<String, List<String>> accessesByEmployee = access_times.stream()
                .collect(Collectors.groupingBy((List<String> aa) -> aa.get(0),
                        Collectors.mapping((List<String> aa) -> aa.get(1), Collectors.toList())));
        List<String> ans = new ArrayList<>();
        for(String emp : accessesByEmployee.keySet()){
            List<Integer> accessesInMinutes = accessesByEmployee.get(emp).stream().map(a -> toMinutes(a)).sorted().collect(Collectors.toList());
            int cnt = countMostAccessInOneHour(accessesInMinutes);
            if(cnt >= 3){
                ans.add(emp);
            }
        }
        return ans;
    }

    int countMostAccessInOneHour(List<Integer> accesses){
        int j = 0;
        int ret = 0;
        for(int i = 0; i < accesses.size(); i++){
            while(j < accesses.size() && accesses.get(j) - accesses.get(i) < 60){
                j++;
            }
            ret = Math.max(ret, j - i);
        }
        return ret;
    }

    int toMinutes(String s){
        return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(2, 4));
    }
}
// @lc code=end
