/*
 * @lc app=leetcode id=893 lang=java
 *
 * [893] Groups of Special-Equivalent Strings
 *
 * https://leetcode.com/problems/groups-of-special-equivalent-strings/description/
 *
 * algorithms
 * Medium (71.12%)
 * Likes:    484
 * Dislikes: 1450
 * Total Accepted:    45K
 * Total Submissions: 63.3K
 * Testcase Example:  '["abcd","cdab","cbad","xyzz","zzxy","zzyx"]'
 *
 * You are given an array of strings of the same length words.
 * 
 * In one move, you can swap any two even indexed characters or any two odd
 * indexed characters of a string words[i].
 * 
 * Two strings words[i] and words[j] are special-equivalent if after any number
 * of moves, words[i] == words[j].
 * 
 * 
 * For example, words[i] = "zzxy" and words[j] = "xyzz" are special-equivalent
 * because we may make the moves "zzxy" -> "xzzy" -> "xyzz".
 * 
 * 
 * A group of special-equivalent strings from words is a non-empty subset of
 * words such that:
 * 
 * 
 * Every pair of strings in the group are special equivalent, and
 * The group is the largest size possible (i.e., there is not a string words[i]
 * not in the group such that words[i] is special-equivalent to every string in
 * the group).
 * 
 * 
 * Return the number of groups of special-equivalent strings from words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["abcd","cdab","cbad","xyzz","zzxy","zzyx"]
 * Output: 3
 * Explanation: 
 * One group is ["abcd", "cdab", "cbad"], since they are all pairwise special
 * equivalent, and none of the other strings is all pairwise special equivalent
 * to these.
 * The other two groups are ["xyzz", "zzxy"] and ["zzyx"].
 * Note that in particular, "zzxy" is not special equivalent to "zzyx".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["abc","acb","bac","bca","cab","cba"]
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 20
 * words[i] consist of lowercase English letters.
 * All the strings are of the same length.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numSpecialEquivGroups(String[] words) {
        return Arrays.stream(words).map(w -> encode(w)).collect(Collectors.toSet()).size();
    }

    String encode(String s){
        List<Character> odd = new ArrayList<>();
        List<Character> even = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            if(i % 2 == 0){
                even.add(s.charAt(i));
            }
            else{
                odd.add(s.charAt(i));
            }
        }
        Collections.sort(odd);
        Collections.sort(even);
        return "" + odd + even;
    }
}
// @lc code=end
