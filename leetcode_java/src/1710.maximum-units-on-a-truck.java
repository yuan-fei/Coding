/*
 * @lc app=leetcode id=1710 lang=java
 *
 * [1710] Maximum Units on a Truck
 *
 * https://leetcode.com/problems/maximum-units-on-a-truck/description/
 *
 * algorithms
 * Easy (73.15%)
 * Likes:    64
 * Dislikes: 3
 * Total Accepted:    7.2K
 * Total Submissions: 9.8K
 * Testcase Example:  '[[1,3],[2,2],[3,1]]\n4'
 *
 * You are assigned to put some amount of boxes onto one truck. You are given a
 * 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi,
 * numberOfUnitsPerBoxi]:
 * 
 * 
 * numberOfBoxesi is the number of boxes of type i.
 * numberOfUnitsPerBoxi is the number of units in each box of the type i.
 * 
 * 
 * You are also given an integer truckSize, which is the maximum number of
 * boxes that can be put on the truck. You can choose any boxes to put on the
 * truck as long as the number of boxes does not exceed truckSize.
 * 
 * Return the maximum total number of units that can be put on the truck.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 * Output: 8
 * Explanation: There are:
 * - 1 box of the first type that contains 3 units.
 * - 2 boxes of the second type that contain 2 units each.
 * - 3 boxes of the third type that contain 1 unit each.
 * You can take all the boxes of the first and second types, and one box of the
 * third type.
 * The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
 * Output: 91
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= boxTypes.length <= 1000
 * 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
 * 1 <= truckSize <= 10^6
 * 
 */

// @lc code=start
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> -Integer.compare(a[1], b[1]));
        int j = 0;
        int ans = 0;
        for(int i = 0; i < truckSize; i++){
        	if(j >= boxTypes.length){
    			break;
    		}
    		else{
    			ans += boxTypes[j][1];
    			boxTypes[j][0]--;
    		}
        	if(boxTypes[j][0] == 0){
        		j++;	
        	}
        }
        return ans;
    }
}
// @lc code=end
