/*
 * @lc app=leetcode id=815 lang=java
 *
 * [815] Bus Routes
 *
 * https://leetcode.com/problems/bus-routes/description/
 *
 * algorithms
 * Hard (45.63%)
 * Likes:    2797
 * Dislikes: 72
 * Total Accepted:    116.8K
 * Total Submissions: 256K
 * Testcase Example:  '[[1,2,7],[3,6,7]]\n1\n6'
 *
 * You are given an array routes representing bus routes where routes[i] is a
 * bus route that the i^th bus repeats forever.
 * 
 * 
 * For example, if routes[0] = [1, 5, 7], this means that the 0^th bus travels
 * in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * 
 * 
 * You will start at the bus stop source (You are not on any bus initially),
 * and you want to go to the bus stop target. You can travel between bus stops
 * by buses only.
 * 
 * Return the least number of buses you must take to travel from source to
 * target. Return -1 if it is not possible.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then
 * take the second bus to the bus stop 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target
 * = 12
 * Output: -1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5
 * All the values of routes[i] are unique.
 * sum(routes[i].length) <= 10^5
 * 0 <= routes[i][j] < 10^6
 * 0 <= source, target < 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        Set<Integer>[] rSets = Arrays.stream(routes)
            .map(r -> Arrays.stream(r).boxed().collect(Collectors.toSet()))
            .toArray(Set[]::new);
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] seen = new boolean[n];
        IntStream.range(0, n).filter(i -> rSets[i].contains(source)).forEach(i -> {
                seen[i] = true;
                q.offer(i);
            }
        );
        int step = 0;
        while(!q.isEmpty()){
            // System.out.println(q);
            step++;
            for(int x = q.size(); x > 0; x--){
                int cur = q.poll();
                if(rSets[cur].contains(target)){
                    if(source == target){
                        return 0;
                    }
                    return step;
                }
                IntStream.range(0, n)
                    .filter(i -> !seen[i] && i != cur && intersect(rSets[cur], rSets[i]))
                    .forEach(i -> {
                        q.offer(i);
                        seen[i] = true;
                    });
            }
        }
        return -1;
    }

    boolean intersect(Set<Integer> sa, Set<Integer> sb){
        if(sa.size() > sb.size()){
            return intersect(sb, sa);
        }
        for(int x : sa){
            if(sb.contains(x)){
                return true;
            }
        }
        return false;
    }

}
// @lc code=end
