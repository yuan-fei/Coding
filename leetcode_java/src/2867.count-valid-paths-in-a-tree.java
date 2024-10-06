/*
 * @lc app=leetcode id=2867 lang=java
 *
 * [2867] Count Valid Paths in a Tree
 *
 * https://leetcode.com/problems/count-valid-paths-in-a-tree/description/
 *
 * algorithms
 * Hard (34.25%)
 * Likes:    252
 * Dislikes: 7
 * Total Accepted:    7.2K
 * Total Submissions: 20.3K
 * Testcase Example:  '5\n[[1,2],[1,3],[2,4],[2,5]]'
 *
 * There is an undirected tree with n nodes labeled from 1 to n. You are given
 * the integer n and a 2D integer array edges of length n - 1, where edges[i] =
 * [ui, vi] indicates that there is an edge between nodes ui and vi in the
 * tree.
 * 
 * Return the number of valid paths in the tree.
 * 
 * A path (a, b) is valid if there exists exactly one prime number among the
 * node labels in the path from a to b.
 * 
 * Note that:
 * 
 * 
 * The path (a, b) is a sequence of distinct nodes starting with node a and
 * ending with node b such that every two adjacent nodes in the sequence share
 * an edge in the tree.
 * Path (a, b) and path (b, a) are considered the same and counted only
 * once.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
 * Output: 4
 * Explanation: The pairs with exactly one prime number on the path between
 * them are: 
 * - (1, 2) since the path from 1 to 2 contains prime number 2. 
 * - (1, 3) since the path from 1 to 3 contains prime number 3.
 * - (1, 4) since the path from 1 to 4 contains prime number 2.
 * - (2, 4) since the path from 2 to 4 contains prime number 2.
 * It can be shown that there are only 4 valid paths.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
 * Output: 6
 * Explanation: The pairs with exactly one prime number on the path between
 * them are: 
 * - (1, 2) since the path from 1 to 2 contains prime number 2.
 * - (1, 3) since the path from 1 to 3 contains prime number 3.
 * - (1, 4) since the path from 1 to 4 contains prime number 2.
 * - (1, 6) since the path from 1 to 6 contains prime number 3.
 * - (2, 4) since the path from 2 to 4 contains prime number 2.
 * - (3, 6) since the path from 3 to 6 contains prime number 3.
 * It can be shown that there are only 6 valid paths.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * The input is generated such that edges represent a valid tree.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    Set<Integer> primeList;
    public long countPaths(int n, int[][] edges) {
        primeList = sieve(n);
        int primeNodeCount = 0;
        adj = new List[n + 1];
        for(int i = 1; i <= n; i++){
            adj[i] = new ArrayList<>();
            if(primeList.contains(i)){
                primeNodeCount++;
            }
        }
        for(int[] e : edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        PathCount pc = dfs(1, -1);
        return pc.primePath() - primeNodeCount;
    }

    PathCount dfs(int u, int p){
        long noPrimePathFromRoot = 0;
        long primePathFromRoot = 0; 
        long primePath = 0;
        
        boolean isPrimeRoot = primeList.contains(u);
        
        for(int v : adj[u]){
            if(v != p){
                PathCount childPathCount = dfs(v, u);
                primePath += childPathCount.primePath();
                if(isPrimeRoot){
                    primePath += childPathCount.noPrimePathFromRoot() * primePathFromRoot;
                    noPrimePathFromRoot = 0;
                    primePathFromRoot += childPathCount.noPrimePathFromRoot();
                }
                else{
                    primePath += childPathCount.primePathFromRoot() * noPrimePathFromRoot;
                    primePath += childPathCount.noPrimePathFromRoot() * primePathFromRoot;
                    noPrimePathFromRoot += childPathCount.noPrimePathFromRoot();
                    primePathFromRoot += childPathCount.primePathFromRoot();
                }
                // System.out.println(Arrays.asList(u, v) + " = " + Arrays.asList(noPrimePathFromRoot, primePathFromRoot, primePath));
            }
        }
        noPrimePathFromRoot += (isPrimeRoot ? 0 : 1);
        primePathFromRoot += (isPrimeRoot ? 1 : 0);
        primePath += primePathFromRoot;
        PathCount ret = new PathCount(noPrimePathFromRoot, primePathFromRoot, primePath);
        // System.out.println(u + " = " + ret);
        return ret;
    }

    /** O(nloglogn) */
    Set<Integer> sieve(int n) {
        Set<Integer> primes = new HashSet<>();
        boolean[] isComposite = new boolean[n + 1];
        for (int i = 2; i < isComposite.length; i++) {
            if (!isComposite[i]) {
                primes.add(i);
            }
            for (long j = 1L * i * i; j < isComposite.length; j += i) {
                isComposite[(int)j] = true;
            }
        }

        return primes;
    }
}

record PathCount (long noPrimePathFromRoot, long primePathFromRoot, long primePath){}
// @lc code=end
