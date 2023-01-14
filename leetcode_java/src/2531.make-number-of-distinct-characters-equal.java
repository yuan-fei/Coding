/*
 * @lc app=leetcode id=2531 lang=java
 *
 * [2531] Make Number of Distinct Characters Equal
 *
 * https://leetcode.com/problems/make-number-of-distinct-characters-equal/description/
 *
 * algorithms
 * Medium (20.85%)
 * Likes:    141
 * Dislikes: 73
 * Total Accepted:    6.7K
 * Total Submissions: 32.1K
 * Testcase Example:  '"ac"\n"b"'
 *
 * You are given two 0-indexed strings word1 and word2.
 * 
 * A move consists of choosing two indices i and j such that 0 <= i <
 * word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].
 * 
 * Return true if it is possible to get the number of distinct characters in
 * word1 and word2 to be equal with exactly one move. Return false
 * otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word1 = "ac", word2 = "b"
 * Output: false
 * Explanation: Any pair of swaps would yield two distinct characters in the
 * first string, and one in the second string.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word1 = "abcc", word2 = "aab"
 * Output: true
 * Explanation: We swap index 2 of the first string with index 0 of the second
 * string. The resulting strings are word1 = "abac" and word2 = "cab", which
 * both have 3 distinct characters.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: word1 = "abcde", word2 = "fghij"
 * Output: true
 * Explanation: Both resulting strings will have 5 distinct characters,
 * regardless of which indices we swap.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word1.length, word2.length <= 10^5
 * word1 and word2 consist of only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isItPossible(String word1, String word2) {
        int[] cnt1 = getCntMap(word1);
        int[] cnt2 = getCntMap(word2);
        return isItPossible(cnt1, cnt2);
    }

    boolean isItPossible(int[] cnt1, int[] cnt2){
        // System.out.println(Arrays.toString(cnt1) + Arrays.toString(cnt2));
        if(diff(cnt1, cnt2) < 0){
            return isItPossible(cnt2, cnt1);
        }
        for(int i = 0; i < 26; i++){
            if(cnt1[i] != 0){
                for(int j = 0; j < 26; j++){
                    if(cnt2[j] != 0){
                        swap(cnt1, i, cnt2, j);
                        if(diff(cnt1, cnt2) == 0){
                            return true;
                        }
                        swap(cnt2, i, cnt1, j);
                    }
                }    
            }
        }
        return false;
    }

    int diff(int[] cnt1, int[] cnt2){
        int c1 = 0;
        int c2 = 0;
        for(int i = 0; i < 26; i++){
            if(cnt1[i] != 0){
                c1++;
            }
            if(cnt2[i] != 0){
                c2++;
            }
        }
        return c1 - c2;
    }

    int[] getCntMap(String w){
        int[] cnt = new int[26];
        for(char c : w.toCharArray()){
            cnt[c - 'a']++;
        }
        return cnt;
    }

    void swap(int[] m1, int k1, int[] m2, int k2){
        m1[k1]--;
        m2[k1]++;
        m2[k2]--;
        m1[k2]++;
    }
}
// @lc code=end
