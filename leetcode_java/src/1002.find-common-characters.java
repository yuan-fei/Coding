/*
 * @lc app=leetcode id=1002 lang=java
 *
 * [1002] Find Common Characters
 *
 * https://leetcode.com/problems/find-common-characters/description/
 *
 * algorithms
 * Easy (68.69%)
 * Likes:    3385
 * Dislikes: 276
 * Total Accepted:    197.9K
 * Total Submissions: 287.9K
 * Testcase Example:  '["bella","label","roller"]'
 *
 * Given a string array words, return an array of all characters that show up
 * in all strings within the words (including duplicates). You may return the
 * answer in any order.
 * 
 * 
 * Example 1:
 * Input: words = ["bella","label","roller"]
 * Output: ["e","l","l"]
 * Example 2:
 * Input: words = ["cool","lock","cook"]
 * Output: ["c","o"]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = 1000;
    public List<String> commonChars(String[] words) {
        int[] freq = new int[26];
        Arrays.fill(freq, MAX);
        for(String w : words){
            int[] freqW = new int[26];
            for(int i = 0; i < w.length(); i++){
                freqW[w.charAt(i) - 'a']++;
            }
            for(int i = 0; i < freq.length; i++){
                freq[i] = Math.min(freq[i], freqW[i]);
            }
        }
        // System.out.println(Arrays.toString(freq));
        List<String> res = new ArrayList<>();
        for(int i = 0 ; i < freq.length; i++){
            if(freq[i] != MAX){
                for(int j = 0; j < freq[i]; j++){
                    res.add("" + (char)('a' + i));
                }
            }
        }
        return res;
    }
}
// @lc code=end
