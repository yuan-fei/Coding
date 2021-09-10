/*
 * @lc app=leetcode id=1996 lang=java
 *
 * [1996] The Number of Weak Characters in the Game
 *
 * https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/description/
 *
 * algorithms
 * Medium (22.43%)
 * Likes:    205
 * Dislikes: 9
 * Total Accepted:    4.7K
 * Total Submissions: 21.1K
 * Testcase Example:  '[[5,5],[6,3],[3,6]]'
 *
 * You are playing a game that contains multiple characters, and each of the
 * characters has two main properties: attack and defense. You are given a 2D
 * integer array properties where properties[i] = [attacki, defensei]
 * represents the properties of the i^th character in the game.
 * 
 * A character is said to be weak if any other character has both attack and
 * defense levels strictly greater than this character's attack and defense
 * levels. More formally, a character i is said to be weak if there exists
 * another character j where attackj > attacki and defensej > defensei.
 * 
 * Return the number of weak characters.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: properties = [[5,5],[6,3],[3,6]]
 * Output: 0
 * Explanation: No character has strictly greater attack and defense than the
 * other.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: properties = [[2,2],[3,3]]
 * Output: 1
 * Explanation: The first character is weak because the second character has a
 * strictly greater attack and defense.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: properties = [[1,5],[10,4],[4,3]]
 * Output: 1
 * Explanation: The third character is weak because the second character has a
 * strictly greater attack and defense.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= properties.length <= 10^5
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        List<Integer>[] a = new List[100005];
        for(int i = 0; i < a.length; i++){
            a[i] = new ArrayList<>();
        }
        for(int[] p : properties){
            a[p[0]].add(p[1]);
        }
        for(int i = 0; i < a.length; i++){
            Collections.sort(a[i]);
        }
        int cnt = 0;
        TreeSet<Integer> ts = new TreeSet<>();
        for(int i = a.length - 1; i >= 0; i--){
            if(!a[i].isEmpty()){
                for(int x : a[i]){
                    Integer higher = ts.higher(x);
                    if(higher != null){
                        cnt++;
                    }
                }
                ts.add(a[i].get(a[i].size() - 1));
            }
        }
        return cnt;
    }
}
// @lc code=end
