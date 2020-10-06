/*
 * @lc app=leetcode id=587 lang=java
 *
 * [587] Erect the Fence
 *
 * https://leetcode.com/problems/erect-the-fence/description/
 *
 * algorithms
 * Hard (35.94%)
 * Likes:    246
 * Dislikes: 192
 * Total Accepted:    9.9K
 * Total Submissions: 27.6K
 * Testcase Example:  '[[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]'
 *
 * There are some trees, where each tree is represented by (x,y) coordinate in
 * a two-dimensional garden. Your job is to fence the entire garden using the
 * minimum length of rope as it is expensive. The garden is well fenced only if
 * all the trees are enclosed. Your task is to help find the coordinates of
 * trees which are exactly located on the fence perimeter.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 * Explanation:
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[1,2],[2,2],[4,2]]
 * Output: [[1,2],[2,2],[4,2]]
 * Explanation:
 * 
 * Even you only have trees in a line, you need to use rope to enclose
 * them. 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * All trees should be enclosed together. You cannot cut the rope to enclose
 * trees that will separate them in more than one group.
 * All input integers will range from 0 to 100.
 * The garden has at least one tree.
 * All coordinates are distinct.
 * Input points have NO order. No order required for output.
 * input types have been changed on April 15, 2019. Please reset to default
 * code definition to get new method signature.
 * 
 * 
 */

// @lc code=start
public class Solution {
    public int orientation(int[] p, int[] q, int[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
    }

    public boolean inBetween(int[] p, int[] i, int[] q) {
        boolean a = i[0] >= p[0] && i[0] <= q[0] || i[0] <= p[0] && i[0] >= q[0];
        boolean b = i[1] >= p[1] && i[1] <= q[1] || i[1] <= p[1] && i[1] >= q[1];
        return a && b;
    }

    public int[][] outerTrees(int[][] points) {
        HashSet<int[]> hull = new HashSet<> ();
        if (points.length < 4) {
            for (int[] p: points)
                hull.add(p);
            return hull.toArray(new int[hull.size()][]);
        }
        int left_most = 0;
        for (int i = 0; i < points.length; i++)
            if (points[i][0] < points[left_most][0])
                left_most = i;
        int p = left_most;
        do {
            int q = (p + 1) % points.length;
            for (int i = 0; i < points.length; i++) {
                if (orientation(points[p], points[i], points[q]) < 0) {
                    q = i;
                }
            }
            for (int i = 0; i < points.length; i++) {
                if (i != p && i != q && orientation(points[p], points[i], points[q]) == 0 && inBetween(points[p], points[i], points[q])) {
                    hull.add(points[i]);
                }
            }
            hull.add(points[q]);
            p = q;
        }
        while (p != left_most);
        return hull.toArray(new int[hull.size()][]);
    }
}
// @lc code=end
