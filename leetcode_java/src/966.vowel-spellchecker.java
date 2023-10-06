/*
 * @lc app=leetcode id=966 lang=java
 *
 * [966] Vowel Spellchecker
 *
 * https://leetcode.com/problems/vowel-spellchecker/description/
 *
 * algorithms
 * Medium (51.30%)
 * Likes:    401
 * Dislikes: 797
 * Total Accepted:    39.4K
 * Total Submissions: 76.8K
 * Testcase Example:  '["KiTe","kite","hare","Hare"]\n' +
  '["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]'
 *
 * Given a wordlist, we want to implement a spellchecker that converts a query
 * word into a correct word.
 * 
 * For a given query word, the spell checker handles two categories of spelling
 * mistakes:
 * 
 * 
 * Capitalization: If the query matches a word in the wordlist
 * (case-insensitive), then the query word is returned with the same case as
 * the case in the wordlist.
 * 
 * 
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct =
 * "yellow"
 * 
 * 
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the
 * query word with any vowel individually, it matches a word in the wordlist
 * (case-insensitive), then the query word is returned with the same case as
 * the match in the wordlist.
 * 
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no
 * match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no
 * match)
 * 
 * 
 * 
 * 
 * In addition, the spell checker operates under the following precedence
 * rules:
 * 
 * 
 * When the query exactly matches a word in the wordlist (case-sensitive), you
 * should return the same word back.
 * When the query matches a word up to capitlization, you should return the
 * first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the
 * first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty
 * string.
 * 
 * 
 * Given some queries, return a list of words answer, where answer[i] is the
 * correct word for query = queries[i].
 * 
 * 
 * Example 1:
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries =
 * ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 * Example 2:
 * Input: wordlist = ["yellow"], queries = ["YellOw"]
 * Output: ["yellow"]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= wordlist.length, queries.length <= 5000
 * 1 <= wordlist[i].length, queries[i].length <= 7
 * wordlist[i] and queries[i] consist only of only English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Map<String, Integer> exactMatch = buildExactMatchMap(wordlist);
        Map<String, Integer> caseInsensitiveMatch = buildCaseInsensitiveMatchMap(wordlist);
        Map<String, Integer> ignoreVowelMatch = buildIgnoreVowelMatchMap(wordlist);

        // System.out.println(Arrays.asList(exactMatch, caseInsensitiveMatch, ignoreVowelMatch));
        String[] ans = new String[queries.length];
        for(int i = 0; i < queries.length; i++){
            String ignoreVowel = repalceVowel(queries[i].toUpperCase());
            ans[i] = "";
            if(exactMatch.containsKey(queries[i])){
                ans[i] = wordlist[exactMatch.get(queries[i])];
            }
            else if(caseInsensitiveMatch.containsKey(queries[i].toUpperCase())){
                ans[i] = wordlist[caseInsensitiveMatch.get(queries[i].toUpperCase())];
            }
            else if(ignoreVowelMatch.containsKey(ignoreVowel)){
                ans[i] = wordlist[ignoreVowelMatch.get(ignoreVowel)];
            }
        }
        return ans;
    }

    Map<String, Integer> buildExactMatchMap(String[] wordlist){
        Map<String, Integer> m = new HashMap<>();
        for(int i = wordlist.length - 1; i >= 0; i--){
            m.put(wordlist[i], i);
        }
        return m;
    }

    Map<String, Integer> buildCaseInsensitiveMatchMap(String[] wordlist){
        Map<String, Integer> m = new HashMap<>();
        for(int i = wordlist.length - 1; i >= 0; i--){
            m.put(wordlist[i].toUpperCase(), i);
        }
        return m;
    }

    Map<String, Integer> buildIgnoreVowelMatchMap(String[] wordlist){
        Map<String, Integer> m = new HashMap<>();
        for(int i = wordlist.length - 1; i >= 0; i--){
            m.put(repalceVowel(wordlist[i].toUpperCase()), i);
        }
        return m;
    }

    String repalceVowel(String s){
        return s.replaceAll("[AEIOU]", "_");
    }
}
// @lc code=end
