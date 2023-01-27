/*
 * @lc app=leetcode id=809 lang=java
 *
 * [809] Expressive Words
 *
 * https://leetcode.com/problems/expressive-words/description/
 *
 * algorithms
 * Medium (46.22%)
 * Likes:    779
 * Dislikes: 1794
 * Total Accepted:    105.8K
 * Total Submissions: 228.8K
 * Testcase Example:  '"heeellooo"\n["hello", "hi", "helo"]'
 *
 * Sometimes people repeat letters to represent extra feeling. For
 * example:
 * 
 * 
 * "hello" -> "heeellooo"
 * "hi" -> "hiiii"
 * 
 * 
 * In these strings like "heeellooo", we have groups of adjacent letters that
 * are all the same: "h", "eee", "ll", "ooo".
 * 
 * You are given a string s and an array of query strings words. A query word
 * is stretchy if it can be made to be equal to s by any number of applications
 * of the following extension operation: choose a group consisting of
 * characters c, and add some number of characters c to the group so that the
 * size of the group is three or more.
 * 
 * 
 * For example, starting with "hello", we could do an extension on the group
 * "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has a
 * size less than three. Also, we could do another extension like "ll" ->
 * "lllll" to get "helllllooo". If s = "helllllooo", then the query word
 * "hello" would be stretchy because of these two extension operations: query =
 * "hello" -> "hellooo" -> "helllllooo" = s.
 * 
 * 
 * Return the number of query strings that are stretchy.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "heeellooo", words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation: 
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size
 * 3 or more.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "zzzzzyyyyy", words = ["zzyy","zy","zyy"]
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length, words.length <= 100
 * 1 <= words[i].length <= 100
 * s and words[i] consist of lowercase letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int expressiveWords(String s, String[] words) {
        List<int[]> expS = convert(s);
        int ans = 0;
        for(String w : words){
            List<int[]> expW = convert(w);
            if(expS.size() == expW.size()){
                boolean good = true;
                for(int i = 0; i < expS.size(); i++){
                    if(expS.get(i)[0] != expW.get(i)[0] || expS.get(i)[1] < expW.get(i)[1] || (expS.get(i)[1] != expW.get(i)[1] && expS.get(i)[1] < 3)){
                        good = false;
                        break;
                    }
                }
                if(good){
                    ans++;
                }
            }
        }
        return ans;
    }

    List<int[]> convert(String s){
        List<int[]> ret = new ArrayList<>();
        int left = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(left) != s.charAt(i)){
                ret.add(new int[]{s.charAt(left) - 'a', i - left});
                left = i;
            }
        }
        ret.add(new int[]{s.charAt(left) - 'a', s.length() - left});
        return ret;
    }
}
// @lc code=end
