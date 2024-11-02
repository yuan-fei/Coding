/*
 * @lc app=leetcode id=2910 lang=java
 *
 * [2910] Minimum Number of Groups to Create a Valid Assignment
 *
 * https://leetcode.com/problems/minimum-number-of-groups-to-create-a-valid-assignment/description/
 *
 * algorithms
 * Medium (24.95%)
 * Likes:    372
 * Dislikes: 179
 * Total Accepted:    13.1K
 * Total Submissions: 52.1K
 * Testcase Example:  '[3,2,3,2,3]'
 *
 * You are given a collection of numbered balls and instructed to sort them
 * into boxes for a nearly balanced distribution. There are two rules you must
 * follow:
 * 
 * 
 * Balls with the same box must have the same value. But, if you have more than
 * one ball with the same number, you can put them in different boxes.
 * The biggest box can only have one more ball than the smallest box.
 * 
 * 
 * ​Return the fewest number of boxes to sort these balls following these
 * rules.
 * 
 * 
 * Example 1: 
 * 
 * 
 * Input:   balls = [3,2,3,2,3] 
 * 
 * Output:   2 
 * 
 * Explanation:
 * 
 * We can sort balls into boxes as follows:
 * 
 * 
 * [3,3,3]
 * [2,2]
 * 
 * 
 * The size difference between the two boxes doesn't exceed one.
 * 
 * 
 * Example 2: 
 * 
 * 
 * Input:   balls = [10,10,10,3,1,1] 
 * 
 * Output:   4 
 * 
 * Explanation:
 * 
 * We can sort balls into boxes as follows:
 * 
 * 
 * 
 * 
 * 
 * [10]
 * [10,10]
 * [3]
 * [1,1]
 * 
 * 
 * You can't use fewer than four boxes while still following the rules. For
 * example, putting all three balls numbered 10 in one box would break the rule
 * about the maximum size difference between boxes.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minGroupsForValidAssignment(int[] balls) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int x : balls){
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        int min = balls.length;
        for(int f : freq.values()){
            min = Math.min(f, min);
        }
        int ans = balls.length;
        List<Integer> values = new ArrayList<>(freq.values());
        for(int curMin = 1; curMin <= min; curMin++){
            int g = getGroups(values, curMin);
            if(g != -1){
                ans = Math.min(ans, g);    
            }
        }
        return ans;
    }

    int getGroups(List<Integer> values, int min){
        int ret = 0;
        for(int x : values){
            int integral = x / (min + 1);
            int reminder = x % (min + 1);
            if(reminder == 0 || integral >= (min - reminder)){
                ret += (x + min) / (min + 1);
            }
            else{
                return -1;
            }
        }
        return ret;
    }
}
// @lc code=end
