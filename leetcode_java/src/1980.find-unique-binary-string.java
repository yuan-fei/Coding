/*
 * @lc app=leetcode id=1980 lang=java
 *
 * [1980] Find Unique Binary String
 *
 * https://leetcode.com/problems/find-unique-binary-string/description/
 *
 * algorithms
 * Medium (56.97%)
 * Likes:    142
 * Dislikes: 8
 * Total Accepted:    9.5K
 * Total Submissions: 16.6K
 * Testcase Example:  '["01","10"]'
 *
 * Given an array of strings nums containing n unique binary strings each of
 * length n, return a binary string of length n that does not appear in nums.
 * If there are multiple answers, you may return any of them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = ["01","10"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "00" would also be correct.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = ["00","01"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "10" would also be correct.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = ["111","011","001"]
 * Output: "101"
 * Explanation: "101" does not appear in nums. "000", "010", "100", and "110"
 * would also be correct.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 1 <= n <= 16
 * nums[i].length == n
 * nums[i] is either '0' or '1'.
 * All the strings of nums are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        Set<String> s = new HashSet<>();
        for(String n : nums){
            s.add(n);
        }
        String ret = rand(nums.length);
        while(s.contains(ret)){
            ret = rand(nums.length);
        }
        return ret;
    }

    String rand(int n){
        String s = "";
        Random r = new Random();
        for(int i = 0; i < n; i++){
            s += r.nextBoolean() ? '1' : '0';
        }
        return s;
    }
}
// @lc code=end
