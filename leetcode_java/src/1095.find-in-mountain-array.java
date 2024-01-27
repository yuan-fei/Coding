/*
 * @lc app=leetcode id=1095 lang=java
 *
 * [1095] Find in Mountain Array
 *
 * https://leetcode.com/problems/find-in-mountain-array/description/
 *
 * algorithms
 * Hard (35.26%)
 * Likes:    2072
 * Dislikes: 78
 * Total Accepted:    62.9K
 * Total Submissions: 178.8K
 * Testcase Example:  '[1,2,3,4,5,3,1]\n3'
 *
 * (This problem is an interactive problem.)
 * 
 * You may recall that an array arr is a mountain array if and only if:
 * 
 * 
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * 
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 
 * 
 * 
 * 
 * Given a mountain array mountainArr, return the minimum index such that
 * mountainArr.get(index) == target. If such an index does not exist, return
 * -1.
 * 
 * You cannot access the mountain array directly. You may only access the array
 * using a MountainArray interface:
 * 
 * 
 * MountainArray.get(k) returns the element of the array at index k
 * (0-indexed).
 * MountainArray.length() returns the length of the array.
 * 
 * 
 * Submissions making more than 100 calls to MountainArray.get will be judged
 * Wrong Answer. Also, any solutions that attempt to circumvent the judge will
 * result in disqualification.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the
 * minimum index, which is 2.
 * 
 * Example 2:
 * 
 * 
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= mountain_arr.length() <= 10^4
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 * 
 * 
 */

// @lc code=start
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        int p = getPeak(mountainArr);
        // System.out.println(p);
        int ans = searchTarget(target, mountainArr, 0, p, Integer::compare);
        // System.out.println(ans);
        if(ans == -1){
            ans = searchTarget(target, mountainArr, p, len, (a, b) -> Integer.compare(b, a));
        }
        // System.out.println(ans);
        return ans;
    }

    int getPeak(MountainArray mountainArr){
        int len = mountainArr.length();
        int low  = 0;
        int high = len - 1;
        while(low + 1 < high){
            int mid = (low + high) / 2;
            if(mid + 1 == len){
                high = mid;
            }
            else{
                if(mountainArr.get(mid) < mountainArr.get(mid + 1)){
                    low = mid;
                }
                else{
                    high = mid;
                }
            }
        }
        if(mountainArr.get(low) < mountainArr.get(high)){
            return high;
        }
        return low;
    }

    int searchTarget(int target, MountainArray mountainArr, int start, int end, Comparator<Integer> comparator){
        int low = start;
        int high = end - 1;
        while(low + 1 < high){
            int mid = (low + high) / 2;
            if(comparator.compare(mountainArr.get(mid), target) < 0){
                low = mid;
            }
            else{
                high = mid;
            }
        }
        if(mountainArr.get(low) == target){
            return low;
        }
        else if(mountainArr.get(high) == target){
            return high;
        }
        return -1;
    }
}
// @lc code=end
