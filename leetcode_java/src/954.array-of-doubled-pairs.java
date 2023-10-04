/*
 * @lc app=leetcode id=954 lang=java
 *
 * [954] Array of Doubled Pairs
 *
 * https://leetcode.com/problems/array-of-doubled-pairs/description/
 *
 * algorithms
 * Medium (39.07%)
 * Likes:    1469
 * Dislikes: 144
 * Total Accepted:    84K
 * Total Submissions: 215.1K
 * Testcase Example:  '[3,1,3,6]'
 *
 * Given an integer array of even length arr, return true if it is possible to
 * reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i <
 * len(arr) / 2, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [3,1,3,6]
 * Output: false
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [2,1,2,6]
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4]
 * or [2,4,-2,-4].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= arr.length <= 3 * 10^4
 * arr.length is even.
 * -10^5 <= arr[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canReorderDoubled(int[] arr) {
        List<Integer> positives = new ArrayList<>();
        List<Integer> convertedPositives = new ArrayList<>();
        for(int x : arr){
            if(x < 0){
                convertedPositives.add(-x);
            }
            else{
                positives.add(x);
            }
        }
        return canReorderDoubledPositive(positives) && canReorderDoubledPositive(convertedPositives);
    }
    
    boolean canReorderDoubledPositive(List<Integer> nums) {
        Collections.sort(nums);
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int x : nums){
            if(x % 2 == 0 && cnt.containsKey(x / 2)){
                int c = cnt.get(x / 2) - 1;
                if(c == 0){
                    cnt.remove(x / 2);
                }
                else{
                    cnt.put(x / 2, c);
                }
            }
            else{
                cnt.put(x , cnt.getOrDefault(x, 0) + 1);
            }
        }
        return cnt.isEmpty();
    }
}
// @lc code=end
