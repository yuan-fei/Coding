/*
 * @lc app=leetcode id=1733 lang=java
 *
 * [1733] Minimum Number of People to Teach
 *
 * https://leetcode.com/problems/minimum-number-of-people-to-teach/description/
 *
 * algorithms
 * Medium (34.71%)
 * Likes:    52
 * Dislikes: 177
 * Total Accepted:    2.7K
 * Total Submissions: 7.8K
 * Testcase Example:  '2\n[[1],[2],[1,2]]\n[[1,2],[1,3],[2,3]]'
 *
 * On a social network consisting of m users and some friendships between
 * users, two users can communicate with each other if they know a common
 * language.
 * 
 * You are given an integer n, an array languages, and an array friendships
 * where:
 * 
 * 
 * There are n languages numbered 1 through n,
 * languages[i] is the set of languages the i^​​​​​​th​​​​ user knows, and
 * friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the
 * users u^​​​​​​​​​​​i​​​​​ and vi.
 * 
 * 
 * You can choose one language and teach it to some users so that all friends
 * can communicate with each other. Return the minimum number of users you need
 * to teach.
 * Note that friendships are not transitive, meaning if x is a friend of y and
 * y is a friend of z, this doesn't guarantee that x is a friend of z.
 * 
 * Example 1:
 * 
 * 
 * Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
 * Output: 1
 * Explanation: You can either teach user 1 the second language or user 2 the
 * first language.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships =
 * [[1,4],[1,2],[3,4],[2,3]]
 * Output: 2
 * Explanation: Teach the third language to users 1 and 3, yielding two users
 * to teach.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 500
 * languages.length == m
 * 1 <= m <= 500
 * 1 <= languages[i].length <= n
 * 1 <= languages[i][j] <= n
 * 1 <= u​​​​​​i < v​​​​​​i <= languages.length
 * 1 <= friendships.length <= 500
 * All tuples (u​​​​​i, v​​​​​​i) are unique
 * languages[i] contains only unique values
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Map<Integer, Set<Integer>> languagesMap = new HashMap<>();
        for(int i = 0; i < languages.length; i++) {
            languagesMap.put(i + 1, new HashSet<>());
            for(int l : languages[i]) {
                languagesMap.get(i + 1).add(l);
            }
        }
        boolean[] alreadyCan = new boolean[friendships.length];
        for(int j = 1; j <= n; j++) {
            for(int i = 0; i < friendships.length; i++) {
                if(languagesMap.get(friendships[i][0]).contains(j) && languagesMap.get(friendships[i][1]).contains(j)) {
                    alreadyCan[i] = true;
                }
            }            
        }
        int minTeach = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            Set<Integer> teachSet = new HashSet<>();
            for(int j = 0; j < friendships.length; j++) {
                if(alreadyCan[j]) continue;
                if(!languagesMap.get(friendships[j][0]).contains(i)) teachSet.add(friendships[j][0]);
                if(!languagesMap.get(friendships[j][1]).contains(i)) teachSet.add(friendships[j][1]);
            }
            minTeach = Math.min(teachSet.size(), minTeach);
        }
        return minTeach;
    }
}
// @lc code=end
