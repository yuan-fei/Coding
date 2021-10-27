/*
 * @lc app=leetcode id=2048 lang=java
 *
 * [2048] Next Greater Numerically Balanced Number
 *
 * https://leetcode.com/problems/next-greater-numerically-balanced-number/description/
 *
 * algorithms
 * Medium (43.36%)
 * Likes:    53
 * Dislikes: 165
 * Total Accepted:    5.4K
 * Total Submissions: 12.5K
 * Testcase Example:  '1'
 *
 * An integer x is numerically balanced if for every digit d in the number x,
 * there are exactly d occurrences of that digit in x.
 * 
 * Given an integer n, return the smallest numerically balanced number strictly
 * greater than n.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1
 * Output: 22
 * Explanation: 
 * 22 is numerically balanced since:
 * - The digit 2 occurs 2 times. 
 * It is also the smallest numerically balanced number strictly greater than
 * 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 1000
 * Output: 1333
 * Explanation: 
 * 1333 is numerically balanced since:
 * - The digit 1 occurs 1 time.
 * - The digit 3 occurs 3 times. 
 * It is also the smallest numerically balanced number strictly greater than
 * 1000.
 * Note that 1022 cannot be the answer because 0 appeared more than 0 times.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 3000
 * Output: 3133
 * Explanation: 
 * 3133 is numerically balanced since:
 * - The digit 1 occurs 1 time.
 * - The digit 3 occurs 3 times.
 * It is also the smallest numerically balanced number strictly greater than
 * 3000.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= n <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int nextBeautifulNumber(int n) {
        int[] nums = {1,22,122,333, 1333,4444,22333,55555,14444,122333,224444,155555,666666};
        List<Integer> res = new ArrayList<>();
        for(int x : nums){
            getPermutation(("" + x).toCharArray(), 0, res);
        }
        res.add(1224444);
        Collections.sort(res);
        // System.out.println(res);
        int id = Collections.binarySearch(res, n + 1);
        if(id < 0){
            id = -id - 1;
        }
        return res.get(id);
    }

    void getPermutation(char[] a, int pos, List<Integer> res){
        if(pos == a.length){
            res.add(Integer.parseInt(String.valueOf(a)));
        }
        for(int i = pos; i < a.length; i++){
            if(i > pos && a[i - 1] == a[i]){
                continue;
            }
            else{
                swap(a, i, pos);
                getPermutation(a, pos + 1, res);
                swap(a, i, pos);
            }
        }
    }

    void swap(char[] a, int i, int j){
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
// @lc code=end
