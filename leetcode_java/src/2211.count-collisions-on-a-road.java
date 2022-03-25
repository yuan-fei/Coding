/*
 * @lc app=leetcode id=2211 lang=java
 *
 * [2211] Count Collisions on a Road
 *
 * https://leetcode.com/problems/count-collisions-on-a-road/description/
 *
 * algorithms
 * Medium (35.37%)
 * Likes:    108
 * Dislikes: 95
 * Total Accepted:    7.3K
 * Total Submissions: 20.6K
 * Testcase Example:  '"RLRSLL"'
 *
 * There are n cars on an infinitely long road. The cars are numbered from 0 to
 * n - 1 from left to right and each car is present at a unique point.
 * 
 * You are given a 0-indexed string directions of length n. directions[i] can
 * be either 'L', 'R', or 'S' denoting whether the i^th car is moving towards
 * the left, towards the right, or staying at its current point respectively.
 * Each moving car has the same speed.
 * 
 * The number of collisions can be calculated as follows:
 * 
 * 
 * When two cars moving in opposite directions collide with each other, the
 * number of collisions increases by 2.
 * When a moving car collides with a stationary car, the number of collisions
 * increases by 1.
 * 
 * 
 * After a collision, the cars involved can no longer move and will stay at the
 * point where they collided. Other than that, cars cannot change their state
 * or direction of motion.
 * 
 * Return the total number of collisions that will happen on the road.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: directions = "RLRSLL"
 * Output: 5
 * Explanation:
 * The collisions that will happen on the road are:
 * - Cars 0 and 1 will collide with each other. Since they are moving in
 * opposite directions, the number of collisions becomes 0 + 2 = 2.
 * - Cars 2 and 3 will collide with each other. Since car 3 is stationary, the
 * number of collisions becomes 2 + 1 = 3.
 * - Cars 3 and 4 will collide with each other. Since car 3 is stationary, the
 * number of collisions becomes 3 + 1 = 4.
 * - Cars 4 and 5 will collide with each other. After car 4 collides with car
 * 3, it will stay at the point of collision and get hit by car 5. The number
 * of collisions becomes 4 + 1 = 5.
 * Thus, the total number of collisions that will happen on the road is 5. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: directions = "LLRR"
 * Output: 0
 * Explanation:
 * No cars will collide with each other. Thus, the total number of collisions
 * that will happen on the road is 0.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= directions.length <= 10^5
 * directions[i] is either 'L', 'R', or 'S'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countCollisions(String directions) {
        
        int ans = 0;
        char[] dir = directions.toCharArray();
        ans = count(dir);
        int i = 0;
        int j = directions.length() - 1;
        while(i < j){
            char t = dir[i];
            dir[i] = dir[j];
            dir[j] = t;
            i++;
            j--;
        }
        for(i = 0; i < directions.length(); i++){
            if(dir[i] == 'R'){
                dir[i] = 'L';
            }
            else if(dir[i] == 'L'){
                dir[i] = 'R';
            }
        }
        ans += count(dir);
        return ans;
    }

    int count(char[] directions){
        // System.out.println(Arrays.toString(directions));
        int ans = 0;
        char prev = 'L';
        for(int i = 0; i < directions.length; i++){
            if(directions[i] == 'L'){
                if(prev == 'S'){
                    ans++;
                    prev = 'S';
                }
                else if(prev == 'R'){
                    ans += 2;
                    prev = 'S';
                    directions[i - 1] = 'S';
                }
                else{
                    prev = 'L';
                }
            }
            else if(directions[i] == 'S'){
                if(prev == 'R'){
                    ans++;
                    prev = 'S';
                    directions[i - 1] = 'S';
                }
                else{
                    prev = 'S';
                }
            }
            else{
                prev = 'R';
            }
            directions[i] = prev;
            // System.out.println(i + ", " + ans);
        }
        // System.out.println(Arrays.toString(directions));
        return ans;
    }

}
// @lc code=end
