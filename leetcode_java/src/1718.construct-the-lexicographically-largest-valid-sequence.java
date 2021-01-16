/*
 * @lc app=leetcode id=1718 lang=java
 *
 * [1718] Construct the Lexicographically Largest Valid Sequence
 *
 * https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/description/
 *
 * algorithms
 * Medium (41.77%)
 * Likes:    114
 * Dislikes: 8
 * Total Accepted:    2.6K
 * Total Submissions: 6.3K
 * Testcase Example:  '3'
 *
 * Given an integer n, find a sequence that satisfies all of the
 * following:
 * 
 * 
 * The integer 1 occurs once in the sequence.
 * Each integer between 2 and n occurs twice in the sequence.
 * For every integer i between 2 and n, the distance between the two
 * occurrences of i is exactly i.
 * 
 * 
 * The distance between two numbers on the sequence, a[i] and a[j], is the
 * absolute difference of their indices, |j - i|.
 * 
 * Return the lexicographically largest sequence. It is guaranteed that under
 * the given constraints, there is always a solution. 
 * 
 * A sequence a is lexicographically larger than a sequence b (of the same
 * length) if in the first position where a and b differ, sequence a has a
 * number greater than the corresponding number in b. For example, [0,1,9,0] is
 * lexicographically larger than [0,1,5,6] because the first position they
 * differ is at the third number, and 9 is greater than 5.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3
 * Output: [3,1,2,3,2]
 * Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the
 * lexicographically largest valid sequence.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5
 * Output: [5,3,1,4,3,5,2,4,2]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 20
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] constructDistancedSequence(int n) {
        int[] ans = new int[2 * n - 1];
        rec(n, ans, 0, new HashSet<>());
        return ans;
    }

    boolean rec(int n, int[] a, int pos, Set<Integer> used){
    	if(n == pos){
    		return true;
    	}
    	int cur = 0;
    	for(; cur < a.length; cur++){
			if(a[cur] == 0){
				break;
			}
		}
    	for(int x = n; x >= 1; x--){
    		if(!used.contains(x)){
    			if(x == 1){
    				a[cur] = x;
    				used.add(x);
    				if(rec(n, a, pos + 1, used)){
    					return true;
    				}
    				used.remove(x);
    				a[cur] = 0;
    			}
    			else if(cur + x < a.length && a[cur + x] == 0){
    				a[cur] = a[cur + x] = x;
    				used.add(x);
    				if(rec(n, a, pos + 1, used)){
    					return true;
    				}
    				used.remove(x);
    				a[cur] = a[cur + x] = 0;
    			}
    		}
    	}
    	return false;
    }
}
// @lc code=end
