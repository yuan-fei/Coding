/*
 * @lc app=leetcode id=1371 lang=java
 *
 * [1371] Find the Longest Substring Containing Vowels in Even Counts
 *
 * https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/description/
 *
 * algorithms
 * Medium (42.13%)
 * Likes:    72
 * Dislikes: 2
 * Total Accepted:    1.3K
 * Total Submissions: 3K
 * Testcase Example:  '"eleetminicoworoep"'
 *
 * Given the string s, return the size of the longest substring containing each
 * vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must
 * appear an even number of times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "eleetminicoworoep"
 * Output: 13
 * Explanation: The longest substring is "leetminicowor" which contains two
 * each of the vowels: e, i and o and zero of the vowels: a and u.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "leetcodeisgreat"
 * Output: 5
 * Explanation: The longest substring is "leetc" which contains two e's.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "bcbcbc"
 * Output: 6
 * Explanation: In this case, the given string "bcbcbc" is the longest because
 * all vowels: a, e, i, o and u appear zero times.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 5 x 10^5
 * s contains only lowercase English letters.
 * 
 */

// @lc code=start
class Solution {
    public int findTheLongestSubstring(String s) {
        int[] minIdxByState = new int[1 << 5];
        Arrays.fill(minIdxByState, -1);
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 0);
        map.put('e', 1);
        map.put('i', 2);
        map.put('o', 3);
        map.put('u', 4);
        int cur = 0;
        int max = 0;
        for(int i = 0; i < s.length(); i++){
        	if(map.containsKey(s.charAt(i))){
        		cur ^= (1 << map.get(s.charAt(i)));
        		if(minIdxByState[cur] < 0){
        			minIdxByState[cur] = i;
        		}
        	}
        	max = Math.max(max, (cur == 0) ? i + 1 : i - minIdxByState[cur]);
        }
        return max;
    }
}
// @lc code=end
