/*
 * @lc app=leetcode id=914 lang=java
 *
 * [914] X of a Kind in a Deck of Cards
 *
 * https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/description/
 *
 * algorithms
 * Easy (30.95%)
 * Likes:    1642
 * Dislikes: 429
 * Total Accepted:    106K
 * Total Submissions: 343.3K
 * Testcase Example:  '[1,2,3,4,4,3,2,1]'
 *
 * You are given an integer array deck where deck[i] represents the number
 * written on the i^th card.
 * 
 * Partition the cards into one or more groups such that:
 * 
 * 
 * Each group has exactly x cards where x > 1, and
 * All the cards in one group have the same integer written on them.
 * 
 * 
 * Return true if such partition is possible, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: deck = [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: deck = [1,1,1,2,2,2,3,3]
 * Output: false
 * Explanation: No possible partition.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= deck.length <= 10^4
 * 0 <= deck[i] < 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        return Arrays.stream(deck).boxed()
            .collect(Collectors.toMap(i -> i, i -> 1, (v1, v2) -> v1 + v2)).values().stream()
            .reduce(this::gcd).get() > 1;
    }

    int gcd(int a, int b){
        return (b == 0) ? a : gcd(b, a % b);
    }
}
// @lc code=end
