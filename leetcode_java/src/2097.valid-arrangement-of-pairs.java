/*
 * @lc app=leetcode id=2097 lang=java
 *
 * [2097] Valid Arrangement of Pairs
 *
 * https://leetcode.com/problems/valid-arrangement-of-pairs/description/
 *
 * algorithms
 * Hard (26.14%)
 * Likes:    69
 * Dislikes: 8
 * Total Accepted:    1K
 * Total Submissions: 3.9K
 * Testcase Example:  '[[5,1],[4,5],[11,9],[9,4]]'
 *
 * You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti,
 * endi]. An arrangement of pairs is valid if for every index i where 1 <= i <
 * pairs.length, we have endi-1 == starti.
 * 
 * Return any valid arrangement of pairs.
 * 
 * Note: The inputs will be generated such that there exists a valid
 * arrangement of pairs.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
 * Output: [[11,9],[9,4],[4,5],[5,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 9 == 9 = start1 
 * end1 = 4 == 4 = start2
 * end2 = 5 == 5 = start3
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: pairs = [[1,3],[3,2],[2,1]]
 * Output: [[1,3],[3,2],[2,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 3 == 3 = start1
 * end1 = 2 == 2 = start2
 * The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also
 * valid.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: pairs = [[1,2],[1,3],[2,1]]
 * Output: [[1,2],[2,1],[1,3]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 2 == 2 = start1
 * end1 = 1 == 1 = start2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= pairs.length <= 10^5
 * pairs[i].length == 2
 * 0 <= starti, endi <= 10^9
 * starti != endi
 * No two pairs are exactly the same.
 * There exists a valid arrangement of pairs.
 * 
 * 
 */

// @lc code=start
class Solution {
    Map<Integer, Queue<Integer>> m;
    int[][] pairs;
    public int[][] validArrangement(int[][] pairs) {
        this.pairs = pairs;
        m = new HashMap<>();
        Map<Integer, Integer> degrees = new HashMap<>();
        for(int i = 0; i < pairs.length; i++){
            Queue<Integer> s = m.getOrDefault(pairs[i][0], new LinkedList<>());
            s.offer(i);
            m.put(pairs[i][0], s);
            degrees.put(pairs[i][0], degrees.getOrDefault(pairs[i][0], 0) + 1);
            degrees.put(pairs[i][1], degrees.getOrDefault(pairs[i][1], 0) - 1);
        }
        int start = -1;
        for(int k : degrees.keySet()){
            if(degrees.get(k) > 0 && degrees.get(k) % 2 == 1){
                start = k;
                break;
            }
        }
        // System.out.println(start);
        // System.out.println(m);
        if(start == -1){
            start = m.keySet().iterator().next();
        }
        List<Integer> ret = new ArrayList<>();
        euler(start, -1, ret);
        int[][] res = new int[pairs.length][];
        for(int i = 0; i < pairs.length; i++){
            res[i] = pairs[ret.get(pairs.length - i - 1)];
        }
        return res;
    }

    void euler(int u, int e, List<Integer> res){
        // System.out.println(e + ", " + u + "," + m);
        while(m.containsKey(u) && m.get(u).size() != 0){
            Queue<Integer> it = m.get(u);
            int ne = it.poll();
            int v = pairs[ne][0] + pairs[ne][1] - u;
            euler(v, ne, res);
        }
        
        if(e != -1){
            res.add(e);
            // System.out.println(res);
        }
    }
}
// @lc code=end
