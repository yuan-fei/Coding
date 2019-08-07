/*
 * @lc app=leetcode id=1081 lang=java
 *
 * [1081] Smallest Subsequence of Distinct Characters
 *
 * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/description/
 *
 * algorithms
 * Medium (43.22%)
 * Total Accepted:    4.1K
 * Total Submissions: 9.4K
 * Testcase Example:  '"cdadabcc"'
 *
 * Return the lexicographically smallest subsequence of text that contains all
 * the distinct characters of text exactly once.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "cdadabcc"
 * Output: "adbc"
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "abcd"
 * Output: "abcd"
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "ecbacba"
 * Output: "eacb"
 * 
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: "leetcode"
 * Output: "letcod"
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= text.length <= 1000
 * text consists of lowercase English letters.
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public String smallestSubsequence(String text) {
        List<Character> res = new ArrayList<>();
        boolean[] used = new boolean[26];
        int[] cnt = new int[26];
        for(int i = 0; i < text.length(); i++){
            cnt[text.charAt(i)-'a']++;
        }
        
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if(!used[c-'a']){
                while(!res.isEmpty() && c<res.get(res.size() - 1) && cnt[res.get(res.size() - 1)-'a']>0){
                    char rm = res.remove(res.size() - 1);
                    used[rm-'a']=false;
                }
                res.add(c);
            }
            cnt[c-'a']--; 
            used[c-'a']=true;
        }
        String ret = "";
        for(char c: res){
            ret+=c;
        }
        return ret;
    }
}
