/*
 * @lc app=leetcode id=1163 lang=java
 *
 * [1163] Last Substring in Lexicographical Order
 *
 * https://leetcode.com/problems/last-substring-in-lexicographical-order/description/
 *
 * algorithms
 * Hard (26.93%)
 * Total Accepted:    2.4K
 * Total Submissions: 8.7K
 * Testcase Example:  '"abab"'
 *
 * Given a string s, return the last substring of s in lexicographical
 * order.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "abab"
 * Output: "bab"
 * Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba",
 * "bab"]. The lexicographically maximum substring is "bab".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "leetcode"
 * Output: "tcode"
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 * 
 * 
 */
class Solution {
    public String lastSubstring(String s) {
        List<Integer> candid = new ArrayList<>();
        char max = 'a'-1;
        for(int i=0; i < s.length(); i++){
            if(s.charAt(i)>max){
                candid = new ArrayList<>();    
                max = s.charAt(i);
            }
            if(s.charAt(i)>=max &&(i==0 || s.charAt(i)!=s.charAt(i-1))){
                candid.add(i);
            }
        }
        int len = 1;
        while(candid.size()>1){
            char maxNext = 'a'-1;
            List<Integer> remove = new ArrayList<>();
            for(int i = 0; i < candid.size(); i++){
                if(candid.get(i) + len < s.length() ){
                    maxNext = (char)Math.max(maxNext, s.charAt(candid.get(i) + len));
                }
            }
            for(int i = 0; i < candid.size(); i++){
                if(candid.get(i) + len >= s.length() || s.charAt(candid.get(i) + len) < maxNext || (i>0 && candid.get(i-1)+len==candid.get(i))){
                    remove.add(candid.get(i));
                }
            }
            candid.removeAll(remove);
            len++;
        }
        return s.substring(candid.get(0));
    }
}
