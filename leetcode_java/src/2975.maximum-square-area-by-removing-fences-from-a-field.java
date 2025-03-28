/*
 * @lc app=leetcode id=2975 lang=java
 *
 * [2975] Maximum Square Area by Removing Fences From a Field
 *
 * https://leetcode.com/problems/maximum-square-area-by-removing-fences-from-a-field/description/
 *
 * algorithms
 * Medium (21.84%)
 * Likes:    143
 * Dislikes: 118
 * Total Accepted:    15.3K
 * Total Submissions: 64.5K
 * Testcase Example:  '4\n3\n[2,3]\n[2]'
 *
 * There is a large (m - 1) x (n - 1) rectangular field with corners at (1, 1)
 * and (m, n) containing some horizontal and vertical fences given in arrays
 * hFences and vFences respectively.
 * 
 * Horizontal fences are from the coordinates (hFences[i], 1) to (hFences[i],
 * n) and vertical fences are from the coordinates (1, vFences[i]) to (m,
 * vFences[i]).
 * 
 * Return the maximum area of a square field that can be formed by removing
 * some fences (possibly none) or -1 if it is impossible to make a square
 * field.
 * 
 * Since the answer may be large, return it modulo 10^9 + 7.
 * 
 * Note: The field is surrounded by two horizontal fences from the coordinates
 * (1, 1) to (1, n) and (m, 1) to (m, n) and two vertical fences from the
 * coordinates (1, 1) to (m, 1) and (1, n) to (m, n). These fences cannot be
 * removed.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: m = 4, n = 3, hFences = [2,3], vFences = [2]
 * Output: 4
 * Explanation: Removing the horizontal fence at 2 and the vertical fence at 2
 * will give a square field of area 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: m = 6, n = 7, hFences = [2], vFences = [4]
 * Output: -1
 * Explanation: It can be proved that there is no way to create a square field
 * by removing fences.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= m, n <= 10^9
 * 1 <= hFences.length, vFences.length <= 600
 * 1 < hFences[i] < m
 * 1 < vFences[i] < n
 * hFences and vFences are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    long MOD = (long)1e9 + 7;
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Set<Integer> hGaps = getGaps(m, hFences);
        Set<Integer> vGaps = getGaps(n, vFences);
        // System.out.println(Arrays.asList(hGaps, vGaps));
        long ans = -1;
        for(int g : hGaps){
            if(vGaps.contains(g)){
                 ans = Math.max(ans, 1L * g * g);
                 // System.out.println(ans);
            }
        }
        return (int)(ans % MOD);
    }

    Set<Integer> getGaps(int bound, int[] fences){
        fences = Arrays.copyOf(fences, fences.length + 2);
        int n = fences.length;
        fences[n - 1] = 1;
        fences[n - 2] = bound;
        Set<Integer> ret = new HashSet<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                ret.add(Math.abs(fences[i] - fences[j]));
            }
        }
        return ret;
    }
}
// @lc code=end
