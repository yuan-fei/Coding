/*
 * @lc app=leetcode id=1750 lang=java
 *
 * [1750] Minimum Length of String After Deleting Similar Ends
 *
 * https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/description/
 *
 * algorithms
 * Medium (41.13%)
 * Likes:    79
 * Dislikes: 8
 * Total Accepted:    5.4K
 * Total Submissions: 13K
 * Testcase Example:  '"ca"'
 *
 * Given a string s consisting only of characters 'a', 'b', and 'c'. You are
 * asked to apply the following algorithm on the string any number of
 * times:
 * 
 * 
 * Pick a non-empty prefix from the string s where all the characters in the
 * prefix are equal.
 * Pick a non-empty suffix from the string s where all the characters in this
 * suffix are equal.
 * The prefix and the suffix should not intersect at any index.
 * The characters from the prefix and suffix must be the same.
 * Delete both the prefix and the suffix.
 * 
 * 
 * Return the minimum length of s after performing the above operation any
 * number of times (possibly zero times).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "ca"
 * Output: 2
 * Explanation: You can't remove any characters, so the string stays as is.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "cabaabac"
 * Output: 0
 * Explanation: An optimal sequence of operations is:
 * - Take prefix = "c" and suffix = "c" and remove them, s = "abaaba".
 * - Take prefix = "a" and suffix = "a" and remove them, s = "baab".
 * - Take prefix = "b" and suffix = "b" and remove them, s = "aa".
 * - Take prefix = "a" and suffix = "a" and remove them, s = "".
 * 
 * Example 3:
 * 
 * 
 * Input: s = "aabccabba"
 * Output: 3
 * Explanation: An optimal sequence of operations is:
 * - Take prefix = "aa" and suffix = "a" and remove them, s = "bccabb".
 * - Take prefix = "b" and suffix = "bb" and remove them, s = "cca".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s only consists of characters 'a', 'b', and 'c'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumLength(String s) {
    	List<Integer> l = new ArrayList<>();
    	l.add(0);
        for(int i = 1; i < s.length(); i++){
        	if(s.charAt(i) != s.charAt(i - 1)){
        		l.add(i);
        	}
        }
        int i = 0;
        int j = l.size() - 1;
        while(i < j){
        	if(s.charAt(l.get(i)) == s.charAt(l.get(j))){
        		i++;
        		j--;
        	}
        	else{
        		break;
        	}
        }
        if(i == j){
        	if(i + 1 >= l.size()){
        		j = s.length();
        	}
        	else{
        		j = l.get(i + 1);
        	}
        	if(j - l.get(i) > 1){
        		return 0;
        	}
        	else{
        		return 1;
        	}
        }
        else{
        	if(j + 1 >= l.size()){
        		j = s.length();
        	}
        	else{
        		j = l.get(j + 1);
        	}
        }
        return j - l.get(i);
    }
}
// @lc code=end
