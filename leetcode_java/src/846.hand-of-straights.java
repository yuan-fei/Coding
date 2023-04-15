/*
 * @lc app=leetcode id=846 lang=java
 *
 * [846] Hand of Straights
 *
 * https://leetcode.com/problems/hand-of-straights/description/
 *
 * algorithms
 * Medium (56.17%)
 * Likes:    1948
 * Dislikes: 142
 * Total Accepted:    115.7K
 * Total Submissions: 206.1K
 * Testcase Example:  '[1,2,3,6,2,3,4,7,8]\n3'
 *
 * Alice has some number of cards and she wants to rearrange the cards into
 * groups so that each group is of size groupSize, and consists of groupSize
 * consecutive cards.
 * 
 * Given an integer array hand where hand[i] is the value written on the i^th
 * card and an integer groupSize, return true if she can rearrange the cards,
 * or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: hand = [1,2,3,4,5], groupSize = 4
 * Output: false
 * Explanation: Alice's hand can not be rearranged into groups of 4.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= hand.length <= 10^4
 * 0 <= hand[i] <= 10^9
 * 1 <= groupSize <= hand.length
 * 
 * 
 * 
 * Note: This question is the same as 1296:
 * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 * 
 */

// @lc code=start
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int x : hand){
            tm.putIfAbsent(x, 0);
            tm.put(x, tm.get(x) + 1);
        }
        int n = hand.length;
        while(n > 0){
            int k = tm.firstKey();
            for(int i = 0; i < groupSize; i++){
                if(!tm.containsKey(k + i)){
                    return false;
                }
                int c = tm.get(k + i) - 1;
                if(c == 0){
                    tm.remove(k + i);
                }
                else{
                    tm.put(k + i, c);
                }
                n--;    
            }
        }
        return true;
    }
}
// @lc code=end
