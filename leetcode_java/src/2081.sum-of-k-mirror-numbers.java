/*
 * @lc app=leetcode id=2081 lang=java
 *
 * [2081] Sum of k-Mirror Numbers
 *
 * https://leetcode.com/problems/sum-of-k-mirror-numbers/description/
 *
 * algorithms
 * Hard (29.91%)
 * Likes:    27
 * Dislikes: 49
 * Total Accepted:    1.5K
 * Total Submissions: 5K
 * Testcase Example:  '2\n5'
 *
 * A k-mirror number is a positive integer without leading zeros that reads the
 * same both forward and backward in base-10 as well as in base-k.
 * 
 * 
 * For example, 9 is a 2-mirror number. The representation of 9 in base-10 and
 * base-2 are 9 and 1001 respectively, which read the same both forward and
 * backward.
 * On the contrary, 4 is not a 2-mirror number. The representation of 4 in
 * base-2 is 100, which does not read the same both forward and backward.
 * 
 * 
 * Given the base k and the number n, return the sum of the n smallest k-mirror
 * numbers.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: k = 2, n = 5
 * Output: 25
 * Explanation:
 * The 5 smallest 2-mirror numbers and their representations in base-2 are
 * listed as follows:
 * ⁠ base-10    base-2
 * ⁠   1          1
 * ⁠   3          11
 * ⁠   5          101
 * ⁠   7          111
 * ⁠   9          1001
 * Their sum = 1 + 3 + 5 + 7 + 9 = 25. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: k = 3, n = 7
 * Output: 499
 * Explanation:
 * The 7 smallest 3-mirror numbers are and their representations in base-3 are
 * listed as follows:
 * ⁠ base-10    base-3
 * ⁠   1          1
 * ⁠   2          2
 * ⁠   4          11
 * ⁠   8          22
 * ⁠   121        11111
 * ⁠   151        12121
 * ⁠   212        21212
 * Their sum = 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: k = 7, n = 17
 * Output: 20379000
 * Explanation: The 17 smallest 7-mirror numbers are:
 * 1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064,
 * 6597956, 6958596
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= k <= 9
 * 1 <= n <= 30
 * 
 * 
 */

// @lc code=start
class Solution {
    public long kMirror(int k, int n) {
        long res = 0;
        
        int j = 1;
        while(n != 0 && j < k){
            res += j++;
            n--;
        }
        String[] state = {"0", ""};
        while(n != 0){
            state = generateNext(k, state);

            StringBuilder sb = new StringBuilder();
            sb.append(state[0]);
            sb.reverse();
            sb.insert(0, state[0] + state[1]);
            String sBaseK = sb.toString();
            // System.out.println(Arrays.asList(state[0], state[1]));
            String sBase10 = getString(sBaseK, k, 10);
            if(isPalindrome(sBase10)){
                res += Long.parseLong(sBase10);
                n--;
                // System.out.println(Arrays.asList(sBaseK, res));
            }
            
        }
        return res;
    }

    String getString(String s, int baseFrom, int baseTo){
        return Long.toString(Long.parseLong(s, baseFrom), baseTo);
    }

    String[] generateNext(int base, String[] state){
        String[] ret = Arrays.copyOf(state, 2);
        if(!state[1].equals("")){
            ret[1] = inc(state[1], base);
            if(ret[1].length() > state[1].length()){
                ret[1] = "0";
                ret[0] = inc(state[0], base);
                if(ret[0].length() > state[0].length()){
                    ret[1] = "";
                }    
            }
        }
        else{
            ret[0] = inc(state[0], base);
            if(ret[0].length() > state[0].length()){
                ret[0] = ret[0].substring(0, ret[0].length() - 1);
                ret[1] = "0";
            }    
        }
        return ret;
    }

    String inc(String n, int base){
        return Long.toString(Long.parseLong(n, base) + 1, base);
    }

    boolean isPalindrome(String s){
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            if(s.charAt(i++) != s.charAt(j--)){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
