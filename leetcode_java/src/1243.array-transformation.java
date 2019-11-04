/*
 * @lc app=leetcode id=1243 lang=java
 *
 * [1243] Array Transformation
 *
 * https://leetcode.com/problems/array-transformation/description/
 *
 * algorithms
 * Easy (50.90%)
 * Likes:    15
 * Dislikes: 5
 * Total Accepted:    2.6K
 * Total Submissions: 5K
 * Testcase Example:  '[6,2,3,4]\r'
 *
 * Given an initial array arr, every day you produce a new array using the
 * array of the previous day.
 * 
 * On the i-th day, you do the following operations on the array of day i-1 to
 * produce the array of day i:
 * 
 * 
 * If an element is smaller than both its left neighbor and its right neighbor,
 * then this element is incremented.
 * If an element is bigger than both its left neighbor and its right neighbor,
 * then this element is decremented.
 * The first and last elements never change.
 * 
 * 
 * After some days, the array does not change. Return that final array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [6,2,3,4]
 * Output: [6,3,3,4]
 * Explanation: 
 * On the first day, the array is changed from [6,2,3,4] to [6,3,3,4].
 * No more operations can be done to this array.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,6,3,4,3,5]
 * Output: [1,4,4,4,4,5]
 * Explanation: 
 * On the first day, the array is changed from [1,6,3,4,3,5] to [1,5,4,3,4,5].
 * On the second day, the array is changed from [1,5,4,3,4,5] to [1,4,4,4,4,5].
 * No more operations can be done to this array.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> transformArray(int[] arr) {
        List<Integer> res = Arrays.stream(arr).boxed().collect(Collectors.toList());
        while(changed){
        	changed = false;
        	res = transformArray(res);
        }
        return res;
    }

    boolean changed = true;
    public List<Integer> transformArray(List<Integer> arr) {
    	List<Integer> res = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
        	if(i == 0 || i == arr.size() - 1){
        		res.add(arr.get(i));
        	}
        	else{
        		if(arr.get(i - 1) > arr.get(i) && arr.get(i) < arr.get(i + 1)){
        			res.add(arr.get(i) + 1);
        			changed = true;
        		}
        		else if(arr.get(i - 1) < arr.get(i) && arr.get(i) > arr.get(i + 1)){
        			res.add(arr.get(i) - 1);
        			changed = true;
        		}
        		else{
        			res.add(arr.get(i));	
        		}
        	}
        }
        return res;	
    }
}
// @lc code=end
