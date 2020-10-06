/*
 * @lc app=leetcode id=556 lang=java
 *
 * [556] Next Greater Element III
 *
 * https://leetcode.com/problems/next-greater-element-iii/description/
 *
 * algorithms
 * Medium (31.84%)
 * Likes:    766
 * Dislikes: 211
 * Total Accepted:    45.9K
 * Total Submissions: 144K
 * Testcase Example:  '12'
 *
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit
 * integer which has exactly the same digits existing in the integer n and is
 * greater in value than n. If no such positive 32-bit integer exists, you need
 * to return -1.
 * 
 * Example 1:
 * 
 * 
 * Input: 12
 * Output: 21
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 21
 * Output: -1
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int nextGreaterElement(int n) {
        char[] chars = ("" + n).toCharArray();
        int i = chars.length - 2;
        for(; i >= 0; i--){
        	if(chars[i] < chars[i + 1]){
        		break;
        	}
        }
        if(i < 0){
        	return -1;
        }
        int j = chars.length - 1;
        for(; j >= 0; j--){
        	if(chars[j] > chars[i]){
        		break;
        	}
        }
        swap(chars, i, j);
        Arrays.sort(chars, i + 1, chars.length);
        long m = Long.parseLong(String.valueOf(chars));
        if(m > Integer.MAX_VALUE){
        	return -1;
        }
        return (int)m;
    }
	
	void swap(char[] a, int i, int j) {
		char t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
// @lc code=end
