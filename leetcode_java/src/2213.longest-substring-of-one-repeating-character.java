/*
 * @lc app=leetcode id=2213 lang=java
 *
 * [2213] Longest Substring of One Repeating Character
 *
 * https://leetcode.com/problems/longest-substring-of-one-repeating-character/description/
 *
 * algorithms
 * Hard (21.20%)
 * Likes:    20
 * Dislikes: 33
 * Total Accepted:    653
 * Total Submissions: 3.1K
 * Testcase Example:  '"babacc"\n"bcb"\n[1,3,3]'
 *
 * You are given a 0-indexed string s. You are also given a 0-indexed string
 * queryCharacters of length k and a 0-indexed array of integer indices
 * queryIndices of length k, both of which are used to describe k queries.
 * 
 * The i^th query updates the character in s at index queryIndices[i] to the
 * character queryCharacters[i].
 * 
 * Return an array lengths of length k where lengths[i] is the length of the
 * longest substring of s consisting of only one repeating character after the
 * i^th query is performed.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
 * Output: [3,3,4]
 * Explanation: 
 * - 1^st query updates s = "bbbacc". The longest substring consisting of one
 * repeating character is "bbb" with length 3.
 * - 2^nd query updates s = "bbbccc". 
 * ⁠ The longest substring consisting of one repeating character can be "bbb"
 * or "ccc" with length 3.
 * - 3^rd query updates s = "bbbbcc". The longest substring consisting of one
 * repeating character is "bbbb" with length 4.
 * Thus, we return [3,3,4].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
 * Output: [2,3]
 * Explanation:
 * - 1^st query updates s = "abazz". The longest substring consisting of one
 * repeating character is "zz" with length 2.
 * - 2^nd query updates s = "aaazz". The longest substring consisting of one
 * repeating character is "aaa" with length 3.
 * Thus, we return [2,3].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 * k == queryCharacters.length == queryIndices.length
 * 1 <= k <= 10^5
 * queryCharacters consists of lowercase English letters.
 * 0 <= queryIndices[i] < s.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        Updater updater = new Updater(s);
        int n = queryIndices.length;
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            ans[i] = updater.update(queryIndices[i], queryCharacters.charAt(i));
        }
        return ans;
    }

    class Updater{
        char[] chars;
        public Updater(String s){
            chars = s.toCharArray();
            int left = 0;
            for(int i = 1; i < s.length(); i++){
                if(s.charAt(left) != s.charAt(i)){
                    pos2Len.put(left, i - left);
                    len2Cnt.put(i - left, len2Cnt.getOrDefault(i - left, 0) + 1);
                    left = i;
                }
            }
            pos2Len.put(left, s.length() - left);
            len2Cnt.put(s.length() - left, len2Cnt.getOrDefault(s.length() - left, 0) + 1);
            // System.out.println(len2Cnt);
        }

        TreeMap<Integer, Integer> pos2Len = new TreeMap<>();
        TreeMap<Integer, Integer> len2Cnt = new TreeMap<>();
        void inc(TreeMap<Integer, Integer> tm, int key){
            tm.put(key, tm.getOrDefault(key, 0) + 1);
        }
        void dec(TreeMap<Integer, Integer> tm, int key){
            int cntOfLen = tm.get(key) - 1;
            if(cntOfLen == 0){
                tm.remove(key);
            }
            else{
                tm.put(key, cntOfLen);
            }
        }

        public int update(int i, char c){
            int start = pos2Len.floorKey(i);
            int len = pos2Len.get(start);
            if(chars[start] != c){
                chars[i] = c;
                if(start < i){
                    split(start, i - start);
                    tryMergeBefore(start);
                }
                // System.out.println(pos2Len + " a-> " + len2Cnt);
                if(start + len - 1 > i){
                    split(i, 1);
                    tryMergeAfter(i + 1);
                }
                // System.out.println(pos2Len + " b-> " + len2Cnt);
                int left = tryMergeBefore(i);
                tryMergeAfter(left);
            }
            // System.out.println(String.valueOf(chars) + ": " + pos2Len + " -> " + len2Cnt);
            
            return (int)len2Cnt.lastKey();
        }

        void split(int start, int len){
            int totalLen = pos2Len.get(start);
            pos2Len.put(start, len);
            pos2Len.put(start + len, totalLen - len);
            dec(len2Cnt, totalLen);
            inc(len2Cnt, len);
            inc(len2Cnt, totalLen - len);
        }

        int tryMergeBefore(int start){
            if(pos2Len.lowerKey(start) != null){
                int lower = pos2Len.lowerKey(start);
                if(chars[lower] == chars[start]){
                    merge(lower, start);
                    return lower;
                }
            }
            return start;
        }

        int tryMergeAfter(int start){
            if(pos2Len.higherKey(start) != null){
                int higher = pos2Len.higherKey(start);
                if(chars[higher] == chars[start]){
                    merge(start, higher);
                    return start;
                }
            }
            return start;
        }

        void merge(int left, int right){
            int lenLeft = pos2Len.get(left);
            int lenRight = pos2Len.get(right);
            int newLen = lenLeft + lenRight;
            pos2Len.put(left, newLen);
            pos2Len.remove(right);
            inc(len2Cnt, newLen);
            dec(len2Cnt, lenLeft);
            dec(len2Cnt, lenRight);
        }
    }
}
// @lc code=end
