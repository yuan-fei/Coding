/*
 * @lc app=leetcode id=2440 lang=java
 *
 * [2440] Create Components With Same Value
 *
 * https://leetcode.com/problems/create-components-with-same-value/description/
 *
 * algorithms
 * Hard (50.70%)
 * Likes:    85
 * Dislikes: 1
 * Total Accepted:    1.2K
 * Total Submissions: 2.4K
 * Testcase Example:  '[6,2,2,2,6]\n[[0,1],[1,2],[1,3],[3,4]]'
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1.
 * 
 * You are given a 0-indexed integer array nums of length n where nums[i]
 * represents the value of the i^th node. You are also given a 2D integer array
 * edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an
 * edge between nodes ai and bi in the tree.
 * 
 * You are allowed to delete some edges, splitting the tree into multiple
 * connected components. Let the value of a component be the sum of all nums[i]
 * for which node i is in the component.
 * 
 * Return the maximum number of edges you can delete, such that every connected
 * component in the tree has the same value.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [6,2,2,2,6], edges = [[0,1],[1,2],[1,3],[3,4]] 
 * Output: 2 
 * Explanation: The above figure shows how we can delete the edges [0,1] and
 * [3,4]. The created components are nodes [0], [1,2,3] and [4]. The sum of the
 * values in each component equals 6. It can be proven that no better deletion
 * exists, so the answer is 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2], edges = []
 * Output: 0
 * Explanation: There are no edges to be deleted.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 2 * 10^4
 * nums.length == n
 * 1 <= nums[i] <= 50
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= edges[i][0], edges[i][1] <= n - 1
 * edges represents a valid tree.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    int[] nums;
    public int componentValue(int[] nums, int[][] edges) {
        this.nums = nums;
        int n = nums.length;
        adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int[] e : edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int total = total(0, -1);
        // System.out.println(total);
        for(int f = 1; f <= total; f++){
            if(total % f == 0){
                int r = divide(0, -1, f);
                // System.out.println(Arrays.asList(f, r));
                if(r == 0){
                    return total / f - 1;
                }
            }
        }
        return 0;
    }

    int total(int u, int p){
        int ret = 0;
        for(int v :adj[u]){
            if(v != p){
                ret += total(v, u);
            }
        }
        return ret + nums[u];
    }

    int divide(int u, int p, int tSum){
        int sum = 0;
        for(int v : adj[u]){
            if(v != p){
                int r = divide(v, u, tSum);
                // System.out.println(Arrays.asList(v, u, tSum, r));
                if(r == -1){
                    return -1;
                }
                if(r != 0){
                    sum += r;
                }
            }
        }
        sum += nums[u];

        if(sum <= tSum){
            return sum % tSum;
        }
        else{
            return -1;
        }
    }
}
// @lc code=end
