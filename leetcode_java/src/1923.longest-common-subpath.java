/*
 * @lc app=leetcode id=1923 lang=java
 *
 * [1923] Longest Common Subpath
 *
 * https://leetcode.com/problems/longest-common-subpath/description/
 *
 * algorithms
 * Hard (25.22%)
 * Likes:    125
 * Dislikes: 17
 * Total Accepted:    2K
 * Total Submissions: 8.1K
 * Testcase Example:  '5\n[[0,1,2,3,4],[2,3,4],[4,0,1,2,3]]'
 *
 * There is a country of n cities numbered from 0 to n - 1. In this country,
 * there is a road connecting every pair of cities.
 * 
 * There are m friends numbered from 0 to m - 1 who are traveling through the
 * country. Each one of them will take a path consisting of some cities. Each
 * path is represented by an integer array that contains the visited cities in
 * order. The path may contain a city more than once, but the same city will
 * not be listed consecutively.
 * 
 * Given an integer n and a 2D integer array paths where paths[i] is an integer
 * array representing the path of the i^th friend, return the length of the
 * longest common subpath that is shared by every friend's path, or 0 if there
 * is no common subpath at all.
 * 
 * A subpath of a path is a contiguous sequence of cities within that path.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5, paths = [[0,1,2,3,4],
 * ⁠                      [2,3,4],
 * ⁠                      [4,0,1,2,3]]
 * Output: 2
 * Explanation: The longest common subpath is [2,3].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, paths = [[0],[1],[2]]
 * Output: 0
 * Explanation: There is no common subpath shared by the three paths.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 5, paths = [[0,1,2,3,4],
 * ⁠                      [4,3,2,1,0]]
 * Output: 1
 * Explanation: The possible longest common subpaths are [0], [1], [2], [3],
 * and [4]. All have a length of 1.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^5
 * m == paths.length
 * 2 <= m <= 10^5
 * sum(paths[i].length) <= 10^5
 * 0 <= paths[i][j] < n
 * The same city is not listed multiple times consecutively in paths[i].
 * 
 * 
 */

// @lc code=start
class Solution {
    long mul = 1_000_000_007;
    long mod = (1l<<61)-1;
    long[] pow;
    public int longestCommonSubpath(int n, int[][] paths) {
        int pl = paths.length;
        
        int max = Integer.MAX_VALUE;
        for(int i = 0; i < pl; i++)
            max = Math.min(max, paths[i].length);
        
        pow = new long[max+1];
        pow[0] = 1;
        for(int i = 1; i <= max; i++) {
            pow[i] = multipy(pow[i-1], mul);
        }
        
        int le = 0, ri = max;
        while(le < ri) {
            int mid = le + (ri - le + 1) / 2;
            if(valid(paths, mid))
                le = mid;
            else 
                ri = mid - 1;
        }
        return le;
    }
    
    private boolean valid(int[][] paths, int len) {
        int pl = paths.length;
        Set<Long> prev = null;
        for(int i = 0; i < pl; i++) {
            Set<Long> hashs = rollingHashs(paths[i], len);
            if(prev == null) {
                prev = hashs;
            } else {
                prev.retainAll(hashs);
                if(prev.size() == 0) 
                    return false;
            }
        }
        return true;
    }
    
    private Set<Long> rollingHashs(int[] arr, int len) {
        int l = arr.length;
        long[] hash = new long[l+1];
        for(int i = 1; i <= l; i++) {
            hash[i] = modulo(multipy(hash[i-1], mul) + (arr[i-1]+1));
        }
        Set<Long> ret = new HashSet<>();
        for(int i = len-1; i < l; i++) {
            long soFarHash = hash[i+1];
            long prevHash = hash[i-len+1];
            prevHash = multipy(prevHash, pow[len]);
            long currHash = modulo(soFarHash - prevHash);
            ret.add(currHash);
        }
        return ret;
    }
    
    private long multipy(long a, long b) {
        long al = a&((1l<<31)-1), ah = (a>>>31);
        long bl = b&((1l<<31)-1), bh = (b>>>31);
        long ll = al * bl;
        long mid = al * bh + bl * ah;
        long hh = bh * ah + (mid >>> 31);
        long ret = modulo(modulo(2*hh+ll) + ((mid & (1l<<31)-1)<<31));
        return ret;
    }
    
    private long modulo(long hash) {
        while(hash >= mod)
            hash -= mod;
        while(hash < 0)
            hash += mod;
        return hash;
    }
}
// @lc code=end
