/*
 * @lc app=leetcode id=2781 lang=java
 *
 * [2781] Length of the Longest Valid Substring
 *
 * https://leetcode.com/problems/length-of-the-longest-valid-substring/description/
 *
 * algorithms
 * Hard (34.85%)
 * Likes:    495
 * Dislikes: 17
 * Total Accepted:    18.1K
 * Total Submissions: 50.8K
 * Testcase Example:  '"cbaaaabc"\n["aaa","cb"]'
 *
 * You are given a string word and an array of strings forbidden.
 * 
 * A string is called valid if none of its substrings are present in
 * forbidden.
 * 
 * Return the length of the longest valid substring of the string word.
 * 
 * A substring is a contiguous sequence of characters in a string, possibly
 * empty.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "cbaaaabc", forbidden = ["aaa","cb"]
 * Output: 4
 * Explanation: There are 11 valid substrings in word: "c", "b", "a", "ba",
 * "aa", "bc", "baa", "aab", "ab", "abc" and "aabc". The length of the longest
 * valid substring is 4. 
 * It can be shown that all other substrings contain either "aaa" or "cb" as a
 * substring. 
 * 
 * Example 2:
 * 
 * 
 * Input: word = "leetcode", forbidden = ["de","le","e"]
 * Output: 4
 * Explanation: There are 11 valid substrings in word: "l", "t", "c", "o", "d",
 * "tc", "co", "od", "tco", "cod", and "tcod". The length of the longest valid
 * substring is 4.
 * It can be shown that all other substrings contain either "de", "le", or "e"
 * as a substring. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word.length <= 10^5
 * word consists only of lowercase English letters.
 * 1 <= forbidden.length <= 10^5
 * 1 <= forbidden[i].length <= 10
 * forbidden[i] consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestValidSubstring(String word, List<String> forbidden) {
        Trie root = new Trie();
        for(String s : forbidden){
            add(root, s);
        }
        List<int[]> illegalRanges = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            int len = match(root, word, i);
            if(len > 0){
                int end = i + len - 1;
                while(!illegalRanges.isEmpty() && illegalRanges.get(illegalRanges.size() - 1)[1] > end){
                    illegalRanges.remove(illegalRanges.size() - 1);
                }
                illegalRanges.add(new int[]{i, end});
                // System.out.println(Arrays.toString(illegalRanges.get(illegalRanges.size() - 1)));
            }
        }
        int l = 0;
        int ans = 0;

        for(int[] r : illegalRanges){
            ans = Math.max(ans, r[1] - l);
            l = r[0] + 1;
        }
        ans = Math.max(ans, word.length() - l);
        return ans;
    }

    class Trie {
        Trie[] children = new Trie[26];
        boolean isWord = false;
    }

    void add(Trie root, String s){
        Trie cur = root;
        for(int i = 0; i < s.length(); i++){
            if(cur.children[s.charAt(i) - 'a'] == null){
                cur.children[s.charAt(i) - 'a'] = new Trie();
            }
            cur = cur.children[s.charAt(i) - 'a'];
        }
        cur.isWord = true;
    }

    int match(Trie root ,String s, int start){
        Trie cur = root;
        for(int i = start; i < s.length(); i++){
            if(cur.children[s.charAt(i) - 'a'] == null){
                break;
            }
            cur = cur.children[s.charAt(i) - 'a'];
            if(cur.isWord){
                return i + 1 - start;
            }
        }
        // System.out.println(start + ", " + maxLength);
        return 0;
    }
}
// @lc code=end
