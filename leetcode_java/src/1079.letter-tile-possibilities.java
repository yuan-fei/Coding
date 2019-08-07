/*
 * @lc app=leetcode id=1079 lang=java
 *
 * [1079] Letter Tile Possibilities
 *
 * https://leetcode.com/problems/letter-tile-possibilities/description/
 *
 * algorithms
 * Medium (75.60%)
 * Total Accepted:    9.1K
 * Total Submissions: 12K
 * Testcase Example:  '"AAB"'
 *
 * You have a set of tiles, where each tile has one letter tiles[i] printed on
 * it.  Return the number of possible non-empty sequences of letters you can
 * make.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "AAB"
 * Output: 8
 * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB",
 * "ABA", "BAA".
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "AAABBC"
 * Output: 188
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= tiles.length <= 7
 * tiles consists of uppercase English letters.
 * 
 */
class Solution {
    public int numTilePossibilities(String tiles) {
        Set<String> set = new HashSet<>();
        helper(tiles.toCharArray(), 0, set);
        return set.size();
    }
    
    private void helper(char[] chars, int pos, Set<String> set){
        if(pos>0){
            set.add(new String(Arrays.copyOf(chars, pos)));    
        }
        if(pos >= chars.length){
            return;    
        }
        for(int i = pos; i< chars.length; i++){
            swap(chars, i, pos);
            helper(chars, pos+1, set);
            swap(chars, i, pos);
        }
    }
    
    private void swap(char[] chars, int i, int j){
        char t = chars[i];
        chars[i]=chars[j];
        chars[j] = t;
    }
}
