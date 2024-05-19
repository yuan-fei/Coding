/*
 * @lc app=leetcode id=2836 lang=java
 *
 * [2836] Maximize Value of Function in a Ball Passing Game
 *
 * https://leetcode.com/problems/maximize-value-of-function-in-a-ball-passing-game/description/
 *
 * algorithms
 * Hard (29.05%)
 * Likes:    283
 * Dislikes: 91
 * Total Accepted:    4.4K
 * Total Submissions: 14.5K
 * Testcase Example:  '[2,0,1]\n4'
 *
 * You are given an integer array receiver of length n and an integer k. n
 * players are playing a ball-passing game.
 * 
 * You choose the starting player, i. The game proceeds as follows: player i
 * passes the ball to player receiver[i], who then passes it to
 * receiver[receiver[i]], and so on, for k passes in total. The game's score is
 * the sum of the indices of the players who touched the ball, including
 * repetitions, i.e. i + receiver[i] + receiver[receiver[i]] + ... +
 * receiver^(k)[i].
 * 
 * Return the maximum possible score.
 * 
 * Notes:
 * 
 * 
 * receiver may contain duplicates.
 * receiver[i] may be equal to i.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: receiver = [2,0,1], k = 4
 * 
 * Output: 6
 * 
 * Explanation:
 * 
 * Starting with player i = 2 the initial score is
 * 2:
 * 
 * 
 * 
 * 
 * Pass
 * Sender Index
 * Receiver Index
 * Score
 * 
 * 
 * 1
 * 2
 * 1
 * 3
 * 
 * 
 * 2
 * 1
 * 0
 * 3
 * 
 * 
 * 3
 * 0
 * 2
 * 5
 * 
 * 
 * 4
 * 2
 * 1
 * 6
 * 
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: receiver = [1,1,1,2,3], k = 3
 * 
 * Output: 10
 * 
 * Explanation:
 * 
 * Starting with player i = 4 the initial score is
 * 4:
 * 
 * 
 * 
 * 
 * Pass
 * Sender Index
 * Receiver Index
 * Score
 * 
 * 
 * 1
 * 4
 * 3
 * 7
 * 
 * 
 * 2
 * 3
 * 2
 * 9
 * 
 * 
 * 3
 * 2
 * 1
 * 10
 * 
 * 
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= receiver.length == n <= 10^5
 * 0 <= receiver[i] <= n - 1
 * 1 <= k <= 10^10
 * 
 * 
 */

// @lc code=start
class Solution {
    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        int n = receiver.size();
        boolean[] seen = new boolean[n];
        Map<Integer, Group> idToGroup = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(!seen[i]){
                List<Integer> traversed = new ArrayList<>();
                int lastSeen = dfs(receiver, seen, i, traversed);
                if(!idToGroup.containsKey(lastSeen)){
                    // cycle
                    Group group = new Group(true, traversed.get(0), new HashMap<>(), new ArrayList<>());
                    for(int j = 0; j < traversed.size(); j++){
                        group.add(traversed.get(j));
                        idToGroup.put(traversed.get(j), group);
                        if(lastSeen == traversed.get(j) && j < traversed.size() - 1){
                            group = new Group(false, lastSeen, new HashMap<>(), new ArrayList<>());
                        }
                    }
                }
                else{
                    Group group = idToGroup.get(lastSeen);
                    if(group.isCycle() || group.idToInGroupIds().size() - 1 != group.idToInGroupIds().get(lastSeen)){
                        group = new Group(false, lastSeen, new HashMap<>(), new ArrayList<>());
                    }
                    for(int j = 0; j < traversed.size(); j++){
                        group.add(traversed.get(j));
                        idToGroup.put(traversed.get(j), group);
                    }
                }
            }
        }
        // System.out.println(idToGroup);
        long ans = 0;
        for(int i = 0; i < n; i++){
            long sum = 0;
            long kk = k + 1;
            int id = i;
            Group group = idToGroup.get(id);
            int inGroupId = group.idToInGroupIds().get(id);
            while(!group.isCycle()){
                int cnt = (int)Math.min(kk, inGroupId + 1);
                sum += group.pSum().get(inGroupId + 1) - group.pSum().get(inGroupId + 1 - cnt);
                kk -= cnt;
                id = group.next();
                group = idToGroup.get(id);
                inGroupId = group.idToInGroupIds().get(id);
            }
            // System.out.println(Arrays.asList(kk, sum));
            if(group.isCycle()){
                sum += kk / group.idToInGroupIds.size() * group.pSum().get(group.pSum().size() - 1);
                int cnt = (int)(kk % group.idToInGroupIds.size());

                sum += group.pSum().get(inGroupId + 1) - group.pSum().get(Math.max(0, inGroupId + 1 - cnt))
                        + group.pSum().get(group.idToInGroupIds.size()) - group.pSum().get(Math.min(group.idToInGroupIds.size(), group.idToInGroupIds.size() + inGroupId + 1 - cnt));
            }
            // System.out.println(sum);
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    int dfs(List<Integer> receiver, boolean[] seen, int cur, List<Integer> traversed){
        seen[cur] = true;
        int nxt = receiver.get(cur);
        if(!seen[nxt]){
            int ret = dfs(receiver, seen, nxt, traversed);
            traversed.add(cur);
            return ret;
        }
        else{
            traversed.add(cur);
            return nxt;
        }
    }

    record Group(boolean isCycle, int next, Map<Integer, Integer> idToInGroupIds, List<Long> pSum){
        public Group{
            pSum.add(0L);
        }
        void add(int id){
            pSum.add(pSum.get(pSum.size() - 1) + id);
            idToInGroupIds.put(id, idToInGroupIds.size());
        }
    }
}
// @lc code=end
