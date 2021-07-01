/*
 * @lc app=leetcode id=1916 lang=java
 *
 * [1916] Count Ways to Build Rooms in an Ant Colony
 *
 * https://leetcode.com/problems/count-ways-to-build-rooms-in-an-ant-colony/description/
 *
 * algorithms
 * Hard (47.98%)
 * Likes:    89
 * Dislikes: 15
 * Total Accepted:    1.6K
 * Total Submissions: 3.4K
 * Testcase Example:  '[-1,0,1]'
 *
 * You are an ant tasked with adding n new rooms numbered 0 to n-1 to your
 * colony. You are given the expansion plan as a 0-indexed integer array of
 * length n, prevRoom, where prevRoom[i] indicates that you must build room
 * prevRoom[i] before building room i, and these two rooms must be connected
 * directly. Room 0 is already built, so prevRoom[0] = -1. The expansion plan
 * is given such that once all the rooms are built, every room will be
 * reachable from room 0.
 * 
 * You can only build one room at a time, and you can travel freely between
 * rooms you have already built only if they are connected. You can choose to
 * build any room as long as its previous room is already built.
 * 
 * Return the number of different orders you can build all the rooms in. Since
 * the answer may be large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: prevRoom = [-1,0,1]
 * Output: 1
 * Explanation: There is only one way to build the additional rooms: 0 → 1 →
 * 2
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * Input: prevRoom = [-1,0,0,1,2]
 * Output: 6
 * Explanation:
 * The 6 ways are:
 * 0 → 1 → 3 → 2 → 4
 * 0 → 2 → 4 → 1 → 3
 * 0 → 1 → 2 → 3 → 4
 * 0 → 1 → 2 → 4 → 3
 * 0 → 2 → 1 → 3 → 4
 * 0 → 2 → 1 → 4 → 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == prevRoom.length
 * 2 <= n <= 10^5
 * prevRoom[0] == -1
 * 0 <= prevRoom[i] < n for all 1 <= i < n
 * Every room is reachable from room 0 once all the rooms are built.
 * 
 */

// @lc code=start
import java.math.*;
class Solution {
    int mod=1000000007;
    public int waysToBuildRooms(int[] prevRoom) {
        int n=prevRoom.length;
        ArrayList<Integer>[] tree= new ArrayList[n];
        for(int i=0;i<n;i++){
            tree[i]=new ArrayList<>();
        }
        for(int i=1;i<n;i++){
            tree[prevRoom[i]].add(i);
        }
        int size[]=new int[n];
        dfs(tree,size,0);
        long fact=1;
        for(int i=1;i<=n;i++){
            fact=(fact*i)%mod;
        }
        long denomin=1;
        for(int i=0;i<n;i++){
            denomin=(denomin*size[i])%mod;
        }
        BigInteger den=BigInteger.valueOf(denomin);
        den=den.modInverse(BigInteger.valueOf(mod));
        return (int)((fact*den.longValue())%mod);
        
    }
    int dfs(ArrayList<Integer>[] tree, int[] size, int root ){
        int ans=1;
        for(int e:tree[root]){
            ans+=dfs(tree,size,e);
        }
        size[root]=ans;
        return ans;
    }
}
// @lc code=end
