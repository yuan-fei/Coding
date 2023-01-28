/*
 * @lc app=leetcode id=825 lang=java
 *
 * [825] Friends Of Appropriate Ages
 *
 * https://leetcode.com/problems/friends-of-appropriate-ages/description/
 *
 * algorithms
 * Medium (46.35%)
 * Likes:    619
 * Dislikes: 1109
 * Total Accepted:    70.1K
 * Total Submissions: 151.3K
 * Testcase Example:  '[16,16]'
 *
 * There are n persons on a social media website. You are given an integer
 * array ages where ages[i] is the age of the i^th person.
 * 
 * A Person x will not send a friend request to a person y (x != y) if any of
 * the following conditions is true:
 * 
 * 
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * 
 * 
 * Otherwise, x will send a friend request to y.
 * 
 * Note that if x sends a request to y, y will not necessarily send a request
 * to x. Also, a person will not send a friend request to themself.
 * 
 * Return the total number of friend requests made.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: ages = [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: ages = [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: ages = [20,30,100,110,120]
 * Output: 3
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 ->
 * 100.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == ages.length
 * 1 <= n <= 2 * 10^4
 * 1 <= ages[i] <= 120
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int left = 0;
        int ans = 0;
        for(int i = 0; i < ages.length; i++){
            while(left < i && ages[left] <= ages[i] / 2 + 7){
                left++;
            }
            ans += i - left;
        }
        left = 0;
        for(int i = 0; i < ages.length; i++){
            if(ages[i] != ages[left]){
                if(ages[left] > ages[i - 1] / 2 + 7){
                    // System.out.print(ages[left]);
                    ans += (i - left) * (i - left - 1) / 2;
                    // System.out.println(ans);
                }
                left = i;
            }
        }
        if(ages[left] > ages[ages.length - 1] / 2 + 7){
            ans += (ages.length - left) * (ages.length - left - 1) / 2;
        }
        return ans;
    }
}
// @lc code=end
